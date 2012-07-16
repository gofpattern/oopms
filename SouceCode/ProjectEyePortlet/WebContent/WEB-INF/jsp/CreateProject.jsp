<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Create Project</title>
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
	            buttonImage: "../OOPMSPortlet/resource_files/images/calendar.gif",
	            buttonImageOnly: true
	        });
	        $( "#datepicker2" ).datepicker({
	            showOn: "button",
	            buttonImage: "../OOPMSPortlet/resource_files/images/calendar.gif",
	            buttonImageOnly: true
	        });
	        $( "#selectable" ).selectable();
	});
    
    </script>

</head>

<body id="portal" class="up fl-theme-mist">

<div id="portalPageBodyInner" class="container">

  <div class="content">
  <!-- begin .navigator -->
	<jsp:include page="Nagivator.jsp" />
	<!-- end .navigator -->
   <div class="fl-widget-titlebar titlebar portlet-titlebar" role="sectionhead">
    	<h2 class="title" >Create Project</h2>
    </div>
<portlet:actionURL var="formAction">
  <portlet:param name="action" value="createProject" />
</portlet:actionURL>
<form:form method="post" commandName="CreateProjectForm" action="${formAction}">                       
    <table class="portlet-table">
  <tr>
    <th width="186" scope="row">Project Manager</th>
    <td width="433">Manager</td>
  </tr>
  <tr>
    <th scope="row">Project Code* </th>
    <td><input name="projectCode" value="" maxlength="50" size="50" type="text" /></td>
  </tr>
  <tr>
    <th scope="row">Project Name* </th>
    <td><input name="projectName" value="" maxlength="50" size="50" type="text" /></td>
  </tr>
  <tr>
    <th scope="row">Project Status</th>
    <td><select class="styled" class="SmallCombo" name="projectStatus">
      <option selected="selected">Tentative</option>
      <option>Ongoing</option>
      <option>Closed</option>
      <option>Cancelled</option>
    </select></td>
  </tr>
  <tr>
    <th scope="row">Project Type</th>
    <td><select class="styled" class="SmallCombo" name="projectType">
      <option selected="selected">Product Maintainance</option>
      <option>Project Development</option>
    </select></td>
  </tr>
  <tr>
   <th scope="row">Direct Customer</th>
        <td><input name="customer" value="" maxlength="50" size="50" type="text" /><input type="button" value="Search Icon"></input></td>
      </tr>
      <tr>
        <th scope="row">End Customer</th>
        <td><input name="endCustomer" value="" maxlength="50" size="50" type="text" /></td>
      </tr>
      <tr>
   <th scope="row">Business Domain</th>
    <td><select name="businessDomain" class="styled" class="SmallCombo">
      <option selected="selected">Healthcare</option>
      <option>Universities</option>
      <option>Bank & Finance</option>
      <option>Technology</option>
      <option>Real Estate</option>
      <option>Construction</option>
      <option>Manufacturing</option>
    </select></td>
  </tr>
      <tr>
        <th scope="row">Planned Start Date* </th>
        <td><input maxlength="9" name="planStartDate" size="9" value="" type="text" id="datepicker1"/>
          (mm-dd-yyyy)</td>
      </tr>
      <tr>
        <th scope="row">Planned End Date* </th>
        <td><input maxlength="9" name="planEndDate" size="9" value="" type="text" id="datepicker2"/>
          (mm-dd-yyyy)</td>
      </tr>
      <tr>
        <th scope="row">Scope and Objective</th>
        <td><textarea rows="10" cols="70" name="scopeObjective"></textarea></td>
      </tr>
</table>
	<button type="submit" class="button blue small" name="Submit" value="Submit">Submid</button>
	<button type="reset" class="button blue small">Reset</button>
	<portlet:renderURL var="renderAction">
    		<portlet:param name="jspPage" value="/ProjectEyeHome.jsp" />
    	</portlet:renderURL>
		<a href="${renderAction}">
	<button type="submit" class="button blue small" name="Cancel" value="Cancel">Cancel</button>
	</a>
</form:form>

  <!-- end .content --></div>
  <!-- end .container --></div>
</body>
</html>
