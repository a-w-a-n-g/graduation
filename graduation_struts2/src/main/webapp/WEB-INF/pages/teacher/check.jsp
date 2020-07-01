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
<!--cantainer-fluid  开始 -->
<div class = "cantainer">
	<!-- row-fluid  第一个开始 -->
	<div class="row">
		<!-- col-md-12  第一个开始 -->
		<div class = "col-md-12">
			<!-- panel panel-default  第一个开始 -->
			<div class="panel panel-default">
				<div class="panel-body">
				<!-- 查询条件开始 -->
				<form id="searchForm" class="form-horizontal">
					<div class="form-group">
						<label class="col-md-1 control-label" for = "selection">关键字</label>
						<div class="col-md-2">
							<input class="form-control" type="text" name="selection" id="selection">
						</div>
						
						<div class="col-sm-6" align="left">
							<button type="button" class="btn btn-md btn-success" onclick = "operation.searchForm()">
								<span class="glyphicon glyphicon-search"></span>查询
							</button>
							<!-- <button type="button" class="btn btn-md btn-info" onclick="">
								<span class="glyphicon glyphicon-download"></span>导出
							</button> -->
						</div>
					</div>
				
				</form>
				<!-- 查询条件结束 -->
				</div>
			</div>
			<!-- panel panel-default  第一个开始 -->
		</div>
		<!-- col-md-12  第一个结束 -->	
	</div>
	<!-- row-fluid  第一个结束 -->
	
	<!-- row-fluid  第二个开始-->
	<div class="row-fluid">
		<div class="panel panel-default">
			<div class="panel-body">
				<!-- toolbar开始 -->
				<div id="toolbar" class="btn-toolbar" role="toolbar">
					<button id="add_btn" type="button" class="btn  btn-success btn-md" onclick = "operation.agreeWindow()">
						<span class="glyphicon"></span> 同意
					</button>
					<button id="edit_btn" type="button" class="btn  btn-info  btn_md" onclick="operation.disagreeWindow()">
						<span class="glyphicon"></span> 退回
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
<div id="agreeModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keybord="false">
	<div class="modal-dialog" style="width:700px">
		<div class="modal-content">
			<div class="modal-header">
				同意操作
			</div>
			<!-- modal-body 开始 -->
			<div class="modal-body">
				<form id="agreeForm" class="form-horizontal">
					<div class="form-group">
						<input class="form-control" type="hidden"  name="mid" underline="true"/>
							<!-- <input class="form-control" type="text" name = "roomName" placeholder="请输入教室名称"> -->
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"  for="opinion">同意原因</label>
						<div class="col-sm-6">
							    <textarea class="form-control" rows="2" name="opinion"></textarea>
							<!-- <input class="form-control" type="text" name = "roomName" placeholder="请输入教室名称"> -->
						</div>
					</div>
				</form>	
			</div>
			<!-- modal-body 结束 -->
			<div class="modal-footer">
				<button id="agree_btn" type="button" class="btn btn_success" onclick = "operation.agreeAgain()">
					<span class="glyphicon glyphicon-ok"></span>确定
				</button>
				<button id="close_btn" type="button" class="btn btn_warning" onclick = "operation.closeAgree()">
					<span class="glyphicon glyphicon-remove"></span>关闭
				</button>
			</div>
			
		</div>
	</div>
</div>
<!-- 第一个model 结束 -->

<!-- 第二个model 开始 -->
<div id="disagreeModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keybord="false">
	<div class="modal-dialog" style="width:700px">
		<div class="modal-content">
			<div class="modal-header">
				退回操作
			</div>
			<!-- modal-body 开始 -->
			<div class="modal-body">
				<form id="disagreeForm" class="form-horizontal">
					<div class="form-group">
						<input class="form-control" type="hidden"  name="mid" underline="true"/>
							<!-- <input class="form-control" type="text" name = "roomName" placeholder="请输入教室名称"> -->
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"  for="opinion">退回原因</label>
						<div class="col-sm-6">
							    <textarea class="form-control" rows="2" name="opinion"></textarea>
							<!-- <input class="form-control" type="text" name = "roomName" placeholder="请输入教室名称"> -->
						</div>
					</div>
				</form>	
			</div>
			<!-- modal-body 结束 -->
			<div class="modal-footer">
				<button id="agree_btn" type="button" class="btn btn_success" onclick = "operation.disagreeAgain()">
					<span class="glyphicon glyphicon-ok"></span>确定
				</button>
				<button id="close_btn" type="button" class="btn btn_warning" onclick = "operation.closeDisagree()">
					<span class="glyphicon glyphicon-remove"></span>关闭
				</button>
			</div>
			
		</div>
	</div>
