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

	boolean insertData(Bernal data);
	
	ArrayList<Bernal> getDataByTopicId(Integer topicId);
	
	boolean saveData(Bernal data);
	
	Bernal getDataById(Integer id);
	
}
