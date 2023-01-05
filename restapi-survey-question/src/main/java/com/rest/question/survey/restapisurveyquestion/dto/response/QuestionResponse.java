package com.rest.question.survey.restapisurveyquestion.dto.response;

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
public class QuestionResponse {
  private String id;
  private String correctAnswer;
  private String description;
  private String surveyId;
}
