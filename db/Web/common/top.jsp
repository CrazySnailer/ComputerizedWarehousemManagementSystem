<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>电子化仓储管理系统</title>
<link href="../resources/css/hottest.css" rel="stylesheet" type="text/css" />
<style type="text/css" >
    #top{width:100%;height:75px;}
    #top_up{width:100%;height:50px;background:url(../resources/images/main_03.gif);}
    #top_down{width:100%;height:26px;background:url(../resources/images/main_22.gif);}
    #top_up_center{float:right;height:18px;margin-top:30px;}
    #top_up_center li{float:left; margin-left:2px; list-style:none; }
    #top_down_left{width:360px;height:50px;background:url(../resources/images/ht111.gif);float:left}
    #top_down_center{float:left;}
    #top_down_right{width:600px; margin-top:7px; float:right; text-align:right;}
</style>

</head>

<body>
 <div id="top">
           <div id="top_up">
		   <div id="top_down_left"></div>
<div id="top_up_center">
                     <li><a href="#"><img src="../resources/images/main_06.gif" border="0" /></a>
                     <li><a href="#"><img src="../resources/images/main_08.gif" border="0" /></a>
                     <li><a href="#"><img src="../resources/images/main_10.gif" border="0" /></a>
                     <li><a href="#"><img src="../resources/images/main_12.gif" border="0" /></a>
                     <li><a href="#"><img src="../resources/images/main_14.gif" border="0" /></a>
             </div>
   </div>
           <div id="top_down">
             <div id="top_down_center"></div>
                <div id="top_down_right"><span class="fon">当前用户：${username }</span><span class="title_04"></span><span class="fon">&nbsp;&nbsp;&nbsp;&nbsp;</span></div>
   </div>      
      </div>

</body>
</html>
