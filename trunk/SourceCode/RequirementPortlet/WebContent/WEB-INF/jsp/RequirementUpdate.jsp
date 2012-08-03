<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>Update Requirement</title>
<jsp:include page="header.jsp" />
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
						$("#datepicker8")
								.datepicker(
										{
											showOn : "button",
											buttonImage : "/<spring:message code='app.context'/>/resource_files/images/calendar.gif",
											buttonImageOnly : true
										});
						$("#datepicker9")
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

    rules[5] = 'createdDate|date_le|$designedDate';
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

    rules[18] = 'testedDate|date_le|$acceptedDate';
</SCRIPT>
</head>

<body>

  <div class="container">

    <!-- Header -->
    <div class="header">
      <table width="960">
        <tr>
          <td width="697" height="92">&nbsp;</td>
          <td width="251" valign="top">
            <p>
              Hello Duy , <a href="HomePage.html">Log Out</a>
            </p>
            <p>
              <a href="UserDetail.html">Change your Information</a>
            </p>
          </td>
        </tr>
      </table>
    </div>


    <div class="content">


      <div id="tabs28">
        <ul>
          <h5>
            <li><a href="ManagerHomePage.html"><span>Dash Board</span></a></li>
          </h5>
        </ul>
      </div>

      <div class="demo" style="font-size: 20px" align="right">
        <a href="Create Project-Admin ver.html">Update Requirement</a>
      </div>

      <%--Display errors --%>
      <font color="red"><form:errors path="*"></form:errors></font>

      <%--Submit action --%>
      <portlet:actionURL var="formAction">
        <portlet:param name="action" value="SaveUpdate" />
      </portlet:actionURL>
      <%-- Back to Requirement List --%>
      <portlet:renderURL var="requirementmanager">
        <portlet:param name="action" value="requirementmanager" />
      </portlet:renderURL>

      <form:form name="${portletNamespace}RequirementUpdate" commandName="RequirementUpdateForm" method="post"
        action="${formAction}">

        <div id=errorsDiv style="color: red">
          <c:if test="${not empty errorList }">
            <font color="red">${errorList}</font>
          </c:if>
        </div>

        <table width="95%" cellspacing="1">

          <div>
            <tbody>
              <tr>
                <td align="left" valign="middle"><b><font color="black">Project Name*</font></b><font color="red">&nbsp;</font></td>
                <td width="26%"><form:select cssClass="styled" path="projectDefault" multiple="single">
                    <c:set var="projectMap" value="${projectMap}" />
                    <form:options items="${projectMap}" />
                  </form:select></td>
              </tr>

              <tr>
                <!-- Requirement Name -->
                <td align="left" valign="middle"><b><font color="black">Requirement Name*</font></b><font
                  color="red">&nbsp;</font></td>
                <td align="left" valign="middle"><form:input value="${currentRequirement.requirement}"
                    path="requirementName" maxlength="150" id="requirementName" /></td>
              </tr>

              <tr>
                <td align="left" valign="middle"><b><font color="black">Requirement Size</font></b><font
                  color="red">&nbsp;</font></td>
                <td><form:select path="reqSize">
                    <option value="1" selected="selected">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                  </form:select></td>
              </tr>

              <tr>
                <td align="left" valign="middle"><b><font color="black">Requirement Type</font></b><font
                  color="red">&nbsp;</font></td>
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
                <td align="left" valign="middle"><b><font color="black">SRS Document* </font></b><font color="red">&nbsp;</font></td>
                <td align="left" valign="middle"><form:input path="srs" maxlength="150"
                    value="${currentRequirement.srs}" id="srs" /></td>
              </tr>

              <tr>
                <!-- releaseNote -->
                <td align="left" valign="middle"><b><font color="black">Release Note* </font></b><font color="red">&nbsp;</font></td>
                <td align="left" valign="middle"><form:input path="releaseNote" maxlength="150"
                    value="${currentRequirement.releaseNote}" id="releaseNote" /></td>
              </tr>

              <tr>
                <!-- document -->
                <td align="left" valign="middle"><b><font color="black">Design Document </font></b><font
                  color="red">&nbsp;</font></td>
                <td align="left" valign="middle"><form:input path="document" maxlength="150"
                    value="${currentRequirement.dd}" id="document" /></td>
              </tr>

              <tr>
                <!-- effort -->
                <td align="left" valign="middle"><b><font color="black">Effort </font></b><font color="red">&nbsp;</font></td>
                <td align="left" valign="middle"><form:input path="effort" maxlength="150"
                    value="${currentRequirement.effort}" id="effort" /></td>
              </tr>

              <tr>
                <!-- Elapsed Day -->
                <td align="left" valign="middle"><b><font color="black">Elapsed Day </font></b><font color="red">&nbsp;</font></td>
                <td align="left" valign="middle"><form:input path="elapsedDay" maxlength="150"
                    value="${currentRequirement.elapsedDay}" /></td>
              </tr>

              <tr>
                <!-- Test Case -->
                <td align="left" valign="middle"><b><font color="black">Test Case </font></b><font color="red">&nbsp;</font></td>
                <td align="left" valign="middle"><form:input path="testCase" maxlength="150"
                    value="${currentRequirement.testcase}" /></td>
              </tr>

              <tr>
                <!-- Code Module -->
                <td align="left" valign="middle"><b><font color="black">Code Module </font></b><font color="red">&nbsp;</font></td>
                <td align="left" valign="middle"><form:input path="codeModule" maxlength="150"
                    value="${currentRequirement.codeModule}" /></td>
              </tr>


              <tr>
                <td align="left" valign="middle"><b><font color="black">Created Date</font></b><font color="red">&nbsp;</font></td>
                <td><input maxlength="9" name="createdDate" size="9" value="" type="text" id="datepicker1" />
                  (mm-dd-yyyy)</td>
              </tr>
              <tr>
                <td align="left" valign="middle"><b><font color="black">Designed Date</font></b><font color="red">&nbsp;</font></td>
                <td><input maxlength="9" name="designedDate" size="9" value="" type="text" id="datepicker2" />
                  (mm-dd-yyyy)</td>
              </tr>
              <tr>
                <td align="left" valign="middle"><b><font color="black">Coded Date</font></b><font color="red">&nbsp;</font></td>
                <td><input maxlength="9" name="codedDate" size="9" value="" type="text" id="datepicker3" />
                  (mm-dd-yyyy)</td>
              </tr>
              <tr>
                <td align="left" valign="middle"><b><font color="black">Tested Date</font></b><font color="red">&nbsp;</font></td>
                <td><input maxlength="9" name="testedDate" size="9" value="" type="text" id="datepicker4" />
                  (mm-dd-yyyy)</td>
              </tr>
              <tr>
                <td align="left" valign="middle"><b><font color="black">Deployed Date</font></b><font color="red">&nbsp;</font></td>
                <td><input maxlength="9" name="deployedDate" size="9" value="" type="text" id="datepicker5" />
                  (mm-dd-yyyy)</td>
              </tr>
              <tr>
                <td align="left" valign="middle"><b><font color="black">Accepted Date</font></b><font color="red">&nbsp;</font></td>
                <td><input maxlength="9" name="acceptedDate" size="9" value="" type="text" id="datepicker6" />
                  (mm-dd-yyyy)</td>
              </tr>
              <tr>
                <td align="left" valign="middle"><b><font color="black">Response Date</font></b><font color="red">&nbsp;</font></td>
                <td><input maxlength="9" name="responseDate" size="9" value="" type="text" id="datepicker7" />
                  (mm-dd-yyyy)</td>
              </tr>
              <tr>
                <td align="left" valign="middle"><b><font color="black">Committed Date</font></b><font color="red">&nbsp;</font></td>
                <td><input maxlength="9" name="committedDate" size="9" value="" type="text" id="datepicker8" />
                  (mm-dd-yyyy)</td>
              </tr>
              <tr>
                <td align="left" valign="middle"><b><font color="black">Cancelled Date</font></b><font color="red">&nbsp;</font></td>
                <td><input maxlength="9" name="cancelledDate" size="9" value="" type="text" id="datepicker9" />
                  (mm-dd-yyyy)</td>
              </tr>


            </tbody>
          </div>


        </table>

        <input name="RequirementUpdate" class="Button" value="Save" type="button"
          onclick='submitAction("${portletNamespace}RequirementUpdate", "${formAction}")'>&nbsp;&nbsp;&nbsp;&nbsp;
          <input name="Back" class="Button"
          onclick='submitAction("${portletNamespace}RequirementUpdate", "${requirementmanager}")'
          value="Requirement List" type="button">&nbsp;&nbsp;&nbsp;&nbsp; 
      </form:form>

      <div class="footer">
        <br>
          <p>DMS Group</p>
      </div>
      <!-- end .footer -->

    </div>
    <!-- end .content -->

  </div>
  <!-- end .container -->
</body>
</html>
