package com.crsms.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="user_info")
@NamedQueries({
	@NamedQuery(name = UserInfo.DELETE, query = "DELETE FROM UserInfo uf WHERE uf.id=:id")})
@PrimaryKeyJoinColumn
public class UserInfo {
	
	public static final int MAX_NAME_LENGTH = 20;
	public static final String DELETE = "UserInfo.delete";
	
	@Id  
    @Column(name = "id")  
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crsms_gen")
	@SequenceGenerator(name = "crsms_gen", sequenceName = "user_info_id_seq", allocationSize = 1)
	private Long id;
	
	@OneToOne  
	@Cascade({ CascadeType.ALL })
	private User user;
	
	@Column
	@Size(max = 20)
	private String firstName;
	
	@Column
	@Size(max = 20)
	private String lastName;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@Cascade({CascadeType.ALL})
	private Set<Group> groups;
	
	@Column(name = "image", columnDefinition = "text")
	private String image;
	
	public UserInfo() {	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

}
