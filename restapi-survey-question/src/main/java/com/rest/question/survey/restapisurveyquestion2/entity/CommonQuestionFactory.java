package com.rest.question.survey.restapisurveyquestion.entity;

public class CommonQuestionFactory implements QuestionFactory {
  @Override
  public Question createQuestion(String id, String description, String correctAnswer, String surveyId) {
    return new CommonQuestion(id, description, correctAnswer, surveyId);
  }

  @Override
  public Question updateQuestion(String id, String description, String correctAnswer, String surveyId) {
    return new CommonQuestion(id, description, correctAnswer, surveyId);
  }
}
