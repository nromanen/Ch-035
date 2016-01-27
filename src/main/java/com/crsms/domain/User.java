
package com.crsms.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * 
 * @author Roman Romaniuk
 *
 */
@Entity
@Table(name = "users")
@Access(AccessType.FIELD)
@NamedQueries({
		@NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
		@NamedQuery(name = User.BY_EMAIL, query = "FROM User u WHERE lower (u.email)= lower (:email)"),
		@NamedQuery(name = User.ALL_SORTED, query = "FROM User u ORDER BY u.id"), 
		@NamedQuery(name = User.GET_APPROVED, query = "Select u FROM User u WHERE u.teacherRequest=:teacherRequest "
														+ "ORDER BY u.teacherRequest.requestedDate")})
public class User {
	public static final int MIN_PASSWORD_LENGTH = 5;
	public static final int MAX_PASSWORD_LENGTH = 255;
	public static final String DELETE = "User.delete";
	public static final String ALL_SORTED = "User.getAllSorted";
	public static final String GET_APPROVED = "User.getApproved";
	public static final String BY_EMAIL = "User.getByEmail";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crsms_gen")
	@SequenceGenerator(name = "crsms_gen", sequenceName = "user_id_seq", allocationSize = 21)
	private Long id;

	@Column(nullable = false, unique = true)
	@Email 
	@NotEmpty
	private String email;

	@Column(nullable = false)
	@Size(min = MIN_PASSWORD_LENGTH, max = MAX_PASSWORD_LENGTH)
	private String password;

	@OneToOne(mappedBy = "user")
	@Cascade({ CascadeType.REMOVE })
	private UserInfo userInfo;

	@OneToOne
	@Cascade({ CascadeType.MERGE })
	@JoinTable (name = "user_roles", 
	joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
	inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
	private Role role;
	
	@OneToOne(mappedBy = "user")
	@Cascade({CascadeType.REMOVE})
	private TeacherRequest teacherRequest;
	
	@Column (nullable = false)
	private Boolean isEnabled = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
		

	  public boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	public TeacherRequest getTeacherRequest() {
		return teacherRequest;
	}

	public void setTeacherRequest(TeacherRequest teacherRequest) {
		this.teacherRequest = teacherRequest;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((isEnabled == null) ? 0 : isEnabled.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isEnabled == null) {
			if (other.isEnabled != null)
				return false;
		} else if (!isEnabled.equals(other.isEnabled))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password
				+ ", userInfo=" + userInfo + ", role=" + role + ", isEnabled="
				+ isEnabled + "]";
	}

}
