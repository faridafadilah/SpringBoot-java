package com.faridaf.learnspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConfigurationController {
  @Autowired //Anotasi yang digunakan untuk melakukan inject instance dari suatu bean ke objek yang memiliki dependency
  private CurrentServiceConfiguration configuration;

  @RequestMapping("/currency-configuration")
  public CurrentServiceConfiguration retrieveAllCourse() {
    return configuration;
  }
}
