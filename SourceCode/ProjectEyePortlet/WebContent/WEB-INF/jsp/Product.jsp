<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<portlet:defineObjects />
<link rel="icon" href="https://c15027075.ssl.cf2.rackcdn.com/favicon.ico" type="image/x-icon"/>
<link type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/screen.css" rel="Stylesheet" />
<link type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/ui-lightness/jquery-ui-1.8.21.custom.css" rel="Stylesheet" />	
<link type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/common.css" rel="Stylesheet" />	
<link type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/uportal.css" rel="Stylesheet" />
<link rel="stylesheet" type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/print.css" media="print"/>
<link rel="stylesheet" type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/manage.css" media="all"/>				
<link rel="stylesheet" type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/datepicker.css" media="all"/>
<link rel="fluid-icon" href="https://c15027075.ssl.cf2.rackcdn.com/images/apple-touch-icon-114x114.png"/>
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/jquery-ui-1.8.21.custom.min.js"></script>
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/form-elements.js"></script>
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/css/ga.js"></script>
<script language="javascript" type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/css/jquery.js"></script>
<script language="javascript" type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/css/jquery.cookie.js"></script>
<script language="javascript" type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/css/default.js"></script>
<script language="javascript" type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/css/manage.js"></script>
<script language="javascript" type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/common.js"></script>
<meta name="robots" content="noindex, nofollow"/>

<title>Product</title>	
</head>

<body id="portal" class="up fl-theme-mist">

<div id="portalPageBodyInner" class="container">

  <div class="content">

	<!-- begin .navigator -->
	<jsp:include page="Nagivator.jsp" />
	<!-- end .navigator -->
	
	   <div class="fl-widget-titlebar titlebar portlet-titlebar" role="sectionhead">
    	<h2 class="title" >Product</h2>
    </div>
    
    <div style="border-style:ridge" class="up-portlet-content-wrapper-inner">
    <portlet:actionURL var="formAction">
  		<portlet:param name="action" value="SearchProduct" />
  		<portlet:param name="projectId" value="${projectId}" />
	</portlet:actionURL>
<form:form name="${portletNamespace}SearchProduct" commandName="ProductForm" method="post" action="${formAction}">
    Product List <form:select  class="styled" path="workProduct_SelectedValue" items="${workProduct}"/>
    <button type="button" class="button blue small" onclick='submitAction("${portletNamespace}SearchProduct", "${formAction}")'>View</button> 
</form:form>
    <table class="portlet-table">
	<c:if test="${not empty projectProductList}">
   <tbody><tr >
        <th width="6%" scope="row">No</th>    
        <th width="10%" scope="row">Name</th>
        <th width="10%" scope="row">Work product</th>
        <th width="6%" scope="row">Planned size (UCP)</th>
        <th width="6%" scope="row">Re-planned size (UCP)</th>
        <th width="6%" scope="row">Actual size (UCP)</th>
        <th width="6%" scope="row">Created size (UCP) </th>
        <th width="50%" scope="row">Description</th>  
    </tr>  
        <c:forEach var="product" items="${projectProductList}" varStatus="count">
            <tr>
            <portlet:renderURL var="renderAction">
            	<portlet:param name="action" value="GoProjectDetail" />
            	<portlet:param name="projectId" value="${project.projectId}" />
            </portlet:renderURL>
               <td scope="row">${count.count}</td>
               <td scope="row">${product.name}</td>
               <td scope="row">${product.workProduct}</td>
               <td scope="row">${product.plannedSize}</td>
               <td scope="row">${product.rePlannedSize}</td>
               <td scope="row">${product.actualSize}</td>
               <td scope="row">${product.createdSize}</td>
               <td scope="row">${product.description}</td>                                 
            </tr>
        </c:forEach>
   </tbody>
	</c:if>
	<c:if test="${empty projectChangeRequestList}">
		There is no product
	</c:if>
    </table>
		<portlet:renderURL var="renderAction">
    		<portlet:param name="action" value="GoCreateProduct" />
        	<portlet:param name="projectId" value="${projectId}" />
    	</portlet:renderURL>
    	<a href="${renderAction}">Add New Product</a>
	</div>

  <!-- end .content --></div>
  <!-- end .container --></div>
</body>
</html>
