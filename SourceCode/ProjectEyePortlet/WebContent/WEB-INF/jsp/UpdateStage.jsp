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
<script type="text/javascript">
    $(document).ready(function() {
	  $('#mainTable2 tr').filter(':has(:checkbox:checked)').addClass('selected').end().click(function(event) {
	    $(this).toggleClass('selected');
	    if (event.target.type !== 'checkbox') {
	      $(':checkbox', this).attr('checked', function() {
	        return !this.checked;
	      });
	    }
	  });
	  $( "#datepicker1" ).datepicker({
	            showOn: "button",
	            buttonImage: "/<spring:message code='app.context'/>/resource_files/images/calendar.gif",
	            buttonImageOnly: true
	        });
	        $( "#datepicker2" ).datepicker({
	            showOn: "button",
	            buttonImage: "/<spring:message code='app.context'/>/resource_files/images/calendar.gif",
	            buttonImageOnly: true
	        });
	        $( "#datepicker3" ).datepicker({
	            showOn: "button",
	            buttonImage: "/<spring:message code='app.context'/>/resource_files/images/calendar.gif",
	            buttonImageOnly: true
	        });
	        $( "#datepicker4" ).datepicker({
	            showOn: "button",
	            buttonImage: "/<spring:message code='app.context'/>/resource_files/images/calendar.gif",
	            buttonImageOnly: true
	        });
	        $( "#datepicker5" ).datepicker({
	            showOn: "button",
	            buttonImage: "/<spring:message code='app.context'/>/resource_files/images/calendar.gif",
	            buttonImageOnly: true
	        });
	        $( "#datepicker6" ).datepicker({
	            showOn: "button",
	            buttonImage: "/<spring:message code='app.context'/>/resource_files/images/calendar.gif",
	            buttonImageOnly: true
	        });
	        document.getElementById('description').innerHTML = "${UpdateStageForm.description}";
	        document.getElementById('milestone').innerHTML = "${UpdateStageForm.milestone}";
	});
    
    </script>	
    <title>Create Stage</title>
</head>

<body id="portal" class="up fl-theme-mist">

<div id="portalPageBodyInner" class="container">

  <div class="content">
  <!-- begin .navigator -->
	<jsp:include page="Nagivator.jsp" />
	<!-- end .navigator -->
   <div class="fl-widget-titlebar titlebar portlet-titlebar" role="sectionhead">
    	<h2 class="title" >Create Stage</h2>
    </div>

<div style="border-style:ridge" class="up-portlet-content-wrapper-inner">	
<portlet:actionURL var="formAction">
  <portlet:param name="action" value="UpdateStage" />
</portlet:actionURL>
<portlet:renderURL var="renderAction">
    		<portlet:param name="action" value="GoWorkOrder" />
        	<portlet:param name="projectId" value="${projectId}" />
  	</portlet:renderURL>
<form:form name="${portletNamespace}UpdateStage" commandName="UpdateStageForm" method="post" action="${formAction}"> 
	<table class="portlet-table">
      <tr>      
   		<th scope="row">Stage</th>
    	<td>${UpdateStageForm.stage}</td>
  	  </tr>
  	  <%-- 
  	  <tr>
        <th scope="row">Planned start date*</th>
        <td><form:input maxlength="10" path="plannedStartDate" size="9" value="" type="text" id="datepicker1"/>
          (mm/dd/yyyy)</td>
      </tr>
      <tr>
        <th scope="row">Re-planned start date</th>
        <td><form:input maxlength="10" path="rePlannedStartDate" size="9" value="" type="text" id="datepicker2"/>
          (mm/dd/yyyy)</td>
      </tr>
      <tr>
        <th scope="row">Actual start date</th>
        <td><form:input maxlength="10" path="actualStartDate" size="9" value="" type="text" id="datepicker3"/>
          (mm/dd/yyyy)</td>
      </tr>
      --%>
  	  <tr>
        <th scope="row">Planned end date*</th>
        <td><form:input maxlength="10" path="plannedEndDate" size="9" value="" type="text" id="datepicker4"/>
          (mm/dd/yyyy)</td>
      </tr>
      <tr>
        <th scope="row">Re-planned end date</th>
        <td><form:input maxlength="10" path="rePlannedEndDate" size="9" value="" type="text" id="datepicker5"/>
          (mm/dd/yyyy)</td>
      </tr>
      <tr>
        <th scope="row">Actual end date</th>
        <td><form:input maxlength="10" path="actualEndDate" size="9" value="" type="text" id="datepicker6"/>
          (mm/dd/yyyy)</td>
      </tr>
      <tr>
        <th scope="row">Description*</th>
        <td><textarea rows="10" cols="70" name="description" id="description"></textarea></td>
      </tr>
      <tr>
        <th scope="row">Milestone*</th>
        <td><textarea rows="10" cols="70" name="milestone" id="milestone"></textarea></td>
      </tr>
     </table>
     <input name = "projectId" type="hidden" value="${projectId}"/>
     <input name = "stageId" type="hidden" value="${stageId}"/>
	<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}UpdateStage", "${formAction}")'>Update</button>
	<button type="reset" class="button blue small">Reset</button>
	<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}UpdateStage", "${renderAction}")'>Cancel</button>	
</form:form>	
</div>

  <!-- end .content --></div>
  <!-- end .container --></div>
</body>
</html>
