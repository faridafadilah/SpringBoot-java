package com.rida.buddyquiz.buddyquiz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rida.buddyquiz.buddyquiz.model.Quiz;
import com.rida.buddyquiz.buddyquiz.repository.QuizRepository;
import java.util.*;


@Service
public class QuizService {
  @Autowired
  private QuizRepository repository;

  public Quiz getQuizById(Long id) {
    Optional<Quiz> Opquiz = repository.findById(id);
    Quiz quiz = Opquiz.get();
    return quiz;
  }

  public boolean checkAnswer(Long id, String answer) {
    Quiz quiz = getQuizById(id);
    return quiz.getAnswer().equals(answer);
  }
}
