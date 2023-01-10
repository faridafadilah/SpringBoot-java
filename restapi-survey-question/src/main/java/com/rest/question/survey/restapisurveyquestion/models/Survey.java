package com.rest.question.survey.restapisurveyquestion.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Survey {
  @Id
  private String id;
  private String title;
  private String description;
  @OneToMany(mappedBy = "survey")
  private List<Question> questions;
}