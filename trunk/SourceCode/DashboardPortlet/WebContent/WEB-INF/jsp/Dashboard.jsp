<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet"%>
<%@ taglib prefix="portlet2" uri="http://java.sun.com/portlet_2_0"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="javax.portlet.PortletSession"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="header.jsp" />
<portlet2:defineObjects />
<portlet:defineObjects />
<script language="javascript">
	$(function() {
		$("#datepicker1")
				.datepicker(
						{
							showOn : "button",
							buttonImage : "/<spring:message code="app.context"/>/resource_files/images/calendar.gif",
							buttonImageOnly : true
						});
		$("#datepicker2")
				.datepicker(
						{
							showOn : "button",
							buttonImage : "/<spring:message code="app.context"/>/resource_files/images/calendar.gif",
							buttonImageOnly : true
						});
	});
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
	$(document).ready(function() {
		fnFeaturesInit();
		$('#projectTable').dataTable({
			"bFilter" : true,
			"bSort" : true,
			"bJQueryUI" : true,
			"sPaginationType" : "full_numbers"
		});

	});
</script>
</head>
<body id="portal" class="up fl-theme-mist">
  <div id="portalPageBodyInner" class="container">
    <div class="content">
      <portlet:actionURL var="searchAction">
        <portlet:param name="action" value="search" />
      </portlet:actionURL>
      <c:set var="UserInfor" value='<%=portletSession.getAttribute("UserInfo")%>' />
      <table border="0">
        <tr>
          <th><strong>User: </strong></th>
          <td><strong><font color="#1490E3">${UserInfor.username}</font></strong></td>
        </tr>
        <tr>
          <th><strong>Joined Projects: </strong></th>
          <td><strong><font color="#1490E3">${fn:length(dashboardList)}</font></strong></td>
        </tr>
      </table>
      <form:form name="searchProject" commandName="DashboardForm" method="post" action="${searchAction}">
        <table>
          <tbody>
            <tr>
              <td><b>Project Category</b></td>
              <td><b>Project Domain</b></td>
              <td><b>Project Status</b></td>
              <td><b>Project Health</b></td>
            </tr>
            <tr>
              <td><form:select path="projectCategory" class="styled_2" multiple="single" onchange='this.form.submit()'>
                  <form:options items="${categoryMap}" />
                </form:select></td>

              <td><form:select path="projectDomain" class="styled_2" multiple="single" onchange='this.form.submit()'>
                  <form:options items="${businessDomainMap}" />
                </form:select></td>

              <td><form:select path="projectStatus" class="styled_2" multiple="single" onchange='this.form.submit()'>
                  <form:options items="${statusMap}" />
                </form:select></td>

              <td><form:select path="projectHealth" class="styled_2" multiple="single" onchange='this.form.submit()'>
                  <form:options items="${projectHealthMap}" />
                </form:select></td>
            </tr>
          </tbody>
        </table>
      </form:form>
      <table id="projectTable" class="display dataTable" cellpadding="0" cellspacing="0" border="0" align="center">
        <thead>
          <tr>
            <!-- TABLE HEADER -->
            <th>Project Code</th>
            <th>Project Name</th>
            <th>Project Manager</th>
            <th>Project Health</th>
            <th>Time</th>
            <th>Progress</th>
            <th>Efficiency</th>
            <th>Cost</th>
            <th>Start Date</th>
            <th>Finish Date</th>
            <th>Planned Effort</th>
            <th>Actual Effort</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="dashboard" items="${dashboardList}">
          <c:if test="${dashboard.visible == true}">
            <portlet:actionURL var="renderAction">
              <portlet:param name="action" value="taskmanager" />
              <portlet:param name="projectId" value="${project.projectId}" />
            </portlet:actionURL>
            <tr>
              <td><a href="${renderAction}">${dashboard.project.code}</a></td>
              <td>${dashboard.project.name}</td>
              <td>${dashboard.project.leader}</td>
              <td align="center"><c:choose>
                  <c:when test="${dashboard.projectHealthStatus == 'bad'}">
                    <div class="circle red glow stripes">
                      <span style="width: 100%"></span>
                    </div>
                  </c:when>
                  <c:otherwise>
                    <div class="circle ${dashboard.projectHealthStatus} stripes">
                      <span style="width: 100%"></span>
                    </div>
                  </c:otherwise>
                </c:choose></td>
              <td><div id="percentTime" class="progress_bar green stripes">
                  <span style="width:${dashboard.percentTime}%" align="center"><b><font color="#ffffff"
                      size="2" face="tahoma">${dashboard.percentTime}%</font></b></span>
                </div></td>
              <td><c:choose>
                  <c:when test="${dashboard.percentProgress < 50}">
                    <div id="percentProgress" class="progress_bar glow red stripes">
                      <span style="width: ${dashboard.percentProgress}%" align="center"><b><font
                          color="#ffffff" size="2" face="tahoma">${dashboard.percentProgress}%</font></b></span>
                    </div>
                  </c:when>
                  <c:when test="${dashboard.percentProgress < 80}">
                    <div id="percentProgress" class="progress_bar orange stripes">
                      <span style="width: ${dashboard.percentProgress}%" align="center"><b><font
                          color="#ffffff" size="2" face="tahoma">${dashboard.percentProgress}%</font></b></span>
                    </div>
                  </c:when>
                  <c:otherwise>
                    <div id="percentProgress" class="progress_bar green stripes">
                      <span style="width: ${dashboard.percentProgress}%" align="center"><b><font
                          color="#ffffff" size="2" face="tahoma">${dashboard.percentProgress}%</font></b></span>
                    </div>
                  </c:otherwise>
                </c:choose></td>
              <td align="center"><c:choose>
                  <c:when test="${dashboard.efficiencyStatus == 'bad'}">
                    <div class="circle red glow stripes">
                      <span style="width: 100%"></span>
                    </div>
                  </c:when>
                  <c:otherwise>
                    <div class="circle ${dashboard.efficiencyStatus} stripes">
                      <span style="width: 100%"></span>
                    </div>
                  </c:otherwise>
                </c:choose></td>
              <td align="center"><c:choose>
                  <c:when test="${dashboard.costStatus == 'bad'}">
                    <div class="circle red glow stripes">
                      <span style="width: 100%"></span>
                    </div>
                  </c:when>
                  <c:otherwise>
                    <div class="circle ${dashboard.costStatus} stripes">
                      <span style="width: 100%"></span>
                    </div>
                  </c:otherwise>
                </c:choose></td>
              <td>${dashboard.project.startDate}</td>
              <td>${dashboard.project.planFinishDate}</td>
              <td>${dashboard.project.planEffort}</td>
              <td>${dashboard.project.actualEffort}</td>
            </tr>
            </c:if>
          </c:forEach>
        </tbody>
      </table>


      <div class="footer">
        <p>OOPMS Group</p>
        <!-- end .footer -->
      </div>

      <!-- end .content-->
    </div>


    <!-- end .container -->
  </div>
</body>
</html>