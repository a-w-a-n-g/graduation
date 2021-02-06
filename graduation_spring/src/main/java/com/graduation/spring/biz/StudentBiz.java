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

	Student loginValidate(String name, String password);
	
	ArrayList<Student> getAllStu();
	
	ArrayList<Student> likeSearch(String selection);
	
	boolean updateStudent(Student student);
	
	boolean deleteStudent(Long id);
	
	boolean updatePassword(String id, String psw);
	
	Student getStudentById(Long id);
	
	boolean selectTopic(Long stuId, Integer topicId);
	
}
