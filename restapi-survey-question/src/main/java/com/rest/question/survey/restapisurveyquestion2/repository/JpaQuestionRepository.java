package com.rest.question.survey.restapisurveyquestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.rest.question.survey.restapisurveyquestion.entity.QuestionDataMapper;

@Repository
public interface JpaQuestionRepository<T> extends JpaRepository<QuestionDataMapper, String>, JpaSpecificationExecutor<T> {
  
}
