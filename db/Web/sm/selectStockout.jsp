<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>选择出货单</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <link rel="stylesheet" type="text/css" href="../resources/css/hottest.css">
<script type="text/javascript">

function shelfSelected()
{
	try
    {
        var tests = document.getElementById("test").firstChild;
        window.opener.addItem();
        var j = window.opener.document.getElementById("rowId").value;
        window.opener.document.getElementById("stockoutId"+j).value=tests.options[tests.selectedIndex].text;
        window.opener.document.getElementById("qty"+j).value=tests.options[tests.selectedIndex].value;
    }
    catch(e){}
	var test = document.getElementById("select");
	   var index = test.selectedIndex;
	   var shelf = test.options[index].text;
     if(window.opener)
     	{
     		window.opener.returnValue = shelf;
     	}
     window.returnValue = shelf;
     window.close();
}
</script>

  </head>

<body style="margin:0;background-color:#ECEEF0">
<table width="100%" border="0">
    <tr><td align="center">选择出库单</td></tr>
    <tr>
        <td align="center">
            <div id="test">
            <s:action name="stockout!allFindStockout" executeResult="true"></s:action>
       </td>
  </tr>
  <tr>
   <td align="center">
				<input type="button" value="确定" onClick="shelfSelected()" />
				<input type="button" value="取消 " onClick="window.close()" />
				&nbsp;
			</td>
  </tr>
</table>

</body>
</html>
