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
	<title>商品分类管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link href="../resources/css/hottest.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		function deleteCate(pid){
			var k = window.confirm("你确定要删除该条记录？");
			if(k){
				window.location.href=pid;
				return true;
			}else{
				return false;
			}
		}
	</script>
</head>

<body>
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td height="30">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr height="30">
						<td width="15"><img src="../resources/images/tab_03.gif" width="15"/></td>
						<td background="../resources/images/tab_05.gif" align="left"><img src="../resources/images/311.gif" width="16" height="16" /> <span class="STYLE4">商品分类管理</span>	</td>
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
									<td bgcolor="#eceef0" colspan="7" align="center">
										<table width="100%" border="0" cellspacing="0" cellpadding="0" align="left" height="40" style="border: 1px dashed #d4d4d4;">
											<tr class="my">
												<td height="20" align="left" width="50%" style="padding-left: 10px;">
													<form action="findCategory" method="post" class="STYLE1">
														分类名称：
														<input type="text" name="categoryName" class="bt_03"/>
														<input type="submit" name="Submit" value="查询" class="bt_02" />
													</form>
												</td>
												<td align="right" width="50%" style="padding-left: 10px;">
													<table width="151" border="0" cellspacing="0"
														cellpadding="0"
														style="background: url(../resources/images/htbutton.gif) no-repeat; margin: 5px;">
														<tr>
															<td align="center" height="34">
																<a href="../bim/addCategory.jsp"
																	style="font-size: 15px; font-weight: bold; color: #fff;">添加分类</a>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr class="dz" align="center">
									<td width="10%" height="28"
										background="../resources/images/sale_bg.gif">
										ID
									</td>
									<td width="25%" background="../resources/images/sale_bg.gif">
										分类名称
									</td>
									<td background="../resources/images/sale_bg.gif" width="25%">
										父类
									</td>
									<td background="../resources/images/sale_bg.gif" width="10%">
										分类层次
									</td>
									<td background="../resources/images/sale_bg.gif" width="10%">
										状态
									</td>
									<td background="../resources/images/sale_bg.gif" width="20%"
										colspan="2">
										修改编辑
									</td>
								</tr>
								<s:iterator value="allCategory" status="st">
                                <tr class="my" align="center" bgcolor="#ffffff">
                                    <td><s:property value="#st.getIndex()+1+(pageNumber-1)*pageSize"/></td>
                                    <td><s:property value="categoryName"/></td>
                                    <td><s:property value="parentcategory"/></td>
                                    <td><s:property value="tlevel"/></td>
                                    <td><s:property value="status"/></td>
                                    <td><a href="findCategory!showCategory?cate=<s:property value="categoryName"/>">修改</a></td>
                                    <td><a onClick="return deleteCate('deleteCategory?cateId=<s:property value="id"/>');">删除</a></td>
                                </tr>
                                </s:iterator>
                                <tbody id="catMes"></tbody>
							</table>
						</td>
						<td width="14"
							style="background: url(../resources/images/tab_16.gif) repeat-y right #d3e7fc;">&nbsp;
							
						</td>
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
										<a href="findCategory?pageNumber=1&categoryName=${name }">首页</a> |
										<a href="findCategory?pageNumber=<s:property value="pageNumber-1"/>&categoryName=${name }">上一页</a> |
										<a href="findCategory?pageNumber=<s:property value="pageNumber+1"/>&categoryName=${name }">下一页</a> |
										<a href="findCategory?pageNumber=<s:property value="totalPage"/>&categoryName=${name }">尾页</a>&nbsp;
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
