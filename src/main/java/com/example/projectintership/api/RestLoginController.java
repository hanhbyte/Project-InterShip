package com.example.projectintership.api;

import com.example.projectintership.model.entity.Cart;
import com.example.projectintership.model.entity.Product;
import com.example.projectintership.model.user.AppRole;
import com.example.projectintership.model.user.AppUser;
import com.example.projectintership.securityJWT.UserRespo;
import com.example.projectintership.service.app.AppRoleService;
import com.example.projectintership.service.app.AppUserService;
import com.example.projectintership.service.jwt.JwtService;
import com.example.projectintership.service.product.IProductService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
@CrossOrigin(origins = "*")
public class RestLoginController {

  @Autowired
  HttpSession httpSession;
  @Autowired
  private JwtService jwtService;
  @Autowired
  private AppUserService userService;
  @Autowired
  private AppRoleService roleService;
  @Autowired
  IProductService productService;

  @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserRespo> login(HttpServletRequest request, @RequestBody AppUser user) {
    AppUser appUser = userService.loadUserByUsername(user.getUsername());
    Long id = appUser.getId();
    String role = appUser.getRoll().getName();
    String result = "";
    HttpStatus httpStatus = null;
    try {
      if (userService.checkLogin(user)){
        result = jwtService.generateTokenLogin(user.getUsername());
        httpStatus = HttpStatus.OK;
      }else {
        result = "Wrong user id and password";
        httpStatus = HttpStatus.BAD_REQUEST;
      }
    }catch (Exception ex){
      result = "Server error";
      httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    UserRespo userRespo = new UserRespo(result, id, user.getName(), user.getAvatar(), role);
    return new ResponseEntity<UserRespo>(userRespo, httpStatus);
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public ResponseEntity<String> Register(@RequestBody AppUser appUser){
    AppRole role = roleService.findById(3L).get();
    System.out.println(role.getName());
    appUser.setRoll(role);
    userService.add(appUser);
    return new ResponseEntity<>("Yes", HttpStatus.OK);
  }

  @GetMapping("/showProduct")
  public ResponseEntity<Iterable<Product>> listProduct(){
    Iterable<Product> listProduct = productService.findAll();
    return new ResponseEntity<>(listProduct, HttpStatus.OK);
  }

  @GetMapping("/findAllByName")
  public ResponseEntity<Iterable<Product>> listByNameProduct(@RequestParam("name")String name){
    Iterable<Product> listByNameProduct = productService.findAllByNameContaining(name);
    return new ResponseEntity<>(listByNameProduct, HttpStatus.OK);
  }

  @GetMapping("/addtoCart")
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
      for (Product products : productList){
        if (products.getId() == product.getId()){
          products.setQuantity(products.getQuantity() + quantity);
          check = true;
        }
      }
      if (check = false){
        product.setQuantity(quantity);
        productList.add(product);
      }
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
}
