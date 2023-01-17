package com.rest.question.survey.restapisurveyquestion.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.question.survey.restapisurveyquestion.dto.request.SurveyRequest;
import com.rest.question.survey.restapisurveyquestion.dto.response.SurveyResponse;
import com.rest.question.survey.restapisurveyquestion.usecase.SurveyInputBoundary;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class SurveyController {

  final SurveyInputBoundary surveyInput;

  // Add Survey
  @PostMapping("/add-survey")
  public SurveyResponse createSurvey(@RequestBody SurveyRequest requestModel) {
    return surveyInput.createSurvey(requestModel);
 }
}
