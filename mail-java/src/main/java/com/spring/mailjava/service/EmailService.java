package com.spring.mailjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
  @Autowired
  private JavaMailSender sender;

  public void sendEmail(String to, String subject, String messsage) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setSubject(subject);
    message.setText(messsage);
    message.setFrom("faridafadilah42807@gmail.com");
    sender.send(message);
  }
}
