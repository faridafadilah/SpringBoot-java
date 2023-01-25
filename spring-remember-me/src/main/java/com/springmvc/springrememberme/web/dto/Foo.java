package com.springmvc.springrememberme.web.dto;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Foo implements Serializable {
  private Long id;
  private String name;

  public Foo() {
    super();
  }

  public Foo(final String name) {
    super();
    this.name = name;
  }
}
