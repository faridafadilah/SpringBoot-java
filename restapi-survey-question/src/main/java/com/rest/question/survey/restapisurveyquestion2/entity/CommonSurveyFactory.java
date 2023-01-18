package com.rest.question.survey.restapisurveyquestion.entity;

public class CommonSurveyFactory implements SurveyFactory{
  @Override
  public Survey createSurvey(String id, String title, String description) {
    return new CommonSurvey(id, title, description);
  }

  @Override
  public Survey updateSurvey(String id, String title, String description) {
    return new CommonSurvey(id, title, description);
  }
}
