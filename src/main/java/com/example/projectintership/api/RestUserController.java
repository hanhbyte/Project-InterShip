package com.example.projectintership.api;

import com.example.projectintership.model.entity.Cart;
import com.example.projectintership.model.entity.Comment;
import com.example.projectintership.model.entity.Product;
import com.example.projectintership.service.com.CommentService;
import com.example.projectintership.service.product.IProductService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class RestUserController {
  @Autowired
  HttpSession httpSession;

  @Autowired
  IProductService productService;

  @Autowired
  CommentService commentService;

  @GetMapping("/findAllByPrice/")
  public ResponseEntity<Iterable<Product>> listByPriceProduct(@Param("lowestPrice") double lowestPrice, @Param("highestPrice")double highestPrice){
    Iterable<Product> listProductByPrice = productService.findAllByPriceBetween(lowestPrice, highestPrice);
    return new ResponseEntity<>(listProductByPrice, HttpStatus.OK);
  }

  @GetMapping("/addtoCart/{id}")
  public ResponseEntity<Cart> addtoCart(@PathVariable Long id){
    int quantity = 1;
    Product product = productService.findById(id).get();
    if (httpSession.getAttribute("Hanh") == null){
      Cart cart = new Cart();
      List<Product> productList = new ArrayList<>();
      product.setQuantity(quantity);
      productList.add(product);
      cart.setProductList(productList);
      httpSession.setAttribute("Hanh", cart);
    }else {
      Cart cart = (Cart) httpSession.getAttribute("Hanh");
      List<Product> productList = cart.getProductList();
      boolean check = false;
      for (Product product1 : productList){
        if (product1.getId() == product.getId()){
          product1.setQuantity(product1.getQuantity() + quantity);
          check = true;
        }
      }
      if (check = false){
        product.setQuantity(quantity);
        productList.add(product);
      }
      cart.setProductList(productList);
      httpSession.setAttribute("Hanh", cart);
    }
    Cart cart = (Cart) httpSession.getAttribute("Hanh");
    return new ResponseEntity<>(cart, HttpStatus.OK);
  }

  @GetMapping("/showCart")
  public ResponseEntity<Cart> showCart(){
    Cart cart = (Cart) httpSession.getAttribute("Hanh");
    return new ResponseEntity<>(cart, HttpStatus.OK);
  }

  @PostMapping("/createComment")
  public ResponseEntity<Comment> createComment(Comment comment){
    commentService.createComment(comment);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/listCommentProduct/{id}")
  public ResponseEntity<List<Comment>> listCommentProduct(@PathVariable Long id){
    Product product = productService.findById(id).get();
    List<Comment> list = (List<Comment>) commentService.findAllByProduct(product);
    System.out.println(list.size());
    return new ResponseEntity<>(list, HttpStatus.OK);
  }
}
