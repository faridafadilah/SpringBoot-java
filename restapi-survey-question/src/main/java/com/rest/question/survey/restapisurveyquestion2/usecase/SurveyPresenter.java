package com.rest.question.survey.restapisurveyquestion.usecase;

import com.rest.question.survey.restapisurveyquestion.dto.response.SurveyResponse;

public interface SurveyPresenter {
  SurveyResponse prepareSuccessView(SurveyResponse survey);
  SurveyResponse prepareFailView(String error);
}
