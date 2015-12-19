package com.crsms.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.domain.TeacherRequest;
import com.crsms.domain.User;

@Transactional
public interface TeacherRequestService extends BaseService<TeacherRequest> {
	
	TeacherRequest createRequest(User user);
	
	TeacherRequest approve(TeacherRequest request);
	
	TeacherRequest approve(Long requestId);
	
	TeacherRequest decline(TeacherRequest request);
	
	TeacherRequest decline(Long requestId);
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	Long getRequestsHistoryCount();
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	List<TeacherRequest> getRequestsHistory();
	@PreAuthorize("hasAnyRole('ADMIN')")
	TeacherRequest setApprovedRequest(Long requestId, Boolean approve);

}
