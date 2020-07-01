package com.graduation.struts2.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**

* @author awang
* @version 1.0
* @date 2020年3月16日 下午10:29:18
* 
*/

public class TeacherLoginInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ac = ActionContext.getContext();
		if(ac.getSession().get("id") == null || ac.getSession().get("id") != "2") {
			return "index";
		}
		return invocation.invoke();
	}
	
}
