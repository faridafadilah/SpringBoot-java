package com.rest.question.survey.restapisurveyquestion.usecase;

import com.rest.question.survey.restapisurveyquestion.dto.request.SurveyRequest;
import com.rest.question.survey.restapisurveyquestion.dto.response.SurveyResponse;

public interface SurveyInputBoundary {
  SurveyResponse createSurvey(SurveyRequest requestModel);
}
