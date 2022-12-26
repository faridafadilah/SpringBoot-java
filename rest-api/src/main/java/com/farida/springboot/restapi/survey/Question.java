package com.farida.springboot.restapi.survey;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Question {
  private String id;
  private String description;
  private List<String> options;
  private String correctAnswer;
}