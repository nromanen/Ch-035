package com.crsms.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @author Valerii Motresku
 * @author maftey
 *
 */

@Entity
@Table(name = "course")
@NamedQueries({
	@NamedQuery(name = Course.GET_BY_NAME,
				query = "FROM Course c WHERE c.name=:name"),
	@NamedQuery(name = Course.GET_BY_USER_ID,
				query = "select c from User u join u.courses c where u.id = :userId"),
	@NamedQuery(name = Course.GET_BY_USER_EMAIL,
				query = "select c from User u join u.courses c where u.email = :email")
})
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crsms_gen")
	@SequenceGenerator(name = "crsms_gen", sequenceName = "course_id_seq", allocationSize = 1)
	private Long id;
	
	@Column(nullable = false)
	@NotNull
	@Size(min = 1, max = MAX_NAME_LENGTH)
	private String name;
	
	@Column(nullable = false)
	private String description;
	
//	@OneToOne(fetch = FetchType.LAZY)
//	@PrimaryKeyJoinColumn 
//	@Cascade({CascadeType.ALL})
//	private User owner;
//	
//	@Column(nullable = false)
//	private CourseLanguage language = CourseLanguage.EN;
	
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private DateTime startDate;
	
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDurationAsSecondsInteger")
	private Duration duration;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Module> modules;
	
	@Column(nullable = false)
	private Boolean open = false;
	
	@ManyToOne
    @JoinColumn(name = "area_id")
	private Area area;
	
	@Column(nullable = false)
	private Boolean disable = false;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<User> users = new HashSet<User>();

	public enum CourseLanguage {
		EN, UK,
	}
	
	public enum LazyField {
		MODULES, USERS, ALL,
	}
	
	public static final String GET_BY_NAME = "course.getCourseByName";
	public static final String GET_BY_USER_ID = "course.getCourseByUserId";
	public static final String GET_BY_USER_EMAIL = "course.getCourseByUserEmail";
	
	public static final int MAX_NAME_LENGTH = 255;
	
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

	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}
	
	public int getWeekDuration() {
		if (duration != null)
			return duration.toStandardDays().getDays() / 7;
		return 0;
	}
	
	public void setWeekDuration(int weeks) {
		this.duration = new Duration(weeks * 7L * 24L * 60L * 60L * 1000L);
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean addModule(Module module) {
		return this.modules.add(module);
	}
	
	public boolean deleteModule(Module module) {
		if (this.modules.contains(module)) {
			return this.modules.remove(module);
		}
		return false;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	public boolean addUser(User user) {
		return this.users.add(user);
	}
	
	public boolean deleteUser(User user) {
		if (this.users.contains(user)) {
			return this.users.remove(user);
		}
		return false;
	}

	public Boolean getDisable() {
		return disable;
	}

	public void setDisable(Boolean disable) {
		this.disable = disable;
	}
	
}
