package com.rest.question.survey.restapisurveyquestion2.usecase;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.ValidationException;

import org.springframework.data.domain.Page;

import com.rest.question.survey.restapisurveyquestion2.base.ResponAPI;
import com.rest.question.survey.restapisurveyquestion2.constant.ErrorCode;
import com.rest.question.survey.restapisurveyquestion2.constant.MessageAPI;
import com.rest.question.survey.restapisurveyquestion2.dto.request.QuestionRequest;
import com.rest.question.survey.restapisurveyquestion2.dto.response.QuestionResponse;
import com.rest.question.survey.restapisurveyquestion2.entity.Question;
import com.rest.question.survey.restapisurveyquestion2.entity.QuestionFactory;
import com.rest.question.survey.restapisurveyquestion2.entity.SurveyDataMapper;

public class QuestionInteractor implements QuestionInputBoundary {
  final QuestionDsGateway questionDsGateway;
  final QuestionFactory questionFactory;

  public QuestionInteractor(QuestionDsGateway questionDsGateway, QuestionFactory questionFactory) {
    this.questionDsGateway = questionDsGateway;
    this.questionFactory = questionFactory;
  }

  @Override
  public boolean createQuestion(QuestionRequest requestModel, ResponAPI<QuestionResponse> responAPI) {
    try {
      Question question = questionFactory.createQuestion(requestModel.getId(), requestModel.getDescription(), requestModel.getCorrectAnswer(), requestModel.getSurveyId());
      QuestionRequest questionRequest = new QuestionRequest(question.getId(), question.getDescription(), question.getCorrectAnswer(), question.getSurveyId());
      questionDsGateway.save(questionRequest);

      QuestionResponse questionResponse = new QuestionResponse(question.getId(), question.getDescription(), question.getCorrectAnswer(), question.getSurveyId());
      responAPI.setData(questionResponse);
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
  public boolean updateQuestion(String id, QuestionRequest requestModel, ResponAPI<QuestionResponse> responAPI) {
    try {
      Question question = questionFactory.updateQuestion(requestModel.getId() ,requestModel.getDescription(), requestModel.getCorrectAnswer(), requestModel.getSurveyId());
      QuestionRequest questionRequest = new QuestionRequest(question.getId() ,question.getDescription(), question.getCorrectAnswer(), question.getSurveyId());
      questionDsGateway.update(questionRequest, id);

      QuestionResponse questionResponse = new QuestionResponse(question.getId() ,question.getDescription(), question.getCorrectAnswer(), question.getSurveyId());
      responAPI.setData(questionResponse);
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
  public boolean deleteQuestion(String id, ResponAPI<QuestionResponse> responAPI) {
    try {
      questionDsGateway.delete(id);
      responAPI.setData(null);
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
  public boolean getByIdSurvey(String id, ResponAPI<List<QuestionResponse>> responAPI) {
    try {
      List<QuestionResponse> response = questionDsGateway.findBySurvey(id);
      responAPI.setData(response);
    } catch (Exception e) {
      responAPI.setErrorMessage(e.getMessage());
    }
    return true;
  }

  @Override
  public Page<QuestionResponse> getAllQuestion(String search, Integer page, Integer limit, List<String> sortBy, Boolean desc) {
    sortBy = (sortBy != null) ? sortBy : Arrays.asList("id");
    desc = (desc != null) ? desc : true;
    Page<QuestionResponse> response = questionDsGateway.getAllQuestion(search, page, limit, sortBy, desc);
    return response;
  }
}
