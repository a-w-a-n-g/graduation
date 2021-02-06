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

	boolean insertData(Description data);
	
	ArrayList<Description> getDataByTopicId(Integer topicId);
	
	boolean saveData(Description data);
	
	Description getDataById(Integer id);
	
}
