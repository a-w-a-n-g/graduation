package com.graduation.struts2.action.teacher;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.graduation.spring.biz.BernalBiz;
import com.graduation.spring.biz.DescriptionBiz;
import com.graduation.spring.biz.GuidanceRecordBiz;
import com.graduation.spring.biz.InterimReportBiz;
import com.graduation.spring.biz.ThesisPaperBiz;
import com.opensymphony.xwork2.ActionSupport;

/**

* @author awang
* @version 1.0
* @date 2020年5月7日 下午8:31:58
* 
*/

@Controller
@Scope("prototype")
public class DownloadFileAction extends ActionSupport implements ServletRequestAware{
	
	private String type;
	private Integer Id;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	// 文件输入流
	private ByteArrayInputStream inputStream;
	// 返回内容类型
	private String contentType;
	// 文件下载时，指定的名称
	private String contentDisposition;

	public ByteArrayInputStream getInputStream() {
	    return inputStream;
	}

	public String getContentType() {
	    return contentType;
	}

	public String getContentDisposition() {
	    return contentDisposition;
	}

	private HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
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
	
	public String downloadFile() {
		String type = request.getParameter("type");
		Integer Id = new Integer(request.getParameter("Id"));
        // 2.获取文件路径
		String savePath = "";
        switch(type) {
        	case "任务书":
        		savePath = descriptionBiz.getDataById(Id).getFilePath();
        		break;
        	case "开题报告":
        		savePath = bernalBiz.getDataById(Id).getFilePath();
        		break;
        	case "中期报告":
        		savePath = interimReportBiz.getDataById(Id).getFilePath();
        		break;
        	case "指导记录":
        		savePath = guidanceRecordBiz.getDataById(Id).getFilePath();
        		break;
        	case "论文":
        		savePath = thesisPaperBiz.getDataById(Id).getFilePath();
        		break;
        }
        String filePath = ServletActionContext.getServletContext().getRealPath("/") + savePath;
		String fileName = savePath.split("/")[3];
		try {
	        
	        // 字节输出流
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();
	        // 3.将文件转换成文件流
	        // 如果文件不存在，会抛出异常
	        FileInputStream fis = new FileInputStream(filePath);
	        // 4.将文件流读取到缓冲区（内存中），目的：提高读取效率
	        InputStream input = new BufferedInputStream(fis);
	        // 5.指定内存空间大小
	        byte[] bt = new byte[1024];
	        int len = 0;
	        // 6.从内存中每次读取1024个字节，放到字节数组bt中，然后将bt中的字节写入到输出流中
	        while ((len = input.read(bt)) > 0) {
	            bos.write(bt, 0, len);
	        }
	        // 7.私有属性赋值
	        // 7.1 字节输入流
	        this.inputStream = new ByteArrayInputStream(bos.toByteArray());
	        // 7.2获取该文件所对应的文件类型
	        this.contentType = request.getSession().getServletContext().getMimeType(fileName);
	        // 7.3指定下载该文件时的文件名称
	        this.contentDisposition = "attachment;filename="+URLEncoder.encode(fileName, "UTF-8");

	        bos.close();
	        input.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return SUCCESS;
	}
	
}
