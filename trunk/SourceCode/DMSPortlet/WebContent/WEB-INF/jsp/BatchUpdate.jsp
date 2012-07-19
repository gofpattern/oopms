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
<script src="resource_files/popcalendar.js"></script></head><body topmargin="0" leftmargin="0" bgcolor="#FFFFFF"><div onclick="bShow=true" id="calendar" style="z-index:+999;position:absolute;visibility:hidden;"><table class="pcalTbl" width="250"><tbody><tr bgcolor="#000066"><td><table width="248"><tbody><tr><td class="pcalCaption"><font color="#ffffff"><b><span id="caption"><span id="spanLeft" class="pcalSelBtn" onmouseover="swapImage('changeLeft','pcalleft2.gif');this.style.borderColor='#8af';window.status='Click to scroll to previous month. Hold mouse button to scroll automatically.'" onclick="decMonth()" onmouseout="clearInterval(intervalID1);swapImage('changeLeft','pcalleft1.gif');this.style.borderColor='#36f';window.status=''" onmousedown="clearTimeout(timeoutID1);timeoutID1=setTimeout('StartDecMonth()',500)" onmouseup="clearTimeout(timeoutID1);clearInterval(intervalID1)">&nbsp;<img id="changeLeft" src="resource_files/pcalleft1.gif" border="0" height="11" width="10"></span>&nbsp;<span id="spanRight" class="pcalSelBtn" onmouseover="swapImage('changeRight','pcalright2.gif');this.style.borderColor='#8af';window.status='Click to scroll to next month. Hold mouse button to scroll automatically.'" onmouseout="clearInterval(intervalID1);swapImage('changeRight','pcalright1.gif');this.style.borderColor='#36f';window.status=''" onclick="incMonth()" onmousedown="clearTimeout(timeoutID1);timeoutID1=setTimeout('StartIncMonth()',500)" onmouseup="clearTimeout(timeoutID1);clearInterval(intervalID1)">&nbsp;<img id="changeRight" src="resource_files/pcalright1.gif" border="0" height="11" width="10"></span>&nbsp;<span id="spanMonth" class="pcalSelBtn" onmouseover="swapImage('changeMonth','pcaldrop2.gif');this.style.borderColor='#8af';window.status='Click to select a month.'" onmouseout="swapImage('changeMonth','pcaldrop1.gif');this.style.borderColor='#36f';window.status=''" onclick="popUpMonth()"></span>&nbsp;<span id="spanYear" class="pcalSelBtn" onmouseover="swapImage('changeYear','pcaldrop2.gif');this.style.borderColor='#8af';window.status='Click to select a year.'" onmouseout="swapImage('changeYear','pcaldrop1.gif');this.style.borderColor='#36f';window.status=''" onclick="popUpYear()"></span>&nbsp;</span></b></font></td><td align="right"><a href="javascript:hideCalendar()"><img src="resource_files/pcalclose.gif" border="0" height="13" width="15"></a></td></tr></tbody></table></td></tr><tr><td style="padding: 5px;" bgcolor="#ffffff"><span id="content"></span></td></tr><tr bgcolor="#f0f0f0"><td style="padding: 5px;" align="center"><span id="lblToday"><font color="#000066">Today is <a onmousemove="window.status='Go To Current Month'" onmouseout="window.status=''" title="Go To Current Month" style="text-decoration: none; color: black;" href="javascript:monthSelected=monthNow;yearSelected=yearNow;constructCalendar();">Tue, 17 Jul 2012</a></font></span></td></tr></tbody></table></div><div id="selectMonth" style="z-index:+999;position:absolute;visibility:hidden;"></div><div id="selectYear" style="z-index:+999;position:absolute;visibility:hidden;"></div>
<title>Batch Update Defect</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="StyleSheet" href="resource_files/DMSStyleSheet.css" type="text/css">
<link rel="StyleSheet" href="resource_files/pcal.css" type="text/css">


<script language="JavaScript">
	function selectAssignTo(index) {	
		var oldvalue = frmDefectBatchUpdate.oldstatus[index].value;
		if(oldvalue == "1") {
			frmDefectBatchUpdate.newstatus[index].value = "2";
		}
		else {
			if (frmDefectBatchUpdate.cboAssignTo[index].value == "") {
				frmDefectBatchUpdate.newstatus[index].value = "1";
	 		}
	 		else {
				frmDefectBatchUpdate.newstatus[index].value = oldvalue;
	 		}
	 	}
	} 
