package com.graduation.spring.biz;

import java.util.ArrayList;

import com.graduation.hibernate.entity.Bernal;

/**

* @author awang
* @version 1.0
* @date 2020年5月6日 上午12:01:28
* 
*/

public interface BernalBiz {

	public boolean insertData(Bernal data);
	
	public ArrayList<Bernal> getDataByTopicId(Integer topicId);
	
	public boolean saveData(Bernal data);
	
	public Bernal getDataById(Integer id);
	
}
