<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>修改商品</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" type="text/css" href="../resources/css/hottest.css">
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
            background: url(../resources/images/htdl.gif) no-repeat;
            color:#fff;
            font-size:12px;
            }
        -->
    </style>
    <script type="text/javascript">
  //选择类别
	function selectCategory()
    {
		//alert(j);
		//实际编码时调该方法
		//window.open("<%=request.getContextPath()%>/selectProduct.jsp?no="+j,"Window","width=400px,height=230px,status=no,resizable=yes,scrollable=yes");
        //测试用,实际编码时去掉该句
        var result = window.showModalDialog("selectCategory.jsp","Window","width=400px,height=230px,status=no,resizable=yes,scrollable=yes");
        if(!result)
        	{
        		result = window.returnValue;
        	}
        document.getElementById("cate").value = result;
	}
	//选择品牌
	function selectBrand()
    {
		//alert(j);
		//实际编码时调该方法
		//window.open("<%=request.getContextPath()%>/selectProduct.jsp?no="+j,"Window","width=400px,height=230px,status=no,resizable=yes,scrollable=yes");
        //测试用,实际编码时去掉该句
        var result = window.showModalDialog("selectBrand.jsp","Window","width=400px,height=230px,status=no,resizable=yes,scrollable=yes");
        if(!result)
        	{
        		result = window.returnValue;
        	}
        document.getElementById("bran").value = result;
	}
	function gets(){
		var selectedBrandValue = '<s:property value="goods1.brand"/>';
		var selectedCateValue = '<s:property value="goods1.category"/>';
		var brand = document.getElementById("brand");
		var category = document.getElementById("category");
		jsSelectItemByValue(brand,selectedBrandValue);
		jsSelectItemByValue(category,selectedCateValue);
		document.getElementById("status").value = <s:property value="goods1.status"/>;
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
    <body onload="gets()">
<form action="productAction!updateProduct?productId=<s:property value="goods1.id"/>" method="post" >
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="30"><img src="../resources/images/tab_03.gif" width="15" height="30" /></td>
        <td background="../resources/images/tab_05.gif" align="left"><img src="../resources/images/311.gif" width="16" height="16" /> <span class="STYLE4">商品修改</span></td>
        <td width="14"><img src="../resources/images/tab_07.gif" width="14" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" style="background:url(../resources/images/tab_12.gif) repeat-y left #d3e7fc; ">&nbsp;</td>
        <td width="97%" bgcolor="#FFFFFF"  valign="top" align="center" height="500" style="padding-top:20px;">
		<table width="505px" border="0" cellspacing="0" cellpadding="0">
			<tr style="width:100%;height:20px;background:url(../resources/images/htbg1.gif);">
				<td></td>
			</tr>
			<tr style="width:100%; height:250px; background: url(../resources/images/htbg3.gif) repeat-y; text-align:center;">
				<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td width="20%" height="30" align="right" class="STYLE4">商品名称：</td>
    <td align="left"><input type="text" name="productName" value="<s:property value="goods1.productName"/>"/></td>
  </tr>
  <tr>
    <td height="20" align="right" class="STYLE4">条&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
    <td height="20" align="left"><input type="text" name="barcode" value="<s:property value="goods1.barcode"/>"/></td>
  </tr>
  <tr>
    <td height="30" align="right" class="STYLE4">类&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
    <td height="20" align="left"><s:action name="findCategory!findAll" executeResult="true"></s:action>
  </tr>
    <tr>
    <td height="30" align="right" class="STYLE4">品&nbsp;&nbsp;&nbsp;&nbsp;牌：</td>
    <td height="20" align="left"><s:action name="brandAction!allBrand" executeResult="true"></s:action>
  </tr>
    <tr>
    <td height="30" align="right" class="STYLE4">型&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
    <td height="20" align="left"><input type="text" name="specification" value="<s:property value="goods1.specification"/>"/></td>
    </tr>
    <tr>
    <td height="30" align="right" class="STYLE4">临&nbsp;界&nbsp;值：</td>
    <td height="20" align="left"><input type="text" name="threshold" value="<s:property value="goods1.threshold"/>"/></td>
    </tr>
  <tr>
    <td height="20" align="right" class="STYLE4">描&nbsp;&nbsp;&nbsp;&nbsp;述：</td>
    <td height="20" align="left"><textarea  cols="40" rows="4" property="description" value="" name="description"><s:property value="goods1.description"/></textarea></td>
  </tr>
    <tr>
    <td height="30" align="right" class="STYLE4">创建时间：</td>
    <td height="20" align="left"><input type="text" name="createTime" value="<s:property value="goods1.createTime"/>"/></td>
    </tr>
    <tr>
    <td height="30" align="right" class="STYLE4">状&nbsp;&nbsp;&nbsp;&nbsp;态：</td>
    <td height="20" align="left">
      <select name="status" id="status">
        <option value="1">激活</option>
        <option value="0">冻结</option>
      </select></td>
  </tr>
  <tr>
    <td height="30" colspan="2" align="center">
	<input type="submit" name="" value="确定" class="bt_01" />&nbsp; 
	<input type="submit" name="" value="取消"  class="bt_01"/></td>
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
