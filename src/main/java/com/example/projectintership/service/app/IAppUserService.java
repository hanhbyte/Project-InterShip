package com.example.projectintership.service.app;

import com.example.projectintership.model.user.AppUser;
import com.example.projectintership.service.IGeneralService;

public interface IAppUserService extends IGeneralService<AppUser> {
  AppUser getUserByUsername(String username);
  AppUser getCurrentUser();
  Iterable<AppUser> findAppUserByRole();
}
