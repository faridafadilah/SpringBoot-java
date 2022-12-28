package com.farida.springboot.restapi.latihan;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Survey {
  @Id
  private String id;
  private String title;
  private String description;
  @OneToMany(mappedBy = "survey")
  private List<Question> questions;
}