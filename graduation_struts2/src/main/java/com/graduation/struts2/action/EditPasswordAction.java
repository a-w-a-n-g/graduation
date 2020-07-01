package com.graduation.struts2.action;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.graduation.spring.biz.AdministratorBiz;
import com.graduation.spring.biz.StudentBiz;
import com.graduation.spring.biz.TeacherBiz;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**

* @author awang
* @version 1.0
* @date 2020年4月22日 下午11:13:01
* 
*/

@Controller
@Scope("prototype")
public class EditPasswordAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	@Resource
	private AdministratorBiz administratorBiz;
	@Resource
	private TeacherBiz teacherBiz;
	@Resource
	private StudentBiz studentBiz;
	
	public void editPsw() throws Exception {
		ActionContext ac = ActionContext.getContext();
		boolean ifSuccess = false;
		if(ac.getSession().get("id").equals("1")) {
			ifSuccess = studentBiz.updatePassword(ac.getSession().get("user").toString(), request.getParameter("password"));			
		}else if(ac.getSession().get("id").equals("2")) {
			ifSuccess = teacherBiz.updatePassword(ac.getSession().get("user").toString(), request.getParameter("password"));			
		}else {
			ifSuccess = administratorBiz.updatePassword(ac.getSession().get("user").toString(), request.getParameter("password"));			
		}
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		if(ifSuccess) {
			json.accumulate("showInfo", "修改密码成功！");			
		}else{
			json.accumulate("showInfo", "修改密码失败！");						
		}
		out.println(json);
		out.flush();
		out.close();
	}
	
}
