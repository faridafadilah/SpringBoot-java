package com.farida.springboot.restapi.survey;

import java.util.List;

public class Survey {
  public Survey() {
  }

  public Survey(String id, String title, String description, List<Question> question) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.question = question;
  }

  private String id;
  private String title;
  private String description;
  private List<Question> question;
  
  public String getId() {
    return id;
  }
  public String getTitle() {
    return title;
  }
  public String getDescription() {
    return description;
  }
  public List<Question> getQuestion() {
    return question;
  }
  @Override
  public String toString() {
    return "Survey [id=" + id + ", title=" + title + ", description=" + description + ", question=" + question + "]";
  }
}
