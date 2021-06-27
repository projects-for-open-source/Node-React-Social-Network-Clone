/**
 * Nickname: Richard
 * Email: richardvu.work@gmail.com
 * Skype: richardvu.work@gmail.com
 * Phone: (+84) 0935710974 - (+84) 0935810974
 * Country: Viet Nam
 * Year: 2021
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 */
package com.richard.application;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.richard.application.io.entities.AuthorityEntity;
import com.richard.application.io.entities.RoleEntity;
import com.richard.application.io.entities.UserEntity;
import com.richard.application.io.repositories.AuthorityRepository;
import com.richard.application.io.repositories.RoleRepository;
import com.richard.application.io.repositories.UserRepository;
import com.richard.application.shared.Roles;
import com.richard.application.shared.Utils;

/**
 * @author admin
 */
@Component
public class InitialUsersSetup {

  /** authorityRepository: */
  @Autowired
  AuthorityRepository authorityRepository;

  /** roleRepository: */
  @Autowired
  RoleRepository roleRepository;

  /** bCryptPasswordEncoder: */
  @Autowired
  BCryptPasswordEncoder bCryptPasswordEncoder;

  /** utils: */
  @Autowired
  Utils utils;

  /** userRepository: */
  @Autowired
  UserRepository userRepository;

  /**
   * createAuthority InitialUsersSetup
   * 
   * @param name
   * @return
   */
  @Transactional
  private AuthorityEntity createAuthority(String name) {

    AuthorityEntity authority = authorityRepository.findByName(name);
    if (authority == null){
      authority = new AuthorityEntity(name);
      authorityRepository.save(authority);
    }
    return authority;
  }

  /**
   * createRole InitialUsersSetup
   * 
   * @param name
   * @param authorities
   * @return
   */
  @Transactional
  private RoleEntity createRole(String name, Collection<AuthorityEntity> authorities) {

    RoleEntity role = roleRepository.findByName(name);
    if (role == null){
      role = new RoleEntity(name);
      role.setAuthorities(authorities);
      roleRepository.save(role);
    }
    return role;
  }

  /**
   * onApplicationEvent InitialUsersSetup
   * 
   * @param event
   */
  @EventListener
  @Transactional
  public void onApplicationEvent(ApplicationReadyEvent event) {
    System.out.println("From Application ready event...");

    AuthorityEntity readAuthority = createAuthority("READ_AUTHORITY");
    AuthorityEntity writeAuthority = createAuthority("WRITE_AUTHORITY");
    AuthorityEntity deleteAuthority = createAuthority("DELETE_AUTHORITY");

    createRole(Roles.ROLE_USER.name(), Arrays.asList(readAuthority, writeAuthority));
    RoleEntity roleAdmin = createRole(Roles.ROLE_ADMIN.name(), Arrays.asList(readAuthority, writeAuthority, deleteAuthority));

    if (roleAdmin == null){
      return;
    }

    UserEntity adminUser = new UserEntity();
    adminUser.setFirstName("watabe");
    adminUser.setLastName("watabe");
    adminUser.setEmail("watabe@cizlabo.com");
    adminUser.setEmailVerificationStatus(true);
    adminUser.setUserId(utils.generateUserId(30));
    adminUser.setEncryptedPassword(bCryptPasswordEncoder.encode("abc123!"));
    adminUser.setRoles(Arrays.asList(roleAdmin));
    adminUser.setCreateDate(ZonedDateTime.now());
    adminUser.setActive(true);
    adminUser.setJoiningDate(ZonedDateTime.now());

    UserEntity storedUserDetails = userRepository.findByEmail("watabe");
    if (storedUserDetails == null){
      userRepository.save(adminUser);
    }

  }

}