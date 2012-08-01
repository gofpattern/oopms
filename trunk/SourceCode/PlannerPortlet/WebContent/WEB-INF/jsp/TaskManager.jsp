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

						fnFeaturesInit();
						$('#taskTable').dataTable({
							"bFilter" : true,
							"bSort" : true,
							"bJQueryUI" : true,
							"sPaginationType" : "full_numbers"
						});
					});

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
	rules[15] = 'alphabetic|mask|alphabetic';
	rules[16] = 'startDate|mask|mydate';
	rules[17] = 'actualDate|mask|mydate';
	rules[18] = 'task.currenteffort:Current Effort|required';
	rules[19] = 'task.currenteffort:Current Effort|numeric';
	rules[20] = 'task.description:Description|required';
	rules[21] = 'task.taskname:Title|minlength|10';
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

      <portlet:actionURL var="changeProjectAction">
        <portlet:param name="action" value="changeProject" />
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
                <td class="ColumnLabel"><label for="add-form-stage">Stage*</label></td>
                <td><form:select class="styled" path="task.stageid" value="${edTask.stageid}" multiple="single"
                    id="add-form-stage">
                    <form:options items="${stageMapAdd}" />
                  </form:select><br /> <span id=errorsDiv_task.stageid></span></td>
              </tr>
              <tr>
                <td class="ColumnLabel"><label for="add-form-startDate">Start Date*</label></td>
                <td class="CellBGR3"><form:input path="startDate" value="${edTask.startdate_str}"
                    id="add-form-startDate"></form:input> (MM-DD-YYYY)<br /> <span id=errorsDiv_startDate></span></td>
                <td class="ColumnLabel"><label for="add-form-process">Process*&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                <td><form:select class="styled" path="task.process" value="${edTask.process}" multiple="single"
                    id="add-form-process">
                    <form:options items="${processMapAdd}" />
                  </form:select><br /> <span id=errorsDiv_task.process></span></td>
                <td class="ColumnLabel"><label for="add-form-assignedTo">Assigned To*</label></td>
                <td><form:select class="styled" path="task.assignedto" value="${edTask.assignedto}"
                    multiple="single" id="add-form-assignedTo">
                    <form:options items="${developerMapAdd}" />
                  </form:select><br /> <span id=errorsDiv_task.assignedto></span></td>
              </tr>
              <tr>
                <td class="ColumnLabel"><label for="add-form-finishDate">Finish Date*</label></td>
                <td class="CellBGR3"><form:input path="actualDate" value="${edTask.planDate_str}"
                    id="add-form-finishDate" /> (MM-DD-YYYY)<br /> <span id=errorsDiv_actualDate></span></td>
                <td class="ColumnLabel"><label for="add-form-product">Product*</label></td>
                <td><form:select class="styled" path="task.product" value="${edTask.product}" multiple="single"
                    id="add-form-process">
                    <form:options items="${productMapAdd}" />
                  </form:select><br /> <span id=errorsDiv_task.product></span></td>
                <td class="ColumnLabel"><label for="add-form-status">Status*</label></td>
                <td><form:select class="styled" path="task.statusid" value="${edTask.statusid}" multiple="single"
                    id="add-form-status">
                    <form:options items="${statusMapAdd}" />
                  </form:select><br /> <span id=errorsDiv_task.statusid></span></td>
              </tr>
              <tr>
                <td class="ColumnLabel"><label for="add-form-plannedEffort">Planned Effort*</label></td>
                <td class="CellBGR3"><form:input path="task.plannedeffort" value="${edTask.plannedeffort}"
                    id="add-form-plannedEffort" /> (Hours)<br /> <span id=errorsDiv_task.plannedeffort></span></td>
                <td class="ColumnLabel"><label for="add-form-productSize">Product Size*</label></td>
                <td class="CellBGR3"><form:input path="task.productsize" value="${edTask.productsize}"
                    id="add-form-productSize" /><br /> <span id=errorsDiv_task.productsize></span></td>
              </tr>
              <tr>
                <td class="ColumnLabel"><label for="add-form-currentEffort">Current Effort*</label></td>
                <td class="CellBGR3"><form:input path="task.currenteffort" value="${edTask.currenteffort}"
                    id="add-form-currentEffort" /> (Hours)</td>
                <td class="ColumnLabel"><label for="add-form-completedSize">Completed Size</label></td>
                <td class="CellBGR3"><form:input path="task.completedsize" value="${edTask.completedsize}"
                    id="add-form-completedSize" /></td>
              </tr>
              <tr>
                <td class="ColumnLabel"><label for="add-form-description">Description*&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
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
              <td><b>&nbsp;&nbsp;Stage&nbsp;&nbsp;</b></td>
              <td><b>&nbsp;&nbsp;Assigned&nbsp;To&nbsp;&nbsp;</b></td>
              <td><b>&nbsp;&nbsp;Status&nbsp;</b></td>
              <td><b>&nbsp;&nbsp;Sort&nbsp;by&nbsp;&nbsp;</b></td>
            </tr>
            <tr>
              <td><form:select path="projectId" multiple="single" onchange='submitAction("searchTask", "${changeProjectAction}")'>
                  <form:options items="${projectMap}" />
                </form:select></td>
              <td><form:select path="stageDefault" multiple="single" onchange='this.form.submit()'>
                  <form:options items="${stageMap}" />
                </form:select></td>
              <td><form:select path="developerDefault" multiple="single" onchange='this.form.submit()'>
                  <form:options items="${developerMap}" />
                </form:select></td>
              <td><form:select path="statusDefault" multiple="single" onchange='this.form.submit()'>
                  <form:options items="${statusMap}" />
                </form:select></td>
              <td width="10%"><select class="styled" onchange="showAlert()">
                  <option selected="selected" value="0">Status</option>
                  <option value="1">Remaining&nbsp;Time</option>
                  <option value="2">Completeness&nbsp;Rate</option>
              </select></td>
              <td width="56%"></td>
            </tr>
          </table>
        </form:form>
        <table class="portlet-table" id="taskTable">
          <thead>
            <tr>
              <!-- TABLE HEADER -->
              <th><b>No.</b></th>
              <th><b>Project Code</b></th>
              <th><b>Task Name</b></th>
              <th><b>Stage</b></th>
              <th><b>Process</b></th>
              <th><b>Assigned To</b></th>
              <th><b>Remaining Time</b></th>
              <th><b>Completeness Rate</b></th>
              <th><b>Start Date</b></th>
              <c:choose>
                <c:when test="${taskStatus == 2 }">
                  <th><b>Finish Date</b></th>
                </c:when>
                <c:when test="${taskStatus == 'All' }">
                  <th><b>Planned Finish Date</b></th>
                  <th><b>Finish Date</b></th>
                </c:when>
                <c:otherwise>
                  <th><b>Planned Finish Date</b></th>
                </c:otherwise>
              </c:choose>
              <th><b>Planned Effort</b></th>
              <th><b>Actual Effort</b></th>
              <th><b>Update</b></th>
              <th><b>Delete</b></th>
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
                        <c:choose>
                          <c:when test="${taskStatus == 2 }">
                            <td>${task.actualDate_str}</td>
                          </c:when>
                          <c:when test="${taskStatus == 'All' }">
                            <td>${task.planDate_str}</td>
                            <c:choose>
                              <c:when test="${not empty task.actualDate_str}">
                                <td>${task.actualDate_str}</td>
                              </c:when>
                              <c:otherwise>
                                <td>N/A</td>
                              </c:otherwise>
                            </c:choose>
                          </c:when>
                          <c:otherwise>
                            <td>${task.planDate_str}</td>
                          </c:otherwise>
                        </c:choose>
                        <td>${task.plannedeffort}&nbsp;Hour</td>
                        <c:choose>
                          <c:when test="${task.statusid =='2'}">
                            <td>${task.effort}&nbsp;Hour</td>
                          </c:when>
                          <c:otherwise>
                            <td>N/A</td>
                          </c:otherwise>
                        </c:choose>
                        <td><input type="image" alt="Submit"
                          src="/<spring:message code="app.context"/>/resource_files/icons/Actions-document-edit-icon.png"
                          width="24" height="24"
                          onclick='submitAction("${task.taskid}modTask", "${DoPlannerEditAction}")'></input></td>
                        <td><form:input path="taskId" value="${task.taskid}" type="hidden" /> <input type="image"
                          alt="Submit"
                          src="/<spring:message code="app.context"/>/resource_files/icons/Actions-delete-icon.png"
                          width="24" height="24" onclick='submitAction("${task.taskid}modTask", "${deleteTaskAction}")' />
                      </form:form>
                    </tr>
                  </c:when>
                </c:choose>
              </c:forEach>
            </tbody>
          </c:if>
        </table>

      </div>
      <div align="right">
        <input type="button" name="" value="Import" /> <input type="button" name="input" value="Report" />
      </div>

    </div>

    <div class="footer">
      <p>OOPMS Group</p>
      <!-- end .footer -->
    </div>
  </div>
</body>
</html>