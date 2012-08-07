<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />	
</head>

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

