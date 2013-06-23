<%@page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
response.setHeader("Pragma", "No-cache"); 
response.setHeader("Cache-Control", "no-cache"); 
response.setDateHeader("Expires", 0); 
%>
<html>
	<head>
		<title>盘点管理</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script type="text/javascript" src="../resources/js/jquery.js"></script>
        <script defer="defer" language="javascript" type="text/javascript" src="../datepicker/WdatePicker.js"></script>
		<link href="../resources/css/hottest.css" rel="stylesheet" type="text/css" />
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

	var text = document.createTextNode(j);
	detailList.rows[i].cells[0].appendChild(text);
	
	detailList.rows[i].insertCell(1);
	var input10 = document.createElement("input");
		input10.setAttribute("type","hidden");
		input10.setAttribute("name","productId");
		input10.setAttribute("id","productId"+j);	
	detailList.rows[i].cells[1].appendChild(input10);
	var input1 = document.createElement("input");
		input1.setAttribute("type","text");
		input1.setAttribute("size","38");
		input1.setAttribute("name","productName");
	//	input1.setAttribute("disabled","true");
		input1.setAttribute("id","productName"+j);	
	detailList.rows[i].cells[1].appendChild(input1);
	var input8 = document.createElement("input");
		input8.setAttribute("type","button");
		input8.setAttribute("value","选择商品");
		input8.onclick= selectProduct;
	detailList.rows[i].cells[1].appendChild(input8);	
	
	detailList.rows[i].insertCell(2);
	var input2 = document.createElement("input");
		input2.setAttribute("type","text");
		input2.setAttribute("size","26");
		input2.setAttribute("name","qty");
		input2.setAttribute("id","qty"+j);
	detailList.rows[i].cells[2].appendChild(input2);
	
	detailList.rows[i].insertCell(3);
	var input3 = document.createElement("input");
		input3.setAttribute("type","text");
		input3.setAttribute("size","26");
		input3.setAttribute("name","checkQty");
	detailList.rows[i].cells[3].appendChild(input3);
		
	detailList.rows[i].insertCell(4);
	detailList.rows[i].cells[4].setAttribute("height","25");	
	var deleteRow = document.createElement("a");
	deleteRow.setAttribute("href","javascript:deleteItem("+j+")");
	deleteRow.appendChild(document.createTextNode("删除"));
	detailList.rows[i].cells[4].appendChild(deleteRow);	
	
	//选择商品
	function selectProduct()
    {
		var checkSid = document.getElementById("shelf").value;
		//alert(checkSid);
		if(checkSid==10000){
			alert("请先选择所要盘点的货架！");
			return;
		}
        //实际编码时调该方法
		//window.open("<%=request.getContextPath()%>/selectProduct.jsp?no="+j+"&checkSid="+checkSid,"Window","width=400px,height=230px,status=no,resizable=yes,scrollable=yes");
        //测试用,实际编码时去掉该句
		  var result = window.showModalDialog("selectProduct.jsp","Window","width=400px,height=230px,status=no,resizable=yes,scrollable=yes");
	        if(!result)
	        	{
	        		result = window.returnValue;
	        	}
	      	document.getElementById("productName"+j).value = result;
	      	 $("#qty"+j).load("goodsStockin!sumQty?productName="+result,function(responseTxt,statusTxt,xhr){
	  		      if(statusTxt=="success")
	  		       	document.getElementById("qty"+j).value = responseTxt;
	  		      if(statusTxt=="error")
	  		        alert("Error: "+xhr.status+": "+xhr.statusText);
	  		    });
	}
	
	k++;	
}

//删除列
function deleteItem(m){
	var myrow = document.getElementById("tr"+m);
	detailList.deleteRow(myrow.rowIndex);
}
-->
</script>
	</head>

	<body>
		<form action="checkAction" method="post">
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td height="30">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="15" height="30"><img src="../resources/images/tab_03.gif" width="15" height="30" /></td>
								<td background="../resources/images/tab_05.gif" align="left"><img src="../resources/images/311.gif" width="16" height="16" /> <span class="STYLE4">盘点管理</span></td>
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
								<td width="97%" bgcolor="#FFFFFF" height="500" valign="top" align="center">
									<table id="detailList" width="100%" border="0" cellspacing="1"
										cellpadding="0" bgcolor="#cecece" align="center">
										<tr class="dz">
											<td bgcolor="#eceef0" colspan="7" align="center">
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
																					盘点单号：
																					  <input id="checkId" type="text" name="checkId"
																						value="" size="12"></td>
																				<td width="25%">
																					创建人员：
																					<input name="createBy" type="text" value=""
																						size="12"></td>
																			  <td width="25%">
																					货&nbsp;&nbsp;架&nbsp;&nbsp;号：
                                                                                      <s:action name="shelfAction!allFindShelf1" executeResult="true"></s:action>
																				<td width="25%">&nbsp;</td>
																		  </tr>
																	  </table>																	</td>
																</tr>
																<tr align="left" class="dz">
																	<td>
																		<table width="100%" border="0">
																			<tr class="my">
																				<td width="25%">盘点日期：
                                                                                    <input name="checkDate" type="text" id="checkDate" size="12">
                                                                                    &nbsp;<img onClick="WdatePicker({el:$dp.$('checkDate')})" src="../datepicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
                                                                                </td>
																				<td width="25%">创建时间：
																					<input name="createTime" type="text" id="createTime" size="12">
                                                                                    &nbsp;<img onClick="WdatePicker({el:$dp.$('createTime')})" src="../datepicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
                                                                                </td>
																			  <td width="25%">
                                                                               状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：
                                                                                  <select name="status">
                                                                                    <option value="激活"> 激活 </option>
                                                                                    <option value="冻结"> 冻结 </option>
                                                                                  </select>
                                                                              </td>
																				<td width="25%">&nbsp;</td>
																		  </tr>
																	  </table></td>
																</tr>
																<tr class="dz">
																	<td>
																		<table width="100%" border="0">
																			<tr>
																				<td width="75%" class="my">
																					备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：
																			  <input type="text" name="remark" size="80">																			  </td>
																				<td width="25%"><input type="button" name="Input2" value="添加盘点" class="bt_021" onClick="addItem()" /></td>
																			</tr>
																	</table></td>
																</tr>
															</table></td>
													</tr>
												</table></td>
										</tr>
										<tr class="dz" align="center">
											<td width="10%" height="25"
												background="../resources/images/sale_bg.gif">
												序号											</td>
											<td width="50%" height="25"
												background="../resources/images/sale_bg.gif">
												商品名称 											</td>
											
											<td width="15%" background="../resources/images/sale_bg.gif">
												数量											</td>
											<td width="15%" background="../resources/images/sale_bg.gif">实际库存</td>
											<td width="10%" background="../resources/images/sale_bg.gif">操作</td>
										</tr>
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
									<img src="../resources/images/tab_20.gif" width="15"
										height="29" />
								</td>
								<td background="../resources/images/tab_21.gif">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td colspan="2" width="21%" height="14" class="STYLE1"
												align="right" style="padding-right: 90px;" valign="middle">
												<input type="submit" name="Input" value="提交盘点" class="bt_02" />
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
