package com.chatapp.websocket.chatapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Message {
  private String senderName;
  private String receiverName;
  private String message;
  private String date;
  private Status status;

  public enum Status {
    JOIN,
    MESSAGE,
    LEAVE
  }     
}
