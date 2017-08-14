function searchDataDic() {
    $("#dg").datagrid('load', {
        "dataDicName" : $("#s_dataDicName").combobox("getValue"),
        "dataDicValue" : $("#s_dataDicValue").val()
    });
}

function openDataDicAddDialog() {
    $("#dlg").dialog("open").dialog("setTitle","添加数据字典信息");
    $("#dataDicId").val('');
}

function openDataDicModifyDialog() {
    var selectedRows = $("#dg").datagrid("getSelections");
    if(selectedRows.length != 1 ) {
        $.messager.alert("系统提示","请选择一条要编辑的数据！");
        return;
    }
    var row=selectedRows[0];
    $("#dlg").dialog("open").dialog("setTitle","编辑数据字典信息");
    $("#fm").form("load", row);
    $("#dataDicId").val(row.id);
}

function saveDataDic() {
    var dataDicName = $("#dataDicName").combobox("getText");
    var dataDicValue = $("#dataDicValue").val();
    if(isEmpty(dataDicName)) {
        $.messager.alert("系统提示","数据字典名不能为空！");
        return;
    }
    if(isEmpty(dataDicValue)) {
        $.messager.alert("系统提示","数据字典值不能为空！");
        return;
    }
    var dataDicId = $("#dataDicId").val();
    $.post("add_update", {dataDicName:dataDicName, dataDicValue:dataDicValue, id: dataDicId}, function(result) {
        if(result.resultCode == 1) {
            $.messager.alert("系统提示","保存成功！");
            $("#dlg").dialog("close");
            $("#dg").datagrid("reload");
            resetValue();
        }else{
            $.messager.alert("系统提示","保存失败！");
        }
    });
}

function resetValue() {
    $("#dataDicName").combobox("setValue", "");
    $("#dataDicValue").val("");
}

function closeDataDicDialog(){
    $("#dlg").dialog("close");
    resetValue();
}

function deleteDataDic() {
    var selectedRows = $("#dg").datagrid("getSelections");
    if(selectedRows.length == 0) {
        $.messager.alert("系统提示","请选择要删除的数据！");
        return;
    }
    var strIds = [];
    for(var i=0; i<selectedRows.length; i++){
        strIds.push(selectedRows[i].id);
    }
    var ids = strIds.join(",");
    $.messager.confirm("系统提示","您确定要删除这<font color=red>" + selectedRows.length + "</font>条数据吗？",function(r) {
        if(r) {
            $.post("delete",{ids:ids}, function(result) {
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