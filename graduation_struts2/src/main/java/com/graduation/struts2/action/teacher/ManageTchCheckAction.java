package com.graduation.struts2.action.teacher;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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

import com.graduation.hibernate.entity.ModifyTopic;
import com.graduation.hibernate.entity.Topic;
import com.graduation.spring.biz.ModifyTopicBiz;
import com.graduation.spring.biz.TopicBiz;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**

* @author awang
* @version 1.0
* @date 2020年5月4日 下午3:18:53
* 
*/

@Controller
@Scope("prototype")
public class ManageTchCheckAction extends ActionSupport implements ServletRequestAware {

	private Integer mid;
	private String opinion;
	
	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	private HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	
	@Resource
	private TopicBiz topicBiz;
	@Resource
	private ModifyTopicBiz modifyTopicBiz;
	
	/*
	 * 获取教师需要审核的改题申请
	 */
	public void getAllTchCheck() throws Exception{
		ActionContext ac = ActionContext.getContext();
		String selection = request.getParameter("selection");
		List<ModifyTopic> modifyTopicList;
		if(!selection.trim().equals("")) {
			modifyTopicList = modifyTopicBiz.likeSearch(selection);
		}else{
			modifyTopicList = modifyTopicBiz.getAllModifyTopic();
		}
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象 
		JSONArray jsonArray = new JSONArray();
		for(ModifyTopic modifyTopic : modifyTopicList) {
			JSONObject json = new JSONObject();
			json.accumulate("mid", modifyTopic.getId());
			json.accumulate("title", modifyTopic.getTitle());
			json.accumulate("departmentName", modifyTopic.getDepartmentName());
			json.accumulate("difficulty", modifyTopic.getDifficulty());
			json.accumulate("teacher", topicBiz.getTopicById(modifyTopic.getTopicId()).getTeacher().getName());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			json.accumulate("modifyTime", formatter.format(modifyTopic.getModifyTime()));
			if(modifyTopic.getIfPass_admin() != null) {
				if(modifyTopic.getIfPass_admin()) {
					json.accumulate("ifPassA", "审核通过");					
				}else{
					json.accumulate("ifPassA", "审核不通过");										
				}
			}else{
				json.accumulate("ifPassA", "未审核");
			}
			if(modifyTopic.getIfPass_tch() != null) {
				if(modifyTopic.getIfPass_tch()) {
					json.accumulate("ifPassT", "审核通过");					
				}else{
					json.accumulate("ifPassT", "审核不通过");										
				}
			}else{
				json.accumulate("ifPassT", "未审核");
			}
			String opinion;
			if(modifyTopic.getOpinion() != null) {
				opinion = modifyTopic.getOpinion();
			}else{
				opinion = "无";
			}
			json.accumulate("detail", "<a href='javascript:operation.showWindow(\"" + modifyTopic.getIntroduction()
			+"\"," + "\"" + modifyTopic.getRequirement() + "\"," + "\"" + opinion + "\"" +")'>查看详情</a>");
			
			Topic topic = topicBiz.getTopicById(modifyTopic.getTopicId());
			json.accumulate("topic", "<a href='javascript:operation.topicWindow(\"" + topic.getTitle()
			+ "\"," + "\"" + topic.getDepartmentName() + "\"," + "\"" + topic.getDifficulty() 
			+ "\"," + "\"" + topic.getIntroduction()
			+"\"," + "\"" + topic.getRequirement() + "\"," + "\"" + topic.getOpinion() + "\"" +")'>查看原信息</a>");
			jsonArray.put(json);
		}
		out.println(jsonArray.toString());
		out.flush();
		out.close();
	}
	
	public void agreeModify() throws Exception {
		boolean ifSuccess;
		ModifyTopic data = new ModifyTopic();
		data.setId(mid);
		ifSuccess = modifyTopicBiz.tchAgreeModify(mid, opinion);
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		if(ifSuccess) {
			json.accumulate("showInfo", "审核成功！");			
		}else{
			json.accumulate("showInfo", "审核失败！");						
		}
		out.println(json);
		out.flush();
		out.close();
	}
	
	public void disagreeModify() throws Exception {
		boolean ifSuccess;
		ModifyTopic data = new ModifyTopic();
		data.setId(mid);
		ifSuccess = modifyTopicBiz.tchDisagreeModify(mid, opinion);
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		if(ifSuccess) {
			json.accumulate("showInfo", "审核成功！");			
		}else{
			json.accumulate("showInfo", "审核失败！");						
		}
		out.println(json);
		out.flush();
		out.close();
	}
}
