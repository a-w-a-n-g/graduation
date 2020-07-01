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

	public boolean insertData(InterimReport data);
	
	public ArrayList<InterimReport> getData(Integer topicId);
	
	public boolean saveData(InterimReport data);
	
	public InterimReport getDataById(Integer id);
	
}
