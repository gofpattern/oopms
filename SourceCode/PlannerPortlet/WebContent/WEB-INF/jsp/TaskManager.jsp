<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css"
  href="/<spring:message code="app.context"/>/resource_files/css/ui-lightness/jquery-ui-1.8.21.custom.css"
  rel="Stylesheet" />
<link type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/common.css" rel="Stylesheet" />
<link type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/uportal.css" rel="Stylesheet" />
<link type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/yav/yav-style.css" rel="Stylesheet" />
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
  src="/<spring:message code="app.context"/>/resource_files/js/jquery-ui-1.8.21.custom.min.js"></script>
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/form-elements.js"></script>
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/common.js"></script>
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/yav.js"></script>
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/yav-config.js"></script>
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
		$("#selectable").selectable();
	});
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
</script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {

						// set description when update a task
						document.getElementById('add-form-description').innerHTML = "${edTask.description}";

						yav.init('addForm', rules);

						// set show and hide for hidden-add-form
						if ('${flag}' == 0) {
							$(".hidden-add-form").hide();
						}

						$("#add-button").click(function() {
							$(".hidden-add-form").show("slow");
						});
						$("#cancel-button").click(function() {
							$(".hidden-add-form").hide("slow");
						});
					});
</script>
<SCRIPT type="text/javascript">
	var rules = new Array();
	rules[0] = 'task.taskname:Title|required';
	rules[1] = 'startDate:Start Date|required';
	rules[2] = 'startDate:Start Date|date';
	rules[3] = 'actualDate:Finish Date|required';
	rules[4] = 'actualDate:Finish Date|date';
	rules[5] = 'startDate|date_le|$actualDate';
	rules[6] = 'task.plannedeffort:Planned Effort|required';
	rules[7] = 'task.plannedeffort:Planned Effort|numeric';
	rules[8] = 'task.stageid:Stage|required';
	rules[9] = 'task.process:Process|required';
	rules[10] = 'task.product:Product|required';
	rules[11] = 'task.productsize:Product Size|required';
	rules[12] = 'task.productsize:Product Size|numeric';
	rules[13] = 'task.assignedto: Assigned To|required';
	rules[14] = 'task.statusid:Status|required';
    rules[15]='alphabetic|mask|alphabetic';
    rules[16]='startDate|mask|mydate';
    rules[17]='actualDate|mask|mydate';
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
	yav.addMask('mydate', '  /  /    ', '1234567890');
</SCRIPT>
<style type="text/css">
#portal #portalPageBodyInner .content #content_planner .portlet-table {
  font-size: 12px;
}

#portal #portalPageBodyInner .content #content_planner table {
  font-size: 12px;
}

