package com.rest.question.survey.restapisurveyquestion.Interceptor;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.rest.question.survey.restapisurveyquestion.base.ResponAPI;
import com.rest.question.survey.restapisurveyquestion.constant.ErrorCode;
import com.rest.question.survey.restapisurveyquestion.dto.response.ListUserResponse;
import com.rest.question.survey.restapisurveyquestion.exception.UnauthorizedException;
import com.rest.question.survey.restapisurveyquestion.models.User;
import com.rest.question.survey.restapisurveyquestion.repository.UserRepository;
import com.rest.question.survey.restapisurveyquestion.security.jwt.JwtUtils;


@Component
public class UserInterceptor implements HandlerInterceptor {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JwtUtils jwtUtils = new JwtUtils();
  

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String signKey = "riDaSecretKey";
    String url = request.getRequestURI();
    System.out.print(url);

    String headerAuth = request.getHeader("Authorization");
    if (headerAuth == null || !headerAuth.contains("Bearer ")) {
      System.out.println("UNAUTORIZATION");
      throw (new UnauthorizedException(ErrorCode.UNAUTHORIZED));
    }

    String token = headerAuth.substring(7);
    
    String username = jwtUtils.usernameFromToken(token, signKey);
    System.out.println("username: "+ username);

    Optional<User> userOptional = userRepository.findByUsername(username);
    if (!userOptional.isPresent()) {
      System.out.println("UNAUTORIZATION");
      throw (new UnauthorizedException(ErrorCode.USER_NOT_FOUND));
    }
    User user = userOptional.get();
    if(user.getRoles().isEmpty()) {
      System.out.println("UNAUTORIZATION");
      throw (new UnauthorizedException(ErrorCode.UNAUTHORIZED));
    }

    List<ListUserResponse> responses = userRepository.findByUsernameAndRole(username);
    if(responses.isEmpty()) {
      System.out.println("UNAUTHORIZATION");
      throw (new Exception(ErrorCode.UNAUTHORIZED));
    }
    return HandlerInterceptor.super.preHandle(request, response, handler);
  }
}