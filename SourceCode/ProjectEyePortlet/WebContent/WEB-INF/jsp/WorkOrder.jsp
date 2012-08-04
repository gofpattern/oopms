<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="robots" content="noindex, nofollow"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
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
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/yav.js"></script>
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/yav-config.js"></script>
<script language="javascript" type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/css/jquery.js"></script>
<script language="javascript" type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/css/jquery.cookie.js"></script>
<script language="javascript" type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/css/default.js"></script>
<script language="javascript" type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/css/manage.js"></script>
<script language="javascript" type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/common.js"></script>
	
</head>

<body id="portal" class="up fl-theme-mist">

<div id="portalPageBodyInner" class="container">

  <div class="content">
  <!-- begin .navigator -->
	<jsp:include page="Nagivator.jsp" />
	<!-- end .navigator -->
  
   <div class="fl-widget-titlebar titlebar portlet-titlebar" role="sectionhead">
    	<h2 class="title" >Work Order</h2>
    </div>
	
<form:form name="${portletNamespace}WorkOrder" method="post" action="${formAction}">
   	<table class="portlet-table">

	<c:if test="${not empty stageList}">
   <tbody><tr >
   		<th width="5%" scope="row">No</th>
        <th width="15%" scope="row">Stage</th>    
        <th width="10%" scope="row">Number of iterations</th> 
        <th width="10%" scope="row">Planned end date</th>
        <th width="10%" scope="row">Re-planned end date</th>
        <th width="25%" scope="row">Description</th>
        <th width="25%" scope="row">Milestone</th>   
    </tr>
    
  
        <c:forEach var="stage" items="${stageList}" varStatus="count">
            <tr>
            <portlet:renderURL var="renderAction">
            	<portlet:param name="action" value="GoUpdateStage" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="stageId" value="${stage.milestoneId}" />
            </portlet:renderURL>
               <td scope="row">${count.count}</td>
               <td scope="row"><a href="${renderAction}">${stage.name}</a></td>
               <td scope="row"></td>
               <td scope="row">${stage.baseFinishDate}</td>
               <td scope="row">${stage.planFinishDate}</td>
               <td scope="row">${stage.description}</td>
               <td scope="row">${stage.milestone}</td>
            </tr>
        </c:forEach>
    </tbody>
    </c:if>
    </table>
    
    <table class="portlet-table">

	<c:if test="${not empty deliverableList}">
   <tbody><tr >
   		<th width="5%" scope="row">No</th>
        <th width="15%" scope="row">Stage</th>    
        <th width="10%" scope="row">First committed date</th> 
        <th width="10%" scope="row">Last committed date</th>
        <th width="10%" scope="row">Actual committed date</th>
        <th width="25%" scope="row">Status</th>
        <th width="25%" scope="row">Note</th>   
    </tr>
      <c:if test="${not empty deliverableList[0]}">conme1</c:if>
      <c:if test="${not empty deliverableList[1]}">conme2</c:if>
      <c:if test="${not empty deliverableList[2]}">conme3</c:if>
      <c:if test="${not empty deliverableList[3]}">conme4</c:if>
      <c:if test="${not empty deliverableList[4]}">conme5</c:if>
      <c:if test="${not empty deliverableList[5]}">conme6</c:if>
        
    </tbody>
    </c:if>
    </table> 	
    	<portlet:renderURL var="renderAction2">
    		<portlet:param name="action" value="GoCreateDeliverable" />
        	<portlet:param name="projectId" value="${projectId}" />
    	</portlet:renderURL>
    	<br/><button type="button" class="button blue small" onclick='submitAction("${portletNamespace}WorkOrder", "${renderAction2}")'>Add New Deliverable</button>	
</form:form>
	</div>
	
  <!-- end .content --></div>
  <!-- end .container --></div>
</body>
</html>
