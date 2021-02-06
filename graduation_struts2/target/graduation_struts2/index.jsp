<%@ page language="java" import="java.util.*,java.sql.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" href="css/index.css" rel="stylesheet" />
<script type="text/javascript" src="js/index.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body onKeyDown="enter()">
<div class="login"><form name="form" action="login" method="post">
<ul><li><center><b><big>WELCOME！</big></b></center></li>
<li><center><i class="icon-user"></i><input id="name" class="user" name="user" type="text" autofocus placeholder="请输入用户名"/></li>
<li><center><i class="icon-psw"></i><input id="password" class="user" name="psw" type="password" placeholder="请输入密码"/></center></li>
<li><center>验证码：<input type="text" name="validateCode">
<img alt="看不清，换一张" src="${pageContext.request.contextPath}/servlet/DrawImage" onclick="changeImg()"></center></li>
<li><center><input class="id" name="id" type="radio" value="1" checked/>学生&nbsp;
<input class="id" name="id" type="radio" value=2>老师&nbsp;
<input class="id" name="id" type="radio" value=3>管理员&nbsp;</center></li>
<li><center><input class="submit" type="button" value="登录" onclick="check()"/></center></li>
</ul></form></div>
</body>
</html>
