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
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/datatable.js"></script>
<link type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/datatable.css" rel="Stylesheet" /> 
<script type="text/javascript">





            function fnFeaturesInit ()
            {
                /* Not particularly modular this - but does nicely :-) */
                $('ul.limit_length>li').each( function(i) {
                    if ( i > 10 ) {
                        this.style.display = 'none';
                    }
                } );
                
                $('ul.limit_length').append( '<li class="css_link">Show more<\/li>' );
                $('ul.limit_length li.css_link').click( function () {
                    $('ul.limit_length li').each( function(i) {
                        if ( i > 5 ) {
                            this.style.display = 'list-item';
                        }
                    } );
                    $('ul.limit_length li.css_link').css( 'display', 'none' );
                } );
            }

            $(document).ready( function() {
        	

                     
                fnFeaturesInit();
                $('#mainTable1').dataTable( {
                    "bFilter": true,
                    "bSort": true,
                    "bJQueryUI": true,
                    "sPaginationType": "full_numbers"
                } );
                $('#mainTable2').dataTable( {
                    "bFilter": true,
                    "bSort": true,
                    "bJQueryUI": true,
                    "sPaginationType": "full_numbers"
                } );
                $('#mainTable3').dataTable( {
                    "bFilter": true,
                    "bSort": true,
                    "bJQueryUI": true,
                    "sPaginationType": "full_numbers"
                } );
                $('#mainTable4').dataTable( {
                    "bFilter": true,
                    "bSort": true,
                    "bJQueryUI": true,
                    "sPaginationType": "full_numbers"
                } );
                $('#mainTable5').dataTable( {
                    "bFilter": true,
                    "bSort": true,
                    "bJQueryUI": true,
                    "sPaginationType": "full_numbers"
                } );
                $('#mainTable6').dataTable( {
                    "bFilter": true,
                    "bSort": true,
                    "bJQueryUI": true,
                    "sPaginationType": "full_numbers"
                } );
                $('#mainTable7').dataTable( {
                    "bFilter": true,
                    "bSort": true,
                    "bJQueryUI": true,
                    "sPaginationType": "full_numbers"
                } );

            } );
        </script>	
</head>

<body id="portal" class="up fl-theme-mist">

<div id="portalPageBodyInner" class="container"><%@ include file="/WEB-INF/jsp/Menu.jsp" %>

  <div class="content">
  <!-- begin .navigator -->
	 
	<!-- end .navigator -->
  
   <div class="fl-widget-titlebar titlebar portlet-titlebar" role="sectionhead">
    	<h2 class="title" >Work Order</h2>
    </div>
	
<form:form name="${portletNamespace}WorkOrder" method="post" action="${formAction}">
<h2>Stage</h2>
   	<table class="display dataTable" id="mainTable7" cellpadding="0" cellspacing="0" border="0">	

	<c:if test="${not empty stageList}">
    <thead><tr >
   		<th width="5%" scope="row">No</th>
        <th width="15%" scope="row">Stage</th>    
        <th width="10%" scope="row">Number of iterations</th> 
        <th width="10%" scope="row">Planned end date</th>
        <th width="10%" scope="row">Re-planned end date</th>
        <th width="25%" scope="row">Description</th>
        <th width="25%" scope="row">Milestone</th>   
    </tr>
    </thead>
    <tbody>
  
        <c:forEach var="stage" items="${stageList}" varStatus="count">
            <tr>
            <portlet:renderURL var="renderAction">
            	<portlet:param name="action" value="GoUpdateStage" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="stageId" value="${stage.milestoneId}" />
            	<portlet:param name="stageNumber" value="${count.count}" />
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
    </table><br>
<br/>    
<h2>Deliverables</h2>

 <c:if test="${not empty deliverableListStage1}">
