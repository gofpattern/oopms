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
    <portlet:actionURL var="ForcedRemoveDailyExpense">
            <portlet:param name="action" value="ForcedRemoveDailyExpense" />
            <portlet:param name="projectId" value="${projectId}" />
            <portlet:param name="oopmsCostDailyExpenseId" value="${deletingOopmsCostDailyExpenseId}" />
    </portlet:actionURL>
<SCRIPT type="text/javascript">
var rules = new Array();
rules[0] = 'viewDate:View Date|required';
rules[1] = 'viewDate:View Date|date';
rules[2] = 'payDate:Pay Date|required';
rules[3] = 'payDate:Pay Date|date';
yav.addHelp('viewDate', 'Please input View Date before checking');
yav.addHelp('payDate', 'Please input Pay Date before paying daily expense');
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
                $('#mainTable6').dataTable( {
                    "bFilter": true,
                    "bSort": true,
                    "bJQueryUI": true,
                    "sPaginationType": "full_numbers"
                } );
                $('#mainTable7').dataTable( {
                    "bFilter": true,
                    "bSort": true,
                    "bJQueryUI": true,
                    "sPaginationType": "full_numbers"
                } );
                $('#mainTable8').dataTable( {
                    "bFilter": true,
                    "bSort": true,
                    "bJQueryUI": true,
                    "sPaginationType": "full_numbers"
                } );
                $('#mainTable9').dataTable( {
                    "bFilter": true,
                    "bSort": true,
                    "bJQueryUI": true,
                    "sPaginationType": "full_numbers"
                } );
                $('#mainTable10').dataTable( {
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
                $( "#datepicker2" ).datepicker({
    	            showOn: "button",
    	            buttonImage: "/<spring:message code='app.context'/>/resource_files/images/calendar.gif",
    	            buttonImageOnly: true
    	        });
                yav.init('${portletNamespace}CostManagement', rules);
                if(document.getElementById("deleteDailyFlag").value==1) {}
                var actionUrl = "${ForcedRemoveCostType}";
                var actionUrl2 = "${ForcedRemoveDailyExpense}";
                actionUrl = actionUrl.replace(/amp;/g, '');
                actionUrl2 = actionUrl2.replace(/amp;/g, '');
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
                
                if(document.getElementById("deleteDailyFlag").value==1) {
    				document.getElementById("deleteDailyFlag").value = 0;
    				if(confirm('This Daily Expense is used by another Exceptional Costs. Delete it will also delete other Exceptional Costs that using it. Do you still want to delete it?')) {
    					submitAction("${portletNamespace}CostManagement", actionUrl2);
    				}
    			}
                
                if(document.getElementById("payExceptionalCostFlag").value==1) {
    				document.getElementById("payExceptionalCostFlag").value = 0;
    				alert('This Exceptional Cost daily expenses are not paid yet. Please pay all daily expense relate to this Exceptional Cost before pay it');
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
<portlet:renderURL var="formAction7">
  	<portlet:param name="action" value="GoCreateBudgetRecords" />
  	<portlet:param name="projectId" value="${projectId}" />
</portlet:renderURL>
<portlet:renderURL var="formAction8">
  	<portlet:param name="action" value="GoCostManagement" />
  	<portlet:param name="projectId" value="${projectId}" />
  	<portlet:param name="ViewBudgetRecord" value="ViewBudgetRecord" />
</portlet:renderURL>
<portlet:renderURL var="formAction9">
  	<portlet:param name="action" value="GoCostManagement" />
  	<portlet:param name="projectId" value="${projectId}" />
  	<portlet:param name="ViewInvoiceRecords" value="ViewInvoiceRecords" />
</portlet:renderURL>
<portlet:renderURL var="formAction10">
  	<portlet:param name="action" value="ExportInvoice" />
  	<portlet:param name="projectId" value="${projectId}" />
  	<portlet:param name="templatePath" value="${templatePath}" />
  	<portlet:param name="exportPath" value="${exportPath}" />
</portlet:renderURL>
<form:form name="${portletNamespace}CostManagement" commandName="CostManagementForm" method="post" action="${formAction6}">
Current Budget is : ${currentBudget}
<br><button type="button" class="button blue small" onclick='submitAction("${portletNamespace}CostManagement", "${formAction7}")'>Add new Budget Record</button>
<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}CostManagement", "${formAction8}")'>View Budget Records</button>
<c:if test="${not empty BudgetRecords}">
	<p id="header2DuyND" style="text-align:center">--------------------------------Budget Records--------------------------------</p>
</c:if>
	<c:if test="${not empty BudgetRecords}">
	<table class="display dataTable" id="mainTable6" cellpadding="0" cellspacing="0" border="0">	

   <thead>
   	<tr>
   		<th width="5%" scope="row">No</th>
        <th width="10%" scope="row">Value</th>    
        <th width="10%" scope="row">Type</th> 
        <th width="65%" scope="row">Description</th>
        <th width="10%" scope="row">Action</th>
    </tr>
   </thead>
   <tbody>
   <c:forEach var="budget" items="${BudgetRecords}" varStatus="count">
   	<tr>  	
   			<portlet:renderURL var="renderAction2">
            	<portlet:param name="action" value="GoUpdateBudgetRecords" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="oopmsBudgetId" value="${budget.oopmsBudgetId}" />
            </portlet:renderURL>
            <portlet:actionURL var="renderAction3">
            	<portlet:param name="action" value="RemoveBudgetRecord" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="oopmsBudgetId" value="${budget.oopmsBudgetId}" />
            	<portlet:param name="budgetValue" value="${budget.value}" />
            	<portlet:param name="budgetType" value="${budget.type}" />
            </portlet:actionURL>
     		   <td scope="row">${count.count}</td>
               <td scope="row"><a href="${renderAction2}">${budget.value}</a></td>
               <td scope="row">
               		<c:if test="${budget.type == 0}">
               			Increase
               		</c:if>
               		<c:if test="${budget.type == 1}">
               			Decrease
               		</c:if>
               </td>
               <td scope="row">${budget.description}</td>
               <td scope="row">
               	<button type="button" class="button blue small" onclick='return submitAction3("${portletNamespace}CostManagement", "${renderAction3}", "Do you sure you want to delete this Budget record?");'>Remove</button>
               </td>
     </tr>
    </c:forEach>
    </tbody>
   
</table> 	
 </c:if>
	<br>

<br>
Current Invoice is : ${currentExpense}
<br><button type="button" class="button blue small" onclick='submitAction("${portletNamespace}CostManagement", "${formAction9}")'>View Invoice Records</button><br>
<c:if test="${not empty InvoiceRecords}">
	<p id="header2DuyND" style="text-align:center">--------------------------------Invoice Records--------------------------------</p>
<br><button type="button" class="button blue small" onclick='submitAction("${portletNamespace}CostManagement", "${formAction10}")'>Export to Excel</button><br>
</c:if>
<c:if test="${not empty InvoiceOneTime}">
<h3>One Time Expense</h3>
</c:if>
	<table class="display dataTable" id="mainTable7" cellpadding="0" cellspacing="0" border="0">	
	<c:if test="${not empty InvoiceOneTime}">
   <thead>
   	<tr>
   		<th width="5%" scope="row">No</th>
        <th width="10%" scope="row">Name</th>    
        <th width="65%" scope="row">Description</th> 
        <th width="10%" scope="row">Date</th>
        <th width="10%" scope="row">Total</th>
    </tr>
   </thead>
   <tbody>
   <c:forEach var="invoiceOneTime" items="${InvoiceOneTime}" varStatus="count">
   	<tr>  	
     		   <td scope="row">${count.count}</td>
               <td scope="row">${invoiceOneTime.name}</td>
               <td scope="row">${invoiceOneTime.description}</td>
               <td scope="row">${invoiceOneTime.occurDate}</td>
               <td scope="row">${invoiceOneTime.cost}</td>
     </tr>
    </c:forEach>
    </tbody>
    </c:if>
</table> 	
	<br>
	
<c:if test="${not empty InvoiceDaily}">
<h3>Daily Expense</h3>
</c:if>
	<table class="display dataTable" id="mainTable8" cellpadding="0" cellspacing="0" border="0">	
	<c:if test="${not empty InvoiceDaily}">
   <thead>
   	<tr>
   		<th width="5%" scope="row">No</th>
        <th width="10%" scope="row">Name</th>    
        <th width="55%" scope="row">Description</th> 
        <th width="10%" scope="row">Cost/day</th>
        <th width="10%" scope="row">Days</th>
        <th width="10%" scope="row">Total</th>
    </tr>
   </thead>
   <tbody>
   <c:forEach var="invoiceDaily" items="${InvoiceDaily}" varStatus="count">
   	<tr>  	
     		   <td scope="row">${count.count}</td>
               <td scope="row">${invoiceDaily.name}</td>
               <td scope="row">${invoiceDaily.description}</td>
               <td scope="row">${invoiceDaily.costDay}</td>
               <td scope="row">${invoiceDaily.days}</td>
               <td scope="row">${invoiceDaily.total}</td>
     </tr>
    </c:forEach>
    </tbody>
    </c:if>
</table> 	
	<br>
	
<c:if test="${not empty InvoiceExceptionalExpense}">
<h3>Exceptional Expense</h3>
</c:if>
	<table class="display dataTable" id="mainTable9" cellpadding="0" cellspacing="0" border="0">	
	<c:if test="${not empty InvoiceExceptionalExpense}">
   <thead>
   	<tr>
   		<th width="5%" scope="row">No</th>
        <th width="10%" scope="row">Name</th>    
        <th width="55%" scope="row">Description</th> 
        <th width="10%" scope="row">AffectTo</th>
        <th width="10%" scope="row">Effect</th>
        <th width="10%" scope="row">Total</th>
    </tr>
   </thead>
   <tbody>
   <c:forEach var="invoiceExceptional" items="${InvoiceExceptionalExpense}" varStatus="count">
   	<tr>  	
     		   <td scope="row">${count.count}</td>
               <td scope="row">${invoiceExceptional.name}</td>
               <td scope="row">${invoiceExceptional.description}</td>
               <td scope="row">${invoiceExceptional.effectTo}</td>
               <td scope="row">${invoiceExceptional.effect}</td>
               <td scope="row">${invoiceExceptional.total}</td>
     </tr>
    </c:forEach>
    </tbody>
    </c:if>
</table> 	
	<br>
	
<c:if test="${not empty InvoiceExceptionalDeduct}">
<h3>Exceptional Deduct</h3>
</c:if>
	<table class="display dataTable" id="mainTable10" cellpadding="0" cellspacing="0" border="0">	
	<c:if test="${not empty InvoiceExceptionalDeduct}">
   <thead>
   	<tr>
   		<th width="5%" scope="row">No</th>
        <th width="10%" scope="row">Name</th>    
        <th width="55%" scope="row">Description</th> 
        <th width="10%" scope="row">AffectTo</th>
        <th width="10%" scope="row">Effect</th>
        <th width="10%" scope="row">Total</th>
    </tr>
   </thead>
   <tbody>
   <c:forEach var="invoiceExceptional" items="${InvoiceExceptionalDeduct}" varStatus="count">
   	<tr>  	
     		   <td scope="row">${count.count}</td>
               <td scope="row">${invoiceExceptional.name}</td>
               <td scope="row">${invoiceExceptional.description}</td>
               <td scope="row">${invoiceExceptional.effectTo}</td>
               <td scope="row">${invoiceExceptional.effect}</td>
               <td scope="row">${invoiceExceptional.total}</td>
     </tr>
    </c:forEach>
    </tbody>
    </c:if>
</table> 	
	<br>
	    
    	By Date<form:input maxlength="10" path="viewDate" size="9" value="" type="text" id="datepicker1" style='width:80px'/>(mm/dd/yyyy)
    	<button type="button" class="button blue small" onclick='submitAction2("${portletNamespace}CostManagement", "${formAction6}")'>Check</button>
    	<br/><span id=errorsDiv_viewDate>&nbsp;</span>
    	<h4 style="color: red">Expense at ${viewDate} is : ${expense}</h4>
    
	<br>
 <p id="header2DuyND" style="text-align:center">--------------------------------Expense Detail--------------------------------</p>
 <h3>One Time Expense</h3>   
 <table class="display dataTable" id="mainTable1" cellpadding="0" cellspacing="0" border="0">	
   <thead>
   	<tr>
   		<th width="5%" scope="row">No</th>
        <th width="20%" scope="row">Name</th>    
        <th width="10%" scope="row">Cost($)</th> 
        <th width="13%" scope="row">Date</th>
        <th width="32%" scope="row">Description</th>
        <th width="20%" scope="row">Action</th>
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
            <portlet:actionURL var="renderAction4">
            	<portlet:param name="action" value="PayOneTimeExpense" />
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
               	<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}CostManagement", "${renderAction4}")'>Pay</button>
               </td>
     </tr>
    </c:forEach>
    </tbody>
</table> 	
<br>
	<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}CostManagement", "${formAction1}")'>Add new One Time Expense</button>
