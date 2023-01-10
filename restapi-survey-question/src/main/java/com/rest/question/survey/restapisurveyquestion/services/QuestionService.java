package com.rest.question.survey.restapisurveyquestion.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.ValidationException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.rest.question.survey.restapisurveyquestion.base.BasePageInterface;
import com.rest.question.survey.restapisurveyquestion.base.ResponAPI;
import com.rest.question.survey.restapisurveyquestion.constant.ErrorCode;
import com.rest.question.survey.restapisurveyquestion.constant.MessageAPI;
import com.rest.question.survey.restapisurveyquestion.dto.request.QuestionRequest;
import com.rest.question.survey.restapisurveyquestion.dto.response.QuestionResponse;
import com.rest.question.survey.restapisurveyquestion.models.Question;
import com.rest.question.survey.restapisurveyquestion.models.Survey;
import com.rest.question.survey.restapisurveyquestion.repository.QuestionRepository;
import com.rest.question.survey.restapisurveyquestion.repository.SurveyRepository;
import com.rest.question.survey.restapisurveyquestion.specification.QuestionSpecification;

@Service
public class QuestionService implements BasePageInterface<Question, QuestionSpecification, QuestionResponse, String>{
  @Autowired
  private QuestionRepository questionRepository;
  private ModelMapper objectMapper = new ModelMapper();

  @Autowired
  private SurveyRepository surveyRepository;

  @Autowired
  private QuestionSpecification specification;

  // Add Question
  public boolean createQuestion(QuestionRequest body, ResponAPI<QuestionResponse> responAPI) {
    try {
      Question question = objectMapper.map(body, Question.class);
      questionRepository.save(question);

      responAPI.setData(mapToQuestionResponse(question));
      responAPI.setErrorCode(ErrorCode.SUCCESS);
      responAPI.setErrorMessage(MessageAPI.SUCCESS);
    } catch (ValidationException e) {
      responAPI.setErrorMessage(e.getMessage());
    } catch (Exception e) {
      responAPI.setErrorCode(ErrorCode.BODY_NOT_VALID);
      responAPI.setErrorMessage(e.getMessage());
      return false;
    }
    return true;
  }

  // Update Question
  public boolean updateQuestion(QuestionRequest body, String id, ResponAPI<QuestionResponse> responAPI) {
    Optional<Question> qOptional = questionRepository.findById(id);
    if(!qOptional.isPresent()) {
      responAPI.setErrorCode(ErrorCode.BODY_NOT_VALID);
      responAPI.setErrorMessage(MessageAPI.BODY_NOT_VALID);
      return false;
    }

    try {
      Question question = qOptional.get();
      question.setId(id);
      question.setCorrectAnswer(body.getCorrectAnswer());
      question.setDescription(body.getDescription());
      questionRepository.save(question);

      responAPI.setData(mapToQuestionResponse(question));
      responAPI.setErrorCode(ErrorCode.SUCCESS);
      responAPI.setErrorMessage(MessageAPI.SUCCESS);
    } catch (ValidationException e) {
      responAPI.setErrorCode(ErrorCode.BODY_NOT_VALID);
      responAPI.setErrorMessage(MessageAPI.BODY_NOT_VALID);
    } catch (Exception e) {
      responAPI.setErrorCode(ErrorCode.BODY_NOT_VALID);
      responAPI.setErrorMessage(e.getMessage());
      return false;
    }
    return true;
  }

  // Delete Question
  public boolean deleteQuestion(String id, ResponAPI<QuestionResponse> responAPI) {
    Optional<Question> qOptional = questionRepository.findById(id);
    if(!qOptional.isPresent()) {
      responAPI.setErrorCode(ErrorCode.BODY_NOT_VALID);
      responAPI.setErrorMessage(MessageAPI.BODY_NOT_VALID);
    }

    try {
      Question question = qOptional.get();
      questionRepository.deleteById(id);
      responAPI.setData(mapToQuestionResponse(question));
      responAPI.setErrorCode(ErrorCode.SUCCESS);
      responAPI.setErrorMessage(MessageAPI.SUCCESS);
    } catch (Exception e) {
      responAPI.setErrorCode(ErrorCode.BODY_NOT_VALID);
      responAPI.setErrorMessage(e.getMessage());
      return false;
    }
    return true;
  }

  //Get All + Pagination
  public Page<QuestionResponse> getAllQUestion(String search, Integer page, Integer limit, List<String> sortBy,
      Boolean desc) {
    sortBy = (sortBy != null) ? sortBy : Arrays.asList("id");
    desc = (desc != null) ? desc : true;
    Pageable pageableRequest = this.defaultPage(search, page, limit, sortBy, desc);

    Page<Question> settingPage = questionRepository.findAll(this.defaultSpec(search, specification), pageableRequest);

    //convert to list dto
    List<Question> list = settingPage.getContent();
    List<QuestionResponse> responseList = list.stream().map(a -> objectMapper.map(a, QuestionResponse.class)).collect(Collectors.toList());
    Page<QuestionResponse> responsePage = new PageImpl<>(responseList, pageableRequest, settingPage.getTotalElements());
    return responsePage;
  }

  //Get Question By Survey Id
  public boolean getQuestionBySurveyId(ResponAPI<List<QuestionResponse>> responAPI, String id) {
    Optional<Survey> optionalSurvey = surveyRepository.findById(id);
    if(!optionalSurvey.isPresent()) {
      responAPI.setErrorMessage("Data tidak ditemukan!");
      return false;
    }
    try {
      List<QuestionResponse> response =QuestionResponse.getInstance(optionalSurvey.get());
      responAPI.setData(response);
    } catch (Exception e) {
      responAPI.setErrorMessage(e.getMessage());
    }
    return true;
  }

  private QuestionResponse mapToQuestionResponse(Question question) {
    return objectMapper.map(question, QuestionResponse.class);
  }
}