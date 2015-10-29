package com.crsms.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.Set;

/**
 * 
 * @author Valerii Motresku
 *
 */

@Entity
@Table(name="module")
@NamedQueries({
	@NamedQuery(name = Module.GET_ALL, 
				query = "from Module order by id asc"),
				
	@NamedQuery(name = Module.GET_ALL_BY_COURSE_ID, 
				query = "from Module where course_id = :id order by id asc"),
				
	@NamedQuery(name = Module.DELETE_BY_ID,
				query = "delete Module where id = :id"
				)
})
public class Module {
	
	public static final String GET_ALL = "Module.getAll";
	public static final String GET_ALL_BY_COURSE_ID = "Module.getAllByCourseId";
	public static final String DELETE_BY_ID = "Module.deleteById";
	
	public static final int MAX_NAME_LENGTH = 30;
	public static final int MAX_DESCTIPTION_LENGTH = 1000;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crsms_gen")
	@SequenceGenerator(name = "crsms_gen", sequenceName = "module_id_seq", allocationSize = 1)
	private Long id;
	
	@Column(nullable = false)
	@NotEmpty
	@Size(max = MAX_NAME_LENGTH)
	private String name;
	
	@Column(nullable = false)
	@NotEmpty
	@Size(max = MAX_DESCTIPTION_LENGTH)
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@Cascade({CascadeType.ALL})
	private Set<Resource> resources;
	
	@OneToMany(mappedBy = "module",fetch = FetchType.LAZY)
	@Cascade({CascadeType.ALL})
	private Set<Test> tests;
	
	@Column(nullable = false)
	private Boolean available = false;
	
	@Column(name = "order_position", nullable = true)
	private Long orderPosition;
	
	public Module() {
		super();
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}
	
	public void removeResource(Resource resource) {
		this.resources.remove(resource);
	}

	public Set<Test> getTests() {
		return tests;
	}

	public void setTests(Set<Test> tests) {
		this.tests = tests;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Long getOrderPosition() {
		return orderPosition;
	}

	public void setOrderPosition(Long orderPosition) {
		this.orderPosition = orderPosition;
	}
	
	public void addResource(Resource resource) {
		this.resources.add(resource);
	}
	
}
