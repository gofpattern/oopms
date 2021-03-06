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

            } );
        </script>

<title>Team Management</title>	
</head>

<body id="portal" class="up fl-theme-mist">

<div  class="container"><%@ include file="/WEB-INF/jsp/Menu.jsp" %>

  <div class="content">
  
  <!-- begin .navigator -->
	 
	<!-- end .navigator -->
  
   <div class="fl-widget-titlebar titlebar portlet-titlebar" role="sectionhead">
    	<br><p class="title" id="headerDuyND">Team Management</p>
    </div>
	
	<portlet:actionURL var="searchAction">
  <portlet:param name="action" value="SearchUser" />
  <portlet:param name="projectId" value="${projectId}" />
</portlet:actionURL>
<portlet:actionURL var="updateRoleAction">
  <portlet:param name="action" value="UpdateRoleAction" />
  <portlet:param name="projectId" value="${projectId}" />
</portlet:actionURL>
<portlet:renderURL var="renderAction">
            	<portlet:param name="action" value="GoProjectDetail" />
            	<portlet:param name="projectId" value="${projectId}" />
            </portlet:renderURL>
<form:form name="${portletNamespace}TeamManagement" commandName="TeamManagementForm" method="post" action="${formAction}">
	<table class="display dataTable" id="mainTable1" cellpadding="0" cellspacing="0" border="0">
	<c:if test="${not empty projectTeamList}">
   <thead><tr >
        <th width="5%" scope="row">No</th>    
        <th width="30%" scope="row">Name</th>
        <th width="20%" scope="row">Account</th>
        <th width="20%" scope="row">Member Role</th>
        <th width="25%" scope="row">Action</th>
    </tr>  
    </thead>
    <tbody>
        <c:forEach var="user" items="${projectTeamList}" varStatus="count">
            <tr>
            <portlet:renderURL var="renderAction2">
            	<portlet:param name="action" value="RemoveTeamMember" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="developerId" value="${user.developerId}" />
            </portlet:renderURL>
               <td scope="row">${count.count}</td>
               <td scope="row">${user.userName}</td>
               <td scope="row">${user.userAccount}</td>
               <td scope="row">
               <c:if test="${user.selectedRole == 0}">
               		Project Owner and Project Manager
               </c:if>
               <c:if test="${user.selectedRole == 1}">
               		Project Manager
               </c:if>
               <c:if test="${user.selectedRole == 6}">
               		Project Owner
               </c:if>
               <c:if test="${user.selectedRole != 0}">
               	<c:if test="${user.selectedRole != 1}">
               	<c:if test="${user.selectedRole != 6}">
               		<form:select  class="styled" path="projectTeamList[${count.index}].selectedRole" items="${roleList}"/>
               	</c:if>
               </c:if>
               </c:if>               		
               	</td>
               <td scope="row">
               <c:if test="${user.selectedRole != 0}">
               	<c:if test="${user.selectedRole != 1}">
               	<c:if test="${user.selectedRole != 6}">
               		<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}TeamManagement", "${renderAction2}")'>Remove from this Project</button>
               	</c:if>
               </c:if>
               </c:if> 
					
				</td>
				<form:input path="projectTeamList[${count.index}].developerId" value="${user.developerId}" type="hidden" />
            </tr>            
        </c:forEach>
   </tbody>
	</c:if>
	</table><br>
	<br/>
	<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}TeamManagement", "${updateRoleAction}")'>Save</button>
	<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}TeamManagement", "${renderAction}")'>Back</button>
	<br/><br>
	Search User <form:input path="searchString" value="" maxlength="50" size="50" type="text" /> 	
	<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}TeamManagement", "${searchAction}")'>Search</button><br/>
	<form:radiobutton path="searchType" value="name"/> By Name 
	<form:radiobutton path="searchType" value="account"/> by Account 
	<table class="display dataTable" id="mainTable2" cellpadding="0" cellspacing="0" border="0">
	<c:if test="${not empty userList}">
   <thead><tr >
        <th width="40%" scope="row">Name</th>    
        <th width="40%" scope="row">Account</th>
        <th width="20%" scope="row">Action</th>    
    </tr>
    </thead>
    <tbody>
    
  
        <c:forEach var="developer" items="${userList}">
            <tr>
            <portlet:renderURL var="AddAction">
            	<portlet:param name="action" value="AddUserToTeam" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="developerId" value="${developer.developerId}" />
            </portlet:renderURL>
               <td scope="row">${developer.name}</td>
               <td scope="row">${developer.account}</td>  
               <td scope="row"><button type="button" class="button blue small" onclick='submitAction("${portletNamespace}TeamManagement", "${AddAction}")'>Add to Project team</button></td>                                  
            </tr>
        </c:forEach>
   </tbody>
	</c:if>
	</table><br>
</form:form>
  <!-- end .content --></div>
  <!-- end .container --></div>
</body>
</html>
