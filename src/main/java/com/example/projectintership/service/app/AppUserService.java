package com.example.projectintership.service.app;

import com.example.projectintership.model.user.AppRole;
import com.example.projectintership.model.user.AppUser;
import com.example.projectintership.repository.user.IAppUserRepo;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

  @Autowired
  IAppUserRepo appUserRepository;

  public List<AppUser> findAll(){
    return (List<AppUser>) appUserRepository.findAll();
  }

  public AppUser findById(Long id){
    return appUserRepository.findById(id).get();
  }

  public boolean add(AppUser user){
    List<AppUser> listUser = findAll();
    for (AppUser userExist : listUser){
      if (user.getId() == userExist.getId() || StringUtils.equals(user.getUsername(), userExist.getUsername())){
        return false;
      }
    }
    appUserRepository.save(user);
    return true;
  }

  public void delete(Long id){
    appUserRepository.deleteById(id);
  }

  public AppUser loadUserByUsername(String username){
    return appUserRepository.findByUsername(username);
  }

  public boolean checkLogin(AppUser user){
    List<AppUser> listUser =   findAll();
    for (AppUser userexist : listUser){
      if (StringUtils.equals(user.getName(), userexist.getUsername()) && StringUtils.equals(user.getPassword(), userexist.getPassword())){
        return true;
      }
    }
    return false;
  }

  public Iterable<AppUser> findUserByRole(AppRole role){
    return appUserRepository.myUserQuery(role);
  }

  public Iterable<AppUser> findSellByRole(AppRole role){
    return appUserRepository.mySellerQuery(role);
  }
}
