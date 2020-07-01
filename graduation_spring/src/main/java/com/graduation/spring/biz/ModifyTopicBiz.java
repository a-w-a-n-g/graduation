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

	public ArrayList<ModifyTopic> getAllModifyTopic();
	
	public ArrayList<ModifyTopic> likeSearch(String selection);
	
	public boolean insertModify(ModifyTopic mt);
	
	public ArrayList<ModifyTopic> getAdminCheckTopic();
	
	public boolean adminAgreeModify(Integer id, String opinion);
	
	public boolean adminDisagreeModify(Integer id, String opinion);
	
	public boolean tchAgreeModify(Integer id, String opinion);
	
	public boolean tchDisagreeModify(Integer id, String opinion);
	
}
