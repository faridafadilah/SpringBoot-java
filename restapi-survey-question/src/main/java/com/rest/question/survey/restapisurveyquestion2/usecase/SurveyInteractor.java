package com.rest.question.survey.restapisurveyquestion.usecase;

import java.util.Arrays;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.data.domain.Page;

import com.rest.question.survey.restapisurveyquestion.base.ResponAPI;
import com.rest.question.survey.restapisurveyquestion.constant.ErrorCode;
import com.rest.question.survey.restapisurveyquestion.constant.MessageAPI;
import com.rest.question.survey.restapisurveyquestion.dto.request.SurveyRequest;
import com.rest.question.survey.restapisurveyquestion.dto.response.DtoResListSurvey;
import com.rest.question.survey.restapisurveyquestion.dto.response.SurveyResponse;
import com.rest.question.survey.restapisurveyquestion.entity.Survey;
import com.rest.question.survey.restapisurveyquestion.entity.SurveyFactory;

public class SurveyInteractor implements SurveyInputBoundary {
  final SurveyCreateDsGateway surveyCreateDsGateway;
  final SurveyFactory surveyFactory;
  

  public SurveyInteractor(SurveyCreateDsGateway surveyCreateDsGateway, SurveyFactory surveyFactory) {
    this.surveyCreateDsGateway = surveyCreateDsGateway;
    this.surveyFactory = surveyFactory;
  }


  @Override
 public boolean createSurvey(SurveyRequest request, ResponAPI<SurveyResponse> responAPI) {
    try {
      Survey survey = surveyFactory.createSurvey(request.getId(), request.getTitle(), request.getDescription());
      SurveyRequest surveyRequest = new SurveyRequest(survey.getId(), survey.getTitle(), survey.getDescription());
      surveyCreateDsGateway.save(surveyRequest);

      SurveyResponse accountResponse = new SurveyResponse(survey.getId(), survey.getTitle(), survey.getDescription());
      responAPI.setData(accountResponse);
      responAPI.setErrorCode(ErrorCode.SUCCESS);
      responAPI.setErrorMessage(MessageAPI.SUCCESS);
    } catch (ValidationException e) {
      responAPI.setErrorMessage(e.getMessage());
    } catch (Exception e) {
      responAPI.setErrorMessage(e.getMessage());
      responAPI.setErrorCode(ErrorCode.BODY_NOT_VALID);
      return false;
    }
    
    return true;
  }

  @Override
  public boolean updateSurvey(String id, SurveyRequest request, ResponAPI<SurveyResponse> responAPI) {
    try {
      Survey survey = surveyFactory.updateSurvey(request.getId(), request.getTitle(), request.getDescription());
      SurveyRequest surveyRequest = new SurveyRequest(survey.getId(), survey.getTitle(), survey.getDescription());
      surveyCreateDsGateway.update(surveyRequest, id);
  
      SurveyResponse accountResponse = new SurveyResponse(survey.getId(), survey.getTitle(), survey.getDescription());
      responAPI.setData(accountResponse);
      responAPI.setErrorCode(ErrorCode.SUCCESS);
      responAPI.setErrorMessage(MessageAPI.SUCCESS);
    } catch (ValidationException e) {
      responAPI.setErrorMessage(e.getMessage());
    } catch (Exception e) {
      responAPI.setErrorMessage(e.getMessage());
      responAPI.setErrorCode(ErrorCode.BODY_NOT_VALID);
      return false;
    }
    return true;
  }

  @Override
  public boolean deleteSurvey(String id, ResponAPI<SurveyResponse> responAPI) {
    try {
      SurveyResponse survey = surveyCreateDsGateway.delete(id);
      responAPI.setData(survey);
      responAPI.setErrorCode(ErrorCode.SUCCESS);
      responAPI.setErrorMessage(MessageAPI.SUCCESS);
    } catch (ValidationException e) {
      responAPI.setErrorMessage(e.getMessage());
    } catch (Exception e) {
      responAPI.setErrorMessage(e.getMessage());
      responAPI.setErrorCode(ErrorCode.BODY_NOT_VALID);
      return false;
    }
    return true;
  }

  @Override
  public boolean getByIdSurvey(String id, ResponAPI<DtoResListSurvey> responAPI) {
    try {
      DtoResListSurvey survey = surveyCreateDsGateway.getByIdSurvey(id);
      responAPI.setData(survey);
    } catch (Exception e) {
      responAPI.setErrorMessage(e.getMessage());
      responAPI.setErrorCode(ErrorCode.BODY_NOT_VALID);
      return false;
    }
    return true;
  }

  @Override
  public Page<DtoResListSurvey> getAllSurvey(String search, Integer page, Integer limit, List<String> sortBy, Boolean desc) {
    sortBy = (sortBy != null) ? sortBy : Arrays.asList("id");
    desc = (desc != null) ? desc : true;
    Page<DtoResListSurvey> response = surveyCreateDsGateway.getAllSurvey(search, page, limit, sortBy, desc);
    return response;
  }
}
