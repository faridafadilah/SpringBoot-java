package com.openfeign.learn.learnopenfeign.exception;

public class BadRequestException extends Exception {
  public BadRequestException(){}

  public BadRequestException(String message) {
    super(message);
  }

  public BadRequestException(Throwable cause) {
    super(cause);
  }

  @Override
  public String toString() {
    return "BadRequestException: " +getMessage();
  }
}
