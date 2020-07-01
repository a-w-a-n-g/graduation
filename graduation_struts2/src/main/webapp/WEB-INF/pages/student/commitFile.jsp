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
					<button id="add_btn" type="button" class="btn  btn-success btn-md" onclick = "operation.showDescription()">
						<span class="glyphicon"></span> 提交任务书
					</button>
					<button id="add_btn" type="button" class="btn  btn-success btn-md" onclick = "operation.showBernal()">
						<span class="glyphicon"></span> 提交开题报告
					</button>
					<button id="add_btn" type="button" class="btn  btn-success btn-md" onclick = "operation.showInterim()">
						<span class="glyphicon"></span> 提交中期报告
					</button>
					<button id="add_btn" type="button" class="btn  btn-success btn-md" onclick = "operation.showGuidance()">
						<span class="glyphicon"></span> 提交指导记录
					</button>
					<button id="add_btn" type="button" class="btn  btn-success btn-md" onclick = "operation.showPaper()">
						<span class="glyphicon"></span> 提交论文
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

<div id="descriptionModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keybord="false">
	<div class="modal-dialog" style="width:700px">
		<div class="modal-content">
			<div class="modal-header">
				提交任务书
			</div>
			<!-- modal-body 开始 -->
			<div class="modal-body">
				<form id="descriptionForm" class="form-horizontal" >
					<div class="form-group">
						<label class="col-sm-4 control-label"  for="name">上传任务书</label>
						<div class="col-sm-6">
							    <input id="description" type="file"  name="upload" data-show-upload="false" data-show-caption="true"/>
						</div>
					</div>
				</form>	
			</div>
			<!-- modal-body 结束 -->
			<div class="modal-footer">
				<button id="add_save_btn" type="button" class="btn btn_success" onclick = "operation.commitDescription()">
					<span class="glyphicon glyphicon-ok"></span>提交
				</button>
				<button id="delete_save_btn" type="button" class="btn btn_warning" onclick = "operation.closeDescription()">
					<span class="glyphicon glyphicon-remove"></span>关闭
				</button>
			</div>
		</div>
	</div>
</div>

<div id="bernalModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keybord="false">
	<div class="modal-dialog" style="width:700px">
		<div class="modal-content">
			<div class="modal-header">
				提交开题报告
			</div>
			<!-- modal-body 开始 -->
			<div class="modal-body">
				<form id="bernalForm" class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-4 control-label"  for="name">上传开题报告</label>
						<div class="col-sm-6">
							    <input id="bernal" type="file"  name="upload" data-show-upload="false" data-show-caption="true"/>
						</div>
					</div>
				</form>	
			</div>
			<!-- modal-body 结束 -->
			<div class="modal-footer">
				<button id="add_save_btn" type="button" class="btn btn_success" onclick = "operation.commitBernal()">
					<span class="glyphicon glyphicon-ok"></span>提交
				</button>
				<button id="delete_save_btn" type="button" class="btn btn_warning" onclick = "operation.closeBernal()">
					<span class="glyphicon glyphicon-remove"></span>关闭
				</button>
			</div>	
		</div>
	</div>
</div>

<div id="interimModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keybord="false">
	<div class="modal-dialog" style="width:700px">
		<div class="modal-content">
			<div class="modal-header">
				提交中期报告
			</div>
			<!-- modal-body 开始 -->
			<div class="modal-body">
				<form id="interimForm" class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-4 control-label"  for="name">上传中期报告</label>
						<div class="col-sm-6">
							    <input id="interim" type="file"  name="upload" data-show-upload="false" data-show-caption="true"/>
						</div>
					</div>
				</form>	
			</div>
			<!-- modal-body 结束 -->
			<div class="modal-footer">
				<button id="add_save_btn" type="button" class="btn btn_success" onclick = "operation.commitInterim()">
					<span class="glyphicon glyphicon-ok"></span>提交
				</button>
				<button id="delete_save_btn" type="button" class="btn btn_warning" onclick = "operation.closeInterim()">
					<span class="glyphicon glyphicon-remove"></span>关闭
				</button>
			</div>
		</div>
	</div>
</div>

