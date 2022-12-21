package com.contact.springboot.contactapp.contact;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class Contact {
  public Contact() {

  }

  @Id
  @GeneratedValue
  private long id;
  String username;
  private String nohp;
  @Size(min=10,message = "Maaf minimal caracter 10")
  private String email;

  
  @Override
  public String toString() {
    return "Contact [id=" + id + ", username=" + username + ", nohp=" + nohp + ", email=" + email + "]";
  }


  public Contact(long id, String username, String nohp, String email) {
    this.id = id;
    this.username = username;
    this.nohp = nohp;
    this.email = email;
  }


  public long getId() {
    return id;
  }


  public void setId(long id) {
    this.id = id;
  }


  public String getUsername() {
    return username;
  }


  public void setUsername(String username) {
    this.username = username;
  }


  public String getNohp() {
    return nohp;
  }


  public void setNohp(String nohp) {
    this.nohp = nohp;
  }

  public String getEmail() {
    return email;
  }


  public void setEmail(String email) {
    this.email = email;
  }

}
