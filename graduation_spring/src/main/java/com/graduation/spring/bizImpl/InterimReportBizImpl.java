package com.graduation.spring.bizImpl;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graduation.hibernate.entity.InterimReport;
import com.graduation.spring.biz.InterimReportBiz;
import com.graduation.spring.dao.HandleEntityDAO;

/**

* @author awang
* @version 1.0
* @date 2020年5月6日 上午12:07:15
* 
*/

@Service("interimReportBiz")
public class InterimReportBizImpl implements InterimReportBiz {

	private HandleEntityDAO handle;
	
	public InterimReportBizImpl() {
		
	}
	
	@Autowired
	public void setHandleEntityDAO(HandleEntityDAO handle) {
		this.handle = handle;
	}
	
	@PostConstruct
	public void init() {
		handle.setType("InterimReport");
	}
	
	@Override
	public boolean insertData(InterimReport data) {
		return handle.setData(data).insertEntity();
	}
	
	@Override
	public ArrayList<InterimReport> getData(Integer topicId) {
		InterimReport data = new InterimReport();
		data.setTopicId(topicId);
		return (ArrayList<InterimReport>)handle.setData(data).setBaseQuery().setOrder("commitTime", "desc").getResultList();
	}
	
	@Override
	public boolean saveData(InterimReport data) {
		return handle.setData(data).updateEntity();
	}
	
	@Override
	public InterimReport getDataById(Integer id) {
		InterimReport data = new InterimReport();
		data.setId(id);
		return (InterimReport)handle.setData(data).setBaseQuery().getResultList().get(0);
	}
	
}
