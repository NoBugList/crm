<!doctype html>
<html>
    <#include "common/common.header.ftl" >
	<body style="margin: 1px">
		
		<table id="dg" title="模块管理" class="easyui-datagrid"
	       fitColumns="true" pagination="true" rownumbers="true" 
	       url="${ctx}/module/list?sort=orders.asc" fit="true" toolbar="#tb">
		    <thead>
		    <tr>
		        <th field="cb" checkbox="true" align="center"></th>
		        <th field="id" width="50" align="center">编号</th>
		        <th field="parent" width="200" align="center" formatter="formatParentName">父模块名称</th>
		        <th field="moduleName" width="200" align="center">模块名称</th>
		        <th field="moduleStyle" width="50" align="center">模块样式</th>
		        <th field="url" width="100" align="center">路径/方法</th>
		        <th field="optValue" width="50" align="center">操作权限 </th>
		        <th field="grade" width="200" align="center" formatter="formatGrade">层级</th>
		        <th field="orders" width="100" align="center">排序 </th>
		        <th field="updateDate" width="100" align="center">修改时间</th>
		    </tr>
		    </thead>
		</table>
		
		<#--工具栏-->
		<div id="tb">
			<div>
				<a href="javascript:openAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">创建</a>
	        	<a href="javascript:openModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
	        	<a href="javascript:deletes()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		    </div>
		</div>
		
		<#--弹出框-->
		<div id="dlg" class="easyui-dialog" style="width:700px;height:250px;padding: 10px 20px"
	     closed="true" buttons="#dlg-buttons">
	     
			
		    <form id="fm" method="post">
				<input type="hidden" id="id" name="id" />
		        <table cellspacing="8px">
		            <tr>
		                <td>模块名称：</td>
		                <td>
		                	<input type="text" id="moduleName" name="moduleName" required="true"/>&nbsp;<font color="red">*</font>
		                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		                <td>模块样式：</td>
		                <td><input type="text" id="moduleStyle" name="moduleStyle" /></td>
		            </tr>
		            <tr>
		                <td>排序：</td>
		                <td><input type="text" id="orders" name="orders" required="true" />&nbsp;<font color="red">*</font></td>
		                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		                <td>路径/方法：</td>
		                <td><input type="text" id="url" name="url" required="true" />&nbsp;<font color="red">*</font></td>
		            </tr>
		            <tr>
		                <td>操作权限：</td>
		                <td><input type="text" id="optValue" name="optValue" required="true" />&nbsp;<font color="red">*</font></td>
		               	<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		                <td>层级：</td>
		                <td>
		                	<select class="easyui-combobox" name="grade" id="grade" >
								<option value=0 >根级</option>
								<option value=1 >第一级</option>
								<option value=2 >第二级</option>
							</select>
		                </td>
		            </tr>
		            <tr id="parentTr" style="display:none">
		                <td>父级菜单：</td>
		                <td><input type="text" id="parentId" name="parentId" class="easyui-combobox" /></td>
		                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		            </tr>
		        </table>
		    </form>
		</div>
		
		<#--弹出框按钮-->
		<div id="dlg-buttons">
		    <a href="javascript:saveModule()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		    <a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
		</div>
		
		
	</body>
	<script src="${ctx}/js/module.js" ></script>
</html>