package com.rida.buddyquiz.buddyquiz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@EnableAsync
public class WebConfig implements WebMvcConfigurer {
  @Configuration
  public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
    }
  
      @Override
      public void addCorsMappings(CorsRegistry registry) {
          registry.addMapping("/ws/**")
              .allowedOrigins("http://localhost:8080/")
              .allowedMethods("*")
              .allowedHeaders("*")
              .allowCredentials(true);
      }
  }
}
