<%@page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
response.setHeader("Pragma", "No-cache"); 
response.setHeader("Cache-Control", "no-cache"); 
response.setDateHeader("Expires", 0); 
%>
<html>
	<head>
		<title>入库管理</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script type="text/javascript"
			src="../resources/js/jquery.js"></script>
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
            width: 100px;
            height: 20px;
            border: 0px;
            background: #0099CC;
            color: #fff;
            font-size: 12px;
        }

        .bt_03 {
            width: 150px;
            height: 15px;
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
<script type="text/javascript">
<!--
//添加列
var k = 1; //定义元素唯一标识符序号k
var s = 1;
function addItem(){
	var detailList = document.getElementById("detailList");
	var i=detailList.rows.length;

	var row = detailList.insertRow(i);
	var rowIndex = detailList.rows[i].rowIndex;
	var j=k;
	document.getElementById("rowId").value=j;
	
	detailList.rows[i].setAttribute("align","center");
	detailList.rows[i].setAttribute("bgcolor","#FFFFFF");
	detailList.rows[i].setAttribute("id","tr"+j);
	
	detailList.rows[i].insertCell(0);

	var text = document.createTextNode(j);
	detailList.rows[i].cells[0].appendChild(text);
	
	detailList.rows[i].insertCell(1);
	var input1 = document.createElement("input");
		input1.setAttribute("type","text");
		input1.setAttribute("size","38");
		input1.setAttribute("readonly","readonly");
		input1.setAttribute("name","stockOutId");
		input1.setAttribute("id","stockoutId"+j);	
	detailList.rows[i].cells[1].appendChild(input1);
	
	detailList.rows[i].insertCell(2);
	var input2 = document.createElement("input");
		input2.setAttribute("type","text");
		input2.setAttribute("size","38");
		input2.setAttribute("name","qty");
		input2.setAttribute("id","qty"+j);
	detailList.rows[i].cells[2].appendChild(input2);

	detailList.rows[i].insertCell(3);
	detailList.rows[i].cells[3].setAttribute("height","25");	
	var deleteRow = document.createElement("a");
	deleteRow.setAttribute("href","javascript:deleteItem("+j+")");
	deleteRow.appendChild(document.createTextNode("删除"));
	detailList.rows[i].cells[3].appendChild(deleteRow);	
	
	k++;	
}

//删除列
function deleteItem(m){
	var myrow = document.getElementById("tr"+m);
	detailList.deleteRow(myrow.rowIndex);
}

function addDeliver(){
	var cid = document.getElementById("city").value;
    /*
	if(cid==10000||cid==null){
		alert("请先选择送货目的城市！");
		return;
	}
    */
    //打开新窗口，选择出库单，并调用该页面的addItem方法，增加到下面表格中
	 var result = window.showModalDialog("selectStockout.jsp","Window","width=400px,height=230px,status=no,resizable=yes,scrollable=yes");
     if(!result)
     	{
     		result = window.returnValue;
     	}
   	document.getElementById("stockoutId"+s).value = result;
   	s++;
}
-->
</script>
	</head>
	<body>
		<form action="deliverAction" method="post">
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td height="30">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="15" height="30"><input type="hidden" id="rowId" value=""/><img src="../resources/images/tab_03.gif" width="15" height="30" /></td>
								<td background="../resources/images/tab_05.gif" align="left"><img src="../resources/images/311.gif" width="16" height="16" /> <span class="STYLE4">送货管理</span></td>
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
																				<td width="34%">
																					送货单号：
																			        <input id="deliverId" type="text" name="deliverId" value="" size="12">
                                                                                 </td>
																				<td width="22%">
																					创建人员：
																			        <input name="createBy" type="text" value="" size="12"></td>
																				<td width="22%">
                                                                                    目的城市：
																					 <s:action name="customerAction!allFindCustomer1" executeResult="true"></s:action>
                                                                               </td>
																				<td width="22%">&nbsp;</td>
																		  </tr>
																	  </table>																	</td>
																</tr>
																<tr align="left" class="dz">
																	<td>
																		<table width="100%" border="0">
																			<tr class="my">
																			    <td width="34%">
                                                                                送货日期：
                                                                                   <input name="deliverDate" type="text" id="deliverDate" size="12">
                                                                                   &nbsp;<img onClick="WdatePicker({el:$dp.$('deliverDate')})" src="../datepicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
																				</td>
																				<td width="22%">
                                                                                   创建时间：
                                                                                  <input name="createTime" type="text" id="createTime" size="12">
                                                                                  &nbsp;<img onClick="WdatePicker({el:$dp.$('createTime')})" src="../datepicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
                                                                                </td>
																				<td width="22%">
                                                                                   状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：
                                                                                    <select name="status">
                                                                                        <option value="激活"> 激活 </option>
                                                                                        <option value="冻结"> 冻结 </option>
                                                                                    </select>
                                                                                </td>
																			  <td width="22%">&nbsp;</td>
																		  </tr>
																	  </table>																	</td>
																</tr>
																<tr class="dz">
																	<td>
																		<table width="100%" border="0">
																			<tr>
																				<td width="56%" class="my">
																					备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：
                                                                                    <input type="text" name="remark" size="80">
                                                                                </td>
																				<td width="44%">
                                                                                    <input type="button" name="Input2" value="添加送货" class="bt_021" onClick="addDeliver()" />
                                                                                </td>
																			</tr>
																	</table>
                                                                 </td>
																</tr>
															</table>
                                                         </td>
													</tr>
												</table>											</td>
										</tr>
										<tr class="dz" align="center">
											<td width="10%" height="25"
												background="../resources/images/sale_bg.gif">
												序号											</td>
											<td width="40%" height="25"
												background="../resources/images/sale_bg.gif">出库单号</td>
											
											<td width="40%" background="../resources/images/sale_bg.gif">
												数量											</td>
											<td width="10%" background="../resources/images/sale_bg.gif">
												操作											</td>
										</tr>
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
									<img src="../resources/images/tab_20.gif" width="15"
										height="29" />
								</td>
								<td background="../resources/images/tab_21.gif">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td colspan="2" width="21%" height="14" class="STYLE1"
												align="right" style="padding-right: 90px;" valign="middle">
												<input type="submit" name="Input" value="提交送货" class="bt_02" />
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
