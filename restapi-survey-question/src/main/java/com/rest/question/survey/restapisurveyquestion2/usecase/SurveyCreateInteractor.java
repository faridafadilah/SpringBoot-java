package com.rest.question.survey.restapisurveyquestion.usecase;


import com.rest.question.survey.restapisurveyquestion.dto.request.SurveyRequest;
import com.rest.question.survey.restapisurveyquestion.dto.response.SurveyResponse;
import com.rest.question.survey.restapisurveyquestion.entity.Survey;
import com.rest.question.survey.restapisurveyquestion.entity.SurveyFactory;

public class SurveyCreateInteractor implements SurveyInputBoundary {
  final SurveyCreateDsGateway surveyCreateDsGateway;
  final SurveyPresenter surveyPresenter;
  final SurveyFactory surveyFactory;
  

  public SurveyCreateInteractor(SurveyCreateDsGateway surveyCreateDsGateway, SurveyPresenter surveyPresenter,
      SurveyFactory surveyFactory) {
    this.surveyCreateDsGateway = surveyCreateDsGateway;
    this.surveyPresenter = surveyPresenter;
    this.surveyFactory = surveyFactory;
  }


  @Override
  public SurveyResponse createSurvey(SurveyRequest request) {
    Survey survey = surveyFactory.createSurvey(request.getId(), request.getTitle(), request.getDescription());
    SurveyRequest surveyRequest = new SurveyRequest(survey.getId(), survey.getTitle(), survey.getDescription());
    surveyCreateDsGateway.save(surveyRequest);

    SurveyResponse accountResponse = new SurveyResponse(survey.getId(), survey.getTitle(), survey.getDescription());
    return surveyPresenter.prepareSuccessView(accountResponse);
  }
}
