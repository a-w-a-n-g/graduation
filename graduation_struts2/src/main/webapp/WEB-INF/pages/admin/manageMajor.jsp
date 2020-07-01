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
					<button id="add_btn" type="button" class="btn  btn-success btn-md" onclick = "operation.addWindow()">
						<span class="glyphicon glyphicon-plus"></span> 新增
					</button>
					<button id="edit_btn" type="button" class="btn  btn-info  btn_md" onclick="operation.editWindow()">
						<span class="glyphicon glyphicon-edit"></span> 编辑
					</button>
					<button id="delete_btn" type="button" class="btn  btn-danger btn-md" onclick="operation.deleteFrom()">
						<span class="glyphicon glyphicon-minus"></span> 删除
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
<div id="setModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keybord="false">
	<div class="modal-dialog" style="width:700px">
		<div class="modal-content">
			<div class="modal-header">
				编辑
			</div>
			<!-- modal-body 开始 -->
			<div class="modal-body">
				<form id="setForm" class="form-horizontal">
					<div class="form-group">
						<div class="col-sm-6">
							    <input class="form-control" type="hidden"  name="major.id" underline="true" />
							<!-- <input class="form-control" type="text" name = "roomName" placeholder="请输入教室名称"> -->
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="department">所属学院</label>
						<div class="col-sm-6">
							    <input class="form-control" type="text"  name="department" underline="true"  placeholder="请输入学院"/>
							<!-- <input class="form-control" type="text" name = "roomName" placeholder="请输入教室名称"> -->
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="name">专业名称</label>
						<div class="col-sm-6">
							    <input class="form-control" type="text"  name="major.name" underline="true"  placeholder="请输入专业"/>
							<!-- <input class="form-control" type="text" name = "roomName" placeholder="请输入教室名称"> -->
						</div>
					</div>				
				</form>	
			</div>
			<!-- modal-body 结束 -->
			<div class="modal-footer">
				<button id="add_save_btn" type="button" class="btn btn_success" onclick = "operation.addForm()">
					<span class="glyphicon glyphicon-ok"></span>保存
				</button>
				<button id="edit_save_btn" type="button" class="btn btn_info" onclick = "operation.editForm()">
					<span class="glyphicon glyphicon-ok"></span>保存
				</button>
				<button id="delete_save_btn" type="button" class="btn btn_warning" onclick = "operation.closeMoal()">
					<span class="glyphicon glyphicon-remove"></span>关闭
				</button>
			</div>
			
		</div>
	</div>



</div>
<!-- 第一个model 结束 -->

<script type="text/javascript">

