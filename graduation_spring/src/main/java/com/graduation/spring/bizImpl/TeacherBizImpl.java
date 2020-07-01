package com.graduation.spring.bizImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.graduation.hibernate.entity.Student;
import com.graduation.hibernate.entity.Teacher;
import com.graduation.hibernate.entity.Topic;
import com.graduation.spring.biz.TeacherBiz;
import com.graduation.spring.dao.HandleEntityDAO;

/**

* @author awang
* @version 1.0
* @date 2020年2月19日 下午6:55:37
* 
*/

@Service("teacherBiz")
public class TeacherBizImpl implements TeacherBiz{

	@Resource(name="handleEntityDAO")
	private HandleEntityDAO handle;
	
	public TeacherBizImpl() {
		
	}

	@PostConstruct
	public void init() {
		handle.setType("Teacher");		
	}
	
	@Override
	public Teacher loginValidate(String name, String password) {
		Teacher login = new Teacher();
		login.setId(name);
		login.setPassword(password);
		List<Teacher> list = handle.setData(login).setBaseQuery().getResultList();
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}	
	
	@Override
	public boolean updatePassword(String id, String psw) {
		Teacher data = new Teacher();
		data.setId(id);;
		data = (Teacher)handle.setData(data).setBaseQuery().getResultList().get(0);
		data.setPassword(psw);
		return handle.setData(data).updateEntity();
	}
	
	@Override
	public ArrayList<Teacher> getAllTch() {
		return (ArrayList<Teacher>)handle.setData(new Teacher()).setBaseQuery().getResultList();
	}
	
	@Override
	public ArrayList<Teacher> likeSearch(String selection) {
		ArrayList<Teacher> returnList = new ArrayList<Teacher>();
		ArrayList<Teacher> subList;
		subList = (ArrayList<Teacher>)handle.setData(new Teacher()).setBaseQuery().setLikeQuery("id", "%"+selection+"%").getResultList();
		returnList.addAll(subList);
		subList = (ArrayList<Teacher>)handle.setBaseQuery().setLikeQuery("name", "%"+selection+"%").getResultList();
		returnList.addAll(subList);
		subList = (ArrayList<Teacher>)handle.setBaseQuery().setLikeQuery("sex", "%"+selection+"%").getResultList();
		returnList.addAll(subList);
		subList = (ArrayList<Teacher>)handle.setBaseQuery().setLikeQuery("title", "%"+selection+"%").getResultList();
		returnList.addAll(subList);
		subList = (ArrayList<Teacher>)handle.setBaseQuery().setLikeQuery("phone", "%"+selection+"%").getResultList();
		returnList.addAll(subList);
		subList = (ArrayList<Teacher>)handle.setBaseQuery().setLikeQuery("mail", "%"+selection+"%").getResultList();
		returnList.addAll(subList);
		return returnList;
	}
	
	@Override
	public Teacher searchOne(String teacherId) {
		Teacher data = new Teacher();
		data.setId(teacherId);
		return (Teacher)handle.setData(data).setBaseQuery().getResultList().get(0);
	}
	
	@Override
	public boolean insertTch(Teacher teacher) {
		return handle.setData(teacher).insertEntity();
	}
	
	@Override
	public boolean updateTch(Teacher teacher) {
		Teacher data = new Teacher();
		data.setId(teacher.getId());
		List list = handle.setData(data).setBaseQuery().getResultList();
		if(list.size() == 0) {
			teacher.setPassword("123456");
			return this.insertTch(teacher);
		}
		data = (Teacher)list.get(0);
		if(data.getName() != teacher.getName()) {
			data.setName(teacher.getName());
		}
		if(data.getSex() != teacher.getSex()) {
			data.setSex(teacher.getSex());
		}
		if(data.getTitle() != teacher.getTitle()) {
			data.setTitle(teacher.getTitle());
		}
		if(data.getPhone() != teacher.getPhone()) {
			data.setPhone(teacher.getPhone());
		}
		if(data.getMail() != teacher.getMail()) {
			data.setMail(teacher.getMail());
		}
		return handle.setData(data).updateEntity();
	}
	
	@Override
	public boolean deleteTeacher(String id) {
		Teacher data = new Teacher();
		data.setId(id);
		data = (Teacher)handle.setData(data).setBaseQuery().getResultList().get(0);
		return handle.setData(data).deleteEntity();
	}
	
	@Override
	public boolean addTopic(Topic topic, String teacherId) {
		Teacher teacher = new Teacher();
		teacher.setId(teacherId);
		teacher = (Teacher)handle.setData(teacher).setBaseQuery().getResultList().get(0);
		topic.setTeacher(teacher);
		teacher.getTopics().add(topic);
		return handle.setData(teacher).updateEntity();
	}
}
