package com.graduation.struts2.action.teacher;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.graduation.hibernate.entity.Bernal;
import com.graduation.hibernate.entity.Description;
import com.graduation.hibernate.entity.GuidanceRecord;
import com.graduation.hibernate.entity.InterimReport;
import com.graduation.hibernate.entity.Student;
import com.graduation.hibernate.entity.Teacher;
import com.graduation.hibernate.entity.ThesisPaper;
import com.graduation.hibernate.entity.Topic;
import com.graduation.spring.biz.BernalBiz;
import com.graduation.spring.biz.DescriptionBiz;
import com.graduation.spring.biz.GuidanceRecordBiz;
import com.graduation.spring.biz.InterimReportBiz;
import com.graduation.spring.biz.TeacherBiz;
import com.graduation.spring.biz.ThesisPaperBiz;
import com.graduation.spring.biz.TopicBiz;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**

* @author awang
* @version 1.0
* @date 2020年5月7日 上午3:49:50
* 
*/

@Controller
@Scope("prototype")
public class CheckFileAction extends ActionSupport implements ServletRequestAware{
	
	private String type;
	private String opinion;
	private Integer fid;

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	private HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	@Resource
	private TeacherBiz teacherBiz;
	@Resource
	private DescriptionBiz descriptionBiz;
	@Resource
	private BernalBiz bernalBiz;
	@Resource
	private InterimReportBiz interimReportBiz;
	@Resource
	private GuidanceRecordBiz guidanceRecordBiz;
	@Resource
	private ThesisPaperBiz thesisPaperBiz;
	@Resource
	private TopicBiz topicBiz;
	
	public void getTchCheckFile() throws Exception {
		ActionContext ac = ActionContext.getContext();
		Teacher teacher = teacherBiz.searchOne(ac.getSession().get("user").toString());
		Set<Topic> topics = teacher.getTopics();
		String selection = request.getParameter("selection");
		List<Description> descriptionList = new ArrayList<Description>();
		List<Bernal> bernalList = new ArrayList<Bernal>();
		List<InterimReport> interimReportList = new ArrayList<InterimReport>();
		List<GuidanceRecord> guidanceRecordList = new ArrayList<GuidanceRecord>();
		List<ThesisPaper> thesisPaperList = new ArrayList<ThesisPaper>();
		
		Iterator<Topic> topic = topics.iterator();
		while(topic.hasNext()) {
			Integer topicId = topic.next().getId();
			descriptionList.addAll(descriptionBiz.getDataByTopicId(topicId));			
		}
		topic = topics.iterator();
		while(topic.hasNext()) {
			Integer topicId = topic.next().getId();
			bernalList.addAll(bernalBiz.getDataByTopicId(topicId));			
		}
		topic = topics.iterator();
		while(topic.hasNext()) {
			Integer topicId = topic.next().getId();
			interimReportList.addAll(interimReportBiz.getData(topicId));			
		}
		topic = topics.iterator();
		while(topic.hasNext()) {
			Integer topicId = topic.next().getId();
			guidanceRecordList.addAll(guidanceRecordBiz.getDataByTopicId(topicId));			
		}
		topic = topics.iterator();
		while(topic.hasNext()) {
			Integer topicId = topic.next().getId();
			thesisPaperList.addAll(thesisPaperBiz.getDataByTopicId(topicId));			
		}
		
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象 
		JSONArray jsonArray = new JSONArray();
		
		for(ThesisPaper data:thesisPaperList) {
			JSONObject json = new JSONObject();
			json.accumulate("fid", data.getId());
			json.accumulate("type", "论文");
			Student stu = topicBiz.getTopicById(data.getTopicId()).getStudent();
			json.accumulate("student", stu.getClassNum().getName() + "--" + stu.getName());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			json.accumulate("time", formatter.format(data.getCommitTime()));
			if(data.getIfPass() == null) {
				json.accumulate("check", "未审核");
				json.accumulate("opinion", "无");
			}else{
				if(data.getIfPass()){
					json.accumulate("check", "审核通过");					
				}else{
					json.accumulate("check", "审核不通过");										
				}
				json.accumulate("opinion", data.getOpinion());
			}
			json.accumulate("file", "<a href='downloadFile.action?Id=" + data.getId() +
					"&&type=论文 " + "'>下载文件</>");
			jsonArray.put(json);
		}
		
		for(GuidanceRecord data:guidanceRecordList) {
			JSONObject json = new JSONObject();
			json.accumulate("fid", data.getId());
			json.accumulate("type", "指导记录");
			Student stu = topicBiz.getTopicById(data.getTopicId()).getStudent();
			json.accumulate("student", stu.getClassNum().getName() + "--" + stu.getName());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			json.accumulate("time", formatter.format(data.getCommitTime()));
			if(data.getIfPass() == null) {
				json.accumulate("check", "未审核");
				json.accumulate("opinion", "无");
			}else{
				if(data.getIfPass()){
					json.accumulate("check", "审核通过");					
				}else{
					json.accumulate("check", "审核不通过");										
				}
				json.accumulate("opinion", data.getOpinion());
			}
			json.accumulate("file", "<a href='downloadFile.action?Id=" + data.getId() +
					"&&type=指导记录" + "'>下载文件</>");
			jsonArray.put(json);
		}
		
		for(InterimReport data:interimReportList) {
			JSONObject json = new JSONObject();
			json.accumulate("fid", data.getId());
			json.accumulate("type", "中期报告");
			Student stu = topicBiz.getTopicById(data.getTopicId()).getStudent();
			json.accumulate("student", stu.getClassNum().getName() + "--" + stu.getName());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			json.accumulate("time", formatter.format(data.getCommitTime()));
			if(data.getIfPass() == null) {
				json.accumulate("check", "未审核");
				json.accumulate("opinion", "无");
			}else{
				if(data.getIfPass()){
					json.accumulate("check", "审核通过");					
				}else{
					json.accumulate("check", "审核不通过");										
				}
				json.accumulate("opinion", data.getOpinion());
			}
			json.accumulate("file", "<a href='downloadFile.action?Id=" + data.getId() +
					"&&type=中期报告" + "'>下载文件</>");
			jsonArray.put(json);
		}
		
		for(Bernal data:bernalList) {
			JSONObject json = new JSONObject();
			json.accumulate("fid", data.getId());
			json.accumulate("type", "开题报告");
			Student stu = topicBiz.getTopicById(data.getTopicId()).getStudent();
			json.accumulate("student", stu.getClassNum().getName() + "--" + stu.getName());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			json.accumulate("time", formatter.format(data.getCommitTime()));
			if(data.getIfPass() == null) {
				json.accumulate("check", "未审核");
				json.accumulate("opinion", "无");
			}else{
				if(data.getIfPass()){
					json.accumulate("check", "审核通过");					
				}else{
					json.accumulate("check", "审核不通过");										
				}
				json.accumulate("opinion", data.getOpinion());
			}
			json.accumulate("file", "<a href='downloadFile.action?Id=" + data.getId() +
					"&&type=开题报告" + "'>下载文件</>");
			jsonArray.put(json);
		}

		for(Description data:descriptionList) {
			JSONObject json = new JSONObject();
			json.accumulate("fid", data.getId());
			json.accumulate("type", "任务书");
			Student stu = topicBiz.getTopicById(data.getTopicId()).getStudent();
			json.accumulate("student", stu.getClassNum().getName() + "--" + stu.getName());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			json.accumulate("time", formatter.format(data.getCommitTime()));
			if(data.getIfPass() == null) {
				json.accumulate("check", "未审核");
				json.accumulate("opinion", "无");
			}else{
				if(data.getIfPass()){
					json.accumulate("check", "审核通过");					
				}else{
					json.accumulate("check", "审核不通过");										
				}
				json.accumulate("opinion", data.getOpinion());
			}
			json.accumulate("file", "<a href='downloadFile.action?Id=" + data.getId() +
					"&&type=任务书" + "'>下载文件</>");
			jsonArray.put(json);
		}

		out.println(jsonArray.toString());
		out.flush();
		out.close();
	}
	
