package com.rest.question.survey.restapisurveyquestion2.interface_adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.rest.question.survey.restapisurveyquestion2.base.BasePageInterface;
import com.rest.question.survey.restapisurveyquestion2.dto.request.SurveyRequest;
import com.rest.question.survey.restapisurveyquestion2.dto.response.DtoResListSurvey;
import com.rest.question.survey.restapisurveyquestion2.dto.response.SurveyResponse;
import com.rest.question.survey.restapisurveyquestion2.entity.SurveyDataMapper;
import com.rest.question.survey.restapisurveyquestion2.repository.JpaSurveyRepository;
import com.rest.question.survey.restapisurveyquestion2.specification.SurveySpecification;
import com.rest.question.survey.restapisurveyquestion2.usecase.SurveyCreateDsGateway;

public class JpaSurvey implements SurveyCreateDsGateway, BasePageInterface<SurveyDataMapper, SurveySpecification, SurveyResponse, String>{
  final JpaSurveyRepository surveyRepository;
  final SurveySpecification specification;
  private ModelMapper objectMapper = new ModelMapper();
  

  public JpaSurvey(JpaSurveyRepository surveyRepository, SurveySpecification specification) {
    this.surveyRepository = surveyRepository;
    this.specification = specification;
  }

  @Override
  public void save(SurveyRequest request) {
    SurveyDataMapper survey = objectMapper.map(request, SurveyDataMapper.class);
    surveyRepository.save(survey);
  }

  @Override
  public void update(SurveyRequest request, String id) {
    Optional<SurveyDataMapper> sOptional = surveyRepository.findById(id);
    SurveyDataMapper survey = sOptional.get();
      survey.setId(id);
      survey.setTitle(request.getTitle());
      survey.setDescription(request.getDescription());
      //save database
      surveyRepository.save(survey);
  }

  @Override
  public SurveyResponse delete(String id) {
    Optional<SurveyDataMapper> optionalS = surveyRepository.findById(id);
    SurveyDataMapper survey = optionalS.get();
    SurveyResponse response = new SurveyResponse(survey.getId(), survey.getTitle(), survey.getDescription());
    
    surveyRepository.deleteById(id);
    return response;
  }

  @Override
  public DtoResListSurvey getByIdSurvey(String id) {
    Optional<SurveyDataMapper> optionalS = surveyRepository.findById(id);
    DtoResListSurvey response = DtoResListSurvey.getInstance(optionalS.get());
    return response;
  }

  @Override
  public Page<DtoResListSurvey> getAllSurvey(String search, Integer page, Integer limit, List<String> sortBy, Boolean desc) {
    Pageable pageableRequest = this.defaultPage(search, page, limit, sortBy, desc);
    Page<SurveyDataMapper> settingPage = surveyRepository.findAll(this.defaultSpec(search, specification), pageableRequest);
    List<SurveyDataMapper> surveys = settingPage.getContent();
    List<DtoResListSurvey> responseList = new ArrayList<>();
    surveys.stream().forEach(a -> {
      responseList.add(DtoResListSurvey.getInstance(a));
    });
    Page<DtoResListSurvey> response = new PageImpl<>(responseList, pageableRequest, settingPage.getTotalElements());
    return response;
  }

}
