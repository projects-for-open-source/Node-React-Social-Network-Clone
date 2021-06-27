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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Richard
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "password_reset_tokens")
public class PasswordResetTokenEntity implements Serializable {

  /** serialVersionUID: */
  private static final long serialVersionUID = 1L;

  /** id: */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  /** token: */
  private String token;

  /** userDetails: */
  @OneToOne()
  @JoinColumn(name = "users_id")
  private UserEntity userDetails;

}
