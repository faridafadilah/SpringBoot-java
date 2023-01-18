package com.rest.question.survey.restapisurveyquestion2.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.question.survey.restapisurveyquestion2.base.ResponAPI;
import com.rest.question.survey.restapisurveyquestion2.constant.ErrorCode;
import com.rest.question.survey.restapisurveyquestion2.constant.MessageAPI;
import com.rest.question.survey.restapisurveyquestion2.dto.request.QuestionRequest;
import com.rest.question.survey.restapisurveyquestion2.dto.response.QuestionResponse;
import com.rest.question.survey.restapisurveyquestion2.usecase.QuestionInputBoundary;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class QuestionController {
  final QuestionInputBoundary questionInput;

  //Get All Question
  @GetMapping("/questions")
  public ResponseEntity<ResponAPI<Page<QuestionResponse>>> getAllQuestion(
      @RequestParam(value = "search", required = false) String search,
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "limit", required = false) Integer limit,
      @RequestParam(value = "sortBy", required = false) List<String> sortBy,
      @RequestParam(value = "descending", required = false) Boolean desc) {  
    Page<QuestionResponse> responsePage = questionInput.getAllQuestion(search, page, limit, sortBy, desc);
    ResponAPI<Page<QuestionResponse>> responAPI = new ResponAPI<>();
    responAPI.setErrorMessage(MessageAPI.SUCCESS);
    responAPI.setErrorCode(ErrorCode.SUCCESS); 
    responAPI.setData(responsePage);
    return ResponseEntity.status(HttpStatus.OK).body(responAPI);
  }

  //Add Question
  @PostMapping("/add-question")
  public ResponseEntity<ResponAPI<QuestionResponse>> createQuestion(@RequestBody QuestionRequest requestModel) {
    ResponAPI<QuestionResponse> responAPI = new ResponAPI<>();
    if(!questionInput.createQuestion(requestModel, responAPI)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responAPI);
    }
    return ResponseEntity.ok(responAPI);
  }

  //Edit Question
 @PostMapping("/edit-question/{id}")
 public ResponseEntity<ResponAPI<QuestionResponse>> updateQuestion(@PathVariable String id, @RequestBody QuestionRequest requestModel) {
  ResponAPI<QuestionResponse> responAPI = new ResponAPI<>();
  if(!questionInput.updateQuestion(id, requestModel, responAPI)) {
   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responAPI);
  }
  return ResponseEntity.ok(responAPI);
 }

 //Delete Question
 @DeleteMapping("/delete-question/{id}")
 public ResponseEntity<ResponAPI<QuestionResponse>> deleteQuestion(@PathVariable String id) {
  ResponAPI<QuestionResponse> responAPI = new ResponAPI<>();
  if(!questionInput.deleteQuestion(id, responAPI)) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responAPI);
  }
  return ResponseEntity.ok(responAPI);
 }

 //Get By Id
 @GetMapping("/questions/{id}")
 public ResponseEntity<ResponAPI<List<QuestionResponse>>> getByIdSurvey(@PathVariable("id") String id) {
   ResponAPI<List<QuestionResponse>> responAPI = new ResponAPI<>();
   if(!questionInput.getByIdSurvey(id, responAPI)) {
     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responAPI);
   }
   responAPI.setErrorCode(ErrorCode.SUCCESS);
   responAPI.setErrorMessage(MessageAPI.SUCCESS);
   return ResponseEntity.ok(responAPI);
 }

  
}
