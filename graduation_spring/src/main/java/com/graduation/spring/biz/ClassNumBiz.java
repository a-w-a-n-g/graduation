package com.graduation.spring.biz;

import java.util.ArrayList;

import com.graduation.hibernate.entity.ClassNum;
import com.graduation.hibernate.entity.Student;

/**

* @author awang
* @version 1.0
* @date 2020年4月20日 下午6:57:03
* 
*/

public interface ClassNumBiz {

	public ArrayList<ClassNum> getAllClass();
	
	public ClassNum search(ClassNum classNum);
	
	public boolean addStudent(Student stu, String classNum);
	
	public ArrayList<ClassNum> likeSearch(String selection);
	
	public boolean updateClass(ClassNum classNum);
	
	public boolean deleteClass(Integer id);
	
}
