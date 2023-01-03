package com.rest.question.survey.restapisurveyquestion.base;

import lombok.Data;

@Data
public class RequestAPI {
  private String surveyId;
  private String questionId;
}
