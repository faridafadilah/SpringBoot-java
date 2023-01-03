package com.rest.question.survey.restapisurveyquestion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.question.survey.restapisurveyquestion.base.ResponAPI;
import com.rest.question.survey.restapisurveyquestion.constant.ErrorCode;
import com.rest.question.survey.restapisurveyquestion.constant.MessageAPI;
import com.rest.question.survey.restapisurveyquestion.dto.QuestionDto;
import com.rest.question.survey.restapisurveyquestion.repository.QuestionRepository;

@RestController
public class QuestionController {
  @Autowired
  private QuestionRepository qRepository;

  @GetMapping("questions")
  public ResponseEntity<ResponAPI<List<QuestionDto>>> getAllQuestionSpecific() {
    ResponAPI<List<QuestionDto>> responseAPI = new ResponAPI<>();
    List<QuestionDto> question = qRepository.getAllQuestion();
    responseAPI.setErrorMessage(MessageAPI.SUCCESS);
    responseAPI.setErrorCode(ErrorCode.SUCCESS);
    responseAPI.setData(question);
    return ResponseEntity.ok(responseAPI);
  }
}

