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
		<title>送货一览表</title>
		<script defer="defer" language="javascript" type="text/javascript" src="../datepicker/WdatePicker.js"></script>
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
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td height="30">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="15" height="30"><img src="../resources/images/tab_03.gif" width="15" height="30" /></td>
							<td background="../resources/images/tab_05.gif" align="left"><img src="../resources/images/311.gif" width="16" height="16" /> <span class="STYLE4">送货一览表</span></td>
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
								style="background: url(../resources/images/tab_12.gif) repeat-y left #d3e7fc;">&nbsp;</td>
							<td width="97%" bgcolor="#FFFFFF" height="500" valign="top"
								align="center">
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									bgcolor="#cecece" align="center">
									<tr class="dz">
										<td bgcolor="#eceef0" colspan="9" align="center">
										<form action="deliverQuery" method="post">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0" align="left" height="40"
												style="border: 1px dashed #d4d4d4;">
												<tr class="my">
													<td height="20" align="left" width="30%"
														style="padding-left: 10px;">
														开始日期：
														<input type="text" id="startDate" class="bt_03"
															name="startDate" />&nbsp;<img onClick="WdatePicker({el:$dp.$('startDate')})" src="../datepicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
														结束日期：
														<input type="text" id="endDate" class="bt_03"
															name="endDate" />&nbsp;<img onClick="WdatePicker({el:$dp.$('endDate')})" src="../datepicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
														城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市：
														<s:action name="customerAction!allFindCustomer1" executeResult="true"></s:action>
														<input type="submit" name="Submit" value="查询" class="bt_02" />
													</td>
												</tr>
											</table>
											</form>
										</td>
									</tr>
									<tr class="dz" align="center">
										<td width="10%" height="28"
											background="../resources/images/sale_bg.gif">
											送货单号
										</td>
										<td width="15%" background="../resources/images/sale_bg.gif">
											送货日期
										</td>
										<td width="10%" background="../resources/images/sale_bg.gif">
											操作员
										</td>
										<td width="20%" background="../resources/images/sale_bg.gif">
											城市
										</td>
										<td width="15%" background="../resources/images/sale_bg.gif">出库单号</td>
										<td width="20%" background="../resources/images/sale_bg.gif">客户</td>
										<td width="10%" background="../resources/images/sale_bg.gif">
											商品数量
										</td>
									</tr>
                                    <s:iterator value="allDeliver" status="st" id="all">
											<s:iterator value="#all.goodsdelivers">
												<tr class="my" align="center" bgcolor="#ffffff">
												<td align="right"><s:property value="#all.deliverId"/></td>
												<td align="left"><s:property value="#all.deliverDate"/></td>
												<td align="left"><s:property value="#all.createBy"/></td>
												<td align="left"><s:property value="#all.city"/></td>
												<td align="left"><s:property value="stockOutId"/></td>
												
												<td align="left"><s:property value="productName"/><s:action name="stockout!findCustomer" executeResult="true"><s:param name="stockoutId"><s:property value="stockOutId"/></s:param> </s:action></td>
												<td align="right"><s:property value="qty"/></td>
												</tr>
											</s:iterator>
									</s:iterator>
								</table>
							</td>
							<td width="14"
								style="background: url(../resources/images/tab_16.gif) repeat-y right #d3e7fc;">&nbsp;</td>
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
                                            <a href="deliverQuery?pageNumber=1&cityName=${name }&start=${start }&end=${end }">首页</a> |
                                            <a href="deliverQuery?pageNumber=<s:property value="pageNumber-1"/>&cityName=${name }&start=${start }&end=${end }">上一页</a> |
                                            <a href="deliverQuery?pageNumber=<s:property value="pageNumber+1"/>&cityName=${name }&start=${start }&end=${end }">下一页</a> |
                                            <a href="deliverQuery?pageNumber=<s:property value="totalPage"/>&cityName=${name }&start=${start }&end=${end }">尾页</a>&nbsp;
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