<h3>Initiation</h3>    
    <table class="display dataTable" id="mainTable1" cellpadding="0" cellspacing="0" border="0">	
   <thead>
   	<tr>
   		<th width="5%" scope="row">No</th>
        <th width="20%" scope="row">Product Name</th>    
        <th width="10%" scope="row">Planned committed date</th> 
        <th width="10%" scope="row">Re-planned committed date</th>
        <th width="10%" scope="row">Actual committed date</th>
        <th width="10%" scope="row">Status</th>
        <th width="25%" scope="row">Note</th>
        <th width="10%" scope="row">Action</th>   
    </tr>
   </thead>
   <tbody>
   <c:forEach var="deliverable" items="${deliverableListStage1}" varStatus="count">
   	<tr>  	
   			<portlet:renderURL var="renderAction2">
            	<portlet:param name="action" value="GoUpdateDeliverable" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="delivarableId" value="${deliverable.moduleId}" />
            </portlet:renderURL>
            <portlet:renderURL var="renderAction3">
            	<portlet:param name="action" value="RemoveDeliverable" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="delivarableId" value="${deliverable.moduleId}" />
            </portlet:renderURL>
     		   <td scope="row">${count.count}</td>
               <td scope="row"><a href="${renderAction2}">${deliverable.name}</a></td>
               <td scope="row">${deliverable.plannedReleaseDate}</td>
               <td scope="row">${deliverable.replannedReleaseDate}</td>
               <td scope="row">${deliverable.actualReleaseDate}</td>
               <td scope="row">
               	<c:if test="${deliverable.status == 173 }">On-going</c:if>
               	<c:if test="${deliverable.status == 174 }">Finish</c:if>
               	<c:if test="${deliverable.status == 175 }">Cancelled</c:if>
               	<c:if test="${deliverable.status == 176 }">Tentative</c:if>
               </td>
               <td scope="row">${deliverable.baselineNote}</td>
               <td scope="row">
               	<button type="button" class="button blue small" onclick='return submitAction3("${portletNamespace}WorkOrder", "${renderAction3}", "Do you sure you want to delete this Deliverable?");'>Remove</button>
               </td>
     </tr>
    </c:forEach>
    </tbody>
    </table><br> 	
    </c:if><br/>
    
    <c:if test="${not empty deliverableListStage2}">
<h3>Definition</h3>    
    <table class="display dataTable" id="mainTable2" cellpadding="0" cellspacing="0" border="0">	
   <thead>
   	<tr>
   		<th width="5%" scope="row">No</th>
        <th width="20%" scope="row">Product Name</th>    
        <th width="10%" scope="row">Planned committed date</th> 
        <th width="10%" scope="row">Re-planned committed date</th>
        <th width="10%" scope="row">Actual committed date</th>
        <th width="10%" scope="row">Status</th>
        <th width="25%" scope="row">Note</th>
        <th width="10%" scope="row">Action</th>   
    </tr>
   </thead>
   <tbody>
   <c:forEach var="deliverable" items="${deliverableListStage2}" varStatus="count">
   	<tr>  	
   			<portlet:renderURL var="renderAction2">
            	<portlet:param name="action" value="GoUpdateDeliverable" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="delivarableId" value="${deliverable.moduleId}" />
            </portlet:renderURL>
            <portlet:renderURL var="renderAction3">
            	<portlet:param name="action" value="RemoveDeliverable" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="delivarableId" value="${deliverable.moduleId}" />
            </portlet:renderURL>
     		   <td scope="row">${count.count}</td>
               <td scope="row"><a href="${renderAction2}">${deliverable.name}</a></td>
               <td scope="row">${deliverable.plannedReleaseDate}</td>
               <td scope="row">${deliverable.replannedReleaseDate}</td>
               <td scope="row">${deliverable.actualReleaseDate}</td>
               <td scope="row">
               	<c:if test="${deliverable.status == 173 }">On-going</c:if>
               	<c:if test="${deliverable.status == 174 }">Finish</c:if>
               	<c:if test="${deliverable.status == 175 }">Cancelled</c:if>
               	<c:if test="${deliverable.status == 176 }">Tentative</c:if>
               </td>
               <td scope="row">${deliverable.baselineNote}</td>
               <td scope="row">
               	<button type="button" class="button blue small" onclick='return submitAction3("${portletNamespace}WorkOrder", "${renderAction3}", "Do you sure you want to delete this Deliverable?");'>Remove</button>
               </td>
     </tr>
    </c:forEach>
    </tbody>
    </table><br> 	
    </c:if><br/>
    
    <c:if test="${not empty deliverableListStage3}">
