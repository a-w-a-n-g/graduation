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
import com.graduation.hibernate.entity.Student;
import com.graduation.spring.biz.ClassNumBiz;
import com.graduation.spring.biz.StudentBiz;
import com.graduation.spring.biz.TeacherBiz;
import com.opensymphony.xwork2.ActionSupport;

/**

* @author awang
* @version 1.0
* @date 2020年4月20日 下午4:56:57
* 
*/

@Controller
@Scope("prototype")
public class ManageStudentAction extends ActionSupport implements ServletRequestAware{

	private Student student;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	private HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	@Resource
	private StudentBiz studentBiz;
	@Resource
	private TeacherBiz teacherBiz;
	@Resource
	private ClassNumBiz classNumBiz;
	
	public void getAllStu() throws IOException, JSONException {
		String selection = request.getParameter("selection");
		List<Student> studentList;
		if(!selection.trim().equals("")) {
			studentList = studentBiz.likeSearch(selection);
		}else{
			studentList = studentBiz.getAllStu();			
		}
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象 
		JSONArray jsonArray = new JSONArray();
		for(Student stu:studentList) {
			JSONObject json = new JSONObject();
			json.accumulate("id", stu.getId());
			json.accumulate("department", stu.getClassNum().getMajor().getDepartment().getName());
			json.accumulate("major", stu.getClassNum().getMajor().getName());
			json.accumulate("classNum", stu.getClassNum().getName());
			json.accumulate("name", stu.getName());
			if(stu.getSex() == 0) {
				json.accumulate("sex", "男");
			}else{
				json.accumulate("sex", "女");
			}
			json.accumulate("phone", stu.getPhone());
			json.accumulate("mail", stu.getMail());
			jsonArray.put(json);
		}
		out.println(jsonArray.toString());
		out.flush();
		out.close();
	}
	
	public void insertStu() throws IOException {
		if(request.getParameter("sex").equals("男")) {
			student.setSex(0);
		}else{
			student.setSex(1);
		}
		student.setPassword("123456");
		boolean ifSuccess = classNumBiz.addStudent(student, request.getParameter("classNum"));
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
	
	public void updateStu() throws IOException {
		if(request.getParameter("sex").equals("男")) {
			student.setSex(0);
		}else{
			student.setSex(1);
		}
		ClassNum cla = new ClassNum();
		cla.setName(request.getParameter("classNum"));
		student.setClassNum(cla);
		boolean ifSuccess = studentBiz.updateStudent(student);
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
	
	public void deleteStu() throws IOException {
		boolean ifSuccess = studentBiz.deleteStudent(student.getId());
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
	
	public void getClassName() throws IOException, JSONException {
		 List<ClassNum> classNumList = classNumBiz.getAllClass(); 
		 HttpServletResponse response=ServletActionContext.getResponse();
	        response.setContentType("text/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			//将要被返回到客户端的对象 
			JSONArray jsonArray = new JSONArray();
			for(ClassNum classnum:classNumList) {
				JSONObject json = new JSONObject();
				json.accumulate("classId", classnum.getId());
				json.accumulate("className", classnum.getName());
				jsonArray.put(json);
			}
			out.println(jsonArray.toString());
			out.flush();
			out.close();
	}

}
