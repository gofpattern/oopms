<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet"%>
<%@ taglib prefix="portlet2" uri="http://java.sun.com/portlet_2_0"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="javax.portlet.PortletSession"%>

<c:set var="portletNamespace" scope="request">
  <portlet:namespace />
</c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>Requirement Add</title>
<jsp:include page="header.jsp" />
<portlet2:defineObjects />
<portlet:defineObjects />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style type="text/css">
#tabs28 {
  float: left;
  width: 100%;
  background: #444;
  font-size: 93%;
  line-height: normal;
  border-bottom: 1px solid #DD740B;
}

#tabs28 ul {
  margin: 0;
  padding: 0px 10px 0 20px;
  list-style: none;
}

#tabs28 li {
  display: inline;
  margin: 0;
  padding: 0;
}

#tabs28 a {
  float: left;
  background: url(slide-left.gif) no-repeat left top;
  margin: 0;
  padding: 0 0 0 5px;
  text-decoration: none;
}

#tabs28 a span {
  float: left;
  display: block;
  background: url(side-right.gif) no-repeat right top;
  padding: 5px 15px 4px 6px;
  color: #FFF;
}

#tabs28 a span {
  float: none;
}

#tabsI a:hover span {
  color: #FFF;
}

#tabs28 a:hover {
  background-position: 0% -42px;
}

#tabs28 a:hover span {
  background-position: 100% -42px;
}

textarea {
  padding: .2em .4em;
  line-height: 1.4;
  background: white;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.2) inset;
  -moz-border-radius: .6em;
  -webkit-border-radius: .6em;
  border-radius: .6em;
}
</style>

<script language="javascript">
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

	$(document)
			.ready(

					function() {
						yav.init("${portletNamespace}RequirementAdd", rules);
						$('#mainTable tr')
								.filter(':has(:checkbox:checked)')
								.addClass('selected')
								.end()
								.click(
										function(event) {
											$(this).toggleClass('selected');
											if (event.target.type !== 'checkbox') {
												$(':checkbox', this)
														.attr(
																'checked',
																function() {
																	return !this.checked;
																});
											}
										});

						$("#datepicker1")
								.datepicker(
										{
											showOn : "button",
											buttonImage : "/<spring:message code='app.context'/>/resource_files/images/calendar.gif",
											buttonImageOnly : true
										});
						$("#datepicker2")
								.datepicker(
										{
											showOn : "button",
											buttonImage : "/<spring:message code='app.context'/>/resource_files/images/calendar.gif",
											buttonImageOnly : true
										});
						$("#datepicker3")
								.datepicker(
										{
											showOn : "button",
											buttonImage : "/<spring:message code='app.context'/>/resource_files/images/calendar.gif",
											buttonImageOnly : true
										});
						$("#datepicker4")
								.datepicker(
										{
											showOn : "button",
											buttonImage : "/<spring:message code='app.context'/>/resource_files/images/calendar.gif",
											buttonImageOnly : true
										});
						$("#datepicker5")
								.datepicker(
										{
											showOn : "button",
											buttonImage : "/<spring:message code='app.context'/>/resource_files/images/calendar.gif",
											buttonImageOnly : true
										});
						$("#datepicker6")
								.datepicker(
										{
											showOn : "button",
											buttonImage : "/<spring:message code='app.context'/>/resource_files/images/calendar.gif",
											buttonImageOnly : true
										});
						$("#datepicker7")
								.datepicker(
										{
											showOn : "button",
											buttonImage : "/<spring:message code='app.context'/>/resource_files/images/calendar.gif",
											buttonImageOnly : true
										});
					});
</script>

