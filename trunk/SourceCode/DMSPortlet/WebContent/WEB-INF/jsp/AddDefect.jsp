<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="resource_files/CommonScript.js"></script>
<script type="text/javascript" src="resource_files/utils.js"></script>
<script type="text/javascript" src="resource_files/popcalendar.js"></script></head><body topmargin="0" leftmargin="0" bgcolor="#FFFFFF"><div onclick="bShow=true" id="calendar" style="z-index:+999;position:absolute;visibility:hidden;"><table class="pcalTbl" width="250"><tbody><tr bgcolor="#000066"><td><table width="248"><tbody><tr><td class="pcalCaption"><font color="#ffffff"><b><span id="caption"><span id="spanLeft" class="pcalSelBtn" onmouseover="swapImage('changeLeft','pcalleft2.gif');this.style.borderColor='#8af';window.status='Click to scroll to previous month. Hold mouse button to scroll automatically.'" onclick="decMonth()" onmouseout="clearInterval(intervalID1);swapImage('changeLeft','pcalleft1.gif');this.style.borderColor='#36f';window.status=''" onmousedown="clearTimeout(timeoutID1);timeoutID1=setTimeout('StartDecMonth()',500)" onmouseup="clearTimeout(timeoutID1);clearInterval(intervalID1)">&nbsp;<img id="changeLeft" src="resource_files/pcalleft1.gif" height="11" border="0" width="10"></span>&nbsp;<span id="spanRight" class="pcalSelBtn" onmouseover="swapImage('changeRight','pcalright2.gif');this.style.borderColor='#8af';window.status='Click to scroll to next month. Hold mouse button to scroll automatically.'" onmouseout="clearInterval(intervalID1);swapImage('changeRight','pcalright1.gif');this.style.borderColor='#36f';window.status=''" onclick="incMonth()" onmousedown="clearTimeout(timeoutID1);timeoutID1=setTimeout('StartIncMonth()',500)" onmouseup="clearTimeout(timeoutID1);clearInterval(intervalID1)">&nbsp;<img id="changeRight" src="resource_files/pcalright1.gif" height="11" border="0" width="10"></span>&nbsp;<span id="spanMonth" class="pcalSelBtn" onmouseover="swapImage('changeMonth','pcaldrop2.gif');this.style.borderColor='#8af';window.status='Click to select a month.'" onmouseout="swapImage('changeMonth','pcaldrop1.gif');this.style.borderColor='#36f';window.status=''" onclick="popUpMonth()"></span>&nbsp;<span id="spanYear" class="pcalSelBtn" onmouseover="swapImage('changeYear','pcaldrop2.gif');this.style.borderColor='#8af';window.status='Click to select a year.'" onmouseout="swapImage('changeYear','pcaldrop1.gif');this.style.borderColor='#36f';window.status=''" onclick="popUpYear()"></span>&nbsp;</span></b></font></td><td align="right"><a href="javascript:hideCalendar()"><img src="resource_files/pcalclose.gif" height="13" border="0" width="15"></a></td></tr></tbody></table></td></tr><tr><td style="padding: 5px;" bgcolor="#ffffff"><span id="content"></span></td></tr><tr bgcolor="#f0f0f0"><td style="padding: 5px;" align="center"><span id="lblToday"><font color="#000066">Today is <a onmousemove="window.status='Go To Current Month'" onmouseout="window.status=''" title="Go To Current Month" style="text-decoration: none; color: black;" href="javascript:monthSelected=monthNow;yearSelected=yearNow;constructCalendar();">Tue, 5 Jun 2012</a></font></span></td></tr></tbody></table></div><div id="selectMonth" style="z-index:+999;position:absolute;visibility:hidden;"></div><div id="selectYear" style="z-index:+999;position:absolute;visibility:hidden;"></div>
<title>Add New Defect</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="StyleSheet" href="/<spring:message code="app.context"/>/resource_files/DMSStyleSheet.css" type="text/css">
<link rel="StyleSheet" href="/<spring:message code="app.context"/>/resource_files/pcal.css" type="text/css">
<script language="javascript">
function doSave() {
    var form = document.frmDefectAdd;
    document.forms[0].NewDefect.disabled = true;
    if (!isValidForm()) {
        document.forms[0].NewDefect.disabled = false;
        return;
    }
    form.hidAction.value = "DM";
    form.hidActionDetail.value = "SaveNewDefect";
    form.action = "DMSServlet";
    form.hidSaveNewCounter.value = parseInt(form.hidSaveNewCounter.value) + 1;  // Validator for check user refresh button
    form.submit();
}

