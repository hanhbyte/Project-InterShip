package com.example.projectintership.service.app;

import com.example.projectintership.model.user.AppRole;
import com.example.projectintership.repository.user.IAppRollRepo;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppRoleService implements IAppRoleService {

  @Autowired
  IAppRollRepo appRollRepository;

  @Override
  public Iterable<AppRole> findAll() {
    return null;
  }

  @Override
  public Optional<AppRole> findById(Long id) {
    return appRollRepository.findById(id);
  }

  @Override
  public void save(AppRole appRole) {

  }

  @Override
  public void remove(Long id) {

  }
}
