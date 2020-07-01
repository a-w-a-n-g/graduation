package com.graduation.struts2.action.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.graduation.hibernate.entity.ClassNum;
import com.graduation.hibernate.entity.Student;
import com.graduation.hibernate.entity.Teacher;
import com.graduation.spring.biz.TeacherBiz;
import com.opensymphony.xwork2.ActionSupport;

/**

* @author awang
* @version 1.0
* @date 2020年4月23日 下午10:03:07
* 
*/

@Controller
@Scope("prototype")
public class ManageTeacherAction extends ActionSupport implements ServletRequestAware {

	private Teacher teacher;

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	private HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	@Resource
	private TeacherBiz teacherBiz;
	
	public void getAllTeacher() throws IOException, JSONException {
		String selection = request.getParameter("selection");
		List<Teacher> teacherList;
		if(!selection.trim().equals("")) {
			teacherList = teacherBiz.likeSearch(selection);
		}else{
			teacherList = teacherBiz.getAllTch();			
		}
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象 
		JSONArray jsonArray = new JSONArray();
		for(Teacher tch:teacherList) {
			JSONObject json = new JSONObject();
			json.accumulate("id", tch.getId());
			json.accumulate("name", tch.getName());
			json.accumulate("title", tch.getTitle());
			if(tch.getSex() == 0) {
				json.accumulate("sex", "男");
			}else{
				json.accumulate("sex", "女");
			}
			json.accumulate("phone", tch.getPhone());
			json.accumulate("mail", tch.getMail());
			jsonArray.put(json);
		}
		out.println(jsonArray.toString());
		out.flush();
		out.close();
	}
	
	public void insertTeacher() throws IOException {
		if(request.getParameter("sex").equals("男")) {
			teacher.setSex(0);
		}else{
			teacher.setSex(1);
		}
		teacher.setPassword("123456");
		boolean ifSuccess = teacherBiz.insertTch(teacher);
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		if(ifSuccess) {
			json.accumulate("showInfo", "添加成功！");			
		}else{
			json.accumulate("showInfo", "添加失败！");						
		}
		out.println(json);
		out.flush();
		out.close();
	}
	
	public void updateTeacher() throws IOException {
		if(request.getParameter("sex").equals("男")) {
			teacher.setSex(0);
		}else{
			teacher.setSex(1);
		}
		boolean ifSuccess = teacherBiz.updateTch(teacher);
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		if(ifSuccess) {
			json.accumulate("showInfo", "修改成功！");			
		}else{
			json.accumulate("showInfo", "修改失败！");						
		}
		out.println(json);
		out.flush();
		out.close();
	}
	
	public void deleteTeacher() throws IOException {
		boolean ifSuccess = teacherBiz.deleteTeacher(teacher.getId());
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		if(ifSuccess) {
			json.accumulate("showInfo", "删除成功！");			
		}else{
			json.accumulate("showInfo", "删除失败！");						
		}
		out.println(json);
		out.flush();
		out.close();
	}

}
