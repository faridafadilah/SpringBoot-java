package com.movie.feign.moviefeign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.feign.moviefeign.dto.DetailsAccount;
import com.movie.feign.moviefeign.dto.Favorite;
import com.movie.feign.moviefeign.dto.Base;
import com.movie.feign.moviefeign.dto.Watchlist;
import com.movie.feign.moviefeign.service.RestClientService;

@RestController
@RequestMapping("/api/account")
public class AccountController {
  @Autowired
  private RestClientService service;

  //Get Detail Account
  @GetMapping("/")
  public ResponseEntity<DetailsAccount> getDetailAccount() {
    return service.getDetailAccount();
  }

  //Get Watchlist
  @GetMapping("/{account_id}/watchlist/all")
  public ResponseEntity<Base> getAllWatchlist(@PathVariable("account_id") int account_id,  @RequestParam(value = "page", required = false) Integer page) {
    return service.getAllWatchlist(account_id, page);
  }

  //Add Watchlist
  @PostMapping("/{account_id}/watchlist")
  public ResponseEntity<Watchlist> addWatchlist(@RequestBody Watchlist watchlist, @PathVariable("account_id") int account_id) {
    return service.addWatchlist(watchlist, account_id);
  }

  //Add Favorite
  @PostMapping("/{account_id}/favorite")
  public ResponseEntity<Favorite> addFavorite(@RequestBody Favorite favorite, @PathVariable("account_id") int account_id) {
    return service.addFavorite(favorite, account_id);
  }
}
