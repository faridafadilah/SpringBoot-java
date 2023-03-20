package com.rida.buddyquiz.buddyquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rida.buddyquiz.buddyquiz.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
  
}