</div>
<!-- 第二个model 结束 -->

<!-- 第三个model 开始 -->
<div id="showModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keybord="false">
	<div class="modal-dialog" style="width:700px">
		<div class="modal-content">
			<div class="modal-header">
				详细信息
			</div>
			<!-- modal-body 开始 -->
			<div class="modal-body">
				<form id="showForm" class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-4 control-label" for="introduction">简介</label>
						<div class="col-sm-6">
							    <textarea class="form-control" rows="5" name="introduction" underline="true" ></textarea>
							<!-- <input class="form-control" type="text" name = "roomName" placeholder="请输入教室名称"> -->
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="requirement">要求</label>
						<div class="col-sm-6">
							    <textarea class="form-control" rows="8" name="requirement" underline="true" ></textarea>
							<!-- <input class="form-control" type="text" name = "roomName" placeholder="请输入教室名称"> -->
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="opinion">审核意见</label>
						<div class="col-sm-6">
							    <textarea class="form-control" rows="2" name="checkOpinion" underline="true" ></textarea>
							<!-- <input class="form-control" type="text" name = "roomName" placeholder="请输入教室名称"> -->
						</div>
					</div>
				</form>	
			</div>
			<!-- modal-body 结束 -->
			<div class="modal-footer">
				<button id="close_btn" type="button" class="btn btn_warning" onclick = "operation.closeShow()">
					<span class="glyphicon glyphicon-remove"></span>关闭
				</button>
			</div>
			
		</div>
	</div>
</div>
<!-- 第三个model 结束 -->

<!-- 第四个model 开始 -->
<div id="topicModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keybord="false">
	<div class="modal-dialog" style="width:700px">
		<div class="modal-content">
			<div class="modal-header">
				原题信息
			</div>
			<!-- modal-body 开始 -->
			<div class="modal-body">
				<form id="topicForm" class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-4 control-label" for="title">题目</label>
						<div class="col-sm-6">
							   <input class="form-control" type="text"  name="topic.title" underline="true"/>
							<!-- <input class="form-control" type="text" name = "roomName" placeholder="请输入教室名称"> -->
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="departmentName">所属学院</label>
						<div class="col-sm-6">
							   <input class="form-control" type="text"  name="topic.departmentName" underline="true"/>
							<!-- <input class="form-control" type="text" name = "roomName" placeholder="请输入教室名称"> -->
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="difficulty">难度</label>
						<div class="col-sm-6">
							   <input class="form-control" type="text"  name="topic.difficulty" underline="true"/>
							<!-- <input class="form-control" type="text" name = "roomName" placeholder="请输入教室名称"> -->
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="introduction">简介</label>
						<div class="col-sm-6">
							    <textarea class="form-control" rows="5" name="topic.introduction" underline="true" ></textarea>
							<!-- <input class="form-control" type="text" name = "roomName" placeholder="请输入教室名称"> -->
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="requirement">要求</label>
						<div class="col-sm-6">
							    <textarea class="form-control" rows="8" name="topic.requirement" underline="true" ></textarea>
							<!-- <input class="form-control" type="text" name = "roomName" placeholder="请输入教室名称"> -->
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="opinion">开题意见</label>
						<div class="col-sm-6">
							    <textarea class="form-control" rows="2" name="topic.opinion" underline="true" ></textarea>
							<!-- <input class="form-control" type="text" name = "roomName" placeholder="请输入教室名称"> -->
						</div>
					</div>
				</form>	
			</div>
			<!-- modal-body 结束 -->
			<div class="modal-footer">
				<button id="close_btn" type="button" class="btn btn_warning" onclick = "operation.closeTopic()">
					<span class="glyphicon glyphicon-remove"></span>关闭
				</button>
			</div>
			
		</div>
	</div>
</div>
<!-- 第四个model 结束 -->

<script type="text/javascript">

