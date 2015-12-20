package com.crsms.service;

import java.util.List;
import java.util.Set;

import com.crsms.domain.Group;
import com.crsms.domain.User;
import com.crsms.dto.UserIdFNameLNameEmailDto;

public interface GroupService extends BaseService<Group> {
	
	void subscribe(Long groupId, User user);

	void subscribe(Long groupId, String email);

	void unsubscribe(Long groupId, String email);
	
	void unsubscribe(Long groupId, Long studentId);
	
	List<Group> getAllByCourseId(Long courseId);

	void save(Long courseId, Group group);

	void deleteById(Long groupId);

	void update(Long courseId, Group group);

	List<UserIdFNameLNameEmailDto> getStudentsFromGroup(Long groupId);
	
	/**
	 * Takes emails of students, who should be subscribed to the group.
	 * Returns emails of students, who were subscribed earlier or are subscribed to
	 * other group of the same course.
	 * User will be subscribed if email exists in DB or will be created, if not.
	 * @param courseId course id
	 * @param groupId group id
	 * @param emails emails of student's, who should be subscribed to the group
	 * @return emails of students, which weren't subscribed
	 */
	List<String> addStudents(Long courseId, Long groupId, Set<String> emails);
	
	/**
	 * Takes collection of emails and course id. Returns subcollection of user's emails, 
	 * which are subscribed to any group, which is assigned to the course.
	 * @param courseId course id
	 * @param emails emails to check
	 * @return list of student's emails who are subscribed to the course.
	 */
	List<String> selectAlreadySubscribedUsers(Long courseId, Set<String> emails);

	List<UserIdFNameLNameEmailDto> searchStudents(String textToSearch);
	
	List<UserIdFNameLNameEmailDto> getStudentsFromGroupPaginated(Long groupId,
			String sortBy, String sortOrder, Integer page, Integer limit);

	Long getStudentsCountFromGroup(Long groupId);
}