<SCRIPT type="text/javascript">
	var rules = new Array();
	rules[0] = 'requirementName:Requirement Name|required';
	rules[1] = 'srs:SRS Document|required';
	rules[2] = 'releaseNote:Release Notes|required';
	rules[3] = 'document:Design Document|maxlength|150';
	rules[4] = 'effort:Effort|maxlength|2';

	rules[5] = 'designedDate|date_lt|$createdDate';
	rules[6] = 'designedDate|date_le|$codedDate';
	rules[7] = 'codedDate|date_le|$testedDate';
	rules[8] = 'testedDate|date_le|$deployedDate';
	rules[9] = 'deployedDate|date_le|$acceptedDate';

	rules[10] = 'createdDate|date_le|$codedDate';
	rules[11] = 'createdDate|date_le|$testedDate';
	rules[12] = 'createdDate|date_le|$deployedDate';
	rules[13] = 'createdDate|date_le|$acceptedDate';

	rules[14] = 'designedDate|date_le|$testedDate';
	rules[15] = 'designedDate|date_le|$deployedDate';
	rules[16] = 'designedDate|date_le|$acceptedDate';

	rules[17] = 'codedDate|date_le|$deployedDate';
	rules[18] = 'testedDate|date_le|$acceptedDate';

	rules[19] = 'deployedDate|date_le|$acceptedDate';

	rules[20] = 'effort:Effort|numeric';

	rules[21] = 'createdDate|mask|mydate';
	rules[22] = 'designedDate|mask|mydate';
	rules[23] = 'codedDate|mask|mydate';
	rules[24] = 'testedDate|mask|mydate';
	rules[25] = 'deployedDate|mask|mydate';
	rules[26] = 'acceptedDate|mask|mydate';

	yav.addHelp('requirementName', 'Provide your Requirement Name');
	yav.addHelp('srs', 'Provide your SRS Directory');
	yav.addHelp('releaseNote', 'Provide your Requirement Realease Note');
	yav.addHelp('document', 'Provide your Design Doc Directory');
	yav.addHelp('effort', 'Provide your Effort with maxlength 2');

	yav.addHelp('createdDate', 'Provide your created Date');
	yav
			.addHelp('designedDate',
					'Provide your designed Date after created Date');
	yav.addHelp('codedDate', 'Provide your coded Date after designed Date');
	yav.addHelp('testedDate', 'Provide your tested Date after coded Date');
	yav.addHelp('deployedDate', 'Provide your deployed Date after tested Date');
	yav.addHelp('acceptedDate',
			'Provide your accepted Date after deployed Date');

	yav.addMask('mydate', '  /  /    ', '1234567890');
</SCRIPT>
</head>

