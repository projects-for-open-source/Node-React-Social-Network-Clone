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
import java.util.Collection;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author Richard
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "roles")
@RequiredArgsConstructor
public class RoleEntity implements Serializable {

  /** serialVersionUID: */
  private static final long serialVersionUID = 1L;

  /** id: */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  /** name: */
  @NonNull
  @Column(nullable = false, length = 20)
  private String name;

  /** users: */
  @ManyToMany(mappedBy = "roles")
  private Collection<UserEntity> users;

  /** authorities: */
  @ManyToMany(cascade = { CascadeType.PERSIST }, fetch = FetchType.EAGER)
  @JoinTable(name = "roles_authorities", joinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "authorities_id", referencedColumnName = "id"))
  private Collection<AuthorityEntity> authorities;

}
