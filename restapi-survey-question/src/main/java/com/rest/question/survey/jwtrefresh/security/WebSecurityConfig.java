package com.rest.question.survey.restapisurveyquestion.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.rest.question.survey.restapisurveyquestion.Interceptor.AdminInterceptor;
import com.rest.question.survey.restapisurveyquestion.Interceptor.LogoutInterceptor;
import com.rest.question.survey.restapisurveyquestion.Interceptor.UserInterceptor;
import com.rest.question.survey.restapisurveyquestion.security.jwt.AuthEntryPointJwt;
import com.rest.question.survey.restapisurveyquestion.security.jwt.AuthTokenFilter;
import com.rest.question.survey.restapisurveyquestion.security.services.UserDetailsServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig implements WebMvcConfigurer {
  @Autowired
  UserDetailsServiceImpl userDetailsService;

  @Autowired
  private AuthEntryPointJwt unauthorizedHandler;

  @Autowired
  private UserInterceptor userInterceptor;

  @Autowired
  private AdminInterceptor adminInterceptor;

  @Autowired
  private LogoutInterceptor logoutInterceptor;

  @Bean
  public AuthTokenFilter authenticationJwTokenFilter() {
    return new AuthTokenFilter();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());

    return authProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable()
        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authorizeRequests().antMatchers("/api/auth/**").permitAll()
        .antMatchers("/api/test/**").permitAll()
        .anyRequest().authenticated();

        http.authenticationProvider(authenticationProvider());        
        http.addFilterBefore(authenticationJwTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
      }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    WebMvcConfigurer.super.addInterceptors(registry);
    registry.addInterceptor(userInterceptor).addPathPatterns(
      "/api/surveys", 
      "/api/questions/survey/question",
      "/api/surveys/**",
      "/api/questions/**"
     );
    registry.addInterceptor(adminInterceptor).addPathPatterns( 
      "/api/add-question",
      "/api/edit-question/**",
      "/api/delete-question/**",
      "/api/add-survey",
      "/api/edit-survey/**",
      "/api/delete-survey/**"
    );
    registry.addInterceptor(logoutInterceptor).addPathPatterns(
      "/api/auth/signout"
    );
  }
}