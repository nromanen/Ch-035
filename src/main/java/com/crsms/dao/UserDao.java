package com.crsms.dao;

import java.util.List;

import com.crsms.domain.User;
import org.joda.time.DateTime;
/**
 * 
 * @author Roman Romaniuk
 *
 */

public interface UserDao extends BaseDao<User> {
	
	User getUserByEmail(String email);

	long getRowsCount(String keyWord);
	
	List<User> getPagingUsers(int offSet, int itemsPerPage,
							String sortingField, String order, String keyWord);
	
	long getUsersToApproveCount();
	
	List<User> getUsersToApprove();

	
}
