package com.movie.feign.moviefeign.hystrix;

import org.springframework.stereotype.Component;

import com.movie.feign.moviefeign.feign.MovieClient;
import com.movie.feign.moviefeign.model.DetailMovie;
import com.movie.feign.moviefeign.model.Movie;

@Component
public class MovieFallback implements MovieClient {
  @Override
  public Movie getListMovie(String movieId, Integer page) {
    return null;
  }

  @Override
  public DetailMovie getDetailMovie(String movieId) {
    return null;
  }
}
