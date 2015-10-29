package com.crsms.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
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
@Table(name="course")
@NamedQueries({
	@NamedQuery(name = Course.GET_BY_NAME, query = "FROM Course c WHERE c.name=:name")
})
public class Course {
	public static final String GET_BY_NAME = "course.getCourseByName";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crsms_gen")
	@SequenceGenerator(name = "crsms_gen", sequenceName = "course_id_seq", allocationSize = 1)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, length = 1024)
	private String description;
	
	
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private DateTime startDate;
	
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDurationAsSecondsInteger")
	private Duration duration;
	
	@OneToMany(mappedBy="course", fetch = FetchType.LAZY)
	@Cascade({CascadeType.ALL})
	private Set<Module> modules;
	
	@Column(nullable = false)
	private Boolean open = false;
	
	//TODO: change
	@ManyToOne
	//@Cascade({CascadeType.ALL})
    @JoinColumn(name="area_id")
	private Area area;
	
	public Course() { }

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
		if(duration != null)
			return duration.toStandardDays().getDays()/7;
		return 0;
	}
	
	public void setWeekDuration(int weeks) {
		this.duration = new Duration(weeks*7L*24L*60L*60L*1000L);
	}

	public Set<Module> getModules() {
		return modules;
	}

	public void setModules(Set<Module> modules) {
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
}
