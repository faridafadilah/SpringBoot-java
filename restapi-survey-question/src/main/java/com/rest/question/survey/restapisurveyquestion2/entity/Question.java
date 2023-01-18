package com.rest.question.survey.restapisurveyquestion.entity;

public interface Question {
  String getId();
  String getDescription();
  String getCorrectAnswer();
  String getSurveyId();
}
