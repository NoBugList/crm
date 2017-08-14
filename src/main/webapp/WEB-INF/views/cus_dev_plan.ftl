<!doctype html>
<html>
    <#include "common/common.header.ftl" >
	<body style="margin: 1px">
		
		<#--营销机会信息展示-->
		<div id="p" class="easyui-panel" title="销售机会信息" style="width: 700px;height: 350px;padding: 10px">
		    <table cellspacing="8px">
		        <input type="hidden" id="saleChanceId" name="saleChanceId" value="${saleChance.id?c}"/>
		        <tr>
		            <td>客户名称：</td>
		            <td><input type="text" id="customerName" name="customerName" readonly="readonly" value="${saleChance.customerName?default('')}" /></td>
		            <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		            <td>机会来源</td>
		            <td><input type="text" id="chanceSource" name="chanceSource" readonly="readonly" value="${saleChance.chanceSource?if_exists}"/></td>
		        </tr>
		        <tr>
		            <td>联系人：</td>
		            <td><input type="text" id="linkMan" name="linkMan" readonly="readonly" value="${saleChance.linkMan?if_exists}"/></td>
		            <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		            <td>联系电话：</td>
		            <td><input type="text" id="linkPhone" name="linkPhone" readonly="readonly" value="${saleChance.linkPhone?if_exists}"/></td>
		        </tr>
		        <tr>
		            <td>成功几率(%)：</td>
		            <td><input type="text" id="cgjl" name="cgjl" readonly="readonly" value="${saleChance.cgjl?if_exists}"/></td>
		            <td colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;</td>
		        </tr>
		        <tr>
		            <td>概要：</td>
		            <td colspan="4"><input type="text" id="overview" name="overview" style="width: 420px" readonly="readonly" value="${saleChance.overview?if_exists}"/></td>
		        </tr>
		        <tr>
		            <td>机会描述：</td>
		            <td colspan="4">
		                <textarea rows="5" cols="50" id="description" name="description" readonly="readonly" >${saleChance.description?if_exists}</textarea>
		            </td>
		        </tr>
		        <tr>
		            <td>创建人：</td>
		            <td><input type="text" readonly="readonly" id="createMan" name="createMan" value="${saleChance.createMan?if_exists}" /></td>
		            <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		            <td>创建时间：</td>
		            <td><input type="text" id="createTime" name="createDate" readonly="readonly" value="<#if saleChance.createDate??>${saleChance.createDate?datetime}</#if>" /></td>
		        </tr>
		        <tr>
		            <td>指派给：</td>
		            <td>
		                <input type="text" readonly="readonly" id="assignMan" name="assignMan" value="${saleChance.assignMan?if_exists}"  />
		            </td>
		            <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		            <td>指派时间：</td>
		            <td><input type="text" id="assignTime" name="assignTime" readonly="readonly" value="<#if saleChance.assignTime??>${saleChance.assignTime?string('yyyy-MM-dd HH:mm:ss')}</#if>" /></td>
		        </tr>
		    </table>
		</div>
		
		
		<#--开发计划项展示-->
		<br/>
		<table id="dg" title="开发计划项" style="width:700px;height:250px"
		       toolbar="#toolbar" idField="id" rownumbers="true" fitColumns="true" singleSelect="true">
		    <thead>
		    <tr>
		        <th field="id" width="50">编号</th>
		        <th field="saleChanceId" hidden="true">营销机会ID</th>
		        <th field="planDate" width="50" editor="{type:'datebox',options:{required:true}}">日期</th>
		        <th field="planItem" width="100" editor="{type:'validatebox',options:{required:true}}">计划内容</th>
		        <th field="exeAffect" width="100" editor="{type:'validatebox',options:{required:true}}">执行效果</th>
		    </tr>
		    </thead>
		</table>
		<div id="toolbar">
			<#if show??>
			<#if show == 0 >
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addPlan();">添加计划</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deletePlan();">删除计划</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="save();">保存计划</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancelRow();">撤销行</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-kfcg" plain="true" onclick="updateSaleChanceDevResult(2)">开发成功</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-zzkf" plain="true" onclick="updateSaleChanceDevResult(3)">终止开发</a>
		    </#if>
		    </#if>
		</div>
		
	</body>
	<script src="${ctx}/js/cus.dev.plan.js" ></script>
</html>