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

	ArrayList<Topic> getAllStuTopic(String department);
	
	ArrayList<Topic> getAllTchTopic(String teacherId);
	
	ArrayList<Topic> getAllPassTopic(String teacherId);
	
	ArrayList<Topic> likeSearch(String selection);
	
	boolean updateTopic(Topic topic);
	
	boolean insertTopic(Topic topic);
	
	boolean deteleTopic(Integer id);
	
	ArrayList<Topic> getCheckTopic();
	
	Topic getTopicById(Integer id);
	
	boolean saveTopic(Topic topic);
	
	boolean agreeInsert(Integer id, String opinion);
	
	boolean disagreeInsert(Integer id, String opinion);
	
	Topic getSelectedTopic(String sid);
	
}
