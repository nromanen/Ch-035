package com.crsms.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@NamedQueries({ @NamedQuery(name = Role.ALL_SORTED, query = "FROM Role r ") })
public class Role {

	public static final String ALL_SORTED = "Role.getAll";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crsms_gen")
	@SequenceGenerator(name = "crsms_gen", sequenceName = "role_id_seq")
	private Long id;

	@Column(nullable = false)
	private String name;
	
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "user_roles",
//	joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
//	inverseJoinColumns = {@JoinColumn (name = "user_id", referencedColumnName = "id")})
//	@JoinColumn(name = "user_id")
//	private List <User> users;

	public Role() {
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

	public String toString() {
		return getName();
	}
//	public List<User> getUser() {
//		return users;
//	}
//
//	public void addUser(User user) {
//		this.users.add(user);
//	}
	

}