function doBack() {
    var form = document.frmDefectAdd;
    form.hidAction.value = "DM";
    form.hidActionDetail.value = "SearchDefect";
    form.action = "DMSServlet";
    form.submit();
}

function doQueryListing() {
    var form = document.frmDefectAdd;
    form.hidAction.value = "DM";
    form.hidActionDetail.value = "QueryListing";
    form.action = "DMSServlet";
    form.submit();
}

function doAttach() {
    var form = document.forms[0];
    form.hidAction.value = "DM";
    form.hidActionDetail.value = "AttachNewForm";
    form.action = "DMSServlet";
    form.submit();
}

function showTempFile(nIndex) {
    var form = document.forms[0];
    showAttachFile(nIndex, form, "AttachFileView");
}

function removeTempFile(nIndex) {
    if (confirm("Do you want to remove this temporary attach file?")) {
        var form = document.forms[0];
        removeAttachFile(nIndex, form, "AttachFileRemoveAdd");
    }
}

function isValidForm() {
    var count;
    var form = document.frmDefectAdd;
    for (count = 0x00; count < document.forms[0].length; count++) {
        if (!isValidControl(document.forms[0].elements[count])) {
            return false;
        }
    }
    if ((form.DueDate.value.length > 0)) {
        if (CompareDate(form.DueDate , form.CreateDate) < 0) {
            alert("Due Date must greater than or equals to Create Date");
            form.DueDate.focus();
            return false;
        }
    }
    return true;
}

function isValidControl(control) {
    if (control.name == "CreateDate") {
        if (isDate(control) && isLessThanCurDate(control)) {
            return true;
        }
        else {
            return false;
        }
    }
    else if (control.name == "DueDate") {
        if (control.value.length <= 0 || isDate(control)) {
            return true;
        }
        else {
            return false;
        }
    }
    else if (control.name == "Title") {
        return textValidate(control, 1, 150);
    }
    else if (control.name == "WorkProduct") {
        return isPositiveNumberCombobox(control);
    }
    else if (control.name == "Severity") {
        return isPositiveNumberCombobox(control);
    }
    else if (control.name == "TypeofActivity"){
        return isPositiveNumberCombobox(control);
    }
    else if (control.name == "QCActivity") {
        return isPositiveNumberCombobox(control);
    }
    else if (control.name == "Type") {
        return isPositiveNumberCombobox(control);
    }
    else if (control.name == "DefectOrigin") {
        return isPositiveNumberCombobox(control);
    }
    else if (control.name == "txtDescription") {
        control.name = "Description";
        if (textValidate(control, 1, 1200)) {
            control.name = "txtDescription";
            return true;
        }
        else {
            control.name = "txtDescription";
            return false;
        }
    }
    else if (control.name == "txtCorrectiveAction") {
        control.name = "Corrective Actions";
        if (textValidate(control, 0, 1200)) {
            control.name = "txtCorrectiveAction";
            return true;
        }
        else {
            control.name = "txtCorrectiveAction";
            return false;
        }
    }
    return true;
}
//modified by MinhPT
//09-Dec-03
//for fix TypeOfAcitvity when select QCActivity
function ChangeTypeActivity() {
    var form = document.forms[0];
    var QCActivity = form.QCActivity.value;
    var check = QCActivity.substring(0,1);

    while (form.TypeofActivity.options.length > 0) {
        form.TypeofActivity.options[0] = null;
    }

    switch (check) {
        case "1": //Test
            appendOption(form.TypeofActivity, 1, 'Test', true);
            break;
        case "2": //Review
            appendOption(form.TypeofActivity, 2, 'Review', true);
            break;
        case "3": //Inspection
            appendOption(form.TypeofActivity, 3, 'Inspection', true);
            break;
        case "4": //Audit
            appendOption(form.TypeofActivity, 4, 'Audit', true);
            break;
    }
}

