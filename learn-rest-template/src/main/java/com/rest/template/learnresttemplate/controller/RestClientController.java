package com.rest.template.learnresttemplate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.template.learnresttemplate.dto.User;
import com.rest.template.learnresttemplate.service.RestClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/users")
public class RestClientController {
  @Autowired
  private RestClientService service;

  @GetMapping("/{id}")
  public ResponseEntity<User> findUserStringById(@PathVariable("id") int id) {
      return service.getUserById(id);
  }
  
  @GetMapping("/")
  public ResponseEntity<?> findAll() {
    return service.getAll();
  }

  @PostMapping("/")
  public ResponseEntity<User> addUser(@RequestBody User user) {
    return service.addUserPost(user);
  }
}
