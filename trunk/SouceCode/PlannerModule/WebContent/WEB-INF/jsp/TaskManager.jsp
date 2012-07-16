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
<script type="text/javascript">
	$(function() {
		$("#datepicker1")
				.datepicker(
						{
							showOn : "button",
							buttonImage : "../PlannerModule/resource_files/images/calendar.gif",
							buttonImageOnly : true
						});
		$("#datepicker2")
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
  display: none;
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
  height: 500px;
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
<body id="portal" class="up fl-theme-mist">
  <div class="container" id="portalPageBodyInner">
    <div class="content">

      <input id="add-button" type="button" name="ok" value=" Add " />

      <portlet:actionURL var="formAction">
        <portlet:param name="action" value="addTask" />
      </portlet:actionURL>


      <div class="hidden-add-form">
        <form:form commandName="PlannerForm" method="post" action="${formAction}">

          <p id="add-form">
          <table class="Table" cellspacing="1" width="560">
            <caption class="TableCaption">&nbsp;</caption>
            <caption class="TableCaption">
              <h1>Add new Task</h1>
            </caption>
            <tbody>
              <tr>
                <td width="139" class="ColumnLabel"><label for="add-form-title">Title*</label></td>
                <td width="412" class="CellBGR3"><form:input path="title"></form:input></td>
              </tr>
              <tr>
                <td class="ColumnLabel"><label for="datepicker1">Start Date</label></td>
                <td class="CellBGR3"><input type="text" id="datepicker1" /> (DD-MMM-YY)</td>
              </tr>
              <tr>
                <td class="ColumnLabel"><label for="datepicker2">Finish Date</label></td>
                <td class="CellBGR3"><input type="text" id="datepicker2" /> (DD-MMM-YY)</td>
              </tr>
              <tr>
                <td class="ColumnLabel"><label for="add-form-plannedEffort">Planned Effort</label></td>
                <td class="CellBGR3"><input type="text" id="add-form-plannedEffort"/> (Hours)</td>
              </tr>
              <tr>
                <td class="ColumnLabel"><label for="add-form-actualEffort">Actual Effort</label></td>
                <td class="CellBGR3"><input type="text" id="add-form-actualEffort"/> (Hours)</td>
              </tr>
              <tr>
                <td class="ColumnLabel"><label for="add-form-stage">Stage*</label></td>
                <td><form:select class="styled" path="stageDefault" multiple="single" id="add-form-stage">
                  <form:options items="${stageMap}" />
                </form:select></td>
              </tr>
              <tr>
                <td class="ColumnLabel"><label for="add-form-process">Process</label></td>
                <td><form:select class="styled" path="stageDefault" multiple="single" id="add-form-process">
                  <form:options items="${processMap}" />
                </form:select></td>
              </tr>
              <tr>
                <td class="ColumnLabel"><label for="add-form-product">Product*</label></td>
                <td class="CellBGR3"><select name="cboStatus2" class="styled" class="SmallCombo" id="add-form-product">
                    <option selected="selected" value="-1">All Product</option>
                    <option value="1">LOC</option>
                    <option value="2">Report</option>
                    <option value="3">Document</option>
                    <option value="4">Test Case</option>
                    <option value="0">Others</option>
                </select></td>
              </tr>
              <tr>
                <td class="ColumnLabel"><label for="add-form-assignedTo">Assigned To*</label></td>
                <td><form:select class="styled" path="developerDefault" multiple="single" id="add-form-assignedTo">
                  <form:options items="${developerMap}" />
                </form:select></td>
              </tr>
              <tr>
                <td class="ColumnLabel"><label for="add-form-status">Status*</label></td>
                <td><form:select class="styled" path="statusDefault" multiple="single" id="add-form-status">
                    <form:options items="${statusMap}" />
                  </form:select></td>
              </tr>
              <tr>
                <td class="ColumnLabel"><label for="add-form-description">Description</label></td>
                <td class="CellBGR3"><textarea rows="4" cols="50" name="note" id="add-form-description"></textarea></td>
              </tr>
            </tbody>
          </table>
          </p>
          <p>
            <input type="submit" name="ok" value=" OK " class="BUTTON"> <input id="cancel-button" type="button"
              name="cancel-button" value=" Cancel " />
          </p>
        </form:form>
      </div>

      <div id="content_planner">
        <form:form commandName="PlannerForm" method="post" action="${formAction}">
          <table>
            <tr>
              <td><b>&nbsp;&nbsp;Status&nbsp;&nbsp;</b></td>
              <td><b>&nbsp;&nbsp;Stage&nbsp;&nbsp;</b></td>
              <td><b>&nbsp;&nbsp;Assigned To&nbsp;</b></td>
              <td><b>&nbsp;&nbsp;Sort&nbsp;by&nbsp;&nbsp;</b></td>
            </tr>
            <tr>
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
                    <form action="Controller">
                      <c:set var="count" value="${count + 1}" />
                      <td>${count}</td>
                      <td>${task.taskcode}</td>
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
                        src="../PlannerModule/resource_files/icons/Actions-document-edit-icon.png" width="24"
                        height="24" value="edit" name="action"></input></td>
                      <td><input type="image" alt="Submit"
                        src="../PlannerModule/resource_files/icons/Actions-delete-icon.png" width="24" height="24"
                        value="delete" name="action"></input></td>
                    </form>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </c:if>
        </form:form>
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