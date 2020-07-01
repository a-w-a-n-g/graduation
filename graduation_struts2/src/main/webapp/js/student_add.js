$(function () {
	window.alert("aa");
	$("#classNum").combobox({			//从数据库获取下拉框值，现在未完成
		url:'admin/getAllClass.action',
		valueField: "classId",
		textField: "className",
		panelHeight: "auto",
		editable: false, //不允许手动输入
		required : true,
		onLoadSuccess: function () { //数据加载完毕事件
			var data = $("#classNum").combobox("getData"); 
			if (data.length > 0) {
				$("#classNum").combobox("select", data[0].colleageId);
			}
		}
	});
});