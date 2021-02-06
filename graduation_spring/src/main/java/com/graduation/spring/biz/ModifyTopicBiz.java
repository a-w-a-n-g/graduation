package com.graduation.spring.biz;

import java.util.ArrayList;

import com.graduation.hibernate.entity.ModifyTopic;

/**

* @author awang
* @version 1.0
* @date 2020年5月1日 下午6:08:58
* 
*/

public interface ModifyTopicBiz {

	ArrayList<ModifyTopic> getAllModifyTopic();
	
	ArrayList<ModifyTopic> likeSearch(String selection);
	
	boolean insertModify(ModifyTopic mt);
	
	ArrayList<ModifyTopic> getAdminCheckTopic();
	
	boolean adminAgreeModify(Integer id, String opinion);
	
	boolean adminDisagreeModify(Integer id, String opinion);
	
	boolean tchAgreeModify(Integer id, String opinion);
	
	boolean tchDisagreeModify(Integer id, String opinion);
	
}
