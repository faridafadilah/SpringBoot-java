package com.spring.learnbruteforcesecurity.core.security.token;

import com.spring.learnbruteforcesecurity.core.security.jpa.SecureToken;

public interface SecureTokenService {
  SecureToken createSecureToken();
  void saveSecureToken(final SecureToken token);
  SecureToken findByToken(final String token);
  void removeToken(final SecureToken token);
  void removeTokenByToken(final String token);
}
