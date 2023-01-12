package com.openfeign.learn.learnopenfeign.config;

import org.springframework.context.annotation.Bean;
import org.apache.http.entity.ContentType;

import feign.Logger;
import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.ErrorDecoder;
import feign.okhttp.OkHttpClient;

public class ClientConfiguration {
  @Bean
  public Logger.Level feignLoggerLevel() {
    return Logger.Level.FULL;
  }

  @Bean
  public ErrorDecoder errorDecoder() {
    return new CustomErrorDecoder();
  }

  @Bean
  public OkHttpClient client() {
    return new OkHttpClient();
  }

  @Bean
  public RequestInterceptor requestInterceptor() {
    return requestTemplate -> {
      requestTemplate.header("user", "rida");
      requestTemplate.header("password", "farida");
      requestTemplate.header("Accept", ContentType.APPLICATION_JSON.getMimeType());
    };
  }

  @Bean
  public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
    return new BasicAuthRequestInterceptor("rida", "farida");
  }
}
