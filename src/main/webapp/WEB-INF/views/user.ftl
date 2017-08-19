<!doctype html>
<html>
<head>
    <#include "common/common.header.ftl" >
</head>
<body style="margin: 1px">
	<table id="dg" title="销售机会信息管理" class="easyui-datagrid"
	       fitColumns="true" pagination="true" rownumbers="true"
	       url="${ctx}/user/list" fit="true" toolbar="#tb">
	    <thead>
	    <tr>
	        <th field="cb" checkbox="true" align="center"></th>
	        <th field="id" width="50" align="center">编号</th>
	        <th field="userName" width="100" align="center">用户名</th>
	        <th field="trueName" width="200" align="center">真实姓名</th>
	        <th field="email" width="100" align="center">邮箱</th>
	        <th field="phone" width="100" align="center">联系电话</th>
	        <th field="createDate" width="100" align="center">创建时间</th>
	        <th field="roleIds" width="100" align="center" hidden="true">关联角色</th>
	    </tr>
	    </thead>
	</table>
	<#--工具栏-->
	<div id="tb">
		<div>
	        <a href="javascript:openAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">创建</a>
	        <a href="javascript:openModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
	        <a href="javascript:deleteUser()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	    </div>
	    <div>
	        &nbsp;用户名：&nbsp;<input type="text" id="s_userName" size="20" onkeydown="if(event.keyCode==13) searchUser()"/>
	    </select>
	        <a href="javascript:searchUser()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
	    </div>
	</div>
	
	<#--弹出框-->
	<div id="dlg" class="easyui-dialog" style="width:680px;height:200px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
	
	    <form id="fm" method="post">
	    	<input type="hidden" id="id" name="id" />
	        <table cellspacing="8px">
	            <tr>
	                <td>用户名：</td>
	                <td>
	                	<input type="text" id="userName" name="userName" />
	                </td>
	                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	                <td>密码</td>
	                <td><input type="text" id="password" name="password" /></td>
	            </tr>
	            <tr>
	                <td>真实姓名：</td>
	                <td><input type="text" id="trueName" name="trueName" /></td>
	                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	                <td>邮箱：</td>
	                <td><input type="text" id="email" name="email" /></td>
	            </tr>
	            <tr>
	                <td>联系电话：</td>
	                <td><input type="text" id="phone" name="phone" required="true"/>&nbsp;<font color="red">*</font></td>
	                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	                <td>角色：</td>
	                <td>
	                	<input class="easyui-combobox" id="roleIds" name="roleIds" 
	                		data-options="panelHeight:'auto', editable:false, valueField:'id',
	                		textField:'roleName', url:'${ctx}/role/find_all', multiple:'true'"/>
	                
	                </td>
	            </tr>
	        </table>
	    </form>
	</div>
	
	<#--弹出框按钮-->
	<div id="dlg-buttons">
	    <a href="javascript:saveUser()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	    <a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	
	<script src="${ctx}/js/user.js" ></script>
</body>
</html>