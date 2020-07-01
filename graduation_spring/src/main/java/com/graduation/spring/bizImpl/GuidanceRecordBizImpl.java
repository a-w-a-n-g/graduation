package com.graduation.spring.bizImpl;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graduation.hibernate.entity.GuidanceRecord;
import com.graduation.spring.biz.GuidanceRecordBiz;
import com.graduation.spring.dao.HandleEntityDAO;

/**

* @author awang
* @version 1.0
* @date 2020年5月6日 上午12:08:40
* 
*/

@Service("guidanceRecordBiz")
public class GuidanceRecordBizImpl implements GuidanceRecordBiz {

	private HandleEntityDAO handle;
	
	public GuidanceRecordBizImpl() {
		
	}
	
	@Autowired
	public void setHandleEntityDAO(HandleEntityDAO handle) {
		this.handle = handle;
	}
	
	@PostConstruct
	public void init() {
		handle.setType("GuidanceRecord");
	}
	
	@Override
	public boolean insertData(GuidanceRecord data) {
		return handle.setData(data).insertEntity();
	}
	
	@Override
	public ArrayList<GuidanceRecord> getDataByTopicId(Integer topicId) {
		GuidanceRecord data = new GuidanceRecord();
		data.setTopicId(topicId);
		return (ArrayList<GuidanceRecord>)handle.setData(data).setBaseQuery().setOrder("commitTime", "desc").getResultList();
	}
	
	@Override
	public boolean saveData(GuidanceRecord data) {
		return handle.setData(data).updateEntity();
	}
	
	@Override
	public GuidanceRecord getDataById(Integer id) {
		GuidanceRecord data = new GuidanceRecord();
		data.setId(id);
		return (GuidanceRecord)handle.setData(data).setBaseQuery().getResultList().get(0);
	}
	
}
