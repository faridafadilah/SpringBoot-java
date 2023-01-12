package com.movie.feign.moviefeign.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DetailsAccount {
  private Avatar avatar;
  private int id;
  private String iso_639_1;
  private String iso_3166_1;
  private String name;
  private boolean include_adult;
  private String username;
}
