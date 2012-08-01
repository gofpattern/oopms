<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css"
  href="/<spring:message code="app.context"/>/resource_files/css/ui-lightness/jquery-ui-1.8.21.custom.css"
  rel="Stylesheet" />
<link type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/common.css" rel="Stylesheet" />
<link type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/uportal.css" rel="Stylesheet" />
<link type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/yav/yav-style.css" rel="Stylesheet" />
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
  src="/<spring:message code="app.context"/>/resource_files/js/jquery-ui-1.8.21.custom.min.js"></script>
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/form-elements.js"></script>
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/common.js"></script>
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/yav.js"></script>
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/yav-config.js"></script>
</head>
<body>
  <h1>Hello To OOPMS Planner</h1>
  <a href='<portlet:renderURL><portlet:param name="action" value="taskmanager"/></portlet:renderURL>'>Planner</a>
  <c:if test="${not empty projectList}">
    <table class="portlet-table">
      <tbody>
        <tr>
          <th width="70%" scope="row">Project Name</th>
          <th width="30%" scope="row">Project Code</th>
        </tr>
        <c:forEach var="project" items="${projectList}">
          <tr>
            <portlet:renderURL var="renderAction">
              <portlet:param name="action" value="taskmanager" />
              <portlet:param name="projectId" value="${project.projectId}" />
            </portlet:renderURL>
            <td scope="row"><a href="${renderAction}">${project.name}</a></td>
            <td scope="row"><a href="${renderAction}">${project.code}</a></td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </c:if>
  <c:if test="${empty projectList}">
        You haven't jointed any projects.
    </c:if>
</body>
</html>
