package com.rest.question.survey.restapisurveyquestion2.usecase;

import java.util.List;

import org.springframework.data.domain.Page;

import com.rest.question.survey.restapisurveyquestion2.dto.request.QuestionRequest;
import com.rest.question.survey.restapisurveyquestion2.dto.response.QuestionResponse;

public interface QuestionDsGateway {

  void save(QuestionRequest questionRequest);

  void update(QuestionRequest questionRequest, String id);

  void delete(String id);

  List<QuestionResponse> findBySurvey(String id);

  Page<QuestionResponse> getAllQuestion(String search, Integer page, Integer limit, List<String> sortBy, Boolean desc);
  
}
