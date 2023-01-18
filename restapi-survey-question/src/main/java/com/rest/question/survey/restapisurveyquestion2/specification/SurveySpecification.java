package com.rest.question.survey.restapisurveyquestion.specification;

import java.util.Arrays;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.rest.question.survey.restapisurveyquestion.base.BaseSpecification;
import com.rest.question.survey.restapisurveyquestion.entity.SurveyDataMapper;

@Component
public class SurveySpecification extends BaseSpecification<SurveyDataMapper> {
  @Override
	public Specification<SurveyDataMapper> containsTextInOmni(String text) {
		return containsTextInAttributes(text,
				Arrays.asList("description", "transactionNumber", "transactionType", "platform"));
	}
}
 