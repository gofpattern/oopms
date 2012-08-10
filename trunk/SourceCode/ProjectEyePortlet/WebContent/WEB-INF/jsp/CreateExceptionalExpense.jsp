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
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/datatable.js"></script>
<link type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/datatable.css" rel="Stylesheet" /> 
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
	        yav.init('${portletNamespace}CreateExceptionalExpense', rules);
	        
	        fnFeaturesInit();
            $('#mainTable2').dataTable( {
                "bFilter": true,
                "bSort": true,
                "bJQueryUI": true,
                "sPaginationType": "full_numbers"
            } );
            $('#mainTable5').dataTable( {
                "bFilter": true,
                "bSort": true,
                "bJQueryUI": true,
                "sPaginationType": "full_numbers"
            } );
	});
    
    </script>
<SCRIPT type="text/javascript">
var rules = new Array();
rules[0] = 'name:Name|required';
rules[1] = 'occurDate:Date|required';
rules[2] = 'name:Name|maxlength|150';
rules[3] = 'occurDate:Date|date';
rules[4] = 'description:Description|maxlength|255';
rules[5] = 'ration:Cost Ration|double';
rules[6] = 'fixCost:Fix Cost|double';
yav.addHelp('name', 'Please input Name');
yav.addHelp('occurDate', 'Please input Date');

</SCRIPT>
<title>Create Daily Expense</title>	
</head>

<body id="portal" class="up fl-theme-mist">

<div id="portalPageBodyInner" class="container"><%@ include file="/WEB-INF/jsp/Menu.jsp" %>

  <div class="content">
  <!-- begin .navigator -->
	 
	<!-- end .navigator -->
   <div class="fl-widget-titlebar titlebar portlet-titlebar">
    	<h2 class="title" >Create Daily Expense</h2>
    </div>


	<portlet:actionURL var="formAction">
  		<portlet:param name="action" value="CreateExceptionalExpense" />
  		<portlet:param name="projectId" value="${projectId}" />
	</portlet:actionURL>
	<portlet:renderURL var="renderAction">
    		<portlet:param name="action" value="GoCostManagement" />
        	<portlet:param name="projectId" value="${projectId}" />
  	</portlet:renderURL>
<form:form name="${portletNamespace}CreateExceptionalExpense" commandName="CreateExceptionalExpenseForm" method="post" action="${formAction}">
	<table class="portlet-table">
  	  <tr>
        <th scope="row">Name*</th>
        <td><form:input path="name" value="" maxlength="150" size="100" type="text"/>
		<br/><span id=errorsDiv_name>&nbsp;</span></td>
      </tr>
      <tr>
        <th scope="row">Affect To*</th>
        <td>
        	<form:radiobutton path="affectTo" value="0"/>These type
<table class="display dataTable" id="mainTable5" cellpadding="0" cellspacing="0" border="0">	
   <thead>
   	<tr>
   		<th width="5%" scope="row"></th>
        <th width="20%" scope="row">Name</th>    
        <th width="55%" scope="row">Description</th>
    </tr>
   </thead>
   <tbody>
   <c:forEach var="costType" items="${CostTypeList}">
   	<tr>  	
     		   <td scope="row"><form:checkbox path="costTypes" value="${costType.oopmsCostTypeId}"/></td>
               <td scope="row">${costType.name}</td>
               <td scope="row">${costType.description}</td>
     </tr>
    </c:forEach>
    </tbody>
</table>
        	
			<form:radiobutton path="affectTo" value="1"/>or following
<table class="display dataTable" id="mainTable2" cellpadding="0" cellspacing="0" border="0">	
   <thead>
   	<tr>
   		<th width="5%" scope="row"></th>
        <th width="15%" scope="row">Name</th>    
        <th width="10%" scope="row">Cost/day($)</th> 
        <th width="10%" scope="row">Type</th>
        <th width="40%" scope="row">Description</th>
    </tr>
   </thead>
   <tbody>
   <c:forEach var="dailyExpense" items="${DailyExpenseList}">
   	<tr>  	
     		   <td scope="row"><form:checkbox path="dailyExpenses" value="${dailyExpense.oopmsCostDailyExpenseId}"/></td>
               <td scope="row">${dailyExpense.name}</td>
               <td scope="row">${dailyExpense.cost}</td>
               <td scope="row">${dailyExpense.type}</td>
               <td scope="row">${dailyExpense.description}</td>
     </tr>
    </c:forEach>
    </tbody>
</table>			
		</td>
      </tr>
      <tr>
        <th scope="row">Addition effect</th>
        <td>
        <form:radiobutton path="additionEffect" value="0"/>x <form:input maxlength="10" size="5" value="" path="ration" type="text"/>Cost
        <br/><span id=errorsDiv_ration>&nbsp;</span>
        <br/><form:radiobutton path="additionEffect" value="1"/>Fix Cost<form:input maxlength="60" path="fixCost" size="9" value="" type="text"/>$
        <br/><span id=errorsDiv_fixCost>&nbsp;</span>
        </td>
      </tr>
      <tr>
        <th scope="row">Date*</th>
        <td><form:input maxlength="10" path="occurDate" size="9" value="" type="text" id="datepicker1"/>
          (mm/dd/yyyy)<br></br><span id=errorsDiv_occurDate>&nbsp;</span></td>
      </tr>     
  	  <tr>
        <th scope="row">Description</th>
        <td><textarea rows="10" cols="70" name="description"></textarea>
		<br/><span id=errorsDiv_description>&nbsp;</span></td>
      </tr>
  </table>
	<button type="button" class="button blue small" onclick='submitAction2("${portletNamespace}CreateExceptionalExpense", "${formAction}")'>Create</button>
	<button type="reset" class="button blue small">Reset</button>
	<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}CreateExceptionalExpense", "${renderAction}")'>Cancel</button>
</form:form>


  <!-- end .content --></div>
  <!-- end .container --></div>
</body>
</html>
