package com.rest.question.survey.restapisurveyquestion.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rest.question.survey.restapisurveyquestion.constant.ErrorCode;
import com.rest.question.survey.restapisurveyquestion.constant.ErrorCodeApi;
import com.rest.question.survey.restapisurveyquestion.constant.MessageAPI;
import com.rest.question.survey.restapisurveyquestion.exception.UnauthorizedException;

public class BaseController {
  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<ResponAPI<String>> handleAPINotAuthorized(UnauthorizedException ex, HttpServletRequest request) {
    ResponAPI<String> response = new ResponAPI<>(ErrorCode.UNAUTHORIZED, MessageAPI.UNAUTHORIZED, null);
    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
  }
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResponAPI<String>> handleException(Exception ex, HttpServletRequest request) {
    ResponAPI<String> response = new ResponAPI<>(ErrorCodeApi.FAILED, ex.getMessage(), null);
    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
  }
}

