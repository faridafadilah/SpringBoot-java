package com.farida.springboot.restapi.helloworld;

public class HelloWorldBean {
  private String message;

  @Override
  public String toString() {
    return "HelloWorldBean [message=" + message + "]";
  }

  public String getMessage() {
    return message;
  }

  public HelloWorldBean(String message) {
    this.message = message;
  }
}
