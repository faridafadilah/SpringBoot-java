package com.rest.question.survey.restapisurveyquestion.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.question.survey.restapisurveyquestion.base.ResponAPI;
import com.rest.question.survey.restapisurveyquestion.constant.ErrorCode;
import com.rest.question.survey.restapisurveyquestion.constant.MessageAPI;
import com.rest.question.survey.restapisurveyquestion.dto.request.SurveyRequest;
import com.rest.question.survey.restapisurveyquestion.dto.response.SurveyResponse;
import com.rest.question.survey.restapisurveyquestion.services.SurveyService;

@RestController
@RequestMapping("/api")
public class SurveyController {
  @Autowired
  private SurveyService surveyService;

  //Get All Survey
  @GetMapping("/surveys")
  public ResponseEntity<ResponAPI<Page<SurveyResponse>>> getAllSurvey() {
    Page<SurveyResponse> resSurvey = surveyService.getAllSurvey();
    ResponAPI<Page<SurveyResponse>> responAPI = new ResponAPI<>();
    responAPI.setData(resSurvey);
    responAPI.setErrorCode(ErrorCode.SUCCESS);
    responAPI.setErrorMessage(MessageAPI.SUCCESS);
    return ResponseEntity.ok(responAPI);
  }

  // Add Survey
  @PostMapping("/add-survey")
  public ResponseEntity<ResponAPI<SurveyResponse>> createSurvey(@RequestBody SurveyRequest body) {
    ResponAPI<SurveyResponse> responAPI = new ResponAPI<>();
    if(!surveyService.createSurvey(body, responAPI)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responAPI);
    }
    return ResponseEntity.ok(responAPI);
 }

  // Edit Survey
  @PostMapping("/edit-survey/{id}")
  public ResponseEntity<ResponAPI<SurveyResponse>> updateSurvey(@PathVariable String id, @RequestBody SurveyRequest body) {
    ResponAPI<SurveyResponse> responAPI = new ResponAPI<>();
    if(!surveyService.updateSurvey(body, id, responAPI)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responAPI);
    }
    return ResponseEntity.ok(responAPI);
  }  

  //Delete Survey
  @DeleteMapping("/delete-survey/{id}")
  public ResponseEntity<ResponAPI<SurveyResponse>> deleteSurvey(@PathVariable String id) {
    ResponAPI<SurveyResponse> responAPI = new ResponAPI<>();
    if(!surveyService.deleteSurvey(id, responAPI)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responAPI);
    }
    return ResponseEntity.ok(responAPI);
  }
}
