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
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/datatable.js"></script>
<link type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/datatable.css" rel="Stylesheet" /> 
	<portlet:actionURL var="ForcedRemoveCostType">
            <portlet:param name="action" value="ForcedRemoveCostType" />
            <portlet:param name="projectId" value="${projectId}" />
            <portlet:param name="oopmsCostTypeId" value="${deletingOopmsCostTypeId}" />
    </portlet:actionURL>
<SCRIPT type="text/javascript">
var rules = new Array();
rules[0] = 'viewDate:View Date|required';
rules[1] = 'viewDate:View Date|date';
yav.addHelp('viewDate', 'Please input View Date before checking');
</SCRIPT>
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
        	

                     
                fnFeaturesInit();
                $('#mainTable1').dataTable( {
                    "bFilter": true,
                    "bSort": true,
                    "bJQueryUI": true,
                    "sPaginationType": "full_numbers"
                } );
                $('#mainTable2').dataTable( {
                    "bFilter": true,
                    "bSort": true,
                    "bJQueryUI": true,
                    "sPaginationType": "full_numbers"
                } );
                $('#mainTable3').dataTable( {
                    "bFilter": true,
                    "bSort": true,
                    "bJQueryUI": true,
                    "sPaginationType": "full_numbers"
                } );
                $('#mainTable4').dataTable( {
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
                
                $( "#datepicker1" ).datepicker({
    	            showOn: "button",
    	            buttonImage: "/<spring:message code='app.context'/>/resource_files/images/calendar.gif",
    	            buttonImageOnly: true
    	        });
                yav.init('${portletNamespace}CostManagement', rules);
                var actionUrl = "${ForcedRemoveCostType}";
                actionUrl = actionUrl.replace(/amp;/g, '');
                if(document.getElementById("deleteCostTypeFlag").value==1) {
    				document.getElementById("deleteCostTypeFlag").value = 0;
    				if(confirm('This Type is used by another Exceptional Costs. Delete it will also delete other Exceptional Costs that using it. Do you still want to delete it?')) {
    					submitAction("${portletNamespace}CostManagement", actionUrl);
    				}
    			}
                if(document.getElementById("deleteCostTypeFlag").value==2) {
    				document.getElementById("deleteCostTypeFlag").value = 0;
    				if(confirm('This Type is used by another Daily Expenses. Delete it will unset those Daily Expenses Type that using it. Do you still want to delete it?')) {
    					submitAction("${portletNamespace}CostManagement", actionUrl);
    				}
    			}
                if(document.getElementById("deleteCostTypeFlag").value==3) {
    				document.getElementById("deleteCostTypeFlag").value = 0;
    				if(confirm('This Type is used by another Exceptional Costs and Daily Expenses. Delete it will also delete other Exceptional Costs and unset those Daily Expenses Type that using it. Do you still want to delete it?')) {
    					submitAction("${portletNamespace}CostManagement", actionUrl);
    				}
    			}
            } );
        </script>	
</head>

<body id="portal" class="up fl-theme-mist">

<div class="container"><%@ include file="/WEB-INF/jsp/Menu.jsp" %>

  <div class="content">	
	<!-- begin .navigator -->
	 
	<!-- end .navigator -->
	
	   <div class="fl-widget-titlebar titlebar portlet-titlebar" role="sectionhead">
    	<br><p class="title" id="headerDuyND">Cost Management</p><br>
    </div>
<portlet:renderURL var="formAction1">
  	<portlet:param name="action" value="GoCreateOneTimeExpense" />
  	<portlet:param name="projectId" value="${projectId}" />
</portlet:renderURL>
<portlet:renderURL var="formAction2">
  	<portlet:param name="action" value="GoCreateDailyExpense" />
  	<portlet:param name="projectId" value="${projectId}" />
</portlet:renderURL>
<portlet:renderURL var="formAction3">
  	<portlet:param name="action" value="GoCreateExceptionalExpense" />
  	<portlet:param name="projectId" value="${projectId}" />
</portlet:renderURL>
<portlet:renderURL var="formAction4">
  	<portlet:param name="action" value="GoCreateExceptionalDeduct" />
  	<portlet:param name="projectId" value="${projectId}" />
</portlet:renderURL>
<portlet:renderURL var="formAction5">
  	<portlet:param name="action" value="GoCreateCostType" />
  	<portlet:param name="projectId" value="${projectId}" />
</portlet:renderURL>
<portlet:actionURL var="formAction6">
  	<portlet:param name="action" value="ViewExpense" />
  	<portlet:param name="projectId" value="${projectId}" />
