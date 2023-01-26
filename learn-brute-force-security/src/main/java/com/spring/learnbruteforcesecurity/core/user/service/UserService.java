package com.spring.learnbruteforcesecurity.core.user.service;

import com.spring.learnbruteforcesecurity.core.exception.InvalidTokenException;
import com.spring.learnbruteforcesecurity.core.exception.UnknownIdentifierException;
import com.spring.learnbruteforcesecurity.core.exception.UserAlreadyExistException;
import com.spring.learnbruteforcesecurity.core.user.jpa.data.UserEntity;
import com.spring.learnbruteforcesecurity.web.data.user.MfaTokenData;
import com.spring.learnbruteforcesecurity.web.data.user.UserData;
import dev.samstevens.totp.exceptions.QrGenerationException;

public interface UserService {
  void register(final UserData user) throws UserAlreadyExistException;
    boolean checkIfUserExist(final String email);
    void sendRegistrationConfirmationEmail(final UserEntity user);
    boolean verifyUser(final String token) throws InvalidTokenException;
    UserEntity getUserById(final String id) throws UnknownIdentifierException;
    MfaTokenData mfaSetup(final String email) throws UnknownIdentifierException, QrGenerationException;
}
