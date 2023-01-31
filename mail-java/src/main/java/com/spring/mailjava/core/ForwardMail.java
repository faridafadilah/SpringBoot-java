package com.spring.mailjava.core;

import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class ForwardMail {
  public static void main(String[] args) throws Exception {
    final String user = "fd3457905072e2";
    final String password = "a7ccffc1726ff0";

    // get the session object
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.mailtrap.io");
    props.put("mail.smtp.auth", "true");

    Session session = Session.getDefaultInstance(props,
        new javax.mail.Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, password);
          }
        });

    // Get a store object and connect to the current host
    Store store = session.getStore("pop3");
    store.connect("smtp.mailtrap.io", user, password);

    // Create a Folder object and opent the folder
    Folder folder = store.getFolder("inbox");
    folder.open(Folder.READ_ONLY);

    Message message = folder.getMessage(1);

    // Get all the information from the message
    String from = InternetAddress.toString(message.getFrom());
    if (from != null) {
      System.out.println("From: " + from);
    }
    String replyTo = InternetAddress.toString(
        message.getReplyTo());
    if (replyTo != null) {
      System.out.println("Reply-to: " + replyTo);
    }
    String to = InternetAddress.toString(
        message.getRecipients(Message.RecipientType.TO));
    if (to != null) {
      System.out.println("To: " + to);
    }

    String subject = message.getSubject();
    if (subject != null) {
      System.out.println("Subject: " + subject);
    }
    Date sent = message.getSentDate();
    if (sent != null) {
      System.out.println("Sent: " + sent);
    }
    System.out.println(message.getContent());

    Message message2 = new MimeMessage(session);
    message2.setSubject("Fwd: " + message.getSubject());
    message2.setFrom(new InternetAddress(from));
    message2.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

    BodyPart messageBodyPart = new MimeBodyPart();
    messageBodyPart.setText("Oiginal message:\n\n");

    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(messageBodyPart);

    messageBodyPart = new MimeBodyPart();
    messageBodyPart.setDataHandler(message.getDataHandler());

    multipart.addBodyPart(messageBodyPart);

    message2.setContent(multipart);

    Transport.send(message2);
    System.out.print("message forwarded.....");
  }
}
