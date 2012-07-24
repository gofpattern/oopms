<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" href="../PlannerModule/resource_files/css/ui-lightness/jquery-ui-1.8.21.custom.css"
  rel="Stylesheet" />
<link type="text/css" href="../PlannerModule/resource_files/css/common.css" rel="Stylesheet" />
<link type="text/css" href="../PlannerModule/resource_files/css/uportal.css" rel="Stylesheet" />
<script type="text/javascript" src="../PlannerModule/resource_files/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../PlannerModule/resource_files/js/jquery-ui-1.8.21.custom.min.js"></script>
<script type="text/javascript" src="../PlannerModule/resource_files/js/form-elements.js"></script>
<script type="text/javascript" src="../PlannerModule/resource_files/js/common.js"></script>
<script type="text/javascript">
	$(function() {
		$("#add-form-startDate")
				.datepicker(
						{
							showOn : "button",
							buttonImage : "../PlannerModule/resource_files/images/calendar.gif",
							buttonImageOnly : true
						});
		$("#add-form-finishDate")
				.datepicker(
						{
							showOn : "button",
							buttonImage : "../PlannerModule/resource_files/images/calendar.gif",
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
	$(document).ready(function() {

		$("#add-button").click(function() {
			$(".hidden-add-form").show("slow");
		});
		$("#cancel-button").click(function() {
			$(".hidden-add-form").hide("slow");
		});
	});
	function load() {
		document.getElementById('add-form-description').innerHTML = "${edTask.description}";
	}
</script>
<style type="text/css">
<!--
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
  height: 400px;
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
-->
</style>
</head>
<body id="portal" class="up fl-theme-mist" onload="load()">
  <div class="container" id="portalPageBodyInner">
    <div class="content">

      <portlet:actionURL var="DoPlannerAddAction">
        <portlet:param name="action" value="plannerAdd" />
      </portlet:actionURL>

      <portlet:actionURL var="PlannerAddAction">
        <portlet:param name="action" value="${plAddAction}" />
      </portlet:actionURL>

      <%--       <portlet:actionURL var="addTaskAction">
        <portlet:param name="action" value="addTask" />
      </portlet:actionURL> --%>

      <portlet:actionURL var="searchTaskAction">
        <portlet:param name="action" value="searchTask" />
      </portlet:actionURL>

      <portlet:actionURL var="deleteTaskAction">
        <portlet:param name="action" value="deleteTask" />
      </portlet:actionURL>

      <portlet:actionURL var="DoPlannerEditAction">
        <portlet:param name="action" value="plannerEdit" />
      </portlet:actionURL>

      <form:form commandName="PlannerAddForm" method="post" action="${DoPlannerAddAction}">
        <input id="add-button" type="submit" name="ok" value=" Add " />
      </form:form>

      <%-- <a id="add-button" href='<portlet:actionURL><portlet:param name="action" value="plannerAdd"/></portlet:actionURL>'>AddTask</a> --%>

      <div class="hidden-add-form">

        <form:form commandName="PlannerAddForm" method="post" action="${PlannerAddAction}">

          <p id="add-form">
          <table class="Table" cellspacing="1" width="560">
            <caption class="TableCaption">&nbsp;</caption>
            <caption class="TableCaption">
              <h1>Add new Task</h1>
            </caption>
            <tbody>
              <tr>
                <td width="139" class="ColumnLabel"><label for="add-form-title">Title*</label></td>
                <td width="412" class="CellBGR3"><form:input path="title" id="add-form-title"
                    value="${edTask.taskname}"></form:input> <form:input path="taskId" value="${edTask.taskid}"
                    type="hidden" /></td>
              </tr>
              <tr>
                <td class="ColumnLabel"><label for="add-form-startDate">Start Date</label></td>
                <td class="CellBGR3"><form:input path="startDate" value="${edTask.startdate}"
                    id="add-form-startDate"></form:input> (MM-DD-YY)</td>
                <td class="ColumnLabel"><label for="add-form-stage">Stage*</label></td>
                <td><form:select class="styled" path="stageId" value="${edTask.stageid}" multiple="single"
                    id="add-form-stage">
                    <form:options items="${stageMapAdd}" />
                  </form:select></td>
                <td class="ColumnLabel"><label for="add-form-project">Project*</label></td>
                <td><form:select class="styled" path="projectId" multiple="single" id="add-form-stage">
                    <form:options items="${projectMapAdd}" />
                  </form:select></td>
              </tr>
              <tr>
                <td class="ColumnLabel"><label for="add-form-finishDate">Finish Date</label></td>
                <td class="CellBGR3"><form:input path="endDate" value="${edTask.plannedenddate}"
                    id="add-form-finishDate" /> (MM-DD-YY)</td>
                <td class="ColumnLabel"><label for="add-form-process">Process&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                <td><form:select class="styled" path="processId" value="${edTask.processId}" multiple="single"
                    id="add-form-process">
                    <form:options items="${processMapAdd}" />
                  </form:select></td>
                <td class="ColumnLabel"><label for="add-form-assignedTo">Assigned To*</label></td>
                <td><form:select class="styled" path="developerId" value="${edTask.developerid}" multiple="single"
                    id="add-form-assignedTo">
                    <form:options items="${developerMapAdd}" />
                  </form:select></td>
              </tr>
              <tr>
                <td class="ColumnLabel"><label for="add-form-plannedEffort">Planned Effort</label></td>
                <td class="CellBGR3"><form:input path="plannedEffort" value="${edTask.plannedeffort}"
                    id="add-form-plannedEffort" /> (Hours)</td>
                <td class="ColumnLabel"><label for="add-form-product">Product*</label></td>
                <td><form:select class="styled" path="productId" value="${edTask.product}" multiple="single"
                    id="add-form-process">
                    <form:options items="${productMapAdd}" />
                  </form:select></td>

                <td class="ColumnLabel"><label for="add-form-status">Status*</label></td>
                <td><form:select class="styled" path="statusId" value="${edTask.statusId}" multiple="single"
                    id="add-form-status">
                    <form:options items="${statusMapAdd}" />
                  </form:select></td>
              </tr>
              <tr>
                <td class="ColumnLabel"><label for="add-form-currentEffort">Current Effort</label></td>
                <td class="CellBGR3"><form:input path="currentEffort" id="add-form-currentEffort" /> (Hours)</td>
                <td class="ColumnLabel"><label for="add-form-productSize">Product Size</label></td>
                <td class="CellBGR3"><form:input path="productSize" value="${edTask.productsize}"
                    id="add-form-productSize" /></td>
              </tr>
              <tr>
                <td class="ColumnLabel"><label for="add-form-actualEffort">Actual Effort</label></td>
                <td class="CellBGR3"><form:input path="actualEffort" id="add-form-actualEffort"
                    value="${edTask.actualeffort}" /> (Hours)</td>
                <td class="ColumnLabel"><label for="add-form-completedSize">Completed Size</label></td>
                <td class="CellBGR3"><form:input path="completedSize" value="${edTask.completenessstatus}"
                    id="add-form-completedSize" /></td>
              </tr>
              <tr>
                <td class="ColumnLabel"><label for="add-form-description">Description&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                <td class="CellBGR3"><form:textarea path="description" value="123" rows="4" cols="40"
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
        <form:form commandName="PlannerForm" method="post" action="${searchTaskAction}">
          <table>
            <tr>
              <td><b>&nbsp;&nbsp;Project&nbsp;&nbsp;</b></td>
              <td><b>&nbsp;&nbsp;Status&nbsp;&nbsp;</b></td>
              <td><b>&nbsp;&nbsp;Stage&nbsp;&nbsp;</b></td>
              <td><b>&nbsp;&nbsp;Assigned To&nbsp;</b></td>
              <td><b>&nbsp;&nbsp;Sort&nbsp;by&nbsp;&nbsp;</b></td>
            </tr>
            <tr>
              <td><form:select class="styled" path="projectDefault" multiple="single">
                  <form:options items="${projectMap}" />
                </form:select></td>
              <td><form:select class="styled" path="statusDefault" multiple="single">
                  <form:options items="${statusMap}" />
                </form:select></td>
              <td><form:select class="styled" path="stageDefault" multiple="single">
                  <form:options items="${stageMap}" />
                </form:select></td>
              <td><form:select class="styled" path="developerDefault" multiple="single">
                  <form:options items="${developerMap}" />
                </form:select></td>
              <td width="10%"><select class="styled">
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
                    <td>${count}</td>
                    <td>${task.project_str}</td>
                    <td>${task.taskname}</td>
                    <td>${task.stage_str}</td>
                    <td>${task.process_str}</td>
                    <td>${task.developer_str}</td>
                    <td></td>
                    <td>${task.completenessstatus}</td>
                    <td>${task.startdate}</td>
                    <td>${task.plannedenddate}</td>
                    <td>${task.plannedeffort}</td>
                    <td>${task.actualeffort}</td>
                    <td><input type="image" alt="Submit"
                      src="../PlannerModule/resource_files/icons/Actions-document-edit-icon.png" width="24" height="24"
                      onclick='submitAction("${task.taskid}modTask", "${DoPlannerEditAction}")'></input></td>
                    <td><form:input path="taskId" value="${task.taskid}" type="hidden" /> <input type="image"
                      alt="Submit" src="../PlannerModule/resource_files/icons/Actions-delete-icon.png" width="24"
                      height="24" onclick='submitAction("${task.taskid}modTask", "${deleteTaskAction}")' />
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