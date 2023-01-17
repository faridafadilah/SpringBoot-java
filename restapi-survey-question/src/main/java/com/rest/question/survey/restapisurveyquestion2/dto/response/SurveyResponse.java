package com.rest.question.survey.restapisurveyquestion.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SurveyResponse {
  private String id;
  private String title;
  private String description;
}
