<%@page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>用户登录</title>
        <link href="resources/css/hottest.css" rel="stylesheet" type="text/css" />
    </head>
    <style>
        body {
            background: url(resources/images/htbg.gif) repeat-x top left;
        }

        .d2 {
            border: 0px;
            background: url(resources/images/htdl.gif) no-repeat;
            font-size:12px;
            font-weight:bold;
            width: 71px;
            height: 25px;
            color: #ffffff;
            margin-left: 13px;
        }
        #htdlbg{ margin: 0 auto;}
        #httab{ margin-left: 370px;}
    </style>
    <body>  
        <div id="htdlbg">
            <div id="httab">
                <form action="loginAction" method="post">
                    <input type="hidden" name="sumit" value="yes"
                        id="systemLogin_execute_sumit" />
                   
                                用户名：<s:textfield name="username" size="18" id="userName" cssStyle="width: 150px; margin-top:140px; "></s:textfield><label>${desc }</label><br>
                   
                            密&nbsp;&nbsp;&nbsp;&nbsp;码：<s:password name="password" size="18" id="pwd" cssStyle="width:150px; margin-top:20px; margin-bottom:20px;"></s:password><br>
                         
                                <input type="submit" id="systemLogin_execute_save" name="save"
                                    value="登录" class="d2" />
                                <input type="reset" name="reset" value="取消" class="d2" />
                  
                </form>
            </div>
        </div>      
    </body>
</html>
