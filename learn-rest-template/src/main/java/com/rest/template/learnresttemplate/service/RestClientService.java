package com.rest.template.learnresttemplate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rest.template.learnresttemplate.dto.User;

@Service
public class RestClientService {
  
  @Autowired
  private RestTemplate restTemplate;

  // public ResponseEntity<String> getUserById(int id) {
  //   ResponseEntity<String> response = restTemplate
  //       .getForEntity("https://jsonplaceholder.typicode.com/users/"+id, String.class);
  //     return response;
  // }

  public ResponseEntity<User> getUserById(int id) {
    User user = restTemplate.getForObject("https://jsonplaceholder.typicode.com/users/"+id, User.class);
    return ResponseEntity.ok(user);
  }

  public ResponseEntity<User[]> getAll() {
    ResponseEntity<User[]> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/users", User[].class);
    return response;
  }

  public ResponseEntity<User> addUserPost(User user) {
    User response = restTemplate.postForObject("https://jsonplaceholder.typicode.com/users", user, User.class);
    return ResponseEntity.ok(response);
  }
}
