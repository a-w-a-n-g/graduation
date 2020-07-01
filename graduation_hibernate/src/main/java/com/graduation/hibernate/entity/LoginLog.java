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
* @date 2020年2月9日 下午4:26:13
* 
*/

@Entity
@Table(name = "log_login", catalog = "graduation")
public class LoginLog {

	private Integer id;
	private Integer identity;
	private String name;
	private String entityId;
	private Date loginTime;
	
	public LoginLog() {
		
	}
	
	public LoginLog(Integer identity, String name, String entityId) {
		this.identity = identity;
		this.name = name;
		this.entityId = entityId;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, updatable = false)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(length = 1, columnDefinition = "tinyint")
	public Integer getIdentity() {
		return identity;
	}
	
	public void setIdentity(Integer identity) {
		this.identity = identity;
	}
	
	@Column(length = 12)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(length = 50)
	public String getEntityId() {
		return entityId;
	}
	
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	
	@Column(name = "loginTime", columnDefinition = "datetime")
	public Date getLoginTime() {
		return loginTime;
	}
	
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
}
