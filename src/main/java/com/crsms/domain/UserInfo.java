package com.crsms.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * 
 * @author Valerii Motresku
 *
 */

@Entity
@Table(name="user_info")
public class UserInfo {
	
	@Id  
    @Column(name = "user_id")  
    @GeneratedValue(generator = "user_info_gen")  
    @GenericGenerator(name = "user_info_gen", strategy = "foreign",   
    parameters = @Parameter(name = "property", value = "user"))  
	private Long userId;
	
	@OneToOne  
	@Cascade({ CascadeType.ALL })
    @PrimaryKeyJoinColumn
	private User user;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String secondName;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@Cascade({CascadeType.ALL})


	private Set<Group> groups;
	
	public UserInfo() {
	}


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

}
