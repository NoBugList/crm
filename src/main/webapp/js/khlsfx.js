function searchCustomerLoss() {
    $("#dg").datagrid('load',{
        "cusName":$("#s_cusName").val(),
        "cusManager":$("#s_cusManager").val(),
    });
}