</script>
<script type="text/javascript" src='/<spring:message code="app.context"/>/scripts/common.js'></script>
<%@ include file="/WEB-INF/jsp/menu.jsp" %>

<div>
<p><img src="resource_files/DefectBatchUpdate.gif" border="0" height="28" width="411"></p>
</div>

<portlet:renderURL var="goViewDefectList2"><portlet:param name="action" value="goViewDefectList2"/></portlet:renderURL>

<form:form name="${portletNamespace}BatchUpdate" commandName="batchDefect" method="post" action="${formAction}">

<input name="hidActionDetail" value="" type="hidden">
<input name="hidAction" value="" type="hidden">
<input name="CheckRole" value="1110000000" type="hidden">
<input name="hidTypeOfView" value="ViewAllDefects" type="hidden">
<input name="UserName" value="thachln" type="hidden">
<input name="Account" value="THACHLN" type="hidden">
<input name="Role" value="1110000000" type="hidden">
<input name="Position" value="1110000000" type="hidden">
<input name="ProjectCode" value="OOPMS" type="hidden">
<input name="DateLogin" value="07/17/12" type="hidden">
<input name="ProjectID" value="118385" type="hidden">
<table border="0" width="100%">
    <tbody><tr>
        <td align="right"><a href="javascript:doQueryListing()">View DefectListing</a></td>
    </tr>
</tbody></table>

<%@ include file="/WEB-INF/jsp/header2Defect.jsp" %>

<p></p>
<table bgcolor="#000000" border="0" cellpadding="0" cellspacing="1" width="100%">
    <tbody><tr class="Row0" height="19">
        <th align="center" valign="middle" width="9%">Defect ID</th>
        <th align="center" valign="middle" width="22%">Defect Title</th>
        <th align="center" valign="middle" width="11%">Due Date</th>
        <th align="center" valign="middle" width="9%">Severity<font color="red">*</font></th>
        <th align="center" valign="middle" width="9%">Priority</th>
        <th align="center" valign="middle" width="9%">Defect Owner</th>
        <th align="center" valign="middle" width="9%">Assigned To</th>
     <!--  <TH align="center" valign="middle" width="20%">Corrective Action</TH> -->
    </tr>
    <input name="hidNumberOfRows" value="2" type="hidden">

    <tr class="Row2">
        <!-- DefectID -->
        <input name="hidDefectID" value="522435" type="hidden">
        <input name="txtFixedDate" value="" type="hidden">
        <input name="hidFixedDate" value="" type="hidden">
        <td align="center" width="9%">522435</td>
        <td width="22%">Temporary generated folder is committed into SVN</td>
        <!-- DueDate -->
        <td width="11%">
            <input name="txtDueDate" value="07/17/12" size="13" class="DateBox" maxlength="8" type="text"><img src="resource_files/cal.gif" style="" onclick='showCalendar(txtDueDate[0], txtDueDate[0], "mm/dd/yy",null,1,-1,-1,true)'>
        </td>
       <input name="oldstatus" value="2" type="hidden">
       <input name="newstatus" value="2" type="hidden">
        <!-- Severity -->
        <td width="9%">
        <select name="cboSeverity" size="1" class="VerySmallCombo">
<option value="1">1-Fatal</option><option value="2">2-Serious</option><option value="3">3-Medium</option><option selected="selected" value="4">4-Cosmetic</option>
        </select>
        </td>
        
        <!-- Priority -->
        <td width="9%">
        <select name="cboPriority" size="1" class="VerySmallCombo">
<option value="0"></option><option value="1">1-Immediately</option><option selected="selected" value="2">2-High</option><option value="3">3-Normal</option><option value="4">4-Low</option>
        </select>
        </td>
        <!-- Defect Owner -->
        <td width="9%"><select name="cboDefectOwner" size="1" class="VerySmallCombo">
<option value=""></option><option selected="selected" value="MANH.HOANG.TRUONG">MANH.HOANG.TRUONG</option><option value="NGO.DUC.DUY">NGO.DUC.DUY</option><option value="PHAM.NGUYEN.TRUONG.GIANG">PHAM.NGUYEN.TRUONG.GIANG</option><option value="QA1">QA1</option><option value="SYSADMIN">SYSADMIN</option><option value="THACHLN">THACHLN</option><option value="TO.CONG.THANH.HAI">TO.CONG.THANH.HAI</option>
        </select>
        </td>
        <!-- AssignTo -->
        <td width="9%"><select name="cboAssignTo" size="1" class="VerySmallCombo" onchange="Javascript : selectAssignTo(0);">
