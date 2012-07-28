
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
</head>
<body >
  <h1>Hello To OOPMS Planner</h1>
  <a href='<portlet:renderURL><portlet:param name="action" value="taskmanager"/></portlet:renderURL>'>Planner</a> 
</body>
</html>
