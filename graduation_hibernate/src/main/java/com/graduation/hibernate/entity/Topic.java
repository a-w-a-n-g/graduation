package com.graduation.hibernate.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**

* @author awang
* @version 1.0
* @date 2020年2月9日 下午4:25:56
* 
*/

@Entity
@Table(catalog = "graduation")
public class Topic {

	private Integer id;
	private String title;
	private Teacher teacher;
	private String departmentName;
	private String introduction;
	private String requirement;
	private Date insertTime;
	private Boolean ifPass;
	private String opinion;
	private String difficulty;
	private Boolean ifSelect;
	private Student student;
	
	public Topic() {
		
	}
	
	public Topic(Integer id, String title, Teacher teacher, String departmentName,String introduction,
			String requirement, String opinion, String difficulty) {
		this.id = id;
		this.title = title;
		this.teacher = teacher;
		this.departmentName = departmentName;
		this.introduction = introduction;
		this.requirement = requirement;
		this.opinion = opinion;
		this.difficulty = difficulty;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length = 60)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@ManyToOne(optional = false, cascade = {CascadeType.MERGE,CascadeType.REFRESH}, targetEntity = Teacher.class)
	@JoinColumn(name = "teacherId")
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@Column(length = 30)
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Column(length = 600)
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	@Column(length = 1200)
	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	@Column(columnDefinition = "datetime")
	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	@Column
	public Boolean isIfPass() {
		return ifPass;
	}

	public void setIfPass(Boolean ifPass) {
		this.ifPass = ifPass;
	}

	@Column(length = 300, nullable = true)
	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	@Column(length = 6)
	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	@Column
	public Boolean isIfSelect() {
		return ifSelect;
	}

	public void setIfSelect(Boolean ifSelect) {
		this.ifSelect = ifSelect;
	}

	@OneToOne(mappedBy = "topic", cascade = {CascadeType.MERGE,CascadeType.REFRESH})
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
}
