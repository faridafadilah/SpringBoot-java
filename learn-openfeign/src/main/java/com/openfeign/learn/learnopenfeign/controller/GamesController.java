package com.openfeign.learn.learnopenfeign.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openfeign.learn.learnopenfeign.feign.GamesClient;
import com.openfeign.learn.learnopenfeign.model.Games;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class GamesController {
  private final GamesClient gamesClient;

  @GetMapping("/games/all")
  public List<Games> getAll() {
    List<Games> games = gamesClient.getAllDataGames();
    return games;
  }
}
