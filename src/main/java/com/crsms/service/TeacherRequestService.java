package com.crsms.service;

import com.crsms.domain.TeacherRequest;
import com.crsms.domain.User;

public interface TeacherRequestService extends BaseService<TeacherRequest> {
	
	public TeacherRequest createRequest(User user);
	
	public TeacherRequest approve(TeacherRequest request);
	
	public TeacherRequest approve(Long requestId);
	
	public TeacherRequest decline(TeacherRequest request);
	
	public TeacherRequest decline(Long requestId);

}
