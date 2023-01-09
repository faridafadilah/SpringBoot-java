package com.rest.question.survey.restapisurveyquestion.Interceptor;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.rest.question.survey.restapisurveyquestion.UnauthorizedException;
import com.rest.question.survey.restapisurveyquestion.constant.ErrorCode;
import com.rest.question.survey.restapisurveyquestion.models.User;
import com.rest.question.survey.restapisurveyquestion.repository.UserRepository;
import com.rest.question.survey.restapisurveyquestion.security.jwt.JwtUtils;

@Component
public class UserInterceptor implements HandlerInterceptor {
  @Autowired
  private UserRepository userRepository;
  private JwtUtils jwtUtils = new JwtUtils();

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
  //   String url = request.getRequestURI();

  //   String headerAuth = request.getHeader("Authorization");
  //   if(headerAuth == null || !headerAuth.contains("Bearer")) {
  //     throw (new UnauthorizedException(ErrorCode.UNAUTHORIZED));
  //   }

  //   String token = headerAuth.substring(7);
  //   String id = jwtUtils.getUserNameFromJwtToken(token);

  //   Optional<User> userOptional = userRepository.findById(id);
  //   if(!userOptional.isPresent()) {
  //     throw (new UnauthorizedException(ErrorCode.USER_NOT_FOUND));
  //   }
  //   return HandlerInterceptor.super.preHandle(request, response, handler);
  // }
  System.out.println("Log1Interceptor-preHandle");
  System.out.println("url: " + request.getRequestURL().toString());
  return true;
  }

  @Override
  public void postHandle(HttpServletRequest request,  HttpServletResponse response, Object handler,  ModelAndView modelAndView) throws Exception {
    System.out.println("Log1- postHandle");
  }

  @Override
  public void afterCompletion(
    HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) 
    throws Exception {
      System.out.println("Log1-afterCompletion");
  }
}