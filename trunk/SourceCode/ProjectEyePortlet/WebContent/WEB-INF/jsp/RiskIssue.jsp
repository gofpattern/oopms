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

            } );
        </script>
	
</head>

<body id="portal" class="up fl-theme-mist">

<div  class="container"><%@ include file="/WEB-INF/jsp/Menu.jsp" %>

  <div class="content">
    
    <!-- begin .navigator -->
	 
	<!-- end .navigator -->
	
	   <div class="fl-widget-titlebar titlebar portlet-titlebar" role="sectionhead">
    	<br><p class="title" id="headerDuyND">Risk, Issue</p>
    </div>
<portlet:renderURL var="renderAction1">
    		<portlet:param name="action" value="GoCreateRisk" />
        	<portlet:param name="projectId" value="${projectId}" />
</portlet:renderURL>    	
<portlet:renderURL var="renderAction2">
    		<portlet:param name="action" value="GoCreateIssue" />
        	<portlet:param name="projectId" value="${projectId}" />
</portlet:renderURL>
<form:form name="${portletNamespace}RiskIssue" method="post" action="${formAction}"></form:form>
<br><p id="header2DuyND">Risk<p>
<table class="display dataTable" id="mainTable1" cellpadding="0" cellspacing="0" border="0">	

	<c:if test="${not empty riskList}">
    <thead><tr >
   		<th width="5%" scope="row">No</th>
        <th width="10%" scope="row">Risk Source</th>    
        <th width="5%" scope="row">Probability</th>
        <th width="5%" scope="row">Risk Priority</th>
        <th width="10%" scope="row">Estimated Impact</th>
        <th width="30%" scope="row">Description</th> 
        <th width="25%" scope="row">Trigger</th>
        <th width="10%" scope="row">Action</th>   
    </tr>
    </thead>
    <tbody>
  
        <c:forEach var="risk" items="${riskList}" varStatus="count">
            <tr>
            <portlet:renderURL var="renderAction3">
            	<portlet:param name="action" value="GoUpdateRisk" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="riskId" value="${risk.riskId}" />
            </portlet:renderURL>
            <portlet:actionURL var="renderAction4">
            	<portlet:param name="action" value="DeleteRisk" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="riskId" value="${risk.riskId}" />
            </portlet:actionURL>
               <td scope="row">${count.count}</td>
               <td scope="row"><a href="${renderAction3}">${risk.riskSource}</a></td>
               <td scope="row">${risk.probability}</td>
               <td scope="row">${risk.priority}</td>
               <td scope="row">${risk.estimatedImpact}</td>
               <td scope="row">${risk.description}</td>
               <td scope="row">${risk.trigger}</td>
               <td scope="row">
               <button type="button" class="button blue small" onclick='return submitAction3("${portletNamespace}RiskIssue", "${renderAction4}", "Do you sure you want to delete this Risk?");'>Remove</button>
               </td>
            </tr>
        </c:forEach>
    </tbody>
    </c:if>
    </table><br>
	<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}RiskIssue", "${renderAction1}")'>Add new Risk</button>
<br><p id="header2DuyND">Issue<p>
	<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}RiskIssue", "${renderAction2}")'>Add new Issue</button>
  <!-- end .content --></div>
  <!-- end .container --></div>
</body>
</html>
