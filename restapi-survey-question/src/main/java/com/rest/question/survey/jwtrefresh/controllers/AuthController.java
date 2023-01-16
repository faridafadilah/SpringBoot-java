package com.rest.question.survey.restapisurveyquestion.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.question.survey.restapisurveyquestion.base.BaseController;
import com.rest.question.survey.restapisurveyquestion.exception.TokenRefreshException;
import com.rest.question.survey.restapisurveyquestion.models.ERole;
import com.rest.question.survey.restapisurveyquestion.models.RefreshToken;
import com.rest.question.survey.restapisurveyquestion.models.Role;
import com.rest.question.survey.restapisurveyquestion.models.User;
import com.rest.question.survey.restapisurveyquestion.payload.request.LoginRequest;
import com.rest.question.survey.restapisurveyquestion.payload.request.SignUpRequest;
import com.rest.question.survey.restapisurveyquestion.payload.request.TokenRefreshRequest;
import com.rest.question.survey.restapisurveyquestion.payload.response.JwtResponse;
import com.rest.question.survey.restapisurveyquestion.payload.response.MessageResponse;
import com.rest.question.survey.restapisurveyquestion.payload.response.TokenRefreshResponse;
import com.rest.question.survey.restapisurveyquestion.repository.RoleRepository;
import com.rest.question.survey.restapisurveyquestion.repository.UserRepository;
import com.rest.question.survey.restapisurveyquestion.security.jwt.JwtUtils;
import com.rest.question.survey.restapisurveyquestion.security.services.RefreshTokenService;
import com.rest.question.survey.restapisurveyquestion.security.services.UserDetailslmpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController extends BaseController{
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  RefreshTokenService refreshTokenService;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailslmpl userDetails = (UserDetailslmpl) authentication.getPrincipal();

    String jwt = jwtUtils.generateJwtToken(userDetails);
    System.out.println("jwt"+jwt);

    List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
        .collect(Collectors.toList());

    RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId(), jwt);

    return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(),
        userDetails.getUsername(), userDetails.getEmail(), roles));
  }

  // Controller SignUp
  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
    //Periksa Username atau Email
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

    // Create new user account
    User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  @PostMapping("/refreshtoken")
  public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
    String requestRefreshToken = request.getRefreshToken();
    System.out.println("token sebelumnya"+requestRefreshToken);

    return refreshTokenService.findByToken(requestRefreshToken)
        .map(refreshTokenService::verifyExpiration)
        .map(RefreshToken::getUser)
        .map(user -> {
          String token = jwtUtils.generateTokenFromUsername(user.getUsername());
          System.out.println("token"+token);
          refreshTokenService.editRefreshToken(token, requestRefreshToken);
          return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
        })
        .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
            "Refresh token is not in database!"));
  }

  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
    //Insert Table logout true
    //created_at dan expired_at
    UserDetailslmpl userDetails = (UserDetailslmpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Long userId = userDetails.getId();
    String username = userDetails.getUsername();
    refreshTokenService.createUserLogout(username, userId);
    refreshTokenService.deleteByUserId(userId);
    return ResponseEntity.ok(new MessageResponse("Log Out Successfull!"));
  }

}