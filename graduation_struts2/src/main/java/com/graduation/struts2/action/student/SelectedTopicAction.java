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

import com.graduation.hibernate.entity.Topic;
import com.graduation.spring.biz.TopicBiz;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**

* @author awang
* @version 1.0
* @date 2020年5月5日 上午1:14:40
* 
*/

@Controller
@Scope("prototype")
public class SelectedTopicAction extends ActionSupport implements ServletRequestAware {

	private HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	@Resource
	private TopicBiz topicBiz;
	
	public void getSelectedTopic() throws Exception {
		ActionContext ac = ActionContext.getContext();
		String selection = request.getParameter("selection");
		Topic topic = topicBiz.getSelectedTopic(ac.getSession().get("user").toString());
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象 
		JSONArray jsonArray = new JSONArray();
		JSONObject json = new JSONObject();
		json.accumulate("id", topic.getId());
		json.accumulate("title", topic.getTitle());
		json.accumulate("department", topic.getDepartmentName());
		json.accumulate("difficulty", topic.getDifficulty());
		json.accumulate("teacher", topic.getTeacher().getName()+"老师");
		json.accumulate("detail", "<a href='javascript:operation.showWindow(\"" + topic.getIntroduction()
		+"\"," + "\"" + topic.getRequirement() + "\"" +")'>查看详情</a>");
		jsonArray.put(json);
		out.println(jsonArray.toString());
		out.flush();
		out.close();
	}
	
}
