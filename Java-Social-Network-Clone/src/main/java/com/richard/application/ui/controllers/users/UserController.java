/**
 * Nickname: Richard
 * Email: richardvu.work@gmail.com
 * Skype: richardvu.work@gmail.com
 * Phone: (+84) 0935710974 - (+84) 0935810974
 * Country: Viet Nam
 * Year: 2021
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 */
package com.richard.application.ui.controllers.users;

import java.time.ZonedDateTime;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.richard.application.services.UserService;
import com.richard.application.shared.dtos.UserDto;
import com.richard.application.ui.models.Testing;
import com.richard.application.ui.models.requests.UserRegisterReq;
import com.richard.application.ui.models.responses.Notification;
import com.richard.application.ui.models.responses.UserRest;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author Richard
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = { "http://localhost:8012", "http://localhost:8084" })
public class UserController {

  /** userService: */
  @Autowired
  UserService userService;

  /**
   * getChecking UserController
   * 
   * @return
   */
  @GetMapping("/checking")
  public String getChecking() {
    return "Working...";
  }

  /**
   * createUser UserController
   * 
   * @param userRegister
   * @return
   */
  @PostMapping("/create")
  public Notification createUser(@RequestBody @Validated UserRegisterReq userRegister) {
    return userService.createUser(new ModelMapper().map(userRegister, UserDto.class));
  }

  /**
   * UserController createUsers
   *
   * @param userRegister
   * @return
   */
  @PostMapping("/v2Checking")
  public Notification createUsers(@RequestBody @Valid Testing test) {
    return new Notification(ZonedDateTime.now(), getChecking());
  }

  /**
   * emailVerification UserController
   * 
   * @param token
   * @return
   */
  @GetMapping("/email-verification")
  public Notification emailVerification(@RequestParam(value = "token") String token) {
    return userService.emailVerification(token);
  }

  /**
   * getListUsers UserController
   * 
   * @param page
   * @param limit
   * @return
   */
  @GetMapping
  @ApiImplicitParams({ @ApiImplicitParam(name = "authorization", value = "${authorizationHeader}", paramType = "header") })
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public List<UserRest> getListUsers(@RequestParam(value = "page", defaultValue = "0") int page,
  @RequestParam(value = "limit", defaultValue = "2") int limit) {
    return userService.getListUsers(page, limit);
  }

  @ApiOperation(value = "The Get User Details Web Service Endpoint", notes = "${userController.GetUser.ApiOperation.Notes}")
  @ApiImplicitParams({ @ApiImplicitParam(name = "authorization", value = "${userController.authorizationHeader.description}", paramType = "header") })
  @GetMapping(path = "/{id}")
  public UserRest getUser(@PathVariable String id) {
    UserRest returnValue = new UserRest();

    UserDto userDto = userService.getUserByUserId(id);
    ModelMapper modelMapper = new ModelMapper();
    returnValue = modelMapper.map(userDto, UserRest.class);

    return returnValue;
  }
}