package com.rest.question.survey.restapisurveyquestion.services;

import java.util.Optional;

import javax.validation.ValidationException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.question.survey.restapisurveyquestion.base.ResponAPI;
import com.rest.question.survey.restapisurveyquestion.constant.ErrorCode;
import com.rest.question.survey.restapisurveyquestion.constant.MessageAPI;
import com.rest.question.survey.restapisurveyquestion.dto.request.QuestionRequest;
import com.rest.question.survey.restapisurveyquestion.dto.response.QuestionResponse;
import com.rest.question.survey.restapisurveyquestion.models.Question;
import com.rest.question.survey.restapisurveyquestion.repository.QuestionRepository;

@Service
public class QuestionService {
  @Autowired
  private QuestionRepository questionRepository;
  private ModelMapper objectMapper = new ModelMapper();

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
      responAPI.setErrorCode(ErrorCode.FAILED);
      responAPI.setErrorMessage(e.getMessage());
      return false;
    }
    return true;
  }

  // Update Question
  public boolean updateQuestion(QuestionRequest body, String id, ResponAPI<QuestionResponse> responAPI) {
    Optional<Question> qOptional = questionRepository.findById(id);
    if(!qOptional.isPresent()) {
      responAPI.setErrorCode(ErrorCode.FAILED);
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
      responAPI.setErrorCode(ErrorCode.FAILED);
      responAPI.setErrorMessage(MessageAPI.BODY_NOT_VALID);
    } catch (Exception e) {
      responAPI.setErrorCode(ErrorCode.FAILED);
      responAPI.setErrorMessage(e.getMessage());
      return false;
    }
    return true;
  }

  // Delete Question
  public boolean deleteQuestion(String id, ResponAPI<QuestionResponse> responAPI) {
    Optional<Question> qOptional = questionRepository.findById(id);
    if(!qOptional.isPresent()) {
      responAPI.setErrorCode(ErrorCode.FAILED);
      responAPI.setErrorMessage(MessageAPI.BODY_NOT_VALID);
    }

    try {
      Question question = qOptional.get();
      questionRepository.deleteById(id);
      responAPI.setData(mapToQuestionResponse(question));
      responAPI.setErrorCode(ErrorCode.SUCCESS);
      responAPI.setErrorMessage(MessageAPI.SUCCESS);
    } catch (Exception e) {
      responAPI.setErrorCode(ErrorCode.FAILED);
      responAPI.setErrorMessage(e.getMessage());
      return false;
    }
    return true;
  }

  private QuestionResponse mapToQuestionResponse(Question question) {
    return objectMapper.map(question, QuestionResponse.class);
  }
}