<h3>&nbsp;</h3><h3>Daily Expense</h3>
Set Date to Pay<form:input maxlength="10" path="payDate" size="9" value="" type="text" id="datepicker2" style='width:80px'/><span id=errorsDiv_payDate>&nbsp;</span><br>
<table class="display dataTable" id="mainTable2" cellpadding="0" cellspacing="0" border="0">	
   <thead>
   	<tr>
   		<th width="5%" scope="row">No</th>
        <th width="10%" scope="row">Name</th>    
        <th width="10%" scope="row">Cost/day($)</th> 
        <th width="10%" scope="row">Start Date</th>
        <th width="10%" scope="row">End Date</th>
        <th width="18%" scope="row">Use on following days</th>
        <th width="7%" scope="row">Type</th>
        <th width="10%" scope="row">Description</th>
        <th width="20%" scope="row">Action</th>
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
            <portlet:actionURL var="renderAction4">
            	<portlet:param name="action" value="PayDailyExpense" />
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
               	<button type="button" class="button blue small" onclick='submitAction2("${portletNamespace}CostManagement", "${renderAction4}")'>Pay</button>
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
        <th width="15%" scope="row">Name</th>    
        <th width="10%" scope="row">Affect to</th> 
        <th width="15%" scope="row">Addition effect</th>
        <th width="13%" scope="row">Date</th>
        <th width="22%" scope="row">Description</th>
        <th width="20%" scope="row">Action</th>
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
            <portlet:actionURL var="renderAction4">
            	<portlet:param name="action" value="PayExceptionalCost" />
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
               	<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}CostManagement", "${renderAction4}")'>Pay</button>
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
        <th width="15%" scope="row">Name</th>    
        <th width="10%" scope="row">Affect to</th> 
        <th width="15%" scope="row">Addition effect</th>
        <th width="13%" scope="row">Date</th>
        <th width="22%" scope="row">Description</th>
        <th width="20%" scope="row">Action</th>
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
            <portlet:actionURL var="renderAction4">
            	<portlet:param name="action" value="PayExceptionalCost" />
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
               	<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}CostManagement", "${renderAction4}")'>Pay</button>
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
        <th width="45%" scope="row">Description</th>
        <th width="20%" scope="row">Action</th>
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
               <td scope="row" align="center">
               	<button type="button" class="button blue small" onclick='return submitAction3("${portletNamespace}CostManagement", "${renderAction3}", "Do you sure you want to delete this Type?");'>Remove</button>
               </td>
     </tr>
    </c:forEach>
    </tbody>
</table>
<br>
	<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}CostManagement", "${formAction5}")'>Add new Type</button>
	<input name="deleteCostTypeFlag" type="hidden" value="${deleteCostTypeFlag}" id="deleteCostTypeFlag"/>
	<input name="deleteDailyFlag" type="hidden" value="${deleteDailyFlag}" id="deleteDailyFlag"/>
	<input name="payExceptionalCostFlag" type="hidden" value="${payExceptionalCostFlag}" id="payExceptionalCostFlag"/>
	</form:form>
  <!-- end .content --></div>
  <!-- end .container --></div>
</body>
</html>
