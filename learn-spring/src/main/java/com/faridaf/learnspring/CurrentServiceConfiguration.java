package com.faridaf.learnspring;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// currency-service.url=
// currency-service.username=
// currency-service.key=
// Mengambil data dari Properties
@ConfigurationProperties(prefix = "currency-service")
@Component
public class CurrentServiceConfiguration {
  private String url;
  private String username;
  private String key;

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getKey() {
    return this.key;
  }

  public void setKey(String key) {
    this.key = key;
  }

}