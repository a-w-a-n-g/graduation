<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>毕业设计选题-教师</title>

		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<%@include file="/WEB-INF/pages/common.jsp"%>
		<!-- basic styles -->
		<link href="plugins/ace/css/font-awesome.min.css" rel="stylesheet" />
		<!-- ace styles -->
		<link rel="stylesheet" href="plugins/ace/css/ace.min.css" />
		<!-- 自定义js -->
		<script src="plugins/ace/js/sidebar-menu.js"></script>
		<!-- ace scripts -->
		<script src="plugins/ace/js/ace-elements.min.js"></script>
		<script src="plugins/ace/js/ace.min.js"></script>
		
<style type="text/css">
			
body {
	overflow: hidden;
}
.tab-content {
	border: none;
	padding: 10px 0px;
}

#menu_li_0 {
	display: none;
}
.ace-nav>li {
 height: 50px;
 
}
</style>

	</head>

	<body>
		<div class="navbar navbar-default" id="navbar">
			<script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>

			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<a href="#" class="navbar-brand">
						<small>
							<i class="fa fa-envira"></i>
							毕业设计选题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<small>编号：${user }</small>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<small>职称：${title }</small>
						</small>
					</a><!-- /.brand -->
				</div><!-- /.navbar-header -->

				<div class="navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">

						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<!-- <img class="nav-user-photo" src="images/user.jpg" alt="Jason's Photo" /> -->
								<span class="user-info">
									<small>你好,</small>
									${name }
								</span>

								<i class="icon-caret-down"></i>
							</a>

							<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li>
									<a href="javascript:void(0)" onclick="editInfo()">
										<i class="icon-user"></i>
										个人资料
									</a>
								</li>
								<li>
									<a href="javascript:void(0)" onclick="editPwd()">
										<i class="icon-cog"></i>
										修改密码
									</a>
								</li>
								<li class="divider"></li>

								<li>
									<a href="javascript:void(0)" onclick="logout()">
										<i class="icon-off"></i>
										退出
									</a>
								</li>
							</ul>
						</li>
					</ul><!-- /.ace-nav -->
				</div><!-- /.navbar-header -->
			</div><!-- /.container -->
		</div>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>

				<div class="sidebar" id="sidebar">
					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
					</script>

					<ul class="nav nav-list" id="menu">
					
					</ul><!-- /.nav-list -->

					<div class="sidebar-collapse" id="sidebar-collapse">
						<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
					</div>

					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
					</script>
				</div>

				<div class="main-content">
					

					<div class="page-content">
						

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<ul class="nav nav-tabs" role="tablist">
			       					<!--<li class="active"><a href="#Index" role="tab" data-toggle="tab">首页</a></li>-->
			      				</ul>
			      				<div class="tab-content">
			       					<!--<div role="tabpanel" class="tab-pane active" id="Index"></div>-->
			      				</div>
								
								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->

				<!-- /#ace-settings-container -->
			</div><!-- /.main-container-inner -->
		</div><!-- /.main-container -->

		<!-- basic scripts -->



<!-- 第一个model 开始 -->
<div id="setModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keybord="false">
	<div class="modal-dialog" style="width:700px">
		<div class="modal-content">
			<div class="modal-header">
				修改密码
			</div>
			<!-- modal-body 开始 -->
			<div class="modal-body">
				<form id="setForm" class="form-horizontal">
					<input type="hidden" name="id">
					<div class="form-group">
						<label class="col-sm-4 control-label" for="class_name">原密码：</label>
						<div class="col-sm-6">
							<input class="form-control" type="password" id="password1" placeholder="请输入原密码">
						<br>
						</div>
						<label class="col-sm-4 control-label" for="class_name">新密码：</label>
						<div class="col-sm-6">
							<input class="form-control" type="password" id="password" name = "password" placeholder="请输入新密码">
						</div>
						<label class="col-sm-4 control-label" for="class_name">新密码确认：</label>
						<div class="col-sm-6">
							<input class="form-control" type="password" id = "password2" placeholder="请再次输入新密码">
						</div>
					</div>
				</form>	
			</div>
			<!-- modal-body 结束 -->
			<div class="modal-footer">
				<button id="edit_save_btn" type="button" class="btn btn_info" onclick = "editPassword()" style="background-color:#438EB9!important;border-color:#438EB9;">
					<span class="glyphicon glyphicon-ok"></span>保存
				</button>
				<button id="delete_save_btn" type="button" class="btn btn_warning" onclick = "closeMoal()"  style="background-color:#438EB9!important;border-color:#438EB9;">
					<span class="glyphicon glyphicon-remove"></span>关闭
				</button>
			</div>
			
		</div>
	</div>

