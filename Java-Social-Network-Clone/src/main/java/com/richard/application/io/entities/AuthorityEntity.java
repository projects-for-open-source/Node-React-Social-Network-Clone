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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@RequiredArgsConstructor
@Entity(name = "authorities")
public class AuthorityEntity implements Serializable {

  /** serialVersionUID: */
  private static final long serialVersionUID = -5828101164006114538L;

  /** id: */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  /** name: */
  @NonNull
  @Column(nullable = false, length = 20)
  private String name;

  /** roles: */
  @ManyToMany(mappedBy = "authorities")
  private Collection<RoleEntity> roles;

}