package com.crsms.dao;

import java.util.List;

import com.crsms.domain.User;

/**
 * 
 * @author Roman Romaniuk
 *
 */

public interface UserDao extends BaseDao<User> {
	
	User getUserByEmail(String email);

	long getRowsCount();
	
	List<User> getPagingUsers(int startPosition, int itemsPerPage,
								String sortingField, String order);
}