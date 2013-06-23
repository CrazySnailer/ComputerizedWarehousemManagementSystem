<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>供应商管理</title>
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
		document.form1.vendorName.focus();
		var selectedVendorValue = '<s:property value="vendor1.status"/>';
		var vendor = document.getElementById("status");
		jsSelectItemByValue(vendor,selectedVendorValue);
	
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
<body onLoad="gets();">
<form  action="vendorAction!updateVendor?vendorId=<s:property value="vendor1.id"/>" method="post" name="form1">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="30"><img src="../resources/images/tab_03.gif" width="15" height="30" /></td>
        <td background="../resources/images/tab_05.gif" align="left"><img src="../resources/images/311.gif" width="16" height="16" /> <span class="STYLE4">供应商修改</span></td>
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
		<table width="80%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="20%" height="30" align="right" class="STYLE4">供应商名：</td>
    <td align="left"><input dataType="Require" type="text" name="vendorName" msg="供应商名称必填" value="<s:property value="vendor1.vendorName"/>"/></td>
  </tr>
  <tr>
    <td width="20%" height="30" align="right" class="STYLE4">城市名称：</td>
    <td align="left"><input type="text" name="cityId" value="<s:property value="vendor1.cityId"/>"/></td>
  </tr>
  <tr>
    <td width="20%" height="30" align="right" class="STYLE4">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：</td>
    <td align="left"><input type="text" name="address" value="<s:property value="vendor1.address"/>"/></td>
  </tr>
  <tr>
    <td width="20%" height="30" align="right" class="STYLE4">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编：</td>
    <td align="left"><input dataType="Zip" type="text" name="postcode" msg="邮编格式(6位数字)" value="<s:property value="vendor1.postcode"/>"/></td>
  </tr>
  <tr>
    <td width="20%" height="30" align="right" class="STYLE4">固定电话：</td>
    <td align="left"><input  dataType="Phone" type="text" name="telephone" msg="电话格式：区号-电话" value="<s:property value="vendor1.telephone"/>"/></td>
  </tr>
  <tr>
    <td width="20%" height="30" align="right" class="STYLE4">联&nbsp;&nbsp;系&nbsp;&nbsp;人：</td>
    <td align="left"><input dataType="Chinese" type="text" name="contact" msg="输入中文名字" value="<s:property value="vendor1.contact"/>"/></td>
  </tr>
  <tr>
    <td width="20%" height="30" align="right" class="STYLE4">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：</td>
    <td align="left"><select name="status" id="status">
      <option value="激活">激活</option>
      <option value="冻结">冻结</option>
    </select>
    </td>
  </tr>
  <tr>
    <td height="40" colspan="2" align="center">
	<input type="submit" name="" value="确定" class="bt_01" />&nbsp; 
	<input type="reset" name="" value="取消"  class="bt_01"/></td>
  </tr>
  <tr><td height="40" colspan="2" align="center" class="STYLE4">
  		${result }		
  	  </td>
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
