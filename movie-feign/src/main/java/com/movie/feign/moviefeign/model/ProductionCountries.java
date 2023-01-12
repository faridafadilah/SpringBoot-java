package com.movie.feign.moviefeign.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductionCountries {
  private String iso_3166_1;
  private String name;
}
