package com.spring.mailjava.core;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import com.sun.mail.pop3.POP3Store;  

public class ReceiveEmail {

	public static void receiveEmail(String pop3Host, String storeType, String user, String password) {
		try {
			Properties properties = new Properties();
			properties.put("mail.pop3.host", pop3Host);
			Session emailSession = Session.getDefaultInstance(properties);
			
			POP3Store emailStore = (POP3Store) emailSession.getStore(storeType);
			emailStore.connect("fd3457905072e2", "a7ccffc1726ff0");
			
			Folder emailFolder = emailStore.getFolder("My Inbox");
			emailFolder.open(Folder.READ_ONLY);
			
			Message[] messages = emailFolder.getMessages();
			for(int i=0; i < messages.length; i++) {
				Message message = messages[i];
				System.out.println("---------------------------------");  
			    System.out.println("Email Number " + (i + 1));  
			    System.out.println("Subject: " + message.getSubject());  
			    System.out.println("From: " + message.getFrom()[0]);  
			    System.out.println("Text: " + message.getContent().toString());  
			}
			emailFolder.close(false);
			emailStore.close();
		}  catch (NoSuchProviderException e) {e.printStackTrace();}   
		   catch (MessagingException e) {e.printStackTrace();}  
		   catch (IOException e) {e.printStackTrace();}  
	}
	
	public static void main(String[] args) {
		String host = "smtp.mailtrap.io";//change accordingly  
		String mailStoreType = "pop3";  
		String username= "fd3457905072e2";  
		String password= "a7ccffc1726ff0";//change accordingly  
		  
		receiveEmail(host, mailStoreType, username, password);  
		  
	}  

}

