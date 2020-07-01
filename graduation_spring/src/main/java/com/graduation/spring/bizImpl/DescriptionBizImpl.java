package com.graduation.spring.bizImpl;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graduation.hibernate.entity.Description;
import com.graduation.spring.biz.DescriptionBiz;
import com.graduation.spring.dao.HandleEntityDAO;

/**

* @author awang
* @version 1.0
* @date 2020年5月6日 上午12:04:06
* 
*/

@Service("descriptionBiz")
public class DescriptionBizImpl implements DescriptionBiz {

	private HandleEntityDAO handle;
	
	public DescriptionBizImpl() {
		
	}
	
	@Autowired
	public void setHandleEntityDAO(HandleEntityDAO handle) {
		this.handle = handle;
	}
	
	@PostConstruct
	public void init() {
		handle.setType("Description");
	}
	
	@Override
	public boolean insertData(Description data) {
		return handle.setData(data).insertEntity();
	}
	
	@Override
	public ArrayList<Description> getDataByTopicId(Integer topicId) {
		Description data = new Description();
		data.setTopicId(topicId);
		return (ArrayList<Description>)handle.setData(data).setBaseQuery().setOrder("commitTime", "desc").getResultList();
	}
	
	@Override
	public boolean saveData(Description data) {
		return handle.setData(data).updateEntity();
	}
	
	@Override
	public Description getDataById(Integer id) {
		Description data = new Description();
		data.setId(id);
		return (Description)handle.setData(data).setBaseQuery().getResultList().get(0);

	}
	
}
