<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="robots" content="noindex, nofollow" />
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<portlet:defineObjects />
<link rel="icon" href="https://c15027075.ssl.cf2.rackcdn.com/favicon.ico" type="image/x-icon" />
<link type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/screen.css" rel="Stylesheet" />
<link type="text/css"
  href="/<spring:message code="app.context"/>/resource_files/css/ui-lightness/jquery-ui-1.8.21.custom.css"
  rel="Stylesheet" />
<link type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/common.css" rel="Stylesheet" />
<link type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/uportal.css" rel="Stylesheet" />
<link rel="stylesheet" type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/print.css"
  media="print" />
<link rel="stylesheet" type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/manage.css"
  media="all" />
<link rel="stylesheet" type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/datepicker.css"
  media="all" />
<link rel="fluid-icon" href="https://c15027075.ssl.cf2.rackcdn.com/images/apple-touch-icon-114x114.png" />
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
  src="/<spring:message code="app.context"/>/resource_files/js/jquery-ui-1.8.21.custom.min.js"></script>
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/form-elements.js"></script>
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/css/ga.js"></script>
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/yav.js"></script>
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/yav-config.js"></script>
<link type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/yav/yav-style.css" rel="Stylesheet" />
<script language="javascript" type="text/javascript"
  src="/<spring:message code="app.context"/>/resource_files/css/jquery.js"></script>
<script language="javascript" type="text/javascript"
  src="/<spring:message code="app.context"/>/resource_files/css/jquery.cookie.js"></script>
<script language="javascript" type="text/javascript"
  src="/<spring:message code="app.context"/>/resource_files/css/default.js"></script>
<script language="javascript" type="text/javascript"
  src="/<spring:message code="app.context"/>/resource_files/css/manage.js"></script>
<script language="javascript" type="text/javascript"
  src="/<spring:message code="app.context"/>/resource_files/common.js"></script>

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
	  document.getElementById('scopeObjective').innerHTML = "${UpdateProjectForm.scopeObjective}";
	  yav.init('${portletNamespace}UpdateProject', rules);      
	});
    
    </script>
<SCRIPT type="text/javascript">
	var rules = new Array();
	rules[0] = 'projectCode:Project Code|required';
	rules[1] = 'projectName:Project Name|required';
	rules[2] = 'planStartDate:Planned Start Date|required';
	rules[3] = 'planEndDate:Planned End Date|required';
	rules[4] = 'projectCode:Project Code|fixlength|3';
	rules[5] = 'projectName:Project Name|maxlength|150';
	rules[6] = 'customer:Direct Customer|maxlength|150';
	rules[7] = 'endCustomer:End Customer|maxlength|150';
	rules[8] = 'scopeObjective:Scope and Objective|maxlength|600';
	rules[9] = 'planStartDate|date_le|$planEndDate';
	rules[10] = 'planStartDate:Planned Start Date|date';
	rules[11] = 'planEndDate:Planned End Date|date';
	    yav.addHelp('projectName', 'Provide your Project Name');
</SCRIPT>

<title>Update Project</title>
</head>

<body id="portal" class="up fl-theme-mist">

  <div  class="container"><%@ include file="/WEB-INF/jsp/Menu.jsp" %>

    <div class="content">
      <div class="fl-widget-titlebar titlebar portlet-titlebar">        
		<br><p class="title" id="headerDuyND">Update Project</p>
      </div>
      <portlet:actionURL var="formAction">
        <portlet:param name="action" value="UpdateProject" />
      </portlet:actionURL>
      <portlet:renderURL var="renderAction">
        <portlet:param name="action" value="GoProjectDetail" />
        <portlet:param name="projectId" value="${projectId}" />
      </portlet:renderURL>
      <form:form name="${portletNamespace}UpdateProject" commandName="UpdateProjectForm" method="post"
        action="${formAction}" onsubmit="return yav.performCheck('${portletNamespace}UpdateProject', rules, 'inline');">

        <div id=errorsDiv style="color: red">
          <c:if test="${not empty errorList }">
            <font color="red">${errorList}</font>
          </c:if>
        </div>
        <table class="portlet-table">
          <tr>
            <th width="250" scope="row">Project Manager</th>
            <td width="650">${UpdateProjectForm.manager }</td>
          </tr>
          <tr>
            <th scope="row">Project Code*</th>
            <td>${UpdateProjectForm.projectCode}</td>
          </tr>
          <tr>
            <th scope="row">Project Name*</th>
            <td><form:input path="projectName" value="" maxlength="150" size="50" type="text" id="projectName" style='width:635px'/>
            <br/><span id=errorsDiv_projectName>&nbsp;</span></td>
          </tr>
          <tr>
            <th scope="row">Project Status</th>
            <td><form:select class="styled" path="projectStatus_SelectedValue" items="${projectStatus}" /></td>
          </tr>
          <tr>
            <th scope="row">Project Category</th>
            <td><form:select class="styled" path="projectCategory_SelectedValue" items="${projectCategory}" /></td>
          </tr>
          <tr>
            <th scope="row">Direct Customer</th>
            <td><form:input path="customer" value="" maxlength="150" size="50" type="text" id="customer" /></td>
          </tr>
          <tr>
            <th scope="row">End Customer</th>
            <td><form:input path="endCustomer" value="" maxlength="150" size="50" type="text" id="endCustomer" /></td>
          </tr>
          <tr>
            <th scope="row">Business Domain</th>
            <td><form:select class="styled" path="businessDomain_SelectedValue" items="${businessDomain}" /></td>
          </tr>
          <tr>
            <th scope="row">Planned Start Date*</th>
            <td><form:input maxlength="10" path="planStartDate" size="9" value="" type="text" id="datepicker1" style='width:80px' />
              <span id=errorsDiv_planStartDate>&nbsp;</span></td>
          </tr>
          <tr>
            <th scope="row">Planned End Date*</th>
            <td><form:input maxlength="10" path="planEndDate" size="9" value="" type="text" id="datepicker2" style='width:80px' />
              <span id=errorsDiv_planEndDate>&nbsp;</span></td>
          </tr>
          <tr>
            <th scope="row">Scope and Objective</th>
            <td><textarea rows="10" cols="70" name="scopeObjective" id="scopeObjective" style='width:635px'></textarea></td>
          </tr>

        </table>
        <input name="projectId" type="hidden" value="${projectId}" />
        <button type="button" class="button blue small"
          onclick='submitAction2("${portletNamespace}UpdateProject", "${formAction}")'>Update</button>
        <button type="reset" class="button blue small">Reset</button>
        <button type="button" class="button blue small"
          onclick='submitAction("${portletNamespace}UpdateProject", "${renderAction}")'>Cancel</button>

      </form:form>

      <!-- end .content -->
    </div>
    <!-- end .container -->
  </div>
</body>
</html>
