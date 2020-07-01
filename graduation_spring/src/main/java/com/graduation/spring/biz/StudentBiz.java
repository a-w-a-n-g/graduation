package com.graduation.spring.biz;

import java.util.ArrayList;

import com.graduation.hibernate.entity.Student;

/**

* @author awang
* @version 1.0
* @date 2020年2月19日 下午6:48:37
* 
*/

public interface StudentBiz {

	public Student loginValidate(String name, String password);
	
	public ArrayList<Student> getAllStu();
	
	public ArrayList<Student> likeSearch(String selection);
	
	public boolean updateStudent(Student student);
	
	public boolean deleteStudent(Long id);
	
	public boolean updatePassword(String id, String psw);
	
	public Student getStudentById(Long id);
	
	public boolean selectTopic(Long stuId, Integer topicId);
	
}
