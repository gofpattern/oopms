<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="resource_files/CommonScript.js"></script>
<script type="text/javascript" src="resource_files/utils.js"></script>
<script type="text/javascript" src="resource_files/popcalendar.js"></script>
</head>
<title>Add New Defect</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="StyleSheet" href="/<spring:message code="app.context"/>/resource_files/DMSStyleSheet.css" type="text/css">
<link rel="StyleSheet" href="/<spring:message code="app.context"/>/resource_files/pcal.css" type="text/css">
<link type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/yav/yav-style.css" rel="Stylesheet" />
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/yav.js"></script>
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/yav-config.js"></script>
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/css/jquery.js"></script>
<script type="text/javascript">
	function validateDefect() {
		var fromDate = document.getElementById("datepicker1").value;
		var toDate = document.getElementById("datepicker2").value;
		if (!checkValidDate(fromDate, "formatFromDate")
				|| !checkValidDate(toDate, "formatToDate")) {
			$('#errorDiv').css('display', 'inline');
			$('#errorDiv').css('text-align', 'center');
			return false;
		}
		return true;
	}

	function submitAction(formName, actionUrl) {

		var frm = document.forms[formName];

		frm.action = actionUrl;

		frm.submit();

	}

	function submitActionValidate(formName, actionUrl) {
		var frm = document.forms[formName];
		var flag = yav.performCheck(formName, rules, 'inline');
		if (flag == true) {
			frm.action = actionUrl;
			frm.submit();
		}
	}
	function submitActionConfirm(formName, actionUrl) {

		var r = confirm("Are you sure to delete ?");
		if (r == true) {
			var frm = document.forms[formName];

			frm.action = actionUrl;

			frm.submit();
		} else {
			return;
		}
	}
	$(document)
			.ready(
					function() {
						// Listen for click on toggle checkbox

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

						// apply validation
						yav.init('${portletNamespace}AddDefect', rules);

					});
	// validate rules
	var rules = new Array();
	rules[0] = 'projectDis:Project|required';
	rules[1] = 'memberDisCreated:Created by|required';
	rules[2] = 'title:Title|required';
	rules[3] = 'description:Description|required';
	rules[4] = 'createDateString:Created date|required';
	rules[5] = 'createDateString:Created date|date';
	rules[6] = 'dueDateString:Due date|date';
	rules[7] = 'dueDateString:Due date|required';
	rules[8] = 'createDateString:Created date|date_le|$dueDateString:Due date';

	yav.addHelp('projectDis', 'Select your Project');
	yav.addHelp('memberDisCreated', 'Select your Developer');
	yav.addHelp('title', 'Provide your Title');
	yav.addHelp('description', 'Provide your Description');
	yav.addHelp('qcActivityDis', 'Select your QC activity');
	yav.addHelp('originDis', 'Select Defect origin');
	yav.addHelp('typeDis', 'Select Type of activity');
	yav.addHelp('priorityDis', 'Select your Priority');
	yav.addHelp('workProductDis', 'Select your Work product');
	yav.addHelp('severityDis', 'Select your Severity');
	yav.addHelp('testCaseId', 'Provide your Test Case ID');
	yav.addHelp('memberDisOwner', 'Select Defect owner');
	yav.addHelp('memberDisAssigned', 'Select Assigned To');
	yav.addHelp('createDateString', 'Provide your Created date');
	yav.addHelp('dueDateString', 'Provide your Due date');
	yav.addHelp('causeAnalysis', 'Provide your Cause analysis');
	yav.addHelp('correctiveAction', 'Provide your Corrective action');
</script>

<script type="text/javascript" src='/<spring:message code="app.context"/>/scripts/common.js'></script>
<jsp:include page="header.jsp" />
<portlet:actionURL var="formAction">
  <portlet:param name="action" value="updateDefect" />
</portlet:actionURL>
<portlet:renderURL var="goViewDefectList2">
  <portlet:param name="action" value="init" />
</portlet:renderURL>




