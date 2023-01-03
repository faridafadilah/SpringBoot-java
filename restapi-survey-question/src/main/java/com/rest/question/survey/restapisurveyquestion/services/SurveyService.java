package com.rest.question.survey.restapisurveyquestion.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import com.rest.question.survey.restapisurveyquestion.base.ResponAPI;
import com.rest.question.survey.restapisurveyquestion.constant.ErrorCode;
import com.rest.question.survey.restapisurveyquestion.constant.MessageAPI;
import com.rest.question.survey.restapisurveyquestion.dto.request.SurveyRequest;
import com.rest.question.survey.restapisurveyquestion.dto.response.SurveyResponse;
import com.rest.question.survey.restapisurveyquestion.models.Survey;
import com.rest.question.survey.restapisurveyquestion.repository.SurveyRepository;

@Service
public class SurveyService {

  @Autowired
  SurveyRepository surveyRepository;

  public boolean createSurvey(SurveyRequest body, ResponAPI<SurveyResponse> responAPI) {
    try {
      ModelMapper modelMapper = new ModelMapper();
      Survey survey =modelMapper.map(body, Survey.class);
      surveyRepository.save(survey);
      responAPI.setErrorCode(ErrorCode.SUCCESS);
      responAPI.setErrorMessage(MessageAPI.SUCCESS);
    } catch (ValidationException e) {
      responAPI.setErrorMessage(e.getMessage());
    } catch (Exception e) {
      responAPI.setErrorMessage(e.getMessage());
      responAPI.setErrorCode(ErrorCode.FAILED);
      return false;
    }
    return true;
  }

  public boolean updateSurvey(SurveyRequest body, String id, ResponAPI<SurveyResponse> responAPI) {
    //find by Id
    Optional<Survey> sOptional = surveyRepository.findById(id);
    if(!sOptional.isPresent()) {
      responAPI.setErrorCode(ErrorCode.FAILED);
      responAPI.setErrorMessage(MessageAPI.BODY_NOT_VALID);
      return false;
    }

    try {
      //get data berdasar id
      Survey survey = sOptional.get();
      System.out.println(sOptional.get());
      survey.setId(id);
      survey.setTitle(body.getTitle());
      survey.setDescription(body.getDescription());
      //save database
      surveyRepository.save(survey);

      // Response
      responAPI.setErrorCode(ErrorCode.SUCCESS);
      responAPI.setErrorMessage(MessageAPI.SUCCESS);
    } catch (ValidationException e) {
      responAPI.setErrorMessage(MessageAPI.BODY_NOT_VALID);
      responAPI.setErrorCode(ErrorCode.FAILED);
    } catch (Exception e) {
      responAPI.setErrorCode(ErrorCode.FAILED);
      responAPI.setErrorMessage(e.getMessage());
      return false;
    }
    return true;
  }

  public boolean deleteSurvey(String id, ResponAPI<SurveyResponse> responAPI) {
    Optional<Survey> optionalS = surveyRepository.findById(id);
    if(!optionalS.isPresent()) {
      responAPI.setErrorCode(ErrorCode.FAILED);
      responAPI.setErrorMessage(MessageAPI.BODY_NOT_VALID);
      return false;
    }

    try {
      surveyRepository.deleteById(id);
      responAPI.setErrorCode(ErrorCode.SUCCESS);
      responAPI.setErrorMessage(MessageAPI.SUCCESS);
    } catch (Exception e) {
      responAPI.setErrorCode(ErrorCode.FAILED);
      responAPI.setErrorMessage(e.getMessage());
      return false;
    }
    return true;
  }

  public Page<SurveyResponse> getAllSurvey() {
    Page<Survey> surveyResponse = surveyRepository.getSurvey();
    List<SurveyResponse> body = surveyResponse.getContent().stream().map(a -> mapToSurveyResponse(a)).collect(Collectors.toList());
    return new PageImpl<>(body);
  }

  private SurveyResponse mapToSurveyResponse(Survey survey) {
    ModelMapper objectMapper = new ModelMapper();
    return objectMapper.map(survey, SurveyResponse.class);
  }
}
