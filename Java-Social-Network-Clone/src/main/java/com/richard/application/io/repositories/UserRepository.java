/**
 * Nickname: Richard
 * Email: richardvu.work@gmail.com
 * Skype: richardvu.work@gmail.com
 * Phone: (+84) 0935710974 - (+84) 0935810974
 * Country: Viet Nam
 * Year: 2021
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 */
package com.richard.application.io.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.richard.application.io.entities.UserEntity;

/**
 * @author admin
 *
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	
	/**
	 * @param email
	 * @return
	 */
	UserEntity findByEmail(String email);
	
	/**
	 * @param userId
	 * @return
	 */
	UserEntity findByUserId(String userId);
	
	/**
	 * @param token
	 * @return
	 */
	UserEntity findUserByEmailVerificationToken(String token);
	
	/**
	 * findAllUsersWithConfirmedEmailAddress UserRepository
	 * 
	 * @param pageableRequest
	 * @return
	 */
	@Query(value = "select * from users u where u.EMAIL_VERIFICATION_STATUS = 'true'", countQuery = "select count(*) from users u where u.EMAIL_VERIFICATION_STATUS = 'true'", nativeQuery = true)
	Page<UserEntity> findAllUsersWithConfirmedEmailAddress(Pageable pageableRequest);
	
	/**
	 * findUserByFirstName UserRepository
	 * 
	 * @param firstName
	 * @return
	 */
	@Query(value = "select * from users u where u.first_name = ?1", nativeQuery = true)
	List<UserEntity> findUserByFirstName(String firstName);
	
	/**
	 * findUserByLastName UserRepository
	 * 
	 * @param lastName
	 * @return
	 */
	@Query(value = "select * from users u where u.last_name = :lastName", nativeQuery = true)
	List<UserEntity> findUserByLastName(@Param("lastName") String lastName);
	
	/**
	 * findUsersByKeyword UserRepository
	 * 
	 * @param keyword
	 * @return
	 */
	@Query(value = "select * from users u where first_name LIKE %:keyword% or last_name LIKE %:keyword%", nativeQuery = true)
	List<UserEntity> findUsersByKeyword(@Param("keyword") String keyword);
	
	/**
	 * findUserFirstNameAndLastNameByKeyword UserRepository
	 * 
	 * @param keyword
	 * @return
	 */
	@Query(value = "select u.first_name, u.last_name from users u where u.first_name LIKE %:keyword% or u.last_name LIKE %:keyword%", nativeQuery = true)
	List<Object[]> findUserFirstNameAndLastNameByKeyword(@Param("keyword") String keyword);
	
	/**
	 * updateUserEmailVerificationStatus UserRepository
	 * 
	 * @param emailVerificationStatus
	 * @param userId
	 */
	@Transactional
	@Modifying
	@Query(value = "update users u set u.EMAIL_VERIFICATION_STATUS=:emailVerificationStatus where u.user_id=:userId", nativeQuery = true)
	void updateUserEmailVerificationStatus(@Param("emailVerificationStatus") boolean emailVerificationStatus,
	    @Param("userId") String userId);
	
	/**
	 * findUserEntityByUserId UserRepository
	 * 
	 * @param userId
	 * @return
	 */
	@Query("select user from users user where user.userId =:userId")
	UserEntity findUserEntityByUserId(@Param("userId") String userId);
	
	/**
	 * getUserEntityFullNameById UserRepository
	 * 
	 * @param userId
	 * @return
	 */
	@Query("select user.firstName, user.lastName from users user where user.userId =:userId")
	List<Object[]> getUserEntityFullNameById(@Param("userId") String userId);
	
	/**
	 * updateUserEntityEmailVerificationStatus UserRepository
	 * 
	 * @param emailVerificationStatus
	 * @param userId
	 */
	@Modifying
	@Transactional
	@Query("UPDATE users u set u.emailVerificationStatus =:emailVerificationStatus where u.userId = :userId")
	void updateUserEntityEmailVerificationStatus(@Param("emailVerificationStatus") boolean emailVerificationStatus,
	    @Param("userId") String userId);
	
}