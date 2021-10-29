package com.example.projectintership.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private double price;
  private int quantity;
  private int number;
  private String description;
  private String image;
  private String category;

  @ManyToOne
  @JoinColumn(name = "cart_id")
  private Cart cart;

  @OneToMany(targetEntity = Compound.class, cascade = CascadeType.ALL)
  private List<Compound> compoundList;

  @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL)
  private List<Comment> commentList;
}
