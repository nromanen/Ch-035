package com.crsms.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.validator.constraints.NotEmpty;



/**
 * @author Valerii Motresku
 * @author St. Roman
 */

@Entity
@Table(name = "module")
@NamedQueries({
	@NamedQuery(name = Module.GET_ALL, 
				query = "from Module order by id asc"),
				
	@NamedQuery(name = Module.GET_ALL_BY_COURSE_ID, 
				query = "select m from Course c join c.modules m"
					 + " where course_id = :id order by m.id asc"),
	@NamedQuery(name = Module.GET_BY_TEST,
				query = "SELECT module FROM Module module "
						+ "JOIN module.tests test "
						+ "WHERE test.id = :id"),
				
	@NamedQuery(name = Module.DELETE_BY_ID,
				query = "delete Module where id = :id"
				),
	@NamedQuery(name = Module.DISABLE_TESTS,
				query = "UPDATE Test test SET test.disable=true WHERE test IN "
						+ "(SELECT testList "
						+ "FROM Module module "
						+ "JOIN module.tests testList "
						+ "WHERE module.id = :id)"),
	@NamedQuery(name = Module.DISABLE_QUESTIONS,
				query = "UPDATE Question question SET question.disable=true WHERE question IN "
						+ "(SELECT questionList "
						+ "FROM Module module "
						+ "JOIN module.tests testList "
						+ "JOIN testList.questions questionList "
						+ "WHERE module.id = :id)"),
	@NamedQuery(name = Module.DISABLE_ANSWERS,
				query = "UPDATE Answer answer SET answer.disable=true WHERE answer IN "
						+ "(SELECT answerList "
						+ "FROM Module module "
						+ "JOIN module.tests testList "
						+ "JOIN testList.questions questionList "
						+ "JOIN questionList.answers answerList "
						+ "WHERE module.id = :id)")
})
public class Module {
	
	public static final String GET_ALL = "Module.getAll";
	public static final String GET_ALL_BY_COURSE_ID = "Module.getAllByCourseId";
	public static final String DELETE_BY_ID = "Module.deleteById";
	public static final String DISABLE_TESTS = "Module.disableTestsByModule";
	public static final String DISABLE_QUESTIONS = "Module.disableQuestionsByModule";
	public static final String DISABLE_ANSWERS = "Module.disableAnswersByModule";
	public static final String GET_BY_TEST = "Module.getByTest";
	public static final int MAX_NAME_LENGTH = 255;

	
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
	private String description;
		
	@ManyToMany
	private List<Resource> resources;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Test> tests;
	
	@Column(nullable = false)
	private Boolean available = false;
	
	@Column(name = "order_position", nullable = true)
	private Long orderPosition;
	
	@Column(nullable = false)
	private Boolean disable = false;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
	
	public void removeResource(Resource resource) {
		this.resources.remove(resource);
	}

	public List<Test> getTests() {
		return tests;
	}

	public void setTests(List<Test> tests) {
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

	public Boolean getDisable() {
		return disable;
	}

	public void setDisable(Boolean disable) {
		this.disable = disable;
	}
		
	public void addTest(Test test) {
		this.tests.add(test);
	}
	
	public void removeTest(Test test) {
		this.tests.remove(test);
	}

}
