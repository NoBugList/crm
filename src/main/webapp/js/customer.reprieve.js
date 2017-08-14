$(function() {
    var lossId = $("#lossId").val();

    $("#dg").edatagrid({
        url: 'list?lossId=' + lossId,
        saveUrl: 'add?lossId=' + lossId,
        updateUrl: 'update',
        destroyUrl: 'delete'
    });
});

function confirmLoss() {
    var lossId = $("#lossId").val();
    $.messager.prompt('系统提示', '请输入流失原因：', function(r) {
        if (r) {
            $.post( ctx + "customer_loss/confirmLoss",{lossId: lossId , lossReason: r}, function(result) {
                if(result.resultCode == 1) {
                    $.messager.alert("系统提示","执行成功！");
                }else{
                    $.messager.alert("系统提示", "执行失败！");
                }
            });
        }
    });
}