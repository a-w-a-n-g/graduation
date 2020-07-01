<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" href="css/login.css" rel="stylesheet" />
<script type="text/javascript" src="js/login.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body onKeyDown="enter()">
<div class="login"><form name="form" action="login" method="post">
<ul><li><center><b><big>WELCOME！</big></b></center></li>
<li><center><i class="icon-user"></i><input id="name" class="user" name="user" type="text" placeholder="请输入用户名"/></li>
<li><center><i class="icon-psw"></i><input id="password" class="user" name="psw" type="password" placeholder="请输入密码"/></center></li>
<li style="line-height:50px;position:relative;left:90px;margin-bottom:20px">验证码：<input id="code" type="text" name="code" style="width:50px" placeholder="输入！"/>
<img id="codeImage" title="看不清，换一张" src="validateCode" onclick="changeImg()" /></li>
<li><center><input class="id" name="id" type="radio" value="1"/>学生&nbsp;
<input class="id" name="id" type="radio" value=2>老师&nbsp;
<input class="id" name="id" type="radio" value=3>管理员&nbsp;</center></li>
<li><center><input class="submit" type="button" value="登录" onclick="check()"/></center></li>
</ul></form></div>
</body>
<script type="text/javascript">
window.onload = function() {
	var codeValidate = '<%=request.getAttribute("codeValidate")%>';
	var user = '<%=request.getAttribute("user")%>';
	var psw = '<%=request.getAttribute("psw")%>';
	var id = '<%=request.getAttribute("id")%>';
	var radios = document.getElementsByName("id");
	if(id == "null") {
		radios[0].checked = "checked";
	}else{
		for(var i = 0; i < radios.length; i++) {
			if(radios[i].value == id) {
				radios[i].checked = "checked";
			}
		}
	}
	if(user != "null") {
		document.getElementById("name").value = user;		
	}
	if(codeValidate == "false"){
		document.getElementById("password").value = psw;
		document.getElementById("code").placeholder="错误";
		document.form.code.focus();
	}else if(psw != "null"){
		document.getElementById("password").placeholder="密码错误";		
		document.form.psw.focus();
	}else{
		document.form.user.focus();
	}
}</script>
</html>
