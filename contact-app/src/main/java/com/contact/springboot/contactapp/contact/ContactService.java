package com.contact.springboot.contactapp.contact;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactService extends JpaRepository<Contact, Long>{
  public List<Contact> findByUsername(String username);
}
