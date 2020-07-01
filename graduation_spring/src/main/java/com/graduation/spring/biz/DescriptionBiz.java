package com.graduation.spring.biz;

import java.util.ArrayList;

import com.graduation.hibernate.entity.Description;

/**

* @author awang
* @version 1.0
* @date 2020年5月6日 上午12:01:10
* 
*/

public interface DescriptionBiz {

	public boolean insertData(Description data);
	
	public ArrayList<Description> getDataByTopicId(Integer topicId);
	
	public boolean saveData(Description data);
	
	public Description getDataById(Integer id);
	
}
