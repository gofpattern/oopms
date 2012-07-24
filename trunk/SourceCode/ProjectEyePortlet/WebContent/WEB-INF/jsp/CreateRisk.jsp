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
<link type="text/css" href="../OOPMSPortlet/resource_files/css/screen.css" rel="Stylesheet" />
<link type="text/css" href="../OOPMSPortlet/resource_files/css/ui-lightness/jquery-ui-1.8.21.custom.css" rel="Stylesheet" />	
<link type="text/css" href="../OOPMSPortlet/resource_files/css/common.css" rel="Stylesheet" />	
<link type="text/css" href="../OOPMSPortlet/resource_files/css/uportal.css" rel="Stylesheet" />
<link rel="stylesheet" type="text/css" href="../OOPMSPortlet/resource_files/css/print.css" media="print"/>
<link rel="stylesheet" type="text/css" href="../OOPMSPortlet/resource_files/css/manage.css" media="all"/>				
<link rel="stylesheet" type="text/css" href="../OOPMSPortlet/resource_files/css/datepicker.css" media="all"/>
<link rel="fluid-icon" href="https://c15027075.ssl.cf2.rackcdn.com/images/apple-touch-icon-114x114.png"/>
<script type="text/javascript" src="../OOPMSPortlet/resource_files/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../OOPMSPortlet/resource_files/js/jquery-ui-1.8.21.custom.min.js"></script>
<script type="text/javascript" src="../OOPMSPortlet/resource_files/js/form-elements.js"></script>
<script type="text/javascript" src="../OOPMSPortlet/resource_files/css/ga.js"></script>
<script language="javascript" type="text/javascript" src="../OOPMSPortlet/resource_files/css/jquery.js"></script>
<script language="javascript" type="text/javascript" src="../OOPMSPortlet/resource_files/css/jquery.cookie.js"></script>
<script language="javascript" type="text/javascript" src="../OOPMSPortlet/resource_files/css/default.js"></script>
<script language="javascript" type="text/javascript" src="../OOPMSPortlet/resource_files/css/manage.js"></script>
<script language="javascript" type="text/javascript" src="../OOPMSPortlet/resource_files/common.js"></script>
<meta name="robots" content="noindex, nofollow"/>	
	

<title>Create Risk</title>
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
  <portlet:param name="projectId" value="${projectId}" />
</portlet:actionURL>
<portlet:renderURL var="renderAction">
    		<portlet:param name="action" value="GoRiskIssue" />
        	<portlet:param name="projectId" value="${projectId}" />
  	</portlet:renderURL>
<form:form name="${portletNamespace}CreateRisk" commandName="CreateRiskForm" method="post" action="${formAction}">                        
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
    <td><form:select  class="SmallCombo" path="estimatedImpactTo_SelectedValue" items="${estimatedImpactTo}"/></td>
  </tr>
  <tr>
    <td><form:select  class="SmallCombo" path="estimatedImpactUnit_SelectedValue" items="${estimatedImpactUnit}"/></td>
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
	<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}CreateRisk", "${formAction}")'>Create</button>
	<button type="reset" class="button blue small">Reset</button>
	<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}CreateRisk", "${renderAction}")'>Cancel</button>		
</form:form>

<table class="portlet-table">
        <tr style="color: red;" >
          <th  align="center" width="92" scope="row"><b><font color="#000000">Column</font></b></th>
          <th align="center" width="331" scope="row"><b><font color="#000000">Value</font></b></th>
          <th align="center" width="140" scope="row"><b><font color="#000000">&nbsp;Formula</font></b></th>
        </tr>
        <tr style="color: red;">
          <td width="92"><i>Probability</i></td>
          <td align="left" width="331"><i><font color="#0000FF">High (0.8 – 1.0); Medium (0.4 – 0.7); Low 
            (0.0 – 0.3)</font></i></td>
          <td align="left" width="140">&nbsp;</td>
        </tr>
        <tr style="color: red;">
          <td  width="92"><i>Total Impact </i></td>
          <td  width="331"><i><font color="#0000FF">High (8.0 – 10.0); Moderate (4.0 – 7.0); 
            Low (0.0 – 3.0)</font></i></td>
          <td  align="left" width="140"><i><font color="#0000FF">&nbsp;SUM(Estimated impact)</font></i></td>
        </tr>
        <tr style="color: red;">
          <td  width="92" height="23"><i>Risk Priority</i></td>
          <td  width="331" height="23"><i><font color="#0000FF">1 to 10 (highest to lowest)</font></i></td>
          <td  align="left" width="140" height="23"><i><font color="#0000FF">&nbsp;</font></i></td>
        </tr>
    </table>
</div>
  <!-- end .content --></div>
  <!-- end .container --></div>
</body>
</html>
