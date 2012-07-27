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
	
	<title>Project Detail</title>
</head>

<body id="portal" class="up fl-theme-mist">

<div id="portalPageBodyInner" class="container">
  <div class="content">
  	
  	<!-- begin .navigator -->
	<jsp:include page="Nagivator.jsp" />
	<!-- end .navigator -->
  	
   <div class="fl-widget-titlebar titlebar portlet-titlebar" role="sectionhead">
    	<h2 class="title" >Project Detail</h2>
    </div>
 <div style="border-style:ridge" class="up-portlet-content-wrapper-inner">
  <table class="portlet-table">
  <tr>
        <th scope="row">Project Manager</th>
        <td>${projectManager}</td>
      </tr>
      <tr>
        <th scope="row">Project Status</th>
        <td>${projectStatus}</td>
      </tr>
      <tr>
        <th scope="row">Project Code</th>
        <td>${projectCode}</td>
      </tr>
      <tr>
        <th scope="row">Project Name</th>
        <td>${projectName}</td>
      </tr>
      <tr>
        <th scope="row">Project Category</th>
        <td>${projectCategory}</td>
      </tr>
      <tr>
        <th scope="row">Direct Customer</th>
        <td>${directCustomer}</td>
      </tr>
      <tr>
        <th scope="row">End Customer</th>
        <td>${endCustomer}</td>
      </tr>
      <tr>
        <th scope="row">Business Domain</th>
        <td>${businessDomain}</td>
      </tr>
      <tr>
        <th scope="row">Planned Start Date</th>
        <td>${plannedStartDate}</td>
      </tr>
      <tr>
        <th scope="row">Planned End Date* </th>
        <td>${plannedEndDate}</td>
      </tr>
      <tr>
        <th scope="row">Plan effort</th>
        <td>${planEffort}</td>
      </tr>
      <tr>
        <th scope="row">Actual Effort</th>
        <td>${actualEffort}</td>
      </tr>
      <tr>
        <th scope="row">Scope and Objective</th>
        <td>${scopeAndObjective}</td>
      </tr>
      <tr>
        <th scope="row">Project size</th>
        <td>${projectSize}</td>
      </tr>
      <tr>
        <th scope="row">Progress</th>
        <td>${progress}</td>
      </tr>
      <tr>
        <th scope="row">Time Spent</th>
        <td>${timeSpent}</td>
      </tr>
      <tr>
        <th scope="row">Cost Expense/Budget</th>
        <portlet:renderURL var="renderAction">
    		<portlet:param name="action" value="GoCostManagement" />
        	<portlet:param name="projectId" value="${projectId}" />
    	</portlet:renderURL>
        <td><a href="${renderAction}">${cost}</a></td>
      </tr>
      <tr>
        <th scope="row">Risk,Issue</th>
        <portlet:renderURL var="renderAction">
    		<portlet:param name="action" value="GoRiskIssue" />
        	<portlet:param name="projectId" value="${projectId}" />
    	</portlet:renderURL>
        <td><a href="${renderAction}">${riskIssue}</a></td>
      </tr>
      <tr>
        <th scope="row">Change request</th>
        <portlet:renderURL var="renderAction">
    		<portlet:param name="action" value="GoChangeRequest" />
        	<portlet:param name="projectId" value="${projectId}" />
    	</portlet:renderURL>
        <td><a href="${renderAction}">${changeRequest}</a></td>
      </tr>
      <tr>
        <th scope="row">Project Health</th>
        <td>${projectHealth}</td>
      </tr>
      <tr>
        <th scope="row">Project evaluation</th>
        <td>${projectEvaluation}</td>
      </tr>
  </table>
  <c:if test="${(role == 1) || (role==0)}"> 
	<portlet:renderURL var="renderAction">
    	<portlet:param name="action" value="goUpdateProject" />
        <portlet:param name="projectId" value="${projectId}" />
    </portlet:renderURL>
    <a href="${renderAction}">Change Project Info</a>
    
    <portlet:renderURL var="renderAction">
    	<portlet:param name="action" value="goTeamManagement" />
        <portlet:param name="projectId" value="${projectId}" />
    </portlet:renderURL>
    <br/><a href="${renderAction}">Team Management</a>
 </c:if>
 <c:if test="${(role == 6) || (role==0)}">
 <portlet:renderURL var="renderAction">
    	<portlet:param name="action" value="GoAssignProjectManager" />
        <portlet:param name="projectId" value="${projectId}" />
    </portlet:renderURL>
    <br/><a href="${renderAction}">Assign Project Manager</a> 
 </c:if>
</div>
  <!-- end .content --></div>
  <!-- end .container --></div>
</body>
</html>
