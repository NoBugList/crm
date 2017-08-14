// 格式化分配状态
function formatState(value) {
	if (value == null) {
		return "未知";
	}else if (value == 0) {
		return "未分配";
	} else {
		return "已分配";
	}
}

// 搜索
function searchSaleChance() {
	var data = {
			customerName: $("#s_customerName").val(),
			overview: $("#s_overview").val(),
			createMan: $("#s_createMan").val(),
			state:$("#s_state").combobox('getValue')
	}
	$("#dg").datagrid('load', data);
}

// 弹出创建窗体
function openSaleChanceAddDialog(){
	$("#dlg").dialog('open').dialog('setTitle', "添加营销机会");
}

// 弹出修改窗体
function openSaleChanceModifyDialog() {
	// 获取选中的行
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length != 1) {
		$.messager.alert("系统提示", "请选择一行进行修改");
		return;
	}
	// 给form表单赋值
	var row = rows[0];
	$("#fm").form('load', row);
	$("#dlg").dialog('open').dialog('setTitle', '修改')
}


//添加或修改
function saveSaleChance() {
	var customerName = $("#customerId").combobox('getText');
	if (customerName == null || $.trim(customerName).length == 0) {
		alert("请选择客户");
		return;
	}
	$("#customerName").val(customerName);
	var id = $("#id").val();
	var url = "add";
	if (id != null) {
		url = "update";
	}
	$("#fm").form('submit', {
		url: url, // 相对路径访问
		onSubmit: function () {
			return $(this).form('validate');
		},
		success: function (data) {
			data = JSON.parse(data); // 转化为json对象 弱对象
			if (data.resultCode == 1) { // 成功
				// 先打印成功
				$.messager.alert('提示', "保存成功！");
				// 置空
				resetValue();
				// 刷新 关闭窗体
				$('#dlg').dialog('close');
				$('#dg').datagrid('reload');
			} else { // 失败
				$.messager.alert("系统提示","保存失败！");
				return;
			}
		}
	})
}
// 删除
function deleteSaleChance() {
	// 获取选中的行
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert("系统提示", "至少选择一行进行删除");
		return;
	}
	// 获取选中行的ID
	var ids = [];
	for(var i =0; i < rows.length; i++) {
		console.log(JSON.stringify(rows[i])); // 将对象转化为json字符串
		ids.push(rows[i].id);
	}
	var content = "您确定要删除这<font color=red>" + rows.length + "</font>条数据吗？";
	1,2,3
	$.messager.confirm("系统提示", content, function(r) {
		if (r) {
			$.post('delete', {ids:ids.join(',')}, function(resp) {
				if(resp.resultCode == 1) { // 删除成功
					$.messager.alert('系统提示', resp.resultMessage);
					$("#dg").datagrid('load'); // 重新刷新数据
				} else if (resp.resultCode == 201){
					$.messager.alert('系统提示', resp.resultMessage);
					window.parent.location.href= '/index';
				} else {
					$.messager.alert('系统提示', resp.resultMessage);
				}
			});
		}
	});
	
}

//打开修改窗体
function openSaleChanceModifyDialog() {
	// 必须选中一条记录
	var selectedRows = $("#dg").datagrid('getSelections');
	if (selectedRows.length != 1) {
		$.messager.alert("提示", "请选择一行进行修改");
		return;
	}
	// 选中行的数据复制给form
	var row = selectedRows[0];
	console.log(JSON.stringify(row));
	$("#fm").form('load', row);
	var createMan = $.cookie('userName');
	$("#createMan").val(createMan);
	$("#dlg").dialog('open').dialog('setTitle', '修改营销机会');
}

// 重置
function resetValue(){
	$("#fm").form('reset');
}

// 关闭弹出框
function closeSaleChanceDialog() {
	// 置空
	resetValue();
	$("#dlg").dialog('close');
}
