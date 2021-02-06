package com.graduation.spring.biz;

import java.util.ArrayList;

import com.graduation.hibernate.entity.Teacher;
import com.graduation.hibernate.entity.Topic;

/**

* @author awang
* @version 1.0
* @date 2020年2月19日 下午6:48:48
* 
*/

public interface TeacherBiz {

	Teacher loginValidate(String name, String password);
	
	ArrayList<Teacher> getAllTch();
	
	ArrayList<Teacher> likeSearch(String selection);
	
	Teacher searchOne(String teacherId);
	
	boolean insertTch(Teacher teacher);
	
	boolean updateTch(Teacher teacher);
	
	boolean deleteTeacher(String id);
	
	boolean updatePassword(String id, String psw);
	
	boolean addTopic(Topic topic, String teacherId);
	
}
