$(function() {
	var cusId = $("#cusId").val();
    $("#dg").edatagrid({
        url: 'list?customerId=' + cusId,
        saveUrl: 'add?cusId='+ cusId,
        updateUrl: 'update',
        destroyUrl: 'delete',
        pagination: true, // 分页
        pageSize: 10 // 每页条数

    });
});

// 添加一行
function add() {
	$('#dg').edatagrid('addRow');
}

// 保存行
function save() {
	$('#dg').edatagrid('saveRow');
	$('#dg').edatagrid('reload');
}

// 删除行
function deleteLinkman() {
	$('#dg').edatagrid('destroyRow');
}

//撤销行
function cancelRow() {
	$('#dg').edatagrid('cancelRow');
}
