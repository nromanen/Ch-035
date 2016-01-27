package com.crsms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.TeacherRequestDao;
import com.crsms.dao.UserDao;
import com.crsms.domain.Role;
import com.crsms.domain.TeacherRequest;
import com.crsms.domain.User;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private TeacherRequestDao requestDao;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(final String email)
			throws UsernameNotFoundException {
		com.crsms.domain.User user = userDao.getUserByEmail(email);
		TeacherRequest request = requestDao.getTeacherRequestByUserEmail(email);
		Boolean accountNonLocked = (request == null) ? true : request.getApproved();
		if (user == null) {
			throw new UsernameNotFoundException("E-mail not found");
			
		}

		return new org.springframework.security.core.userdetails.User(
				user.getEmail(), user.getPassword(), user.getIsEnabled(), true,
				true, accountNonLocked, getGrantedAuthorities(user));
	}

	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		Role role = user.getRole();
		authorities.add(new SimpleGrantedAuthority(role.getName()));
		return authorities;
	}
}