package com.example.demo.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping("/api/1.0/users")
  public UserDto createUser(@Valid @RequestBody CreateUserRequest req){
    // userService deve criar User internamente, hashear senha e ignorar campos não permitidos
    return userService.createUser(req);
  }

  @PutMapping("/api/1.0/users/{id}")
  @PreAuthorize("@userAuthorizationService.canUpdate(#id) or hasRole('admin')")
  public UserDto updateUser(@PathVariable long id, @Valid @RequestBody UpdateUserRequest req){
    // userService faz validação adicional e ignora campos proibidos
    return userService.updateUser(id, req);
  }
  
}
