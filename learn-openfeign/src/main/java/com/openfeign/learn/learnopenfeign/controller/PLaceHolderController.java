package com.openfeign.learn.learnopenfeign.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.openfeign.learn.learnopenfeign.feign.JSONPlaceHolderClient;
import com.openfeign.learn.learnopenfeign.model.Post;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class PLaceHolderController {
  private final JSONPlaceHolderClient jsonPlaceHolderClient;

  @GetMapping("/user")
  public List<Post> getAll() {
    List<Post> user = jsonPlaceHolderClient.getPosts();
    return user;
  }

  @GetMapping("/user/{id}")
  public Post getById(@PathVariable("id") Long id) {
    Post post = jsonPlaceHolderClient.getPostById(id);
    return post;
  }
}
