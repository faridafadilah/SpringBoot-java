package com.rest.question.survey.restapisurveyquestion.interface_adapter;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.rest.question.survey.restapisurveyquestion.dto.response.SurveyResponse;
import com.rest.question.survey.restapisurveyquestion.usecase.SurveyPresenter;

public class SurveyResponseFormatter implements SurveyPresenter {
  @Override 
  public SurveyResponse prepareSuccessView(SurveyResponse response) {
    return response;
  }

  @Override
  public SurveyResponse prepareFailView(String error) {
    throw new ResponseStatusException(HttpStatus.CONFLICT, error);
  }
}
