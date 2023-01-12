package com.openfeign.learn.learnopenfeign.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Games {
  String title;
  String thumb;
  String author;
  String tag;
  String time;
  String desc;
  String key;
}
