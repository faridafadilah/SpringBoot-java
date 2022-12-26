package com.farida.springboot.restapi.survey;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Survey {
  private String id;
  private String title;
  private String description;
  private List<Question> question;
}
