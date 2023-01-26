package com.spring.learnbruteforcesecurity.core.user.jpa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.spring.learnbruteforcesecurity.core.user.jpa.data.Group;

@Repository
public interface UserGroupRepository extends PagingAndSortingRepository<Group, Long> {
  Group findByCode(String code);
}
