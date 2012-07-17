<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<portlet:defineObjects />
<html><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<script src="resource_files/CommonScript.js"></script>
<script src="resource_files/utils.js"></script>
<script src="resource_files/popcalendar.js"></script></head><body topmargin="0" leftmargin="0" bgcolor="#FFFFFF"><div onclick="bShow=true" id="calendar" style="z-index:+999;position:absolute;visibility:hidden;"><table class="pcalTbl" width="250"><tbody><tr bgcolor="#000066"><td><table width="248"><tbody><tr><td class="pcalCaption"><font color="#ffffff"><b><span id="caption"><span id="spanLeft" class="pcalSelBtn" onmouseover="swapImage('changeLeft','pcalleft2.gif');this.style.borderColor='#8af';window.status='Click to scroll to previous month. Hold mouse button to scroll automatically.'" onclick="decMonth()" onmouseout="clearInterval(intervalID1);swapImage('changeLeft','pcalleft1.gif');this.style.borderColor='#36f';window.status=''" onmousedown="clearTimeout(timeoutID1);timeoutID1=setTimeout('StartDecMonth()',500)" onmouseup="clearTimeout(timeoutID1);clearInterval(intervalID1)">&nbsp;<img id="changeLeft" src="resource_files/pcalleft1.gif" height="11" border="0" width="10"></span>&nbsp;<span id="spanRight" class="pcalSelBtn" onmouseover="swapImage('changeRight','pcalright2.gif');this.style.borderColor='#8af';window.status='Click to scroll to next month. Hold mouse button to scroll automatically.'" onmouseout="clearInterval(intervalID1);swapImage('changeRight','pcalright1.gif');this.style.borderColor='#36f';window.status=''" onclick="incMonth()" onmousedown="clearTimeout(timeoutID1);timeoutID1=setTimeout('StartIncMonth()',500)" onmouseup="clearTimeout(timeoutID1);clearInterval(intervalID1)">&nbsp;<img id="changeRight" src="resource_files/pcalright1.gif" height="11" border="0" width="10"></span>&nbsp;<span id="spanMonth" class="pcalSelBtn" onmouseover="swapImage('changeMonth','pcaldrop2.gif');this.style.borderColor='#8af';window.status='Click to select a month.'" onmouseout="swapImage('changeMonth','pcaldrop1.gif');this.style.borderColor='#36f';window.status=''" onclick="popUpMonth()"></span>&nbsp;<span id="spanYear" class="pcalSelBtn" onmouseover="swapImage('changeYear','pcaldrop2.gif');this.style.borderColor='#8af';window.status='Click to select a year.'" onmouseout="swapImage('changeYear','pcaldrop1.gif');this.style.borderColor='#36f';window.status=''" onclick="popUpYear()"></span>&nbsp;</span></b></font></td><td align="right"><a href="javascript:hideCalendar()"><img src="resource_files/pcalclose.gif" height="13" border="0" width="15"></a></td></tr></tbody></table></td></tr><tr><td style="padding: 5px;" bgcolor="#ffffff"><span id="content"></span></td></tr><tr bgcolor="#f0f0f0"><td style="padding: 5px;" align="center"><span id="lblToday"><font color="#000066">Today is <a onmousemove="window.status='Go To Current Month'" onmouseout="window.status=''" title="Go To Current Month" style="text-decoration: none; color: black;" href="javascript:monthSelected=monthNow;yearSelected=yearNow;constructCalendar();">Tue, 5 Jun 2012</a></font></span></td></tr></tbody></table></div><div id="selectMonth" style="z-index:+999;position:absolute;visibility:hidden;"></div><div id="selectYear" style="z-index:+999;position:absolute;visibility:hidden;"></div>
<title>All Open Defects</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="StyleSheet" href="resource_files/DMSStyleSheet.css" type="text/css">
<link rel="StyleSheet" href="resource_files/pcal.css" type="text/css">
<script language="javascript">
function doSearch() {
    var form = document.frmAllDefectList;
    if (!isValidForm()) {
        return;
    }
    form.numPage.value = 0;
    form.hidAction.value = "DM";
    form.hidActionDetail.value = "SearchDefect";
    form.action = "DMSServlet";
    form.submit();
}

