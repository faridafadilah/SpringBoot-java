package com.rest.question.survey.restapisurveyquestion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.question.survey.restapisurveyquestion.models.ERole;
import com.rest.question.survey.restapisurveyquestion.models.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
