package com.spring.mailjava.core;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendHtmlEmail {
  public static void main(String[] args) {
    String host = "smtp.mailtrap.io";
    String to = "faridafadilah42807@gmail.com";
    final String user = "fd3457905072e2";
    final String password = "a7ccffc1726ff0";

    Properties properties = System.getProperties();
    properties.setProperty("mail.smtp.host", host);
    properties.put("mail.smtp.auth", true);

    Session session = Session.getDefaultInstance(properties,  
    new javax.mail.Authenticator() {  
     protected PasswordAuthentication getPasswordAuthentication() {  
      return new PasswordAuthentication(user,password);  
     }  
    });  

    try {
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(user));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

      message.setSubject("HTML Message");
      message.setContent("<h1>sending html mail check</h1>", "text/html");

      Transport.send(message);
      System.out.print("message sent......");
    } catch (MessagingException ex) {
      ex.printStackTrace();
    }
  }
}
