$(document).ready(function() {
    $("#createPeople").val($.cookie('userName'));
});
function resetValue(){
    $("#serveType").combobox("setValue","");
    $("#customer").val("");
    $("#overview").val("");
    $("#serviceRequest").val("");
}

function saveCustomerService(){
    var url = ctx + "customer_serve/add_update";
    $("#fm").form("submit",{
        url : url,
        onSubmit: function(){
            return $(this).form("validate");
        },
        success:function(result) {
            var result = JSON.parse(result);
            if(result.resultCode == 1) {
                $.messager.alert("系统提示","保存成功！");
                resetValue();
            } else {
                $.messager.alert("系统提示","保存失败！");
                return;
            }
        }
    });
}
