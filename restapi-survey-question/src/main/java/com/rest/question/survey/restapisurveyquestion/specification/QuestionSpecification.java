package com.rest.question.survey.restapisurveyquestion.specification;

import java.util.Arrays;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.rest.question.survey.restapisurveyquestion.base.BaseSpecification;
import com.rest.question.survey.restapisurveyquestion.models.Question;

@Component
public class QuestionSpecification extends BaseSpecification<Question> {
  @Override
	public Specification<Question> containsTextInOmni(String text) {
		return containsTextInAttributes(text,
				Arrays.asList("description", "transactionNumber", "transactionType", "platform"));
	}
}
