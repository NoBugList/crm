$(document).ready(function () {
	// 当层级选择变化时进行触发change事件
	$("#grade").combobox({
		'onChange': function(grade) { // grade就是change后的combobox的值
			// 先清空父级的内容
			$("#parentId").combobox('clear'); // 先清空父级菜单
			if (grade == 0) { // 如果是根级的话不需要父级菜单
				$("#parentTr").hide();
			} else {
				$("#parentTr").show();
			}
			// 调用后台获取父级菜单
			$("#parentId").combobox({
				url:'find_module_by_grade?grade=' + (grade - 1),
				valueField:'id',
				textField:'moduleName',
				panelHeight:'auto',
				value: ''
			});
		}
	});
	
});

// 格式化
function formatGrade (value) {
	switch (value) {
	case 0:
		return "根级";
	case 1:
		return "第一级";
	case 2:
		return "第二级";
	default: "未知"
		break;
	}
}

// 弹出添加框
function openAddDialog() {
	$("#dlg").dialog('open');
	$("#dlg").dialog('setTitle', "添加模块");
}

// 修改框
function openModifyDialog() {
	
	// 先要获取修改的行数据
	var selectedRows = $("#dg").datagrid('getSelections');
	if (selectedRows.length != 1) {
		$.messager.alert("提示", "请选择一行进行修改");
		return;
	}
	var row = selectedRows[0];
	
	// 给表单赋值
	$("#fm").form('load', row);
	
	// 打开弹出框
	$("#dlg").dialog('open').dialog('setTitle', "修改模块");
	
	// 展示父级菜单
	if (row.grade != 0) { // 显示父级菜单
		$("#parentTr").show();
		$("#parentId").combobox('clear'); // 先清空父级菜单
		// 调用后台获取父级菜单
		$("#parentId").combobox({
			url:'find_module_by_grade?grade=' + (row.grade - 1),
			valueField:'id',
			textField:'moduleName',
			panelHeight:'auto',
			value: row.parentId
		});
	} else {
		$("#parentId").combobox('setValue', '');
	}
}

// 保存
function saveModule() {
	
	var id = $("#id").val();
	var url = "add"; // id =='';
	if (id != null && $.trim(id).length > 0 && !isNaN(id)) { // id有值而且必须为数字
		url= "update";
	}
	$("#fm").form('submit', {
		url: url,
		onSubmit: function () { // 参数校验
			return $(this).form('validate');
		},
		success: function(resp) {
			var data = JSON.parse(resp);
			if (data.resultCode == 0) {
				$.messager.alert("错误提示", data.resultMessage);
			} else {
				$.messager.alert("提示", data.resultMessage);
				closeDialog();
				$("#dg").datagrid('reload');
			}
		}
	});
}

// 关闭
function closeDialog() {
	$("#dlg").dialog('close');
	$("#fm").form('reset');
}


//删除
function deletes() {
	// 先判断是否选中
	var ids = [];
	// 获取选中行
	var selectedRows = $("#dg").datagrid('getSelections');
	if (selectedRows.length == 0) {
		$.messager.alert("提示", "请选择行进行删除");
		return;
	}
	for (var i = 0; i < selectedRows.length; i++) {
		var row = selectedRows[i];
		ids.push(row.id);
	}
	console.log(ids + "---" + ids.join(','));
	
	$.messager.confirm('确认删除', '您确定要删除<span style="color:red">' + ids.length + "</span>条记录吗？", function(r){
		if (r) {
			// 然后执行ajax请求删除
			$.post('delete', {ids: ids.join(',')}, function(resp) {
				if (resp.resultCode == 0) {
					$.messager.alert("提示", resp.resultMessage);
				} else {
					$.messager.alert("提示", resp.result);
					$("#dg").datagrid('reload');
				}
			});
		}
	});
}