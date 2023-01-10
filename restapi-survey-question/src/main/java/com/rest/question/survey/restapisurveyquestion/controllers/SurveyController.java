package com.rest.question.survey.restapisurveyquestion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.rest.question.survey.restapisurveyquestion.base.BaseController;
import com.rest.question.survey.restapisurveyquestion.base.BaseResponsePage;
import com.rest.question.survey.restapisurveyquestion.base.ResponAPI;
import com.rest.question.survey.restapisurveyquestion.constant.ErrorCode;
import com.rest.question.survey.restapisurveyquestion.constant.MessageAPI;
import com.rest.question.survey.restapisurveyquestion.dto.request.SurveyRequest;
import com.rest.question.survey.restapisurveyquestion.dto.response.DtoResListSurvey;
import com.rest.question.survey.restapisurveyquestion.dto.response.SurveyResponse;
import com.rest.question.survey.restapisurveyquestion.services.SurveyService;


@RestController
@RequestMapping("/api")
public class SurveyController extends BaseController {
  @Autowired
  private SurveyService surveyService;

  //Get All Surveys
  @GetMapping("/surveys")
  public ResponseEntity<ResponAPI<BaseResponsePage>> getAllSurvey(
      @RequestParam(value = "search", required = false) String search,
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "limit", required = false) Integer limit,
      @RequestParam(value = "sortBy", required = false) List<String>sortBy,
      @RequestParam(value = "descending", required = false) Boolean desc) {
    Page<DtoResListSurvey> surveyNotPending = surveyService.getAllSurvey(search, page, limit, sortBy, desc);
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

  // Get Surveys by Id
  @GetMapping("/surveys/{id}")
  public ResponseEntity<ResponAPI<DtoResListSurvey>> getDetailSurvey(@PathVariable("id") String id) {
    ResponAPI<DtoResListSurvey> responAPI = new ResponAPI<>();
    if(!surveyService.getSurveyById(responAPI, id)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responAPI);
    }
    responAPI.setErrorCode(ErrorCode.SUCCESS);
    responAPI.setErrorMessage(MessageAPI.SUCCESS);
    return ResponseEntity.status(HttpStatus.OK).body(responAPI);
  }

  // Add Survey
  @PostMapping("/add-survey")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<ResponAPI<SurveyResponse>> createSurvey(@RequestBody SurveyRequest body) {
    ResponAPI<SurveyResponse> responAPI = new ResponAPI<>();
    if(!surveyService.createSurvey(body, responAPI)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responAPI);
    }
    return ResponseEntity.ok(responAPI);
 }

  // Edit Survey
  @PostMapping("/edit-survey/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<ResponAPI<SurveyResponse>> updateSurvey(@PathVariable String id, @RequestBody SurveyRequest body) {
    ResponAPI<SurveyResponse> responAPI = new ResponAPI<>();
    if(!surveyService.updateSurvey(body, id, responAPI)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responAPI);
    }
    return ResponseEntity.ok(responAPI);
  }  

  //Delete Survey
  @DeleteMapping("/delete-survey/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<ResponAPI<SurveyResponse>> deleteSurvey(@PathVariable String id) {
    ResponAPI<SurveyResponse> responAPI = new ResponAPI<>();
    if(!surveyService.deleteSurvey(id, responAPI)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responAPI);
    }
    return ResponseEntity.ok(responAPI);
  }
}


// Get All Survey
  // @GetMapping("/surveys")
  // public ResponseEntity<Map<String, Object>> getAllSurvey(
  //   @RequestParam(required = false) String title,
  //   @RequestParam(defaultValue = "0") int page,
  //   @RequestParam(defaultValue = "3") int size
  // ) {
  //   try {
  //     List<Survey> surveys = new ArrayList<Survey>();
  //     Pageable paging = PageRequest.of(page, size);

  //     Page<Survey> pageSurvey;
  //     if(title == null) {
  //       pageSurvey = surveyRepository.findAll(paging);
  //     } else {
  //       pageSurvey = surveyRepository.findByTitleContaining(title, paging);
  //     }
  //     surveys = pageSurvey.getContent();

  //     Map<String, Object> response = new HashMap<>();
  //     response.put("surveys", surveys);
  //     response.put("currentPage", pageSurvey.getNumber());
  //     response.put("totalItems", pageSurvey.getTotalElements());
  //     response.put("totalPages", pageSurvey.getTotalPages());

  //     return new ResponseEntity<>(response, HttpStatus.OK);
  //   } catch (Exception e) {
  //     return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  //   }
  // }