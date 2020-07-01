package com.graduation.hibernate.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**

* @author awang
* @version 1.0
* @date 2020年2月9日 下午4:27:14
* 
*/

@Entity
@Table(name = "department", catalog = "graduation")
public class Department {

	private Integer id;
	private String name;
	private Set<Major> majors;
	
	public Department() {
		
	}
	
	public Department(String name) {
		this.name = name;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, columnDefinition = "tinyint", updatable = false)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "name", length = 30)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	public Set<Major> getMajors() {
		return majors;
	}

	public void setMajors(Set<Major> major) {
		this.majors = major;
	}
	
}
