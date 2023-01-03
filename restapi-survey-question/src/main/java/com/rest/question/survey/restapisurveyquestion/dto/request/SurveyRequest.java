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
public class SurveyRequest {
  private String id;
  private String title;
  private String description;
}
