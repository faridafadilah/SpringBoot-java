package com.rest.question.survey.restapisurveyquestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.rest.question.survey.restapisurveyquestion.entity.SurveyDataMapper;

@Repository
public interface JpaSurveyRepository<T> extends JpaRepository<SurveyDataMapper, String>, JpaSpecificationExecutor<T> {
  
}
