package com.example.projectintership.repository.user;

import com.example.projectintership.model.user.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppRollRepo extends JpaRepository<AppRole, Long> {

}
