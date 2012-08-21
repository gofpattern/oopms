<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet"%>
<%@ taglib prefix="portlet2" uri="http://java.sun.com/portlet_2_0"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="javax.portlet.PortletSession"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="header.jsp" />
<portlet2:defineObjects />
<portlet:defineObjects />
<script type="text/javascript">  

	$(function() {
		$("#add-form-startDate")
				.datepicker(
						{
							showOn : "button",
							buttonImage : "/<spring:message code="app.context"/>/resource_files/images/calendar.gif",
							buttonImageOnly : true
						});
		$("#add-form-finishDate")
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

	$(document)
			.ready(
					function() {
					    
						$(function() {
					        $( "#dialog" ).dialog({
					            autoOpen: false,
					            resizable: false,
					            height: 210,
					            width: 450,
					            modal: true,
					            buttons: {
					                Ok: function() {
					                    $( this ).dialog( "close" );
					                }
					            }
					        });
					    });
						// Check module of project
				          $(function() {
				              if('<%=portletSession.getAttribute("CHANGEPROJECT_ERROR")%>' == 'true') {
				            	  $( "#dialog" ).dialog( "open" );
							}

						});

						// set description when update a task
						document.getElementById('add-form-description').innerHTML = "${edTask.description}";						

						// set show and hide for hidden-add-form
						if ('${flag}' == 0) {
							$(".hidden-add-form").hide();
						}

						$("#add-button").click(function() {
							$(".hidden-add-form").show('slow');
						});
						$("#cancel-button").click(function() {
							$(".hidden-add-form").hide('slow');
						});

						fnFeaturesInit();
						$('#taskTable').dataTable({
							"bFilter" : true,
							"bSort" : true,
							"bJQueryUI" : true,
							"sPaginationType" : "full_numbers"
						});
						// apply validation
						yav.init('addForm', rules);
					});

            	var rules = new Array();
            	rules[0] = 'task.taskname:Title|required';
            	rules[1] = 'startDate:Start Date|required';
            	rules[2] = 'startDate:Start Date|date';
            	rules[3] = 'actualDate:Finish Date|required';
            	rules[4] = 'actualDate:Finish Date|date';
            	rules[5] = 'startDate:Start Date|date_le|$actualDate:Finish Date';
            	rules[6] = 'task.plannedeffort:Planned Effort|required';
            	rules[7] = 'task.plannedeffort:Planned Effort|numeric';
            	rules[8] = 'task.stageid:Stage|required';
            	rules[9] = 'task.process:Process|required';
            	rules[10] = 'task.product:Product|required';
            	rules[11] = 'task.productsize:Product Size|required';
            	rules[12] = 'task.productsize:Product Size|numeric';
            	rules[13] = 'task.assignedto: Assigned To|required';
            	rules[14] = 'task.statusid:Status|required';
            	/* 	rules[15] = 'startDate|mask|mydate';
            	 rules[16] = 'actualDate|mask|mydate'; */
            	rules[15] = 'task.currenteffort:Current Effort|required';
            	rules[16] = 'task.currenteffort:Current Effort|numeric';
            	rules[17] = 'task.description:Description|required';
            	rules[18] = 'task.completedsize:Completed Size|numeric';
            	rules[19] = 'task.sizeunit:Product Size Unit|required';
            	rules[20] = 'task.sizeunit:Product Size Unit|numeric';
            
            	rules[21] = 'task.productsize:Product Size|numrange|1-1000000';
            
            	yav.addHelp('task.taskname', 'Provide your Title');
            	yav.addHelp('startDate', 'Provide your Start Date');
            	yav.addHelp('actualDate', 'Provide your Finish Date');
            	yav.addHelp('task.plannedeffort', 'Provide your Planned Effort');
            	yav.addHelp('task.stageid', 'Provide your Stage');
            	yav.addHelp('task.process', 'Provide your Process');
            	yav.addHelp('task.product', 'Provide your Product');
            	yav.addHelp('task.productsize', 'Provide your Product Size');
            	yav.addHelp('task.assignedto', 'Provide your assigned member');
            	yav.addHelp('task.statusid', 'Provide your Task Status');
            	yav.addHelp('task.sizeunit', 'Provide your product size unit');
            	yav.addHelp('task.completedsize', 'Provide completed size');
            	yav.addHelp('task.description', 'Provide your Task Description');
            	yav.addMask('mydate', '  /  /    ', '1234567890');
</script>
<style type="text/css">
    td .173{ 
        background-color: green;
    }
    td .174{
        background-color: blue;
    }
    td .175{
        background-color: gray;
    }
    td .176{
        background-color: yellow;
    }
</style>
</head>
<body id="portal" class="up fl-theme-mist">
  <div class="container" id="portalPageBodyInner">
   <div id="dialog" title="Missing Products">
         <p>
        <font color="#1490E3"><spring:message code="emptyModules.warning_1"/><b><%=portletSession.getAttribute("PROJECT_NAME")%></b>
        <spring:message code="emptyModules.warning_2"/></font>
      </p>
      <p>
        <font color="#1490E3"><spring:message code="emptyModules.warning_3"/></font>
      </p>
</div>
<!-- <div id="dialog-confirm" title="Delete Task">
    <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>The item will be permanently deleted and cannot be recovered. Are you sure?</p>
</div> -->
    <div class="content">
      <portlet:actionURL var="DoPlannerAddAction">
        <portlet:param name="action" value="plannerAdd" />
      </portlet:actionURL>

      <portlet:actionURL var="DoPlannerEditAction">
        <portlet:param name="action" value="plannerEdit" />
      </portlet:actionURL>

      <portlet:actionURL var="PlannerAddAction">
        <portlet:param name="action" value="${plAddAction}" />
      </portlet:actionURL>

      <portlet:actionURL var="searchAction">
        <portlet:param name="action" value="search" />
      </portlet:actionURL>

      <portlet:actionURL var="changeProjectAction">
        <portlet:param name="action" value="changeProject" />
      </portlet:actionURL>

      <portlet:actionURL var="deleteTaskAction">
        <portlet:param name="action" value="deleteTask" />
      </portlet:actionURL>
      <c:set var="UserInfo" value='<%=portletSession.getAttribute("UserInfo")%>' />
      <table border="0">
        <tr>
          <td><strong>User</strong></td>
          <td><strong><font color="#1490E3">${UserInfo.username}</font></strong></td>
        </tr>
        <tr>
          <td><strong>Role</strong></td>
          <td><strong><font color="#1490E3">${role}</font></strong></td>
        </tr>
      </table>
      <c:if test="${role == 'Project Manager' }">
        <form:form commandName="PlannerAddForm" method="post" action="${DoPlannerAddAction}">
          <input id="add-button" type="submit" name="ok" value=" Add " />
        </form:form>
      </c:if>

      <%-- <a id="add-button" href='<portlet:actionURL><portlet:param name="action" value="plannerAdd"/></portlet:actionURL>'>AddTask</a> --%>

      <div class="hidden-add-form">
        <DIV id=errorsDiv></DIV>
        <form:form name="addForm" commandName="PlannerAddForm" method="post" action="${PlannerAddAction}"
          onsubmit="return yav.performCheck('addForm', rules, 'inline');">
          <p id="add-form">
          <table class="Table" cellspacing="1" width="560">
            <caption class="TableCaption">&nbsp;</caption>
            <caption class="TableCaption">
              <h1>Add new Task</h1>
            </caption>
            <tbody>
              <tr>
                <td width="10%" class="ColumnLabel"><label for="add-form-title">Title*</label></td>
                <td width="30%" class="CellBGR3"><form:input path="task.taskname" id="add-form-title"
                    value="${edTask.taskname}" /> <form:input path="task.taskid" value="${edTask.taskid}" type="hidden" /><br />
                  <span id=errorsDiv_task.taskname></span>&nbsp;</td>
                <td width="10%"></td>
                <td width="20%"></td>
                <td width="10%"></td>
                <td width="20%"></td>
              </tr>
              <tr>
                <td class="ColumnLabel"><label for="add-form-startDate">Start Date*</label></td>
                <td class="CellBGR3"><form:input path="startDate" value="${edTask.startdate_str}"
                    id="add-form-startDate"></form:input> (MM-DD-YYYY)<br /> <span id=errorsDiv_startDate></span>&nbsp;</td>


                <td class="ColumnLabel"><label for="add-form-stage">Stage*</label></td>
                <td><form:select class="styled" path="task.stageid" value="${edTask.stageid}" multiple="single"
                    id="add-form-stage">
                    <form:options items="${stageMapAdd}" />
                  </form:select><br /> <span id=errorsDiv_task.stageid></span>&nbsp;</td>

                <td class="ColumnLabel"><label for="add-form-module">Product*</label></td>
                <td><form:select class="styled" path="task.module.moduleId" multiple="single" id="add-form-module"
                    value="${edTask.module.moduleId}">
                    <form:options items="${moduleMapAdd}" />
                  </form:select><br /> <span id=errorsDiv_task.module.moduleId></span>&nbsp;</td>
              </tr>
              <tr>

                <td class="ColumnLabel"><label for="add-form-finishDate">Finish Date*</label></td>
                <td class="CellBGR3"><form:input path="actualDate" value="${edTask.planDate_str}"
                    id="add-form-finishDate" /> (MM-DD-YYYY)<br /> <span id=errorsDiv_actualDate></span>&nbsp;</td>
                <td class="ColumnLabel"><label for="add-form-process">Process*&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                <td><form:select class="styled" path="task.process" value="${edTask.process}" multiple="single"
                    id="add-form-process">
                    <form:options items="${processMapAdd}" />
                  </form:select><br /> <span id=errorsDiv_task.process></span>&nbsp;</td>
                <td class="ColumnLabel"><label for="add-form-product">Product Size Unit*</label></td>
                <td><form:select class="styled" path="task.sizeunit" multiple="single" id="add-form-process"
                    value="${task.sizeunit}">
                    <form:options items="${sizeUnitMapAdd}" />
                  </form:select><br /> <span id=errorsDiv_task.sizeunit></span>&nbsp;</td>
              </tr>
              <tr>
                <td class="ColumnLabel"><label for="add-form-plannedEffort">Planned Effort*</label></td>
                <td class="CellBGR3"><form:input path="task.plannedeffort" value="${edTask.plannedeffort}"
                    id="add-form-plannedEffort" /> (Hours)<br /> <span id=errorsDiv_task.plannedeffort></span>&nbsp;</td>
                <c:choose>
                  <c:when test="${role == 'Project Manager'}">
                    <td class="ColumnLabel"><label for="add-form-assignedTo">Assigned To*</label></td>
                    <td><form:select class="styled" path="task.assignedto" value="${edTask.assignedto}"
                        multiple="single" id="add-form-assignedTo">
                        <form:options items="${developerMapAdd}" />
                      </form:select><br /> <span id=errorsDiv_task.assignedto></span>&nbsp;</td>
                  </c:when>
                  <c:otherwise>
                    <td></td>
                    <td></td>
                  </c:otherwise>
                </c:choose>
                <td class="ColumnLabel"><label for="add-form-productSize">Product Size*</label></td>
                <td class="CellBGR3"><form:input path="task.productsize" value="${edTask.productsize}"
                    id="add-form-productSize" /><br /> <span id=errorsDiv_task.productsize></span>&nbsp;</td>
              </tr>
              <tr>
                <td class="ColumnLabel"><label for="add-form-currentEffort">Current Effort*</label></td>
                <td class="CellBGR3"><form:input path="task.currenteffort" value="${edTask.currenteffort}"
                    id="add-form-currentEffort" /> (Hours)<br /> <span id=errorsDiv_task.currenteffort></span>&nbsp;</td>
                <td class="ColumnLabel"><label for="add-form-status">Status*</label></td>
                <td><form:select class="styled" path="task.statusid" value="${edTask.statusid}" multiple="single"
                    id="add-form-status">
                    <form:options items="${statusMapAdd}" />
                  </form:select><br /> <span id=errorsDiv_task.statusid></span>&nbsp;</td>
                <td class="ColumnLabel"><label for="add-form-completedSize">Completed Size</label></td>
                <td class="CellBGR3"><form:input path="task.completedsize" value="${edTask.completedsize}"
                    id="add-form-completedSize" /><span id=errorsDiv_task.completedsize></span>&nbsp;</td>
              </tr>
              <tr>
                <td class="ColumnLabel"><label for="add-form-description">Description*&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                <td class="CellBGR3"><form:textarea path="task.description" rows="4" cols="40"
                    id="add-form-description"></form:textarea><br /> <span id=errorsDiv_task.description></span>&nbsp;</td>
              </tr>
              <tr>

              </tr>
            </tbody>
          </table>
          </p>
          <p>
            <input type="submit" class="BUTTON" value="OK"> <input id="cancel-button" type="button"
              value=" Cancel " />
          </p>
        </form:form>
      </div>

      <div id="content_planner">
        <form:form name="searchTask" commandName="PlannerForm" method="post" action="${searchAction}">
          <table>
            <tr>
              <td><b>&nbsp;&nbsp;Project&nbsp;&nbsp;</b></td>
              <td><b>&nbsp;&nbsp;Stage&nbsp;&nbsp;</b></td>
              <td><b>&nbsp;&nbsp;Assigned&nbsp;To&nbsp;&nbsp;</b></td>
              <td><b>&nbsp;&nbsp;Status&nbsp;</b></td>
            </tr>
            <tr>
              <td><form:select path="projectId" class="styled_2" multiple="single"
                  onchange='submitAction("searchTask", "${changeProjectAction}")'>
                  <form:options items="${projectMap}" />
                </form:select></td>
              <td><form:select path="stageDefault" class="styled_2" multiple="single" onchange='this.form.submit()'>
                  <form:options items="${stageMap}" />
                </form:select></td>
              <td><form:select path="developerDefault" class="styled_2" multiple="single"
                  onchange='this.form.submit()'>
                  <form:options items="${developerMap}" />
                </form:select></td>
              <td><form:select path="statusDefault" class="styled_2" multiple="single"
                  onchange='this.form.submit()'>
                  <form:options items="${statusMap}" />
                </form:select></td>
              <td width="56%"></td>
            </tr>
          </table>
        </form:form>
        <table id="taskTable" class="display dataTable" cellpadding="0" cellspacing="0" border="0">
          <thead>
            <tr>
              <!-- TABLE HEADER -->
              <th><b>No.</b></th>
              <th><b>Project Code</b></th>
              <th><b>Task Name</b></th>
              <th><b>Stage</b></th>
              <th><b>Process</b></th>
              <th><b>Assigned To</b></th>
              <th><b>Remaining Effort</b></th>
              <th><b>Completeness Rate</b></th>
              <th><b>Start Date</b></th>
              <th><b>Finish Date</b></th>
              <c:if test="${taskStatus == 174 ||taskStatus == 'All'}">
                <th><b>Actual Effort</b></th>
              </c:if>
              <th><b>Update</b></th>
              <c:if test="${role == 'Project Manager' }">
                <th><b>Delete</b></th>
              </c:if>
            </tr>
          </thead>
          <c:if test="${not empty taskList}">
            <tbody>
              <c:set var="count" value="0" />
              <c:forEach items="${taskList}" var="task">
                <c:choose>
                  <c:when test="${task.visible == true }">
                    <tr>
                      <form:form name="${task.taskid}modTask" commandName="PlannerForm" method="post" action="#">
                        <c:set var="count" value="${count + 1}" />
                        <fmt:parseNumber var="i" type="number" value="${task.completedsize}" />
                        <fmt:parseNumber var="j" type="number" value="${task.productsize}" />
                        <fmt:formatNumber var="completeRate" value="${(i/j)}" minFractionDigits="2" type="percent" />
                        <form:input path="taskId" value="${task.taskid}" type="hidden" />
                        <td class = "${task.statusid}">${count}</td>
                        <td>${task.project_str}</td>
                        <td>${task.taskname}</td>
                        <td>${task.stage_str}</td>
                        <td>${task.process_str}</td>
                        <td>${task.developer_str}</td>
                        <td>${task.plannedeffort - task.currenteffort}h</td>
                        <c:choose>
                          <c:when test="${not empty completeRate}">
                            <td>${completeRate}</td>
                          </c:when>
                          <c:otherwise>
                            <td>N/A</td>
                          </c:otherwise>
                        </c:choose>
                        <td>${task.startdate_str}</td>
                        <c:choose>
                          <c:when test="${task.statusid == 174 }">
                            <td>${task.actualDate_str}</td>
                          </c:when>
                          <c:otherwise>
                            <td>${task.planDate_str}</td>
                          </c:otherwise>
                        </c:choose>
                        <c:if test="${taskStatus =='174' ||taskStatus =='All'}">
                          <c:choose>
                            <c:when test="${not empty task.effort}">
                              <td>${task.effort}h</td>
                            </c:when>
                            <c:otherwise>
                              <td></td>
                            </c:otherwise>
                          </c:choose>
                        </c:if>
                        <td><input type="image" alt="Submit"
                          src="/<spring:message code="app.context"/>/resource_files/icons/Actions-document-edit-icon.png"
                          width="24" height="24"
                          onclick='submitAction("${task.taskid}modTask", "${DoPlannerEditAction}")'></input></td>
                        <c:if test="${role == 'Project Manager' }">
                          <td><input type="image" alt="Submit"
                            src="/<spring:message code="app.context"/>/resource_files/icons/Actions-delete-icon.png"
                            width="24" height="24"
                            onclick='submitAction("${task.taskid}modTask", "${deleteTaskAction}")' /></td>
                        </c:if>
                      </form:form>
                    </tr>
                  </c:when>
                </c:choose>
              </c:forEach>
            </tbody>
          </c:if>
        </table>
        </p>
        <p>
      </div>
      <!--       <div align="right">
        <input type="button" name="" value="Import" /> <input type="button" name="input" value="Report" />
      </div> -->

    </div>

    <div class="footer">
      <p>OOPMS Group</p>
      <!-- end .footer -->
    </div>
  </div>
</body>
</html>

