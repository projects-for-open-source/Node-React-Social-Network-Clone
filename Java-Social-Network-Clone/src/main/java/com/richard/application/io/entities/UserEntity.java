/**
 * Nickname: Richard
 * Email: richardvu.work@gmail.com
 * Skype: richardvu.work@gmail.com
 * Phone: (+84) 0935710974 - (+84) 0935810974
 * Country: Viet Nam
 * Year: 2021
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 */
package com.richard.application.io.entities;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author admin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class UserEntity implements Serializable {

  /** serialVersionUID: */
  private static final long serialVersionUID = 1L;

  /** id: */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  /** userId: */
  @Column(nullable = false)
  private String userId;

  /** firstName: */
  @Column(nullable = false, length = 50)
  private String firstName;

  /** lastName: */
  @Column(nullable = false, length = 50)
  private String lastName;

  /**
   * email - UserEntity.java - String:
   */
  @Column(nullable = false, length = 120, unique = true)
  private String email;

  /** encryptedPassword: */
  @Column(nullable = false)
  private String encryptedPassword;

  /** emailVerificationToken: */
  private String emailVerificationToken;

  /** emailVerificationStatus: */
  @Column(nullable = false)
  private Boolean emailVerificationStatus = false;

  /** addresses: */
  @OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL)
  private List<AddressEntity> addresses;

  /**
   * roles - UserEntity.java - Collection<RoleEntity>:
   */
  @ManyToMany(cascade = { CascadeType.PERSIST }, fetch = FetchType.EAGER)
  @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
  private Collection<RoleEntity> roles;

  /**
   * createDate - UserEntity.java - ZonedDateTime:
   */
  private ZonedDateTime createDate;

  /** updateTime: */
  private ZonedDateTime updateTime;

  /** userImage: */
  private String userImage;

  /** active: */
  private boolean active;

  /** joiningDate: */
  private ZonedDateTime joiningDate;

}
