package com.contact.springboot.contactapp.security;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SpringSecurityConfiguration {
  @Bean
  public InMemoryUserDetailsManager createUserDetailsManager() {
    Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);
    
    UserDetails userDetails = createNewUser("farida", "cantik", passwordEncoder);

    return new InMemoryUserDetailsManager(userDetails);
  }

  private UserDetails createNewUser(String username, String password, Function<String, String> passwordEncoder) {
    UserDetails userDetails = User.builder()
    .passwordEncoder(passwordEncoder)
    .username(username)
    .password(password)
    .roles("USER", "ADMIN")
    .build();
    return userDetails;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
