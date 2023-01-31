package com.spring.mailjava.core;

import java.io.BufferedReader;
import com.sun.mail.imap.protocol.FLAGS;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;

public class DeleteMail {
  public static void main(String args[]) throws Exception {
    String user = "fd3457905072e2";
    String password = "a7ccffc1726ff0";

    Properties properties = System.getProperties();
    Session session = Session.getDefaultInstance(properties);

    Store store = session.getStore("pop3");
    store.connect("smtp.mailtrap.io", user, password);

    Folder folder = store.getFolder("inbox");

    if(!folder.exists()) {
      System.out.println("inbox not found!");
      System.exit(0);
    }

    folder.open(Folder.READ_WRITE);

    Message[] msg = folder.getMessages();

    for (int i = 0; i < msg.length; i++) {  
      System.out.println("--------- " + (i + 1) + "------------");  
      String from = InternetAddress.toString(msg[i].getFrom());  
      
      if (from != null) {  
        System.out.println("From: " + from);  
      }  
     
      String replyTo = InternetAddress.toString(  
      msg[i].getReplyTo());  
      if (replyTo != null) {  
       System.out.println("Reply-to: " + replyTo);  
      }  
      String to = InternetAddress.toString(  
      msg[i].getRecipients(Message.RecipientType.TO));  
        
      if (to != null) {  
        System.out.println("To: " + to);  
      }  
      
      String subject = msg[i].getSubject();  
      if (subject != null) {  
        System.out.println("Subject: " + subject);  
      }  
      Date sent = msg[i].getSentDate();  
      if (sent != null) {  
        System.out.println("Sent: " + sent);  
      }  
      System.out.println("Message : ");  
      System.out.println(msg[i].getContent());  
    }

     // get the message number to delete (optional)  
    System.out.println("Enter message number to delete :");  
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
    String no = br.readLine();  
    //5) delete the message using setFlag method  
    msg[Integer.parseInt(no) - 1].setFlag(FLAGS.Flag.DELETED, true);  
      
    System.out.println("Message Deleted .....");  
    
    folder.close(true);  
    store.close();  
  }  
}     
