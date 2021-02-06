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

	ArrayList<ClassNum> getAllClass();
	
	ClassNum search(ClassNum classNum);
	
	boolean addStudent(Student stu, String classNum);
	
	ArrayList<ClassNum> likeSearch(String selection);
	
	boolean updateClass(ClassNum classNum);
	
	boolean deleteClass(Integer id);
	
}
