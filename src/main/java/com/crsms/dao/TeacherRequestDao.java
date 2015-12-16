package com.crsms.dao;

import javax.transaction.Transactional;

import com.crsms.domain.TeacherRequest;
import com.crsms.domain.User;

public interface TeacherRequestDao extends BaseDao<TeacherRequest> {
	@Transactional
	User getUserByRequestId(Long requestId);
	@Transactional
	Long getRequestsCount();
}