function doSort(type) {
    var form = document.frmAllDefectList;
    if (!isValidForm()) {
        return;
    }
    form.hidAction.value = "DM";
    form.hidActionDetail.value = "SearchDefect";
    form.action = "DMSServlet";
    if (type == "Next") {
        if (Next()) {
            form.submit();
        }
    }
    else if (type == "Prev") {
        if (Prev()) {
            form.submit();
        }
    }
    else {
        // Reverse direction of current sorted column
        if (form.SortBy.value == type) {
            if (form.Direction.value > 0) {
                form.Direction.value = 0;
            }
            else {
                form.Direction.value = 1;
            }
        }
        // New column
        else {
            // Sort descending for date fields
            if ((type == 'FixedDate') || (type == 'DueDate')) {
                form.Direction.value = 0;
            }
            else {
                form.Direction.value = 1;
            }
        }
        form.SortBy.value = type;
        form.numPage.value = 0;
        form.submit();
    }
}

function doQueryListing() {
    var form = document.frmAllDefectList;
    form.hidAction.value = "DM";
    form.hidActionDetail.value = "QueryListing";
    form.action = "DMSServlet";
    form.submit();
}

function Next() {
    var num;
    num = parseInt(document.forms[0].numPage.value);
    if (num < (document.forms[0].totalPage.value - 1)) {
        num++;
        document.forms[0].numPage.value = num;
        return true;
    }
    return false;
}

function Prev() {
    var num;
    num = parseInt(document.forms[0].numPage.value);
    if (num > 0) {
        num--;
        document.forms[0].numPage.value = num;
        if (num < 1) {
            num = 1;
        }
        return true;
    }
    return false;
}

function numberAllowed() {
    if (window.event.keyCode > 57 || window.event.keyCode < 48) {
        if (window.event.keyCode != 13) {
            window.event.keyCode = 0;
        }
    }
}

function doGoPage() {
    if (!isValidForm()) {
        return;
    }
    if (isNonNegativeInteger(document.forms[0].txtPage.value - 1)) {
        if ((parseInt(document.forms[0].txtPage.value)) > parseInt(document.forms[0].totalPage.value)) {
            alert("Invalid page.");
            return false;
        }
        else {
            document.forms[0].numPage.value = document.forms[0].txtPage.value - 1;
            document.forms[0].hidAction.value = "DM";
            document.forms[0].hidActionDetail.value = "SearchDefect";
            document.forms[0].action = "DMSServlet";
            document.forms[0].submit();
        }
    }
    else {
        alert("Invalid number.");
        document.forms[0].txtPage.focus();
        document.forms[0].txtPage.select();
        return false;
    }
}

function doAddNew() {
    var form = document.frmAllDefectList;
    form.hidAction.value = "DM";
    form.hidActionDetail.value = "AddDefect";
    form.action = "DMSServlet";
    form.submit();
}

function doUpdate(defect_id) {
    var form = document.frmAllDefectList;
    if (!isValidForm()) {
        return;
    }
    form.hidUpdateDefect.value = defect_id;
    form.hidAction.value = "DM";
    form.hidActionDetail.value = "UpdateDefect";
    form.action = "DMSServlet";
    form.submit();
}
function doBatchUpdate() {
    var form = document.frmAllDefectList;
    if(checkValid()) {
        form.hidAction.value = "DM";
        form.hidActionDetail.value = "BatchUpdateDefect";
        form.action = "DMSServlet";
        form.submit();
    }
    return;
}

function doRefresh() {
    var form = document.frmAllDefectList;
    if (!isValidForm()) {
        return;
    }
    form.hidAction.value = "DM";
    form.hidActionDetail.value = "SearchDefect";
    form.action = "DMSServlet";
    form.submit();
}

function doExport() {
    var form = document.frmAllDefectList;
    if (!isValidForm()) {
        return;
    }
    form.onsubmit = window.open('about:blank','popup','width=780,height=550,top=0,left=0,menubar=yes');
    form.target = "popup";
    form.hidExportAll.value = "true";
    form.hidAction.value = "DM";
    form.hidActionDetail.value = "ExportDefect";
    form.action = "DMSServlet";
    form.submit();
    //Reset to default settings
    form.onsubmit = "";
    form.target = "";
}

function doMoveDefect() {
    var form = document.frmAllDefectList;
//    if (!isValidForm()) {
//        return;
//    }
    if (checkValid()) {
        form.onsubmit = "";
        form.target = "";
        form.hidAction.value = "DM";
        form.hidActionDetail.value = "MoveDefect";
        form.action = "DMSServlet";
        form.submit();
    }
    return;
}

