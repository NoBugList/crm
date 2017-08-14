$(document).ready(function() {
    $("#grade").combobox({ // 层级改变

        // 层级改变时触发
        onChange:function(grade) { // select的change事件
            $('#parentId').combobox('clear');
            if(grade == 0) {
            	 $("#parentIdDiv").hide();
            } else {
            	 $("#parentIdDiv").show();
            }
            $('#parentId').combobox({
                valueField:'id', //值字段
                textField:'moduleName', //显示的字段
                url:'find_by_grade?grade=' + (grade - 1),
                panelHeight:'auto',
                editable:true//不可编辑，只能选择
            });
        }
    });
})
function formatGrade(val, row) {
    switch (val) {
        case 0:
            return "根级";
        case 1:
            return "第一级";
        case 2:
            return "第二级";
    }
}

function openAddDialog() {
    $("#dlg").dialog("open").dialog("setTitle","添加模块信息");
    resetValue();
    openParentCombobox();
}

function openModifyDialog() {
    var selectedRows=$("#dg").datagrid("getSelections");
    if(selectedRows.length != 1) {
        $.messager.alert("系统提示","请选择一条要编辑的数据！");
        return;
    }
    var row = selectedRows[0];
    $("#dlg").dialog("open").dialog("setTitle","编辑模块信息");
    $("#fm").form("load", row);
    $("#id").val(row.id);
    openParentCombobox(row);
}

// 打开层级选择
function openParentCombobox(row) {
    var grade = $("#grade").combobox('getValue');
    if(grade == 0) {
        $("#parentIdDiv").hide();
        return;
    } else { // 修改
        $("#parentIdDiv").show();
        $('#parentId').combobox({
            valueField:'id', //值字段
            textField:'moduleName', //显示的字段
            url:'find_by_grade?grade=' + (grade - 1),
            panelHeight:'auto',
            editable:true,//不可编辑，只能选择
            value:row.parentId
        });
    }

}

function saveModule() {
	var id = $("#id").val();
	var url = "add";
	if (id != null && $.trim(id).length > 0 && !isNaN(id)) { // 判断是否为数字
		url = "update";
	}
    $("#fm").form("submit", {
        url: url,
        onSubmit:function() {
            if(isEmpty($("#moduleName").val())) {
                $.messager.alert("系统提示", "请输入模块名称！");
                return false;
            }
            return $(this).form("validate");
        },
        success:function(result) {
            var result = JSON.parse(result);
            if(result.resultCode == 1) {
                $.messager.alert("系统提示", "保存成功！");
                resetValue();
                $("#dlg").dialog("close");
                $("#dg").datagrid("reload");
            } else {
                $.messager.alert("系统提示", result.resultMessage);
                return;
            }
        }
    });
}

function resetValue() {
    $("#id").val('');
    $("#moduleName").val('');
    $("#moduleStyle").val('');
    $("#url").val('');
    $("#orders").val('');
    $("#optValue").val('');
    $("#grade").combobox('setValue', 0);
}

function closeDialog(){
    $("#dlg").dialog("close");
    resetValue();
}

function deleteModule() {
    var selectedRows = $("#dg").datagrid("getSelections");
    if(selectedRows.length == 0) {
        $.messager.alert("系统提示","请选择要删除的数据！");
        return;
    }
    var strIds=[];
    for(var i=0; i<selectedRows.length; i++) {
        strIds.push(selectedRows[i].id);
    }
    var ids=strIds.join(",");
    $.messager.confirm("系统提示", "您确定要删除这<font color=red>" + selectedRows.length + "</font>条数据吗？", function(r) {
        if(r) {
            $.post("delete",{ids : ids}, function(result) {
                if(result.resultCode == 1) {
                    $.messager.alert("系统提示","数据已成功删除！");
                    $("#dg").datagrid("reload");
                }else{
                    $.messager.alert("系统提示","数据删除失败，请联系系统管理员！");
                }
            });
        }
    });
}
