package com.rest.question.survey.restapisurveyquestion.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.rest.question.survey.restapisurveyquestion.models.Survey;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, String> {
  @Query(value = "SELECT * FROM Survey", nativeQuery = true)
  Page<Survey> getSurvey();
}
