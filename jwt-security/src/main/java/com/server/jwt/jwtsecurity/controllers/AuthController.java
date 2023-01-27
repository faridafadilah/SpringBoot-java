package com.server.jwt.jwtsecurity.controllers;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.jwt.jwtsecurity.models.ERole;
import com.server.jwt.jwtsecurity.models.Role;
import com.server.jwt.jwtsecurity.models.User;
import com.server.jwt.jwtsecurity.payload.request.LoginRequest;
import com.server.jwt.jwtsecurity.payload.request.SignUpRequest;
import com.server.jwt.jwtsecurity.payload.response.JwtResponse;
import com.server.jwt.jwtsecurity.payload.response.MessageResponse;
import com.server.jwt.jwtsecurity.repository.RoleRepository;
import com.server.jwt.jwtsecurity.repository.UserRepository;
import com.server.jwt.jwtsecurity.security.handler.AuthenticationFailureHandler;
import com.server.jwt.jwtsecurity.security.jwt.JwtUtils;
import com.server.jwt.jwtsecurity.security.services.UserDetailslmpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	AuthenticationFailureHandler failureHandler;

	@Autowired
	JwtUtils jwtUtils;

	// Controller SignIn
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
					
			// Perbarui SecurityContext menggunakan objek Authentication
			SecurityContextHolder.getContext().setAuthentication(authentication);

			// Hasilkan jwt
			String jwt = jwtUtils.generateJwtToken(authentication);

			// Dapatkan UserDetails dari objek authentication
			UserDetailslmpl userDetails = (UserDetailslmpl) authentication.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream()
					.map(item -> item.getAuthority())
					.collect(Collectors.toList());

			// Respon berisi jwt, data{id,username,email,role}
			return ResponseEntity
					.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));

		} catch (AuthenticationException failed) {
			failureHandler.onAuthenticationFailure(loginRequest.getUsername(), request, response, failed);

			return ResponseEntity
					.status(HttpStatus.UNAUTHORIZED)
					.body(failed);
		}
	}

	// Controller SignUp
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		// Periksa Username atau Email
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
					case "super":
						Role superRole = roleRepository.findByName(ERole.ROLE_SUPER_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(superRole);
						break;
					default:
						Role userRole = roleRepository.findByName(ERole.ROLE_USER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(userRole);
				}
			});
		}
		user.setFailedLoginAttempts(3);
		user.setIp(null);
		user.setLoginBlocked(false);
		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
