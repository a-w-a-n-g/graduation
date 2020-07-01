package com.graduation.struts2.action.student;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.graduation.hibernate.entity.Bernal;
import com.graduation.hibernate.entity.ClassNum;
import com.graduation.hibernate.entity.Description;
import com.graduation.hibernate.entity.GuidanceRecord;
import com.graduation.hibernate.entity.InterimReport;
import com.graduation.hibernate.entity.ThesisPaper;
import com.graduation.spring.biz.BernalBiz;
import com.graduation.spring.biz.DescriptionBiz;
import com.graduation.spring.biz.GuidanceRecordBiz;
import com.graduation.spring.biz.InterimReportBiz;
import com.graduation.spring.biz.StudentBiz;
import com.graduation.spring.biz.ThesisPaperBiz;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**

* @author awang
* @version 1.0
* @date 2020年5月6日 下午6:51:16
* 
*/

@Controller
@Scope("prototype")
public class CommitFileAction extends ActionSupport implements ServletRequestAware{
	
	private File upload;//定义一个File ,变量名要与jsp中的input标签的name一致
    private String uploadContentType;//上传文件的mimeType类型
    private String uploadFileName;//上传文件的名称


	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	private HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	@Resource
	private StudentBiz studentBiz;
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
	
	public void getAllFile() throws Exception {
		ActionContext ac = ActionContext.getContext();
		Integer topicId = studentBiz.getStudentById(new Long(ac.getSession().get("user").toString())).getTopic().getId();
		String selection = request.getParameter("selection");
		List<Description> descriptionList;
		List<Bernal> bernalList;
		List<InterimReport> interimReportList;
		List<GuidanceRecord> guidanceRecordList;
		List<ThesisPaper> thesisPaperList;
		if(!selection.trim().equals("")) {
			descriptionList = descriptionBiz.getDataByTopicId(topicId);			
			bernalList = bernalBiz.getDataByTopicId(topicId);			
			interimReportList = interimReportBiz.getData(topicId);			
			guidanceRecordList = guidanceRecordBiz.getDataByTopicId(topicId);			
			thesisPaperList = thesisPaperBiz.getDataByTopicId(topicId);			
		}else{
			descriptionList = descriptionBiz.getDataByTopicId(topicId);			
			bernalList = bernalBiz.getDataByTopicId(topicId);			
			interimReportList = interimReportBiz.getData(topicId);			
			guidanceRecordList = guidanceRecordBiz.getDataByTopicId(topicId);			
			thesisPaperList = thesisPaperBiz.getDataByTopicId(topicId);
		}
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象 
		JSONArray jsonArray = new JSONArray();
		
		//获取论文数据
		for(ThesisPaper data:thesisPaperList) {
			JSONObject json = new JSONObject();
			json.accumulate("type", "论文");
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
		
		//获取指导记录数据
		for(GuidanceRecord data:guidanceRecordList) {
			JSONObject json = new JSONObject();
			json.accumulate("type", "指导记录");
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
					"&&type=指导记录 " + "'>下载文件</>");
			jsonArray.put(json);
		}
		
		//获取中期报告记录
		for(InterimReport data:interimReportList) {
			JSONObject json = new JSONObject();
			json.accumulate("type", "中期报告");
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
					"&&type=中期报告 " + "'>下载文件</>");
			jsonArray.put(json);
		}
		
