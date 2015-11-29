package com.crsms.dao;

import java.util.List;

import com.crsms.domain.Group;
import com.crsms.dto.UserIdAndEmailDto;

public interface GroupDao extends BaseDao<Group> {

	List<Group> getAllByCourseId(Long courseId);

	void deleteById(Long groupId);

	List<UserIdAndEmailDto> getStudentsFromGroup(Long groupId);

}
