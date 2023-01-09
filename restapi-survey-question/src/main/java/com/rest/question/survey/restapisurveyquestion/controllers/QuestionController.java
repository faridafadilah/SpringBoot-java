package com.rest.question.survey.restapisurveyquestion.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.question.survey.restapisurveyquestion.base.ResponAPI;
import com.rest.question.survey.restapisurveyquestion.constant.ErrorCode;
import com.rest.question.survey.restapisurveyquestion.constant.MessageAPI;
import com.rest.question.survey.restapisurveyquestion.dto.QuestionDto;
import com.rest.question.survey.restapisurveyquestion.dto.request.QuestionRequest;
import com.rest.question.survey.restapisurveyquestion.dto.response.QuestionResponse;
import com.rest.question.survey.restapisurveyquestion.dto.response.QuestionResponse;
import com.rest.question.survey.restapisurveyquestion.repository.QuestionRepository;
import com.rest.question.survey.restapisurveyquestion.services.QuestionService;

@RestController
@RequestMapping("/api")
public class QuestionController {
  @Autowired
  private QuestionRepository qRepository;

  @Autowired
  private QuestionService questionService;

  // Get All Question
  @GetMapping("/questions")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<ResponAPI<Page<QuestionResponse>>> getAllQuestion(
      @RequestParam(value = "search", required = false) String search,
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "limit", required = false) Integer limit,
      @RequestParam(value = "sortBy", required = false) List<String> sortBy,
      @RequestParam(value = "descending", required = false) Boolean desc) {  
    Page<QuestionResponse> responsePage = questionService.getAllQUestion(search, page, limit, sortBy, desc);
    ResponAPI<Page<QuestionResponse>> responAPI = new ResponAPI<>();
    responAPI.setErrorMessage(MessageAPI.SUCCESS);
    responAPI.setErrorCode(ErrorCode.SUCCESS); 
    responAPI.setData(responsePage);
    return ResponseEntity.status(HttpStatus.OK).body(responAPI);
  }

  //Get All Data join Question and Survey
  @GetMapping("/questions/survey/question")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<ResponAPI<List<QuestionDto>>> getAllQuestionSpecific() {
    ResponAPI<List<QuestionDto>> responseAPI = new ResponAPI<>();
    List<QuestionDto> question = qRepository.getAllQuestion();
    responseAPI.setErrorMessage(MessageAPI.SUCCESS);
    responseAPI.setErrorCode(ErrorCode.SUCCESS);
    responseAPI.setData(question);
    return ResponseEntity.ok(responseAPI);
  }

  //Get All Data Question By Survey Id
  @GetMapping("/questions/{id}")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<ResponAPI<List<QuestionResponse>>> getQuestionBySurveyId(@PathVariable("id") String id) {
    ResponAPI<List<QuestionResponse>> responAPI = new ResponAPI<>();

    if(!questionService.getQuestionBySurveyId(responAPI, id)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responAPI);
    }
    responAPI.setErrorCode(ErrorCode.SUCCESS);
    responAPI.setErrorMessage(MessageAPI.SUCCESS);
    return ResponseEntity.status(HttpStatus.OK).body(responAPI);
  }

  //Add Question
  @PostMapping("/add-question")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<ResponAPI<QuestionResponse>> createQuestion(@RequestBody QuestionRequest body) {
    ResponAPI<QuestionResponse> responAPI = new ResponAPI<>();
    if(!questionService.createQuestion(body,responAPI)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responAPI);
    }
    return ResponseEntity.ok(responAPI);
  } 

  //Edit Question
  @PostMapping("/edit-question/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<ResponAPI<QuestionResponse>> updateQuestion(@PathVariable String id, @RequestBody QuestionRequest body) {
    ResponAPI<QuestionResponse> responAPI = new ResponAPI<>();
    if(!questionService.updateQuestion(body, id, responAPI)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responAPI);
    }
    return ResponseEntity.ok(responAPI);
  }

  //Delete Question
  @DeleteMapping("/delete-question/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<ResponAPI<QuestionResponse>> deleteQuestion(@PathVariable String id) {
    ResponAPI<QuestionResponse> responAPI = new ResponAPI<>();
    if(!questionService.deleteQuestion(id, responAPI)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responAPI);
    }
    return ResponseEntity.ok(responAPI);
  }
}

