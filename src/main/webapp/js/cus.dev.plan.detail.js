$(function() {
    var saleChanceId = $("#saleChanceId").val();
    $("#dg").edatagrid({
        url:'../cus_dev_plan/list?saleChanceId=' + saleChanceId,
        saveUrl:'../cus_dev_plan/add_update?saleChanceId=' + saleChanceId,
        updateUrl:'../cus_dev_plan/add_update?saleChanceId=' + saleChanceId,
        destroyUrl:'../cus_dev_plan/delete'
    });
});

function formatPlanDate(val, row) {
    if (val == null) {
        return '';
    }
    return new Date(val).format('yyyy-MM-dd');

}

function updateSaleChanceDevResult(devResult) {
    var param = {};
    param.id = $("#saleChanceId").val();
    param.devResult = devResult;
    $.post("update_devresult", param, function(result) {
        if(result.resultCode == 1) {
            $.messager.alert("系统提示", "操作成功！");
        } else {
            $.messager.alert("系统提示","操作失败！");
        }
    });
}
