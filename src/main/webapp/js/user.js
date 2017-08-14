function searchUser() {
    $("#dg").datagrid('load', {
        "userName":$("#s_userName").val()
    });
}

function openAddDialog() {
    $("#dlg").dialog("open").dialog("setTitle","添加用户信息");
    $("#id").val('');
}

function openModifyDialog() {
    var selectedRows=$("#dg").datagrid("getSelections");
    if(selectedRows.length != 1) {
        $.messager.alert("系统提示","请选择一条要编辑的数据！");
        return;
    }
    var row=selectedRows[0];
    $("#dlg").dialog("open").dialog("setTitle","编辑用户信息");
    $("#fm").form("load",row);
    $("#userId").val(row.id);
}

function saveUser() {
	var id = $("#id").val();
	var url = "add";
	if (id != null && $.trim(id).length > 0 && !isNaN(id)) { // 判断是否为数字
		url = "update";
	}
    $("#fm").form("submit", {
        url: url,
        onSubmit:function() {
            if($("#roleIds").combobox("getValue") == "") {
                $.messager.alert("系统提示", "请选择用户角色！");
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
            }else{
                $.messager.alert("系统提示", result.result);
                return;
            }
        }
    });
}

function resetValue() {
    $("#userName").val("");
    $("#password").val("");
    $("#trueName").val("");
    $("#email").val("");
    $("#phone").val("");
    $("#roleIds").combobox("clear");
    $("#id").val('');
}

function closeDialog(){
    $("#dlg").dialog("close");
    resetValue();
}

function deleteUser(){
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