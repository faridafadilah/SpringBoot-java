package com.springmvc.springrememberme.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.springmvc.springrememberme.web.interceptor.LoggerInterceptor;
import com.springmvc.springrememberme.web.interceptor.SessionTimerInterceptor;
import com.springmvc.springrememberme.web.interceptor.UserInterceptor;

@EnableWebMvc
@Configuration
@ComponentScan("springmvc.springrememberme.web.controller")
public class MvcConfig implements WebMvcConfigurer {
  public MvcConfig() {
    super();
  }

  @Override
  public void addViewControllers(final ViewControllerRegistry registry) {
    registry.addViewController("/anonymous.html");

    registry.addViewController("/login.html");
    registry.addViewController("/homepage.html");
    registry.addViewController("/console.html");
    registry.addViewController("/csrfHome.html");
  }

  @Bean
  public ViewResolver viewResolver() {
    final InternalResourceViewResolver bean = new InternalResourceViewResolver();

    bean.setViewClass(JstlView.class);
    bean.setPrefix("/WEB-INF/view/");
    bean.setSuffix(".jsp");

    return bean;
  }

  @Override
  public void addInterceptors(final InterceptorRegistry registry) {
    registry.addInterceptor(new LoggerInterceptor());
    registry.addInterceptor(new UserInterceptor());
    registry.addInterceptor(new SessionTimerInterceptor());
  }
}
