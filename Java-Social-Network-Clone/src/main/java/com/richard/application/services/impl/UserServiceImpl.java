/**
 * Nickname: Richard
 * Email: richardvu.work@gmail.com
 * Skype: richardvu.work@gmail.com
 * Phone: (+84) 0935710974 - (+84) 0935810974
 * Country: Viet Nam
 * Year: 2021
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 */
package com.richard.application.services.impl;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.richard.application.configurations.EmailSender;
import com.richard.application.exceptions.ApplicationException;
import com.richard.application.io.entities.RoleEntity;
import com.richard.application.io.entities.UserEntity;
import com.richard.application.io.repositories.AddressRepository;
import com.richard.application.io.repositories.RoleRepository;
import com.richard.application.io.repositories.UserRepository;
import com.richard.application.securities.SecurityConstants;
import com.richard.application.services.UserService;
import com.richard.application.shared.Roles;
import com.richard.application.shared.Utils;
import com.richard.application.shared.dtos.AddressDTO;
import com.richard.application.shared.dtos.UserDto;
import com.richard.application.ui.models.responses.Notification;
import com.richard.application.ui.models.responses.UserPrincipal;
import com.richard.application.ui.models.responses.UserRest;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Richard
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

  /** userRepository: */
  @Autowired
  UserRepository userRepository;

  /** roleRepository: */
  @Autowired
  RoleRepository roleRepository;

  /** bCryptPasswordEncoder: */
  @Autowired
  BCryptPasswordEncoder bCryptPasswordEncoder;

  /** utils: */
  @Autowired
  Utils utils;

  /** emailSender: */
  @Autowired
  EmailSender emailSender;

  /**
   * addressRepository - UserServiceImpl.java - AddressRepository:
   */
  @Autowired
  AddressRepository addressRepository;

  /**
   * loadUserByUsername UserServiceImpl
   * 
   * @param email
   * @return
   * @throws UsernameNotFoundException
   */
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserEntity userEntity = userRepository.findByEmail(email);

    if (userEntity == null){
      throw new UsernameNotFoundException(email);
    }

    return new UserPrincipal(userEntity);
  }

  /**
   * getUser UserServiceImpl
   * 
   * @param email
   * @return
   */
  @Override
  public UserDto getUser(String email) {
    UserEntity userEntity = userRepository.findByEmail(email);

    if (userEntity == null){
      throw new UsernameNotFoundException(email);
    }

    UserDto returnValue = new UserDto();
    BeanUtils.copyProperties(userEntity, returnValue);
    return returnValue;
  }

  /**
   * getListUsers UserServiceImpl
   * 
   * @param page
   * @param limit
   * @return
   */
  @Override
  public List<UserRest> getListUsers(int page, int limit) {
    List<UserRest> returnValue = new ArrayList<>();

    if (page > 0)
      page = page - 1;

    Pageable pageableRequest = PageRequest.of(page, limit);

    Page<UserEntity> usersPage = userRepository.findAll(pageableRequest);
    List<UserEntity> users = usersPage.getContent();

    Type listType = new TypeToken<List<UserRest>>() {
    }.getType();
    returnValue = new ModelMapper().map(users, listType);

    return returnValue;
  }

  /**
   * createUser UserServiceImpl
   * 
   * @param userDto
   * @return
   */
  @Override
  public Notification createUser(UserDto userDto) {

    if (userRepository.findByEmail(userDto.getEmail()) != null){
      throw new ApplicationException("User already exists");
    }

    for (int i = 0; i < userDto.getAddresses().size(); i++){
      AddressDTO address = userDto.getAddresses().get(i);
      address.setUserDetails(userDto);
      address.setAddressId(utils.generateAddressId(30));
      userDto.getAddresses().set(i, address);
    }

    userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
    userDto.setRoles(new HashSet<>(Arrays.asList(Roles.ROLE_USER.name())));
    userDto.setUserId(utils.generateUserId(30));
    userDto.setEmailVerificationToken(utils.generateEmailVerificationToken(userDto.getUserId()));

    /** Set Roles */
    Collection<RoleEntity> roleEntities = new HashSet<>();
    for (String role : userDto.getRoles()){
      RoleEntity roleEntity = roleRepository.findByName(role);
      if (roleEntity != null){
        roleEntities.add(roleEntity);
      }
    }

    UserEntity userEntity = new ModelMapper().map(userDto, UserEntity.class);
    userEntity.setRoles(roleEntities);
    userEntity.setCreateDate(ZonedDateTime.now());
    emailSender.sendEmail(userEntity.getEmail(), SecurityConstants.VERIFICATION_EMAIL_USER,
    "http://127.0.0.1:8012/users/email-verification?token=" + userEntity.getEmailVerificationToken());
    userRepository.save(userEntity);
    log.info("User was created", userEntity);
    return new Notification(ZonedDateTime.now(), "Please check your email please!");
  }

  /**
   * emailVerification UserServiceImpl
   * 
   * @param token
   * @return
   */
  @Override
  public Notification emailVerification(String token) {

    UserEntity userEntity = userRepository.findUserByEmailVerificationToken(token);
    if (userEntity == null){
      log.info("", userEntity);
      throw new ApplicationException("Access Denied", HttpStatus.BAD_REQUEST);
    }

    userEntity.setUpdateTime(ZonedDateTime.now());
    userEntity.setEmailVerificationToken(null);
    userEntity.setEmailVerificationStatus(true);
    userEntity.setActive(true);
    userEntity.setJoiningDate(ZonedDateTime.now());
    userRepository.save(userEntity);
    return new Notification(ZonedDateTime.now(), "Email was verified");
  }

  @Override
  public UserDto getUserByUserId(String id) {
    UserEntity userEntity = userRepository.findByUserId(id);

    if (userEntity == null)
      throw new UsernameNotFoundException(id);

    UserDto returnValue = new UserDto();
    BeanUtils.copyProperties(userEntity, returnValue);

    return returnValue;
  }

}