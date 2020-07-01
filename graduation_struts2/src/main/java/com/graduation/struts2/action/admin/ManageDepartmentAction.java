package com.graduation.struts2.action.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

import com.graduation.hibernate.entity.Department;
import com.graduation.hibernate.entity.Student;
import com.graduation.spring.biz.DepartmentBiz;
import com.opensymphony.xwork2.ActionSupport;

/**

* @author awang
* @version 1.0
* @date 2020年4月22日 下午11:43:18
* 
*/

@Controller
@Scope("prototype")
public class ManageDepartmentAction extends ActionSupport implements ServletRequestAware{

	private Department department;
	
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	private HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	@Resource
	private DepartmentBiz departmentBiz;
	
	public void getAllDpm() throws IOException, JSONException {
		String selection = request.getParameter("selection");
		List<Department> departmentList;
		if(!selection.trim().equals("")) {
			departmentList = departmentBiz.likeSearch(selection);
		}else{
			departmentList = departmentBiz.getAllDpm();
		}
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象 
		JSONArray jsonArray = new JSONArray();
		for(Department dpm:departmentList) {
			JSONObject json = new JSONObject();
			json.accumulate("id", dpm.getId());
			json.accumulate("name", dpm.getName());
			jsonArray.put(json);
		}
		out.println(jsonArray.toString());
		out.flush();
		out.close();
	}
	
	public void insertDpm() throws IOException {
		boolean ifSuccess = departmentBiz.insertDpm(department);
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
	
	public void updateDpm() throws IOException {
		boolean ifSuccess = departmentBiz.updateDpm(department.getId(), department.getName());
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
	
	public void deleteDpm() throws IOException {
		boolean ifSuccess = departmentBiz.deleteDpm(department.getName());
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
