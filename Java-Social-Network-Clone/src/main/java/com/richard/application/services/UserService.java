/**
 * Nickname: Richard
 * Email: richardvu.work@gmail.com
 * Skype: richardvu.work@gmail.com
 * Phone: (+84) 0935710974 - (+84) 0935810974
 * Country: Viet Nam
 * Year: 2021
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 */
package com.richard.application.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.richard.application.shared.dtos.UserDto;
import com.richard.application.ui.models.responses.Notification;
import com.richard.application.ui.models.responses.UserRest;

/**
 * @author Richard
 */
public interface UserService extends UserDetailsService {

  /**
   * @param userDto
   * @return
   */
  Notification createUser(UserDto userDto);

  /**
   * @param token
   * @return
   */
  Notification emailVerification(String token);

  /**
   * @param userName
   * @return
   */
  UserDto getUser(String userName);

  /**
   * @param page
   * @param limit
   * @return
   */
  List<UserRest> getListUsers(int page, int limit);

  /**
   * @param email
   * @return
   */
  UserDto getUserByUserId(String id);

}
