function openAddDialog() {
    $("#dlg").dialog("open").dialog("setTitle","添加角色信息");
    $("#id").val('');
}

function openModifyDialog() {
    var selectedRows = $("#dg").datagrid("getSelections");
    if(selectedRows.length != 1) {
        $.messager.alert("系统提示","请选择一条要编辑的数据！");
        return;
    }
    var row = selectedRows[0];
    console.log(row)
    $("#fm").form("load", row);
    $("#dlg").dialog("open").dialog("setTitle","编辑角色信息");
}

function saveRole() {
	
	var id = $("#id").val();
	var url = "add";
	if (id != null && $.trim(id).length >0 && !isNaN(id)) { // 确定id是数字
		url = "update";
	}
    $("#fm").form("submit", {
        url: url,
        onSubmit:function() {
            if(isEmpty($("#roleName").val())) {
                $.messager.alert("系统提示", "请输入角色名称！");
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
    $("#roleName").val("");
    $("#roleRemark").val("");
    $("#id").val('');
}

function closeRoleDialog(){
    $("#dlg").dialog("close");
    resetValue();
}

function deleteRoles() {

    var strIds=[];
    var selectedRows = $("#dg").datagrid('getSelections');
    console.log(selectedRows);
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
// 关联权限
function relatePermissions() {
    var selectedRows = $("#dg").datagrid("getSelections");
    if(selectedRows.length != 1) {
        $.messager.alert("系统提示","请选择一条要编辑的数据！");
        return;
    }
    var roleId = selectedRows[0].id;
    var url = ctx + 'module/relate_permission?roleId=' + roleId;
    window.parent.openTab('角色关联权限', url, 'icon-user');
}