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

	boolean insertData(GuidanceRecord data);
	
	ArrayList<GuidanceRecord> getDataByTopicId(Integer topicId);
	
	boolean saveData(GuidanceRecord data);
	
	GuidanceRecord getDataById(Integer id);
	
}
