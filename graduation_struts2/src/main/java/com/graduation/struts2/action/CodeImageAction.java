package com.graduation.struts2.action;

import java.io.ByteArrayInputStream;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.graduation.struts2.validateCode.DrawImage;
import com.opensymphony.xwork2.ActionSupport;

/**

* @author awang
* @version 1.0
* @date 2020年2月25日 下午3:34:10
* 
*/

@Controller
@Scope("prototype")
public class CodeImageAction extends ActionSupport implements SessionAware {
	
	//Struts2中Map类型的session
    private Map<String, Object> session;
    
    //图片流
    private ByteArrayInputStream imageStream;

    public ByteArrayInputStream getImageStream() {
        return imageStream;
    }

    public void setImageStream(ByteArrayInputStream imageStream) {
        this.imageStream = imageStream;
    }

    
    public String execute() throws Exception {
        DrawImage image = new DrawImage();
    	imageStream = image.getImageAsInputStream();
    	String checkCode = image.getCheckCode();
        //放入session中
    	session.put("checkCode", checkCode);
        return SUCCESS;
    }

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}


}
