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

	public boolean insertData(ThesisPaper data);
	
	public ArrayList<ThesisPaper> getDataByTopicId(Integer topicId);
	
	public boolean saveData(ThesisPaper data);
	
	public ThesisPaper getDataById(Integer id);
	
}
