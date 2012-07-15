<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ProjectEye HomePage</title>
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
<body>
<portlet:actionURL var="formAction">
  <portlet:param name="action" value="homeCreateProject" />
</portlet:actionURL>
<form:form method="post" commandName="ProjectEyeHomeForm" action="${formAction}">
	<button type="submit" class="button blue small" name="Submit" value="Submit">Create New Project</button>
</form:form>

<portlet:actionURL var="formAction">
  <portlet:param name="action" value="homeCreateProduct" />
</portlet:actionURL>
<form:form method="post" commandName="ProjectEyeHomeForm" action="${formAction}">
	<button type="submit" class="button blue small" name="Submit" value="Submit">Create New Product</button>
</form:form>

<portlet:actionURL var="formAction">
  <portlet:param name="action" value="homeCreateRisk" />
</portlet:actionURL>
<form:form method="post" commandName="ProjectEyeHomeForm" action="${formAction}">
	<button type="submit" class="button blue small" name="Submit" value="Submit">Create New Risk</button>
</form:form>

<portlet:actionURL var="formAction">
  <portlet:param name="action" value="homeCreateRisk" />
</portlet:actionURL>
<form:form method="post" commandName="ProjectEyeHomeForm" action="${formAction}">
<table id="projectList" border="0" cellpadding="3" cellspacing="0" width="100%" >

   <tbody><tr >
        <th width="70%">Project Name</th>    
        <th width="30%">Project Code</th>    
    </tr>
    <c:if test="${not empty projectList}">
  
        <c:forEach var="project" items="${projectList}">
            <tr>
               <td ><font color="">${project.name}</font></td>
               <td ><font color="">${project.code}</font></td>                     
            </tr>
        </c:forEach>
   
	</c:if>
    
    </tbody>
</table>
</form:form>
</body>
</html>