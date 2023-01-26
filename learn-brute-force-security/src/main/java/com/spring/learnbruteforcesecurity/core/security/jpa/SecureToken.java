package com.spring.learnbruteforcesecurity.core.security.jpa;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import com.spring.learnbruteforcesecurity.core.user.jpa.data.UserEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "secureTokens")
public class SecureToken {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String token;

  @CreationTimestamp
  @Column(updatable = false)
  private Timestamp timestamp;

  @Column(updatable = false)
  @Basic(optional = false)
  private LocalDateTime expireAt;

  @ManyToOne
  @JoinColumn(name = "costumer_id", referencedColumnName = "id")
  private UserEntity user;

  @Transient
  private boolean isExpired;

  public boolean isExpired() {
    return getExpireAt().isBefore(LocalDateTime.now()); // this is generic implementation, you can always make it timezone specific
  }
}