.hidden-add-form {
  webkit-box-shadow: rgb(170, 170, 170) 0px 0px 5px 0px;
  background-attachment: scroll;
  background-color: #EFEFEF;
  background-origin: padding-box;
  border-bottom-left-radius: 5px;
  border-bottom-right-radius: 5px;
  border-top-left-radius: 5px;
  border-top-right-radius: 5px;
  box-shadow: rgb(170, 170, 170) 0px 0px 5px 0px;
  color: #505050;
  font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
  font-size: 12px;
  height: auto;
  margin-bottom: 20px;
  margin-left: 50px;
  margin-right: 0px;
  margin-top: 0px;
  padding-bottom: 25px;
  padding-left: 25px;
  padding-right: 25px;
  padding-top: 25px;
  width: 900px;
}
</style>
</head>
<body id="portal" class="up fl-theme-mist"">
  <div class="container" id="portalPageBodyInner">
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

      <portlet:actionURL var="searchByStatusAction">
        <portlet:param name="action" value="searchByStatus" />
      </portlet:actionURL>

      <portlet:actionURL var="deleteTaskAction">
        <portlet:param name="action" value="deleteTask" />
      </portlet:actionURL>

      <form:form commandName="PlannerAddForm" method="post" action="${DoPlannerAddAction}">
        <input id="add-button" type="submit" name="ok" value=" Add " />
      </form:form>

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
                <td width="139" class="ColumnLabel"><label for="add-form-title">Title*</label></td>
                <td width="412" class="CellBGR3"><form:input path="task.taskname" id="add-form-title"
                    value="${edTask.taskname}" /> <form:input path="task.taskid" value="${edTask.taskid}" type="hidden" /><br />
                  <span id=errorsDiv_task.taskname></span></td>
              </tr>
              <tr>
                <td class="ColumnLabel"><label for="add-form-startDate">Start Date*</label></td>
                <td class="CellBGR3"><form:input path="startDate" value="${edTask.startdate_str}"
                    id="add-form-startDate"></form:input> (MM-DD-YYYY)<br />
                <span id=errorsDiv_startDate></span></td>
                <td class="ColumnLabel"><label for="add-form-stage">Stage*</label></td>
                <td><form:select class="styled" path="task.stageid" value="${edTask.stageid}" multiple="single"
                    id="add-form-stage">
                    <form:options items="${stageMapAdd}" />
                  </form:select><br />
                <span id=errorsDiv_task.stageid></span></td>
              </tr>
              <tr>
                <td class="ColumnLabel"><label for="add-form-finishDate">Finish Date*</label></td>
                <td class="CellBGR3"><form:input path="actualDate" value="${edTask.planDate_str}"
                    id="add-form-finishDate" /> (MM-DD-YYYY)<br />
                <span id=errorsDiv_actualDate></span></td>
                <td class="ColumnLabel"><label for="add-form-process">Process*&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                <td><form:select class="styled" path="task.process" value="${edTask.process}" multiple="single"
                    id="add-form-process">
                    <form:options items="${processMapAdd}" />
                  </form:select><br />
                <span id=errorsDiv_task.process></span></td>
                <td class="ColumnLabel"><label for="add-form-assignedTo">Assigned To*</label></td>
                <td><form:select class="styled" path="task.assignedto" value="${edTask.assignedto}"
                    multiple="single" id="add-form-assignedTo">
                    <form:options items="${developerMapAdd}" />
                  </form:select><br />
                <span id=errorsDiv_task.assignedto></span></td>
              </tr>
              <tr>
                <td class="ColumnLabel"><label for="add-form-plannedEffort">Planned Effort*</label></td>
                <td class="CellBGR3"><form:input path="task.plannedeffort" value="${edTask.plannedeffort}"
                    id="add-form-plannedEffort" /> (Hours)<br />
                <span id=errorsDiv_task.plannedeffort></span></td>
                <td class="ColumnLabel"><label for="add-form-product">Product*</label></td>
                <td><form:select class="styled" path="task.product" value="${edTask.product}" multiple="single"
                    id="add-form-process">
                    <form:options items="${productMapAdd}" />
                  </form:select><br />
                <span id=errorsDiv_task.product></span></td>

                <td class="ColumnLabel"><label for="add-form-status">Status*</label></td>
                <td><form:select class="styled" path="task.statusid" value="${edTask.statusid}" multiple="single"
                    id="add-form-status">
                    <form:options items="${statusMapAdd}" />
                  </form:select><br />
                <span id=errorsDiv_task.statusid></span></td>
              </tr>
              <tr>
                <td class="ColumnLabel"><label for="add-form-currentEffort">Current Effort</label></td>
                <td class="CellBGR3"><form:input path="task.currenteffort" value="${edTask.currenteffort}"
                    id="add-form-currentEffort" /> (Hours)</td>
                <td class="ColumnLabel"><label for="add-form-productSize">Product Size*</label></td>
                <td class="CellBGR3"><form:input path="task.productsize" value="${edTask.productsize}"
                    id="add-form-productSize" /><br />
                <span id=errorsDiv_task.productsize></span></td>
              </tr>
              <tr>
                <td class="ColumnLabel"><label for="add-form-effort">Actual Effort</label></td>
                <td class="CellBGR3"><form:input path="task.effort" id="add-form-effort"
                    value="${edTask.effort}" /> (Hours)</td>
                <td class="ColumnLabel"><label for="add-form-completedSize">Completed Size</label></td>
                <td class="CellBGR3"><form:input path="task.completedsize"
                    value="${edTask.completedsize}" id="add-form-completedSize" /></td>
              </tr>
              <tr>
                <td class="ColumnLabel"><label for="add-form-description">Description&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                <td class="CellBGR3"><form:textarea path="task.description" rows="4" cols="40"
                    id="add-form-description"></form:textarea></td>
              </tr>
            </tbody>
          </table>
          </p>
          <p>
            <input type="submit" name="ok" class="BUTTON"> <input id="cancel-button" type="button"
              name="cancel-button" value=" Cancel " />
          </p>
        </form:form>
      </div>

      <div id="content_planner">
        <form:form name="searchTask" commandName="PlannerForm" method="post" action="${searchAction}">
          <table>
            <tr>
              <td><b>&nbsp;&nbsp;Project&nbsp;&nbsp;</b></td>
              <td><b>&nbsp;&nbsp;Status&nbsp;&nbsp;</b></td>
              <td><b>&nbsp;&nbsp;Stage&nbsp;&nbsp;</b></td>
              <td><b>&nbsp;&nbsp;Assigned To&nbsp;</b></td>
              <td><b>&nbsp;&nbsp;Sort&nbsp;by&nbsp;&nbsp;</b></td>
            </tr>
            <tr>
              <td><form:select class="styled" path="projectId" multiple="single">
                  <form:options items="${projectMap}" />
                </form:select></td>
              <td><form:select class="styled" path="statusDefault" multiple="single" onchange="showAlert()">
                  <form:options items="${statusMap}" />
                </form:select></td>
              <td><form:select class="styled" path="stageDefault" multiple="single">
                  <form:options items="${stageMap}" />
                </form:select></td>
              <td><form:select class="styled" path="developerDefault" multiple="single">
                  <form:options items="${developerMap}" />
                </form:select></td>
              <td width="10%"><select class="styled" onchange="showAlert()">
                  <option selected="selected" value="0">Status</option>
                  <option value="1">Task Name</option>
                  <option value="2">Remaining Time</option>
                  <option value="2">Stage</option>
                  <option value="2">Product</option>
              </select></td>
              <td width="56%"><input type="button" name="input2" value="Search" /></td>
            </tr>
          </table>
        </form:form>
        <c:set var="list" value="${taskList}" />
        <c:if test="${not empty list}">
          <table class="portlet-table">
            <thead>
              <tr>
                <!-- TABLE HEADER -->
                <th><b><font><span>No.</span></font></b>
                <th><b><font><span>Task Code</span></font></b>
                <th><b><font><span>Task Name</span></font></b>
                <th><b><font><span>Stage</span></font></b>
                <th><b><font><span>Process</span></font></b>
                <th><b><font><span>Assigned To</span></font></b>
                <th><b><font><span>Remaining Time</span></font></b>
                <th><b><font><span>Completeness Rate</span></font></b>
                <th><b><font><span>Start Date</span></font></b>
                <th><b><font><span>Finish Date</span></font></b>
                <th><b><font><span>Planned Effort</span></font></b>
                <th><b><font><span>Actual Effort</span></font></b>
                <th><b><font><span>Update</span></font></b>
                <th><b><font><span>Delete</span></font></b>
              </tr>
            </thead>
            <tbody>
              <c:set var="count" value="0" />
              <c:forEach items="${taskList}" var="task">
                <tr>
                  <form:form name="${task.taskid}modTask" commandName="PlannerForm" method="post" action="#">
                    <c:set var="count" value="${count + 1}" />
                    <fmt:parseNumber var="i" type="number" value="${task.completedsize}" />
                    <fmt:parseNumber var="j" type="number" value="${task.productsize}" />
                    <fmt:formatNumber var="completeRate" value="${(i/j)}" minFractionDigits="2" type="percent" />
                    <td>${count}</td>
                    <td>${task.project_str}</td>
                    <td>${task.taskname}</td>
                    <td>${task.stage_str}</td>
                    <td>${task.process_str}</td>
                    <td>${task.developer_str}</td>
                    <td>${task.plannedeffort - task.currenteffort}&nbsp;Hour</td>
                    <c:choose>
                      <c:when test="${not empty completeRate}">
                        <td>${completeRate}</td>
                      </c:when>
                      <c:otherwise>
                        <td>N/A</td>
                      </c:otherwise>
                    </c:choose>
                    <td>${task.startdate_str}</td>
                    <td>${task.planDate_str}</td>
                    <td>${task.plannedeffort}&nbsp;Hour</td>
                    <c:choose>
                      <c:when test="${task.statusid =='2'}">
                        <td>${task.effort}</td>
                      </c:when>
                      <c:otherwise>
                        <td>N/A</td>
                      </c:otherwise>
                    </c:choose>
                    <td><input type="image" alt="Submit"
                      src="/<spring:message code="app.context"/>/resource_files/icons/Actions-document-edit-icon.png"
                      width="24" height="24" onclick='submitAction("${task.taskid}modTask", "${DoPlannerEditAction}")'></input></td>
                    <td><form:input path="taskId" value="${task.taskid}" type="hidden" /> <input type="image"
                      alt="Submit"
                      src="/<spring:message code="app.context"/>/resource_files/icons/Actions-delete-icon.png"
                      width="24" height="24" onclick='submitAction("${task.taskid}modTask", "${deleteTaskAction}")' />
                  </form:form>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </c:if>
      </div>
      <div id="button">
        <input type="button" name="" value="Add New Task" /> <input type="button" name="" value="Import" /> <input
          type="button" name="input" value="Report" />
      </div>

    </div>

    <div class="footer">
      <p>OOPMS Group</p>
      <!-- end .footer -->
    </div>
  </div>
</body>
</html>