function isValidForm() {
    var count;
    var form = document.forms[0];
    for (count = 0x00; count < form.elements.length; count++) {
        if (!isValidControl(form.elements[count])) {
            return false;
        }
    }
    
    if ((form.txtFromDate.value.length > 0) && (form.txtToDate.value.length > 0)) {
        if (CompareDate(form.txtFromDate , form.txtToDate) > 0) {
            alert("From Date must lower or equal To Date");
            form.txtFromDate.focus();
            return false;
        }
    }
    if ((form.txtFixedFrom.value.length > 0) && (form.txtFixedTo.value.length > 0)) {
        if (CompareDate(form.txtFixedFrom , form.txtFixedTo) > 0) {
            alert("Fixed From date must lower or equal Fixed To date");
            form.txtFixedFrom.focus();
            return false;
        }
    }
    if (!isNonNegativeInteger(form.txtDefectID.value)) {
        alert("Please enter a positive number for DefectID");
        form.txtDefectID.focus();
        return false;
    }
    return true;
}

function isValidControl(control) {
    if ((control.name == "txtFromDate") || (control.name == "txtToDate") ||
        (control.name == "txtFixedFrom") || (control.name == "txtFixedTo")) {
        if (control.value.length <= 0 || isDate(control)) {
            return true;
        }
        else {
            return false;
        }
    }
    return true;
}

function checkValid() {
    var flag = true;
    for (var i = 0; i < document.forms[0].elements.length; i++) {
        var e = document.forms[0].elements[i];
        if (e.name == "selected" && e.type == "checkbox") {
            if (e.checked == 1) {
                flag = false;
            }
        }
    }
    if(flag) {
        alert("Please select defects to do this action!");
        return false;
    }
    return true;
}

function CheckAll(form) {
    for (var i = 0; i < form.elements.length; i++) {
        var e = form.elements[i];
        if (e.name != "allbox") {
            e.checked = form.allbox.checked;
        }
    }
}
</script>

<%--
<script type="text/javascript" src='<%= renderRequest.getContextPath() + "/scripts/common.js"%>'></script>
 --%>
<script type="text/javascript" src='/<spring:message code="app.context"/>/scripts/common.js'></script>

<%@ include file="/WEB-INF/jsp/headerDefect.jsp" %>
<div>
<p><img src="resource_files/AllOpenDefectListing.gif" height="28" border="0" width="510"></p>
</div>

<portlet:actionURL var="formAction">
  <portlet:param name="action" value="search" />
</portlet:actionURL>
<portlet:renderURL var="goAddNewDefect">
  <portlet:param name="action" value="goAddNewDefect" />
</portlet:renderURL>

<form:form name="${portletNamespace}Search" commandName="viewDefectList" method="post" action="${formAction}">
  <input name="eventId" value="" type="hidden">
<input name="hidActionDetail" value="" type="hidden">
<input name="hidAction" value="" type="hidden">
<input name="userRole" value="1110000000" type="hidden">
<input name="CheckRole" value="1110000000" type="hidden">
<input name="hidTypeOfView" value="ViewAllOpenDefects" type="hidden">
<input name="SortBy" value="" type="hidden">
<input name="Direction" value="0" type="hidden">
<input name="numPage" value="0" type="hidden">
<input name="totalPage" value="1" type="hidden">
<input name="hidExportAll" value="false" type="hidden">
<input name="hidNewProject" value="" type="hidden">
<input name="hidUpdateDefect" value="" type="hidden">
<input name="Role" value="1000100000" type="hidden">
<table border="0" width="100%">
    <tbody><tr>
        <td align="right"><a href="javascript:doQueryListing()">View DefectListing</a></td>
    </tr>
