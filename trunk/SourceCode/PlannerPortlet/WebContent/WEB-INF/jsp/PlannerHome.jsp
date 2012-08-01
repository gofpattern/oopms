<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="header.jsp"></jsp:include>
</head>
<body>
  <h1>Hello To OOPMS Planner</h1>
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
