package com.rest.question.survey.restapisurveyquestion.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.rest.question.survey.restapisurveyquestion.models.Survey;

@Repository
public interface SurveyRepository<T> extends JpaRepository<Survey, String>, JpaSpecificationExecutor<T> {
  // Page<Survey> findByTitleContaining(String title, Specification<Survey> specification, Pageable pageable);
}
