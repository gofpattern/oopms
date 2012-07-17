<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<title>Project Management System</title>
<body>

  <div class="bg">

    <div id="header" class="">

      <div class="container narrow">

        <%-- Define actionURL --%>
        <portlet:actionURL var="formAction2">
          <portlet:param name="action" value="forward" />
        </portlet:actionURL>

        <div id="toolbar" class="">
          <div id="login">
            <a href='<portlet:renderURL><portlet:param name="action" value="forward"/></portlet:renderURL>'>Render Forward</a>
            <a href='<portlet:actionURL><portlet:param name="action" value="forward"/></portlet:actionURL>'>Action Forward</a>
            <a href='<portlet:renderURL><portlet:param name="action" value="forward2"/></portlet:renderURL>'>Render Forward Portlet 2.0</a>
            <a href='<portlet:actionURL><portlet:param name="action" value="forward2"/></portlet:actionURL>'>Action Forward Portlet 2.0</a>
          </div>

          <div class="callus">
            <form:form method="post" commandName="loginForm" action="${formAction2}">
              <form:button id="changepw" class="button blue small" name="Change Password" value="Change Password" />
            </form:form>
          </div>

        </div>
      </div>

    </div>

    <div id="content" class="content ">

      <div class="container narrow">

        <div id="tpl-manage" class="clearfix">

          <div id="tpl-signup">

            <div class="tpl-2col">

              <div class="col1 bigmargin">


                <div class="box">
                  <a href='<portlet:renderURL><portlet:param name="action" value="forward"/></portlet:renderURL>'>Render Forward</a>
                  <a href='<portlet:actionURL><portlet:param name="action" value="forward"/></portlet:actionURL>'>Action Forward</a>
                  <a href='<portlet:renderURL><portlet:param name="action" value="forward2"/></portlet:renderURL>'>Render Forward Portlet 2.0</a>
                  <a href='<portlet:actionURL><portlet:param name="action" value="forward2"/></portlet:actionURL>'>Action Forward Portlet 2.0</a>
                  
                  <%-- Define actionURL --%>
                  <portlet:actionURL var="formAction">
                    <portlet:param name="action" value="forward" />
                  </portlet:actionURL>

                  <form:form method="post" commandName="loginForm" action="${formAction}" class="default biglabel">



                    <input type="submit" id="login" name="login" value="Forward" class="button small ">



                  </form:form>

                </div>

                <div id="holdon"></div>

              </div>

            </div>

          </div>

        </div>


      </div>

    </div>

  </div>

</body>
</html>