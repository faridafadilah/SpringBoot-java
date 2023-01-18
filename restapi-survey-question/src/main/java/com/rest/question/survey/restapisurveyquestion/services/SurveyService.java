package com.rest.question.survey.restapisurveyquestion.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import com.rest.question.survey.restapisurveyquestion.base.BasePageInterface;
import com.rest.question.survey.restapisurveyquestion.base.ResponAPI;
import com.rest.question.survey.restapisurveyquestion.constant.ErrorCode;
import com.rest.question.survey.restapisurveyquestion.constant.MessageAPI;
import com.rest.question.survey.restapisurveyquestion.dto.request.SurveyRequest;
import com.rest.question.survey.restapisurveyquestion.dto.response.DtoResListSurvey;
import com.rest.question.survey.restapisurveyquestion.dto.response.SurveyResponse;
import com.rest.question.survey.restapisurveyquestion.models.Survey;
import com.rest.question.survey.restapisurveyquestion.repository.SurveyRepository;
import com.rest.question.survey.restapisurveyquestion.specification.SurveySpecification;

@Service
public class SurveyService implements BasePageInterface<Survey, SurveySpecification, SurveyResponse, String> {

  @Autowired
  private SurveyRepository surveyRepository;

  @Autowired
  private SurveySpecification specification;
  private ModelMapper objectMapper = new ModelMapper();

  // Add Survey
  public boolean createSurvey(SurveyRequest body, ResponAPI<SurveyResponse> responAPI) {
    try {
      Survey survey =objectMapper.map(body, Survey.class);
      surveyRepository.save(survey);

      responAPI.setData(mapToSurveyResponse(survey));
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

  //Update Survey
  public boolean updateSurvey(SurveyRequest body, String id, ResponAPI<SurveyResponse> responAPI) {
    //find by Id
    Optional<Survey> sOptional = surveyRepository.findById(id);
    if(!sOptional.isPresent()) {
      responAPI.setErrorCode(ErrorCode.BODY_NOT_VALID);
      responAPI.setErrorMessage(MessageAPI.BODY_NOT_VALID);
      return false;
    }

    try {
      //get data berdasar id
      Survey survey = sOptional.get();
      survey.setId(id);
      survey.setTitle(body.getTitle());
      survey.setDescription(body.getDescription());
      //save database
      surveyRepository.save(survey);

      // Response
      responAPI.setData(mapToSurveyResponse(survey));
      responAPI.setErrorCode(ErrorCode.SUCCESS);
      responAPI.setErrorMessage(MessageAPI.SUCCESS);
    } catch (ValidationException e) {
      responAPI.setErrorMessage(MessageAPI.BODY_NOT_VALID);
      responAPI.setErrorCode(ErrorCode.BODY_NOT_VALID);
    } catch (Exception e) {
      responAPI.setErrorCode(ErrorCode.BODY_NOT_VALID);
      responAPI.setErrorMessage(e.getMessage());
      return false;
    }
    return true;
  }

  //Delete Survey
  public boolean deleteSurvey(String id, ResponAPI<SurveyResponse> responAPI) {
    Optional<Survey> optionalS = surveyRepository.findById(id);
    if(!optionalS.isPresent()) {
      responAPI.setErrorCode(ErrorCode.BODY_NOT_VALID);
      responAPI.setErrorMessage(MessageAPI.BODY_NOT_VALID);
      return false;
    }

    try {
      Survey survey = optionalS.get();
      if(!survey.getQuestions().isEmpty()) {
        responAPI.setData(null);
        responAPI.setErrorCode(ErrorCode.BODY_NOT_VALID);
        responAPI.setErrorMessage(MessageAPI.ERROR_RELATION + "Question");
      } else {
        surveyRepository.deleteById(id);
        responAPI.setData(mapToSurveyResponse(survey));
        responAPI.setErrorCode(ErrorCode.SUCCESS);
        responAPI.setErrorMessage(MessageAPI.SUCCESS);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return true;
  }
	
  // Get All + Pagination
	public Page<DtoResListSurvey> getAllSurvey(String search, Integer page, Integer limit, List<String> sortBy,
    Boolean desc) {
    sortBy = (sortBy != null) ? sortBy : Arrays.asList("id");
    desc = (desc != null) ? desc : true;
    Pageable pageableRequest = this.defaultPage(search, page, limit, sortBy, desc);
    Page<Survey> settingPage = surveyRepository.findAll(this.defaultSpec(search, specification), pageableRequest);
    List<Survey> surveys = settingPage.getContent();
    List<DtoResListSurvey> responseList = new ArrayList<>();
    surveys.stream().forEach(a -> {
      responseList.add(DtoResListSurvey.getInstance(a));
    });
    Page<DtoResListSurvey> response = new PageImpl<>(responseList, pageableRequest, settingPage.getTotalElements());
    return response;
  }

  // Get By Id
  public boolean getSurveyById(ResponAPI<DtoResListSurvey> responAPI, String id) {
    Optional<Survey> optionalSurvey = surveyRepository.findById(id);
    if(!optionalSurvey.isPresent()) {
      responAPI.setErrorMessage("Data tidak ditemukan!");
      return false;
    }
    try {
      DtoResListSurvey response = DtoResListSurvey.getInstance(optionalSurvey.get());
      responAPI.setData(response);
    } catch (Exception e) {
      responAPI.setData(DtoResListSurvey.getInstance(null));
      responAPI.setErrorMessage(e.getMessage());
    }
    return true;
  }

  private SurveyResponse mapToSurveyResponse(Survey survey) {
    return objectMapper.map(survey, SurveyResponse.class);
  }
}
