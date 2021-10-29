package com.example.projectintership.securityJWT;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRespo {
  String token;
  private Long user_id;
  private String name;
  private String avatar;
  private String role;
}
