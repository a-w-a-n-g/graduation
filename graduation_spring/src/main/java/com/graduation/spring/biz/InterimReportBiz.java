package com.graduation.spring.biz;

import java.util.ArrayList;

import com.graduation.hibernate.entity.InterimReport;

/**

* @author awang
* @version 1.0
* @date 2020年5月6日 上午12:01:54
* 
*/

public interface InterimReportBiz {

	boolean insertData(InterimReport data);
	
	ArrayList<InterimReport> getData(Integer topicId);
	
	boolean saveData(InterimReport data);
	
	InterimReport getDataById(Integer id);
	
}
