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
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/yav.js"></script>
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/yav-config.js"></script>
<link type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/yav/yav-style.css" rel="Stylesheet" />
<script language="javascript" type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/css/jquery.js"></script>
<script language="javascript" type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/css/jquery.cookie.js"></script>
<script language="javascript" type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/css/default.js"></script>
<script language="javascript" type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/css/manage.js"></script>
<script language="javascript" type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/common.js"></script>
<meta name="robots" content="noindex, nofollow"/>
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
	        yav.init('${portletNamespace}CreateDeliverable', rules);
	});
    
    </script>
    <SCRIPT type="text/javascript">
	var rules = new Array();
	rules[0] = 'plannedCommittedDate:Planned committed date|required';
	rules[1] = 'plannedCommittedDate:Planned committed date|date';
	rules[2] = 'rePlannedCommittedDate:Re-planned committed date|date';
	rules[3] = 'actualCommittedDate:Actual committed date|date';
	rules[4] = 'Note:note|maxlength|600';
	rules[5] = 'plannedCommittedDate:Planned committed date|date_le|$plannedEndDateOfProject:Planned end date of project';
	rules[6] = 'rePlannedCommittedDate:Re-planned committed date|date_le|$plannedEndDateOfProject:Planned end date of project';
	yav.addHelp('plannedCommittedDate', 'Please input Planned committed date');

</SCRIPT>
    
    <title>Create Deliverable</title>	
</head>

<body id="portal" class="up fl-theme-mist">

<div id="portalPageBodyInner" class="container"><%@ include file="/WEB-INF/jsp/Menu.jsp" %>

  <div class="content">
  <!-- begin .navigator -->
	 
	<!-- end .navigator -->
   <div class="fl-widget-titlebar titlebar portlet-titlebar" role="sectionhead">
    	<h2 class="title" >Create Deliverable</h2>
    </div>

<div style="border-style:ridge" class="up-portlet-content-wrapper-inner">	
<portlet:actionURL var="formAction">
  <portlet:param name="action" value="CreateDeliverable" />
  <portlet:param name="projectId" value="${projectId}" />
</portlet:actionURL>
<portlet:renderURL var="renderAction">
    		<portlet:param name="action" value="GoWorkOrder" />
        	<portlet:param name="projectId" value="${projectId}" />
  	</portlet:renderURL>
  	<portlet:renderURL var="renderAction2">
    		<portlet:param name="action" value="GoCreateProduct" />
        	<portlet:param name="projectId" value="${projectId}" />
    	</portlet:renderURL>
<c:if test="${ empty deliverable}">
	There is no product to deliver. Please create new Product.<br/>
	<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}CreateDeliverable", "${renderAction2}")'>Add New Product</button>
	<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}CreateDeliverable", "${renderAction}")'>Cancel</button>
</c:if>

<form:form name="${portletNamespace}CreateDeliverable" commandName="CreateDeliverableForm" method="post" action="${formAction}"> 
<c:if test="${not empty deliverable }">
<table class="portlet-table">
      <tr>      
   		<th scope="row">Deliverable</th>
    	<td><form:select  class="styled" path="deliverable_SelectedValue" items="${deliverable}"/></td>
  	  </tr>
  	  <tr>
        <th scope="row">Planned committed date*</th>
        <td><form:input maxlength="10" path="plannedCommittedDate" size="9" value="" type="text" id="datepicker1"/>
          (mm/dd/yyyy)<br></br><span id=errorsDiv_plannedCommittedDate>&nbsp;</span></td>
      </tr>
      <tr>
        <th scope="row">Re-planned committed date</th>
        <td><form:input maxlength="10" path="rePlannedCommittedDate" size="9" value="" type="text" id="datepicker2"/>
          (mm/dd/yyyy)<br></br><span id=errorsDiv_rePlannedCommittedDate>&nbsp;</span></td>
      </tr>
      <tr>
        <th scope="row">Actual committed date</th>
        <td><form:input maxlength="10" path="actualCommittedDate" size="9" value="" type="text" id="datepicker3"/>
          (mm/dd/yyyy)<br></br><span id=errorsDiv_actualCommittedDate>&nbsp;</span></td>
      </tr>
      <tr>
        <th scope="row">Note</th>
        <td><textarea rows="10" cols="70" name="note"></textarea>
		<br/><span id=errorsDiv_note>&nbsp;</span></td>
      </tr>
     </table><br>
    <input name = "plannedEndDateOfProject" type="hidden" value="${CreateDeliverableForm.plannedEndDateOfProject}"/>   
	<button type="button" class="button blue small" onclick='submitAction2("${portletNamespace}CreateDeliverable", "${formAction}")'>Create</button>
	<button type="reset" class="button blue small">Reset</button>
	<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}CreateDeliverable", "${renderAction}")'>Cancel</button>	
	</c:if>		
</form:form>

</div>

  <!-- end .content --></div>
  <!-- end .container --></div>
</body>
</html>