<h3>Solution</h3>    
    <table class="display dataTable" id="mainTable3" cellpadding="0" cellspacing="0" border="0">	
   <thead>
   	<tr>
   		<th width="5%" scope="row">No</th>
        <th width="20%" scope="row">Product Name</th>    
        <th width="10%" scope="row">Planned committed date</th> 
        <th width="10%" scope="row">Re-planned committed date</th>
        <th width="10%" scope="row">Actual committed date</th>
        <th width="10%" scope="row">Status</th>
        <th width="25%" scope="row">Note</th>
        <th width="10%" scope="row">Action</th>   
    </tr>
   </thead>
   <tbody>
   <c:forEach var="deliverable" items="${deliverableListStage3}" varStatus="count">
   	<tr>  	
   			<portlet:renderURL var="renderAction2">
            	<portlet:param name="action" value="GoUpdateDeliverable" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="delivarableId" value="${deliverable.moduleId}" />
            </portlet:renderURL>
            <portlet:renderURL var="renderAction3">
            	<portlet:param name="action" value="RemoveDeliverable" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="delivarableId" value="${deliverable.moduleId}" />
            </portlet:renderURL>
     		   <td scope="row">${count.count}</td>
               <td scope="row"><a href="${renderAction2}">${deliverable.name}</a></td>
               <td scope="row">${deliverable.plannedReleaseDate}</td>
               <td scope="row">${deliverable.replannedReleaseDate}</td>
               <td scope="row">${deliverable.actualReleaseDate}</td>
               <td scope="row">
               	<c:if test="${deliverable.status == 173 }">On-going</c:if>
               	<c:if test="${deliverable.status == 174 }">Finish</c:if>
               	<c:if test="${deliverable.status == 175 }">Cancelled</c:if>
               	<c:if test="${deliverable.status == 176 }">Tentative</c:if>
               </td>
               <td scope="row">${deliverable.baselineNote}</td>
               <td scope="row">
               	<button type="button" class="button blue small" onclick='return submitAction3("${portletNamespace}WorkOrder", "${renderAction3}", "Do you sure you want to delete this Deliverable?");'>Remove</button>
               </td>
     </tr>
    </c:forEach>
    </tbody>
    </table><br> 	
    </c:if><br/>
    
    <c:if test="${not empty deliverableListStage4}">
<h3>Construction</h3>    
    <table class="display dataTable" id="mainTable4" cellpadding="0" cellspacing="0" border="0">	
   <thead>
   	<tr>
   		<th width="5%" scope="row">No</th>
        <th width="20%" scope="row">Product Name</th>    
        <th width="10%" scope="row">Planned committed date</th> 
        <th width="10%" scope="row">Re-planned committed date</th>
        <th width="10%" scope="row">Actual committed date</th>
        <th width="10%" scope="row">Status</th>
        <th width="25%" scope="row">Note</th>
        <th width="10%" scope="row">Action</th>   
    </tr>
   </thead>
   <tbody>
   <c:forEach var="deliverable" items="${deliverableListStage4}" varStatus="count">
   	<tr>  	
   			<portlet:renderURL var="renderAction2">
            	<portlet:param name="action" value="GoUpdateDeliverable" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="delivarableId" value="${deliverable.moduleId}" />
            </portlet:renderURL>
            <portlet:renderURL var="renderAction3">
            	<portlet:param name="action" value="RemoveDeliverable" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="delivarableId" value="${deliverable.moduleId}" />
            </portlet:renderURL>
     		   <td scope="row">${count.count}</td>
               <td scope="row"><a href="${renderAction2}">${deliverable.name}</a></td>
               <td scope="row">${deliverable.plannedReleaseDate}</td>
               <td scope="row">${deliverable.replannedReleaseDate}</td>
               <td scope="row">${deliverable.actualReleaseDate}</td>
               <td scope="row">
               	<c:if test="${deliverable.status == 173 }">On-going</c:if>
               	<c:if test="${deliverable.status == 174 }">Finish</c:if>
               	<c:if test="${deliverable.status == 175 }">Cancelled</c:if>
               	<c:if test="${deliverable.status == 176 }">Tentative</c:if>
               </td>
               <td scope="row">${deliverable.baselineNote}</td>
               <td scope="row">
               	<button type="button" class="button blue small" onclick='return submitAction3("${portletNamespace}WorkOrder", "${renderAction3}", "Do you sure you want to delete this Deliverable?");'>Remove</button>
               </td>
     </tr>
    </c:forEach>
    </tbody>
    </table><br> 	
    </c:if><br/>
    
    <c:if test="${not empty deliverableListStage5}">
