package com.graduation.struts2.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**

* @author awang
* @version 1.0
* @date 2020年2月27日 下午8:06:46
* 
*/

//  用于检测是否登录，还没完善好，暂时不使用
public class LoginFilter implements Filter {
	private String excludedPage;
	private String[] excludedPages;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		excludedPage = filterConfig.getInitParameter("ignores");//此处的ignores就是在web.xml定义的ignores参数。
        if (excludedPage != null && excludedPage.length() > 0){
            excludedPages = excludedPage.split(",");
        }

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse rsp = (HttpServletResponse)response;
//		System.out.println("ServletPath:" + req.getServletPath());
//		System.out.println("ContextPaht:" + req.getContextPath());
//		System.out.println("RequestURI:" + req.getRequestURI());
//		System.out.println("RequestURL:" + req.getRequestURL());
		// 定义表示变量 并验证用户请求URL 是否包含不过滤路径
        boolean ifIgnore = false;
        if(req.getServletPath().equals("")) {
        	ifIgnore = true;
        }else{
        	for (String page:excludedPages) {
        		if (req.getServletPath().equals(page)){
        			ifIgnore = true;
        		}
        	}        	
        }
        if(ifIgnore) {
        	chain.doFilter(req, rsp);
        }else{
        	HttpSession session = req.getSession();
        	if(session.getAttribute("ifLogin") == null || session.getAttribute("ifLogin") == "0") {
        		rsp.sendRedirect("http://localhost:8080/graduation_struts2/");
        		return;
        	}else{
        		chain.doFilter(req, rsp);
        	}
        }

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
