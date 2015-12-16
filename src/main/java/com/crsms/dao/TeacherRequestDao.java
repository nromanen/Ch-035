package com.crsms.dao;

import java.util.List;

import com.crsms.domain.TeacherRequest;

public interface TeacherRequestDao extends BaseDao<TeacherRequest> {

	Long getRequestsHistoryCount();
	
	List<TeacherRequest> getRequestsHistory();
	
	TeacherRequest getTeacherRequestByUserEmail(String email);
}
