package com.rida.buddyquiz.buddyquiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rida.buddyquiz.buddyquiz.services.QuizService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import com.rida.buddyquiz.buddyquiz.model.*;

@RestController
public class QuizController {
  @Autowired
  private QuizService service;

  // @GetMapping("/")
  //   public String index() {
  //       return "index";
  // }

  @GetMapping("/api/quizzes/{id}")
  public Quiz getQuizById(@PathVariable("id") Long id) {
    return service.getQuizById(id);
  }

  @PostMapping("/api/quizzes/{id}/answer")
  public void checkAnswer(@PathVariable("id") Long id, @RequestBody String answer) {
    service.checkAnswer(id, answer);
  }
}
