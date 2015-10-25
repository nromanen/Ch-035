package com.crsms.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "users")
@Access(AccessType.FIELD)
@NamedQueries({
		@NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
		@NamedQuery(name = User.BY_EMAIL, query = "FROM User u WHERE u.email= :email"),
		@NamedQuery(name = User.ALL_SORTED, query = "FROM User u ORDER BY u.id"), })
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@SequenceGenerator(name = "user_gen", initialValue = 1)
public class User {
	
	private static final long serialVersionUID = 1L;
	public static final String DELETE = "User.delete";
	public static final String ALL_SORTED = "User.getAllSorted";
	public static final String BY_EMAIL = "User.getByEmail";
	public static final String SELECT_ID = "SELECT setval('users_id_seq', (SELECT MAX(id) FROM users)+1)";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crsms_gen")
	@SequenceGenerator(name = "crsms_gen", sequenceName = "user_id_seq")
	private Long id;

	@Column(nullable = false)
	@Email 
	@NotEmpty
	private String email;

	@Column(nullable = false)
	@Size(min=5, max=30)
	private String password;

	@OneToOne(mappedBy = "user")
	@Cascade({ CascadeType.SAVE_UPDATE })
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//	@JoinColumn(name = "userInfo_id")
	private UserInfo userInfo;

	@OneToOne
	@Cascade({ CascadeType.SAVE_UPDATE })
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@JoinColumn(name = "role_id")
	private Role role;

	public User() {
		super();
	}

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
	@Override
	
	public String toString() {
		return "User{" 
					+ ", id: " + getId() 
					+ ", email: " + getEmail()
					+ ", password: " + getPassword() 
					+ ", role: " + getRole()
					+ ", user info: " + getUserInfo() 
					+ "}";
	}

}
