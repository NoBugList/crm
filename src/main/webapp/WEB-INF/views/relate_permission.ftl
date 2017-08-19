<!DOCTYPE html>
<HTML>
<HEAD>
	<title>角色授权</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/ztree/css/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctx}/ztree/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${ctx}/ztree/js/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="${ctx}/ztree/js/jquery.ztree.excheck.js"></script>
	<SCRIPT type="text/javascript">
		var setting = {
			check: {
				enable: true,
				chkboxType: { "Y" : "ps", "N" : "ps" }
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onCheck: zTreeOnCheck
			}
		};
		
		function zTreeOnCheck(event, treeId, treeNode) {
			var params = {roleId:${roleId?c}, moduleId:treeNode.id, checked:treeNode.checked};
			$.post('dorelate', params, function(data) {
				if (data.resultCode == 0) {
					alert(data.resultMessage);
				}
			});
			
		}
		
		var zNodes = []; // ?c去掉100,000中的逗号
		<#list modules as module >
			zNodes[${module_index}] = {id:${module.id?c}, pId:<#if module.parentId??>${module.parentId?c}<#else>0</#if>, 
			name:'${module.moduleName?default('')}', open:true, checked:${module.checked?string('true', 'false')}};
		</#list>
		
		$(document).ready(function(){
			$.fn.zTree.init($("#tree"), setting, zNodes);
		});
		
	</SCRIPT>
</HEAD>

<BODY>
<div class="content_wrap">
	<div class="zTreeDemoBackground left">
		<ul id="tree" class="ztree"></ul>
	</div>
</div>
</BODY>
</HTML>