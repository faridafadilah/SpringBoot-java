package com.rest.question.survey.restapisurveyquestion.usecase;

import java.util.List;

import org.springframework.data.domain.Page;

import com.rest.question.survey.restapisurveyquestion.dto.request.SurveyRequest;
import com.rest.question.survey.restapisurveyquestion.dto.response.DtoResListSurvey;
import com.rest.question.survey.restapisurveyquestion.dto.response.SurveyResponse;

public interface SurveyCreateDsGateway {
  void save(SurveyRequest requestModel);
  void update(SurveyRequest request, String id);
  SurveyResponse delete(String id);
  DtoResListSurvey getByIdSurvey(String id);
  Page<DtoResListSurvey> getAllSurvey(String search, Integer page, Integer limit, List<String> sortBy, Boolean desc);
}