	public void agreeFile() throws Exception {
		boolean ifSuccess = false;
		switch(type){
			case "任务书":
				Description data = new Description();
				data.setId(fid);
				data = descriptionBiz.getDataById(fid);
				data.setIfPass(true);
				data.setOpinion(opinion);
				ifSuccess = descriptionBiz.saveData(data);
				break;
			case "开题报告":
				Bernal data1 = new Bernal();
				data1.setId(fid);
				data1 = bernalBiz.getDataById(fid);
				data1.setIfPass(true);
				data1.setOpinion(opinion);
				ifSuccess = bernalBiz.saveData(data1);
				break;
			case "中期检查":
				InterimReport data2 = new InterimReport();
				data2.setId(fid);
				data2 = interimReportBiz.getDataById(fid);
				data2.setIfPass(true);
				data2.setOpinion(opinion);
				ifSuccess = interimReportBiz.saveData(data2);
				break;
			case "指导记录":
				GuidanceRecord data3 = new GuidanceRecord();
				data3.setId(fid);
				data3 = guidanceRecordBiz.getDataById(fid);
				data3.setIfPass(true);
				data3.setOpinion(opinion);
				ifSuccess = guidanceRecordBiz.saveData(data3);
				break;
			case "论文":
				ThesisPaper data4 = new ThesisPaper();
				data4.setId(fid);
				data4 = thesisPaperBiz.getDataById(fid);
				data4.setIfPass(true);
				data4.setOpinion(opinion);
				ifSuccess = thesisPaperBiz.saveData(data4);
				break;
				
 		}
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
	
	public void disagreeFile() throws Exception {
		boolean ifSuccess = false;
		switch(type){
			case "任务书":
				Description data = new Description();
				data.setId(fid);
				data = descriptionBiz.getDataById(fid);
				data.setIfPass(false);
				data.setOpinion(opinion);
				ifSuccess = descriptionBiz.saveData(data);
				break;
			case "开题报告":
				Bernal data1 = new Bernal();
				data1.setId(fid);
				data1 = bernalBiz.getDataById(fid);
				data1.setIfPass(false);
				data1.setOpinion(opinion);
				ifSuccess = bernalBiz.saveData(data1);
				break;
			case "中期检查":
				InterimReport data2 = new InterimReport();
				data2.setId(fid);
				data2 = interimReportBiz.getDataById(fid);
				data2.setIfPass(false);
				data2.setOpinion(opinion);
				ifSuccess = interimReportBiz.saveData(data2);
				break;
			case "指导记录":
				GuidanceRecord data3 = new GuidanceRecord();
				data3.setId(fid);
				data3 = guidanceRecordBiz.getDataById(fid);
				data3.setIfPass(false);
				data3.setOpinion(opinion);
				ifSuccess = guidanceRecordBiz.saveData(data3);
				break;
			case "论文":
				ThesisPaper data4 = new ThesisPaper();
				data4.setId(fid);
				data4 = thesisPaperBiz.getDataById(fid);
				data4.setIfPass(false);
				data4.setOpinion(opinion);
				ifSuccess = thesisPaperBiz.saveData(data4);
				break;
				
 		}
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
