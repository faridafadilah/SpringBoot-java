package com.myapp.springboot.firstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
  @RequestMapping("say-hello")
  @ResponseBody // untuk menampilkan tanpa callback
  public String sayHello() {
    return "Hello Farida Fadilah! Welcome to Spring Boot";
  }

  @RequestMapping("say-html")
  @ResponseBody
  public String sayHelloHtml() {
    StringBuffer sb = new StringBuffer();
    sb.append("<h1>Hello HTML</h1>");
    return sb.toString();
  }

  @RequestMapping("say-hello-jsp")
  public String sayHelloJSP() {
    return "sayHello";
  }
}