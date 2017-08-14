function searchSaleChance() {
	var data = {
			customerName: $("#s_customerName").val(),
			overview: $("#s_overview").val(),
			devResult:$("#s_devResult").combobox('getValue')
	}
	$("#dg").datagrid('load', data);
}

function formatDevResult(val, row) {
	switch (val) {
		case 0:
			return "未开发";
		case 1:
			return "开发中";
		case 2:
			return "开发成功";
		case 3:
			return "开发失败";
	}
}

function formatOptBtn(val, row) {
	var devResult = row.devResult;
	if (devResult < 1 ) {
		return '<a href="javascript:openDev(0, '+ row.id +')" >开发</a>';
	} else {
		return '<a href="javascript:openDev(1, '+ row.id +')" >查看详情</a>';
	}
}

function openDev (devStatus, saleChanceId) {
	var text = "";
	if (devStatus == 0) { // 开发
		text = "客户开发计划项管理";
	} else {
		text = "查看客户开发计划项";
	}
	//var url = "../cus_dev_plan/index?saleChanceId=" + saleChanceId;
	var url = ctx + "cus_dev_plan/index?saleChanceId=" + saleChanceId + "&show=" + devStatus; 
	window.parent.openTab(text, url, "icon-khkfjh");
	
}