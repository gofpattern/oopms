<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet"%>
<%@ taglib prefix="portlet2" uri="http://java.sun.com/portlet_2_0"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="javax.portlet.PortletSession"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/<spring:message code="app.context"/>/resource_files/css/chart/basic.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/enhance.js"></script>    
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/css/jquery.js"></script>    

<portlet2:defineObjects />
<portlet:defineObjects />
<script type="text/javascript">
function submitAction(formName, actionUrl) {
	  
	var frm = document.forms[formName];
	    
	    frm.action = actionUrl;
	    
	    frm.submit();
	    
	    
	}    
    
    enhance({
        loadScripts: [
            '/<spring:message code="app.context"/>/resource_files/js/excanvas.js',
            '/<spring:message code="app.context"/>/resource_files/js/jquery.min.js',
            '/<spring:message code="app.context"/>/resource_files/js/visualize.jQuery.js',
            '/<spring:message code="app.context"/>/resource_files/js/example.js'
        ],
        loadStyles: [
            '/<spring:message code="app.context"/>/resource_files/css/chart/visualize.css',
            '/<spring:message code="app.context"/>/resource_files/css/chart/visualize-light.css'
        ]   
    });   


</SCRIPT>
</head>
<body id="portal" class="up fl-theme-mist">
 <portlet:renderURL var="BackAction">
        <portlet:param name="action" value="taskmanager" />
      </portlet:renderURL>
  <div class="container">
    <div class="content" align="center">
           <div align="left">
        <form:form commandName="PlannerForm" method="post" action="${BackAction}">
         <abbr title="Back to Task List"><input type="image" alt="Submit"
                            src="/<spring:message code="app.context"/>/resource_files/icons/back.png"
                            width="70" height="70"
                            onclick='this.form.submit()' /></abbr>
        </form:form>
      </div>
      <c:set var="UserInfo" value='<%=portletSession.getAttribute("UserInfo")%>' />
<%--       <table>
        <tr>
          <th><strong>User: </strong></th>
          <td><strong><font color="#1490E3">${UserInfo.username}</font></strong></td>
        </tr>
      </table> --%>
      <div align="center"><h1><strong><font color="#1490E3" size="10">PLANNER REPORT</font></strong></h1></div>
      <p></p>
      <p></p>
      <p></p>
      <p></p>
      <c:if test="${not empty reportInfoList}">
        <table id="reportTable">
        <caption>Tasks Distribution in ${project.name}</caption>
          <thead>
            <tr>
              <td></td>
              <th>Tentative</th>
              <th>OnGoing</th>
              <th>Closed</th>
              <th>Cancelled</th>
            </tr>
          </thead>
          <tbody>           
            <c:forEach var="reportInfo" items="${reportInfoList}">              
              <tr>                
                <th >${reportInfo.developerName}</th>
                <td>${reportInfo.totalTentativeTasks}</td>
                <td>${reportInfo.totalOngoingTasks}</td>
                <td>${reportInfo.totalClosedTasks}</td>
                <td>${reportInfo.totalCancelTasks}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </c:if>
      <c:if test="${empty reportInfoList}">
        <h5><spring:message code="emptyProjects.warning"/></h5>
    </c:if>
    </div>
    <br>
    <div class="footer">
      <p>OOPMS Group</p>
      <!-- end .footer -->
    </div>
  </div>
</body>
</html>
