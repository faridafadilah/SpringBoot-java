package com.movie.feign.moviefeign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.movie.feign.moviefeign.config.ClientConfiguration;
import com.movie.feign.moviefeign.hystrix.MovieFallback;
import com.movie.feign.moviefeign.model.DetailMovie;
import com.movie.feign.moviefeign.model.Movie;


@FeignClient(value = "movies", 
            url = "https://api.themoviedb.org/3/movie",
            configuration = ClientConfiguration.class,
            fallback = MovieFallback.class)
public interface MovieClient {
  @RequestMapping(value = "/{id}/lists?api_key=088a595cff499817542e784ddf51c732&page={page}", method = RequestMethod.GET)
  public Movie getListMovie(@PathVariable("id") String id, @PathVariable(required = false) Integer page);

  @RequestMapping(method = RequestMethod.GET, value = "/{id}?api_key=088a595cff499817542e784ddf51c732")
  DetailMovie getDetailMovie(@PathVariable("id") String id);
}
