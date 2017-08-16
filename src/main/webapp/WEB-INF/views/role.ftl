<!doctype html>
<html>
<head>
    <#include "common/common.header.ftl" >
</head>
<body style="margin: 1px">
	<table id="dg" title="角色管理" class="easyui-datagrid"
	       fitColumns="true" pagination="false" rownumbers="true"
	       url="${ctx}/role/list" fit="true" toolbar="#tb" singleSelect = "false">
	    <thead>
	    <tr>
	        <th field="cb" checkbox="true" align="center"></th>
	        <th field="id" width="50" align="center">编号</th>
	        <th field="roleName" width="200" align="center">角色名称</th>
	        <th field="roleRemark" width="300" align="center">角色描述</th>
	        <th field="createDate" width="100" align="center">创建时间</th>
	        <th field="updateDate" width="100" align="center">更新时间</th>
	    </tr>
	    </thead>
	</table>
	<#--工具栏-->
	<div id="tb">
		<div>
	        <a href="javascript:openAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">创建</a>
	        <a href="javascript:openModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
	        <a href="javascript:deleteRoles()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	        <a href="javascript:relatePermissions()" class="easyui-linkbutton" iconCls="icon-add" plain="true">关联权限</a>
	    </div>
	</div>
	
	<#--弹出框-->
	<div id="dlg" class="easyui-dialog" style="width:600px;height:250px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
	
	    <form id="fm" method="post">
	        <table cellspacing="8px">
	        	<input type="hidden" id="id" name="id" />
	            <tr>
	                <td>角色名称：</td>
	                <td colspan="4"><input type="text" id="roleName" name="roleName" style="width: 420px"/></td>
	            </tr>
	            <tr>
	                <td>角色描述：</td>
	                <td colspan="4">
	                    <textarea rows="5" cols="50" id="roleRemark" name="roleRemark" style="margin: 0px;width: 421px;height: 75px;resize: none;"></textarea>
	                </td>
	            </tr>
	        </table>
	    </form>
	</div>
	
	<#--弹出框按钮-->
	<div id="dlg-buttons">
	    <a href="javascript:saveRole()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	    <a href="javascript:closeRoleDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	
	<script src="${ctx}/js/role.js" ></script>
</body>
</html>