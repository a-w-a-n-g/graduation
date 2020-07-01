package com.graduation.struts2.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.graduation.hibernate.entity.Student;
import com.graduation.hibernate.entity.Teacher;
import com.graduation.spring.biz.AdministratorBiz;
import com.graduation.spring.biz.StudentBiz;
import com.graduation.spring.biz.TeacherBiz;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**

* @author awang
* @version 1.0
* @date 2020年2月21日 下午10:46:52
* 
*/

@Controller
@Scope("prototype")
public class LoginAction extends ActionSupport implements ServletRequestAware{

	private String user;
	private String psw;
	private int id;
	private String code;
	
	private HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	@Resource
	private AdministratorBiz administratorBiz;
	@Resource
	private StudentBiz studentBiz;
	@Resource
	private TeacherBiz teacherBiz;
	
	@Override
	public String execute() {
		ActionContext ac = ActionContext.getContext();
		Map<String, Object> session = ac.getSession();
//		防止不是从index页面访问该action时出现的问题
		if(user == null || psw == null || code == null) {
			return ERROR;
		}
		if(session.get("checkCode")!=null && session.get("checkCode").equals(code)){
			String name;
			if(id == 1) {
				Student login = studentBiz.loginValidate(user, psw);
				if(login != null) {
					ac.getSession().put("user", user);
					ac.getSession().put("id", "1");
					ac.getSession().put("name", login.getName());
					ac.getSession().put("classNum", login.getClassNum().getName());
					ac.getSession().put("major", login.getClassNum().getMajor().getName());
					ac.getSession().put("department", login.getClassNum().getMajor().getDepartment().getName());
					ac.getSession().put("phone", login.getPhone());
					ac.getSession().put("mail", login.getMail());
					if(login.getTopic() != null) {
						ac.getSession().put("select", true);						
					}else{
						ac.getSession().put("select", false);
					}
					return "student";
				}
			}
			if(id == 2) {
				Teacher login = teacherBiz.loginValidate(user, psw);
				if(login != null) {
					ac.getSession().put("user", user);
					ac.getSession().put("id", "2");
					ac.getSession().put("name", login.getName());
					ac.getSession().put("title", login.getTitle());
					return "teacher";
				}
			}
			if(id == 3) {
				name = administratorBiz.loginValidate(user, psw);
				if(name != null) {
					ac.getSession().put("user", user);
					ac.getSession().put("id", "3");
					ac.getSession().put("name", name);
					return "admin";
				}
			}
			return ERROR;
		}else{
			request.setAttribute("codeValidate", "false");		//也可以使用ac.put(key, value)存储request属性
			return ERROR;
		}
	}
	
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		super.validate();
	}
}