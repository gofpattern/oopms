<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Create Risk</title>
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
  
  	<!-- begin .navigator -->
	<jsp:include page="Nagivator.jsp" />
	<!-- end .navigator -->
   <div class="fl-widget-titlebar titlebar portlet-titlebar" role="sectionhead">
    	<h2 class="title" >Create Risk</h2>
    </div>
    
    
	
<div style="border-style:ridge" class="up-portlet-content-wrapper-inner">	
<portlet:actionURL var="formAction">
  <portlet:param name="action" value="createRisk" />
</portlet:actionURL>
<form:form method="get" commandName="CreateRiskForm" action="${formAction}">                       
    <table class="portlet-table">
  <tr>
    <th width="186" scope="row">Risk Source*</th>
    <td width="433">
    <form:select  class="SmallCombo" path="riskSource_SelectedValue" items="${riskSource}"/>
    </td>
  </tr>
  <tr>
    <th scope="row">Description*</th>
    <td><textarea rows="10" cols="70" name="description"></textarea></td>
  </tr>
  <tr>
    <th scope="row">Probability*</th>
    <td><input name="probability" value="" maxlength="50" size="50" type="text" /></td>
  </tr>
  <tr>
    <th scope="row" rowspan="3">Estimated Impact*</th>
    <td><select name="estimatedImpactTo" class="SmallCombo" id="i1">
      <option selected="selected" value="b">Schedule</option>
      <option value="c">Effort</option>
      <option value="d">Finance</option>
      <option value="e">Team</option>
      <option value="f">Timeliness</option>
      <option value="g">Requirement completion</option>
      <option value="h">Leakage</option>
      <option value="i">Customer satisfaction</option>
      <option value="j">Correction Cost</option>
      <option value="k">Other</option>
    </select></td>
  </tr>
  <tr>
    <td><select name="estimatedImpactUnit" class="SmallCombo" id="u1">
      <option selected="selected" value="b">%</option>
      <option value="c">day</option>
      <option value="d">month</option>
      <option value="e">person.day</option>
      <option value="f">person.month</option>
      <option value="g">$</option>
      <option value="h">#</option>
    </select></td>
  </tr>
  <tr>
    <td><input name="estimatedImpact" value="" maxlength="50" size="50" type="text" /></td>
  </tr>
  <tr>
    <th scope="row">Total Impact</th>
    <td><input name="totalImpact" value="" maxlength="50" size="50" type="text" /></td>
  </tr>
  <tr>
   <th scope="row">Risk Priority*</th>
        <td><input name="riskPriority" value="" maxlength="50" size="50" type="text" /></td>
      </tr>
  <tr>
   <th scope="row">Trigger</th>
    <td><textarea rows="10" cols="70" name="trigger"></textarea></td>
   </tr>
</table>
	<button type="submit" class="button blue small" name="buttonAction" value="Submit">Create</button>
	<button type="reset" class="button blue small">Reset</button>
	<portlet:renderURL var="renderAction">
    		<portlet:param name="action" value="GoRiskIssue" />
        	<portlet:param name="projectId" value="${projectId}" />
  	</portlet:renderURL>
    <a href="${renderAction}">
		<button type="button" class="button blue small" name="Cancel" value="Cancel">Cancel</button>
	</a>		
</form:form>
</div>
  <!-- end .content --></div>
  <!-- end .container --></div>
</body>
</html>
