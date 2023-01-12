package com.movie.feign.moviefeign.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Avatar {
  private Gravatar gravatar;
  private Tmdb tmdb;
}
