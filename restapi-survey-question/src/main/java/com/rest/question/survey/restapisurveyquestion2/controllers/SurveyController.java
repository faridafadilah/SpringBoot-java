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

import com.rest.question.survey.restapisurveyquestion2.base.BaseResponsePage;
import com.rest.question.survey.restapisurveyquestion2.base.ResponAPI;
import com.rest.question.survey.restapisurveyquestion2.constant.ErrorCode;
import com.rest.question.survey.restapisurveyquestion2.constant.MessageAPI;
import com.rest.question.survey.restapisurveyquestion2.dto.request.SurveyRequest;
import com.rest.question.survey.restapisurveyquestion2.dto.response.DtoResListSurvey;
import com.rest.question.survey.restapisurveyquestion2.dto.response.SurveyResponse;
import com.rest.question.survey.restapisurveyquestion2.usecase.SurveyInputBoundary;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class SurveyController {

  final SurveyInputBoundary surveyInput;

  //Get All Surveys
  @GetMapping("/surveys")
  public ResponseEntity<ResponAPI<BaseResponsePage>> getAllSurvey(
      @RequestParam(value = "search", required = false) String search,
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "limit", required = false) Integer limit,
      @RequestParam(value = "sortBy", required = false) List<String>sortBy,
      @RequestParam(value = "descending", required = false) Boolean desc) {
    Page<DtoResListSurvey> surveyNotPending = surveyInput.getAllSurvey(search, page, limit, sortBy, desc);
    ResponAPI<BaseResponsePage> responAPI = new ResponAPI<>();
    BaseResponsePage<List<DtoResListSurvey>> basePage = new BaseResponsePage<>();
    basePage.setContent(surveyNotPending.toList());
    basePage.setCurrentPage(surveyNotPending.getPageable().getPageNumber());
    basePage.setTotalPage(surveyNotPending.getPageable().getPageSize());
    basePage.setTotalElement(surveyNotPending.getTotalElements());
    responAPI.setErrorMessage(MessageAPI.SUCCESS);
    responAPI.setErrorCode(ErrorCode.SUCCESS);
    responAPI.setData(basePage);
    return ResponseEntity.status(HttpStatus.OK).body(responAPI);
  }

  // Add Survey
  @PostMapping("/add-survey")
  public ResponseEntity<ResponAPI<SurveyResponse>> createSurvey(@RequestBody SurveyRequest requestModel) {
    ResponAPI<SurveyResponse> responAPI = new ResponAPI<>();
    if(!surveyInput.createSurvey(requestModel, responAPI)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responAPI);
    }
    return ResponseEntity.ok(responAPI);
 }

 //Edit Survey
 @PostMapping("/edit-survey/{id}")
  public ResponseEntity<ResponAPI<SurveyResponse>> updateSurvey(@PathVariable String id, @RequestBody SurveyRequest requestModel) {
    ResponAPI<SurveyResponse> responAPI = new ResponAPI<>();
    if(!surveyInput.updateSurvey(id, requestModel, responAPI)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responAPI);
    }
    return ResponseEntity.ok(responAPI);
  }

  //Delete Survey
  @DeleteMapping("/delete-survey/{id}")
  public ResponseEntity<ResponAPI<SurveyResponse>> deleteSurvey(@PathVariable String id) {
    ResponAPI<SurveyResponse> responAPI = new ResponAPI<>();
    if(!surveyInput.deleteSurvey(id, responAPI)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responAPI);
    }
    return ResponseEntity.ok(responAPI);
  }

   // Get Surveys by Id
  @GetMapping("/surveys/{id}")
  public ResponseEntity<ResponAPI<DtoResListSurvey>> getDetailSurvey(@PathVariable("id") String id) {
    ResponAPI<DtoResListSurvey> responAPI = new ResponAPI<>();
    if(!surveyInput.getByIdSurvey(id, responAPI)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responAPI);
    }
    responAPI.setErrorCode(ErrorCode.SUCCESS);
    responAPI.setErrorMessage(MessageAPI.SUCCESS);
    return ResponseEntity.ok(responAPI);
  }
}