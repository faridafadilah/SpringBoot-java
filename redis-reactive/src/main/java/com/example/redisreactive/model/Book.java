package com.example.redisreactive.model;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.data.redis.core.RedisHash;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RedisHash
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
  @NotNull
  private String id;
  private String title;
  private int page;
  private String isbn;
  private String description;
  private double price;

  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate publicationDate;
}
