package com.graduation.spring.biz;

import java.util.ArrayList;

import com.graduation.hibernate.entity.GuidanceRecord;

/**

* @author awang
* @version 1.0
* @date 2020年5月6日 上午12:02:38
* 
*/

public interface GuidanceRecordBiz {

	public boolean insertData(GuidanceRecord data);
	
	public ArrayList<GuidanceRecord> getDataByTopicId(Integer topicId);
	
	public boolean saveData(GuidanceRecord data);
	
	public GuidanceRecord getDataById(Integer id);
	
}
