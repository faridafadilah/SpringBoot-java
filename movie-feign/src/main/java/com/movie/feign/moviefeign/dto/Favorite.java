package com.movie.feign.moviefeign.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Favorite {
  private String media_type;
  private int media_id;
  private boolean favorite;
}
