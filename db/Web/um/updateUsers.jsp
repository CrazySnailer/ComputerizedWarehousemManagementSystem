<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户管理</title>
    <script defer="defer" language="javascript" type="text/javascript" src="../datepicker/WdatePicker.js"></script>
	<link href="../resources/css/hottest.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
        .bt_01{
            width:71px;
            height:25px;
            border:0px;
            background: url(../resources/images/htdl.gif) no-repeat;
            color:#fff;
            font-size:12px;
            }
    </style>
    <script type="text/javascript">
function gets(){
	var selectedDeptName = '<s:property value="users1.deptName"/>';
	var selectedUtype = '<s:property value="users1.utype"/>';
	var selectedStatus = '<s:property value="users1.status"/>';
	var deptName = document.getElementById("deptName");
	var	status = document.getElementById("status");
	var utype = document.getElementById("utype");
	jsSelectItemByValue(status,selectedStatus);
	jsSelectItemByValue(utype,selectedUtype);
}

function jsSelectItemByValue(obj,objText){
	for(var i = 0 ; i < obj.options.length; i++){
		if(obj.options[i].value == objText){
			obj.options[i].selected = true;
			break;
		}
	}
}
</script>
</head>

<body onload="gets();">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="30"><img src="../resources/images/tab_03.gif" width="15" height="30" /></td>
        <td background="../resources/images/tab_05.gif" align="left"><img src="../resources/images/311.gif" width="16" height="16" /> <span class="STYLE4">用户修改</span></td>
        <td width="14"><img src="../resources/images/tab_07.gif" width="14" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" style="background:url(../resources/images/tab_12.gif) repeat-y left #d3e7fc; ">&nbsp;</td>
        <td width="97%" bgcolor="#FFFFFF"  valign="top" align="center" height="500" style="padding-top:20px">
			<table width="505px" border="0" cellspacing="0" cellpadding="0">
			<tr style="width:100%;height:20px;background:url(../resources/images/htbg1.gif);">
				<td></td>
			</tr>
			<tr style="width:100%; height:250px; background: url(../resources/images/htbg3.gif) repeat-y; text-align:center;">
				<td>
				<form action="usersAction!updateUsers?usersId=<s:property value="users1.id"/>" method="post" onSubmit="" >
				<table width="505px" border="0" cellspacing="0" cellpadding="0">
				  <tr>
					<td height="10" align="right" class="STYLE4">用户编号：</td>
					<td align="left"><input type="text" name="userId" disabled="disabled" value="<s:property value="users1.id"/>"/></td>
				  </tr>
				  <tr>
					<td height="30" align="right" class="STYLE4">用&nbsp;&nbsp;户&nbsp;&nbsp;名：</td>
					<td align="left"><input type="text" name="username" value="<s:property value="users1.username"/>"/></td>
				  </tr>
				  <tr>
					<td height="30" align="right" class="STYLE4">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
					<td height="20" align="left"><input type="password" name="password" value="<s:property value="users1.password"/>"/></td>
				  </tr>
				  <tr>
				  
					<td height="30" align="right" class="STYLE4">确认密码：</td>
					<td height="20" align="left"><input type="password" name="confirm" /></td>
				  </tr>
				  <tr>
					<td height="30" align="right" class="STYLE4">用户类型：</td>
					<td height="20" align="left">
						<select name="utype" id="utype">
							 <option value="超级管理员">超级管理员</option>
							<option value="一般管理员">一般管理员</option>
							<option value="一般用户">一般用户</option>
						</select>
					</td>
					</tr>
					<tr>
					<td height="30" align="right" class="STYLE4">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：</td>
					<td height="20" align="left">
						<s:action name="departmentAction!allFindDepartment1" executeResult="true"></s:action>
					</td>
					</tr>
					
					<tr>
					<td height="30" align="right" class="STYLE4">电子邮件：</td>
					<td height="20" align="left"><input type="text" name="email" value='<s:property value="users1.email"/>'/></td>
					</tr>
				 
					<tr>
					<td height="30" align="right" class="STYLE4">创建时间：</td>
					<td height="20" align="left"><input id="createTime" type="text" name="createTime" value='<s:property value="users1.createTime"/>' />
					&nbsp;<img onClick="WdatePicker({el:$dp.$('createTime')})" src="../datepicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					</tr>
					<tr>
					<td height="30" align="right" class="STYLE4">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：</td>
					<td height="20" align="left">
					  <select name="status" id="status">
						<option value="正常">正常</option>
						<option value="冻结">冻结</option>
					  </select></td>
				  </tr>
				  <tr>
					<td height="30" colspan="2" align="center">
					<input type="submit" name="registerSub" value="确定" class="bt_01" />&nbsp; 
					<input type="reset" name="cancel" value="取消"  class="bt_01"/></td>
				  </tr>
				  <tr>
					  <td height="40" colspan="2" align="center" class="STYLE4">${result }</td>
				  </tr>
				</table>
				</form>
				</td>
			</tr>
			<tr style="width:100%; height:17px; background: url(../resources/images/htbg2.gif) no-repeat;">
				<td>&nbsp;</td>
			</tr>
		</table>
	</td>
        <td width="14"  style="background:url(../resources/images/tab_16.gif) repeat-y right #d3e7fc; ">&nbsp;</td>
    </tr>
    </table></td>
  </tr>
  <tr>
    <td height="29"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="29"><img src="../resources/images/tab_20.gif" width="15" height="29" /></td>
        <td background="../resources/images/tab_21.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="21%" height="14">&nbsp;</td>
            <td width="79%" class="STYLE1" align="right">&nbsp;</td>
          </tr>
        </table></td>
        <td width="14"><img src="../resources/images/tab_22.gif" width="14" height="29" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>