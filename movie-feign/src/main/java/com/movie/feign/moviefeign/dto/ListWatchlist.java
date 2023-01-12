package com.movie.feign.moviefeign.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ListWatchlist {
  private boolean adult;
  private String backdrop_path;
  private List<Integer> genre_ids;
  private int id;
  private String original_language;
  private String original_title;
  private String overview;
  private Float popularity;
  private String poster_path;
  private String release_date;
  private String title;
  private boolean video;
  private Float vote_average;
  private int vote_count;
}
