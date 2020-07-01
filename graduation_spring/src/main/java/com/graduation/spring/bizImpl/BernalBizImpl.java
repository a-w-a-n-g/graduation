package com.graduation.spring.bizImpl;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graduation.hibernate.entity.Bernal;
import com.graduation.spring.biz.BernalBiz;
import com.graduation.spring.dao.HandleEntityDAO;

/**

* @author awang
* @version 1.0
* @date 2020年5月6日 上午12:06:21
* 
*/

@Service("bernalBiz")
public class BernalBizImpl implements BernalBiz {

	private HandleEntityDAO handle;
	
	public BernalBizImpl() {
		
	}
	
	@Autowired
	public void setHandleEntityDAO(HandleEntityDAO handle) {
		this.handle = handle;
	}
	
	@PostConstruct
	public void init() {
		handle.setType("Bernal");
	}
	
	@Override
	public boolean insertData(Bernal data) {
		return handle.setData(data).insertEntity();
	}
	
	@Override
	public ArrayList<Bernal> getDataByTopicId(Integer topicId) {
		Bernal data = new Bernal();
		data.setTopicId(topicId);
		return (ArrayList<Bernal>)handle.setData(data).setBaseQuery().setOrder("commitTime", "desc").getResultList();
	}
	
	@Override
	public boolean saveData(Bernal data) {
		return handle.setData(data).updateEntity();
	}
	
	@Override
	public Bernal getDataById(Integer id) {
		Bernal data = new Bernal();
		data.setId(id);
		return (Bernal)handle.setData(data).setBaseQuery().getResultList().get(0);
	}
	
}
