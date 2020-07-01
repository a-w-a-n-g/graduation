<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<title></title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<%@include file="/WEB-INF/pages/common.jsp"%>


<style type="text/css">
			
body {
	
	background-color:#e6e8ea;
	padding-right:0px !important;
	    background-color: white;
	    height:100%;
}
.modal{
	overflow:auto !important;
	padding-right:0px !important;
}


</style>

</head>
<body >

	
	<!-- row-fluid  第二个开始-->
	<div class="row-fluid">
		<div class="panel panel-default">
			<div class="panel-body">
				<!-- toolbar开始 -->
				<div id="toolbar" class="btn-toolbar" role="toolbar">
					<button id="add_btn" type="button" class="btn  btn-success btn-md" onclick = "operation.modify()">
						<span class="glyphicon"></span> 申请修改选题
					</button>
				</div>
				<!-- toolbar结束 -->
				<table id="dg" ></table>
			</div>
		</div>
	</div>
<!-- row-fluid  第二个结束-->


</div>
<!--cantainer-fluid  结束 -->

<!-- 第一个model 开始 -->
<div id="showModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keybord="false">
	<div class="modal-dialog" style="width:700px">
		<div class="modal-content">
			<div class="modal-header">
				课题详情
			</div>
			<!-- modal-body 开始 -->
			<div class="modal-body">
				<form id="showForm" class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-4 control-label"  for="introduction">简介</label>
						<div class="col-sm-6">
							    <textarea class="form-control" rows="5" name="introduction"></textarea>
							<!-- <input class="form-control" type="text" name = "roomName" placeholder="请输入教室名称"> -->
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="requirement">要求</label>
						<div class="col-sm-6">
							    <textarea class="form-control" rows="8" name="requirement"></textarea>
							<!-- <input class="form-control" type="text" name = "roomName" placeholder="请输入教室名称"> -->
						</div>
					</div>
				</form>	
			</div>
			<!-- modal-body 结束 -->
			<div class="modal-footer">
				<button id="delete_save_btn" type="button" class="btn btn_warning" onclick = "operation.close()">
					<span class="glyphicon glyphicon-remove"></span>关闭
				</button>
			</div>
			
		</div>
	</div>



</div>
<!-- 第一个model 结束 -->

<!-- 第一个model 开始 -->
<div id="setModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keybord="false">
	<div class="modal-dialog" style="width:700px">
		<div class="modal-content">
			<div class="modal-header">
				信息输入
			</div>
			<!-- modal-body 开始 -->
			<div class="modal-body">
				<form id="setForm" class="form-horizontal">
					<div class="form-group">
						<input class="form-control" type="hidden"  name="topic.id" underline="true"/>
							<!-- <input class="form-control" type="text" name = "roomName" placeholder="请输入教室名称"> -->
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="title">题目</label>
						<div class="col-sm-6">
							    <input class="form-control" type="text"  name="topic.title" underline="true"  placeholder="请输入题目"/>
							<!-- <input class="form-control" type="text" name = "roomName" placeholder="请输入教室名称"> -->
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="difficulty">难度</label>
						<div class="col-sm-6">
							   <input class="form-control" type="text"  name="topic.difficulty" underline="true" placeholder="请输入难度"/>
							<!-- <input class="form-control" type="text" name = "roomName" placeholder="请输入教室名称"> -->
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="introduction">简介</label>
						<div class="col-sm-6">
							    <textarea class="form-control" rows="5" name="topic.introduction" underline="true"  placeholder="请输入简介"></textarea>
							<!-- <input class="form-control" type="text" name = "roomName" placeholder="请输入教室名称"> -->
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="requirement">要求</label>
						<div class="col-sm-6">
							    <textarea class="form-control" rows="8" name="topic.requirement" underline="true"  placeholder="请输入要求"></textarea>
							<!-- <input class="form-control" type="text" name = "roomName" placeholder="请输入教室名称"> -->
						</div>
					</div>
					
				</form>	
			</div>
			<!-- modal-body 结束 -->
			<div class="modal-footer">
				<button id="add_save_btn" type="button" class="btn btn_success" onclick = "operation.addForm()">
					<span class="glyphicon glyphicon-ok"></span>提交申请
				</button>
				<button id="delete_save_btn" type="button" class="btn btn_warning" onclick = "operation.closeMoal()">
					<span class="glyphicon glyphicon-remove"></span>关闭
				</button>
			</div>
			
		</div>
	</div>

<script type="text/javascript">

//用户操作
var operation={
	searchForm:function(){
		$('#dg').bootstrapTable("refresh");		//刷新重新获取数据，即传查询数据到bootstrapTable的后台url
	},
	modify:function(){
		$('#setModal').modal('show');		
	},
	closeMoal:function(){
		$('#setModal').modal('hide');				
	},
	showWindow:function(){
		document.getElementsByName('introduction')[0].value = arguments[0];
		document.getElementsByName('requirement')[0].value = arguments[1];
		$('#showModal').modal('show');
	},
	//关闭详情页面
	close:function(){
		$('#showModal').modal('hide');
	},
	//导出
	exportFile:function(){
		
		
	},
	//审核
	audit:function(){
		
		
	}
}

$(function(){				//用于获取dg表的数据，columns对应后台url所传来的参数名。也用于search表单的提交，queryParams用于设置传到后台url的参数。
	$('#dg').bootstrapTable({
		url:"getSelectedTopic.action",
		sidePagination:"client",		//之前这里设为server就显示不出数据，这里参数值不同json对象不同
		queryParams:function(params) {
			var temp = {
					rows : params.limit,
					page : params.offset,
					selection : $("#selection").val()
			}
			return temp;
		},
		method: "get",			//之前这里设为post后台无法通过request获取queryParams里面的属性
        contentType:"application/json;charset=utf-8",
		pagination:true,
		pageNumber:1,
		pageSize:10,
		pageList:[10,25,50,100],
		//search:true,
		clickToSelect:true,
		singleSelect:true,
		toolbar:'#toolbar',			//配置工具栏，添加、编辑、删除等。
		columns:columns,
	})
	
});


//表格格式化
var fmt = {
	queryParams:function(params){
		var temp = getFormJson("searchForm");
		temp.rows = params.limit;
		temp.page = params.offset;
		return temp;
	},	
}
var columns=[[
 	  
	{
		field:'id',
		title:'id',
		width:10,
		align:'center',
		visible:false
	},
	     {title: 'ID', field: 'id', height:200, align: 'center', valign: 'middle',visible:true},
	     {title: '题目', field: 'title', align: 'center', valign: 'middle',visible:true},
	     {title: '所属学院', field: 'department', align: 'center', valign: 'middle',visible:true},
	     {title: '指导教师', field: 'teacher', align: 'center', valign: 'middle',visible:true},
	     {title: '难度', field: 'difficulty', align: 'center', valign: 'middle',visible:true},
	     {title: '详情', field: 'detail', align: 'center', valign: 'middle',visible:true},
]];

</script>



</body>
</html>

