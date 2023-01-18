package com.rest.question.survey.restapisurveyquestion2.entity;

public interface QuestionFactory {
  Question createQuestion(String id, String description, String correctAnswer, String surveyId);
  Question updateQuestion(String id, String description, String correctAnswer, String surveyId);
}
