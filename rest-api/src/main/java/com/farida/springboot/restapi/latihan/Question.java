package com.farida.springboot.restapi.latihan;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Question {
  @Id
  @GeneratedValue
  private String id;
  private String description;
  private List<String> options;
  private String correctAnswer;
  @ManyToOne
  @JoinColumn(name = "survey_id")
  private Survey survey;
}
