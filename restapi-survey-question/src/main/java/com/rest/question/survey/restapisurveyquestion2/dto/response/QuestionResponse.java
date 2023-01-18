package com.rest.question.survey.restapisurveyquestion.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.rest.question.survey.restapisurveyquestion.entity.QuestionDataMapper;
import com.rest.question.survey.restapisurveyquestion.entity.SurveyDataMapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponse {
  private String id;
  private String correctAnswer;
  private String description;
  private String surveyId;

  public static List<QuestionResponse> getInstance(SurveyDataMapper survey) {
    if(survey != null) {
      try {
        List<QuestionResponse> questions = new ArrayList<>();
        if(survey.getQuestions() != null) {
          if(!survey.getQuestions().isEmpty()) {
            for(QuestionDataMapper question : survey.getQuestions()) {
              QuestionResponse questionResponse = new QuestionResponse();
              questionResponse.setId(question.getId());
              questionResponse.setCorrectAnswer(question.getCorrectAnswer());
              questionResponse.setDescription(question.getDescription());
              questionResponse.setSurveyId(survey.getId());
              questions.add(questionResponse);
            }
          }
        }
        return questions;
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return null;
  }
}
