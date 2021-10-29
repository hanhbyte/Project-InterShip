package com.example.projectintership.model.user;

import com.example.projectintership.model.entity.Cart;
import com.example.projectintership.model.entity.Comment;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Data
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
public class AppUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String username;
  private String password;
  private String phoneNumber;
  private String address;
  private String avatar;
  private String email;
  private String status;

  @ManyToOne
  private AppRole roll;

  @OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL)
  private Cart cart;

  @OneToOne(targetEntity = Comment.class, cascade = CascadeType.ALL)
  private List<Comment> commentList;

  public Collection<? extends GrantedAuthority> getAuthorities(){
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    authorities.add(new SimpleGrantedAuthority(roll.getAuthority()));
    return authorities;
  }
}
