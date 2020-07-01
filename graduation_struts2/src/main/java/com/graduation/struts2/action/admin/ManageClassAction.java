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

import com.graduation.hibernate.entity.ClassNum;
import com.graduation.hibernate.entity.Department;
import com.graduation.hibernate.entity.Major;
import com.graduation.spring.biz.ClassNumBiz;
import com.graduation.spring.biz.DepartmentBiz;
import com.graduation.spring.biz.MajorBiz;
import com.opensymphony.xwork2.ActionSupport;

/**

* @author awang
* @version 1.0
* @date 2020年4月22日 下午11:43:51
* 
*/

@Controller
@Scope("prototype")
public class ManageClassAction extends ActionSupport implements ServletRequestAware{

	private ClassNum classNum;

	public ClassNum getClassNum() {
		return classNum;
	}

	public void setClassNum(ClassNum classNum) {
		this.classNum = classNum;
	}

	private HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	@Resource
	private MajorBiz majorBiz;
	@Resource
	private ClassNumBiz classNumBiz;
	
	public void getAllClass() throws IOException, JSONException {
		String selection = request.getParameter("selection");
		List<ClassNum> classNumList;
		if(!selection.trim().equals("")) {
			classNumList = classNumBiz.likeSearch(selection);
		}else{
			classNumList = classNumBiz.getAllClass();			
		}
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象 
		JSONArray jsonArray = new JSONArray();
		for(ClassNum classNum:classNumList) {
			JSONObject json = new JSONObject();
			json.accumulate("id", classNum.getId());
			json.accumulate("major", classNum.getMajor().getName());
			json.accumulate("department", classNum.getMajor().getDepartment().getName());
			json.accumulate("name", classNum.getName());
			jsonArray.put(json);
		}
		out.println(jsonArray.toString());
		out.flush();
		out.close();
	}
	
	public void insertClass() throws IOException {
		boolean ifSuccess = majorBiz.addClassNum(classNum, request.getParameter("major"));
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
	
	public void updateClass() throws IOException {
		Major m = new Major();
		m.setName(request.getParameter("major"));
		classNum.setMajor(m);
		boolean ifSuccess = classNumBiz.updateClass(classNum);
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
	
	public void deleteClass() throws IOException {
		boolean ifSuccess = classNumBiz.deleteClass(classNum.getId());
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
