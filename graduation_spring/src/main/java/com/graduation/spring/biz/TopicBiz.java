package com.graduation.spring.biz;

import java.util.ArrayList;

import com.graduation.hibernate.entity.Topic;

/**

* @author awang
* @version 1.0
* @date 2020年4月28日 下午4:17:13
* 
*/

public interface TopicBiz {

	public ArrayList<Topic> getAllStuTopic(String department);
	
	public ArrayList<Topic> getAllTchTopic(String teacherId);
	
	public ArrayList<Topic> getAllPassTopic(String teacherId);
	
	public ArrayList<Topic> likeSearch(String selection);
	
	public boolean updateTopic(Topic topic);
	
	public boolean insertTopic(Topic topic);
	
	public boolean deteleTopic(Integer id);
	
	public ArrayList<Topic> getCheckTopic();
	
	public Topic getTopicById(Integer id);
	
	public boolean saveTopic(Topic topic);
	
	public boolean agreeInsert(Integer id, String opinion);
	
	public boolean disagreeInsert(Integer id, String opinion);
	
	public Topic getSelectedTopic(String sid);
	
}
