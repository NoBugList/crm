function openCustomerServiceFeedbackDialog(){
    var selectedRows = $("#dg").datagrid("getSelections");
    if(selectedRows.length != 1 ) {
        $.messager.alert("系统提示","请选择一条要反馈的客户服务数据！");
        return;
    }
    var row = selectedRows[0];
    $("#dlg").dialog("open").dialog("setTitle","客服服务反馈");
    $("#fm").form("load", row);
    $("#state").val("已归档");
    $("#id").val(row.id);
}

function saveCustomerServiceFeedback() {
    $("#fm").form("submit", {
        url: ctx + "customer_serve/add_update",
        onSubmit:function() {
            if($("#myd").combobox("getValue")==""){
                $.messager.alert("系统提示","请选择客户满意度！");
                return false;
            }
            return $(this).form("validate");
        },
        success:function(result) {
            var result = JSON.parse(result)
            if(result.resultCode == 1) {
                $.messager.alert("系统提示","反馈成功！");
                resetValue();
                $("#dlg").dialog("close");
                $("#dg").datagrid("reload");
            }else{
                $.messager.alert("系统提示","反馈失败！");
                return;
            }
        }
    });
}

function resetValue(){
    $("#serviceProceResult").val("");
    $("#myd").combobox("setValue","");
}

function closeCustomerFeedbackDialog(){
    $("#dlg").dialog("close");
    resetValue();
}

