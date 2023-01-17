package com.rest.question.survey.restapisurveyquestion.interface_adapter;

import com.rest.question.survey.restapisurveyquestion.dto.request.SurveyRequest;
import com.rest.question.survey.restapisurveyquestion.entity.SurveyDataMapper;
import com.rest.question.survey.restapisurveyquestion.repository.JpaSurveyRepository;
import com.rest.question.survey.restapisurveyquestion.usecase.SurveyCreateDsGateway;

public class JpaSurvey implements SurveyCreateDsGateway{
  final JpaSurveyRepository surveyRepository;

  JpaSurvey(JpaSurveyRepository surveyRepository) {
    this.surveyRepository = surveyRepository;
  }

  @Override
  public void save(SurveyRequest request) {
    SurveyDataMapper accounDataMapper = new SurveyDataMapper(request.getId(), request.getTitle(), request.getDescription());
    surveyRepository.save(accounDataMapper);
  }
}