</portlet:actionURL>
    <form:form name="${portletNamespace}CostManagement" commandName="CostManagementForm" method="post" action="${formAction6}">
    	By Date<form:input maxlength="10" path="viewDate" size="9" value="" type="text" id="datepicker1" style='width:80px'/>(mm/dd/yyyy)
    	<button type="button" class="button blue small" onclick='submitAction2("${portletNamespace}CostManagement", "${formAction6}")'>Check</button>
    	<br/><span id=errorsDiv_viewDate>&nbsp;</span>
    	<h4 style="color: red">Expense at ${viewDate} is : ${expense}</h4>
    </form:form>
	<br>
 <p id="header2DuyND" style="text-align:center">--------------------------------Expense Detail--------------------------------</p>
 <h3>One Time Expense</h3>   
 <table class="display dataTable" id="mainTable1" cellpadding="0" cellspacing="0" border="0">	
   <thead>
   	<tr>
   		<th width="5%" scope="row">No</th>
        <th width="20%" scope="row">Name</th>    
        <th width="10%" scope="row">Cost($)</th> 
        <th width="10%" scope="row">Date</th>
        <th width="45%" scope="row">Description</th>
        <th width="10%" scope="row">Action</th>
    </tr>
   </thead>
   <tbody>
   <c:forEach var="oneTimeExpense" items="${OneTimeExpenseList}" varStatus="count">
   	<tr>  	
   			<portlet:renderURL var="renderAction2">
            	<portlet:param name="action" value="GoUpdateOneTimeExpense" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="oopmsCostOneTimeExpenseId" value="${oneTimeExpense.oopmsCostOneTimeExpenseId}" />
            </portlet:renderURL>
            <portlet:actionURL var="renderAction3">
            	<portlet:param name="action" value="RemoveOneTimeExpense" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="oopmsCostOneTimeExpenseId" value="${oneTimeExpense.oopmsCostOneTimeExpenseId}" />
            </portlet:actionURL>
     		   <td scope="row">${count.count}</td>
               <td scope="row"><a href="${renderAction2}">${oneTimeExpense.name}</a></td>
               <td scope="row">${oneTimeExpense.cost}</td>
               <td scope="row">${oneTimeExpense.occurDate}</td>
               <td scope="row">${oneTimeExpense.description}</td>
               <td scope="row">
               	<button type="button" class="button blue small" onclick='return submitAction3("${portletNamespace}CostManagement", "${renderAction3}", "Do you sure you want to delete this Expense?");'>Remove</button>
               </td>
     </tr>
    </c:forEach>
    </tbody>
</table> 	
<br>
	<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}CostManagement", "${formAction1}")'>Add new One Time Expense</button>
<h3>&nbsp;</h3><h3>Daily Expense</h3>
<table class="display dataTable" id="mainTable2" cellpadding="0" cellspacing="0" border="0">	
   <thead>
   	<tr>
   		<th width="5%" scope="row">No</th>
        <th width="10%" scope="row">Name</th>    
        <th width="10%" scope="row">Cost/day($)</th> 
        <th width="10%" scope="row">Start Date</th>
        <th width="10%" scope="row">End Date</th>
        <th width="20%" scope="row">Use on following days</th>
        <th width="10%" scope="row">Type</th>
        <th width="20%" scope="row">Description</th>
        <th width="10%" scope="row">Action</th>
    </tr>
   </thead>
   <tbody>
   <c:forEach var="dailyExpense" items="${DailyExpenseList}" varStatus="count">
   	<tr>  	
   			<portlet:renderURL var="renderAction2">
            	<portlet:param name="action" value="GoUpdateDailyExpense" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="oopmsCostDailyExpenseId" value="${dailyExpense.oopmsCostDailyExpenseId}" />
            </portlet:renderURL>
            <portlet:actionURL var="renderAction3">
            	<portlet:param name="action" value="RemoveDailyExpense" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="oopmsCostDailyExpenseId" value="${dailyExpense.oopmsCostDailyExpenseId}" />
            </portlet:actionURL>
     		   <td scope="row">${count.count}</td>
               <td scope="row"><a href="${renderAction2}">${dailyExpense.name}</a></td>
               <td scope="row">${dailyExpense.cost}</td>
               <td scope="row">${dailyExpense.startDate}</td>
               <td scope="row">${dailyExpense.endDate}</td>
               <td scope="row">${dailyExpense.dateUsed}</td>
               <td scope="row">${dailyExpense.type}</td>
               <td scope="row">${dailyExpense.description}</td>
               <td scope="row">
               	<button type="button" class="button blue small" onclick='return submitAction3("${portletNamespace}CostManagement", "${renderAction3}", "Do you sure you want to delete this Expense?");'>Remove</button>
               </td>
     </tr>
    </c:forEach>
    </tbody>
</table>
<br>
	<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}CostManagement", "${formAction2}")'>Add new Daily Expense</button>
