package com.rest.question.survey.restapisurveyquestion.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Question {
  @Id
  private String id;
  private String description;
  private String correctAnswer;
  @ManyToOne
  @JoinColumn(name = "survey_id")
  private Survey survey;
}