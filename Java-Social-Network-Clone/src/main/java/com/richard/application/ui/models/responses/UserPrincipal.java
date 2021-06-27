/**
 * Nickname: Richard
 * Email: richardvu.work@gmail.com
 * Skype: richardvu.work@gmail.com
 * Phone: (+84) 0935710974 - (+84) 0935810974
 * Country: Viet Nam
 * Year: 2021
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 */
package com.richard.application.ui.models.responses;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.richard.application.io.entities.AuthorityEntity;
import com.richard.application.io.entities.RoleEntity;
import com.richard.application.io.entities.UserEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Richard
 */
@Getter
@Setter
public class UserPrincipal implements UserDetails {

	/** serialVersionUID: */
	private static final long serialVersionUID = -7530187709860249942L;

	/** userEntity: */
	private UserEntity userEntity;

	/** userId: */
	private String userId;

	/**
	 * @param userEntity
	 */
	public UserPrincipal(UserEntity userEntity) {
		this.userEntity = userEntity;
		this.userId = userEntity.getUserId();
	}

	/**
	 * getAuthorities UserPrincipal
	 *
	 * @return
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> authorities = new HashSet<>();
		Collection<AuthorityEntity> authorityEntities = new HashSet<>();

		// Get user Roles
		Collection<RoleEntity> roles = userEntity.getRoles();

		if (roles == null)
			return authorities;

		roles.forEach((role) -> {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
			authorityEntities.addAll(role.getAuthorities());
		});

		authorityEntities.forEach((authorityEntity) -> {
			authorities.add(new SimpleGrantedAuthority(authorityEntity.getName()));
		});

		return authorities;
	}

	/**
	 * getPassword UserPrincipal
	 *
	 * @return
	 */
	@Override
	public String getPassword() {
		return this.userEntity.getEncryptedPassword();
	}

	/**
	 * getUsername UserPrincipal
	 *
	 * @return
	 */
	@Override
	public String getUsername() {
		return this.userEntity.getEmail();
	}

	/**
	 * isAccountNonExpired UserPrincipal
	 *
	 * @return
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * isAccountNonLocked UserPrincipal
	 *
	 * @return
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * isCredentialsNonExpired UserPrincipal
	 *
	 * @return
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * isEnabled UserPrincipal
	 *
	 * @return
	 */
	@Override
	public boolean isEnabled() {
		return this.userEntity.getEmailVerificationStatus();
	}

}