<h3>&nbsp;</h3><h3>Exceptional Expense</h3>
<table class="display dataTable" id="mainTable3" cellpadding="0" cellspacing="0" border="0">	
   <thead>
   	<tr>
   		<th width="5%" scope="row">No</th>
        <th width="10%" scope="row">Name</th>    
        <th width="20%" scope="row">Affect to</th> 
        <th width="10%" scope="row">Addition effect</th>
        <th width="10%" scope="row">Date</th>
        <th width="35%" scope="row">Description</th>
        <th width="10%" scope="row">Action</th>
    </tr>
   </thead>
   <tbody>
   <c:forEach var="exceptionalExpense" items="${ExceptionalExpenseList}" varStatus="count">
   	<tr>  	
   			<portlet:renderURL var="renderAction2">
            	<portlet:param name="action" value="GoUpdateExceptinalExpense" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="oopmsExceptionalCostId" value="${exceptionalExpense.oopmsExceptionalCostId}" />
            </portlet:renderURL>
            <portlet:actionURL var="renderAction3">
            	<portlet:param name="action" value="RemoveExceptionalCost" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="oopmsExceptionalCostId" value="${exceptionalExpense.oopmsExceptionalCostId}" />
            </portlet:actionURL>
     		   <td scope="row">${count.count}</td>
               <td scope="row"><a href="${renderAction2}">${exceptionalExpense.name}</a></td>
               <td scope="row">${exceptionalExpense.affectTo}</td>
               <td scope="row">${exceptionalExpense.additionEffect}</td>
               <td scope="row">${exceptionalExpense.occurDate}</td>
               <td scope="row">${exceptionalExpense.description}</td>
               <td scope="row">
               	<button type="button" class="button blue small" onclick='return submitAction3("${portletNamespace}CostManagement", "${renderAction3}", "Do you sure you want to delete this Expense?");'>Remove</button>
               </td>
     </tr>
    </c:forEach>
    </tbody>
</table>
<br>
	<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}CostManagement", "${formAction3}")'>Add new Exceptional Expense</button>
<h3>&nbsp;</h3><h3>Exceptional Deduct</h3>
<table class="display dataTable" id="mainTable4" cellpadding="0" cellspacing="0" border="0">	
   <thead>
   	<tr>
   		<th width="5%" scope="row">No</th>
        <th width="10%" scope="row">Name</th>    
        <th width="20%" scope="row">Affect to</th> 
        <th width="10%" scope="row">Addition effect</th>
        <th width="10%" scope="row">Date</th>
        <th width="35%" scope="row">Description</th>
        <th width="10%" scope="row">Action</th>
    </tr>
   </thead>
   <tbody>
   <c:forEach var="exceptionalDeduct" items="${ExceptionalDeductList}" varStatus="count">
   	<tr>  	
   			<portlet:renderURL var="renderAction2">
            	<portlet:param name="action" value="GoUpdateExceptinalDeduct" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="oopmsExceptionalCostId" value="${exceptionalDeduct.oopmsExceptionalCostId}" />
            </portlet:renderURL>
            <portlet:actionURL var="renderAction3">
            	<portlet:param name="action" value="RemoveExceptionalCost" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="oopmsExceptionalCostId" value="${exceptionalDeduct.oopmsExceptionalCostId}" />
            </portlet:actionURL>
     		   <td scope="row">${count.count}</td>
               <td scope="row"><a href="${renderAction2}">${exceptionalDeduct.name}</a></td>
               <td scope="row">${exceptionalDeduct.affectTo}</td>
               <td scope="row">${exceptionalDeduct.additionEffect}</td>
               <td scope="row">${exceptionalDeduct.occurDate}</td>
               <td scope="row">${exceptionalDeduct.description}</td>
               <td scope="row">
               	<button type="button" class="button blue small" onclick='return submitAction3("${portletNamespace}CostManagement", "${renderAction3}", "Do you sure you want to delete this Deduct?");'>Remove</button>
               </td>
     </tr>
    </c:forEach>
    </tbody>
</table>
<br>
	<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}CostManagement", "${formAction4}")'>Add new Exceptional Deduct</button>
<h3>&nbsp;</h3><h3>Cost Types</h3>
 <table class="display dataTable" id="mainTable5" cellpadding="0" cellspacing="0" border="0">	
   <thead>
   	<tr>
   		<th width="5%" scope="row">No</th>
        <th width="30%" scope="row">Name</th>    
        <th width="55%" scope="row">Description</th>
        <th width="10%" scope="row">Action</th>
    </tr>
   </thead>
   <tbody>
   <c:forEach var="costType" items="${CostTypeList}" varStatus="count">
   	<tr>  	
   			<portlet:renderURL var="renderAction2">
            	<portlet:param name="action" value="GoUpdateCostType" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="oopmsCostTypeId" value="${costType.oopmsCostTypeId}" />
            </portlet:renderURL>
            <portlet:actionURL var="renderAction3">
            	<portlet:param name="action" value="RemoveCostType" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="oopmsCostTypeId" value="${costType.oopmsCostTypeId}" />
            </portlet:actionURL>
     		   <td scope="row">${count.count}</td>
               <td scope="row"><a href="${renderAction2}">${costType.name}</a></td>
               <td scope="row">${costType.description}</td>
               <td scope="row">
               	<button type="button" class="button blue small" onclick='return submitAction3("${portletNamespace}CostManagement", "${renderAction3}", "Do you sure you want to delete this Type?");'>Remove</button>
               </td>
     </tr>
    </c:forEach>
    </tbody>
</table>
<br>
	<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}CostManagement", "${formAction5}")'>Add new Type</button>
	<input name="deleteCostTypeFlag" type="hidden" value="${deleteCostTypeFlag}" id="deleteCostTypeFlag"/>
  <!-- end .content --></div>
  <!-- end .container --></div>
</body>
</html>
