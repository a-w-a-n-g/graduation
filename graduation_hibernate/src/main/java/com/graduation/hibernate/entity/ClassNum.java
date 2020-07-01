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
* @date 2020年2月14日 上午12:45:23
* 
*/

@Entity
@Table(name = "class", catalog = "graduation")
public class ClassNum {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;
	
	@Column(length = 50)
	private String name;
	
	@ManyToOne(optional = false, cascade = {CascadeType.MERGE,CascadeType.REFRESH}, targetEntity = Major.class)
	@JoinColumn(name = "majorId")
	private Major major;
	
	@OneToMany(mappedBy = "classNum", cascade = CascadeType.ALL)
	private Set<Student> students;
	
	public ClassNum() {
		
	}
	
	public ClassNum(Integer id, String name, Major major) {
		this.name = name;
		this.major = major;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
}