//added by MinhPT
//06-Dec-03
var nNumberOfModule = 14;
var arrModuleID = new Array(14);
var arrModuleName = new Array(14);
var arrModuleWPID = new Array(14);
arrModuleID[0] = "0";
arrModuleName[0] = "";
arrModuleWPID[0] = "";
arrModuleID[1] = "171165";
arrModuleName[1] = "HTML Prototype";
arrModuleWPID[1] = "82";
arrModuleID[2] = "171161";
arrModuleName[2] = "Report 1";
arrModuleWPID[2] = "55";
arrModuleID[3] = "171167";
arrModuleName[3] = "Report 2";
arrModuleWPID[3] = "55";
arrModuleID[4] = "171169";
arrModuleName[4] = "Report 3";
arrModuleWPID[4] = "55";
arrModuleID[5] = "171171";
arrModuleName[5] = "Report 4";
arrModuleWPID[5] = "55";
arrModuleID[6] = "171173";
arrModuleName[6] = "Report 5";
arrModuleWPID[6] = "55";
arrModuleID[7] = "171175";
arrModuleName[7] = "Report 6";
arrModuleWPID[7] = "55";
arrModuleID[8] = "171179";
arrModuleName[8] = "Software Design Description";
arrModuleWPID[8] = "8";
arrModuleID[9] = "171177";
arrModuleName[9] = "Software Project Management Plan";
arrModuleWPID[9] = "52";
arrModuleID[10] = "171163";
arrModuleName[10] = "Software Requirement Specification";
arrModuleWPID[10] = "3";
arrModuleID[11] = "171181";
arrModuleName[11] = "Software Test Documentation";
arrModuleWPID[11] = "13";
arrModuleID[12] = "171183";
arrModuleName[12] = "Software User's Manual";
arrModuleWPID[12] = "15";
arrModuleID[13] = "171185";
arrModuleName[13] = "Software package";
arrModuleWPID[13] = "9";

//added by MinhPT
//06-Dec-03
//Corrected by TrungTN
function selectWorkProduct(){
    var form = document.forms[0];
    var i;
    while (form.ModuleCode.options.length > 0) {
        form.ModuleCode.options[0] = null;
    }
    appendOption(form.ModuleCode, "0", "", false);
    
    currentWPID = form.cboWorkProduct.value;
    for (i = 0; i < nNumberOfModule; i++) {
        if (arrModuleWPID[i] == currentWPID) {
            if (arrModuleID[i] == '0') {
                appendOption(form.ModuleCode, arrModuleID[i], arrModuleName[i], true);
            }
            else {
                appendOption(form.ModuleCode, arrModuleID[i], arrModuleName[i], false);
		    }
        }
    }
    if (form.ModuleCode.options.length == 1) {
        // Important when combo have only one empty element
        form.ModuleCode.options[0].selected=true;
    }
}
</script>

<script type="text/javascript" src='/<spring:message code="app.context"/>/scripts/common.js'></script>
<%@ include file="/WEB-INF/jsp/menu.jsp" %>
<portlet:actionURL var="formAction"><portlet:param name="action" value="save"/></portlet:actionURL>
<portlet:renderURL var="goViewDefectList2"><portlet:param name="action" value="goViewDefectList2"/></portlet:renderURL>

<form:form name="${portletNamespace}AddDefect" commandName="defectForm" method="post" action="${formAction}">
<div><img src="/<spring:message code="app.context"/>/resource_files/DefectAddnew.gif" height="28" border="0" width="411"></div>
<input name="hidActionDetail" value="" type="hidden">
<input name="hidAction" value="" type="hidden">
<input name="CheckRole" value="1110000000" type="hidden">
<input name="hidTypeOfView" value="ViewAllOpenDefects" type="hidden">
<input name="UserName" value="System Administrator" type="hidden">
<input name="Account" value="SYSADMIN" type="hidden">
<input name="Role" value="1000100000" type="hidden">
<input name="Position" value="1110000000" type="hidden">
<input name="ProjectCode" value="OOPMS" type="hidden">
<input name="DateLogin" value="06/05/12" type="hidden">
<input name="ProjectID" value="118385" type="hidden">
<input name="hidFileIndex" value="" type="hidden">
<input name="hidSaveNewCounter" value="0" type="hidden">


