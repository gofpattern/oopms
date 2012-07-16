<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet"%>
<%@ taglib prefix="portlet2" uri="http://java.sun.com/portlet_2_0"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="javax.portlet.PortletSession"%>
<portlet2:defineObjects />
<portlet:defineObjects />

<title>Dashboard: Project Bubble</title>
<jsp:include page="header.jsp" />

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
                                buttonImage: "../TimesheetPortlet/resource_files/images/calendar.gif",
                                buttonImageOnly: true
                            });
                            $( "#datepicker2" ).datepicker({
                                showOn: "button",
                                buttonImage: "../TimesheetPortlet/resource_files/images/calendar.gif",
                                buttonImageOnly: true
                            });
                            $( "#selectable" ).selectable();
                fnFeaturesInit();
                $('#mainTable2').dataTable( {
                    "bFilter": true,
                    "bSort": true,
                    "bJQueryUI": true,
                    "sPaginationType": "full_numbers"
                } );
                
                SyntaxHighlighter.config.clipboardSwf = 'media/javascript/syntax/clipboard.swf';
                SyntaxHighlighter.all();
                
            } );
        </script>
<script type="text/javascript">
    $(document).ready(function() {

    
    });
   
    </script>
</head>
<body class="">

<div class="bg">

<div id="content" class="content loggedin">

<div class="container "><portlet:actionURL
  var="timesheetFormAction">
  <portlet:param name="action" value="searchTimesheet" />
</portlet:actionURL> <form:form name="timesheet" method="post" commandName="timesheetForm"
  action="${timesheetFormAction}">
  <form:errors path="*" cssStyle="color:red;" />
  <table border="0">
    <tbody>
      <tr>
        <td><strong>User</strong></td>
        <td width="20%"><strong><font color="#1490E3"><%=portletSession.getAttribute("USER",  PortletSession.APPLICATION_SCOPE)%></font></strong></td>
        <td><strong>Project</strong></td>
        <td width="26%"><form:select cssClass="styled"
          path="projectDefault" multiple="single">
          <form:options items="${projectMap}" />
        </form:select></td>
        <td><strong>From Date</strong></td>
        <td class="vAlignMid"><form:input path="fromDate"
          id="datepicker1" /></td>


      </tr>
      <tr>
        <td><strong>Role</strong></td>
        <td><strong><font color="#1490E3">Developer</font></strong></td>
        <td width="7%" align="left"><strong>Status</strong></td>
        <td width="26%"><select class="styled" size="1"
          name="status" class="SmallCombo" value="0">
          <option value="All" selected="">All</option>
          <option value="1">Unapproved</option>
          <option value="2">Approved</option>
          <option value="3">Rejected</option>
        </select></td>
        <td width="17%" align="left"><strong>To Date</strong></td>
        <td colspan="2" class="vAlignMid"><form:input path="toDate"
          id="datepicker2" /></td>
        <td></td>
      </tr>
    </tbody>
  </table>

  <p><input type="submit" id="Search" class="button blue small"
    name="Search" value="Search" /></p>
    
</form:form>
<table id="mainTable2" class="display dataTable"  cellpadding="0" cellspacing="0" border="0" >
  <thead>
    <tr>
      <th width="5%"><input type="checkbox" name="allbox"
        value="Check All" onClick="JavaScript:checkAll();"></th>
      <th width="10%">Date</th>
      <th width="10%">Project</th>
      <th width="5%">Work</th>
      <th width="5%">Process</th>
      <th width="5%">Time</th>
      <th width="48%">Description</th>
      <th width="7%">Status</th>
    </tr>
  </thead>
  <tbody align="center">
    <c:if test="${not empty timesheetList}">

      <c:forEach var="timesheet" items="${timesheetList}">
        <tr>
          <td class="cb"><input type="checkbox" value="yes"></td>
          <td><font color="">${timesheet.occurDate}</font></td>
          <td><font color="">${timesheet.project.name}</font></td>
          <td><font color="">${timesheet.towName}</font></td>
          <td><font color="">${timesheet.processName}</font></td>
          <td><font color="">${timesheet.duration}</font></td>
          <td><font color="">${timesheet.description}</font></td>
          <td><c:if test="${timesheet.status==0}">UnApprived</c:if>
          <c:if test="${timesheet.status==1}">Approved</c:if></td>

        </tr>
      </c:forEach>

    </c:if>

  </tbody>
</table>

<p><input type="button" class="button blue small" name="Update"
  onclick="javascript:doUpdate()" value="Update" class="Button" /> <input
  type="button" class="button blue small" name="Delete"
  onClick="javascript:doDelete()" value="Delete" class="Button"></p>


</div>
</div>
</div>





</body>
</html>