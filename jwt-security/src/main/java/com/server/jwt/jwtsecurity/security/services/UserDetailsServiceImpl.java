package com.server.jwt.jwtsecurity.security.services;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.jwt.jwtsecurity.models.User;
import com.server.jwt.jwtsecurity.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  // private final int MAX_ATTEMPT = 3;

  @Autowired
  private HttpServletRequest request;

  @Override
  @Modifying
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    String ip = getClientIP();
    System.out.println("ip: " + ip);

    try {
      User user = userRepository.findByUsername(username)
          .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

      // Long id = user.getId();
      if(user.getFailedLoginAttempts() >= 3) {

        // Optional<User> dataUser = userRepository.findById(id);
        // User list = dataUser.get();
        // list.setLoginBlocked(true);
        // list.setIp(ip);
        user.setLoginBlocked(true);
        user.setIp(ip);
        userRepository.save(user);
        throw new RuntimeException("blocked");
      }

      return UserDetailslmpl.build(user);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private String getClientIP() {
    String xfHeader = request.getHeader("X-Forwarded-For");
    if (xfHeader == null) {
      return request.getRemoteAddr();
    }
    return xfHeader.split(",")[0];
  }
}
