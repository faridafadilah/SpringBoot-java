package com.spring.learnbruteforcesecurity.core.user.jpa.data;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FailedLogin {
  private int count;
  private LocalDateTime date;

  public FailedLogin() {
    this.count = 0;
    this.date = LocalDateTime.now();
  }

  public FailedLogin(int count, LocalDateTime date) {
    this.count = count;
    this.date = date;
  }
}
