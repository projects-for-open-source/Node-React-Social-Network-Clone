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

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.richard.application.io.entities.AddressEntity;
import com.richard.application.io.entities.UserEntity;

/**
 * @author admin
 *
 */
@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
	
	/**
	 * @param userEntity
	 * @return
	 */
	List<AddressEntity> findAllByUserDetails(UserEntity userEntity);
	
	/**
	 * @param addressId
	 * @return
	 */
	AddressEntity findByAddressId(String addressId);
	
}
