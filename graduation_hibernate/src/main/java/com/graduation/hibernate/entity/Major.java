package com.graduation.hibernate.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**

* @author awang
* @version 1.0
* @date 2020年2月9日 下午4:27:24
* 
*/

@Entity
@Table(catalog = "graduation")
public class Major {

	private Integer id;
	private String name;
	private Department department;
	private Set<ClassNum> classes;
	
	public Major() {
		
	}
	
	public Major(String name, Department department) {
		this.name = name;
		this.department = department;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, columnDefinition = "tinyint", updatable = false)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(length = 30)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne(optional = false, cascade = {CascadeType.MERGE,CascadeType.REFRESH}, targetEntity = Department.class)
	@JoinColumn(name = "departmentId")
	public Department getDepartment() {
		return department;
	}
	
	public void setDepartment(Department department) {
		this.department = department;
	}

	@OneToMany(mappedBy = "major", cascade = CascadeType.ALL)
	public Set<ClassNum> getClasses() {
		return classes;
	}

	public void setClasses(Set<ClassNum> classes) {
		this.classes = classes;
	}
	
}
