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
<jsp:include page="header.jsp"></jsp:include>
<portlet2:defineObjects />
<portlet:defineObjects />
<script type="text/javascript">	
	// sort
	function fnFeaturesInit() {
		/* Not particularly modular this - but does nicely :-) */
		$('ul.limit_length>li').each(function(i) {
			if (i > 10) {
				this.style.display = 'none';
			}
		});

		$('ul.limit_length').append('<li class="css_link">Show more<\/li>');
		$('ul.limit_length li.css_link').click(function() {
			$('ul.limit_length li').each(function(i) {
				if (i > 5) {
					this.style.display = 'list-item';
				}
			});
			$('ul.limit_length li.css_link').css('display', 'none');
		});
	}
</script>
<script type="text/javascript">
	$(document).ready(function() {
		fnFeaturesInit();
		$('#projectTable').dataTable({
			"bFilter" : true,
			"bSort" : true,
			"bJQueryUI" : true,
			"sPaginationType" : "full_numbers"
		});
		// Check module of project
		  $(function() {
			  if('<%=portletSession.getAttribute("ERROR")%>'== 'true') {
								$("#dialog").dialog("open");
							}

						});

					});

	// jquery dialog
	$(function() {

		$("#dialog").dialog({
			autoOpen : false,
			resizable : false,
			height : 210,
			width : 450,
			modal : true,
			buttons : {
				Ok : function() {
					$(this).dialog("close");
				}
			}
		});
	});
</SCRIPT>
</head>
<body id="portal" class="up fl-theme-mist">
  <div class="container">

    <div id="dialog" title="Missing Products">
      <p>
        <font color="#1490E3"><spring:message code="emptyModules.warning_1"/><b><%=portletSession.getAttribute("PROJECT_NAME")%></b>
        <spring:message code="emptyModules.warning_2"/></font>
      </p>
      <p>
        <font color="#1490E3"><spring:message code="emptyModules.warning_3"/></font>
      </p>
    </div>
    <div class="content">

      <c:set var="UserInfo" value='<%=portletSession.getAttribute("UserInfo")%>' />
      <table border="0">
        <tr>
          <th><strong>User: </strong></th>
          <td><strong><font color="#1490E3">${UserInfo.username}</font></strong></td>
        </tr>
        <tr>
          <th><strong>Joined Projects: </strong></th>
          <td><strong><font color="#1490E3">${fn:length(projectList)}</font></strong></td>
        </tr>
      </table>
      <p></p>
      <c:if test="${not empty projectList}">
        <table id="projectTable" class="display dataTable" ellpadding="0" cellspacing="0" border="0" align="center">
          <thead>
            <tr>
              <th width="5%">No.</th>

              <th width="80%">Project Name</th>
              <th width="20%">Project Code</th>
            </tr>
          </thead>
          <tbody>
            <c:set var="count" value="0" />
            <c:forEach var="project" items="${projectList}">
              <c:set var="count" value="${count + 1}" />
              <portlet:actionURL var="renderAction">
                <portlet:param name="action" value="taskmanager" />
                <portlet:param name="projectId" value="${project.projectId}" />
                <portlet:param name="developerId" value="${developerId}" />
              </portlet:actionURL>
              <tr>
                <td>${count}</td>

                <td align="center" ><a href="${renderAction}">${project.name}</a></td>

                <td><a href="${renderAction}">${project.code}</a></td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </c:if>
      <c:if test="${empty projectList}">
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