</div>
<!-- 第一个model 结束 -->

<!-- 第二个model 开始 -->
<div id="infoModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keybord="false">
	<div class="modal-dialog" style="width:700px">
		<div class="modal-content">
			<div class="modal-header">
				修改信息
			</div>
			<!-- modal-body 开始 -->
			<div class="modal-body">
				<form id="infoForm" class="form-horizontal">
					<input type="hidden" name="id">
					<div class="form-group">
						<label class="col-sm-4 control-label" for="class_name">联系号码：</label>
						<div class="col-sm-6">
							<input class="form-control" type="text" id="phone">${phone }</input>
						</div>
						<label class="col-sm-4 control-label" for="class_name">邮箱：</label>
						<div class="col-sm-6">
							<input class="form-control" type="text" id="mail" name = "mail">${mail }</input>
						</div>
					</div>
				</form>	
			</div>
			<!-- modal-body 结束 -->
			<div class="modal-footer">
				<button id="edit_save_btn" type="button" class="btn btn_info" onclick = "editInfo()" style="background-color:#438EB9!important;border-color:#438EB9;">
					<span class="glyphicon glyphicon-ok"></span>保存
				</button>
				<button id="delete_save_btn" type="button" class="btn btn_warning" onclick = "closeInfo()"  style="background-color:#438EB9!important;border-color:#438EB9;">
					<span class="glyphicon glyphicon-remove"></span>关闭
				</button>
			</div>
			
		</div>
	</div>

</div>
<!-- 第二个model 结束 -->












		<!-- inline scripts related to this page -->

		<script type="text/javascript">
			$(window).resize(function() {
			  	$("#sidebar").height($(window).height()-$("#navbar").height());
			  	$("div .tab-pane iframe").height($("#sidebar").height()-60);
			});

			
			
			
			
			$(function () {
				
				
				/* $.post("tMenus/findMenus.htm", {
					//THEME_TYPE : code
				},function(data) {
					$("#sidebar").height($(window).height()-$("#navbar").height()-40);	
					$('#menu').sidebarMenu({
						data:eval('(' + data + ')')
					});
					eval($("#menu_li_0 a").attr("href"));
				}); */
				var data ;
				if(parseInt("${id}")==2){ //管理员
					data=[{
					     id: '000',
					     text: '首页',
					     icon: 'fa fa-home fa-lg',
					     url: 'home.action',
					    }, {
					    id: '02',
					    text: '课题管理',
					    icon: 'fa fa-gg',
					    url: 'manageTopic.action'
					    }, {
					    id: '03',
					    text: '课题操作记录',
					    icon: 'fa fa-gg',
					    url: 'operationRecord.action'
					    }, {
					    id: '04',
					    text: '课题修改审核',
					    icon: 'fa fa-gg',
					    url: 'tchCheck.action'
					    }, {
					    id: '05',
					    text: '学生提交文档审核',
					    icon: 'fa fa-gg',
					    url: 'checkFile.action'},
					    ]	
					
				}
			
				
						
				$("#sidebar").height($(window).height()-$("#navbar").height()-40);	
				   $('#menu').sidebarMenu({
				    data:data
				   }); 
				   eval($("#menu_li_000 a").attr("href"));
				   
				   
			  });
			    
		function logout(){
			bootbox.confirm({
				size:"small",
				title:"提示",
				message:"确认退出吗？",
				callback:function(result){
					if(result){
						
						window.location.href="index.jsp"
					}
				}
			});
			
		}
		
		function editPwd(){
			$('#setModal').modal('show');
		}
		function closeMoal(){
			$('#setModal').modal('hide');
		}  
		function editInfo(){
			$('#infoModal').modal('show');
		}
		function closeInfo(){
			$('#infoModal').modal('hide');
		}  
		
		function editPassword(){
			var password = $('#password').val();
			if(document.getElementById("password1").value != "admin") {
				message.alert("原密码输入错误");
				return;
			}
			if(password == ""){
				message.alert("请输入新密码");
				return;
			}
			if(document.getElementById("password").value != document.getElementById("password2").value) {
				message.alert("两次密码输入不一致");
				return;
			}
			
			$.post("editPsw.action", {password:password} ,function(data) {
				message.alert(data.showInfo);
				
			});
		} 
		
		</script>
</body>
</html>

