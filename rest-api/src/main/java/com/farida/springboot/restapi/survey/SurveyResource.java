package com.farida.springboot.restapi.survey;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

//Controller Rest API
@RestController
public class SurveyResource {
  private SurveyService surveyService;

  public SurveyResource(SurveyService surveyService) {
    this.surveyService = surveyService;
  }
  //Menampilkan Semua data survey
  @RequestMapping("/surveys")
  public List<Survey> retrieveAllSurveys() {
    return surveyService.retrieveAllSurveys();
  }
  //Menampilkan data survey berdasarkan id
  @RequestMapping("/surveys/{surveyId}")
  public Survey retrieveByIdSurveys(@PathVariable String surveyId ) {
    Survey survey = surveyService.retrieveSurveyById(surveyId);
    if(survey== null)
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    return survey;
  }
  // Menampilkan Semua question berdasarkan id survey 
  @RequestMapping("/surveys/{surveyId}/questions")
  public List<Question> retrieveAllSurveyQuestion(@PathVariable String surveyId ) {
    List<Question> questions = surveyService.retrieveAllSurveyQuestion(surveyId);
    if(questions== null)
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    return questions;
  }
  //Menampilkan Question berdasarkan idQuestion dan idSurvey 
  @RequestMapping("/surveys/{surveyId}/questions/{questionId}")
  public Question retrieveSpecificSurveyQuestion(@PathVariable String surveyId, @PathVariable String questionId) {
    Question question = surveyService.retrieveSpesificSurveyQuestion(surveyId, questionId);
    if(question== null)
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    return question;
  }
  
  //Menambahkan question
  @RequestMapping(value="/surveys/{surveyId}/questions", method = RequestMethod.POST)
  public ResponseEntity<Object> addNewSurveyQuestion(@PathVariable String surveyId, @RequestBody Question question){ 
    String questionId = surveyService.addNewSurveyQuestion(surveyId, question);
    //Header Location
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{questionId}").buildAndExpand(questionId).toUri();
    //Response
    return ResponseEntity.created(location).build();
  }

  //Menghapus question
  @RequestMapping(value = "/surveys/{surveyId}/questions/{questionId}", method = RequestMethod.DELETE)
  public ResponseEntity<Object> deletesurveyQuestion(@PathVariable String surveyId, @PathVariable String questionId) {
    surveyService.deleteSurveyQuestion(surveyId, questionId);
    return ResponseEntity.noContent().build();
  }

  //Mengedit question
  @RequestMapping(value = "/surveys/{surveyId}/questions/{questionId}", method = RequestMethod.PUT)
  public ResponseEntity<Object> updateSurveyQuestion(@PathVariable String surveyId, @PathVariable String questionId, @RequestBody Question question){ 
    surveyService.updateSurveyQuestion(surveyId, questionId, question);
    //Response
    return ResponseEntity.noContent().build();
  }

}