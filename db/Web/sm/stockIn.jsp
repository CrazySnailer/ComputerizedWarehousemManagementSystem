<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
response.setHeader("Pragma", "No-cache"); 
response.setHeader("Cache-Control", "no-cache"); 
response.setDateHeader("Expires", 0); 
%>
<html>
	<head>
		<title>入库管理</title>
        <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="../resources/js/jquery.js"></script>
		<link href="../resources/css/hottest.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
        <!--
        body {
            margin-left: 0px;
            margin-top: 0px;
            margin-right: 0px;
            margin-bottom: 0px;
            font-size: 12px;
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
            width: 100px;
            height: 20px;
            border: 0px;
            background: #0099CC;
            color: #fff;
            font-size: 12px;
        }

        .bt_03 {
            height: 18px;
        }

        .bt_021 {
            width: 100px;
            height: 20px;
            border: 0px;
            background: #0099CC;
            color: #fff;
            font-size: 12px;
        }
        -->
        </style>
        <script defer="defer" language="javascript" type="text/javascript" src="../datepicker/WdatePicker.js"></script>
	</head>
<script type="text/javascript">
<!--
//添加列
var k = 1; //定义元素唯一标识符序号k
function addItem(){
	var detailList = document.getElementById("detailList");
	var i=detailList.rows.length;

	var row = detailList.insertRow(i);
	var rowIndex = detailList.rows[i].rowIndex;
	var j=k;
	
	detailList.rows[i].setAttribute("align","center");
	detailList.rows[i].setAttribute("bgcolor","#FFFFFF");
	detailList.rows[i].setAttribute("id","tr"+j);
	
	detailList.rows[i].insertCell(0);

	var text = document.createTextNode(rowIndex-1);
	detailList.rows[i].cells[0].appendChild(text);
	
	detailList.rows[i].insertCell(1);
	var input10 = document.createElement("input");
		input10.setAttribute("type","hidden");
		input10.setAttribute("name","productId");
		input10.setAttribute("id","productId"+j);	
	detailList.rows[i].cells[1].appendChild(input10);
	var input1 = document.createElement("input");
		input1.setAttribute("type","text");
		input1.setAttribute("size","30");
		input1.setAttribute("name","productName");
//		input1.setAttribute("disabled","true");
		input1.setAttribute("id","productName"+j);	
	detailList.rows[i].cells[1].appendChild(input1);
	var input8 = document.createElement("input");
		input8.setAttribute("type","button");
		input8.setAttribute("value","选择商品");
		input8.onclick= selectProduct;
	detailList.rows[i].cells[1].appendChild(input8);
	
	detailList.rows[i].insertCell(2);
	var input20 = document.createElement("input");
		input20.setAttribute("type","hidden");
		input20.setAttribute("name","shelfId");
		input20.setAttribute("id","shelfId"+j);
	detailList.rows[i].cells[2].appendChild(input20);
	var input2 = document.createElement("input");
		input2.setAttribute("type","text");
		input2.setAttribute("size","25");
		input2.setAttribute("name","shelfName");
//		input2.setAttribute("disabled","true");
		input2.setAttribute("id","shelfName"+j);
	detailList.rows[i].cells[2].appendChild(input2);
	var input9 = document.createElement("input");
		input9.setAttribute("type","button");
		input9.setAttribute("value","选择货架");
		input9.onclick= selectShelf;
	detailList.rows[i].cells[2].appendChild(input9);	
	
	detailList.rows[i].insertCell(3);
	var input3 = document.createElement("input");
		input3.setAttribute("type","text");
		input3.setAttribute("size","26");
		input3.setAttribute("name","qty");
	detailList.rows[i].cells[3].appendChild(input3);
		
	detailList.rows[i].insertCell(4);
	detailList.rows[i].cells[0].setAttribute("height","25");	
	var deleteRow = document.createElement("a");
	deleteRow.setAttribute("href","javascript:deleteItem("+j+")");
	deleteRow.appendChild(document.createTextNode("删除"));
	detailList.rows[i].cells[4].appendChild(deleteRow);
	
	//选择商品
	function selectProduct()
    {
		//alert(j);
		//实际编码时调该方法
		//window.open("<%=request.getContextPath()%>/selectProduct.jsp?no="+j,"Window","width=400px,height=230px,status=no,resizable=yes,scrollable=yes");
        //测试用,实际编码时去掉该句
        var result = window.showModalDialog("selectProduct.jsp","Window","width=400px,height=230px,status=no,resizable=yes,scrollable=yes");
        if(!result)
        	{
        		result = window.returnValue;
        	}
      	document.getElementById("productName"+j).value = result;
	}
	
	//选择货架
	function selectShelf()
    {
        //实际编码时调该方法
		//window.open("<%=request.getContextPath()%>/selectShelf.jsp?no="+j,"Window","width=400px,height=200px,status=no,resizable=yes,scrollable=yes");
        //测试用,实际编码时去掉该句
        var result = window.showModalDialog("selectShelf.jsp","Window","width=400px,height=200px,status=no,resizable=yes,scrollable=yes");
        if(!result)
    	{
    		result = window.returnValue;
    	}
  		document.getElementById("shelfName"+j).value = result;
	}

	k++;
}

