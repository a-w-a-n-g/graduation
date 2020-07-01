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
							    <textarea class="form-control" rows="2" name="opinion" underline="true" ></textarea>
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
<!-- 第一个model 结束 -->

<script type="text/javascript">

var operation={
		searchForm:function(){
			$('#dg').bootstrapTable("refresh");		//刷新重新获取数据，即传查询数据到bootstrapTable的后台url
		},
		//弹出详情页面
		showWindow:function(){
			document.getElementsByName('introduction')[0].value = arguments[0];
			document.getElementsByName('requirement')[0].value = arguments[1];
			document.getElementsByName('opinion')[0].value = arguments[2];
			$('#showModal').modal('show');
		},
		//关闭详情页面
		closeShow:function(){
			$('#showModal').modal('hide');
		}
}

$(function(){				//用于获取dg表的数据，columns对应后台url所传来的参数名。也用于search表单的提交，queryParams用于设置传到后台url的参数。
	$('#dg').bootstrapTable({
		url:"getAllModifyTopic.action",
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
		field:'id',
		title:'id',
		width:10,
		align:'center',
		visible:false
	},
	     {title: '操作类型与题目', field: 'title', align: 'center', valign: 'middle',visible:true},
	     {title: '所属学院', field: 'departmentName', align: 'center', valign: 'middle',visible:true},
	     {title: '难度', field: 'difficulty', align: 'center', valign: 'middle',visible:true},
	     {title: '操作申请时间', field: 'modifyTime', align: 'center', valign: 'middle',visible:true},
	     {title: '是否通过审核', field: 'ifPass', align: 'center', valign: 'middle',visible:true},
	     {title: '查看详情', field: 'detail', align: 'center', valign: 'middle',visible:true},
	/* {field:'roomName',title:'教室',width:10,align:'center',visible:true} */
	
	
]];


</script>



</body>
</html>

