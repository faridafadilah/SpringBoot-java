package com.farida.springboot.restapi.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetails, Long>{
  List<UserDetails> findByRole(String role);
}
