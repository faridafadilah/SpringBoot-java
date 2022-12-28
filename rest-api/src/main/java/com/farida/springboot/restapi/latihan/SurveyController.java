package com.farida.springboot.restapi.latihan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farida.springboot.restapi.base.ResponAPI;
import com.farida.springboot.restapi.constant.ErrorCode;
import com.farida.springboot.restapi.constant.MessageAPI;

@RestController
public class SurveyController {
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
