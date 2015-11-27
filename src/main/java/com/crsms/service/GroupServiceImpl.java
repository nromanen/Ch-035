package com.crsms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.GroupDao;
import com.crsms.domain.Group;
import com.crsms.domain.User;
import com.crsms.dto.UserIdAndEmailDto;

@Service("groupService")
@Transactional
public class GroupServiceImpl extends BaseServiceImpl<Group> implements GroupService {
	@Autowired
	private GroupDao groupDao;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private UserService	userService;
	
	@Override
	public void subscribe(Long groupId, String email) {
		Group group = groupDao.getById(groupId);
		User user = userService.getUserByEmail(email);
		group.addUser(user);
		groupDao.update(group);
	}

	@Override
	public void unsubscribe(Long groupId, String email) {
		Group group = groupDao.getById(groupId);
		User user = userService.getUserByEmail(email);
		group.deleteUser(user);
		groupDao.update(group);
	}

	@Override
	public List<Group> getAllByCourseId(Long courseId) {
		return groupDao.getAllByCourseId(courseId);
	}

	@Override
	public void save(Long courseId, Group group) {
		group.setCourse(courseService.getById(courseId));
		groupDao.save(group);
	}

	@Override
	public void deleteById(Long groupId) {
		groupDao.deleteById(groupId);
	}

	@Override
	public void update(Long courseId, Group group) {
		group.setCourse(courseService.getById(courseId));
		groupDao.update(group);
	}

	@Override
	public List<UserIdAndEmailDto> getStudentsFromGroup(Long groupId) {
		return groupDao.getStudentsFromGroup(groupId);
	}

}
