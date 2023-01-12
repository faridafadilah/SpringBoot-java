package com.openfeign.learn.learnopenfeign.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.openfeign.learn.learnopenfeign.model.Games;

@FeignClient(value = "games" , url = "https://the-lazy-media-api.vercel.app")
public interface GamesClient {

  @RequestMapping(value = "/api/games/console-game",method = RequestMethod.GET)
  public List<Games> getAllDataGames();
}
