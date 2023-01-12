package com.movie.feign.moviefeign.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductionCompanies {
  private int id;
  private String logo_path;
  private String name;
  private String origin_country;
}
