package com.crsms.service;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.TeacherRequestDao;
import com.crsms.domain.TeacherRequest;
import com.crsms.domain.User;

@Service
public class TeacherRequestServiceImpl extends BaseServiceImpl<TeacherRequest> implements TeacherRequestService {
	
	@Autowired
	private TeacherRequestDao teacherRequestDao;

	@Override
	@Transactional
	public TeacherRequest createRequest(User user) {
		final  TeacherRequest request = new TeacherRequest();
		
		teacherRequestDao.save(request);
		
		request.setUser(user);
		request.setRequestedDate(DateTime.now());
		teacherRequestDao.update(request);
		
		return request;
	}
	
	@Override
	@Transactional
	public TeacherRequest approve(TeacherRequest request) {
		return changeApprovedStatus(request, true);
	}
	
	@Override
	@Transactional
	public TeacherRequest approve(Long requestId) {
		return approve(getById(requestId));
	}
	
	@Override
	@Transactional
	public TeacherRequest decline(TeacherRequest request) {
		return changeApprovedStatus(request, false);
	}
	
	@Override
	@Transactional
	public TeacherRequest decline(Long requestId) {
		return decline(getById(requestId));
	}
	
	private TeacherRequest changeApprovedStatus(TeacherRequest request, boolean status) {
		request.setApproved(status);
		request.setReviewdDate(DateTime.now());
		
		teacherRequestDao.update(request);
		
		return request;
	}

}
