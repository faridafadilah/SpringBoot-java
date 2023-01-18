package com.rest.question.survey.restapisurveyquestion2.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="question")
public class QuestionDataMapper {
  @Id
  private String id;
  private String description;
  private String correctAnswer;

  @ManyToOne
  @JoinColumn(name = "survey_id")
  private SurveyDataMapper survey;
}
