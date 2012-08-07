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
<meta name="robots" content="noindex, nofollow"/>
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
                $('#mainTable2').dataTable( {
                    "bFilter": true,
                    "bSort": true,
                    "bJQueryUI": true,
                    "sPaginationType": "full_numbers"
                } );

            } );
        </script>



<title>ProjectEye Home</title>
</head>
<body>
<div style="border-style:ridge" class="up-portlet-content-wrapper-inner">
<portlet:renderURL var="formAction">
  <portlet:param name="action" value="GoCreateProject" />
</portlet:renderURL>
<form:form name="${portletNamespace}ProjectEyeHome" method="post" action="${formAction}" >
</form:form>
  <button type="button" class="button blue small" onclick='submitAction("${portletNamespace}ProjectEyeHome", "${formAction}")'>Create New Project</button>
<br/>
<table class="display dataTable" id="mainTable2" cellpadding="0" cellspacing="0" border="0">

	<c:if test="${not empty projectList}">
	<thead>
   <tr >
   		<th width="5%" scope="row">No</th>
        <th width="30%" scope="row">Project Name</th>    
        <th width="10%" scope="row">Project Code</th> 
        <th width="25%" scope="row">Your role in project</th>
        <th width="30%" scope="row">Action</th>   
    </tr>
    </thead>
   <tbody> 
  
        <c:forEach var="project" items="${projectList}" varStatus="count">
            <tr>
            <portlet:renderURL var="renderAction">
            	<portlet:param name="action" value="GoProjectDetail" />
            	<portlet:param name="projectId" value="${project.projectId}" />
            </portlet:renderURL>
            <portlet:renderURL var="renderAction2">
            	<portlet:param name="action" value="DeleteProject" />
            	<portlet:param name="projectId" value="${project.projectId}" />
            </portlet:renderURL>
            <portlet:renderURL var="renderAction3">
            	<portlet:param name="action" value="LeaveProject" />
            	<portlet:param name="projectId" value="${project.projectId}" />
            </portlet:renderURL>
               <td scope="row">${count.count}</td>
               <td scope="row"><a href="${renderAction}">${project.name}</a></td>
               <td scope="row"><a href="${renderAction}">${project.code}</a></td>
               <td scope="row">${project.roleString}</td>
               <td scope="row">
               <c:if test="${project.role != 0 }">
               	<c:if test="${project.role != 1 }">
               	 <c:if test="${project.role != 6 }">
               	 	<button type="button" class="button blue small" onclick='submitAction3("${portletNamespace}ProjectEyeHome", "${renderAction3}", "Do you sure you want to leave this project?")'>Leave this project</button>
               	 </c:if>
               	</c:if>
               </c:if>
               <c:if test="${project.role == 0 }">
               		<button type="button" class="button blue small" onclick='submitAction3("${portletNamespace}ProjectEyeHome", "${renderAction2}", "Do you sure you want to delete this project?")'>Delete this project</button>
               </c:if>
               <c:if test="${project.role == 6 }">
               		<button type="button" class="button blue small" onclick='submitAction3("${portletNamespace}ProjectEyeHome", "${renderAction2}", "Do you sure you want to delete this project?")'>Delete this project</button>
               </c:if>
               
               </td>                                      
            </tr>
        </c:forEach>
   </tbody>
	</c:if>
	<c:if test="${empty projectList}">
		You haven't jointed any projects.
	</c:if>
    
    
</table>
</div>
</body>
</html>