		//获取开题报告数据
		for(Bernal data:bernalList) {
			JSONObject json = new JSONObject();
			json.accumulate("type", "开题报告");
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
					"&&type=开题报告 " + "'>下载文件</>");
			jsonArray.put(json);
		}
		
		//获取任务书数据
		for(Description data:descriptionList) {
			JSONObject json = new JSONObject();
			json.accumulate("type", "任务书");
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
					"&&type=任务书 " + "'>下载文件</>");
			jsonArray.put(json);
		}

		out.println(jsonArray.toString());
		out.flush();
		out.close();
	}
	
	public void commitDescription() throws Exception {
		ActionContext ac = ActionContext.getContext();
		boolean ifSuccess = false;
		String mes;
		Integer topicId = studentBiz.getStudentById(new Long(ac.getSession().get("user").toString())).getTopic().getId();
		// 判断文件是否为空  
		if(upload == null) {
			mes = "提交文件不能为空！";
		}else{
			if(descriptionBiz.getDataByTopicId(topicId).size()>0 && descriptionBiz.getDataByTopicId(topicId).get(0).getIfPass() == null) {
				mes = "提交失败，已提交且正在审核中！";
			}else if(descriptionBiz.getDataByTopicId(topicId).size()>0 && descriptionBiz.getDataByTopicId(topicId).get(0).getIfPass()) {
				mes = "提交失败，已提交且已经通过审核！";
			}else{
				try {  
					// 文件保存路径  
					String savePath = "uploadFiles/description/" + ac.getSession().get("user");
					String filePath = ServletActionContext.getServletContext().getRealPath("/") + savePath;  
					Description data = new Description();
					data.setTopicId(studentBiz.getStudentById(new Long(ac.getSession().get("user").toString())).getTopic().getId());
					data.setFilePath(savePath + "/" + uploadFileName);
					data.setCommitTime(new Date(System.currentTimeMillis()));
					// 转存文件  
					File save = new File(filePath,uploadFileName);
					//完成文件上传的操作
					FileUtils.copyFile(upload, save);
					ifSuccess = descriptionBiz.insertData(data);
				} catch (Exception e) {  
					e.printStackTrace();  
				}  			
				if(ifSuccess) {
					mes = "提交成功！";
				}else{
					mes = "提交失败！";
				}
			}
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
	
	public void commitBernal() throws Exception {
		ActionContext ac = ActionContext.getContext();
		boolean ifSuccess = false;
		String mes;
		Integer topicId = studentBiz.getStudentById(new Long(ac.getSession().get("user").toString())).getTopic().getId();
		if(bernalBiz.getDataByTopicId(topicId).size()>0 && bernalBiz.getDataByTopicId(topicId).get(0).getIfPass() == null) {
			mes = "提交失败，已提交且正在审核中！";
		}else if(bernalBiz.getDataByTopicId(topicId).size()>0 && bernalBiz.getDataByTopicId(topicId).get(0).getIfPass()) {
			mes = "提交失败，已提交且已经通过审核！";
		}else{
			// 判断文件是否为空  
			if(upload != null) {
				try {  
					// 文件保存路径  
					String savePath = "uploadFiles/bernal/" + ac.getSession().get("user");
					String filePath = ServletActionContext.getServletContext().getRealPath("/") + savePath;  
					Bernal data = new Bernal();
					data.setTopicId(studentBiz.getStudentById(new Long(ac.getSession().get("user").toString())).getTopic().getId());
					data.setFilePath(savePath + "/" + uploadFileName);
					data.setCommitTime(new Date(System.currentTimeMillis()));
					// 转存文件  
					File save = new File(filePath,uploadFileName);
					//完成文件上传的操作
					FileUtils.copyFile(upload, save);
					ifSuccess = bernalBiz.insertData(data);
				} catch (Exception e) {  
					e.printStackTrace();  
				}  			
			}
			if(ifSuccess) {
				mes = "提交成功！";
			}else{
				mes = "提交失败！";
			}
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
	
	public void commitInterim() throws Exception {
		ActionContext ac = ActionContext.getContext();
		boolean ifSuccess = false;
		String mes;
		Integer topicId = studentBiz.getStudentById(new Long(ac.getSession().get("user").toString())).getTopic().getId();
		if(interimReportBiz.getData(topicId).size()>0 && interimReportBiz.getData(topicId).get(0).getIfPass() == null) {
			mes = "提交失败，已提交且正在审核中！";
		}else if(interimReportBiz.getData(topicId).size()>0 && interimReportBiz.getData(topicId).get(0).getIfPass()) {
			mes = "提交失败，已提交且已经通过审核！";
		}else{
			// 判断文件是否为空  
			if(upload != null) {
				try {  
					// 文件保存路径  
					String savePath = "uploadFiles/interim/" + ac.getSession().get("user");
					String filePath = ServletActionContext.getServletContext().getRealPath("/") + savePath;  
					InterimReport data = new InterimReport();
					data.setTopicId(studentBiz.getStudentById(new Long(ac.getSession().get("user").toString())).getTopic().getId());
					data.setFilePath(savePath + "/" + uploadFileName);
					data.setCommitTime(new Date(System.currentTimeMillis()));
					// 转存文件  
					File save = new File(filePath,uploadFileName);
					//完成文件上传的操作
					FileUtils.copyFile(upload, save);
					ifSuccess = interimReportBiz.insertData(data);
				} catch (Exception e) {  
					e.printStackTrace();  
				}  			
			}
			if(ifSuccess) {
				mes = "提交成功！";
			}else{
				mes = "提交失败！";
			}
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
	
	public void commitGuidance() throws Exception {
		ActionContext ac = ActionContext.getContext();
		boolean ifSuccess = false;
		String mes;
		Integer topicId = studentBiz.getStudentById(new Long(ac.getSession().get("user").toString())).getTopic().getId();
		if(guidanceRecordBiz.getDataByTopicId(topicId).size()>0 && guidanceRecordBiz.getDataByTopicId(topicId).get(0).getIfPass() == null) {
			mes = "提交失败，已提交且正在审核中！";
		}else if(guidanceRecordBiz.getDataByTopicId(topicId).size()>0 && guidanceRecordBiz.getDataByTopicId(topicId).get(0).getIfPass()) {
			mes = "提交失败，已提交且已经通过审核！";
		}else{
			// 判断文件是否为空  
			if(upload != null) {
				try {  
					// 文件保存路径  
					String savePath = "uploadFiles/guidance/" + ac.getSession().get("user");
					String filePath = ServletActionContext.getServletContext().getRealPath("/") + savePath;  
					GuidanceRecord data = new GuidanceRecord();
					data.setTopicId(studentBiz.getStudentById(new Long(ac.getSession().get("user").toString())).getTopic().getId());
					data.setFilePath(savePath + "/" + uploadFileName);
					data.setCommitTime(new Date(System.currentTimeMillis()));
					// 转存文件  
					File save = new File(filePath,uploadFileName);
					//完成文件上传的操作
					FileUtils.copyFile(upload, save);
					ifSuccess = guidanceRecordBiz.insertData(data);
				} catch (Exception e) {  
					e.printStackTrace();  
				}  			
			}
			if(ifSuccess) {
				mes = "提交成功！";
			}else{
				mes = "提交失败！";
			}
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
	
	public void commitPaper() throws Exception {
		ActionContext ac = ActionContext.getContext();
		boolean ifSuccess = false;
		String mes;
		Integer topicId = studentBiz.getStudentById(new Long(ac.getSession().get("user").toString())).getTopic().getId();
		if(thesisPaperBiz.getDataByTopicId(topicId).size()>0 && thesisPaperBiz.getDataByTopicId(topicId).get(0).getIfPass() == null) {
			mes = "提交失败，已提交且正在审核中！";
		}else if(thesisPaperBiz.getDataByTopicId(topicId).size()>0 && thesisPaperBiz.getDataByTopicId(topicId).get(0).getIfPass()) {
			mes = "提交失败，已提交且已经通过审核！";
		}else{
			// 判断文件是否为空  
			if(upload != null) {
				try {  
					// 文件保存路径  
					String savePath = "uploadFiles/paper/" + ac.getSession().get("user");
					String filePath = ServletActionContext.getServletContext().getRealPath("/") + savePath;  
					ThesisPaper data = new ThesisPaper();
					data.setTopicId(studentBiz.getStudentById(new Long(ac.getSession().get("user").toString())).getTopic().getId());
					data.setFilePath(savePath + "/" + uploadFileName);
					data.setCommitTime(new Date(System.currentTimeMillis()));
					// 转存文件  
					File save = new File(filePath,uploadFileName);
					//完成文件上传的操作
					FileUtils.copyFile(upload, save);
					ifSuccess = thesisPaperBiz.insertData(data);
				} catch (Exception e) {  
					e.printStackTrace();  
				}  			
			}
			if(ifSuccess) {
				mes = "提交成功！";
			}else{
				mes = "提交失败！";
			}
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
