<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  


<%@ page import="javax.portlet.*"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>

<portlet:defineObjects />


<b>
    Test page 2
    
      <portlet:actionURL var="formAction5">
  <portlet:param name="action" value="return" />
</portlet:actionURL>
 <portlet:actionURL var="formAction6">
  <portlet:param name="action" value="returnHome" />
</portlet:actionURL>

<form:form method="post" commandName="loginForm" action="${formAction5}">
   <form:button id="changepw"  class="button blue small" name="Change Password" value="Return">Return Portlet 2.0 </form:button>
 </form:form>
 <form:form method="post" commandName="loginForm" action="${formAction6}">
   <form:button id="changepw"  class="button blue small" name="Change Password" value="Return">Return Home</form:button>
 </form:form>
 
 
</b>