package com.rest.question.survey.restapisurveyquestion.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.rest.question.survey.restapisurveyquestion.models.Question;
import com.rest.question.survey.restapisurveyquestion.models.Survey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoResListSurvey {
  public String id;
  public String title;
  public String description;
  public List<QuestionResponse> questions;

  public static DtoResListSurvey getInstance(Survey survey) {
    DtoResListSurvey dto = new DtoResListSurvey();
    if(survey != null) {
      try {
        dto.setId(survey.getId());
        dto.setTitle(survey.getTitle());
        dto.setDescription(survey.getDescription());
        
        List<QuestionResponse> questions = new ArrayList<>();
        if(survey.getQuestions() != null) {
          if(!survey.getQuestions().isEmpty()) {
            for(Question question : survey.getQuestions()) {
              QuestionResponse questionResponse = new QuestionResponse();
              questionResponse.setId(question.getId());
              questionResponse.setCorrectAnswer(question.getCorrectAnswer());
              questionResponse.setDescription(question.getDescription());
              questions.add(questionResponse);
              dto.setQuestions(questions);
              return dto;
            }
          }
        }
      } catch (Exception e) {
        return null;
      }
    }
    return null;
  }
}
