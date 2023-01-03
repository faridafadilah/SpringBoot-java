package com.rest.question.survey.restapisurveyquestion.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Question {
  @Id
  @GeneratedValue
  private String id;
  private String description;
  @Column
  @ElementCollection(targetClass=Integer.class)
  private List<String> options;
  private String correctAnswer;
  @ManyToOne
  @JoinColumn(name = "survey_id",insertable = false, updatable = false)
  private Survey survey;
}


