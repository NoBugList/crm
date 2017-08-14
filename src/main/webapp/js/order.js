function formatState(val, row){
    if(val == 1) {
        return "已回款";
    } else {
        return "未回款";
    }
}

function formatAction(val, row) {
//	var rowStr = encodeURIComponent(row);
//	return "<a href='javascript:openOrderDetailsDialog(" + rowStr + ")'>查看订单明细</a>"
    return "<a href='javascript:openOrderDetailsDialog(" + row.id + ")'>查看订单明细</a>"
}

// 打开详情窗体
function openOrderDetailsDialog(orderId) {
//	$.ajax({url: "detail",dataType: 'json', data:{}, async:false, success: function() {
//		
//	}});
	// 加载详细数据
    $.post("detail", {orderId : orderId},function(result) {
        $("#fm").form('load', result);
        if(result.state == 0 ) {
            $("#state").val("未回款");
        } else if(result.state == 1) {
            $("#state").val("已回款");
        }
    });
    // 加载总金额
    $.post(ctx + "order_details/getTotalPrice", {orderId : orderId}, function(result) {
        $("#totalMoney").val(result.result);
    });
 
    // 加载详细列表数据
    $("#dg2").datagrid('load',{
        "orderId":orderId
    });
    
    // 打开窗体
    $("#dlg").dialog("open").dialog("setTitle","订单明细");
}

function closeOrderDetailsDialog(){
    $("#dlg").dialog("close");
}