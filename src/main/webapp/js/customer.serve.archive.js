function searchCustomerService() {
    console.log($("#s_customer").val());
    var createTimefrom = $("#s_createDatefrom").datebox("getValue");
    if (createTimefrom != null && $.trim(createTimefrom).length > 0) {
    	createTimefrom += " 00:00:00";
    }
    var createDateto = $("#s_createDateto").datebox("getValue");
    if (createDateto != null && $.trim(createDateto).length > 0) {
    	createDateto += " 23:59:59";
    }
    $("#dg").datagrid('load',{
        "customer" : $("#s_customer").val() ,
        "overview" : $("#s_overview").val(),
        "serveType" : $("#s_serveType").combobox("getValue"),
        "createTimefrom" : createTimefrom,
        "createTimeto" : createDateto
    });
}

function openCustomerServiceFileDialog() {
    var selectedRows = $("#dg").datagrid("getSelections");
    if(selectedRows.length != 1) {
        $.messager.alert("系统提示","请选择一条要查看的客户服务数据！");
        return;
    }
    var row=selectedRows[0];
    $("#fm").form("load",row);
    $("#dlg").dialog("open").dialog("setTitle","客服服务详情");
}

function closeCustomerFileDialog(){
    $("#dlg").dialog("close");
}

