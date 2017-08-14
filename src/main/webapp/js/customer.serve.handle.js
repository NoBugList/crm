function openCustomerServiceProceDialog() {
    var selectedRows=$("#dg").datagrid("getSelections");
    if(selectedRows.length != 1 ) {
        $.messager.alert("系统提示", "请选择一条要处理的客户服务数据！");
        return;
    }
    var row = selectedRows[0];
    $("#dlg").dialog("open").dialog("setTitle","处理客服服务");
    $("#fm").form("load",row);
    $("#serviceProceTime").val(new Date().format("yyyy-MM-dd hh:mm:ss")); //  
    $("#serviceProcePeople").val($.cookie("userName"));
    $("#state").val("已处理");
    $("#id").val(row.id);
}

function saveCustomerServiceProce() {
    $("#fm").form("submit",{
        url: ctx + "customer_serve/add_update",
        onSubmit:function() {
            return $(this).form("validate");
        },
        success:function(result) {
            var result = JSON.parse(result);
            if(result.resultCode == 1) {
                $.messager.alert("系统提示","处理成功！");
                resetValue();
                $("#dlg").dialog("close");
                $("#dg").datagrid("reload");
            }else{
                $.messager.alert("系统提示","处理失败！");
                return;
            }
        }
    });
}

function resetValue() {
    $("#serviceProce").val("");
    $("#serviceProceTime").val("");
}

function closeCustomerProceDialog() {
    $("#dlg").dialog("close");
    resetValue();
}