<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:select list="allProduct" name="product" width="20" id="select" theme="simple" listKey="productName" listValue="productName" headerKey="0" headerValue="请选择商品"></s:select>