package com.rest.question.survey.restapisurveyquestion.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class QuestionRequest {
  private String id;
  private String correctAnswer;
  private String description;
  private String surveyId;
}
