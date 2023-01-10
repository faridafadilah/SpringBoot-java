package com.rest.question.survey.restapisurveyquestion.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rest.question.survey.restapisurveyquestion.dto.response.ListUserResponse;
import com.rest.question.survey.restapisurveyquestion.models.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
  Optional<User> findByUsername(String username);
  Boolean existsByUsername(String username);
  Boolean existsByEmail(String email);

  @Query(value = "SELECT u.username as usernameUser, r.name as roleUser FROM users u INNER JOIN user_roles ur ON ur.user_id = u.id INNER JOIN roles r ON r.id = ur.role_id " +
    "WHERE u.username = :username AND r.name = 'ROLE_USER'", nativeQuery = true)
  List<ListUserResponse> findByUsernameAndRole(@Param("username") String username);

  @Query(value = "SELECT u.username as usernameUser, r.name as roleUser FROM users u INNER JOIN user_roles ur ON ur.user_id = u.id INNER JOIN roles r ON r.id = ur.role_id " +
  "WHERE u.username = :username AND r.name = 'ROLE_ADMIN'", nativeQuery = true)
  List<ListUserResponse> findByUserAndRoleAdmin(@Param("username") String username);
}