<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>部门添加</title>
	<link href="../resources/css/hottest.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
	<!--
	body {
		margin-left: 0px;
		margin-top: 0px;
		margin-right: 0px;
		margin-bottom: 0px;
	}
	.STYLE1 {font-size: 12px}
	.STYLE4 {
		font-size: 12px;
		color: #1F4A65;
		font-weight: bold;
		font-family:"宋体";
	}
	
	a:link {
		font-size: 12px;
		color: #06482a;
		text-decoration: none;
	}
	a:visited {
		font-size: 12px;
		color: #06482a;
		text-decoration: none;
	}
	a:hover {
		font-size: 12px;
		color: #FF0000;
		text-decoration: underline;
	}
	a:active {
		font-size: 12px;
		color: #FF0000;
		text-decoration: none;
	}
	.bt_01{
	    width:71px;
		height:25px;
		border:0px;
	    background: url(images/htdl.gif) no-repeat;
		color:#fff;
		font-size:12px;
		}
	-->
	</style>
  </head>
<script type="text/javascript">
function gets(){
	var selectedStatus = '<s:property value="department1.status"/>';
	var	status = document.getElementById("status");
	jsSelectItemByValue(status,selectedStatus);
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
<body onload="gets();">

<form action="departmentAction!updateDepartment?departmentId=<s:property value="department1.id"/>" onSubmit="" name="form1" method="post">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="30"> <img src="../resources/images/tab_03.gif" width="15" height="30" /></td>
        <td background="../resources/images/tab_05.gif" align="left"><img src="../resources/images/311.gif" width="16" height="16" /> <span class="STYLE4">部门修改</span></td>
        <td width="14"><img src="../resources/images/tab_07.gif" width="14" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      
      <tr>
        <td width="15" style="background:url(../resources/images/tab_12.gif) repeat-y left #d3e7fc; ">&nbsp;</td>
        <td width="97%" bgcolor="#FFFFFF"  valign="top" align="center" height="500" style=" padding-top:20px;">
			<table width="505px" border="0" cellspacing="0" cellpadding="0">
			<tr style="width:100%;height:20px;background:url(../resources/images/htbg1.gif);">
				<td></td>
			</tr>
			<tr style="width:100%; height:250px; background: url(../resources/images/htbg3.gif) repeat-y; text-align:center;">
				<td>
				<table width="468" border="0" cellspacing="0" cellpadding="0" height="202">
				  <tr>
					<td width="20%" height="30" align="right" class="STYLE4">部门名称：</td>
					<td align="left"><input type="text" name="deptName" value='<s:property value="department1.deptName"/>'/></td>
				  </tr>
				  <tr>
					<td width="20%" height="30" align="right" class="STYLE4">部门经理：</td>
					<td align="left"><input type="text"  name="manager"  value='<s:property value="department1.manager"/>'/></td>
				  </tr>
				  <tr>
					<td width="20%" height="30" align="right" class="STYLE4">部门描述：</td>
					<td align="left"><textarea name="comments"  cols="40" rows="5"><s:property value="department1.comments"/></textarea></td>
				  </tr>
				  <tr>
					<td width="20%" height="3-0" align="right" class="STYLE4">部门状态：</td>
					<td align="left">
						<select name="status" id="status">
						  <option value="激活">激活</option>
						  <option value="冻结">冻结</option>
						</select>
					</td>
				  </tr>
				  <tr>
					<td height="40" colspan="2" align="center"  style="color:blue">
					<input type="submit" value="确定"  class="bt_02"/>&nbsp; 
					<input type="reset" value="取消"  class="bt_02"/></td>
					</tr>
					 <tr><td height="40" colspan="2" align="center" class="STYLE4">${result }</td>
					</tr>
				</table>
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
</form>
</body>
</html>
