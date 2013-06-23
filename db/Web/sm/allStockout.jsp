<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:select list="allStockout" name="selectStockoutForm" property="qty" size="10" id="select" theme="simple" listKey="stockOutId" listValue="stockOutId"></s:select>