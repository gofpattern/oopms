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
<link type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/yav/yav-style.css" rel="Stylesheet" />


<script type="text/javascript">
    $(document).ready(function() {
	        yav.init('${portletNamespace}CreateBudgetRecord', rules);
	});
    
    </script>
<SCRIPT type="text/javascript">
	var rules = new Array();
	rules[0] = 'value:Value|required';
	rules[1] = 'description:Description|maxlength|255';
	rules[2] = 'value:Value|double';
	yav.addHelp('value', 'Provide your Budget Value');

</SCRIPT>

<title>Create Budget Record</title>
</head>

<body id="portal" class="up fl-theme-mist">

<div  class="container">

  <div class="content">
   <div class="fl-widget-titlebar titlebar portlet-titlebar">
    	<p class="title" id="headerDuyND">Create Budget Record</p>
    </div>
<portlet:actionURL var="formAction">
  <portlet:param name="action" value="CreateBudgetRecord" />
  <portlet:param name="projectId" value="${projectId}" />
</portlet:actionURL>
<portlet:renderURL var="renderAction">
    		<portlet:param name="action" value="GoCostManagement" />
        	<portlet:param name="projectId" value="${projectId}" />
  	</portlet:renderURL>
<form:form name="${portletNamespace}CreateBudgetRecord" commandName="CreateBudgetRecordForm" method="post" action="${formAction}" onsubmit="return yav.performCheck('${portletNamespace}CreateBudgetRecord', rules, 'inline');">

<div id=errorsDiv style="color: red">
<c:if test="${not empty errorList }">
	<font color="red">${errorList}</font>
</c:if>
</div>
    <table class="portlet-table">
  <tr>
    <th scope="row">Type</th>
    <td>
		<form:radiobutton path="budgetType" value="0"/>Increase
		<form:radiobutton path="budgetType" value="1"/>Decrease
	</td>
  </tr>
  <tr>
    <th scope="row">Value*</th>
    <td><form:input path="value" value="" maxlength="22" size="50" type="text" style='width:100px'/>
    <span id=errorsDiv_value>&nbsp;</span></td>
  </tr>
      <tr>
        <th scope="row">Description</th>
        <td><textarea rows="10" cols="70" name="description" id="description" style='width:675px'></textarea>
        <br/><span id=errorsDiv_description>&nbsp;</span></td>
      </tr>
</table>
	<button type="button" class="button blue small" onclick='submitAction2("${portletNamespace}CreateBudgetRecord", "${formAction}")'>Create</button>
	<button type="reset" class="button blue small">Reset</button>
	<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}CreateBudgetRecord", "${renderAction}")'>Cancel</button>
	
</form:form>

  <!-- end .content --></div>
  <!-- end .container --></div>
</body>
</html>