//用户操作
var operation={
	searchForm:function(){
		$('#dg').bootstrapTable("refresh");		//刷新重新获取数据，即传查询数据到bootstrapTable的后台url
	},
	//弹出添加页面
	addWindow:function(){
		$('#setModal').modal('show');
		formObj.setBtnIsShow("add_save_btn","edit_save_btn",1);

	},
	//添加表单
	addForm:function(){
		$("#setForm").bootstrapValidator('validate');
		if($("#setForm").data("bootstrapValidator").isValid()){
			var temp = getFormJson("setForm");
			
			 $.ajax
	         ({
	                 url: 'insertMajor.action', //用于文件上传的服务器端请求地址
	                 secureuri: false, //是否需要安全协议，一般设置为false
	                 data:temp,
	                 dataType: 'json', //返回值类型 一般设置为json
	                 success: function (data, status)  //服务器成功响应处理函数
	                 {
	                	 
	     			
	     				/*  var obj = eval('('+data+')'); */
	     				$("#dg").bootstrapTable("refresh");
	     				message.alert(data.showInfo);
	     				$('#setForm').data('bootstrapValidator').resetForm(true);
	     				$('#setForm')[0].reset();
	     				$('#setModal').modal('hide');
	     			
	                 },
	                 error: function (data, status, e)//服务器响应失败处理函数
	                 {
	                     alert(e);
	                 }
	             })
			
			
			
			
			
		/* 	$.post("TInfo/insertTInfo.htm", temp ,function(data) {
				var obj = eval('('+data+')');
				$("#dg").bootstrapTable("refresh");
				message.alert(obj.showInfo);
				$('#setForm').data('bootstrapValidator').resetForm(true);
				$('#setForm')[0].reset();
				$('#setModal').modal('hide');
			}); */
		}
		
	},
	//弹出修改页面
	editWindow:function(){
		var rows = $('#dg').bootstrapTable('getSelections');
		if(rows.length == 1  ){
			setForm("#setForm",rows[0]);
			if(rows[0].id != undefined)
			document.getElementsByName('major.id')[0].value=rows[0].id;
			if(rows[0].name != undefined)
			document.getElementsByName('major.name')[0].value=rows[0].name;
			$('#setModal').modal('show');
			formObj.setBtnIsShow("add_save_btn","edit_save_btn",0);
			
		}else{
			message.alert("请选择一条要修改的数据！");
			return;
		}
		
	},
	//修改表单
	editForm:function(){
		var validate = $("#setForm").bootstrapValidator('validate');
		if($("#setForm").data("bootstrapValidator").isValid()){
			var temp = getFormJson("setForm");
			
			 $.ajax
	         ({
	                 url: 'updateMajor.action', 
	                 secureuri: false, //是否需要安全协议，一般设置为false
	                 data:temp,
	                 dataType: 'json', //返回值类型 一般设置为json
	                 success: function (data, status)  //服务器成功响应处理函数
	                 {
	                	 
	     			
	     				/*  var obj = eval('('+data+')'); */
	     				$("#dg").bootstrapTable("refresh");
	     				message.alert(data.showInfo);
	     				$('#setForm').data('bootstrapValidator').resetForm(true);
	     				$('#setForm')[0].reset();
	     				$('#setModal').modal('hide');
	     			
	                 },
	                 error: function (data, status, e)//服务器响应失败处理函数
	                 {
	                     alert(e);
	                 }
	             })
			
			
		/* 	$.post("TInfo/updateTInfo.htm", temp ,function(data) {
				var obj = eval('('+data+')');
				$("#dg").bootstrapTable("refresh");
				message.alert(obj.showInfo);
				$('#setForm').data('bootstrapValidator').resetForm(true);
				$('#setForm')[0].reset();
				$('#setModal').modal('hide');
			}); */
		}
		
	},
	//删除表单
	deleteFrom:function(){
		var rows = $("#dg").bootstrapTable("getSelections");
		if(rows.length == 1  ){
			bootbox.confirm({
				size:"small",
				title:"提示",
				message:"确认删除这条数据吗？",
				callback:function(result){
					if(result){
						$.post("deleteMajor.action", {'major.id':rows[0].id} ,function(data) {
							$("#dg").bootstrapTable("refresh");
							message.alert(data.showInfo);
							
						});
						
					}
				}
			});
			
		}else{
			message.alert("请选择一条要删除的数据！");
			return;
		}
		
	},
	//关闭页面
	closeMoal:function(){
		$('#setForm').data('bootstrapValidator').resetForm(true);
		//$('#setForm').reset();
		$('#setModal').modal('hide');
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
		url:"getAllMajor.action",
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
	     {title: 'ID', field: 'id', align: 'center', valign: 'middle',visible:true},
	     {title: '所属学院', field: 'department', align: 'center', valign: 'middle',visible:true},
	     {title: '专业名称', field: 'name', align: 'center', valign: 'middle',visible:true},
	/* {field:'roomName',title:'教室',width:10,align:'center',visible:true} */
	
	
]];


$(function(){
	$("#setForm").bootstrapValidator({
		live:'disabled',
		excluded:[':disabled',':hidden',':not(:visible)'],
		fields:{
				department:{
					validators:{
						 notEmpty: {
							message:"学院不能为空!",
						}
					}
					
				},
			    
				'major.name':{
					validators:{
						 notEmpty: {
							message:"专业名不能为空!",
						}
					}
					
				},			
		}
		
	});
	
	
	
})





</script>



</body>
</html>