<h3>Transition</h3>    
    <table class="display dataTable" id="mainTable5" cellpadding="0" cellspacing="0" border="0">	
   <thead>
   	<tr>
   		<th width="5%" scope="row">No</th>
        <th width="20%" scope="row">Product Name</th>    
        <th width="10%" scope="row">Planned committed date</th> 
        <th width="10%" scope="row">Re-planned committed date</th>
        <th width="10%" scope="row">Actual committed date</th>
        <th width="10%" scope="row">Status</th>
        <th width="25%" scope="row">Note</th>
        <th width="10%" scope="row">Action</th>   
    </tr>
   </thead>
   <tbody>
   <c:forEach var="deliverable" items="${deliverableListStage5}" varStatus="count">
   	<tr>  	
   			<portlet:renderURL var="renderAction2">
            	<portlet:param name="action" value="GoUpdateDeliverable" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="delivarableId" value="${deliverable.moduleId}" />
            </portlet:renderURL>
            <portlet:renderURL var="renderAction3">
            	<portlet:param name="action" value="RemoveDeliverable" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="delivarableId" value="${deliverable.moduleId}" />
            </portlet:renderURL>
     		   <td scope="row">${count.count}</td>
               <td scope="row"><a href="${renderAction2}">${deliverable.name}</a></td>
               <td scope="row">${deliverable.plannedReleaseDate}</td>
               <td scope="row">${deliverable.replannedReleaseDate}</td>
               <td scope="row">${deliverable.actualReleaseDate}</td>
               <td scope="row">
               	<c:if test="${deliverable.status == 173 }">On-going</c:if>
               	<c:if test="${deliverable.status == 174 }">Finish</c:if>
               	<c:if test="${deliverable.status == 175 }">Cancelled</c:if>
               	<c:if test="${deliverable.status == 176 }">Tentative</c:if>
               </td>
               <td scope="row">${deliverable.baselineNote}</td>
               <td scope="row">
               	<button type="button" class="button blue small" onclick='return submitAction3("${portletNamespace}WorkOrder", "${renderAction3}", "Do you sure you want to delete this Deliverable?");'>Remove</button>
               </td>
     </tr>
    </c:forEach>
    </tbody>
    </table><br> 	
    </c:if><br/>
    
    <c:if test="${not empty deliverableListStage6}">
<h3>Termination</h3>    
    <table class="display dataTable" id="mainTable6" cellpadding="0" cellspacing="0" border="0">	
   <thead>
   	<tr>
   		<th width="5%" scope="row">No</th>
        <th width="20%" scope="row">Product Name</th>    
        <th width="10%" scope="row">Planned committed date</th> 
        <th width="10%" scope="row">Re-planned committed date</th>
        <th width="10%" scope="row">Actual committed date</th>
        <th width="10%" scope="row">Status</th>
        <th width="25%" scope="row">Note</th>
        <th width="10%" scope="row">Action</th>   
    </tr>
   </thead>
   <tbody>
   <c:forEach var="deliverable" items="${deliverableListStage6}" varStatus="count">
   	<tr>  	
   			<portlet:renderURL var="renderAction2">
            	<portlet:param name="action" value="GoUpdateDeliverable" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="delivarableId" value="${deliverable.moduleId}" />
            </portlet:renderURL>
            <portlet:renderURL var="renderAction3">
            	<portlet:param name="action" value="RemoveDeliverable" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="delivarableId" value="${deliverable.moduleId}" />
            </portlet:renderURL>
     		   <td scope="row">${count.count}</td>
               <td scope="row"><a href="${renderAction2}">${deliverable.name}</a></td>
               <td scope="row">${deliverable.plannedReleaseDate}</td>
               <td scope="row">${deliverable.replannedReleaseDate}</td>
               <td scope="row">${deliverable.actualReleaseDate}</td>
               <td scope="row">
               	<c:if test="${deliverable.status == 173 }">On-going</c:if>
               	<c:if test="${deliverable.status == 174 }">Finish</c:if>
               	<c:if test="${deliverable.status == 175 }">Cancelled</c:if>
               	<c:if test="${deliverable.status == 176 }">Tentative</c:if>
               </td>
               <td scope="row">${deliverable.baselineNote}</td>
               <td scope="row">
               	<button type="button" class="button blue small" onclick='return submitAction3("${portletNamespace}WorkOrder", "${renderAction3}", "Do you sure you want to delete this Deliverable?");'>Remove</button>
               </td>
     </tr>
    </c:forEach>
    </tbody>
    </table><br> 	
    </c:if><br/>
    
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
