<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet"%>
<%@ taglib prefix="portlet2" uri="http://java.sun.com/portlet_2_0"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="javax.portlet.PortletSession"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="header.jsp"></jsp:include>
<portlet2:defineObjects />
<portlet:defineObjects />

<script type="text/javascript">
	$(function() {
		$("#radio").buttonset();
		$("#format").buttonset();
		$("input:submit, a, button", ".demo").button();
		$("a", ".demo").click(function() {
			return false;
		});

		// run the currently selected effect
		function runEffect() {
			// get effect type from 
			var selectedEffect = $("#effectTypes").val();

			// most effect types need no options passed by default
			var options = {};
			// some effects have required parameters
			if (selectedEffect === "scale") {
				options = {
					percent : 100
				};
			} else if (selectedEffect === "size") {
				options = {
					to : {
						width : 280,
						height : 185
					}
				};
			}

			// run the effect
			$("#effect").show(selectedEffect, options, 500, callback);
		}
		;

		//callback function to bring a hidden box back
		function callback() {
			setTimeout(function() {
				$("#effect:visible").removeAttr("style").fadeOut();
			}, 1000);
		}
		;

		// set effect from select menu value
		$("#button").click(function() {
			runEffect();
			return false;
		});

		$("#effect").hide();
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
</script>
<script type="text/javascript">
	$(document).ready(function() {

		fnFeaturesInit();
		$('#mainTable2').dataTable({
			"bFilter" : true,
			"bSort" : true,
			"bJQueryUI" : true,
			"sPaginationType" : "full_numbers"
		});
	});
</SCRIPT>
</head>

<body>
  <div class="container">
    <div class="content">
      <c:set var="UserInfor" value='<%=portletSession.getAttribute("UserInfo")%>' />
      <table border="0">
        <!-- User infor -->        
        <tr>
          <th><strong>User: </strong></th>
          <td><strong><font color="#1490E3">${UserInfor.username}</font></strong></td>
        </tr>
        <tr>
          <th><strong>Joined Projects: </strong></th>
          <td><strong><font color="#1490E3">${fn:length(projectList)}</font></strong></td>
        </tr>
        <c:if test="${not empty projectList}">
        <tr>
          <th><strong>Requirements: </strong></th>
          <td><strong><font color="#1490E3"><a
                href='<portlet:renderURL><portlet:param name="action" value="requirementmanager"/></portlet:renderURL>'>Lists</a></font></strong></td>
        </tr>        
        <tr>
          <th><strong>Projects: </strong></th>
          <td><strong><font color="#1490E3"><a
                href='<portlet:renderURL><portlet:param name="action" value="requirementwelcome"/></portlet:renderURL>'>Lists</a></font></strong></td>
        </tr>
        </c:if>
      </table>

      <p></p>

      <c:if test="${not empty projectList}">
        <table id="mainTable2" class="display dataTable" ellpadding="0" cellspacing="0" border="0" align="center">
          <thead>
            <tr>
              <th width="5%">No.</th>
              <th width="20%">Project Code</th>
              <th width="75%">Project Name</th>
            </tr>
          </thead>
          <tbody>

            <c:forEach var="project" items="${projectList}" varStatus="count">
              <tr>
                <portlet:renderURL var="renderAction">
                  <portlet:param name="action" value="requirementmanager1project" />
                  <portlet:param name="projectId" value="${project.projectId}" />
                </portlet:renderURL>
                <td scope="row">${count.count}</td>
                <td align="center" scope="row"><a href="${renderAction}">${project.code}</a></td>
                <td align="center" scope="row"><a href="${renderAction}">${project.name}</a></td>
              </tr>
            </c:forEach>

          </tbody>
        </table>
      </c:if>
      <c:if test="${empty projectList}">
        You haven't joined any projects.
    </c:if>

    </div>
    <!-- end .content -->
  </div>

  <br>
  <div class="footer">
    <p>OOPMS Group</p>
    <!-- end .footer -->
  </div>
</body>
</html>
