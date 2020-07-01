package com.graduation.spring.bizImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graduation.hibernate.entity.Department;
import com.graduation.hibernate.entity.Major;
import com.graduation.hibernate.entity.Student;
import com.graduation.spring.biz.DepartmentBiz;
import com.graduation.spring.dao.HandleEntityDAO;

import fontUtils.ChineseAndUnicode;

/**

* @author awang
* @version 1.0
* @date 2020年4月21日 上午2:37:17
* 
*/

@Service("departmentBiz")
public class DepartmentBizImpl implements DepartmentBiz {
	
	private HandleEntityDAO handle;
	
	public DepartmentBizImpl() {
		
	}
	
	@Autowired
	public void setHandleEntityDAO(HandleEntityDAO handle) {
		this.handle = handle;
	}
	
	@PostConstruct
	public void init() {
		handle.setType("Department");
	}

	@Override
	public Department search(Department department) {
		List departmentList = handle.setData(department).setBaseQuery().getResultList();
		if(departmentList.size() > 0){
			return (Department)departmentList.get(0);			
		}
		return null;
	}
	
	@Override
	public ArrayList<Department> likeSearch(String selection) {
		return (ArrayList<Department>)handle.setData(new Department()).setBaseQuery().setLikeQuery("name", "%" + ChineseAndUnicode.chinaToUnicode(selection) + "%").getResultList();
	}
	
	@Override
	public ArrayList<Department> getAllDpm() {
		return (ArrayList<Department>)handle.setData(new Department()).setBaseQuery().getResultList();
	}
	
	@Override
	public boolean updateDpm(Integer id, String name) {
		Department data = new Department();
		data.setId(id);
		List result = handle.setData(data).setBaseQuery().getResultList();
		if(result.size() > 0) {
			data = (Department)result.get(0);
			data.setName(name);
		}else{
			data = new Department();
			data.setName(name);
			data = (Department)handle.setData(data).setBaseQuery().getResultList();
			data.setId(id);
		}
		return handle.setData(data).updateEntity();
	}
	
	@Override
	public boolean deleteDpm(String name) {
		Department data = new Department();
		data.setName(name);
		data = (Department)handle.setData(data).setBaseQuery().getResultList().get(0);
		return handle.setData(data).deleteEntity();
	}
	
	@Override
	public boolean insertDpm(Department department) {
		return handle.setData(department).insertEntity();
	}
	
	@Override
	public boolean addMajor(Major major, String departmentName) {
		Department department = new Department();
		department.setName(departmentName);
		department = this.search(department);
		if(department == null) {
			return false;
		}
		major.setDepartment(department);
		department.getMajors().add(major);
		return handle.setData(department).updateEntity();
	}
	

}