<option value=""></option><option selected="selected" value="MANH.HOANG.TRUONG">MANH.HOANG.TRUONG</option><option value="NGO.DUC.DUY">NGO.DUC.DUY</option><option value="PHAM.NGUYEN.TRUONG.GIANG">PHAM.NGUYEN.TRUONG.GIANG</option><option value="QA1">QA1</option><option value="SYSADMIN">SYSADMIN</option><option value="THACHLN">THACHLN</option><option value="TO.CONG.THANH.HAI">TO.CONG.THANH.HAI</option>
        </select>
        </td>
    </tr>
   

    <tr class="Row1">
        <!-- DefectID -->
        <input name="hidDefectID" value="522415" type="hidden">
        <input name="txtFixedDate" value="" type="hidden">
        <input name="hidFixedDate" value="" type="hidden">
        <td align="center" width="9%">522415</td>
        <td width="22%">These is no description flow of use cases</td>
        <!-- DueDate -->
        <td width="11%">
            <input name="txtDueDate" value="06/06/12" size="13" class="DateBox" maxlength="8" type="text"><img src="resource_files/cal.gif" style="" onclick='showCalendar(txtDueDate[1], txtDueDate[1], "mm/dd/yy",null,1,-1,-1,true)'>
        </td>
       <input name="oldstatus" value="1" type="hidden">
       <input name="newstatus" value="1" type="hidden">
        <!-- Severity -->
        <td width="9%">
        <select name="cboSeverity" size="1" class="VerySmallCombo">
<option value="1">1-Fatal</option><option value="2">2-Serious</option><option selected="selected" value="3">3-Medium</option><option value="4">4-Cosmetic</option>
        </select>
        </td>
        
        <!-- Priority -->
        <td width="9%">
        <select name="cboPriority" size="1" class="VerySmallCombo">
<option value="0"></option><option value="1">1-Immediately</option><option selected="selected" value="2">2-High</option><option value="3">3-Normal</option><option value="4">4-Low</option>
        </select>
        </td>
        <!-- Defect Owner -->
        <td width="9%"><select name="cboDefectOwner" size="1" class="VerySmallCombo">
<option selected="selected" value=""></option><option value="MANH.HOANG.TRUONG">MANH.HOANG.TRUONG</option><option value="NGO.DUC.DUY">NGO.DUC.DUY</option><option value="PHAM.NGUYEN.TRUONG.GIANG">PHAM.NGUYEN.TRUONG.GIANG</option><option value="QA1">QA1</option><option value="SYSADMIN">SYSADMIN</option><option value="THACHLN">THACHLN</option><option value="TO.CONG.THANH.HAI">TO.CONG.THANH.HAI</option>
        </select>
        </td>
        <!-- AssignTo -->
        <td width="9%"><select name="cboAssignTo" size="1" class="VerySmallCombo" onchange="Javascript : selectAssignTo(1);">
<option selected="selected" value=""></option><option value="MANH.HOANG.TRUONG">MANH.HOANG.TRUONG</option><option value="NGO.DUC.DUY">NGO.DUC.DUY</option><option value="PHAM.NGUYEN.TRUONG.GIANG">PHAM.NGUYEN.TRUONG.GIANG</option><option value="QA1">QA1</option><option value="SYSADMIN">SYSADMIN</option><option value="THACHLN">THACHLN</option><option value="TO.CONG.THANH.HAI">TO.CONG.THANH.HAI</option>
        </select>
        </td>
    </tr>
   

 <tr>
     <td>
	     <input name="oldstatus" value="1" type="hidden">    	 
     </td>
     <td>
	     <input name="newstatus" value="1" type="hidden">
	 </td>  
	 <td>
   	 <input name="cboAssignTo" type="hidden">
	 </td>
    </tr>
</tbody></table>
<p></p>
<p><input name="BatchUpdateStatus" class="button" onclick="javascript:doSave()" value="Save" type="button">
&nbsp;&nbsp;&nbsp;&nbsp; <input name="Back" class="button" onclick='submitAction("${portletNamespace}BatchUpdate", "${goViewDefectList2}")' value="Defect List" type="button"></p>
</form:form>
<br>
<br>
<br>
<script language="javascript">
function doSave() {
    var form = document.frmDefectBatchUpdate;
    if (!isValidForm()) {
        return;
    }
    //GenFixedDate();
    form.hidAction.value = "DM";
    form.hidActionDetail.value = "SaveBatchUpdateDefect";
    form.action = "DMSServlet";
    form.submit();
}

