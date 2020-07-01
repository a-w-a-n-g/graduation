package com.graduation.spring.bizImpl;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graduation.hibernate.entity.ThesisPaper;
import com.graduation.spring.biz.ThesisPaperBiz;
import com.graduation.spring.dao.HandleEntityDAO;

/**

* @author awang
* @version 1.0
* @date 2020年5月6日 上午12:09:42
* 
*/

@Service("thesisPaperBiz")
public class ThesisPaperBizImpl implements ThesisPaperBiz {

	private HandleEntityDAO handle;
	
	public ThesisPaperBizImpl() {
		
	}
	
	@Autowired
	public void setHandleEntityDAO(HandleEntityDAO handle) {
		this.handle = handle;
	}
	
	@PostConstruct
	public void init() {
		handle.setType("ThesisPaper");
	}
	
	@Override
	public boolean insertData(ThesisPaper data) {
		return handle.setData(data).insertEntity();
	}
	
	@Override
	public ArrayList<ThesisPaper> getDataByTopicId(Integer topicId) {
		ThesisPaper data = new ThesisPaper();
		data.setTopicId(topicId);
		return (ArrayList<ThesisPaper>)handle.setData(data).setBaseQuery().setOrder("commitTime", "desc").getResultList();
	}
	
	@Override
	public boolean saveData(ThesisPaper data) {
		return handle.setData(data).updateEntity();
	}
	
	@Override
	public ThesisPaper getDataById(Integer id) {
		ThesisPaper data = new ThesisPaper();
		data.setId(id);
		return (ThesisPaper)handle.setData(data).setBaseQuery().getResultList().get(0);
	}
	
}
