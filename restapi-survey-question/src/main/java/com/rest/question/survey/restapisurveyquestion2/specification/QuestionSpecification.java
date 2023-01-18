package com.rest.question.survey.restapisurveyquestion2.specification;

import java.util.Arrays;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.rest.question.survey.restapisurveyquestion2.base.BaseSpecification;
import com.rest.question.survey.restapisurveyquestion2.entity.QuestionDataMapper;

@Component
public class QuestionSpecification extends BaseSpecification<QuestionDataMapper> {
  @Override
	public Specification<QuestionDataMapper> containsTextInOmni(String text) {
		return containsTextInAttributes(text,
				Arrays.asList("description", "transactionNumber", "transactionType", "platform"));
	}
}