function doBack() {
    var form = document.frmDefectBatchUpdate;
    form.hidAction.value = "DM";
    form.hidActionDetail.value = "SearchDefect";
    form.action = "DMSServlet";
    form.submit();
}

function doQueryListing() {
    var form = document.frmDefectBatchUpdate;
    form.hidAction.value = "DM";
    form.hidActionDetail.value = "QueryListing";
    form.action = "DMSServlet";
    form.submit();
}

function isValidForm() {
    var count;
    for (count = 0x00; count < document.forms(0).length; count++) {
        if (!isValidControl(document.forms(0).item(count))) {
            return false;
        }
    }
    return true;
}

function isValidControl(control) {
	//var form = document.frmDefectBatchUpdate;
    if (control.name == "txtDueDate") {
        if (control.value.length <= 0 || isDate(control)) {
            return true;
        }
        else {
            return false;
        }
    }
    /*
    else if (control.name == "cboStatus") {
        return isPositiveNumberCombobox(control);
    }*/
    else if (control.name=="cboSeverity") {
        return isPositiveNumberCombobox(control);
    }
    if (control.name == "txtCorAction") {
        if (control.value.length <= 0) {
            alert("Please fill to Corrective Action");
            control.focus();
            return false;
        }
    }
    return true;
}

/*
function CheckRoleStatus(position, numberRow, row) {
    var newValue;
    var oldValue;
    if (numberRow > 1) {
        newValue = document.forms[0].cboStatus[row].value;
        oldValue = document.forms[0].hidCurrentStatus[row].value;
        document.forms[0].txtFixedDate[row].value = document.forms[0].hidFixedDate[row].value;
    }
    else {
        newValue = document.forms[0].cboStatus.value;
        oldValue = document.forms[0].hidCurrentStatus.value;
        document.forms[0].txtFixedDate.value = document.forms[0].hidFixedDate.value;
    }
    var permit = true;
    switch (newValue) { //follow value of status
        case "1": //Error => Cannot assign FixedDate
        case "6": //Cancelled => Clear FixedDate
            if (numberRow > 1) {
                document.forms[0].txtFixedDate[row].value = "";
            }
            else {
                document.forms[0].txtFixedDate.value = "";
            }
            break;
        case "4": //Closed
            if (position.substring(1, 2) != 1 && position.substring(2, 3) != 1 && position.substring(6, 7) != 1) {//tester
                permit = false;
            }
            break;
        case "5": //Accepted
            if (position.substring(2, 3) != 1 && position.substring(6, 7) != 1) {//project leader Or external user
                permit = false;
            }
            break;
    }
    
    if (!permit) {
        if (numberRow > 1) {
            document.forms[0].cboStatus[row].value = oldValue;
        }
        else {
            document.forms[0].cboStatus.value = oldValue;
        }
        alert("You have no permission to change this status!");
    }
 } */

/*
function GenFixedDate() {
    var myForm = document.forms[0];
<    // Status=3: Pending, 4: Tested
if (beanDefectBatchUpdate.getBatchUpdateList().getNumberOfRows() > 1) {%>

    for (var i = 0; i < myForm.cboStatus.length; i++) {
        if ((myForm.txtFixedDate[i].value == "") &&
            (myForm.cboStatus[i].value != myForm.hidCurrentStatus[i].value) &&
            (myForm.cboStatus[i].value == "3" || myForm.cboStatus[i].value == "4"))
        {
            myForm.txtFixedDate[i].value = "<=DateUtil.getCurrentDate()%>";
        }
        else if ((myForm.txtFixedDate[i].value == "") &&
             (myForm.hidFixedDate[i].value != "") &&
             (myForm.cboStatus[i].value == "3" || myForm.cboStatus[i].value == "4"))
        {
            myForm.txtFixedDate[i].value = myForm.hidFixedDate[i].value;
        }
    }
<} else {%>
    if ((myForm.txtFixedDate.value == "") &&
        (myForm.cboStatus.value != myForm.hidCurrentStatus.value) &&
        (myForm.cboStatus.value == "3" || myForm.cboStatus.value == "4"))
    {
        myForm.txtFixedDate.value = "<=DateUtil.getCurrentDate()%>";
    }
    else if ((myForm.txtFixedDate.value == "") &&
             (myForm.hidFixedDate.value != "")
              && (myForm.cboStatus.value == "3" || myForm.cboStatus.value == "4"))
    {
        myForm.txtFixedDate.value = myForm.hidFixedDate.value;
    }
<}%> 
}*/
</script>


</body></html>