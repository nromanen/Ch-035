package com.crsms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "teacher_request")
public class TeacherRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crsms_gen")
	@SequenceGenerator(name = "crsms_gen", sequenceName = "teacher_request_id_seq", allocationSize = 1)
	private Long id;
	
	@OneToOne
	@Cascade({CascadeType.ALL})
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "requested_date", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime requestedDate;
	
	@Column(name = "approved", nullable = true)
	private Boolean approved;
	
	@Column(name = "reviewed_date", nullable = true)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime reviewdDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DateTime getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(DateTime requestedDate) {
		this.requestedDate = requestedDate;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public DateTime getReviewdDate() {
		return reviewdDate;
	}

	public void setReviewdDate(DateTime reviewdDate) {
		this.reviewdDate = reviewdDate;
	}


}
