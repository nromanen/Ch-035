package com.crsms.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author Valerii Motresku
 * @author maftey
 * @author St. Roman
 * 
 */

@Entity
@Table(name = "course")
@NamedQueries({
	@NamedQuery(name = Course.GET_BY_NAME,
				query = "from Course c where c.name=:name"),
	@NamedQuery(name = Course.GET_BY_MODULE,
				query = "SELECT course FROM Course course "
						+ "JOIN course.modules module "
						+ "WHERE module.id = :id"),
	@NamedQuery(name = Course.GET_BY_TEST,
				query = "SELECT course FROM Course course "
						+ "JOIN course.modules module "
						+ "JOIN module.tests test "
						+ "WHERE test.id = :id"),
	@NamedQuery(name = Course.GET_BY_QUESTION,
				query = "SELECT course FROM Course course "
						+ "JOIN course.modules module "
						+ "JOIN module.tests test "
						+ "JOIN test.questions question "
						+ "WHERE question.id = :id"),
	@NamedQuery(name = Course.GET_BY_ANSWER,
				query = "SELECT course FROM Course course "
						+ "JOIN course.modules module "
						+ "JOIN module.tests test "
						+ "JOIN test.questions question "
						+ "JOIN question.answers answer "
						+ "WHERE answer.id = :id"),
	@NamedQuery(name = Course.GET_BY_USER_EMAIL,
				query = "select g.course from Group g join g.users u where u.email = :email"),
	@NamedQuery(name = Course.GET_BY_OWNER_EMAIL,
				query = "select c from Course c join c.owner o"
					 + " where o.email = :email order by c.id"),
	@NamedQuery(name = Course.SEARCH,
				query = "select c from Course c where upper(c.name) like upper(:s) or "
					  + "upper(c.description) like upper(:s) order by c.name, c.description"),
	@NamedQuery(name = Course.GET_STUDENT_COURSES_AND_GROUPS_IDS,
				query = "select c.id, g.id from Group g"
					 + " join g.course c join g.users u where u.email = :email"),
	@NamedQuery(name = Course.GET_ALL_PUBLISHED,
				query = "from Course where published = true"),
	@NamedQuery(name = Course.GET_ALL_ASSOCIATED_WITH_RESOURCE,
				query = "select distinct c from Course c join c.modules cm "
						+ "join cm.resources cmr where cmr.id = :resource_id"),
	@NamedQuery(name = Course.GET_ALL_COURSE_MODULE_NAMES_PAIRS_ASSOCIATED_WITH_RESOURCE,
				query = "select new com.crsms.dto.CourseModuleNamesPairDto(c.name, cm.name) from Course c join c.modules cm "
						+ "join cm.resources cmr where cmr.id = :resource_id"),
	@NamedQuery(name = Course.GET_BY_AREA_ID,
				query = "from Course where area_id = :id order by id asc")
})
public class Course {
	public static final String GET_BY_NAME = "course.getCourseByName";
	public static final String GET_BY_USER_EMAIL = "course.getCourseByUserEmail";
	public static final String GET_BY_OWNER_EMAIL = "course.getCourseByOwnerEmail";
	public static final String GET_BY_MODULE = "course.getByModule";
	public static final String GET_BY_TEST = "course.getByTest";
	public static final String GET_BY_QUESTION = "course.getByQuestion";
	public static final String GET_BY_ANSWER = "course.getByAnswer";
	public static final String SEARCH = "course.search";
	public static final String GET_STUDENT_COURSES_AND_GROUPS_IDS = 
												"course.getStudentCoursesAndGroupsIds";
	public static final String GET_ALL_PUBLISHED = "course.getAllPublished";
	public static final String GET_ALL_ASSOCIATED_WITH_RESOURCE = "course.getAllAssociatedWithResource";
	public static final String GET_ALL_COURSE_MODULE_NAMES_PAIRS_ASSOCIATED_WITH_RESOURCE = 
			"course.getAllCourseModuleNamesPairsAssociatedWithResource";
	public static final String GET_BY_AREA_ID = "course.getByAreaId";
	
	public static final int MAX_NAME_LENGTH = 255;
	
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
	
	@Column(nullable = false)
	@NotNull
	private Integer duration;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Module> modules;
	
	@Column(nullable = false)
	private Boolean open = false;
	
	@ManyToOne
    @JoinColumn(name = "area_id")
	private Area area;
	
	@Column(nullable = false)
	private Boolean disable = false;
	
	@Column(nullable = false)
	private Boolean published = false;
	
	@ManyToOne
	private User owner;
	
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

	/**
	 * @return duration in weeks
	 */
	public Integer getDuration() {
		return duration;
	}
	
	/**
	 * @param duration duration in days
	 */
	public void setDuration(Integer duration) {
		this.duration = duration;
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

	public Boolean getDisable() {
		return disable;
	}

	public void setDisable(Boolean disable) {
		this.disable = disable;
	}
	
	public void disable() {
		this.disable = true;
		for (Module module : this.modules) {
			module.disable();
		}
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}
	
}
