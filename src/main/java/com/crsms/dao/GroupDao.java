package com.crsms.dao;

import java.util.List;
import java.util.Set;

import com.crsms.domain.Group;
import com.crsms.dto.UserIdAndEmailDto;

public interface GroupDao extends BaseDao<Group> {

	List<Group> getAllByCourseId(Long courseId);

	void deleteById(Long groupId);

	List<UserIdAndEmailDto> getStudentsIdsAndEmailsFromGroup(Long groupId);

	List<String> selectAlreadySubscribedUsers(Long courseId, Set<String> emails);

}
