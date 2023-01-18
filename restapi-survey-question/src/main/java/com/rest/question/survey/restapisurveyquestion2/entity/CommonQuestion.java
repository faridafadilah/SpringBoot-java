package com.rest.question.survey.restapisurveyquestion2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommonQuestion implements Question {
  String id;
  String description;
  String correctAnswer;
  String surveyId;
}
