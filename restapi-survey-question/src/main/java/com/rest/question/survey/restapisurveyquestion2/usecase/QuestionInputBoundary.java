package com.rest.question.survey.restapisurveyquestion.usecase;

import java.util.List;

import org.springframework.data.domain.Page;

import com.rest.question.survey.restapisurveyquestion.base.ResponAPI;
import com.rest.question.survey.restapisurveyquestion.dto.request.QuestionRequest;
import com.rest.question.survey.restapisurveyquestion.dto.response.QuestionResponse;

public interface QuestionInputBoundary {

  boolean createQuestion(QuestionRequest requestModel, ResponAPI<QuestionResponse> responAPI);

  boolean updateQuestion(String id, QuestionRequest requestModel, ResponAPI<QuestionResponse> responAPI);

  boolean deleteQuestion(String id, ResponAPI<QuestionResponse> responAPI);

  boolean getByIdSurvey(String id, ResponAPI<List<QuestionResponse>> responAPI);

  Page<QuestionResponse> getAllQuestion(String search, Integer page, Integer limit, List<String> sortBy, Boolean desc);
}
