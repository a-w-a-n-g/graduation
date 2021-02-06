package com.graduation.spring.biz;

import java.util.ArrayList;

import com.graduation.hibernate.entity.ThesisPaper;

/**

* @author awang
* @version 1.0
* @date 2020年5月6日 上午12:02:59
* 
*/

public interface ThesisPaperBiz {

	boolean insertData(ThesisPaper data);
	
	ArrayList<ThesisPaper> getDataByTopicId(Integer topicId);
	
	boolean saveData(ThesisPaper data);
	
	ThesisPaper getDataById(Integer id);
	
}
