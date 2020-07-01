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
* @date 2020年5月5日 下午11:37:10
* 
*/

@Entity
@Table(name = "interim_report", catalog = "graduation")
public class InterimReport {

	private Integer id;
	private Integer topicId;
	private String filePath;
	private Date commitTime;
	private Boolean ifPass;
	private String opinion;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(nullable = false)
	public Integer getTopicId() {
		return topicId;
	}
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	
	@Column(length = 100)
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	@Column(columnDefinition = "datetime")
	public Date getCommitTime() {
		return commitTime;
	}
	public void setCommitTime(Date commitTime) {
		this.commitTime = commitTime;
	}
	
	@Column
	public Boolean getIfPass() {
		return ifPass;
	}
	public void setIfPass(Boolean ifPass) {
		this.ifPass = ifPass;
	}
	
	@Column(length = 300)
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
}