//删除列
function deleteItem(m){
	var myrow = document.getElementById("tr"+m);
	detailList.deleteRow(myrow.rowIndex);
	mylength=detailList.rows.length;
	for(i=2;i<mylength;i++){
		detailList.rows[i].cells[0].innerHTML=i-1;
	}
}
-->
</script>
	<body class="my">
		<form action="stockin" method="post">
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td height="30">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="15" height="30"><img src="../resources/images/tab_03.gif" width="15" height="30" /></td>
								<td background="../resources/images/tab_05.gif" align="left"><img src="../resources/images/311.gif" width="16" height="16" /> <span class="STYLE4">入库管理</span></td>
								<td width="14"><img src="../resources/images/tab_07.gif" width="14" height="30" /></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="15" style="background: url(../resources/images/tab_12.gif) repeat-y left #d3e7fc;">&nbsp;</td>
								<td width="97%" bgcolor="#FFFFFF" height="500" valign="top"
									align="center">

									<table id="detailList" width="100%" border="0" cellspacing="1"
										cellpadding="0" bgcolor="#cecece" align="center">
										<tr class="dz">
											<td bgcolor="#eceef0" colspan="6" align="center">
												<table width="101%" border="0" cellspacing="0"
													cellpadding="0" align="left" height="20%"
													style="border: 1px dashed #d4d4d4;">
													<tr>
														<td colspan="7" height="2" align="left"
															style="padding-left: 10px;">
													<tr>
														<td width="84%" height="52">
															<table width="100%" border="0">
																<tr class="dz">
																  <td>
																    <table width="100%" border="0">
																			<tr class="my">
																				<td width="25%">
																					入库单号：
																					<input id="stockInId" type="text" name="stockInId" value="" size="12"></td>
																			  <td width="25%">入库日期：
                                                                                    <input name="stockInDate" type="text" id="stockInDate" size="12">&nbsp;
                                                                                        <img onClick="WdatePicker({el:$dp.$('stockInDate')})" src="../datepicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
                                                                              </td>
																				<td width="25%">入库类型：
                                                                                  <select id="select" name="stockInType">
                                                                                    <option value="2" selected> 调整入库 </option>
                                                                                    <option value="1" selected> 普通入库 </option>
                                                                                  </select></td>
																				<td width="25%">供货批次：
                                                                                <input name="batchNo" type="text"
																						id="stockInDate32" size="8"></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr align="left" class="dz">
																	<td>
																		<table width="100%" border="0">
																			<tr class="my">
																				<td width="25%">创建人员：
                                                                                <input name="createBy" type="text" value=""
																						size="12"></td>
																				<td width="25%">创建时间：
                                                                                    <input name="createTime" type="text" id="createTime" size="12">&nbsp;
                                                                                    <img onClick="WdatePicker({el:$dp.$('createTime')})" src="../datepicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
                                                                                </td>
																				<td width="25%">供&nbsp;&nbsp;应&nbsp;&nbsp;商：
                                                                                	<s:action name="vendorAction!findAllVendor" executeResult="true"></s:action>
																				<td width="25%">
                                                                                   <select name="status">
                                                                                        <option value="激活"> 激活 </option>
                                                                                        <option value="冻结"> 冻结 </option>
                                                                                   </select>
                                                                                 </td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr class="dz">
																	<td>
																		<table width="100%" border="0">
																			<tr>
																				<td width="75%" class="my">
																					备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：
																					<input type="text" name="remark" size="80">
																			  </td>
																				<td width="25%">
																					<input type="button" name="Input2" value="添加入库"
																						class="bt_021" onClick="addItem();" />
																			  </td>
																			</tr>
																		</table>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr class="dz" align="center">
											<td width="7%" height="25"
												background="../resources/images/sale_bg.gif">
												序号
											</td>
											<td width="28%" height="25"
												background="../resources/images/sale_bg.gif">
												商品名称
											</td>
											<td width="35%" height="25"
												background="../resources/images/sale_bg.gif">
												货架名称
											</td>
											<td width="20%" background="../resources/images/sale_bg.gif">
												数量
											</td>
											<td width="10%" background="../resources/images/sale_bg.gif">
												操作
											</td>
										</tr>

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
									<img src="../resources/images/tab_20.gif" width="15"
										height="29" />
								</td>
								<td background="../resources/images/tab_21.gif">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td colspan="2" width="21%" height="14" class="STYLE1"
												align="right" style="padding-right: 90px;" valign="middle">
												<input type="submit" name="Input" value="提交入库" class="bt_02" />
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
		</form>
	</body>
</html>
