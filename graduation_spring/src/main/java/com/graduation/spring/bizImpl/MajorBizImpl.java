package com.graduation.spring.bizImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graduation.hibernate.entity.ClassNum;
import com.graduation.hibernate.entity.Department;
import com.graduation.hibernate.entity.Major;
import com.graduation.hibernate.entity.Student;
import com.graduation.spring.biz.DepartmentBiz;
import com.graduation.spring.biz.MajorBiz;
import com.graduation.spring.dao.HandleEntityDAO;

/**

* @author awang
* @version 1.0
* @date 2020年4月21日 上午2:37:39
* 
*/

@Service("majorBiz")
public class MajorBizImpl implements MajorBiz {
	
	private HandleEntityDAO handle;
	
	@Resource
	private DepartmentBiz departmentBiz;
	
	public MajorBizImpl() {
		
	}
	
	@Autowired
	public void setHandleEntityDAO(HandleEntityDAO handle) {
		this.handle = handle;
	}
	
	@PostConstruct
	public void init() {
		handle.setType("Major");
	}

	@Override
	public Major search(Major major) {
		List list = handle.setData(major).setBaseQuery().getResultList();
		if(list.size() > 0) {
			return (Major)list.get(0);
		}
		return null;
	}
	
	@Override
	public ArrayList<Major> likeSearch(String selection) {
		ArrayList<Major> returnList = new ArrayList<Major>();
		ArrayList<Major> subList;
		subList = (ArrayList<Major>)handle.setData(new Major()).setBaseQuery().setLikeQuery("name", "%"+selection+"%").getResultList();
		returnList.addAll(subList);
		ArrayList<Department> departmentList = departmentBiz.likeSearch(selection);
		if(departmentList.size()>0) {
			for(Department department:departmentList) {
				Iterator itor = department.getMajors().iterator();
				while(itor.hasNext()) {
					returnList.add((Major)itor.next());
				}
			}
		}
		return returnList;
	}
	
	@Override
	public ArrayList<Major> getAllMajor() {
		return (ArrayList<Major>)handle.setData(new Major()).setBaseQuery().getResultList();
	}
	
	@Override
	public boolean updateMajor(Major major) {
		Major data = new Major();
		data.setId(major.getId());
		List resultList = handle.setData(data).setBaseQuery().getResultList();
		if(resultList.size()==0) {
			return departmentBiz.addMajor(major, major.getDepartment().getName());
		}
		data = (Major)resultList.get(0);
		if(data.getName() != major.getName()) {
			data.setName(major.getName());
		}
//		if(data.getDepartment().getName() != major.getDepartment().getName()) {
//			Department department = departmentBiz.search(major.getDepartment());
//			if(department==null) {
//				return departmentBiz.insertDpm(major.getDepartment()) && departmentBiz.addMajor(data, major.getDepartment().getName());
//			}else{
//				return departmentBiz.addMajor(data, major.getDepartment().getName());
//			}
//		}
		return handle.setData(data).updateEntity();
	}
	
	@Override
	public boolean deleteMajor(Integer id) {
		Major data = new Major();
		data.setId(id);
		data = (Major)handle.setData(data).setBaseQuery().getResultList().get(0);
		return handle.setData(data).deleteEntity();
	}
	
	@Override
	public boolean addClassNum(ClassNum classNum, String majorName) {
		Major major = new Major();
		major.setName(majorName);
		major = this.search(major);
		if(major == null) {
			return false;
		}
		classNum.setMajor(major);
		major.getClasses().add(classNum);
		return handle.setData(major).updateEntity();
	}
}
