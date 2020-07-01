package com.graduation.hibernate.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**

* @author awang
* @version 1.0
* @date 2020年2月9日 下午4:25:45
* 
*/

@Entity
@Table(catalog = "graduation")
public class Teacher {

	private String id;
	private String name;
	private String password;
	private Integer sex;
	private String title;
	private Long phone;
	private String mail;
	private Set<Topic> topics;
	
	public Teacher() {
		
	}
	
	public Teacher(String id, String name, Integer sex, String title, Long phone, String mail) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.title = title;
		this.phone = phone;
		this.mail = mail;
	}

	@Id
	@Column(length = 20, unique = true)
	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	@Column(length = 20)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(length = 11)
	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	@Column(length = 30, nullable = true)
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

//	从表（没有外键的一方）使用mappedBy属性确定主导权（即指向主表类所对应的属性名），配置了mappedBy
//	不可再使用JoinColumn，否则出错。若使用JoinColumn不使用mappedBy则会多执行update语句，造成性能浪费
	@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
	public Set<Topic> getTopics() {
		return topics;
	}

	public void setTopics(Set<Topic> topic) {
		this.topics = topic;
	}
	
}
