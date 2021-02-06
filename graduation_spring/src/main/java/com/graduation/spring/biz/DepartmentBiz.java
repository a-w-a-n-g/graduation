package com.graduation.spring.biz;

import java.util.ArrayList;

import com.graduation.hibernate.entity.Department;
import com.graduation.hibernate.entity.Major;

/**

* @author awang
* @version 1.0
* @date 2020年4月21日 上午2:34:33
* 
*/

public interface DepartmentBiz {

	Department search(Department department);
	
	ArrayList<Department> likeSearch(String selection);
	
	ArrayList<Department> getAllDpm();
	
	boolean updateDpm(Integer id, String name);
	
	boolean deleteDpm(String name);
	
	boolean insertDpm(Department department);
	
	boolean addMajor(Major major, String departmentName);
	
}
