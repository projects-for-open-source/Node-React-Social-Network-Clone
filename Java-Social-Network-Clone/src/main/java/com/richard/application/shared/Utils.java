/**
 * Nickname: Richard
 * Email: richardvu.work@gmail.com
 * Skype: richardvu.work@gmail.com
 * Phone: (+84) 0935710974 - (+84) 0935810974
 * Country: Viet Nam
 * Year: 2021
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 */
package com.richard.application.shared;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.richard.application.securities.SecurityConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Richard
 */
@Service
public class Utils {

  /** RANDOM: */
  private final Random RANDOM = new SecureRandom();

  /** ALPHABET: */
  private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

  /**
   * generateUserId Utils
   * 
   * @param length
   * @return
   */
  public String generateUserId(int length) {
    return generateRandomString(length);
  }

  /**
   * generateAddressId Utils
   * 
   * @param length
   * @return
   */
  public String generateAddressId(int length) {
    return generateRandomString(length);
  }

  /**
   * generateRandomString Utils
   * 
   * @param length
   * @return
   */
  private String generateRandomString(int length) {
    StringBuilder returnValue = new StringBuilder(length);

    for (int i = 0; i < length; i++){
      returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
    }

    return new String(returnValue);
  }

  /**
   * @param userId
   * @return
   */
  public String generateEmailVerificationToken(String userId) {
    System.out.println(SignatureAlgorithm.HS512);
    String token = Jwts.builder().setSubject(userId).setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
    .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret()).compact();
    return token;
  }

}