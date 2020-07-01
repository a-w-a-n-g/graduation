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
					<button id="add_btn" type="button" class="btn  btn-success btn-md" onclick = "operation.disagreeWindow()">
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

<div id="agreeModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keybord="false">
	<div class="modal-dialog" style="width:700px">
		<div class="modal-content">
			<div class="modal-header">
				同意操作
			</div>
			<!-- modal-body 开始 -->
			<div class="modal-body">
				<form id="agreeForm" class="form-horizontal" >
					<div class="form-group">
						<div class="col-sm-6">
							<input class="form-control" type="hidden"  name="fid" underline="true" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"  for="type">文件类型</label>
						<div class="col-sm-6">
							    <input type="text" class="form-control" name="type" underline="true" />
							<!-- <input class="form-control" type="text" name = "roomName" placeholder="请输入教室名称"> -->
						</div>
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
				<button id="add_save_btn" type="button" class="btn btn_success" onclick = "operation.agreeAgain()">
					<span class="glyphicon glyphicon-ok"></span>提交
				</button>
				<button id="delete_save_btn" type="button" class="btn btn_warning" onclick = "operation.closeAgree()">
					<span class="glyphicon glyphicon-remove"></span>关闭
				</button>
			</div>
		</div>
	</div>
</div>

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
						<div class="col-sm-6">
							<input class="form-control" type="hidden"  name="fid" underline="true" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"  for="type">文件类型</label>
						<div class="col-sm-6">
							    <input class="form-control" type="text"  name="type" underline="true" />
						</div>
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
				<button id="add_save_btn" type="button" class="btn btn_success" onclick = "operation.disagreeAgain()">
					<span class="glyphicon glyphicon-ok"></span>提交
				</button>
				<button id="delete_save_btn" type="button" class="btn btn_warning" onclick = "operation.closeDisagree()">
					<span class="glyphicon glyphicon-remove"></span>关闭
				</button>
			</div>	
		</div>
	</div>
</div>

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
			if(rows[0].check == "未审核"){
				document.getElementsByName("type")[0].value = rows[0].type;
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
			
			$.post("agreeFile.action", temp ,function(data) {
		//	var obj = eval('('+data+')');
			$("#dg").bootstrapTable("refresh");
			message.alert(data.showInfo);
			$('#agreeForm')[0].reset();
			$('#agreeModal').modal('hide');
			});
	},
	
	//关闭同意页面
	closeAgree:function(){
		$('#agreeForm')[0].reset();
		$('#agreeModal').modal('hide');
	},
	
	//弹出退回页面
	disagreeWindow:function(){
		var rows = $('#dg').bootstrapTable('getSelections');
		if(rows.length == 1  ){
			if(rows[0].check == "未审核"){
				document.getElementsByName("type")[1].value = rows[0].type;
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
		
		$.post("disagreeFile.action", temp ,function(data) {
	//	var obj = eval('('+data+')');
		$("#dg").bootstrapTable("refresh");
		message.alert(data.showInfo);
		$('#disagreeForm')[0].reset();
		$('#disagreeModal').modal('hide');
		});
	},
	
	//关闭退回页面
	closeDisagree:function(){
		$('#disagreeForm')[0].reset();
		$('#disagreeModal').modal('hide');
	},
	
	//导出
	exportFile:function(){
		
		
	},
	//审核
	audit:function(){
		
		
	}
}

function addFunctionAlty(value, row, index) {
	       return [
	       '<button id="bind" type="button" class="btn btn-default">下载文件</button>'
	       ].join('');
	     } 
	window.operateEvents = {
	        'click #bind': function (e, value, row, index) {
	           alert(row.qxxh);
	           $("#upload").modal('show');
	         }, 'click #unbind': function (e, value, row, index) {
	           alert(row.qxxh);
	           $("#upload").modal('show');
	         }
	       };

$(function(){				//用于获取dg表的数据，columns对应后台url所传来的参数名。也用于search表单的提交，queryParams用于设置传到后台url的参数。
	$('#dg').bootstrapTable({
		url:"getTchCheckFile.action",
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
	     {title: '文档类型', field: 'type', align: 'center', valign: 'middle',visible:true},
	     {title: '学生信息', field: 'student', align: 'center', valign: 'middle',visible:true},
	     {title: '提交时间', field: 'time', align: 'center', valign: 'middle',visible:true},
	     {title: '审核情况', field: 'check', align: 'center', valign: 'middle',visible:true},
	     {title: '审核意见', field: 'opinion', align: 'center', valign: 'middle',visible:true},
	     {title: '下载文件', field: 'file', align: 'center', valign: 'middle',visible:true},  
	
	
]];

</script>



</body>
</html>

