<%@page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
response.setHeader("Pragma", "No-cache"); 
response.setHeader("Cache-Control", "no-cache"); 
response.setDateHeader("Expires", 0); 
%>
<html>
	<head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<title>盘点一览表</title>
		<link href="../resources/css/hottest.css" rel="stylesheet"
			type="text/css" />
		<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.STYLE1 {
	font-size: 12px
}

.STYLE4 {
	font-size: 12px;
	color: #1F4A65;
	font-weight: bold;
	font-family: "宋体";
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

.bt_01 {
	width: 71px;
	height: 25px;
	border: 0px;
	background: url(../resources/images/htdl.gif) no-repeat;
	color: #fff;
	font-size: 12px;
}

.bt_02 {
	width: 60px;
	height: 20px;
	border: 0px;
	background: #0099CC;
	color: #fff;
	font-size: 12px;
}

.bt_03 {
	width: 150px;
	height: 18px;
}
-->
</style>
	</head>
	<body>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="30">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="15" height="30"><img src="../resources/images/tab_03.gif" width="15" height="30" /></td>
							<td background="../resources/images/tab_05.gif" align="left"><img src="../resources/images/311.gif" width="16" height="16" /> <span class="STYLE4">库存一览表</span></td>
							<td width="14"><img src="../resources/images/tab_07.gif" width="14" height="30" /></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="15"
								style="background: url(../resources/images/tab_12.gif) repeat-y left #d3e7fc;">&nbsp;
								

							</td>
							<td width="97%" bgcolor="#FFFFFF" height="500" valign="top"
								align="center">
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									bgcolor="#cecece" align="center">
									<tr class="dz">
										<td bgcolor="#eceef0" colspan="9" align="center">
										<form action="stockAction" method="post">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0" align="left" height="40"
												style="border: 1px dashed #d4d4d4;">
												<tr class="my">
													<td height="20" align="left" width="30%"
														style="padding-left: 10px;">
														商品名称：
														 <s:action name="productAction!allFindProduct1" executeResult="true"></s:action>
														货&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;架：
															<s:action name="shelfAction!allFindShelf1" executeResult="true"></s:action>
														<input type="submit" name="Submit" value="查询" class="bt_02" /></td>
												</tr>
											</table>
											</form></td>
									</tr>
									<tr class="dz" align="center">
										<td width="30%" height="28"
											background="../resources/images/sale_bg.gif">商品名称</td>
										<td width="20%" background="../resources/images/sale_bg.gif">
											商品分类										</td>
										<td width="20%" background="../resources/images/sale_bg.gif">
											品牌										</td>
										<td width="20%" background="../resources/images/sale_bg.gif">货架</td>
										<td width="10%" background="../resources/images/sale_bg.gif">商品库存</td>
										
									</tr>
									<s:iterator value="allStockMessage">
											<tr class="my" align="center" bgcolor="#ffffff">
												<td align="left"><s:property value="productName"/></td>
												<td align="left"><s:property value="category"/></td>
												<td align="left"><s:property value="brand"/></td>
												<td align="left"><s:property value="shelfName"/></td>
												<td align="right"><s:property value="qty"/></td>
											</tr>
									</s:iterator>
								</table>
							</td>
							<td width="14" style="background: url(../resources/images/tab_16.gif) repeat-y right #d3e7fc;">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="29">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="15" height="29">
								<img src="../resources/images/tab_20.gif" width="15" height="29" />
							</td>
							<td background="../resources/images/tab_21.gif">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="21%" height="14">&nbsp;</td>
										<td width="79%" class="STYLE1" align="right">
                                            <a href="stockAction?pageNumber=1&productName=${product}&shelfName=${shelf }">首页</a> |
                                            <a href="stockAction?pageNumber=<s:property value="pageNumber-1"/>&productName=${product}&shelfName=${shelf }">上一页</a> |
                                            <a href="stockAction?pageNumber=<s:property value="pageNumber+1"/>&productName=${product}&shelfName=${shelf }">下一页</a> |
                                            <a href="stockAction?pageNumber=<s:property value="totalPage"/>&productName=${product}&shelfName=${shelf }">尾页</a>&nbsp;
                                            当前第<span class="font_1"><s:property value="pageNumber"/></span>页
										</td>
									</tr>
								</table>
							</td>
							<td width="14">
								<img src="../resources/images/tab_22.gif" width="14" height="29" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>