<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  

<%-- Uncomment below lines to add portlet taglibs to jsp
<%@ page import="javax.portlet.*"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>

<portlet:defineObjects />
--%>

<b>
    Test page 2
    
      <portlet:actionURL var="formAction3">
  <portlet:param name="action" value="return" />
</portlet:actionURL>
 <portlet:actionURL var="formAction4">
  <portlet:param name="action" value="returnHome" />
</portlet:actionURL>

<form:form method="post" commandName="loginForm" action="${formAction3}">
   <form:button id="changepw"  class="button blue small" name="Change Password" value="Return">Return </form:button>
 </form:form>
 <form:form method="post" commandName="loginForm" action="${formAction4}">
   <form:button id="changepw"  class="button blue small" name="Change Password" value="Return">Return Home</form:button>
 </form:form>
 
 
</b>