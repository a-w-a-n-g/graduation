package com.graduation.spring.bizImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graduation.hibernate.entity.ClassNum;
import com.graduation.hibernate.entity.Student;
import com.graduation.spring.biz.ClassNumBiz;
import com.graduation.spring.biz.MajorBiz;
import com.graduation.spring.dao.HandleEntityDAO;

/**

* @author awang
* @version 1.0
* @date 2020年4月20日 下午6:59:07
* 
*/

@Service("ClassNumBiz")
public class ClassNumBizImpl implements ClassNumBiz {
	
	private HandleEntityDAO handle;
	
	@Resource
	private MajorBiz majorBiz;
	
	public ClassNumBizImpl() {
		
	}
	
	@Autowired
	public void setHandleEntityDAO(HandleEntityDAO handle) {
		this.handle = handle;
	}
	
	@PostConstruct
	public void init() {
		handle.setType("ClassNum");
	}

	@Override
	public ArrayList<ClassNum> getAllClass() {
		return (ArrayList<ClassNum>)handle.setData(new ClassNum()).setBaseQuery().getResultList();
	}
	
	@Override
	public ClassNum search(ClassNum classNum) {
		List classList = handle.setData(classNum).setBaseQuery().getResultList();
		if(classList.size() > 0) {
			return (ClassNum)classList.get(0);
		}
		return null;
	}
	
	@Override
	public ArrayList<ClassNum> likeSearch(String selection) {
		return (ArrayList<ClassNum>)handle.setData(new ClassNum()).setBaseQuery().setLikeQuery("name", '%' + selection + '%').getResultList();
	}
	
	@Override
	public boolean addStudent(Student stu, String classNum) {
		ClassNum c = new ClassNum();
		c.setName(classNum);
		c = this.search(c);
		stu.setClassNum(c);
		c.getStudents().add(stu);
		return handle.setData(c).updateEntity();
	}
	
	@Override
	public boolean updateClass(ClassNum classNum) {
		ClassNum data = new ClassNum();
		data.setId(classNum.getId());
		List resultList = handle.setData(data).setBaseQuery().getResultList();
		if(resultList.size()==0) {
			return majorBiz.addClassNum(classNum, classNum.getMajor().getName());
		}
		data = (ClassNum)resultList.get(0);
		if(data.getName() != classNum.getName()) {
			data.setName(classNum.getName());
		}
		return handle.setData(data).updateEntity();
	}
	
	@Override
	public boolean deleteClass(Integer id) {
		ClassNum data = new ClassNum();
		data.setId(id);
		data = (ClassNum)handle.setData(data).setBaseQuery().getResultList().get(0);
		return handle.setData(data).deleteEntity();
	}

}
