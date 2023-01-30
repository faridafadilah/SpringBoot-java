package com.spring.mailjava.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.mailjava.mail.Email;

public class Application {
  public static void main(String[] args) {
    
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    Email email = (Email) context.getBean("email");

    String senderEmailId = "faridafadilah42807@gmail.com";
    String receiverEmailId = "hyam40461gmail.com";
    String subject = "Hallo Gais!";
    String message = "I am no feeling well, I am taking sick leave";

    email.sendEmail(senderEmailId, receiverEmailId, subject, message);
    System.out.println("Email sent Successfully");

    
  }
}
