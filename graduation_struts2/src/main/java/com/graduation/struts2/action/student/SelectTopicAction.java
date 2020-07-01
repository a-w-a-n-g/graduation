package com.graduation.struts2.action.student;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.graduation.hibernate.entity.Student;
import com.graduation.hibernate.entity.Topic;
import com.graduation.spring.biz.StudentBiz;
import com.graduation.spring.biz.TopicBiz;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**

* @author awang
* @version 1.0
* @date 2020年5月4日 下午9:54:33
* 
*/

@Controller
@Scope("prototype")
public class SelectTopicAction extends ActionSupport implements ServletRequestAware {

	private Integer tid;
	
	public Integer getTid() {
		return tid;
	}
	
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	
	private HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	@Resource
	private TopicBiz topicBiz;
	@Resource
	private StudentBiz studentBiz;
	
	public void getAllTopic() throws Exception{
		ActionContext ac = ActionContext.getContext();
		String selection = request.getParameter("selection");
		List<Topic> topicList;
		if(!selection.trim().equals("")) {
			topicList = topicBiz.likeSearch(selection);
		}else{
			topicList = topicBiz.getAllStuTopic(ac.getSession().get("department").toString());
		}
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象 
		JSONArray jsonArray = new JSONArray();
		for(Topic topic : topicList) {
			JSONObject json = new JSONObject();
			json.accumulate("id", topic.getId());
			json.accumulate("title", topic.getTitle());
			json.accumulate("department", topic.getDepartmentName());
			json.accumulate("difficulty", topic.getDifficulty());
			json.accumulate("teacher", topic.getTeacher().getName()+"老师");
			json.accumulate("detail", "<a href='javascript:operation.showWindow(\"" + topic.getIntroduction()
			+"\"," + "\"" + topic.getRequirement() + "\"" +")'>查看详情</a>");
			jsonArray.put(json);
		}
		out.println(jsonArray.toString());
		out.flush();
		out.close();
	}
	
	public void selectTopic() throws Exception{
		ActionContext ac = ActionContext.getContext();
		boolean ifSuccess = false;
		String mes;
		if(!Boolean.valueOf(ac.getSession().get("select").toString()).booleanValue()) {
			ifSuccess = studentBiz.selectTopic(new Long(ac.getSession().get("user").toString()), tid);
			ac.getSession().put("select", true);
			if(ifSuccess) {
				mes = "选题成功！";
			}else{
				mes = "选题失败！";
			}
		}else{
			mes = "选题失败！你已选题！";
		}
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		json.accumulate("showInfo", mes);
		out.println(json);
		out.flush();
		out.close();
	}
	
}