<div id="guidanceModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keybord="false">
	<div class="modal-dialog" style="width:700px">
		<div class="modal-content">
			<div class="modal-header">
				提交指导记录
			</div>
			<!-- modal-body 开始 -->
			<div class="modal-body">
				<form id="guidanceForm" class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-4 control-label"  for="name">上传指导记录</label>
						<div class="col-sm-6">
							    <input id="guidance" type="file"  name="upload" data-show-upload="false" data-show-caption="true"/>
						</div>
					</div>
				</form>	
			</div>
			<!-- modal-body 结束 -->
			<div class="modal-footer">
				<button id="add_save_btn" type="button" class="btn btn_success" onclick = "operation.commitGuidance()">
					<span class="glyphicon glyphicon-ok"></span>提交
				</button>
				<button id="delete_save_btn" type="button" class="btn btn_warning" onclick = "operation.closeGuidance()">
					<span class="glyphicon glyphicon-remove"></span>关闭
				</button>
			</div>
		</div>
	</div>
</div>

<div id="paperModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keybord="false">
	<div class="modal-dialog" style="width:700px">
		<div class="modal-content">
			<div class="modal-header">
				提交论文
			</div>
			<!-- modal-body 开始 -->
			<div class="modal-body">
				<form id="paperForm" class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-4 control-label"  for="name">上传论文</label>
						<div class="col-sm-6">
							    <input id="paper" type="file"  name="upload" data-show-upload="false" data-show-caption="true"/>
						</div>
					</div>
				</form>	
			</div>
			<!-- modal-body 结束 -->
			<div class="modal-footer">
				<button id="add_save_btn" type="button" class="btn btn_success" onclick = "operation.commitPaper()">
					<span class="glyphicon glyphicon-ok"></span>提交
				</button>
				<button id="delete_save_btn" type="button" class="btn btn_warning" onclick = "operation.closePaper()">
					<span class="glyphicon glyphicon-remove"></span>关闭
				</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">

$(function(){

    $("#description").fileinput({
        language : 'zh',
        allowedFileExtensions: ['doc','docx'],//接收的文件后缀
        showPreview :true, //是否显示预览
    });
    $("#bernal").fileinput({
        language : 'zh',
        allowedFileExtensions: ['doc','docx'],//接收的文件后缀
        showPreview :true, //是否显示预览
    });
    $("#interim").fileinput({
        language : 'zh',
        allowedFileExtensions: ['doc','docx'],//接收的文件后缀
        showPreview :true, //是否显示预览
    });
    $("#guidance").fileinput({
        language : 'zh',
        allowedFileExtensions: ['doc','docx'],//接收的文件后缀
        showPreview :true, //是否显示预览
    });
    $("#paper").fileinput({
        language : 'zh',
        allowedFileExtensions: ['doc','docx'],//接收的文件后缀
        showPreview :true, //是否显示预览
    });
	
})

