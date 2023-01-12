package com.movie.feign.moviefeign.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DetailMovie {
  private boolean adult;
  private String backdrop_path;
  private BelongsToCollection belongs_to_collection;
  private int budget;
  private List<Genres> genres;
  private String homepage;
  private int id;
  private String imdb_id;
  private String original_language;
  private String original_title;
  private String overview;
  private Float popularity;
  private String poster_path;
  private List<ProductionCompanies> production_companies;
  private List<ProductionCountries> production_countries;
  private String release_date;
  private int revenue;
  private int runtime;
  private List<SpokenLanguages> spoken_languages;
  private String status;
  private String tagline;
  private String title;
  private boolean video;
  private Float vote_average;
  private int vote_count;
}
