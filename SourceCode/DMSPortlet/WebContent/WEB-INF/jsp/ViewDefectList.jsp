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
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/validation.js"></script>
<title>Defect System</title>
<jsp:include page="header.jsp" />

<script type="text/javascript">
function validateDefect() {
    var  fromDate = document.getElementById("datepicker1").value;
    var toDate = document.getElementById("datepicker2").value;  
    if( !checkValidDate(fromDate,"formatFromDate") || ! checkValidDate(toDate,"formatToDate")) {
     $('#errorDiv').css('display','inline');
     $('#errorDiv').css('text-align','center');
     return false;
    }
  return true;
}


function submitAction(formName, actionUrl) {
    
    var frm = document.forms[formName];
        
        frm.action = actionUrl;
        
        frm.submit();
        
        
    }


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
            function submitActionConfirm(formName, actionUrl) {
               
                var r=confirm("Are you sure to delete ?");
                if (r==true)
                  {
                 var frm = document.forms[formName];
                        
                        frm.action = actionUrl;
                        
                        frm.submit();
                  }
                else
                  {
                 return;
                  }
            }
            $(document).ready( function() {
            // Listen for click on toggle checkbox
            $('#select-all').click(function(event) {   
                if(this.checked) {
                    // Iterate each checkbox
                    $(':checkbox').each(function() {
                        this.checked = true;                        
                    });
                } else { 
                 $(":checkbox").each(function() { this.checked = false; }); 
                 }
            });             
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
                                buttonImage: "/<spring:message code="app.context"/>/resource_files/images/calendar.gif",
                                buttonImageOnly: true
                            });
                            $( "#datepicker2" ).datepicker({
                                showOn: "button",
                                buttonImage: "/<spring:message code="app.context"/>/resource_files/images/calendar.gif",
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
  var="defectFormAction">
  <portlet:param name="action" value="searchDefect" />
</portlet:actionURL> <form:form name="defect" method="post" onsubmit=" return validateDefect();" commandName="viewDefectList"
  action="${defectFormAction}">  
  <table border="0">
    <tbody>
      <tr>
        <td><strong>User</strong></td>
        <td width="20%"><strong><font color="#1490E3"><%=portletSession.getAttribute("USER", PortletSession.APPLICATION_SCOPE)%></font></strong></td>
        <td><strong>Project</strong></td>
        <td width="26%"><form:select 
          path="projectDis" multiple="single">
          <form:options items="${projectMap}" />
        </form:select></td>
         <td><strong>Assigned To</strong></td>
         <td width="26%"><form:select 
          path="memberDisAssigned" multiple="single">
          <form:options items="${memberMap}" />
        </form:select></td>
        <td><strong>Created By</strong></td>
         <td width="26%"><form:select 
          path="memberDisCreated" multiple="single">
          <form:options items="${memberMap}" />
        </form:select></td>
        <td><strong>Create Date</strong></td>
        <td class="vAlignMid"><form:input cssStyle="width:80px;"
          path="createDate" id="datepicker1" /></td>


      </tr>
      <tr>
        <td><strong>Role</strong></td>
        <td><strong><font color="#1490E3"><%=portletSession.getAttribute("ROLE", PortletSession.APPLICATION_SCOPE)%></font></strong></td>
        <td width="7%" align="left"><strong>Status</strong></td>
       <td><form:select 
          path="statusDis" multiple="single">
          <form:options items="${statusMap}" />
        </form:select></td>
        <td width="7%" align="left"><strong>Severity</strong></td>
       <td><form:select 
          path="severityDis" multiple="single">
          <form:options items="${severityMap}" />
        </form:select></td>
        <td width="7%" align="left"><strong>Priority</strong></td>
       <td><form:select 
          path="priorityDis" multiple="single">
          <form:options items="${priorityMap}" />
        </form:select></td>
        <td width="17%" align="left"><strong>Due Date</strong></td>
        <td colspan="2" class="vAlignMid"><form:input
          cssStyle="width:80px;" path="dueDate" id="datepicker2" /></td>
        <td></td>
      </tr>
      <tr>       
       <td><strong>Product</strong></td>
        <td><form:select 
          path="workProductDis" multiple="single">
          <form:options items="${workProductMap}" />
        </form:select></td>
         <td><strong>Type</strong></td>
        <td><form:select 
          path="typeDis" multiple="single">
          <form:options items="${typeMap}" />
        </form:select></td>
         <td><strong>Title</strong></td>
         <td><form:input cssStyle="width:80px;"
          path="title"  /></td>
         <td><strong>Test Case Id</strong></td>
          <td><form:input cssStyle="width:80px;"
          path="testCaseId"  /></td>
      </tr>
    </tbody>
  </table>

  <p><input type="submit" id="Search" class="button blue small"
    name="Search" value="Search" /></p>

</form:form> <portlet:renderURL var="defectFormAction2">
  <portlet:param name="action" value="goAddNewDefect" />
</portlet:renderURL> <form:form name="defect" method="post" commandName="viewDefectList"
  action="${defectFormAction2}">
  <p><input type="submit" id="Search" class="button green small"
    name="Add" value="Add" /></p>
     <c:if test="${not empty defectError}"> <label id="noSelect" style="display: inline; color: red;">${defectError}</label><br></c:if>   
      
    <div id="errorDiv" align="center" style=" display: none; ">    
       
           <label id="formatFromDate" style="display: none; color: red;">Please input correct format from date.  </label><br>
              <label id="formatToDate" style="display: none; color: red;">Please input correct format to date.  </label>
    </div><br>
    
    
</form:form> <portlet:actionURL var="goUpdateDefectAction">
  <portlet:param name="action" value="goUpdateDefect" />
</portlet:actionURL> <portlet:actionURL var="deleteDefectAction">
  <portlet:param name="action" value="deleteDefect" />
</portlet:actionURL> 
<portlet:actionURL var="approveDefectAction">
  <portlet:param name="action" value="approveDefect" />
</portlet:actionURL>
<portlet:actionURL var="goRejectDefectAction">
  <portlet:param name="action" value="goRejectDefect" />
</portlet:actionURL><form:form name="Defect" method="post" commandName="viewDefectList"
  action="${goUpdateDefectAction}">
  <table id="mainTable2" class="display dataTable" cellpadding="0"
    cellspacing="0" border="0">
    <thead>
      <tr>
        <th width="5%"><input id="select-all" type="checkbox"
          name="allbox" value="checkAll"></th>
        <th width="10%">Defect ID</th>
          <th width="48%">Title</th> 
        <th width="10%">Status</th>
        <th width="5%">Test Case ID</th>
        <th width="5%">Severity</th>
        <th width="5%">Priority</th>
            
         <th width="5%">Assigned To</th>
        <th width="5%">Created By</th>
        <th width="48%">Fixed Date</th>
        <th width="7%">Due Date</th>
      </tr>
    </thead>
    <tbody align="center">
      <c:if test="${not empty defectList}">

        <c:forEach var="defect" varStatus="status" items="${defectList}">
          
          <tr>
            <td class="cb"><input id="checkbox" type="checkbox"
              name="defectList[${status.index}].defectId"
              value="${defect.defectId}"></td>
               <td><font color="">${defect.defectId}</font></td>
                <td><font color="">${defect.title}</font></td>
            <td><font color="">${defect.status}</font></td>
            <td><font color="">${defect.testCase}</font></td>
            <td><font color="">${defect.severity}</font></td>
            <td><font color="">${defect.priority}</font></td>
            
             <td><font color="">${defect.assignedTo}</font></td>
            <td><font color="">${defect.createdBy}</font></td>
            
             <td><font color="">${defect.fixedDateString}</font></td>
              <td><font color="">${defect.dueDateString}</font></td>
          
          </tr>
        </c:forEach>
       
      </c:if>

    </tbody>
  </table>
<br>
  <p>  
  <input type="button" class="button blue small" name="Update" id="btnUpdate"
    onclick='submitAction("Defect", "${goUpdateDefectAction}")'
    value="Update" class="button blue small" /> <input type="button"
    class="button blue small" name="Delete" id="btnUpdate"
    onclick='submitActionConfirm("Defect", "${deleteDefectAction}");'
    value="Delete" class="button red small" />
    <c:if test="${ROLE=='Project Manager' }">
    <input type="button" class="button blue small" name="Approve" id="btnUpdate"
    onclick='submitAction("Defect", "${approveDefectAction}")'
    value="Approve" class="button blue small" />
    <input type="button" class="button blue small" name="Reject" id="btnUpdate"
    onclick='submitAction("Defect", "${goRejectDefectAction}")'
    value="Reject" class="button blue small" />
    </c:if>
    
    </p>

</form:form></div>
</div>
</div>





</body>
</html>