package com.graduation.spring.bizImpl;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.graduation.hibernate.entity.ModifyTopic;
import com.graduation.hibernate.entity.Student;
import com.graduation.hibernate.entity.Teacher;
import com.graduation.hibernate.entity.Topic;
import com.graduation.spring.biz.StudentBiz;
import com.graduation.spring.biz.TeacherBiz;
import com.graduation.spring.biz.TopicBiz;
import com.graduation.spring.dao.HandleEntityDAO;

/**

* @author awang
* @version 1.0
* @date 2020年4月28日 下午4:18:22
* 
*/

@Service("topicBiz")
public class TopicBizImpl implements TopicBiz {

	@Resource(name="handleEntityDAO")
	private HandleEntityDAO handle;
	
	public TopicBizImpl() {
		
	}

	@PostConstruct
	public void init() {
		handle.setType("Topic");		
	}
	
	@Resource
	private TeacherBiz teacherBiz;
	@Resource
	private StudentBiz studentBiz;
	
	@Override
	public ArrayList<Topic> getAllStuTopic(String department) {
		Topic data = new Topic();
		data.setDepartmentName(department);
		data.setIfPass(true);
		data.setIfSelect(false);
		return (ArrayList<Topic>)handle.setData(data).setBaseQuery().getResultList();
	}
	
	@Override
	public ArrayList<Topic> getAllTchTopic(String teacherId) {
		Topic topic = new Topic();
		topic.setTeacher(teacherBiz.searchOne(teacherId));
		return (ArrayList<Topic>)handle.setData(topic).setBaseQuery().setOrder("insertTime", "desc").getResultList();
	}
	
	@Override
	public ArrayList<Topic> getAllPassTopic(String teacherId) {
		Topic topic = new Topic();
		topic.setIfPass(true);
		topic.setTeacher(teacherBiz.searchOne(teacherId));
		return (ArrayList<Topic>)handle.setData(topic).setBaseQuery().getResultList();
	}

	@Override
	public ArrayList<Topic> likeSearch(String selection) {
		ArrayList<Topic> returnList = new ArrayList<Topic>();
		ArrayList<Topic> subList;
		subList = (ArrayList<Topic>)handle.setData(new Topic()).setBaseQuery().setLikeQuery("title", "%"+selection+"%").getResultList();
		returnList.addAll(subList);
		subList = (ArrayList<Topic>)handle.setBaseQuery().setLikeQuery("difficulty", "%"+selection+"%").getResultList();
		returnList.addAll(subList);
		return returnList;
	}
	
	@Override
	public boolean updateTopic(Topic topic) {
		ModifyTopic data = new ModifyTopic();
		
		return false;
	}
	
	@Override
	public boolean deteleTopic(Integer id) {
		Topic data = new Topic();
		data.setId(id);
		data = (Topic)handle.setData(data).setBaseQuery().getResultList().get(0);
		return handle.setData(data).deleteEntity();
	}
	
	@Override
	public boolean insertTopic(Topic topic) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public ArrayList<Topic> getCheckTopic() {
		return (ArrayList<Topic>)handle.setData(new Topic()).setBaseQuery().setOrder("insertTime", "desc").getResultList();
	}
	
	@Override
	public Topic getTopicById(Integer id) {
		Topic data = new Topic();
		data.setId(id);
		return (Topic)handle.setData(data).setBaseQuery().getResultList().get(0);
	}
	
	@Override
	public boolean saveTopic(Topic topic) {
		return handle.setData(topic).updateEntity();
	}
	
	@Override
	public boolean agreeInsert(Integer id, String opinion) {
		Topic data = new Topic();
		data.setId(id);
		data = (Topic)handle.setData(data).setBaseQuery().getResultList().get(0);
		data.setIfPass(true);
		data.setOpinion(opinion);
		return handle.setData(data).updateEntity();
	}
	
	@Override
	public boolean disagreeInsert(Integer id, String opinion) {
		Topic data = new Topic();
		data.setId(id);
		data = (Topic)handle.setData(data).setBaseQuery().getResultList().get(0);
		data.setIfPass(false);
		data.setOpinion(opinion);
		return handle.setData(data).updateEntity();
	}
	
	@Override
	public Topic getSelectedTopic(String sid) {
		Student stu = studentBiz.getStudentById(new Long(sid));
		return stu.getTopic();
	}
	
}
