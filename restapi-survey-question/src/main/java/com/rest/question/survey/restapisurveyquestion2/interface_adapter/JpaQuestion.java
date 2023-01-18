package com.rest.question.survey.restapisurveyquestion2.interface_adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.rest.question.survey.restapisurveyquestion2.base.BasePageInterface;
import com.rest.question.survey.restapisurveyquestion2.dto.request.QuestionRequest;
import com.rest.question.survey.restapisurveyquestion2.dto.response.QuestionResponse;
import com.rest.question.survey.restapisurveyquestion2.entity.QuestionDataMapper;
import com.rest.question.survey.restapisurveyquestion2.entity.SurveyDataMapper;
import com.rest.question.survey.restapisurveyquestion2.repository.JpaQuestionRepository;
import com.rest.question.survey.restapisurveyquestion2.repository.JpaSurveyRepository;
import com.rest.question.survey.restapisurveyquestion2.specification.QuestionSpecification;
import com.rest.question.survey.restapisurveyquestion2.usecase.QuestionDsGateway;

public class JpaQuestion implements QuestionDsGateway, BasePageInterface<QuestionDataMapper, QuestionSpecification, QuestionResponse, String> {
  final JpaQuestionRepository questionRepository;

  final JpaSurveyRepository surveyRepository;

  final QuestionSpecification specification;
  
  private ModelMapper objectMapper = new ModelMapper();

  public JpaQuestion(JpaQuestionRepository questionRepository,JpaSurveyRepository surveyRepository, QuestionSpecification questionSpecification) {
    this.questionRepository = questionRepository;
    this.specification = questionSpecification;
    this.surveyRepository = surveyRepository;
  }

  @Override
  public void save(QuestionRequest request) {
    QuestionDataMapper question = objectMapper.map(request, QuestionDataMapper.class);
    questionRepository.save(question);
  }
  
  @Override
  public void update(QuestionRequest request, String id) {
    Optional<QuestionDataMapper> qOptional = questionRepository.findById(id);
    QuestionDataMapper question = qOptional.get();
      question.setId(id);
      question.setDescription(request.getDescription());
      question.setCorrectAnswer(request.getCorrectAnswer());
      questionRepository.save(question);
  }

  @Override
  public void delete(String id) {
    questionRepository.deleteById(id);
  }

  @Override 
  public List<QuestionResponse> findBySurvey(String id) {
    Optional<SurveyDataMapper> optionalS = surveyRepository.findById(id);
    List<QuestionResponse>  response = QuestionResponse.getInstance(optionalS.get());
    return response;
  }

  @Override
  public Page<QuestionResponse> getAllQuestion(String search, Integer page, Integer limit, List<String> sortBy, Boolean desc) {
    Pageable pageableRequest = this.defaultPage(search, page, limit, sortBy, desc);

    Page<QuestionDataMapper> settingPage = questionRepository.findAll(this.defaultSpec(search, specification), pageableRequest);

    //convert to list dto
    List<QuestionDataMapper> list = settingPage.getContent();
    List<QuestionResponse> responseList = list.stream().map(a -> objectMapper.map(a, QuestionResponse.class)).collect(Collectors.toList());
    Page<QuestionResponse> responsePage = new PageImpl<>(responseList, pageableRequest, settingPage.getTotalElements());
    return responsePage;
  }
}
