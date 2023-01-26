package com.spring.learnbruteforcesecurity.core.user.service;


import dev.samstevens.totp.exceptions.QrGenerationException;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.learnbruteforcesecurity.core.email.context.AccountVerificationEmailContext;
import com.spring.learnbruteforcesecurity.core.email.service.EmailService;
import com.spring.learnbruteforcesecurity.core.exception.InvalidTokenException;
import com.spring.learnbruteforcesecurity.core.exception.UnknownIdentifierException;
import com.spring.learnbruteforcesecurity.core.exception.UserAlreadyExistException;
import com.spring.learnbruteforcesecurity.core.security.jpa.SecureToken;
import com.spring.learnbruteforcesecurity.core.security.mfa.MFATokenManager;
import com.spring.learnbruteforcesecurity.core.security.token.SecureTokenService;
import com.spring.learnbruteforcesecurity.core.user.jpa.data.Group;
import com.spring.learnbruteforcesecurity.core.user.jpa.data.UserEntity;
import com.spring.learnbruteforcesecurity.core.user.jpa.repository.SecureTokenRepository;
import com.spring.learnbruteforcesecurity.core.user.jpa.repository.UserGroupRepository;
import com.spring.learnbruteforcesecurity.core.user.jpa.repository.UserRepository;
import com.spring.learnbruteforcesecurity.web.data.user.MfaTokenData;
import com.spring.learnbruteforcesecurity.web.data.user.UserData;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.Objects;

@Service("userService")
public class DefaultUserService implements UserService {

  @Resource
  private UserRepository userRepository;

  @Resource
  private PasswordEncoder passwordEncoder;

  @Resource
  private EmailService emailService;

  @Resource
  private SecureTokenService secureTokenService;

  @Resource
  SecureTokenRepository secureTokenRepository;

  @Resource
  UserGroupRepository groupRepository;

  @Value("${site.base.url.https}")
  private String baseURL;

  @Resource
  private MFATokenManager mfaTokenManager;

  @Override
  public void register(UserData user) throws UserAlreadyExistException {
    if (checkIfUserExist(user.getEmail())) {
      throw new UserAlreadyExistException("User already exists for this email");
    }
    UserEntity userEntity = new UserEntity();
    BeanUtils.copyProperties(user, userEntity);
    encodePassword(user, userEntity);
    updateCustomerGroup(userEntity);
    userEntity.setSecret(mfaTokenManager.generateSecretKey());
    userRepository.save(userEntity);
    sendRegistrationConfirmationEmail(userEntity);

  }

  private void updateCustomerGroup(UserEntity userEntity) {
    Group group = groupRepository.findByCode("customer");
    userEntity.addUserGroups(group);
  }

  @Override
  public boolean checkIfUserExist(String email) {
    return userRepository.findByEmail(email) != null ? true : false;
  }

  @Override
  public void sendRegistrationConfirmationEmail(UserEntity user) {
    SecureToken secureToken = secureTokenService.createSecureToken();
    secureToken.setUser(user);
    secureTokenRepository.save(secureToken);
    AccountVerificationEmailContext emailContext = new AccountVerificationEmailContext();
    emailContext.init(user);
    emailContext.setToken(secureToken.getToken());
    emailContext.buildVerificationUrl(baseURL, secureToken.getToken());
    try {
      emailService.sendMail(emailContext);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public boolean verifyUser(String token) throws InvalidTokenException {
    SecureToken secureToken = secureTokenService.findByToken(token);
    if (Objects.isNull(secureToken) || !StringUtils.equals(token, secureToken.getToken()) || secureToken.isExpired()) {
      throw new InvalidTokenException("Token is not valid");
    }
    UserEntity user = userRepository.getOne(secureToken.getUser().getId());
    if (Objects.isNull(user)) {
      return false;
    }
    user.setAccountVerified(true);
    userRepository.save(user); // let's same user details

    // we don't need invalid password now
    secureTokenService.removeToken(secureToken);
    return true;
  }

  @Override
  public UserEntity getUserById(String id) throws UnknownIdentifierException {
    UserEntity user = userRepository.findByEmail(id);
    if (user == null || BooleanUtils.isFalse(user.isAccountVerified())) {
      // we will ignore in case account is not verified or account does not exists
      throw new UnknownIdentifierException("unable to find account or account is not active");
    }
    return user;
  }

  @Override
  public MfaTokenData mfaSetup(String email) throws UnknownIdentifierException, QrGenerationException {
    UserEntity user = userRepository.findByEmail(email);
    if (user == null) {
      // we will ignore in case account is not verified or account does not exists
      throw new UnknownIdentifierException("unable to find account or account is not active");
    }
    return new MfaTokenData(mfaTokenManager.getQRCode(user.getSecret()), user.getSecret());
  }

  private void encodePassword(UserData source, UserEntity target) {
    target.setPassword(passwordEncoder.encode(source.getPassword()));
  }

}
