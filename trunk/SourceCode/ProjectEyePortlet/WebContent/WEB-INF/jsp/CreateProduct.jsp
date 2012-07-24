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

<title>Create Product</title>	
</head>

<body id="portal" class="up fl-theme-mist">

<div id="portalPageBodyInner" class="container">

  <div class="content">
  <!-- begin .navigator -->
	<jsp:include page="Nagivator.jsp" />
	<!-- end .navigator -->
   <div class="fl-widget-titlebar titlebar portlet-titlebar">
    	<h2 class="title" >Create Product</h2>
    </div>

<div style="border-style:ridge" class="up-portlet-content-wrapper-inner">
	<portlet:actionURL var="formAction">
  		<portlet:param name="action" value="CreateProduct" />
  		<portlet:param name="projectId" value="${projectId}" />
	</portlet:actionURL>
	<portlet:renderURL var="renderAction">
    		<portlet:param name="action" value="GoProduct" />
        	<portlet:param name="projectId" value="${projectId}" />
  	</portlet:renderURL>
<form:form name="${portletNamespace}CreateProduct" commandName="CreateProductForm" method="post" action="${formAction}">
	<table class="portlet-table">
	  <tr>      
   		<th scope="row">Work product*</th>
    	<td><form:select  class="SmallCombo" path="workProduct_SelectedValue" items="${workProduct}"/></td>
  	  </tr>
  	  <tr>
        <th scope="row">Name*</th>
        <td><textarea rows="10" cols="70" name="name"></textarea></td>
      </tr>
      <tr>      
   		<th scope="row">Planned size unit*</th>
    	<td><form:select  class="SmallCombo" path="plannedSizeUnit_SelectedValue" items="${plannedSizeUnit}"/></td>
  	  </tr>
  	  <tr>
    	<th width="186" scope="row">Planned size*</th>
        <td><input name="plannedSize" value="" maxlength="50" size="50" type="text" /></td>
  	  </tr>
  	  <tr>
    	<th width="186" scope="row">Re-planned size</th>
        <td><input name="rePlannedSize" value="" maxlength="50" size="50" type="text" /></td>
  	  </tr>
  	  <tr>      
   		<th scope="row">Actual size unit</th>
    	<td><form:select  class="SmallCombo" path="actualSizeUnit_SelectedValue" items="${actualSizeUnit}"/></td>
  	  </tr>
  	  <tr>
    	<th width="186" scope="row">Actual size</th>
        <td><input name="actualSize" value="" maxlength="50" size="50" type="text" /></td>
  	  </tr>
  	  <tr>
        <th scope="row">Description</th>
        <td><textarea rows="10" cols="70" name="description"></textarea></td>
      </tr>
  </table>                       
	<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}CreateProduct", "${formAction}")'>Create</button>
	<button type="reset" class="button blue small">Reset</button>
	<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}CreateProduct", "${renderAction}")'>Cancel</button>
</form:form>

</div>
  <!-- end .content --></div>
  <!-- end .container --></div>
</body>
</html>
