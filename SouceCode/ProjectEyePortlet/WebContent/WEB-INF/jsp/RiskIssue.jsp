<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Risk, Issue</title>
<link rel="icon" href="https://c15027075.ssl.cf2.rackcdn.com/favicon.ico" type="image/x-icon"/>
<link type="text/css" href="../OOPMSPortlet/resource_files/css/screen.css" rel="Stylesheet" />
<link type="text/css" href="../OOPMSPortlet/resource_files/css/ui-lightness/jquery-ui-1.8.21.custom.css" rel="Stylesheet" />	
<link type="text/css" href="../OOPMSPortlet/resource_files/css/common.css" rel="Stylesheet" />	
<link type="text/css" href="../OOPMSPortlet/resource_files/css/uportal.css" rel="Stylesheet" />
<link rel="stylesheet" type="text/css" href="../OOPMSPortlet/resource_files/css/print.css" media="print">
<script type="text/javascript" src="../OOPMSPortlet/resource_files/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../OOPMSPortlet/resource_files/js/jquery-ui-1.8.21.custom.min.js"></script>
<script type="text/javascript" src="../OOPMSPortlet/resource_files/js/form-elements.js"></script>
<meta name="robots" content="noindex, nofollow">
			
	<link rel="stylesheet" type="text/css" href="../OOPMSPortlet/resource_files/css/manage.css" media="all">				
	<link rel="stylesheet" type="text/css" href="../OOPMSPortlet/resource_files/css/datepicker.css" media="all">
	<link rel="fluid-icon" href="https://c15027075.ssl.cf2.rackcdn.com/images/apple-touch-icon-114x114.png">
	<script type="text/javascript" async="" src="../OOPMSPortlet/resource_files/css/ga.js"></script>
	<script language="javascript" type="text/javascript" src="../OOPMSPortlet/resource_files/css/jquery.js"></script>
	<script language="javascript" type="text/javascript" src="../OOPMSPortlet/resource_files/css/jquery.cookie.js"></script>
	<script language="javascript" type="text/javascript" src="../OOPMSPortlet/resource_files/css/default.js"></script>
	<script language="javascript" type="text/javascript" src="../OOPMSPortlet/resource_files/css/manage.js"></script>
	
</head>

<body id="portal" class="up fl-theme-mist">

<div id="portalPageBodyInner" class="container">

  <div class="content">
   <div class="fl-widget-titlebar titlebar portlet-titlebar" role="sectionhead">
    	<h2 class="title" >Risk, Issue</h2>
    </div>
    
    <!-- begin .navigator -->
	<div style="border-style:ridge" class="up-portlet-content-wrapper-inner">
    <div class="demo" style="font-size:17px">
 		<portlet:renderURL var="renderAction">
    		<portlet:param name="jspPage" value="/ProjectEyeHome.jsp" />
    	</portlet:renderURL>
		<a href="${renderAction}">ProjectEye Home</a>
		
		<portlet:renderURL var="renderAction">
    		<portlet:param name="action" value="GoRiskIssue" />
        	<portlet:param name="projectId" value="${projectId}" />
    	</portlet:renderURL>
    	<a href="${renderAction}">Risk, Issue</a>
    	
    	<portlet:renderURL var="renderAction">
    		<portlet:param name="action" value="GoChangeRequest" />
        	<portlet:param name="projectId" value="${projectId}" />
    	</portlet:renderURL>
    	<a href="${renderAction}">Change Request</a>
    	
    	<portlet:renderURL var="renderAction">
    		<portlet:param name="action" value="GoProduct" />
        	<portlet:param name="projectId" value="${projectId}" />
    	</portlet:renderURL>
    	<a href="${renderAction}">Product</a>
    	
    	<portlet:renderURL var="renderAction">
    		<portlet:param name="action" value="GoWorkOrder" />
        	<portlet:param name="projectId" value="${projectId}" />
    	</portlet:renderURL>
    	<a href="${renderAction}">Work Order</a>
    	
    	<portlet:renderURL var="renderAction">
    		<portlet:param name="action" value="GoCostManagement" />
        	<portlet:param name="projectId" value="${projectId}" />
    	</portlet:renderURL>
    	<a href="${renderAction}">Cost Management</a>	
	</div>
	</div>
	<!-- end .navigator -->
	
  <!-- end .content --></div>
  <!-- end .container --></div>
</body>
</html>
