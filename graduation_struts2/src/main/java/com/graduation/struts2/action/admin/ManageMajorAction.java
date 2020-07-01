package com.graduation.struts2.action.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.graduation.hibernate.entity.Department;
import com.graduation.hibernate.entity.Major;
import com.graduation.hibernate.entity.Student;
import com.graduation.spring.biz.DepartmentBiz;
import com.graduation.spring.biz.MajorBiz;
import com.opensymphony.xwork2.ActionSupport;

/**

* @author awang
* @version 1.0
* @date 2020年4月22日 下午11:43:35
* 
*/

@Controller
@Scope("prototype")
public class ManageMajorAction extends ActionSupport implements ServletRequestAware{

	private Major major;

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}
	
	private HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	@Resource
	private MajorBiz majorBiz;
	@Resource
	private DepartmentBiz departmentBiz;
	
	public void getAllMajor() throws IOException, JSONException {
		String selection = request.getParameter("selection");
		List<Major> majorList;
		if(!selection.trim().equals("")) {
			majorList = majorBiz.likeSearch(selection);
		}else{
			majorList = majorBiz.getAllMajor();			
		}
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象 
		JSONArray jsonArray = new JSONArray();
		for(Major major:majorList) {
			JSONObject json = new JSONObject();
			json.accumulate("id", major.getId());
			json.accumulate("department", major.getDepartment().getName());
			json.accumulate("name", major.getName());
			jsonArray.put(json);
		}
		out.println(jsonArray.toString());
		out.flush();
		out.close();
	}
	
	public void insertMajor() throws IOException {
		boolean ifSuccess = departmentBiz.addMajor(major, request.getParameter("department"));
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
	
	public void updateMajor() throws IOException {
		Department d = new Department();
		d.setName(request.getParameter("department"));
		major.setDepartment(d);
		boolean ifSuccess = majorBiz.updateMajor(major);
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
	
	public void deleteMajor() throws IOException {
		boolean ifSuccess = majorBiz.deleteMajor(major.getId());
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
