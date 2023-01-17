package com.rest.question.survey.restapisurveyquestion.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommonSurvey implements Survey {
  String id;
  String title;
  String description;
}
