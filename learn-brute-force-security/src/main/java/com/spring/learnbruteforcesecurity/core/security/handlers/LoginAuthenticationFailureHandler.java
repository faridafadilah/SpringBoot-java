package com.spring.learnbruteforcesecurity.core.security.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class LoginAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
  public static final String LAST_USERNAME_KEY = "LAST_USERNAME";

  public void onAuthenticationFailure(HttpServletRequest request,
      HttpServletResponse response, AuthenticationException exception)
      throws IOException, ServletException {
    request.getSession().setAttribute(LAST_USERNAME_KEY, request.getParameter("username"));
    super.onAuthenticationFailure(request, response, exception);
  }
}
