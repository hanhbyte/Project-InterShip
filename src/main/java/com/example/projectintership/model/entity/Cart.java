package com.example.projectintership.model.entity;

import com.example.projectintership.model.user.AppUser;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id")
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @OneToOne
  @JoinColumn(name = "user_id")
  private AppUser appUser;
  private String code;

  @OneToMany(targetEntity = Product.class, cascade = CascadeType.ALL)
  private List<Product> productList;
  private LocalDate pickupDate;
  private LocalDate orderDate;
  private String status;

  public double getTotelMoney(){
    double total = 0;
    for (int i = 0; i < productList.size(); i++) {
      total += productList.get(i).getQuantity()* productList.get(i).getPrice();
    }
    return total;
  }

  public double getMoneyProduct(Long id){
    double total =0;
    for (int i = 0; i < productList.size(); i++){
      if(productList.get(i).getId()==id){
        total = productList.get(i).getQuantity()* productList.get(i).getPrice();
      }
    }
    return total;
  }
}
