package com.movie.feign.moviefeign.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.feign.moviefeign.feign.MovieClient;
import com.movie.feign.moviefeign.model.DetailMovie;
import com.movie.feign.moviefeign.model.Movie;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class MovieController {
  private final MovieClient movieClient;
  //Get List Movie
  @GetMapping("/list-movie/{id}")
  public Movie getList(@PathVariable("id") String id,@RequestParam(value = "page", required = false) Integer page) {
    Movie movie = movieClient.getListMovie(id, page);
    return movie;
  }

  //Get Detail Movie
  @GetMapping("/movie-detail/{id}")
  public DetailMovie getDetail(@PathVariable("id") String id) {
    DetailMovie detailMovie = movieClient.getDetailMovie(id);
    return detailMovie;
  }
}
