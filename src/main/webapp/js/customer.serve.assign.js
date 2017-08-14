function openCustomerServiceAssignDialog() {
    var selectedRows = $("#dg").datagrid("getSelections");
    if(selectedRows.length != 1) {
        $.messager.alert("系统提示","请选择一条要分配的客户服务数据！");
        return;
    }
    var row=selectedRows[0];
    $("#dlg").dialog("open").dialog("setTitle", "分配客服服务");
    $("#fm").form("load", row);
    $("#state").val("已分配");
    $("#assignTime").val(new Date().format("yyyy-MM-dd hh:mm:ss")); // 可以后台处理
    $("#id").val(row.id);
}

function saveCustomerService(){
    $("#fm").form("submit",{
        url: ctx + "customer_serve/add_update",
        onSubmit:function() {
            return $(this).form("validate");
        },
        success:function(result) {
            var result = JSON.parse(result);
            if(result.resultCode == 1) {
                $.messager.alert("系统提示","分配成功！");
                resetValue();
                $("#dlg").dialog("close");
                $("#dg").datagrid("reload");
            } else {
                $.messager.alert("系统提示","分配失败！");
                return;
            }
        }
    });
}

function resetValue(){
    $("#assigner").combobox("setValue","");
}

function closeCustomerDialog(){
    $("#dlg").dialog("close");
    resetValue();
}

