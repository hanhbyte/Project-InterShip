package com.example.projectintership.api;

import com.example.projectintership.model.entity.Compound;
import com.example.projectintership.model.entity.Product;
import com.example.projectintership.model.user.AppUser;
import com.example.projectintership.service.app.AppUserService;
import com.example.projectintership.service.com.CompoundService;
import com.example.projectintership.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
@CrossOrigin(origins = "*")
public class RestSellerAdmin {

  @Autowired
  private IProductService productService;
  @Autowired
  private AppUserService userService;
  @Autowired
  private CompoundService compoundService;

  @PostMapping("/product/save")
  public ResponseEntity<Product> saveProduct(@RequestBody Product product){
    AppUser user = userService.findById(2L);
    productService.save(product);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/product/delete/{id}")
  public ResponseEntity<Product> deleteProduct(@PathVariable Long id){
    productService.remove(id);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

  @PostMapping("/listPage")
  public  ResponseEntity<Page<Product>> listPageProduct(@RequestParam String page){
    Pageable pageable = PageRequest.of(Integer.parseInt(page), 5);
    Page<Product> postPage = productService.findAll(pageable);
    return new ResponseEntity<>(postPage, HttpStatus.OK);
  }

  @PostMapping("/createCompound")
  public ResponseEntity<Compound> createCompound(Compound compound){
    compoundService.save(compound);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
