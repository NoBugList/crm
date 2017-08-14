function openTab(text, url, iconCls){
    if($("#tabs").tabs("exists",text)) { // 如果有
        $("#tabs").tabs("select", text); // 选中页签
    }else{
        var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='" + url + "'></iframe>";
        $("#tabs").tabs("add",{
            title:text,
            iconCls:iconCls,
            closable:true,
            content:content
        });
    }
}

function openPasswordModifyDialog(){
    $("#dlg").dialog("open").dialog("setTitle","修改密码");
}

function modifyPassword() {
    $("#fm").form("submit",{
        url:"user/update_password",
        onSubmit:function() {
            var oldPassword = $("#oldPassword").val();
            var newPassword = $("#newPassword").val();
            var newPassword2 = $("#newPassword2").val();
            if(!$(this).form("validate")) {
                return false;
            }
            if(newPassword != newPassword2) {
                $.messager.alert("系统提示", "确认密码输入错误！");
                return false;
            }
            return true;
        },
        success:function(res) {
            res = JSON.parse(res);
            if(res.resultCode == 1){
                $.messager.alert("系统提示", res.result);
                $.removeCookie("userIdString");
                $.removeCookie("userName");
                $.removeCookie("realName");
                setInterval(function() {
                    window.location.href = "index";
                }, 1000);
            }else{
                $.messager.alert("系统提示", res.result);
                return;
            }
        }
    });
}

function closePasswordModifyDialog(){
    resetValue();
    $("#dlg").dialog("close");
}

function resetValue(){
    $("#oldPassword").val("");
    $("#newPassword").val("");
    $("#newPassword2").val("");
}

// 退出系统
function logout() {
    $.messager.confirm("系统提示","您确定要退出系统吗？",function(r) {
        if (r) {
            $.removeCookie("userIdString");
            $.removeCookie("userName");
            $.removeCookie("realName");
            window.location.href = "index";
        }
    });
}