package com.rest.question.survey.restapisurveyquestion.entity;

public interface SurveyFactory {
  Survey createSurvey(String id, String title, String description);
  Survey updateSurvey(String id, String title, String description);
}
