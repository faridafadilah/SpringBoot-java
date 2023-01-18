package com.rest.question.survey.restapisurveyquestion2.usecase;

import java.util.List;

import org.springframework.data.domain.Page;

import com.rest.question.survey.restapisurveyquestion2.base.ResponAPI;
import com.rest.question.survey.restapisurveyquestion2.dto.request.SurveyRequest;
import com.rest.question.survey.restapisurveyquestion2.dto.response.DtoResListSurvey;
import com.rest.question.survey.restapisurveyquestion2.dto.response.SurveyResponse;

public interface SurveyInputBoundary {
  boolean createSurvey(SurveyRequest requestModel, ResponAPI<SurveyResponse> responAPI);

  boolean updateSurvey(String id, SurveyRequest requestModel, ResponAPI<SurveyResponse> responAPI);

  boolean deleteSurvey(String id, ResponAPI<SurveyResponse> responAPI);

  boolean getByIdSurvey(String id, ResponAPI<DtoResListSurvey> responAPI);

  Page<DtoResListSurvey> getAllSurvey(String search, Integer page, Integer limit, List<String> sortBy, Boolean desc);
}
