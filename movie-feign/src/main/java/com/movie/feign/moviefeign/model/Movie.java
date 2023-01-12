package com.movie.feign.moviefeign.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Movie {
  private int id;
  private int page;
  private List<Results> results;
  private int total_pages;
  private int total_results;
}
