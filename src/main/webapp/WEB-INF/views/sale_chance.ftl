<!doctype html>
<html>
    <#include "common/common.header.ftl" >
	<body style="margin: 1px">
		
		<table id="dg" title="销售机会信息管理" class="easyui-datagrid"
	       fitColumns="true" pagination="true" rownumbers="true" 
	       url="${ctx}/sale_chance/list" fit="true" toolbar="#tb">
		    <thead>
		    <tr>
		        <th field="cb" checkbox="true" align="center"></th>
		        <th field="id" width="50" align="center">编号</th>
		        <th field="chanceSource" width="200" align="center" hidden="true" >机会来源</th>
		        <th field="customerId" width="50" align="center" hidden="true">客户ID</th>
		        <th field="customerName" width="100" align="center">客户名称</th>
		        <th field="cgjl" width="50" align="center" hidden="true">成功几率</th>
		        <th field="overview" width="200" align="center">概要</th>
		        <th field="linkMan" width="100" align="center">联系人</th>
		        <th field="linkPhone" width="100" align="center">联系电话</th>
		        <th field="description" width="200" align="center" hidden="true">机会描述</th>
		        <th field="createMan" width="100" align="center">创建人</th>
		        <th field="createDate" width="100" align="center">创建时间</th>
		        <th field="assignMan" width="200" align="center" >指派人</th>
		        <th field="assignTime" width="200" align="center" hidden="true">指派时间</th>
		        <th field="state" width="100" align="center" formatter="formatState" >状态</th>
		        <th field="devResult" width="200" align="center" hidden="true">客户开发状态</th>
		    </tr>
		    </thead>
		</table>
		
		<#--工具栏-->
		<div id="tb">
			<div>
				<a href="javascript:openSaleChanceAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">创建</a>
	        	<a href="javascript:openSaleChanceModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
	        	<a href="javascript:deleteSaleChance()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		    </div>
		    <div>
		        &nbsp;客户名称：&nbsp;<input type="text" id="s_customerName" size="20" onkeydown="if(event.keyCode==13) searchSaleChance()"/>
		        &nbsp;概要：&nbsp;<input type="text" id="s_overview" size="20" onkeydown="if(event.keyCode==13) searchSaleChance()"/>
		        &nbsp;创建人：&nbsp;<input type="text" id="s_createMan" size="20" onkeydown="if(event.keyCode==13) searchSaleChance()"/>
		        &nbsp;分配状态：&nbsp;<select class="easyui-combobox" id="s_state" editable="false" panelHeight="auto" >
		        <option value="">请选择...</option>
		        <option value="0">未分配</option>
		        <option value="1">已分配</option>
		    </select>
		        <a href="javascript:searchSaleChance()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		    </div>
		</div>
		
		<#--弹出框-->
		<div id="dlg" class="easyui-dialog" style="width:700px;height:450px;padding: 10px 20px"
	     closed="true" buttons="#dlg-buttons">
	     
			
		    <form id="fm" method="post">
				<input type="hidden" id="id" name="id" />
				<input type="hidden" id="createMan" name="createMan" />
		        <table cellspacing="8px">
		            <tr>
		                <td>客户名称：</td>
		                <td>
		                	<input type="hidden" id="customerName" name="customerName" />
		                	<input class="easyui-combobox" id="customerId" name="customerId" 
		                	data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'name', url:'${ctx}/customer/find_all'" 
		                	/> &nbsp;<font color="red">*</font>
		                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		                <td>机会来源</td>
		                <td><input type="text" id="chanceSource" name="chanceSource" /></td>
		            </tr>
		            <tr>
		                <td>联系人：</td>
		                <td><input type="text" id="linkMan" name="linkMan" /></td>
		                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		                <td>联系电话：</td>
		                <td><input type="text" id="linkPhone" name="linkPhone" /></td>
		            </tr>
		            <tr>
		                <td>成功几率(%)：</td>
		                <td><input type="text" id="cgjl" name="cgjl" class="easyui-numberbox" data-options="min:0,max:100" required="true"/>&nbsp;<font color="red">*</font></td>
		                <td colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;</td>
		            </tr>
		            <tr>
		                <td>概要：</td>
		                <td colspan="4"><input type="text" id="overview" name="overview" style="width: 420px"/></td>
		            </tr>
		            <tr>
		                <td>机会描述：</td>
		                <td colspan="4">
		                    <textarea rows="5" cols="50" id="description" name="description" style="margin: 0px;width: 421px;height: 75px;resize: none;"></textarea>
		                </td>
		            </tr>
		            
		            <tr>
		                <td>指派给：</td>
		                <td><input class="easyui-combobox" id="assignMan" name="assignMan" 
		                	data-options="panelHeight:'auto',editable:false,valueField:'trueName',textField:'trueName',url:'${ctx}/user/find_customer_manager'"/></td>
		                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		            </tr>
		        </table>
		    </form>
		</div>
		
		<#--弹出框按钮-->
		<div id="dlg-buttons">
		    <a href="javascript:saveSaleChance()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		    <a href="javascript:closeSaleChanceDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
		</div>
		
		
	</body>
	<script src="${ctx}/js/sale.chance.js" ></script>
</html>