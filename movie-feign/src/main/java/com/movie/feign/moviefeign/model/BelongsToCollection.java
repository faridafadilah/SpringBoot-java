package com.movie.feign.moviefeign.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BelongsToCollection {
  private int id;
  private String name;
  private String poster_path;
  private String backdrop_path;
}
