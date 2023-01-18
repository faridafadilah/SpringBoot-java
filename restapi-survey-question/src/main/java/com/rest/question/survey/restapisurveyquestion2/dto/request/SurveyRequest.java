package com.rest.question.survey.restapisurveyquestion2.dto.request;

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
public class SurveyRequest {
  @NotNull
  private String id;
  @NotNull
  private String title;
  @NotNull
  private String description;
}
