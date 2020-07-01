package com.graduation.hibernate.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**

* @author awang
* @version 1.0
* @date 2020年2月12日 下午3:07:21
* 
*/

@Entity
@Table(name = "topic_modify", catalog = "graduation")
public class ModifyTopic {

	private Integer id;
	private String title;
	private String departmentName;
	private String introduction;
	private String requirement;
	private String opinion;
	private String difficulty;
	private Date modifyTime;
	private Boolean ifPass_tch;
	private Boolean ifPass_admin;
	private Integer topicId;
	
	public ModifyTopic() {
		
	}
	
	public ModifyTopic(String title, String departmentName, String introduction, String requirement, 
			String opinion, String difficulty) {
		this.title = title;
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
	
	@Column(columnDefinition = "datetime")
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(nullable = true)
	public Boolean getIfPass_tch() {
		return ifPass_tch;
	}

	public void setIfPass_tch(Boolean ifPass_tch) {
		this.ifPass_tch = ifPass_tch;
	}

	@Column(nullable = true)
	public Boolean getIfPass_admin() {
		return ifPass_admin;
	}

	public void setIfPass_admin(Boolean ifPass_admin) {
		this.ifPass_admin = ifPass_admin;
	}

	@Column(nullable = false)
	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	
}