</tbody></table>
<table class="TblOut2" border="0" cellpadding="0" cellspacing="1" width="100%">
    <tbody><tr>
        <td width="8%"><b>User:</b></td>
        <td width="24%">${portletSessionScope.UserInfo.username}</td>
        <td width="12%"><b>Login Date:</b></td>
        <td width="25%">${portletSessionScope.UserInfo.loginDate}</td>
        <td width="9%"><b>Project</b></td>
        <td align="right" width="22%">
        <%-- 
        <select name="cboProjectList" class="SmallCombo" onchange="javascript:doChangeProject('DM','SearchDefect','ViewAllOpenDefects');"><option selected="selected" value="118385">OOPMS</option>
        </select>
        --%>
         <form:select id="cboProjectList" path="selProject" multiple="false" size="1" items="${viewDefectList.projectMap}">
         </form:select>
        </td>
    </tr>
    <tr>
        <td width="8%"><b>Group:</b></td>
        <td width="24%">FSOFT</td>
        <td width="12%"><b>Position:</b></td>
        <td width="25%">Project Leader</td>
        <td width="9%"><b>Status</b></td>
        <td align="right" width="22%"><select name="cboProjectStatus" class="SmallCombo" onchange="javascript:doChangeProject('DM','SearchDefect','ViewAllOpenDefects');"><option selected="selected" value="0">On-going</option>
        </select></td>

    </tr>
