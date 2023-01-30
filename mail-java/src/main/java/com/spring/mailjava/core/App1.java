package com.spring.mailjava.core;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class App1 {

  public static void main(String[] args) {
    String host = "smtp.mailtrap.io";
    final String user = "fd3457905072e2";// user mailtrap.io
    final String password = "a7ccffc1726ff0";// password mailtrap.io

    String to = "hyam40461@gmail.com";

    // Get the session object
    Properties props = new Properties();
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.auth", "true");

    Session session = Session.getDefaultInstance(props,
        new javax.mail.Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, password);
          }
        });

    // Compose the message
    try {
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(user));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
      message.setSubject("javatpoint");
      message.setText("This is simple program of sending email using JavaMail API");

      // send the message
      Transport.send(message);

      System.out.println("message sent successfully...");

    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }

}