//用户操作
var operation={
	searchForm:function(){
		$('#dg').bootstrapTable("refresh");		//刷新重新获取数据，即传查询数据到bootstrapTable的后台url
	},
	
	showDescription:function() {
		$('#descriptionModal').modal('show');
	},
	
	commitDescription:function() {

		var temp = getFormJson("descriptionForm");
			
		//若是不在ajaxfileupload.js中修改，该函数返回的json数据中会有pre标签，会出错然后进入不到success函数
		$.ajaxFileUpload
		({
	          url: 'commitDescription.action', //用于文件上传的服务器端请求地址
	          secureuri: false, //是否需要安全协议，一般设置为false
	          data:temp,
	          fileElementId: 'description', //文件上传域的ID
	          dataType: 'json', //返回值类型 一般设置为json
	          success: function (data, status)  //服务器成功响应处理函数
	          {
	     	  		$("#dg").bootstrapTable("refresh");
	     			message.alert(data.showInfo);
	     			$('#descriptionForm')[0].reset();
	     			$('#descriptionModal').modal('hide');
	     			
	          },
	          error: function (data, status, e)//服务器响应失败处理函数
	          {
	               alert(e);
	          }
		 })
	},
	
	closeDescription:function() {
		$('#descriptionForm')[0].reset();
		$('#descriptionModal').modal('hide');
	},
	
	showBernal:function() {
		$('#bernalModal').modal('show');
	},
	
	commitBernal:function() {
		var temp = getFormJson("bernalForm");
		
		$.ajaxFileUpload
		({
	          url: 'commitBernal.action', //用于文件上传的服务器端请求地址
	          secureuri: false, //是否需要安全协议，一般设置为false
	          data:temp,
	          fileElementId: 'bernal', //文件上传域的ID
	          dataType: 'json', //返回值类型 一般设置为json
	          success: function (data, status)  //服务器成功响应处理函数
	          {
	     	  		$("#dg").bootstrapTable("refresh");
	     			message.alert(data.showInfo);
	     			$('#bernalForm')[0].reset();
	     			$('#bernalModal').modal('hide');
	     			
	          },
	          error: function (data, status, e)//服务器响应失败处理函数
	          {
	               alert(e);
	          }
		 })
	},
	
	closeBernal:function() {
		$('#bernalForm')[0].reset();
		$('#bernalModal').modal('hide');
	},
	
	showInterim:function() {
		$('#interimModal').modal('show');
	},
	
	commitInterim:function() {
		var temp = getFormJson("interimForm");
		
		$.ajaxFileUpload
		({
	          url: 'commitInterim.action', //用于文件上传的服务器端请求地址
	          secureuri: false, //是否需要安全协议，一般设置为false
	          data:temp,
	          fileElementId: 'interim', //文件上传域的ID
	          dataType: 'json', //返回值类型 一般设置为json
	          success: function (data, status)  //服务器成功响应处理函数
	          {
	     	  		$("#dg").bootstrapTable("refresh");
	     			message.alert(data.showInfo);
	     			$('#interimForm')[0].reset();
	     			$('#interimModal').modal('hide');
	     			
	          },
	          error: function (data, status, e)//服务器响应失败处理函数
	          {
	               alert(e);
	          }
		 })
	},
	
	closeInterim:function() {
		$('#interimForm')[0].reset();
		$('#interimModal').modal('hide');
	},
	
	showGuidance:function() {
		$('#guidanceModal').modal('show');
	},
	
	commitGuidance:function() {
		var temp = getFormJson("guidanceForm");
		
		$.ajaxFileUpload
		({
	          url: 'commitGuidance.action', //用于文件上传的服务器端请求地址
	          secureuri: false, //是否需要安全协议，一般设置为false
	          data:temp,
	          fileElementId: 'guidance', //文件上传域的ID
	          dataType: 'json', //返回值类型 一般设置为json
	          success: function (data, status)  //服务器成功响应处理函数
	          {
	     	  		$("#dg").bootstrapTable("refresh");
	     			message.alert(data.showInfo);
	     			$('#guidanceForm')[0].reset();
	     			$('#guidanceModal').modal('hide');
	     			
	          },
	          error: function (data, status, e)//服务器响应失败处理函数
	          {
	               alert(e);
	          }
		 })
	},
	
	closeGuidance:function() {
		$('#guidanceForm')[0].reset();
		$('#guidanceModal').modal('hide');
	},
	
	showPaper:function() {
		$('#paperModal').modal('show');
	},
	
	commitPaper:function() {
		var temp = getFormJson("paperForm");
		
		$.ajaxFileUpload
		({
	          url: 'commitPaper.action', //用于文件上传的服务器端请求地址
	          secureuri: false, //是否需要安全协议，一般设置为false
	          data:temp,
	          fileElementId: 'paper', //文件上传域的ID
	          dataType: 'json', //返回值类型 一般设置为json
	          success: function (data, status)  //服务器成功响应处理函数
	          {
	     	  		$("#dg").bootstrapTable("refresh");
	     			message.alert(data.showInfo);
	     			$('#paperForm')[0].reset();
	     			$('#paperModal').modal('hide');
	     			
	          },
	          error: function (data, status, e)//服务器响应失败处理函数
	          {
	               alert(e);
	          }
		 })
	},
	
	closePaper:function() {
		$('#paperForm')[0].reset();
		$('#paperModal').modal('hide');
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
		url:"getAllFile.action",
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
	     {title: '文档类型', field: 'type', align: 'center', valign: 'middle',visible:true},
	     {title: '提交时间', field: 'time', align: 'center', valign: 'middle',visible:true},
	     {title: '审核情况', field: 'check', align: 'center', valign: 'middle',visible:true},
	     {title: '审核意见', field: 'opinion', align: 'center', valign: 'middle',visible:true},
	     {title: '下载文件', field: 'file', align: 'center', valign: 'middle',visible:true},
	/* {field:'roomName',title:'教室',width:10,align:'center',visible:true} */
	
	
]];

</script>



</body>
</html>

