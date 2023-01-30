package com.spring.mailjava.core;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendAttachment {
  public static void main(String[] args) {
    String to = "faridafadilah42807@gmail.com";

    final String user = "fd3457905072e2";
    final String password = "a7ccffc1726ff0";

    Properties properties = System.getProperties();
    properties.setProperty("mail.smtp.host", "smtp.mailtrap.io");  
    properties.setProperty("mail.smtp.auth", "true");

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
    message.setSubject("Message Aleart");

    BodyPart messageBodyPart1 = new MimeBodyPart();
    messageBodyPart1.setText("This is message Body");

    MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
    String filename = "SendAttachment.java";//change accordingly  
    DataSource source = new FileDataSource(filename);  
    messageBodyPart2.setDataHandler(new DataHandler(source));  
    messageBodyPart2.setFileName(filename);  
    
    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(messageBodyPart1);
    multipart.addBodyPart(messageBodyPart2);

    message.setContent(multipart);

    Transport.send(message);
    System.out.println("message sent");
  }catch (MessagingException ex) {ex.printStackTrace();}  
  }
}
