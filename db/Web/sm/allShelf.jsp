<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:select list="allShelf"  name="selectShelfForm" property="shelfName" size="10" id="select" theme="simple" listKey="shelfName" listValue="shelfName" headerKey="0" headerValue="请选择货架"></s:select>