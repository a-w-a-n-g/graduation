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
* @date 2020年2月9日 下午4:41:18
* 
*/

@Entity
@Table(catalog = "graduation")
public class Notice {

	private Integer id;
	private Integer writer;
	private String writerName;
	private Integer target;
	private String content;
	
	public Notice() {
		
	}
	
	public Notice(Integer writer, String writerName, Integer target, String content) {
		this.writer = writer;
		this.writerName = writerName;
		this.target = target;
		this.content = content;
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
	public Integer getWriter() {
		return writer;
	}
	
	public void setWriter(Integer writer) {
		this.writer = writer;
	}
	
	@Column(length = 12, nullable = true)
	public String getWriterName() {
		return writerName;
	}
	
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	
	@Column(length = 1, columnDefinition = "tinyint")
	public Integer getTarget() {
		return target;
	}
	
	public void setTarget(Integer target) {
		this.target = target;
	}
	
	@Column(length = 300)
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
}
