<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>selectProduct.jsp</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
<script type="text/javascript">

function shelfSelected()
{
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
	<tr><td align="center">选择货架</td></tr>
		<tr>
			<td align="center">
				<div id="test">
					<s:action name="shelfAction!allFindShelf" executeResult="true"></s:action>
				</div>
				&nbsp;
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
