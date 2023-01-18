package com.rest.question.survey.restapisurveyquestion.dto.request;

import javax.validation.constraints.NotNull;

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
  @NotNull
  private String id;
  @NotNull
  private String correctAnswer;
  @NotNull
  private String description;
  @NotNull
  private String surveyId;
}