<body id="portal" class="up fl-theme-mist">

  <div style="font-size: 100%" id="portalPageBodyInner" class="container">
    <div class="fl-widget-titlebar titlebar portlet-titlebar" role="sectionhead">
      <h2 class="title">Update Defect</h2>
    </div>
    <%--Display errors --%>
    <font color="red"><form:errors path="*"></form:errors></font>
    <DIV id=errorsDiv></DIV>
    <form:form name="${portletNamespace}AddDefect" commandName="updateDefectForm" method="post" action="${formAction}"
      onsubmit="return yav.performCheck('${portletNamespace}AddDefect', rules, 'inline');">

      <table style="font-size: 100%" class="portlet-table" border="0" cellpadding="1" cellspacing="0" width="100%">
        <colgroup>
          <col width="15%">
          <col width="45%">
          <col width="5%">
          <col width="10%">
          <col width="30%">
        </colgroup>
        <tbody>
          <tr>
            <!-- Title -->
            <th align="left" valign="middle"><b><font color="black">Project</font></b><font color="red">&nbsp;*</font></th>
            <td align="left" valign="middle"><form:select path="projectDis" multiple="single">
                <form:options items="${projectMap}" />
              </form:select><span id=errorsDiv_projectDis></span></td>

          </tr>
          <tr>
            <!-- Title -->
            <th align="left" valign="middle"><b><font color="black">Created By</font></b><font color="red">&nbsp;*</font></th>
            <td align="left" valign="middle"><form:select path="memberDisCreated" multiple="single">
                <form:options items="${memberMap}" />
              </form:select><span id=errorsDiv_memberDisCreated></span></td>


          </tr>
          <tr>
            <!-- Title -->
            <th align="left" valign="middle"><b><font color="black">Status</font></b><font color="red">&nbsp;*</font></th>
            <td align="left" valign="middle"><form:select path="statusDis" multiple="single">
                <form:options items="${statusMap}" />
              </form:select><span id=errorsDiv_statusDis></span></td>


          </tr>
          <tr>
            <!-- Title -->
            <th align="left" valign="middle"><b><font color="black">Title</font></b><font color="red">&nbsp;*</font></th>
            <td align="left" valign="middle">
              <%-- 
        <input class="LongBox" maxlength="150" name="Title" value="These is no description flow of use cases" type="text">
        --%> <form:input cssStyle="width:350px;" path="title" maxlength="255" /><span id=errorsDiv_title></span>
            </td>


          </tr>
          <tr>
            <!-- Description -->
            <th align="left" valign="middle"><b><font color="black">Description</font></b><font color="red">&nbsp;*</font></th>
            <td valign="middle">
              <%--
        <textarea class="LargeTextarea" rows="3" wrap="virtual" name="txtDescription" onchange="Description.value=txtDescription.value">It's better if add more a section to describe the flow of use cases: using graph or matrix</textarea> <input name="Description" value="It's better if add more a section to describe the flow of use cases: using graph or matrix" type="hidden">
         --%> <form:textarea cssStyle="" path="description" cols="55" rows="5" /> <span id=errorsDiv_description></span>
            </td>


          </tr>
          <tr>
            <!-- QC Activity -->
            <th align="left" valign="middle"><font color="black"><b>QC activity</b></font><font color="red">&nbsp;*</font></th>
            <td align="left" valign="middle">
              <%--
        <select name="QCActivity" class="SmallCombo" onchange="ChangeTypeActivity()"><option value="10">10-Unit test</option><option value="11">11-Integration test</option><option value="12">12-System test</option><option value="13">13-Acceptance test</option><option value="14">14-Regression test</option><option value="15">15-After Release test</option><option value="16">16-Other test</option><option value="17">17-Prototype test</option><option selected="selected" value="20">20-Document review</option><option value="21">21-Code review</option><option value="22">22-After Release review</option><option value="23">23-Prototype review</option><option value="24">24-Other review</option><option value="30">30-Quality Gate inspection</option><option value="31">31-Final inspection</option><option value="32">32-Other inspection</option><option value="33">33-UT Inspection</option><option value="40">40-Baseline audit</option><option value="41">41-Other audit</option>
        </select>
         --%> <form:select path="qcActivityDis" multiple="single">
                <form:options items="${qcActivityMap}" />
              </form:select><span id=errorsDiv_qcActivityDis></span>
            </td>
          </tr>
          <tr>
            <!-- Defect Origin  -->
            <th align="left" valign="middle"><font color="black"><b>Defect origin</b></font><font color="red">&nbsp;*</font></th>
            <td align="left" valign="middle"><form:select path="originDis" multiple="single">
                <form:options items="${originMap}" />
              </form:select><span id=errorsDiv_originDis></span></td>
          </tr>
          <tr>
            <!-- Type of Activity -->
            <th align="left" valign="middle"><b><font color="black">Type of activity</font></b><font color="red">&nbsp;*</font></th>
            <td align="left" valign="middle"><form:select path="typeDis" multiple="single">
                <form:options items="${typeMap}" />
              </form:select><span id=errorsDiv_typeDis></span></td>
          </tr>
          <tr>
            <!-- Priority -->
            <th align="left" valign="middle"><b><font color="black">Priority</font></b></th>
            <td align="left" valign="middle"><form:select path="priorityDis" multiple="single">
                <form:options items="${priorityMap}" />
              </form:select><span id=errorsDiv_priorityDis></span></td>
          </tr>

          <tr>
            <!-- Work Product COMBO -->
            <th align="left" valign="middle"><b><font color="black">Work product</font></b><font color="red">&nbsp;*</font></th>
            <td align="left" valign="middle"><form:select path="workProductDis" multiple="single">
                <form:options items="${workProductMap}" />
              </form:select><span id=errorsDiv_workProductDis></span></td>
          </tr>
          <tr>
            <!-- Severity -->
            <th align="left" valign="middle"><b><font color="black">Severity</font></b><font color="red">&nbsp;*</font></th>
            <td align="left" valign="middle"><form:select path="severityDis" multiple="single">
                <form:options items="${severityMap}" />
              </form:select><span id=errorsDiv_severityDis></span></td>
          </tr>

          <tr>
            <!-- Module Code -->
            <th align="left" valign="middle"><b><font color="black">Test Case ID</font></b></th>
            <td align="left" valign="middle"><form:input path="testCaseId" cssStyle="width:180px;" />
            <span id=errorsDiv_testCaseId></span></td>


          </tr>
          <!-- Defect Owner -->
          <tr>
            <th align="left" valign="middle"><b><font color="black">Defect owner</font></b></th>
            <td align="left" valign="middle"><form:select path="memberDisOwner" multiple="single">
                <form:options items="${memberMap}" />
              </form:select><span id=errorsDiv_memberDisOwner></span></td>
          </tr>
          <tr>

          </tr>
          <tr>
            <!-- AssignTo -->
            <th align="left" valign="middle"><b><font color="black">Assigned To</font></b></th>
            <td align="left" valign="middle"><form:select path="memberDisAssigned" multiple="single">
                <form:options items="${memberMap}" />
              </form:select><span id=errorsDiv_memberDisAssigned></span></td>

          </tr>
          <tr>
            <!-- Created Date  -->
            <th id="idCreatedDate" align="left" nowrap="nowrap" valign="middle"><b><font color="black">Created
                  date</font></b><font color="red">&nbsp;*</font></th>
            <td align="left" valign="middle"><form:input cssStyle="width:80px;" path="createDateString"
                disabled="disabled" id="datepicker1" /><span id=errorsDiv_createDateString></span></td>
          </tr>
          <tr>
            <!-- Due Date -->
            <th id="idDueDate" align="left" valign="middle"><b><font color="black">Due date</font></b></th>
            <td align="left" valign="middle"><form:input cssStyle="width:80px;" path="dueDateString"
                disabled="disabled" id="datepicker2" /><span id=errorsDiv_dueDateString></span></td>
          </tr>

          <tr>
            <th valign="middle"><b><font color="black">Cause analysis&nbsp;</font></b></th>
            <td><form:textarea cssStyle="" path="causeAnalysis" cols="55" rows="5" />
            <span id=errorsDiv_causeAnalysis></td>

          </tr>

          <tr>
            <!-- Corrective Action -->
            <th><b><font color="black">Corrective action&nbsp;</font></b></th>
            <td><form:textarea cssStyle="" path="correctiveAction" cols="55" rows="5" /><span
              id=errorsDiv_correctiveAction></span></td>

          </tr>

        </tbody>
      </table>
      <table border="0" cellpadding="1" cellspacing="0" width="100%">
        <tbody>
          <tr>
            <td></td>
          </tr>
        </tbody>
      </table>
      <p>
        <%--
<input name="NewDefect" class="Button" value="Save" type="Submit">&nbsp;&nbsp;&nbsp;&nbsp;
 --%>
        <form:hidden path="defectId" />
        <input name="Save" class="Button" onclick='submitActionValidate("${portletNamespace}AddDefect", "${formAction}")'
          value="Save" type="button">&nbsp;&nbsp;&nbsp;&nbsp; <input name="Back" class="Button"
          onclick='submitAction("${portletNamespace}AddDefect", "${goViewDefectList2}")' value="Defect List"
          type="button">&nbsp;&nbsp;&nbsp;&nbsp; <input name="Attach" class="Button"
          onclick="javascript:doAttach()" value="Attach file" type="button">
      </p>
    </form:form>
    <br> <br> <br>


  </div>
</body>
</html>