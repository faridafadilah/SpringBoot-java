package com.movie.feign.moviefeign.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Results {
  private String description;
  private int favorite_count;
  private int id;
  private int item_count;
  private String iso_639_1;
  private String list_type;
  private String name;
  private String poster_path;
}
