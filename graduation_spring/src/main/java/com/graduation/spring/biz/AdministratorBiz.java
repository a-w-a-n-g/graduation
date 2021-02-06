package com.graduation.spring.biz;

/**

* @author awang
* @version 1.0
* @date 2020年2月19日 下午6:50:10
* 
*/

public interface AdministratorBiz {

	String loginValidate(String name, String password);
	
	boolean updatePassword(String name, String psw);
	
}
