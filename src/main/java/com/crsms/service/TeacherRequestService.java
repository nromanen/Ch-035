package com.crsms.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.crsms.domain.TeacherRequest;
import com.crsms.domain.User;

public interface TeacherRequestService extends BaseService<TeacherRequest> {
	
	public TeacherRequest createRequest(User user);
	
	public TeacherRequest approve(TeacherRequest request);
	
	public TeacherRequest approve(Long requestId);
	
	public TeacherRequest decline(TeacherRequest request);
	
	public TeacherRequest decline(Long requestId);
	
	// set request status for change role (accept/decline)
	@PreAuthorize("hasAnyRole('ADMIN')")
	public TeacherRequest setRequestStatus(TeacherRequest request);
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	User getUserByRequestId(Long requestId);
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	Long getRequestsCount();

}
