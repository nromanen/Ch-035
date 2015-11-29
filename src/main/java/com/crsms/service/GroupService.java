package com.crsms.service;

import java.util.List;

import com.crsms.domain.Group;
import com.crsms.dto.UserIdAndEmailDto;

public interface GroupService extends BaseService<Group> {

	void subscribe(Long groupId, String email);

	void unsubscribe(Long groupId, String email);
	
	List<Group> getAllByCourseId(Long courseId);

	void save(Long courseId, Group group);

	void deleteById(Long groupId);

	void update(Long courseId, Group group);

	List<UserIdAndEmailDto> getStudentsFromGroup(Long groupId);

}