<body>

  <div class="container">

    <table border="0">
      <tr>
        <th><strong>User: </strong></th>
        <td><strong><font color="#1490E3"><%=portletSession.getAttribute("USER", PortletSession.APPLICATION_SCOPE)%></font></strong></td>
      </tr>
      <tr>
        <th><strong>Joined Projects: </strong></th>
        <td><strong><font color="#1490E3">${projectList.size()}</font></strong></td>
      </tr>
      <tr>
        <th><strong>Requirements: </strong></th>
        <td><strong><font color="#1490E3"><a
              href='<portlet:renderURL><portlet:param name="action" value="requirementmanager"/></portlet:renderURL>'>Lists</a></font></strong></td>
      </tr>
    </table>


    <div class="content">

      <%--Display errors --%>
      <font color="red"><form:errors path="*"></form:errors></font>

      <%--Submit action --%>
      <portlet:actionURL var="formAction">
        <portlet:param name="action" value="Save" />
      </portlet:actionURL>
      <%-- Back to Requirement List --%>
      <portlet:renderURL var="requirementmanager">
        <portlet:param name="action" value="requirementmanager" />
      </portlet:renderURL>

      <form:form name="${portletNamespace}RequirementAdd" commandName="RequirementAddForm" method="post"
        action="${formAction}">
        <h1>Create Requirement</h1>

        <div id=errorsDiv style="color: red">
          <c:if test="${not empty errorList }">
            <font color="red">${errorList}</font>
          </c:if>
        </div>

        <table width="95%" cellspacing="1" class="portlet-table">


          <tr>
            <th><b><font color="black">Project Name*</font></b><font color="red">&nbsp;</font></th>
            <td><form:select cssClass="styled" path="projectDefault" multiple="single">
                <c:set var="projectMap" value="${projectMap}" />
                <form:options items="${projectMap}" />
              </form:select></td>
          </tr>

          <tr>
            <!-- Requirement Name -->
            <th><b><font color="black">Requirement Name*</font></b><font color="red">&nbsp;</font></th>
            <td><form:input path="requirementName" maxlength="150" id="requirementName" /><br>
            <span id=errorsDiv_requirementName></span></td>
          </tr>

          <tr>
            <th><b><font color="black">Requirement Size</font></b><font color="red">&nbsp;</font></th>
            <td><form:select path="reqSize">
                <option value="1" selected="selected">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
              </form:select></td>
          </tr>

          <tr>
            <th><b><font color="black">Requirement Type</font></b><font color="red">&nbsp;</font></th>
            <td><form:select path="reqType">
                <option value="1" selected="selected">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
              </form:select></td>
          </tr>

          <tr>
            <!-- srs -->
            <th><b><font color="black">SRS Document* </font></b><font color="red">&nbsp;</font></th>
            <td><form:input path="srs" maxlength="150" id="srs" /><br>
            <span id=errorsDiv_srs></span></td>
          </tr>

          <tr>
            <!-- releaseNote -->
            <th><b><font color="black">Release Note* </font></b><font color="red">&nbsp;</font></th>
            <td><form:input path="releaseNote" maxlength="150" id="releaseNote" /><br>
            <span id=errorsDiv_releaseNote></span></td>
          </tr>

          <tr>
            <!-- document -->
            <th><b><font color="black">Design Document </font></b><font color="red">&nbsp;</font></th>
            <td><form:input path="document" maxlength="150" id="document" /><br>
            <span id=errorsDiv_document></span></td>
          </tr>

          <tr>
            <!-- effort -->
            <th><b><font color="black">Effort </font></b><font color="red">&nbsp;</font></th>
            <td><form:input path="effort" maxlength="150" id="effort" /><br>
            <span id=errorsDiv_effort></span></td>
          </tr>

          <tr>
            <th><b><font color="black">Created Date</font></b><font color="red">&nbsp;</font></th>
            <td><input maxlength="9" name="createdDate" size="9" value="" type="text" id="datepicker1" />
              (mm-dd-yyyy)<br>
            <span id=errorsDiv_createdDate></span></td>
          </tr>
          <tr>
            <th><b><font color="black">Designed Date</font></b><font color="red">&nbsp;</font></th>
            <td><input maxlength="9" name="designedDate" size="9" value="" type="text" id="datepicker2" />
              (mm-dd-yyyy)<br>
            <span id=errorsDiv_designedDate></span></td>
          </tr>
          <tr>
            <th><b><font color="black">Coded Date</font></b><font color="red">&nbsp;</font></th>
            <td><input maxlength="9" name="codedDate" size="9" value="" type="text" id="datepicker3" />
              (mm-dd-yyyy)<br>
            <span id=errorsDiv_codedDate></span></td>
          </tr>
          <tr>
            <th><b><font color="black">Tested Date</font></b><font color="red">&nbsp;</font></th>
            <td><input maxlength="9" name="testedDate" size="9" value="" type="text" id="datepicker4" />
              (mm-dd-yyyy)<br>
            <span id=errorsDiv_testedDate></span></td>
          </tr>
          <tr>
            <th><b><font color="black">Deployed Date</font></b><font color="red">&nbsp;</font></th>
            <td><input maxlength="9" name="deployedDate" size="9" value="" type="text" id="datepicker5" />
              (mm-dd-yyyy)<br>
            <span id=errorsDiv_deployedDate></span></td>
          </tr>
          <tr>
            <th><b><font color="black">Accepted Date</font></b><font color="red">&nbsp;</font></th>
            <td><input maxlength="9" name="acceptedDate" size="9" value="" type="text" id="datepicker6" />
              (mm-dd-yyyy)<br>
            <span id=errorsDiv_acceptedDate></span></td>
          </tr>

        </table>
        <br>
        <input name="RequirementAdd" class="Button" value="Save" type="button"
          onclick='submitAction2("${portletNamespace}RequirementAdd", "${formAction}")'>&nbsp;&nbsp;&nbsp;&nbsp;
          <input name="Back" class="Button"
          onclick='submitAction("${portletNamespace}RequirementAdd", "${requirementmanager}")' value="Requirement List"
          type="button">&nbsp;&nbsp;&nbsp;&nbsp; 
      </form:form>
      <br>
      <div class="footer">
        <p>DMS Group</p>
      </div>
      <!-- end .footer -->

    </div>
    <!-- end .content -->

  </div>
  <!-- end .container -->
</body>
</html>
