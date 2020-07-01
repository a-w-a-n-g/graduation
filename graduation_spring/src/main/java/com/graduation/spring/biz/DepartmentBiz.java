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

	public Department search(Department department);
	
	public ArrayList<Department> likeSearch(String selection);
	
	public ArrayList<Department> getAllDpm();
	
	public boolean updateDpm(Integer id, String name);
	
	public boolean deleteDpm(String name);
	
	public boolean insertDpm(Department department);
	
	public boolean addMajor(Major major, String departmentName);
	
}