</tbody></table>
<p></p>
<table border="0" cellpadding="1" cellspacing="0" width="100%">
    <colgroup>
        <col width="15%">
        <col width="18%">
        <col width="15%">
        <col width="18%">
        <col width="15%">
        <col align="right" width="19%">
    </colgroup><tbody><tr>
		<td><b>Defect Owner</b></td>
        <td><select name="ListingDefectOwner" class="verySmallCombo"><option selected="selected" value="(All)">(All)</option><option value="(None)">(None)</option><option value="SYSADMIN">SYSADMIN</option><option value="THACHLN">THACHLN</option>
        </select></td>
        <td><b>Status</b></td>
        <td><select name="ListingStatus" class="verySmallCombo"><option selected="selected" value="-1">(All)</option><option value="1">1-Error</option><option value="2">2-Assigned</option><option value="3">3-Pending</option><option value="4">4-Tested</option><option value="5">5-Accepted</option><option value="6">6-Cancelled</option>
        </select></td>
        <td><b>Module Code</b></td>
        <td><select class="SmallCombo" name="ListingModuleCode"><option selected="selected" value="-1">(All)</option><option value="0">(None)</option><option value="171165">HTML Prototype</option><option value="171161">Report 1</option><option value="171167">Report 2</option><option value="171169">Report 3</option><option value="171171">Report 4</option><option value="171173">Report 5</option><option value="171175">Report 6</option><option value="171179">Software Design Description</option><option value="171177">Software Project Management Plan</option><option value="171163">Software Requirement Specification</option><option value="171181">Software Test Documentation</option><option value="171183">Software User's Manual</option><option value="171185">Software package</option>
        </select></td>
    </tr>
    <tr>
        <td><b>Assigned To</b></td>
        <td><select name="ListingAssignto" class="verySmallCombo"><option selected="selected" value="(All)">(All)</option><option value="(None)">(None)</option><option value="SYSADMIN">SYSADMIN</option><option value="THACHLN">THACHLN</option>
        </select></td>
        <td><b>Severity</b></td>
        <td><select name="ListingSeverity" class="verySmallCombo"><option selected="selected" value="-1">(All)</option><option value="1">1-Fatal</option><option value="2">2-Serious</option><option value="3">3-Medium</option><option value="4">4-Cosmetic</option>
        </select></td>
        <td><b>Type</b></td>
        <td><select name="ListingType" class="SmallCombo"><option selected="selected" value="-1">(All)</option><option value="1">01-Functionality (Other)</option><option value="12">011-Req misunderstanding</option><option value="13">012-Feature missing</option><option value="14">013-Coding logic</option><option value="15">014-Business logic</option><option value="2">02-User Interface</option><option value="3">03-Performance</option><option value="4">04-Design issue</option><option value="5">05-Coding standard</option><option value="6">06-Document</option><option value="7">07-Data &amp; Database integrity</option><option value="8">08-Security &amp; Access Control</option><option value="9">09-Portability</option><option value="10">10-Other</option><option value="11">11-Tools</option>
        </select></td>
    </tr>
    <tr>
        <td><b>Created By</b></td>
        <td><select name="ListingCreatedBy" class="verySmallCombo"><option selected="selected" value="(All)">(All)</option><option value="(None)">(None)</option><option value="SYSADMIN">SYSADMIN</option><option value="THACHLN">THACHLN</option>
        </select></td>
        <td><b>Reference</b></td>
        <td><select name="Reference" class="verySmallCombo"><option selected="selected" value="-1">(All)</option><option value="0">(None)</option>
        </select></td>
        <td><b>Defect Origin</b></td>
        <td><select name="ListingDefectOrigin" class="SmallCombo"><option selected="selected" value="-1">(All)</option><option value="23">Administration</option><option value="4">Coding</option><option value="21">Collaborator Management</option><option value="8">Configuration Management</option><option value="1">Contract management</option><option value="14">Correction</option><option value="6">Customer Support</option><option value="5">Deployment</option><option value="3">Design</option><option value="18">Document Control</option><option value="24">Facility Management</option><option value="19">IS Management</option><option value="13">Internal Audit</option><option value="15">Management Review</option><option value="26">Other</option><option value="28">Prevention</option><option value="29">Process Improvement</option><option value="9">Project Management</option><option value="12">Quality Control</option><option value="10">Quality Planning</option><option value="16">Recruitment</option><option value="2">Requirement</option><option value="25">Retirement</option><option value="20">Staff Management</option><option value="22">Student Management</option><option value="11">Subcontract Management</option><option value="27">Technology management</option><option value="7">Test</option><option value="17">Training</option>
        </select></td>
    </tr>
    <tr>
       <td><b>Stage Detected</b></td>
        <td><select name="ListingStageDetected" class="verySmallCombo"><option selected="selected" value="-1">(All)</option><option value="0">(None)</option><option value="1">1-Initiation</option><option value="2">2-Definition</option><option value="3">3-Solution</option><option value="4">4-Construction</option><option value="5">5-Transition</option><option value="6">6-Termination</option><option value="15">Definition&amp;Solution</option><option value="13">Initiation&amp;Definition</option><option value="7">Release 1</option><option value="8">Release 2</option><option value="9">Release 3</option><option value="10">Release 4</option><option value="11">Release 5</option><option value="12">Release 6</option><option value="14">Solution&amp;Construction</option>
        </select></td>
        <td><b>Stage Injected</b></td>
        <td><select name="ListingStageInjected" class="verySmallCombo"><option selected="selected" value="-1">(All)</option><option value="0">(None)</option><option value="1">1-Initiation</option><option value="2">2-Definition</option><option value="3">3-Solution</option><option value="4">4-Construction</option><option value="5">5-Transition</option><option value="6">6-Termination</option><option value="15">Definition&amp;Solution</option><option value="13">Initiation&amp;Definition</option><option value="7">Release 1</option><option value="8">Release 2</option><option value="9">Release 3</option><option value="10">Release 4</option><option value="11">Release 5</option><option value="12">Release 6</option><option value="14">Solution&amp;Construction</option>
        </select></td>
        <td><b>QC Activity</b></td>
        <td><select name="ListingQCActivity" class="SmallCombo"><option selected="selected" value="-1">(All)</option><option value="10">10-Unit test</option><option value="11">11-Integration test</option><option value="12">12-System test</option><option value="13">13-Acceptance test</option><option value="14">14-Regression test</option><option value="15">15-After Release test</option><option value="16">16-Other test</option><option value="17">17-Prototype test</option><option value="20">20-Document review</option><option value="21">21-Code review</option><option value="22">22-After Release review</option><option value="23">23-Prototype review</option><option value="24">24-Other review</option><option value="30">30-Quality Gate inspection</option><option value="31">31-Final inspection</option><option value="32">32-Other inspection</option><option value="33">33-UT Inspection</option><option value="40">40-Baseline audit</option><option value="41">41-Other audit</option>
        </select></td>
    </tr>
    <tr>
    	<td><b>Created From </b></td>
        <td>
            <input name="txtFromDate" class="DateBox" maxlength="8" type="text">
            <img src="resource_files/cal.gif" style="" onclick='showCalendar(document.forms[0].txtFromDate, document.forms[0].txtFromDate, "mm/dd/yy",null,1,-1,-1,true)'>
        </td>
        <td><b>Created To </b></td>
        <td>
            <input name="txtToDate" class="DateBox" maxlength="8" type="text">
            <img src="resource_files/cal.gif" style="" onclick='showCalendar(document.forms[0].txtToDate, document.forms[0].txtToDate, "mm/dd/yy",null,1,-1,-1,true)'>
        </td>
        <td><b>Work Product</b></td>
        <td><select name="ListingWorkProduct" class="SmallCombo"><option selected="selected" value="-1">(All)</option><option value="10">Acceptance note</option><option value="5">Architectural design</option><option value="46">Audit program</option><option value="45">Audit record</option><option value="36">Audit report</option><option value="78">Baseline Audit</option><option value="27">Baseline report</option><option value="30">CM Plan</option><option value="31">Change request</option><option value="43">Coding convention</option><option value="32">Configuration Status Report</option><option value="11">Contract</option><option value="33">Customer Satisfaction Survey</option><option value="79">DAR Report</option><option value="80">DP Plan</option><option value="58">DP report</option><option value="62">Database</option><option value="63">Deployment package</option><option value="42">Design prototype</option><option value="8">Detailed design</option><option value="81">Final inspection</option><option value="59">Handover note</option><option value="47">IP</option><option value="48">IP database</option><option value="29">Installation manual</option><option value="69">Integration test case</option><option value="6">Integration test plan</option><option value="13">Integration test report</option><option value="22">Meeting minutes</option><option value="20">Others</option><option value="39">PCB</option><option value="37">PQA report</option><option value="49">Pilot plan</option><option value="50">Pilot record</option><option value="51">Pilot report</option><option value="52">Plan</option><option value="40">Process assets</option><option value="41">Process database</option><option value="53">Program</option><option value="28">Project assets</option><option value="24">Project database</option><option value="4">Project plan</option><option value="23">Project record</option><option value="12">Project report</option><option value="25">Proposal</option><option value="82">Prototype</option><option value="83">QA report</option><option value="84">QA review record</option><option value="17">QDS</option><option value="66">Quality control report</option><option value="67">Quality gate record</option><option value="54">Record</option><option value="14">Release note</option><option value="55">Report</option><option value="21">Requirement prototype</option><option value="35">Resource and environment</option><option value="77">Review TestCase</option><option value="26">Review record</option><option value="38">SQA report</option><option value="3">SRS</option><option value="60">Service Level Agreement</option><option value="44">Software module</option><option value="9">Software package</option><option value="34">Support Diary</option><option value="16">System description</option><option value="7">System test case</option><option value="76">System test plan</option><option value="74">System test report</option><option value="71">Test data</option><option value="85">Test plan</option><option value="72">Test script</option><option value="61">Traceability matrix</option><option value="19">Training course</option><option value="56">Training material</option><option value="64">Training records</option><option value="2">URD</option><option value="68">Unit test case</option><option value="75">Unit test plan</option><option value="73">Unit test report</option><option value="18">Use case</option><option value="15">User manual</option><option value="1">WO</option>
        </select></td>
    </tr>
    <tr>
        <td><b>Fixed From </b></td>
        <td>
            <input name="txtFixedFrom" class="DateBox" maxlength="8" type="text">
            <img src="resource_files/cal.gif" style="" onclick='showCalendar(document.forms[0].txtFixedFrom, document.forms[0].txtFixedFrom, "mm/dd/yy",null,1,-1,-1,true)'>
        </td>
        <td><b>Fixed To </b></td>
        <td>
            <input name="txtFixedTo" class="DateBox" maxlength="8" type="text">
            <img src="resource_files/cal.gif" style="" onclick='showCalendar(document.forms[0].txtFixedTo, document.forms[0].txtFixedTo, "mm/dd/yy",null,1,-1,-1,true)'>
        </td>
        <td><b>Priority</b></td>
        <td><select name="ListingPriority" class="SmallCombo"> <option selected="selected" value="-1">(All)</option><option value="0">(None)</option><option value="1">1-Immediately</option><option value="2">2-High</option><option value="3">3-Normal</option><option value="4">4-Low</option>
		</select></td>
    </tr>
    <tr>
         <td><b>DefectID </b></td>
         <td><input name="txtDefectID" maxlength="15" type="text"></td>	
         <td><b>Test Case ID</b></td>
         <td><input name="txtLstTestCase" title="Full text search" maxlength="45" type="text"></td>
         <td><b>Title </b></td>
         <td><input name="txtLstTitle" title="Full text search" class="SmallBox" maxlength="20" type="text"></td>
    </tr>
    <tr>
        <td colspan="5"><font color="blue">Date format: mm/dd/yy</font></td>
        <td><input name="btnSearchDefect" class="button" onclick="javascript:doSearch()" value="Search" type="button"></td>
    </tr>
