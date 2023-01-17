package com.rest.question.survey.restapisurveyquestion.usecase;

import com.rest.question.survey.restapisurveyquestion.dto.request.SurveyRequest;

public interface SurveyCreateDsGateway {
  void save(SurveyRequest requestModel);
}
