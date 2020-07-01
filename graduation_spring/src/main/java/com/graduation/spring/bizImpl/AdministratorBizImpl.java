package com.graduation.spring.bizImpl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graduation.hibernate.entity.Administrator;
import com.graduation.spring.biz.AdministratorBiz;
import com.graduation.spring.dao.HandleEntityDAO;

/**

* @author awang
* @version 1.0
* @date 2020年2月19日 下午6:53:43
* 
*/

@Service("administratorBiz")
public class AdministratorBizImpl implements AdministratorBiz {
	
	private HandleEntityDAO handle;
	
	public AdministratorBizImpl() {
		
	}
	
	@Autowired
	public void setHandleEntityDAO(HandleEntityDAO handle) {
		this.handle = handle;
	}
	
	@PostConstruct
	public void init() {
		handle.setType("Administrator");
	}
	
	@Override
	public String loginValidate(String name, String password) {
		Administrator login = new Administrator(name, password);
		List<Administrator> list = handle.setData(login).setBaseQuery().getResultList();
		if(list.size() > 0) {
			return list.get(0).getName();
		}
		return null;
	}
	
	@Override
	public boolean updatePassword(String name, String psw) {
		Administrator data = new Administrator();
		data.setName(name);
		data = (Administrator)handle.setData(data).setBaseQuery().getResultList().get(0);
		data.setPassword(psw);
		return handle.setData(data).updateEntity();
	}
	
}
