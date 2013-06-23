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
    <title>商品品牌管理</title>
	<link href="../resources/css/hottest.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		function deleteBrand(pid){
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
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="30"><img src="../resources/images/tab_03.gif" width="15" height="30" /></td>
        <td background="../resources/images/tab_05.gif" align="left"><img src="../resources/images/311.gif" width="16" height="16" /> <span class="STYLE4">商品品牌管理</span></td>
        <td width="14"><img src="../resources/images/tab_07.gif" width="14" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" style="background:url(../resources/images/tab_12.gif) repeat-y left #d3e7fc; ">&nbsp;</td>
        <td width="97%" bgcolor="#FFFFFF" height="500" valign="top" align="center">
		<table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#cecece" align="center">
          <tr class="dz">
            <td bgcolor="#eceef0"  colspan="5" align="center">
                <table width="100%" border="0" cellspacing="0" cellpadding="0"
				align="left" height="40" style="border:1px dashed #d4d4d4;">
                  <tr >
                    <td height="20" align="left" width="50%" style="padding-left:10px;">
                    <form action="brandAction!findBrand" method="post" class="STYLE1"> 
                    品牌名称：<input type="text" name="brandName" class="bt_03"/>
                     <input type="submit" name="Submit" value="查询" class="bt_02" /></form></td>
                    <td align="right" width="50%" style="padding-left:10px;">
					<table width="151" border="0" cellspacing="0" cellpadding="0" style="background:url(../resources/images/htbutton.gif) no-repeat; margin:5px; ">
                 <tr>
                 <td align="center" height="34" ><a href="addBrand.jsp" style="font-size:15px; font-weight:bold;color:#fff; ">添加品牌</a></td>
                 </tr>
       	       </table>
       	    </td>
                  </tr>
                </table>            </td>
          </tr>
          <tr class="dz" align="center" >
            <td width="20%" height="28" background="../resources/images/sale_bg.gif">ID</td>
            <td width="20%" background="../resources/images/sale_bg.gif">品牌名称</td>
            <td background="../resources/images/sale_bg.gif" width="50%">描述</td>
            <td width="10%" background="../resources/images/sale_bg.gif" colspan="2" >修改编辑</td>
          </tr>
           <s:iterator value="allBrand" status="st">
          <tr class="my" align="center"  bgcolor="#ffffff">
              <td><s:property value="#st.getIndex()+1+(pageNumber-1)*pageSize"/></td>
    		  <td align="left"><s:property value="brandName"/></td>
    		  <td align="left"><s:property value="comments"/><br></td>
    		  <td><a href="brandAction?brandName=<s:property value="brandName"/>">修改</a></td>
              <td><a onclick="return deleteBrand('brandAction!deleteBrand?brandId=<s:property value="id"/>');">删除</a></td>
            </tr>
            </s:iterator>
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
            <td width="79%" class="STYLE1" align="right"> 
            <a href="brandAction!findBrand?pageNumber=1&brandName=${name }">首页</a> | 
            <a href="brandAction!findBrand?pageNumber=<s:property value="pageNumber-1"/>&brandName=${name }">上一页</a>
             <a href="brandAction!findBrand?pageNumber=<s:property value="pageNumber+1"/>&brandName=${name }">上一页</a>
            <a href="brandAction!findBrand?pageNumber=<s:property value="totalPage"/>&brandName=${name }">尾页</a>&nbsp;
            当前第 <span style="FONT-WEIGHT: bold; COLOR: #f46521"><s:property value="pageNumber"/></span>页
          	</td>
          </tr>
    </table></td>
  </tr>
</table>
</table>
</body>
</html>