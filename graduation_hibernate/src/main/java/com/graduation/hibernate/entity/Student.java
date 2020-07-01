package com.graduation.hibernate.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**

* @author awang
* @version 1.0
* @date 2020年2月9日 下午4:24:03
* 
*/

@Entity
@Table(catalog = "graduation")
public class Student {

	private Long id;
	private String name;
	private String password;
	private Integer sex;
	private ClassNum classNum;
	private Topic topic;
	private Long phone;
	private String mail;
	
	public Student() {
		
	}
	
	public Student(Long id, String name, Integer sex, ClassNum classNum) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.classNum = classNum;
	}

	@Id
	@Column(length = 13, unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(length = 12)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 16)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(length = 1, columnDefinition = "tinyint")
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	//若不加JoinColumn注解（指向外键列）则会自动生成一个中间表，所以主表（拥有外键的一方）的注解需要与其配合使用
	@ManyToOne(optional = false, cascade = {CascadeType.MERGE,CascadeType.REFRESH}, targetEntity = ClassNum.class)
	@JoinColumn(name = "classId")
	public ClassNum getClassNum() {
		return classNum;
	}

	public void setClassNum(ClassNum classNum) {
		this.classNum = classNum;
	}

	@OneToOne(cascade = {CascadeType.REFRESH,CascadeType.MERGE})
	@JoinColumn(name = "topicId")
	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	@Column(length = 11)
	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	@Column(length = 30)
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
}