<table border="0" width="100%">
    <tbody><tr>
        <td align="right"><a href="javascript:doQueryListing()">View DefectListing</a></td>
    </tr>
</tbody></table>
<%@ include file="/WEB-INF/jsp/header2Defect.jsp" %>
<br>
<%--Display errors --%>
<font color="red"><form:errors path="*"></form:errors></font>

<table class="TblOut" border="0" cellpadding="1" cellspacing="1" width="100%">
    <tbody><tr class="Row0">
        <td align="center" width="100%"><font size="+1">Main</font></td>
    </tr>
</tbody></table>
<table border="0" cellpadding="1" cellspacing="0" width="100%">
    <colgroup>
        <col width="15%">
        <col width="45%">
        <col width="5%">
        <col width="10%">
        <col width="30%">
        </colgroup><input name="DefectID" value="0" type="hidden">
    <tbody><tr>
        <td align="left" nowrap="nowrap" valign="middle"><b><font color="black">Status:&nbsp; Error</font></b> <input name="hidStatus" value="1" type="hidden"></td>
        <td align="left" valign="middle"><b><font color="black">Created user:</font></b>&nbsp;<b>SYSADMIN</b></td>
        <input name="CreateUser" value="SYSADMIN" type="hidden">
        <td>&nbsp;</td>
        <!-- Created Date  -->
        <td id="idCreatedDate" align="left" nowrap="nowrap" valign="middle"><b><font color="black">Created date</font></b><font color="red">&nbsp;*</font></td>
        <td align="left" valign="middle">
            <input style="font-family: Arial; font-size: 8pt; width: 45pt;" name="CreateDate" value="06/05/12" maxlength="8" type="text">
            <img src="/<spring:message code="app.context"/>/resource_files/cal.gif" style="" onclick='showCalendar(document.getElementById("idCreatedDate"), document.forms[0].CreateDate, "mm/dd/yy",null,1,-1,-1,true)'>
            <font color="blue">&nbsp;(mm/dd/yy)</font>
        </td>
    </tr>
    <tr>
        <!-- Title -->
        <td align="left" valign="middle"><b><font color="black">Title</font></b><font color="red">&nbsp;*</font></td>
        <td align="left" valign="middle">
        <%-- 
        <input class="LongBox" maxlength="150" name="Title" value="These is no description flow of use cases" type="text">
        --%>
        <form:input path="title" maxlength="255"/>
        </td>
        <!-- Project Origin -->
        <td>&nbsp;</td>
        <td><b><font color="black">Project Origin</font></b></td>
        <td><form:input path="projectOrigin" maxlength="50"/></td>
    </tr>
    <tr>
        <!-- Description -->
        <td align="left" valign="middle"><b><font color="black">Description</font></b><font color="red">&nbsp;*</font></td>
        <td valign="middle">
        <%--
        <textarea class="LargeTextarea" rows="3" wrap="virtual" name="txtDescription" onchange="Description.value=txtDescription.value">It's better if add more a section to describe the flow of use cases: using graph or matrix</textarea> <input name="Description" value="It's better if add more a section to describe the flow of use cases: using graph or matrix" type="hidden">
         --%>
         <form:textarea path="description" rows="3"/>
        </td>
        <td>&nbsp;</td>
        <!-- Reference -->
        <td valign="top"><b><font color="black">Reference</font></b></td>
        <td valign="top"><form:input path="ref" maxlength="30"/></td>
    </tr>
    <tr>
        <!-- QC Activity -->
        <td align="left" valign="middle"><font color="black"><b>QC activity</b></font><font color="red">&nbsp;*</font></td>
        <td align="left" valign="middle">
        <%--
        <select name="QCActivity" class="SmallCombo" onchange="ChangeTypeActivity()"><option value="10">10-Unit test</option><option value="11">11-Integration test</option><option value="12">12-System test</option><option value="13">13-Acceptance test</option><option value="14">14-Regression test</option><option value="15">15-After Release test</option><option value="16">16-Other test</option><option value="17">17-Prototype test</option><option selected="selected" value="20">20-Document review</option><option value="21">21-Code review</option><option value="22">22-After Release review</option><option value="23">23-Prototype review</option><option value="24">24-Other review</option><option value="30">30-Quality Gate inspection</option><option value="31">31-Final inspection</option><option value="32">32-Other inspection</option><option value="33">33-UT Inspection</option><option value="40">40-Baseline audit</option><option value="41">41-Other audit</option>
        </select>
         --%>
        <form:select path="qcActivity" multiple="false" size="1" items="${qcActivity}">
        </form:select>
        
        </td>
        <td>&nbsp;</td>
        <!-- Defect Origin  -->
        <td align="left" valign="middle"><font color="black"><b>Defect origin</b></font><font color="red">&nbsp;*</font></td>
        <td align="left" valign="middle"><select name="DefectOrigin" class="SmallCombo"><option value="23">Administration</option><option value="4">Coding</option><option value="21">Collaborator Management</option><option value="8">Configuration Management</option><option value="1">Contract management</option><option value="14">Correction</option><option value="6">Customer Support</option><option value="5">Deployment</option><option value="3">Design</option><option value="18">Document Control</option><option value="24">Facility Management</option><option value="19">IS Management</option><option value="13">Internal Audit</option><option value="15">Management Review</option><option value="26">Other</option><option value="28">Prevention</option><option value="29">Process Improvement</option><option value="9">Project Management</option><option value="12">Quality Control</option><option value="10">Quality Planning</option><option value="16">Recruitment</option><option selected="selected" value="2">Requirement</option><option value="25">Retirement</option><option value="20">Staff Management</option><option value="22">Student Management</option><option value="11">Subcontract Management</option><option value="27">Technology management</option><option value="7">Test</option><option value="17">Training</option>
        </select></td>
    </tr>
    <tr>
        <!-- Type of Activity -->
        <td align="left" valign="middle"><b><font color="black">Type of activity</font></b><font color="red">&nbsp;*</font></td>
        <td align="left" valign="middle"><select name="TypeofActivity" class="SmallCombo">
        <option selected="selected" value="2">Review</option></select></td>
        <td>&nbsp;</td>
        <!-- Stage injected -->
        <td align="left" valign="middle"><b><font color="black">Stage injected&nbsp;</font></b></td>
        <td align="left" valign="middle"><select name="StageInjected" class="SmallCombo"><option selected="selected" value="0"></option><option value="1">1-Initiation</option><option value="2">2-Definition</option><option value="3">3-Solution</option><option value="4">4-Construction</option><option value="5">5-Transition</option><option value="6">6-Termination</option><option value="15">Definition&amp;Solution</option><option value="13">Initiation&amp;Definition</option><option value="7">Release 1</option><option value="8">Release 2</option><option value="9">Release 3</option><option value="10">Release 4</option><option value="11">Release 5</option><option value="12">Release 6</option><option value="14">Solution&amp;Construction</option>
        </select></td>
    </tr>
    <tr>
        <!-- Stage detected -->
        <td align="left" nowrap="nowrap" valign="middle"><b><font color="black">Stage detected</font></b></td>
        <td align="left" valign="middle"><select name="StageDetected" class="SmallCombo"><option selected="selected" value="0"></option><option value="1">1-Initiation</option><option value="2">2-Definition</option><option value="3">3-Solution</option><option value="4">4-Construction</option><option value="5">5-Transition</option><option value="6">6-Termination</option><option value="15">Definition&amp;Solution</option><option value="13">Initiation&amp;Definition</option><option value="7">Release 1</option><option value="8">Release 2</option><option value="9">Release 3</option><option value="10">Release 4</option><option value="11">Release 5</option><option value="12">Release 6</option><option value="14">Solution&amp;Construction</option>
        </select></td>
        <td>&nbsp;</td>
        <!-- Priority -->
        <td align="left" valign="middle"><b><font color="black">Priority</font></b></td>
        <td align="left" valign="middle"><select name="Priority" class="SmallCombo"><option value="0"></option><option value="1">1-Immediately</option><option selected="selected" value="2">2-High</option><option value="3">3-Normal</option><option value="4">4-Low</option>
        </select></td>
    </tr>
    <tr>
        <!-- Work Product COMBO -->
        <td align="left" valign="middle"><b><font color="black">Work product</font></b><font color="red">&nbsp;*</font></td>
        <td align="left" valign="middle"><select name="cboWorkProduct" class="SmallCombo" onchange="javascript:selectWorkProduct();"><option value="10">Acceptance note</option><option value="5">Architectural design</option><option value="46">Audit program</option><option value="45">Audit record</option><option value="36">Audit report</option><option value="78">Baseline Audit</option><option value="27">Baseline report</option><option value="30">CM Plan</option><option value="31">Change request</option><option value="43">Coding convention</option><option value="32">Configuration Status Report</option><option value="11">Contract</option><option value="33">Customer Satisfaction Survey</option><option value="79">DAR Report</option><option value="80">DP Plan</option><option value="58">DP report</option><option value="62">Database</option><option value="63">Deployment package</option><option value="42">Design prototype</option><option value="8">Detailed design</option><option value="81">Final inspection</option><option value="59">Handover note</option><option value="47">IP</option><option value="48">IP database</option><option value="29">Installation manual</option><option value="69">Integration test case</option><option value="6">Integration test plan</option><option value="13">Integration test report</option><option value="22">Meeting minutes</option><option value="20">Others</option><option value="39">PCB</option><option value="37">PQA report</option><option value="49">Pilot plan</option><option value="50">Pilot record</option><option value="51">Pilot report</option><option value="52">Plan</option><option value="40">Process assets</option><option value="41">Process database</option><option value="53">Program</option><option value="28">Project assets</option><option value="24">Project database</option><option value="4">Project plan</option><option value="23">Project record</option><option value="12">Project report</option><option value="25">Proposal</option><option value="82">Prototype</option><option value="83">QA report</option><option value="84">QA review record</option><option value="17">QDS</option><option value="66">Quality control report</option><option value="67">Quality gate record</option><option value="54">Record</option><option value="14">Release note</option><option value="55">Report</option><option value="21">Requirement prototype</option><option value="35">Resource and environment</option><option value="77">Review TestCase</option><option value="26">Review record</option><option value="38">SQA report</option><option selected="selected" value="3">SRS</option><option value="60">Service Level Agreement</option><option value="44">Software module</option><option value="9">Software package</option><option value="34">Support Diary</option><option value="16">System description</option><option value="7">System test case</option><option value="76">System test plan</option><option value="74">System test report</option><option value="71">Test data</option><option value="85">Test plan</option><option value="72">Test script</option><option value="61">Traceability matrix</option><option value="19">Training course</option><option value="56">Training material</option><option value="64">Training records</option><option value="2">URD</option><option value="68">Unit test case</option><option value="75">Unit test plan</option><option value="73">Unit test report</option><option value="18">Use case</option><option value="15">User manual</option><option value="1">WO</option>
        </select></td>
        <td>&nbsp;</td>
        <!-- Severity -->
        <td align="left" valign="middle"><b><font color="black">Severity</font></b><font color="red">&nbsp;*</font></td>
        <td align="left" valign="middle"><select name="Severity" class="SmallCombo"><option value="1">1-Fatal</option><option value="2">2-Serious</option><option selected="selected" value="3">3-Medium</option><option value="4">4-Cosmetic</option>
        </select></td>
    </tr>
    <tr>
        <!-- Module Code -->
        <td align="left" valign="middle"><b><font color="black">Module</font></b></td>
        <td align="left" valign="middle"><select name="ModuleCode" class="LongCombo">
        <option value="0"></option><option selected="selected" value="171163">Software Requirement Specification</option></select></td>
        <td>&nbsp;</td>
        <!-- Type -->
        <td align="left" valign="middle"><b><font color="black">Type</font></b><font color="red">&nbsp;*</font></td>
        <td align="left" valign="middle"><select name="Type" class="SmallCombo"><option value="0"></option><option value="1">01-Functionality (Other)</option><option value="12">011-Req misunderstanding</option><option value="13">012-Feature missing</option><option value="14">013-Coding logic</option><option selected="selected" value="15">014-Business logic</option><option value="2">02-User Interface</option><option value="3">03-Performance</option><option value="4">04-Design issue</option><option value="5">05-Coding standard</option><option value="6">06-Document</option><option value="7">07-Data &amp; Database integrity</option><option value="8">08-Security &amp; Access Control</option><option value="9">09-Portability</option><option value="10">10-Other</option><option value="11">11-Tools</option>
        </select></td>
    </tr>
        <!-- Defect Owner -->
    	<tr><td align="left" valign="middle"><b><font color="black">Defect owner</font></b></td>
        <td align="left" valign="middle"><select name="DefectOwner" class="SmallCombo"><option selected="selected" value=""></option><option value="SYSADMIN">SYSADMIN</option><option value="THACHLN">THACHLN</option>
        </select></td>
    </tr><tr>
    
    </tr><tr>
        <!-- AssignTo -->
        <td align="left" valign="middle"><b><font color="black">Assigned To</font></b></td>
        <td align="left" valign="middle"><select name="AssignTo" class="SmallCombo"><option selected="selected" value=""></option><option value="SYSADMIN">SYSADMIN</option><option value="THACHLN">THACHLN</option>
        </select></td>
        <td>&nbsp;</td>
        <!-- Due Date -->
        <td id="idDueDate" align="left" valign="middle"><b><font color="black">Due date</font></b></td>
        <td align="left" valign="middle">
            <input style="font-family: Arial; font-size: 8pt; width: 45pt;" name="DueDate" value="06/06/12" maxlength="8" type="text">
            <img src="/<spring:message code="app.context"/>/resource_files/cal.gif" style="" onclick='showCalendar(document.getElementById("idDueDate"), document.forms[0].DueDate, "mm/dd/yy",null,1,-1,-1,true)'>
            <font color="blue">&nbsp;(mm/dd/yy)</font></td>
    </tr>
    <tr>
        <td rowspan="2" align="left" nowrap="nowrap" valign="middle"><b><font color="black">Cause analysis&nbsp;</font></b></td>
        <td rowspan="2"><textarea class="LargeTextarea" wrap="virtual" name="txtCauseAnalysis" onchange="hidCauseAnalysis.value=txtCauseAnalysis.value"></textarea> <input name="hidCauseAnalysis" value="" type="hidden"></td>
        <td rowspan="2">&nbsp;</td>
        <td valign="top"><b><font color="black">Test Case ID</font></b></td>
        <td valign="top"><input class="SmallBox" maxlength="50" name="txtTestCase" type="text">
        <div id="overDiv" style="position: absolute; visibility: hide; FONT-SIZE: xx-small; FONT-FAMILY: Verdana"></div>
        <br>
        <input name="Image" value="" type="hidden"></td>
    </tr>
    <tr></tr>
    <tr>
        <!-- Corrective Action -->
        <td rowspan="2" align="left" valign="middle"><b><font color="black">Corrective action&nbsp;</font></b></td>
        <td rowspan="2"><textarea class="LargeTextarea" wrap="virtual" name="txtCorrectiveAction" onchange="CorrectiveAction.value=txtCorrectiveAction.value"></textarea></td>
        <td rowspan="2">&nbsp;</td>
        <td colspan="2" valign="top">&nbsp;
        <div id="overDiv" style="position: absolute; visibility: hide; FONT-SIZE: xx-small; FONT-FAMILY: Verdana"></div>
        <br>
        <input name="Image" value="" type="hidden"></td>
        <input name="CorrectiveAction" value="" type="hidden">
    </tr>
    <tr>
    	<td height="1"></td>
    	<td height="1"></td>
    	<td height="1"></td>
    </tr>
</tbody></table>
<table border="0" cellpadding="1" cellspacing="0" width="100%">
    <tbody><tr>
        <td></td>
    </tr>
</tbody></table>
<p>
<%--
<input name="NewDefect" class="Button" value="Save" type="Submit">&nbsp;&nbsp;&nbsp;&nbsp;
 --%>
<input name="Save" class="Button" onclick='submitAction("${portletNamespace}AddDefect", "${formAction}")' value="Save" type="button">&nbsp;&nbsp;&nbsp;&nbsp;
<input name="Back" class="Button" onclick='submitAction("${portletNamespace}AddDefect", "${goViewDefectList2}")' value="Defect List" type="button">&nbsp;&nbsp;&nbsp;&nbsp;
<input name="Attach" class="Button" onclick="javascript:doAttach()" value="Attach file" type="button">
</p>
</form:form>
<br>
<br>
<br>
<script language="JavaScript">
    selectWorkProduct();
    ChangeTypeActivity();
    document.forms[0].Title.focus();
</script>


</body></html>