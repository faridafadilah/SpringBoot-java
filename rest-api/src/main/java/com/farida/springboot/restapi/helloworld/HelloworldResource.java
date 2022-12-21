package com.farida.springboot.restapi.helloworld;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloworldResource {
  //API Params
  @RequestMapping("hello-world-param/{name}")
  public HelloWorldBean helloWorldPathParam(@PathVariable String name) {
    return new HelloWorldBean("Hello World, "+name);
  }
  //API Multiparam
  @RequestMapping("hello-world-param/{name}/message/{message}")
  public HelloWorldBean helloWorldMultiPathParam(@PathVariable String name, @PathVariable String message) {
    return new HelloWorldBean("Hello World "+name + "," + message);
  }
}
