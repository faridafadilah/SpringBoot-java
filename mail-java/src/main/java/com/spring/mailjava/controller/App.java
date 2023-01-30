package com.spring.mailjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.spring.mailjava.service.EmailService;

@Controller
public class App {

  @Autowired
  EmailService emailService;

  
  @GetMapping("/send") 
  @ResponseBody
  public String sendEmailJava() {
    emailService.sendEmail("hisyam@gmail.com", "Hallo Hisyam", "Tolong ambilkan jemuran baju jika cuaca mau hujan");
    System.out.print("success send email");
    return "hello";
  }
}
