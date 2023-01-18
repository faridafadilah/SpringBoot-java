package com.rest.question.survey.restapisurveyquestion.usecase;

import java.util.List;

import org.springframework.data.domain.Page;

import com.rest.question.survey.restapisurveyquestion.base.ResponAPI;
import com.rest.question.survey.restapisurveyquestion.dto.request.SurveyRequest;
import com.rest.question.survey.restapisurveyquestion.dto.response.DtoResListSurvey;
import com.rest.question.survey.restapisurveyquestion.dto.response.SurveyResponse;

public interface SurveyInputBoundary {
  boolean createSurvey(SurveyRequest requestModel, ResponAPI<SurveyResponse> responAPI);

  boolean updateSurvey(String id, SurveyRequest requestModel, ResponAPI<SurveyResponse> responAPI);

  boolean deleteSurvey(String id, ResponAPI<SurveyResponse> responAPI);

  boolean getByIdSurvey(String id, ResponAPI<DtoResListSurvey> responAPI);

  Page<DtoResListSurvey> getAllSurvey(String search, Integer page, Integer limit, List<String> sortBy, Boolean desc);
}
