function searchCustomerLoss() {
    $("#dg").datagrid('load',{
        "cusName":$("#s_cusName").val(),
        "cusManager":$("#s_cusManager").val(),
        "state":$("#s_state").combobox("getValue")
    });
}

function formatState(val, row) {
    if(val == 1) {
        return "确认流失";
    } else {
        return "暂缓流失";
    }
}

function formatAction(val, row) {
    if(row.state == 1) {
        return "客户确认流失";
    } else {
        return "<a href='javascript:openCustomerReprieve(" + row.id + ")'>暂缓流失</a>";
    }
}

function openCustomerReprieve(id) {
    window.parent.openTab('客户流失暂缓措施管理' , ctx + 'customer_reprieve/index?lossId=' + id, 'icon-khlsgl');
}