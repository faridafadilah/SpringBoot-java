package com.spring.learnbruteforcesecurity.core.security.bruteforce;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.spring.learnbruteforcesecurity.core.user.jpa.data.FailedLogin;
import com.spring.learnbruteforcesecurity.core.user.jpa.data.UserEntity;
import com.spring.learnbruteforcesecurity.core.user.jpa.repository.UserRepository;

@Service("bruteForceProtectionService")
public class DefaultBruteForceProtectionService implements BruteForceProtectionService {
  @Value("${jdj.security.failedlogin.count}")
  private int maxFailedLogins;

  @Autowired
  UserRepository userRepository;

  @Value("${jdj.brute.force.cache.max}")
  private int cacheMaxLimit;

  private final ConcurrentHashMap<String, FailedLogin> cache;

  public DefaultBruteForceProtectionService() {
    this.cache = new ConcurrentHashMap<>(cacheMaxLimit);// set max limit force
  }

  @Override
  public void registerLoginFailure(String username) {
    UserEntity user = getUser(username);
    if (user != null && !user.isLoginDisabled()) {
      int failedCounter = user.getFailedLoginAttempts();
      if (maxFailedLogins < failedCounter + 1) {
        // disable account
        user.setLoginDisabled(true);
      } else {
        // update counter
        user.setFailedLoginAttempts(failedCounter + 1);
      }
      userRepository.save(user);
    }
  }

  @Override
  public void resetBruteForceCounter(String username) {
    UserEntity user = getUser(username);
    if(user != null) {
      user.setFailedLoginAttempts(0);
      user.setLoginDisabled(false);
      userRepository.save(user);
    }
  }

  @Override
  public boolean isBruteForceAttack(String username) {
    UserEntity user = getUser(username);
    if(user != null) {
      return user.getFailedLoginAttempts() >= maxFailedLogins ? true:false;
    }
    return false;
  }

  protected FailedLogin getFailedLogin(final String username) {
    FailedLogin failedLogin = cache.get(username.toLowerCase());

    if(failedLogin == null) {
      //setup data
      failedLogin = new FailedLogin(0, LocalDateTime.now());
      cache.put(username.toLowerCase(), failedLogin);
      if(cache.size() > cacheMaxLimit) {
        // add the logic to remve the key based by timestamp
      }
    }
    return failedLogin;
  }

  private UserEntity getUser(final String username) {
    return userRepository.findByEmail(username);
  }

  public int getMaxFailedLogins() {
    return maxFailedLogins;
  }

  public void setMaxFailedLogins(int maxFailedLogins) {
    this.maxFailedLogins = maxFailedLogins;
  }

}