//用户操作
var operation={
	searchForm:function(){
		$('#dg').bootstrapTable("refresh");		//刷新重新获取数据，即传查询数据到bootstrapTable的后台url
	},
	//弹出同意页面
	agreeWindow:function(){
		var rows = $('#dg').bootstrapTable('getSelections');
		if(rows.length == 1  ){
			setForm("#agreeForm",rows[0]);
			if(rows[0].ifPass == "未审核"){
				$('#agreeModal').modal('show');
			}else{
				message.alert("该数据已审核！");
				return;
			}
		}else{
			message.alert("请选择一条要审核的数据！");
			return;
		}
	},
	//进行同意操作
	agreeAgain:function(){
			var temp = getFormJson("agreeForm");
			
			$.post("tchAgreeModify.action", temp ,function(data) {
		//	var obj = eval('('+data+')');
			$("#dg").bootstrapTable("refresh");
			message.alert(data.showInfo);
			$('#agreeForm')[0].reset();
			$('#agreeModal').modal('hide');
			});
	},
	
	//关闭同意页面
	closeAgree:function(){
		$('#agreeModal').modal('hide');
	},
			
	//弹出退回页面
	disagreeWindow:function(){
		var rows = $('#dg').bootstrapTable('getSelections');
		if(rows.length == 1  ){
			setForm("#disagreeForm",rows[0]);
			if(rows[0].ifPass == "未审核"){
				$('#disagreeModal').modal('show');
			}else{
				message.alert("该数据已审核！");
				return;
			}
		}else{
			message.alert("请选择一条要审核的数据！");
			return;
		}
	},
	//进行退回操作
	disagreeAgain:function(){
		var temp = getFormJson("disagreeForm");
		
		$.post("tchDisagreeModify.action", temp ,function(data) {
	//	var obj = eval('('+data+')');
		$("#dg").bootstrapTable("refresh");
		message.alert(data.showInfo);
		$('#disagreeForm')[0].reset();
		$('#disagreeModal').modal('hide');
		});
	},
	
	//关闭退回页面
	closeDisagree:function(){
		$('#disagreeModal').modal('hide');
	},
	
	//弹出详情页面
	showWindow:function(){
		document.getElementsByName('introduction')[0].value = arguments[0];
		document.getElementsByName('requirement')[0].value = arguments[1];
		document.getElementsByName('checkOpinion')[0].value = arguments[2];
		$('#showModal').modal('show');
	},
	
	//关闭详情页面
	closeShow:function(){
		$('#showModal').modal('hide');
	},
	
	//弹出原选题页面
	topicWindow:function(){
		document.getElementsByName('topic.title')[0].value = arguments[0];
		document.getElementsByName('topic.departmentName')[0].value = arguments[1];
		document.getElementsByName('topic.difficulty')[0].value = arguments[2];
		document.getElementsByName('topic.introduction')[0].value = arguments[3];
		document.getElementsByName('topic.requirement')[0].value = arguments[4];
		document.getElementsByName('topic.opinion')[0].value = arguments[5];
		$('#topicModal').modal('show');
	},
	
	//关闭原选题页面
	closeTopic:function(){
		$('#topicModal').modal('hide');
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
		url:"getAllTchCheck.action",
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
	setTime:function(value,row,index){
		
		return formatTimeYMDhms(value);  //转成年月日时分秒
	},
setOpt:function(value,row,index){
		var html ="";
		html +='<button type="button" class="btn btn-md btn-info" onclick = "operation.addScore('+index+')"><span class="glyphicon glyphicon-add"></span>添加成绩</button>'
		return html;
	},		
}
var columns=[[
 	 {
		field:'ck',
		width:'2%',
		checkbox:true,
		align:'center',
		
	},  
	{
		field:'id',
		title:'id',
		width:10,
		align:'center',
		visible:false
	},
	     {title: '题目', field: 'title', align: 'center', valign: 'middle',visible:true},
	     {title: '所属学院', field: 'departmentName', align: 'center', valign: 'middle',visible:true},
	     {title: '难度', field: 'difficulty', align: 'center', valign: 'middle',visible:true},
	     {title: '申请时间', field: 'modifyTime', align: 'center', valign: 'middle',visible:true},
	     {title: '教师审核状态', field: 'ifPassT', align: 'center', valign: 'middle',visible:true},
	     {title: '管理员审核状态', field: 'ifPassA', align: 'center', valign: 'middle',visible:true},
	     {title: '详情', field: 'detail', align: 'center', valign: 'middle',visible:true},
	     {title: '原选题信息', field: 'topic', align: 'center', valign: 'middle',visible:true},
	/* {field:'roomName',title:'教室',width:10,align:'center',visible:true} */
	
	
]];

</script>



</body>
</html>

