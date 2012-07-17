<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>

<portlet:defineObjects />
<c:set var="portletNamespace" scope="request"><portlet:namespace/></c:set>

<script type="text/javascript" src='/ActionPortletExample/scripts/common.js'></script>
<b>
 Test page 2
  <portlet:renderURL var="formAction3">
    <portlet:param name="action" value="return" />
  </portlet:renderURL>
  <portlet:actionURL var="formAction4">
    <portlet:param name="action" value="returnHome" />
  </portlet:actionURL>
  
  <form:form name="${portletNamespace}Return" method="post" commandName="loginForm" action="#">

    <form:button onclick='submitAction("${portletNamespace}Return", "${formAction3}")' class="button blue small" name="Return" value="Return">Return NG</form:button>
    <input onclick='submitAction("${portletNamespace}Return", "${formAction3}")' name="Return" value="Return OK" type="button"/>
    <form:button onclick='submitAction("${portletNamespace}Return", "${formAction4}")' class="button blue small" name="Change Password" value="Return">Return Home</form:button>
  </form:form>
 
 
</b>