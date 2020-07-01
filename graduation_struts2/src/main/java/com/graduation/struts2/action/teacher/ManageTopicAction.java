package com.graduation.struts2.action.teacher;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.graduation.hibernate.entity.ClassNum;
import com.graduation.hibernate.entity.ModifyTopic;
import com.graduation.hibernate.entity.Student;
import com.graduation.hibernate.entity.Topic;
import com.graduation.spring.biz.ModifyTopicBiz;
import com.graduation.spring.biz.TeacherBiz;
import com.graduation.spring.biz.TopicBiz;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**

* @author awang
* @version 1.0
* @date 2020年4月25日 下午8:27:56
* 
*/

@Controller
@Scope("prototype")
public class ManageTopicAction extends ActionSupport implements ServletRequestAware {
	
	private Topic topic;

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	private HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	
	@Resource
	private TopicBiz topicBiz;
	@Resource
	private TeacherBiz teacherBiz;
	@Resource
	private ModifyTopicBiz modifyTopicBiz;
	
	/*
	 * 获取所有已经审核通过的选题
	 */
	public void getAllPassTopic() throws Exception {
		ActionContext ac = ActionContext.getContext();
		String selection = request.getParameter("selection");
		List<Topic> topicList;
		if(!selection.trim().equals("")) {
			topicList = topicBiz.likeSearch(selection);
		}else{
			topicList = topicBiz.getAllPassTopic(ac.getSession().get("user").toString());
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
			json.accumulate("departmentName", topic.getDepartmentName());
			json.accumulate("difficulty", topic.getDifficulty());
			json.accumulate("requirement", topic.getRequirement());
			json.accumulate("introduction", topic.getIntroduction());
			String student;
			if(topic.getStudent() != null) {
				student = topic.getStudent().getClassNum().getName() + "  " +topic.getStudent().getId()
						+ "  " +topic.getStudent().getName();
			}else{
				student = "无";
			}
			if(topic.isIfSelect()) {
				json.accumulate("select", "已被选");
			}else{
				json.accumulate("select", "未被选");				
			}
			json.accumulate("detail", "<a href='javascript:operation.showWindow(\"" + topic.getIntroduction()
			+"\"," + "\"" + topic.getRequirement() + "\"," + "\"" + topic.getOpinion() + "\"," + "\""
					+ student + "\"" +")'>查看</a>");
			jsonArray.put(json);
		}
		out.println(jsonArray.toString());
		out.flush();
		out.close();
	}
	
	/*
	 * 获取当前教师的所有修改选题记录
	 */
	public void getAllModifyTopic() throws Exception {
		ActionContext ac = ActionContext.getContext();
		String selection = request.getParameter("selection");
		List<ModifyTopic> modifyTopicList;
		List<Topic> topicList;
		if(!selection.trim().equals("")) {
			modifyTopicList = modifyTopicBiz.likeSearch(selection);
			topicList = topicBiz.likeSearch(selection);
		}else{
			modifyTopicList = modifyTopicBiz.getAllModifyTopic();
			topicList = topicBiz.getAllTchTopic(ac.getSession().get("user").toString());
		}
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象 
		JSONArray jsonArray = new JSONArray();
		for(ModifyTopic topic : modifyTopicList) {
			JSONObject json = new JSONObject();
			json.accumulate("title", topic.getTitle());
			json.accumulate("departmentName", topic.getDepartmentName());
			json.accumulate("difficulty", topic.getDifficulty());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			json.accumulate("modifyTime", formatter.format(topic.getModifyTime()));
			if(topic.getIfPass_admin() != null) {
				if(topic.getIfPass_admin()) {
					json.accumulate("ifPass", "审核通过");					
				}else{
					json.accumulate("ifPass", "审核不通过");										
				}
			}else{
				json.accumulate("ifPass", "未审核");
			}
			String opinion;
			if(topic.getOpinion() != null) {
				opinion = topic.getOpinion();
			}else{
				opinion = "无";
			}
			json.accumulate("detail", "<a href='javascript:operation.showWindow(\"" + topic.getIntroduction()
			+"\"," + "\"" + topic.getRequirement() + "\"," + "\"" + opinion + "\"" +")'>查看</a>");
			jsonArray.put(json);
		}
		for(Topic topic : topicList) {
			JSONObject json = new JSONObject();
			json.accumulate("title", "添加-->" + topic.getTitle());
			json.accumulate("departmentName", topic.getDepartmentName());
			json.accumulate("difficulty", topic.getDifficulty());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			json.accumulate("modifyTime", formatter.format(topic.getInsertTime()));
			if(topic.isIfPass() != null) {
				if(topic.isIfPass()) {
					json.accumulate("ifPass", "审核通过");					
				}else{
					json.accumulate("ifPass", "审核不通过");					
				}
			}else{
				json.accumulate("ifPass", "未审核");
			}
			String opinion;
			if(topic.getOpinion() != null) {
				opinion = topic.getOpinion();
			}else{
				opinion = "无";
			}
			json.accumulate("detail", "<a href='javascript:operation.showWindow(\"" + topic.getIntroduction()
			+"\"," + "\"" + topic.getRequirement() + "\"," + "\"" + opinion + "\"" +")'>查看</a>");
			jsonArray.put(json);
		}
		out.println(jsonArray.toString());
		out.flush();
		out.close();
	}
	
	/*
	 * 添加所要添加选题记录
	 */
	public void insertTopic() throws IOException {
		ActionContext ac = ActionContext.getContext();
		topic.setIfSelect(false);
		boolean ifSuccess = teacherBiz.addTopic(topic, ac.getSession().get("user").toString());
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
	
	/*
	 * 添加修改选题记录
	 */
	public void updateTopic() throws IOException {
		ModifyTopic mt = new ModifyTopic();
		mt.setTitle("修改-->" + topic.getTitle());
		mt.setDepartmentName(topic.getDepartmentName());
		mt.setIntroduction(topic.getIntroduction());
		mt.setRequirement(topic.getRequirement());
		mt.setOpinion(topic.getOpinion());
		mt.setDifficulty(topic.getDifficulty());
		mt.setModifyTime(new Date(System.currentTimeMillis()));
		mt.setIfPass_tch(true);
		mt.setTopicId(topic.getId());
		boolean ifSuccess = modifyTopicBiz.insertModify(mt);
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
	
	/*
	 *删除选题 
	 */
	public void deleteTopic() throws IOException {
		ModifyTopic mt = new ModifyTopic();
		mt.setTitle("删除-->" + topic.getTitle());
		mt.setDepartmentName(topic.getDepartmentName());
		mt.setIntroduction(topic.getIntroduction());
		mt.setRequirement(topic.getRequirement());
		mt.setOpinion(topic.getOpinion());
		mt.setDifficulty(topic.getDifficulty());
		mt.setModifyTime(new Date(System.currentTimeMillis()));
		mt.setIfPass_tch(true);
		mt.setTopicId(topic.getId());
		boolean ifSuccess = modifyTopicBiz.insertModify(mt);
		ifSuccess = topicBiz.deteleTopic(topic.getId());
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
