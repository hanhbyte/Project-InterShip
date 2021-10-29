package com.example.projectintership.api;

import com.example.projectintership.model.user.AppRole;
import com.example.projectintership.model.user.AppUser;
import com.example.projectintership.service.app.AppRoleService;
import com.example.projectintership.service.app.AppUserService;
import com.example.projectintership.service.jwt.JwtService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class RestAdminController {

  @Autowired
  private JwtService jwtService;
  @Autowired
  private AppUserService userService;
  @Autowired
  private AppRoleService roleService;

  /*--------GET ALL USER--------*/
  @RequestMapping(value = "/users", method = RequestMethod.GET)
  public ResponseEntity<List<AppUser>> getAllUser() {
    return new ResponseEntity<List<AppUser>>(userService.findAll(), HttpStatus.OK);
  }

  /*--------GET USER BY ID--------*/
  @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
  public ResponseEntity<Object> getUserById(@PathVariable Long id) {
    AppUser user = userService.findById(id);
    if (user != null) {
      return new ResponseEntity<Object>(user, HttpStatus.OK);
    }
    return new ResponseEntity<Object>("Not Found User", HttpStatus.NO_CONTENT);
  }

  /*--------CREATE NEW USER--------*/
  @RequestMapping(value = "/users", method = RequestMethod.POST)
  public ResponseEntity<String> createUser(@RequestBody AppUser user) {
    AppRole role = roleService.findById(3L).get();
    System.out.println(role.getName());
    user.setRoll(role);
    if (userService.add(user)) {
      return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
    }
    return new ResponseEntity<String>("User Existed", HttpStatus.BAD_REQUEST);
  }

  /*--------DELETE USER--------*/
  @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
    userService.delete(id);
    return new ResponseEntity<String>("Deleted!", HttpStatus.OK);
  }

  @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
  public ResponseEntity<List<AppUser>> getUserByRole(@PathVariable Long id) {
    AppRole role = roleService.findById(id).get();
    List<AppUser> appUserList = (List<AppUser>) userService.findUserByRole(role);
    return new ResponseEntity<List<AppUser>>(appUserList, HttpStatus.OK);
  }

  @RequestMapping(value = "/role/update/{id}", method = RequestMethod.GET)
  public ResponseEntity<AppUser> lockUser(@PathVariable Long id) {
    AppUser user = userService.findById(id);
    userService.delete(id);
    AppRole role = roleService.findById(4L).get();
    user.setRoll(role);
    user.setStatus("Locked!");
    user.setId(id);
    userService.add(user);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
