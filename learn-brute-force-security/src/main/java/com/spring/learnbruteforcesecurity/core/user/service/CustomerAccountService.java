package com.spring.learnbruteforcesecurity.core.user.service;


import com.spring.learnbruteforcesecurity.core.exception.InvalidTokenException;
import com.spring.learnbruteforcesecurity.core.exception.UnknownIdentifierException;

public interface CustomerAccountService {
  void forgottenPassword(final String userName) throws UnknownIdentifierException;
    void updatePassword(final String password, final String token) throws InvalidTokenException, UnknownIdentifierException;
    boolean loginDisabled(final String username);
}
