package com.rest.question.survey.restapisurveyquestion.entity;

public interface QuestionFactory {
  Question createQuestion(String id, String description, String correctAnswer, String surveyId);
  Question updateQuestion(String id, String description, String correctAnswer, String surveyId);
}