</tbody></table>
<p></p>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody><tr>
        <td height="10" valign="bottom">
        <b>Result <font color="red" size="-1">1 - 1</font> of
        <font color="red" size="-1">1</font> defects in
        <font color="red" size="-1">1</font> pages</b> 
        </td>
        <td align="right" height="10" valign="top">
    </td></tr>
</tbody></table>
<table bgcolor="#000000" border="0" cellpadding="1" cellspacing="1" width="100%">
    <colgroup>
        <col width="3%">
        <col width="8%">
        <col width="29%">
        <col width="8%">
        <col width="8%">
        <col width="11%">
        <col width="8%">
        <col width="6%">
        <col width="8%">
        <col width="8%">
        <col width="8%">
    </colgroup><tbody><tr class="Row0">
        <td align="center" valign="middle"><input name="allbox" value="CheckAll" onclick="JavaScript: CheckAll(document.forms[0]);" type="checkbox"></td>
        <td align="center" valign="middle"><a href="javascript:doSort('DefectID')"><span style="color: white">DefectID</span></a>

        </td>
        <td align="center" valign="middle"><a href="javascript:doSort('Title')"><span style="color: white">Title</span></a>

        </td>
        <td align="center" valign="middle"><a href="javascript:doSort('TestCase')"><span style="color: white">Test Case ID</span></a>

        </td>

        <td align="center" valign="middle"><a href="javascript:doSort('Severity')"><span style="color: white">Severity</span></a>

        </td>
        <td align="center" valign="middle"><a href="javascript:doSort('Priority')"><span style="color: white">Priority</span></a>

        </td>
        <td align="center" valign="middle"><a href="javascript:doSort('Status')"><span style="color: white">Status</span></a>

        </td>
             <td align="center" valign="middle"><a href="javascript:doSort('DefectOwner')"><span style="color: white">Defect Owner</span></a>

        </td>
        <td align="center" valign="middle"><a href="javascript:doSort('AssignTo')"><span style="color: white">Assigned To</span></a>

        </td>
        <td align="center" valign="middle"><a href="javascript:doSort('CreatedBy')"><span style="color: white">Created By</span></a>

        </td>
        <td align="center" valign="middle"><a href="javascript:doSort('FixedDate')"><span style="color: white">Fixed Date</span></a>

        </td>
        <td align="center" valign="middle"><a href="javascript:doSort('DueDate')"><span style="color: white">Due Date</span></a>

        </td>
    </tr>
    <tr class="Row2">
        <td align="center"><input name="selected" value="522415" type="checkbox"></td>
        <td align="center"><a href="http://localhost:8080/dms/DMSServlet?hidAction=DM&amp;hidActionDetail=UpdateDefect&amp;hidUpdateDefect=522415&amp;ProjectID=118385">522415</a></td>
        <td>These is no description flow of use cases</td>
        <td></td>
        <td>3-Medium</td>
        <td>2-High</td>
        <td>1-Error</td>
        <td></td>
        <td></td>
        <td>SYSADMIN</td>
        <td align="center"></td>
        <td align="center">06/06/12</td>
    </tr>
</tbody></table>
<br>
<table border="0" cellpadding="0" cellspacing="1" width="100%">
    <tbody><tr>
        <td align="left" width="50%">
        <input name="AddnewDefect" class="Button" onclick='submitAction("${portletNamespace}Search", "${goAddNewDefect}")' value="Add New" type="button">
        <input name="BatchUpdateDefect" class="Button" onclick="javascript:doBatchUpdate()" value="Batch Update" type="button">
        <input name="Refresh" class="Button" onclick="javascript:doRefresh()" value="Refresh" type="button">
        <input name="ExportDefect" class="Button" onclick="javascript:doExport()" value="Export" type="button">
    </td></tr>
    <tr>
        <td></td>
    </tr>
    <tr>
        <td align="left" width="50%">Move defect(s) to project&nbsp; <select name="cboProject" class="SmallCombo">
            <option selected="selected" value="118385">OOPMS</option>
        </select>&nbsp;<input name="MoveDefect" class="Button" onclick="javascript:doMoveDefect()" value="Move Defect" type="button"></td>
    </tr>
</tbody></table>
</form:form>

</body></html>