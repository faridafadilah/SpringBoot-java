package com.movie.feign.moviefeign.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Watchlist {
  private String media_type;
  private int media_id;
  private boolean watchlist;
}
