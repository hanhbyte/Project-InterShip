package com.example.projectintership.repository.user;

import com.example.projectintership.model.user.AppRole;
import com.example.projectintership.model.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppUserRepo extends JpaRepository<AppUser, Long> {
  AppUser findByUsername(String username);

  @Query("select a from AppUser a where a.roll = ?1")
  Iterable<AppUser> mySellerQuery(AppRole roll);

  @Query("select a from AppUser a where a.roll = ?1")
  Iterable<AppUser> myUserQuery(AppRole roll);
}
