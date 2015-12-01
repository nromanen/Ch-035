package com.crsms.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @author Valerii Motresku
 * @author St. Roman
 *
 */

@Entity
@Table(name = "groups")
@NamedQueries({
	@NamedQuery(name = Group.GET_ALL_BY_COURSE_ID,
			query = "select g from Group g join g.course c where c.id = :courseId order by g.id"),
	@NamedQuery(name = Group.DELETE_BY_ID,
			query = "delete Group where id = :id"),
	@NamedQuery(name = Group.GET_STUDENTS_IDS_AND_EMAILS_FROM_GROUP,
				query = "select new com.crsms.dto.UserIdAndEmailDto(u.id, u.email)"
					  + "from Group g join g.users u where g.id = :id"),
	@NamedQuery(name = Group.SELECT_ALREADY_SUBSCRIBED_USERS,
				query = "select u.email from Group g"
					 + " join g.users u join g.course c"
					 + " where c.id = :courseId and u.email in :emails")
})
public class Group {
	public static final String GET_ALL_BY_COURSE_ID = "group.getAllByCourseId";
	public static final String DELETE_BY_ID = "group.deleteById";
	public static final String GET_STUDENTS_IDS_AND_EMAILS_FROM_GROUP = 
			"group.getStudentsIdsAndEmailsFromGroup";
	public static final String SELECT_ALREADY_SUBSCRIBED_USERS =
			"group.selectAlreadySubscribedUsers";
	
	public static final int MAX_NAME_LENGTH = 100;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crsms_gen")
	@SequenceGenerator(name = "crsms_gen", sequenceName = "group_id_seq", allocationSize = 1)
	private Long id;
	
	@Column(nullable = false)
	@NotNull
	@Size(min = 1, max = MAX_NAME_LENGTH)
	private String name;
	
	@ManyToOne
	private Course course;
	
	@ManyToMany
	private Set<User> users;
	
	@Column
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private DateTime startDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}
	
	public void addUser(User user) {
		this.users.add(user);
	}
	
	public void deleteUser(User user) {
		this.users.remove(user);
	}
}
