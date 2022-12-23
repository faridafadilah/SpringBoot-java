package com.server.jwt.jwtsecurity.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
  @GetMapping("/all")
  public String allAccess() {
    return "Public Content";
  }

  @GetMapping("/user")
  @PreAuthorize("hashRole('USER') or hasRole('SUPER_ADMIN') or hasRole('ADMIN')")
  public String userAccess() {
    return "User Content";
  }

  @GetMapping("/super")
  @PreAuthorize("hasRole('SUPER_ADMIN')")
  public String superAdminAccess() {
    return "Super Admin Board";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    return "Admin Board";
  }
}
