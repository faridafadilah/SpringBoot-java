package com.rest.question.survey.restapisurveyquestion.base;

import java.util.ArrayList;
import java.util.List;

import com.rest.question.survey.restapisurveyquestion.dto.response.QuestionResponse;
import com.rest.question.survey.restapisurveyquestion.models.Question;
import com.rest.question.survey.restapisurveyquestion.models.Survey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponAPI<T> {
  private String errorMessage;
  private String errorCode;
  private T data;
}