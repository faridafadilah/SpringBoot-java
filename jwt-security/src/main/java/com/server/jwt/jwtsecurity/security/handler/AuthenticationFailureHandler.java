package com.server.jwt.jwtsecurity.security.handler;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.server.jwt.jwtsecurity.models.User;
import com.server.jwt.jwtsecurity.repository.UserRepository;

@Component
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
  public static final String LAST_USERNAME_KEY = "LAST_USERNAME";

  @Autowired
  UserRepository userRepository;

  @Autowired
  private HttpServletRequest request;

  public void onAuthenticationFailure(String username, HttpServletRequest request,
      HttpServletResponse response, AuthenticationException exception)
      throws IOException, ServletException {
    String ip = getClientIP();
    System.out.println("ip: " + ip);

    Optional<User> user = userRepository.findByUsername(username);
    if (!user.isPresent()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    // get Data user
    User list = user.get();
    int attempts_failed = list.getFailedLoginAttempts();

    if (attempts_failed >= 3) {
      list.setLoginBlocked(true);
      userRepository.save(list);
      throw new LockedException("Akun telah di block, silahkan hubungi admin");
    } else {
      list.setFailedLoginAttempts(attempts_failed + 1);
      list.setIp(ip);
      userRepository.save(list);
    }

    super.setDefaultFailureUrl("/login?error");
    super.onAuthenticationFailure(request, response, exception);
  }

  private String getClientIP() {
    String xfHeader = request.getHeader("X-Forwarded-For");
    if (xfHeader == null) {
      return request.getRemoteAddr();
    }
    return xfHeader.split(",")[0];
  }
}