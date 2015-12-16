package com.crsms.service;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.RoleDao;
import com.crsms.dao.TeacherRequestDao;
import com.crsms.dao.UserDao;
import com.crsms.domain.Role;
import com.crsms.domain.TeacherRequest;
import com.crsms.domain.User;

@Service
public class TeacherRequestServiceImpl extends BaseServiceImpl<TeacherRequest> implements TeacherRequestService {
	
	@Autowired
	private TeacherRequestDao teacherRequestDao;
	
	@Autowired 
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Override
	@Transactional
	public TeacherRequest createRequest(User user) {
		final  TeacherRequest request = new TeacherRequest();

		teacherRequestDao.save(request);
		request.setUser(user);
		request.setRequestedDate(DateTime.now());
		request.setApproved(false);
		user.setRole(getTeacherRole());
		teacherRequestDao.update(request);
		userDao.update(user);
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
	
	@Override
	public Long getRequestsHistoryCount() {
		return teacherRequestDao.getRequestsHistoryCount();
	}

	@Override
	public List<TeacherRequest> getRequestsHistory() {
		return teacherRequestDao.getRequestsHistory();
	}
	
	@Override
	public TeacherRequest setApprovedRequest(Long requestId, Boolean approve) {
		if (approve)
		return approve(getById(requestId));
		return decline(getById(requestId));
	}
	
	private TeacherRequest changeApprovedStatus(TeacherRequest request, boolean status) {
		request.setApproved(status);
//		request.setReviewdDate(DateTime.now());
		
		teacherRequestDao.update(request);
		
		return request;
	}
	
	private Role getTeacherRole() {
		List<Role> roles = roleDao.getAllRoles();
		for (Role role : roles) {
			if (role.getName().equals("ROLE_TEACHER"))
				return role;
		}
		return null;
	}
}
