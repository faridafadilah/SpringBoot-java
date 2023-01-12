package com.movie.feign.moviefeign.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.movie.feign.moviefeign.dto.Base;
import com.movie.feign.moviefeign.dto.DetailsAccount;
import com.movie.feign.moviefeign.dto.Favorite;
import com.movie.feign.moviefeign.dto.Watchlist;

@Service
public class RestClientService {
  @Autowired
  private RestTemplate restTemplate;

  //Get Detail Account
  public ResponseEntity<DetailsAccount> getDetailAccount() {
    DetailsAccount detailsAccount = restTemplate.getForObject("https://api.themoviedb.org/3/account?api_key=088a595cff499817542e784ddf51c732&session_id=5f1cca75cf66c33d8218d60b7a5505dfc9679e1a", DetailsAccount.class);
    return ResponseEntity.ok(detailsAccount);
  }

  //Get Watchlist
  public ResponseEntity<Base> getAllWatchlist(int account_id, int page) {
    Base base = restTemplate.getForObject("https://api.themoviedb.org/3/account/"+account_id+"/watchlist/movies?api_key=088a595cff499817542e784ddf51c732&language=en-US&session_id=5f1cca75cf66c33d8218d60b7a5505dfc9679e1a&sort_by=created_at.asc&page="+page, Base.class);
    return ResponseEntity.ok(base);
  }

  //Add Watchlis
  public ResponseEntity<Watchlist> addWatchlist(Watchlist watchlist, int account_id) {
    Watchlist response = restTemplate.postForObject("https://api.themoviedb.org/3/account/"+account_id+"/watchlist?api_key=088a595cff499817542e784ddf51c732&session_id=5f1cca75cf66c33d8218d60b7a5505dfc9679e1a", watchlist, Watchlist.class);
    return ResponseEntity.ok(response);
  }

  //Add Favorite
  public ResponseEntity<Favorite> addFavorite(Favorite favorite, int account_id) {
    Favorite response = restTemplate.postForObject("https://api.themoviedb.org/3/account/"+account_id+"/favorite?api_key=088a595cff499817542e784ddf51c732&session_id=5f1cca75cf66c33d8218d60b7a5505dfc9679e1a", favorite, Favorite.class);
    return ResponseEntity.ok(response);
  }
}
