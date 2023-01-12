package com.openfeign.learn.learnopenfeign.hystrix;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.openfeign.learn.learnopenfeign.feign.JSONPlaceHolderClient;
import com.openfeign.learn.learnopenfeign.model.Post;


@Component
public class JSONPlaceHolderFallback implements JSONPlaceHolderClient {
  @Override
  public List<Post> getPosts() {
    return Collections.emptyList();
  }

  @Override
  public Post getPostById(Long postId) {
    return null;
  }
}
