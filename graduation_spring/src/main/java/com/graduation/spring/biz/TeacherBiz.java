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

	public Teacher loginValidate(String name, String password);
	
	public ArrayList<Teacher> getAllTch();
	
	public ArrayList<Teacher> likeSearch(String selection);
	
	public Teacher searchOne(String teacherId);
	
	public boolean insertTch(Teacher teacher);
	
	public boolean updateTch(Teacher teacher);
	
	public boolean deleteTeacher(String id);
	
	public boolean updatePassword(String id, String psw);
	
	public boolean addTopic(Topic topic, String teacherId);
	
}
