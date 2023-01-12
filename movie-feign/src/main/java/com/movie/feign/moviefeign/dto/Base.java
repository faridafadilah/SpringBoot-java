package com.movie.feign.moviefeign.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Base {
  private int id;
  private int page;
  private List<ListWatchlist> results;
  private int total_pages;
  private int total_results;
}
