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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Richard
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "addresses")
public class AddressEntity implements Serializable {

  /** serialVersionUID: */
  private static final long serialVersionUID = 1L;

  /** id: */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  /** addressId: */
  @Column(length = 30, nullable = false)
  private String addressId;

  /** city: */
  @Column(length = 15, nullable = false)
  private String city;

  /** country: */
  @Column(length = 15, nullable = false)
  private String country;

  /** streetName: */
  @Column(length = 100, nullable = false)
  private String streetName;

  /** postalCode: */
  @Column(length = 7, nullable = false)
  private String postalCode;

  /** type: */
  @Column(length = 10, nullable = false)
  private String type;

  /** userDetails: */
  @ManyToOne
  @JoinColumn(name = "users_id")
  private UserEntity userDetails;

}