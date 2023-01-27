package com.spring.remembermesecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser("admin")
        .password("admin123").roles("ADMIN"); 
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/", "/login", "/static/**", "/error**").authenticated()
        .antMatchers("/admin")
        .access("hasRole('ADMIN')")
        .anyRequest()
        .permitAll()
        .and()
        .formLogin()
        .loginPage("/login")
        .loginProcessingUrl("/j_spring_security_check")
        .defaultSuccessUrl("/admin")
        .failureUrl("/login?error")
        .usernameParameter("username")
        .passwordParameter("password")
        .and().rememberMe()
        .key("rememberKey")
        .rememberMeParameter("remember")
        .tokenValiditySeconds(86400);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
