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
    <title>用户管理</title>
    <link href="../resources/css/hottest.css" rel="stylesheet" type="text/css" />
    <style>
    .STYLE1 {font-size: 12px}
    .bt_02{
        width:60px;
        height:20px;
        border:0px;
        background:#0099CC;
        color:#fff;
        font-size:12px;
        }
    </style>
    <script type="text/javascript">
		function deleteUsers(pid){
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
        <td background="../resources/images/tab_05.gif" align="left">
            <img src="../resources/images/311.gif" width="16" height="16" />
            <span class="STYLE4">用户管理</span>
        </td>
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
            <td bgcolor="#eceef0"  colspan="10" align="center">
                <table width="100%" border="0" cellspacing="0" cellpadding="0" align="left" height="40" style="border:1px dashed #d4d4d4;">
                  <tr width="100%">
                    <td height="20" align="left" width="50%" style="padding-left:10px;">
                  <form action="usersAction!findUsers" method="post" class="STYLE1">
                  用户名：<input type="text" name="username" class="bt_04"/> 
                    <input type="submit" name="Submit" value="查询" class="bt_02" /></form></td>
                    <td width="40%" style="color:red"></td>
                    <td align="right" width="40%" style="padding-left:10px;">
					<td align="right" width="40%" style="padding-left:10px;">
                    <table width="151" border="0" cellspacing="0" cellpadding="0" style="background:url(../resources/images/htbutton.gif) no-repeat; margin:5px; ">
           <tr>
                <td align="center" height="34"><a href="addUsers.jsp" style="font-size:15px; font-weight:bold; color:#fff;">添加用户</a></td>
           </tr>
       	</table></td>
                  </tr>
                </table>  
  </td>
          </tr>
          <tr class="dz" align="center" >
            <td width="10%" height="28"  class="STYLE1" background="../resources/images/sale_bg.gif">用户编号</td>
            <td width="10%"  class="STYLE1" background="../resources/images/sale_bg.gif">用户名</td>
            <td width="15%" class="STYLE1" background="../resources/images/sale_bg.gif">用户类型</td>
            <td width="10%" class="STYLE1" background="../resources/images/sale_bg.gif">部门</td>
            <td width="15%"  class="STYLE1" background="../resources/images/sale_bg.gif">创建时间</td>
            <td width="20%"  class="STYLE1" background="../resources/images/sale_bg.gif">最后登录时间</td>
            <td width="20%"  class="STYLE1" background="../resources/images/sale_bg.gif" colspan="4" >操作</td>
          </tr>
			 <s:iterator value="allUsers" status="st">
             <tr class="my" align="center"  bgcolor="#ffffff">
              <td class="STYLE1"><s:property value="id"/></td>
    		  <td align="left" class="STYLE1"><s:property value="username"/></td>
    		  <td align="center" class="STYLE1"><s:property value="utype"/></td>
    		  <td align="left" class="STYLE1"><s:property value="deptName"/></td>
    		  <td align="left" class="STYLE1"><s:property value="createTime"/></td>
     		  <td align="left" class="STYLE1"><s:property value="lastLoginTime"/></td>
         	  <td width="5%"  class="STYLE1"><a href="usersAction!showUsers?userName=<s:property value="username"/>&pageMes=${name }" >详细</a></td>
    		  <td width="5%" class="STYLE1"><a href="grantsManagement.jsp?usersId=<s:property value="id"/>" >权限</a></td>
    		  <td width="5%" class="STYLE1"><a href="usersAction!showUsers?userName=<s:property value="username"/>&pageMes=''" >修改</a></td>
              <td width="5%" class="STYLE1"><a onclick="return deleteUsers('usersAction!deleteUsers?usersId=<s:property value="id"/>');">删除</a></td>
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
            <a href="usersAction!findUsers?pageNumber=1&usersName=${name }">首页</a> | 
            <a href="usersAction!findUsers?pageNumber=<s:property value="pageNumber-1"/>&usersName=${name }">上一页</a>
             <span><font color="red"></font></span>
                <a href="usersAction!findUsers?pageNumber=<s:property value="pageNumber+1"/>&usersName=${name }">下一页</a> | <a href="usersAction!findUsers?pageNumber=<s:property value="totalPage"/>&usersName=${name }">尾页</a>&nbsp;
            当前第 <span
			style="FONT-WEIGHT: bold; COLOR: #f46521"></span><span class="font_1"><s:property value="pageNumber"/></span>页
          	</td>
          </tr>
        </table></td>
        <td width="14"><img src="../resources/images/tab_22.gif" width="14" height="29" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
