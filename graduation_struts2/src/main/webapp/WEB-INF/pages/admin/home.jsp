<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<title>首页</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<%@include file="/WEB-INF/pages/common.jsp"%>

<link rel="stylesheet" type="text/css" href="css/style-home.css">
<style type="text/css">
			

body {
	
	  	background-color:white !important;;
	    height:100%;
	 
}

</style>

</head>
<body >
<!--cantainer-fluid  开始 -->
<div class = "cantainer">
	<!-- row-fluid  第一个开始 -->
	WELCOME!
	<div class="row">
	<div class="four-grids">
					<%-- <div class="col-md-3 col-md-offset-2 four-grid">
						<div class="four-agileits">
							<div class="icon">
								<i class="glyphicon glyphicon-user" aria-hidden="true"></i>
							</div>
							<div class="four-text">
								<h3>学生人数</h3>
								<h4>${studentCount } </h4>
								
							</div>
							
						</div>
					</div>
					<div class="col-md-3 col-md-offset-2 four-grid">
						<div class="four-agileinfo">
							<div class="icon">
								<i class="glyphicon glyphicon-list-alt" aria-hidden="true"></i>
							</div>
							<div class="four-text">
								<h3>老师人数</h3>
								<h4>${teacherCount } </h4>

							</div>
							
						</div>
					</div> --%>
				<!-- 	<div class="col-md-3 four-grid">
						<div class="four-w3ls">
							<div class="icon">
								<i class="glyphicon glyphicon-folder-open" aria-hidden="true"></i>
							</div>
							<div class="four-text">
								<h3>Projects</h3>
								<h4>12,430</h4>
								
							</div>
							
						</div>
					</div>
					<div class="col-md-3 four-grid">
						<div class="four-wthree">
							<div class="icon">
								<i class="glyphicon glyphicon-briefcase" aria-hidden="true"></i>
							</div>
							<div class="four-text">
								<h3>Old Projects</h3>
								<h4>14,430</h4>
								
							</div>
							
						</div>
					</div> -->
					<div class="clearfix"></div>
				</div>	
	</div>
	<!-- row-fluid  第一个结束 -->
	



</div>
<!--cantainer-fluid  结束 -->






</body>
</html>

