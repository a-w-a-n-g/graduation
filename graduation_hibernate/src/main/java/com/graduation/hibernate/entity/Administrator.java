package com.graduation.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**

* @author awang
* @version 1.0
* @date 2020年2月9日 下午4:41:00
* 
*/

@Entity
@Table(name = "administrator", catalog = "graduation")
public class Administrator {

	private Integer id;
	private String name;
	private String password;
	private Boolean power;
	
	public Administrator() {
		
	}
	
	public Administrator(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //此处若不加上括号内的配置，操作会对hibernate_requence表操作
	@Column(name = "id", unique = true, nullable = false, updatable = false)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "name", length = 10)
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
	
	@Column(name = "power")
	public Boolean isPower() {
		return power;
	}
	
	public void setPower(Boolean power) {
		this.power = power;
	}
	
}
