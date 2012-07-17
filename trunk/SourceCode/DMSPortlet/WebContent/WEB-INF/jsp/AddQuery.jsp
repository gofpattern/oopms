<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html><head>
<script src="resource_files/validate.js"></script>
<script src="resource_files/utils.js"></script>
<script src="resource_files/CommonScript.js"></script>
<title>View Defect Creating</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="StyleSheet" href="resource_files/DMSStyleSheet.css" type="text/css">
<script language="javascript">
function doAdd() {
    var form = document.frmQueryAdd;
    if (checkValidate(form)) {
        form.hidAction.value = "DM";
        form.hidActionDetail.value = "SaveNewQuery";
        form.submit();
    }
}

function doBack() {
    var form = document.frmQueryAdd;
    form.hidAction.value = "DM";
    form.hidActionDetail.value = "QueryListing";
    form.submit();
}

function doQueryListing() {
    var form = document.frmQueryAdd;
    form.hidAction.value = "DM";
    form.hidActionDetail.value = "QueryListing";
    form.action = "DMSServlet";
    form.submit();
}

function checkValidate(form) {
    if (!validateRequiredTextField(form.Name, "Query name could not be empty!")) {
        return false;
    }
    var i = 0;
    var flag = true;
    var temp;
    var block = true;
    for (i = 0; i < 10; i++) {
        if (form.FieldName[i].value != 0) {
            if (block) {
                flag = false;
                if (!validateRequiredTextField(form.Values[i],
                        "Please enter value of this field!")) {
                    return false;
                }
                else {
                    temp = form.FieldName[i].value;
                    if ((temp == "defect.qa_id") || (temp == "defect.ds_id")
                            || (temp == "defect.defs_id")) {
                        if (!validateNonNegativeIntegerTextField(form.Values[i],
                                "Invalid number!")) {
                            form.Values[i].focus();
                            form.Values[i].select();
                            return false;
                        }
                    }
                    if ((temp == "defect.close_date") ||
                        (temp == "defect.create_date") ||
                        (temp == "defect.due_date"))
                    {
                        if (!isDate(form.Values[i])) {
                            form.Values[i].focus();
                            form.Values[i].select();
                            return false;
                        }
                    }
                }
            }
            else {
                alert("Can not define expression in this line!");
                form.Values[i].focus();
                form.Values[i].select();
                return false;
            }
        }
        else {
            block=false;
        }
    }   //end for
    if (flag) {
        alert("Select field name to create a query!");
        return false;
    }
    return true;
}
</script>
</head>
<body topmargin="0" leftmargin="0" bgcolor="#FFFFFF">
<div align="left"><link rel="stylesheet" type="text/css" href="resource_files/menu.css">
<script type="text/javascript" src="resource_files/validate.js"></script>
<script type="text/javascript" src="resource_files/menu.js"></script><div id="dropmenudiv" style="visibility:hidden;width:230;background-color:#E6E6E6" onmouseover="clearhidemenu()" onmouseout="dynamichide(event)"></div>

<table bgcolor="#000000" border="0" cellpadding="0" cellspacing="0" height="51" width="100%">
    <tbody><tr>
        <td background="resource_files/bgr_header.gif" bgcolor="#310C52" height="51" width="212"><img src="resource_files/defect_logop1.gif" border="0" height="51" width="188"><br></td>
        <td align="left" background="resource_files/bgr_header.gif" bgcolor="#310C52" height="51" valign="top" width="50%"><img src="resource_files/defect_logop2.gif" border="0" height="51" width="173"></td>
        <td align="right" background="resource_files/bgr_header.gif" bgcolor="#310C52" height="51" valign="top" width="50%"><img src="resource_files/header.gif" border="0" height="51" width="384"></td>
    </tr>
    <tr>
        <td align="left" bgcolor="#000084" width="111"><img src="resource_files/logo2.gif" border="0" height="20" width="148"></td>
        <td colspan="2" align="left" bgcolor="#FFD731" valign="middle">
        <table bgcolor="#310C52" border="0" cellpadding="0" cellspacing="1" height="20" width="100%">
            <tbody><tr>
                <td align="left"><img src="resource_files/invisible.gif" height="1" width="60"></td>
                <td align="left"><img src="resource_files/invisible.gif" height="1" width="210"></td>
                <td align="left"><img src="resource_files/invisible.gif" height="1" width="150"></td>
                <td align="left"><img src="resource_files/invisible.gif" height="1" width="80"></td>
                <td align="right" width="50%"></td>
            </tr>
            <tr>
                <td bgcolor="#310C52">&nbsp;</td>
                <td bordercolor="#310C52" onmouseover="dropdownmenu(this, event, menu1, '150px')" onmouseout="delayhidemenu()" align="center" bgcolor="#FFD731" height="14" valign="middle">
                <p><span style="color: #AE3800; font-family: arial, helvetica; font-size: 10pt; text-decoration: none; font-weight: bold"> <font face="verdana"><b>Project&nbsp;Environment</b></font></span></p>
                </td>
                <td bordercolor="#310C52" onmouseover="dropdownmenu(this, event, menu2, '150px')" onmouseout="delayhidemenu()" align="center" bgcolor="#FFD731" height="14" valign="middle">
                <p><span style="color: #AE3800; font-family: arial, helvetica; font-size: 10pt; text-decoration: none; font-weight: bold"> <font face="Verdana"><b>Manage&nbsp;Defect</b></font></span></p>
                </td>
                <td bordercolor="#310C52" align="center" bgcolor="#FFD731" height="14" valign="middle">
                <p align="center"><font face="Verdana" size="2"> <a href="javascript:void(0)" onclick="return menuClick('mnuLogout',document.forms[0])"> <span style="color: blue; font-family: arial, helvetica; font-size: 10pt; text-decoration: none; font-weight: bold"> <b><font color="#AE3800" face="Verdana">Logout</font></b></span></a></font></p>
                </td>
                <td bordercolor="#310C52" align="center" bgcolor="#FFD731" height="14" valign="middle">
                <p align="right"><font face="Verdana" size="2"> <a href="javascript:void(0)" onclick="return menuClick('mnuHelp',document.forms[0])"> <span style="color: blue; font-family: arial, helvetica; font-size: 10pt; text-decoration: none; font-weight: bold"> <b><font color="#AE3800" face="Verdana">Help&nbsp;&nbsp;</font></b></span></a></font></p>
                </td>
            </tr>
        </tbody></table>
        </td>
    </tr>
</tbody></table>
</div>
<div>
<p><img src="resource_files/ViewDefectCreating.gif" border="0" height="28" width="411"></p>
<form method="POST" action="DMSServlet" name="frmQueryAdd"><input name="hidActionDetail" value="" type="hidden"> <input name="hidAction" value="" type="hidden"> <input name="CheckRole" value="1110000000" type="hidden">
<input name="Role" value="1110000000" type="hidden">
</form></div>
<table class="TblOut2" border="0" width="100%">
    <tbody><tr>
        <td width="8%"><b>User:</b></td>
        <td width="24%">thachln</td>
        <td width="12%"><b>Login Date:</b></td>
        <td width="25%">07/17/12</td>
        <td width="9%"><b>Project</b></td>
        <td align="right" width="22%"><select name="cboProjectList" class="SmallCombo" onchange="javascript:doChangeProject('DM','AddQuery','');"><option value="118405">InterWeb</option><option selected="selected" value="118385">OOPMS</option>
        </select></td>
    </tr>
    <tr>
        <td width="8%"><b>Group:</b></td>
        <td width="24%">FU</td>
        <td width="12%"><b>Position:</b></td>
        <td width="25%">Project Leader</td>
        <td width="9%"><b>Status</b></td>
        <td align="right" width="22%"><select name="cboProjectStatus" class="SmallCombo" onchange="javascript:doChangeProject('DM','AddQuery','');"><option selected="selected" value="0">On-going</option>        </select></td>

    </tr>
</tbody></table>
<p></p>
<table border="0" cellpadding="0" cellspacing="1" width="100%">
    <tbody><tr>
        <td colspan="3" width="15%"><b>Query name:</b></td>
        <td colspan="3" width="35%"><input value="Truong.Bug" name="Name" size="35" type="text"></td>
        <td colspan="3" width="49%">
        <input name="Scope" value="0" type="checkbox"> <b>Public query</b> 
        </td>
    </tr>
</tbody></table>
<p></p>
<table bgcolor="#000000" border="0" cellpadding="1" cellspacing="1" width="100%">
    <tbody><tr class="Row0" height="19">
        <td align="center" width="15%"><b>Field Name&nbsp;</b></td>
        <td align="center" width="7%"><b>Not</b></td>
        <td align="center" width="8%"><b>Criteria</b></td>
        <td align="center" width="37%"><b>Value</b></td>
        <td align="center" width="9%"><b>Logical</b></td>
        <td align="center" width="11%"><b>Group</b></td>
    </tr>
    <tr class="Row2">
        <td align="center" width="15%"><select size="1" name="FieldName">
            <option value="0"></option>
            <option selected="selected" value="defect.assigned_to">Assigned to</option>
            <option value="defect.Defect_Owner">Defect owner</option>
            <option value="defect.qa_id">QC Activity</option>
            <option value="defect.defs_id">Severity</option>
            <option value="defect.ds_id">Status</option>
            <option value="defect.title">Title</option>
            <option value="defect.created_by">Created by</option>
            <option value="defect.create_date">Create Date</option>
            <option value="defect.close_date">Close Date</option>
            <option value="defect.due_date">Due Date</option>
        </select></td>

        <td align="center" width="7%"><input name="Not" value="0" type="checkbox"></td>
        <td align="center" width="8%"><select size="1" name="Criteria">
            <option value="=">=</option>
            <option value="&gt;">&gt;</option>
            <option value="&lt;">&lt;</option>
            <option value="&gt;=">&gt;=</option>
            <option value="&lt;=">&lt;=</option>
            <option selected="selected" value="LIKE">LIKE</option>
        </select></td>
        <td align="center" width="37%"><input value="Truong" name="Values" size="45" type="text"></td>
        <td align="center" width="9%"><select size="1" name="Logical">
            <option selected="selected" value="AND">AND</option>
            <option value="OR">OR</option>
        </select></td>
        <td align="center" width="11%"><select size="1" name="Group">
            <option selected="selected" value="1">Group 1</option>
            <option value="2">Group 2</option>
            <option value="3">Group 3</option>
            <option value="4">Group 4</option>
        </select></td>
    </tr>
    <tr class="Row1">
        <td align="center" width="15%"><select size="1" name="FieldName">
            <option selected="selected" value="0"></option>
            <option value="defect.assigned_to">Assigned to</option>
            <option value="defect.Defect_Owner">Defect owner</option>
            <option value="defect.qa_id">QC Activity</option>
            <option value="defect.defs_id">Severity</option>
            <option value="defect.ds_id">Status</option>
            <option value="defect.title">Title</option>
            <option value="defect.created_by">Created by</option>
            <option value="defect.create_date">Create Date</option>
            <option value="defect.close_date">Close Date</option>
            <option value="defect.due_date">Due Date</option>
        </select></td>

        <td align="center" width="7%"><input name="Not" value="1" type="checkbox"></td>
        <td align="center" width="8%"><select size="1" name="Criteria">
            <option selected="selected" value="=">=</option>
            <option value="&gt;">&gt;</option>
            <option value="&lt;">&lt;</option>
            <option value="&gt;=">&gt;=</option>
            <option value="&lt;=">&lt;=</option>
            <option value="LIKE">LIKE</option>
        </select></td>
        <td align="center" width="37%"><input name="Values" size="45" type="text"></td>
        <td align="center" width="9%"><select size="1" name="Logical">
            <option selected="selected" value="AND">AND</option>
            <option value="OR">OR</option>
        </select></td>
        <td align="center" width="11%"><select size="1" name="Group">
            <option selected="selected" value="1">Group 1</option>
            <option value="2">Group 2</option>
            <option value="3">Group 3</option>
            <option value="4">Group 4</option>
        </select></td>
    </tr>
    <tr class="Row2">
        <td align="center" width="15%"><select size="1" name="FieldName">
            <option selected="selected" value="0"></option>
            <option value="defect.assigned_to">Assigned to</option>
            <option value="defect.Defect_Owner">Defect owner</option>
            <option value="defect.qa_id">QC Activity</option>
            <option value="defect.defs_id">Severity</option>
            <option value="defect.ds_id">Status</option>
            <option value="defect.title">Title</option>
            <option value="defect.created_by">Created by</option>
            <option value="defect.create_date">Create Date</option>
            <option value="defect.close_date">Close Date</option>
            <option value="defect.due_date">Due Date</option>
        </select></td>

        <td align="center" width="7%"><input name="Not" value="2" type="checkbox"></td>
        <td align="center" width="8%"><select size="1" name="Criteria">
            <option selected="selected" value="=">=</option>
            <option value="&gt;">&gt;</option>
            <option value="&lt;">&lt;</option>
            <option value="&gt;=">&gt;=</option>
            <option value="&lt;=">&lt;=</option>
            <option value="LIKE">LIKE</option>
        </select></td>
        <td align="center" width="37%"><input name="Values" size="45" type="text"></td>
        <td align="center" width="9%"><select size="1" name="Logical">
            <option selected="selected" value="AND">AND</option>
            <option value="OR">OR</option>
        </select></td>
        <td align="center" width="11%"><select size="1" name="Group">
            <option selected="selected" value="1">Group 1</option>
            <option value="2">Group 2</option>
            <option value="3">Group 3</option>
            <option value="4">Group 4</option>
        </select></td>
    </tr>
    <tr class="Row1">
        <td align="center" width="15%"><select size="1" name="FieldName">
            <option selected="selected" value="0"></option>
            <option value="defect.assigned_to">Assigned to</option>
            <option value="defect.Defect_Owner">Defect owner</option>
            <option value="defect.qa_id">QC Activity</option>
            <option value="defect.defs_id">Severity</option>
            <option value="defect.ds_id">Status</option>
            <option value="defect.title">Title</option>
            <option value="defect.created_by">Created by</option>
            <option value="defect.create_date">Create Date</option>
            <option value="defect.close_date">Close Date</option>
            <option value="defect.due_date">Due Date</option>
        </select></td>

        <td align="center" width="7%"><input name="Not" value="3" type="checkbox"></td>
        <td align="center" width="8%"><select size="1" name="Criteria">
            <option selected="selected" value="=">=</option>
            <option value="&gt;">&gt;</option>
            <option value="&lt;">&lt;</option>
            <option value="&gt;=">&gt;=</option>
            <option value="&lt;=">&lt;=</option>
            <option value="LIKE">LIKE</option>
        </select></td>
        <td align="center" width="37%"><input name="Values" size="45" type="text"></td>
        <td align="center" width="9%"><select size="1" name="Logical">
            <option selected="selected" value="AND">AND</option>
            <option value="OR">OR</option>
        </select></td>
        <td align="center" width="11%"><select size="1" name="Group">
            <option selected="selected" value="1">Group 1</option>
            <option value="2">Group 2</option>
            <option value="3">Group 3</option>
            <option value="4">Group 4</option>
        </select></td>
    </tr>
    <tr class="Row2">
        <td align="center" width="15%"><select size="1" name="FieldName">
            <option selected="selected" value="0"></option>
            <option value="defect.assigned_to">Assigned to</option>
            <option value="defect.Defect_Owner">Defect owner</option>
            <option value="defect.qa_id">QC Activity</option>
            <option value="defect.defs_id">Severity</option>
            <option value="defect.ds_id">Status</option>
            <option value="defect.title">Title</option>
            <option value="defect.created_by">Created by</option>
            <option value="defect.create_date">Create Date</option>
            <option value="defect.close_date">Close Date</option>
            <option value="defect.due_date">Due Date</option>
        </select></td>

        <td align="center" width="7%"><input name="Not" value="4" type="checkbox"></td>
        <td align="center" width="8%"><select size="1" name="Criteria">
            <option selected="selected" value="=">=</option>
            <option value="&gt;">&gt;</option>
            <option value="&lt;">&lt;</option>
            <option value="&gt;=">&gt;=</option>
            <option value="&lt;=">&lt;=</option>
            <option value="LIKE">LIKE</option>
        </select></td>
        <td align="center" width="37%"><input name="Values" size="45" type="text"></td>
        <td align="center" width="9%"><select size="1" name="Logical">
            <option selected="selected" value="AND">AND</option>
            <option value="OR">OR</option>
        </select></td>
        <td align="center" width="11%"><select size="1" name="Group">
            <option selected="selected" value="1">Group 1</option>
            <option value="2">Group 2</option>
            <option value="3">Group 3</option>
            <option value="4">Group 4</option>
        </select></td>
    </tr>
    <tr class="Row1">
        <td align="center" width="15%"><select size="1" name="FieldName">
            <option selected="selected" value="0"></option>
            <option value="defect.assigned_to">Assigned to</option>
            <option value="defect.Defect_Owner">Defect owner</option>
            <option value="defect.qa_id">QC Activity</option>
            <option value="defect.defs_id">Severity</option>
            <option value="defect.ds_id">Status</option>
            <option value="defect.title">Title</option>
            <option value="defect.created_by">Created by</option>
            <option value="defect.create_date">Create Date</option>
            <option value="defect.close_date">Close Date</option>
            <option value="defect.due_date">Due Date</option>
        </select></td>

        <td align="center" width="7%"><input name="Not" value="5" type="checkbox"></td>
        <td align="center" width="8%"><select size="1" name="Criteria">
            <option selected="selected" value="=">=</option>
            <option value="&gt;">&gt;</option>
            <option value="&lt;">&lt;</option>
            <option value="&gt;=">&gt;=</option>
            <option value="&lt;=">&lt;=</option>
            <option value="LIKE">LIKE</option>
        </select></td>
        <td align="center" width="37%"><input name="Values" size="45" type="text"></td>
        <td align="center" width="9%"><select size="1" name="Logical">
            <option selected="selected" value="AND">AND</option>
            <option value="OR">OR</option>
        </select></td>
        <td align="center" width="11%"><select size="1" name="Group">
            <option selected="selected" value="1">Group 1</option>
            <option value="2">Group 2</option>
            <option value="3">Group 3</option>
            <option value="4">Group 4</option>
        </select></td>
    </tr>
    <tr class="Row2">
        <td align="center" width="15%"><select size="1" name="FieldName">
            <option selected="selected" value="0"></option>
            <option value="defect.assigned_to">Assigned to</option>
            <option value="defect.Defect_Owner">Defect owner</option>
            <option value="defect.qa_id">QC Activity</option>
            <option value="defect.defs_id">Severity</option>
            <option value="defect.ds_id">Status</option>
            <option value="defect.title">Title</option>
            <option value="defect.created_by">Created by</option>
            <option value="defect.create_date">Create Date</option>
            <option value="defect.close_date">Close Date</option>
            <option value="defect.due_date">Due Date</option>
        </select></td>

        <td align="center" width="7%"><input name="Not" value="6" type="checkbox"></td>
        <td align="center" width="8%"><select size="1" name="Criteria">
            <option selected="selected" value="=">=</option>
            <option value="&gt;">&gt;</option>
            <option value="&lt;">&lt;</option>
            <option value="&gt;=">&gt;=</option>
            <option value="&lt;=">&lt;=</option>
            <option value="LIKE">LIKE</option>
        </select></td>
        <td align="center" width="37%"><input name="Values" size="45" type="text"></td>
        <td align="center" width="9%"><select size="1" name="Logical">
            <option selected="selected" value="AND">AND</option>
            <option value="OR">OR</option>
        </select></td>
        <td align="center" width="11%"><select size="1" name="Group">
            <option selected="selected" value="1">Group 1</option>
            <option value="2">Group 2</option>
            <option value="3">Group 3</option>
            <option value="4">Group 4</option>
        </select></td>
    </tr>
    <tr class="Row1">
        <td align="center" width="15%"><select size="1" name="FieldName">
            <option selected="selected" value="0"></option>
            <option value="defect.assigned_to">Assigned to</option>
            <option value="defect.Defect_Owner">Defect owner</option>
            <option value="defect.qa_id">QC Activity</option>
            <option value="defect.defs_id">Severity</option>
            <option value="defect.ds_id">Status</option>
            <option value="defect.title">Title</option>
            <option value="defect.created_by">Created by</option>
            <option value="defect.create_date">Create Date</option>
            <option value="defect.close_date">Close Date</option>
            <option value="defect.due_date">Due Date</option>
        </select></td>

        <td align="center" width="7%"><input name="Not" value="7" type="checkbox"></td>
        <td align="center" width="8%"><select size="1" name="Criteria">
            <option selected="selected" value="=">=</option>
            <option value="&gt;">&gt;</option>
            <option value="&lt;">&lt;</option>
            <option value="&gt;=">&gt;=</option>
            <option value="&lt;=">&lt;=</option>
            <option value="LIKE">LIKE</option>
        </select></td>
        <td align="center" width="37%"><input name="Values" size="45" type="text"></td>
        <td align="center" width="9%"><select size="1" name="Logical">
            <option selected="selected" value="AND">AND</option>
            <option value="OR">OR</option>
        </select></td>
        <td align="center" width="11%"><select size="1" name="Group">
            <option selected="selected" value="1">Group 1</option>
            <option value="2">Group 2</option>
            <option value="3">Group 3</option>
            <option value="4">Group 4</option>
        </select></td>
    </tr>
    <tr class="Row2">
        <td align="center" width="15%"><select size="1" name="FieldName">
            <option selected="selected" value="0"></option>
            <option value="defect.assigned_to">Assigned to</option>
            <option value="defect.Defect_Owner">Defect owner</option>
            <option value="defect.qa_id">QC Activity</option>
            <option value="defect.defs_id">Severity</option>
            <option value="defect.ds_id">Status</option>
            <option value="defect.title">Title</option>
            <option value="defect.created_by">Created by</option>
            <option value="defect.create_date">Create Date</option>
            <option value="defect.close_date">Close Date</option>
            <option value="defect.due_date">Due Date</option>
        </select></td>

        <td align="center" width="7%"><input name="Not" value="8" type="checkbox"></td>
        <td align="center" width="8%"><select size="1" name="Criteria">
            <option selected="selected" value="=">=</option>
            <option value="&gt;">&gt;</option>
            <option value="&lt;">&lt;</option>
            <option value="&gt;=">&gt;=</option>
            <option value="&lt;=">&lt;=</option>
            <option value="LIKE">LIKE</option>
        </select></td>
        <td align="center" width="37%"><input name="Values" size="45" type="text"></td>
        <td align="center" width="9%"><select size="1" name="Logical">
            <option selected="selected" value="AND">AND</option>
            <option value="OR">OR</option>
        </select></td>
        <td align="center" width="11%"><select size="1" name="Group">
            <option selected="selected" value="1">Group 1</option>
            <option value="2">Group 2</option>
            <option value="3">Group 3</option>
            <option value="4">Group 4</option>
        </select></td>
    </tr>
    <tr class="Row1">
        <td align="center" width="15%"><select size="1" name="FieldName">
            <option selected="selected" value="0"></option>
            <option value="defect.assigned_to">Assigned to</option>
            <option value="defect.Defect_Owner">Defect owner</option>
            <option value="defect.qa_id">QC Activity</option>
            <option value="defect.defs_id">Severity</option>
            <option value="defect.ds_id">Status</option>
            <option value="defect.title">Title</option>
            <option value="defect.created_by">Created by</option>
            <option value="defect.create_date">Create Date</option>
            <option value="defect.close_date">Close Date</option>
            <option value="defect.due_date">Due Date</option>
        </select></td>

        <td align="center" width="7%"><input name="Not" value="9" type="checkbox"></td>
        <td align="center" width="8%"><select size="1" name="Criteria">
            <option selected="selected" value="=">=</option>
            <option value="&gt;">&gt;</option>
            <option value="&lt;">&lt;</option>
            <option value="&gt;=">&gt;=</option>
            <option value="&lt;=">&lt;=</option>
            <option value="LIKE">LIKE</option>
        </select></td>
        <td align="center" width="37%"><input name="Values" size="45" type="text"></td>
        <td align="center" width="9%"><select size="1" name="Logical">
            <option selected="selected" value="AND">AND</option>
            <option value="OR">OR</option>
        </select></td>
        <td align="center" width="11%"><select size="1" name="Group">
            <option selected="selected" value="1">Group 1</option>
            <option value="2">Group 2</option>
            <option value="3">Group 3</option>
            <option value="4">Group 4</option>
        </select></td>
    </tr>

</tbody></table>
<font style="FONT-FAMILY: Verdana; FONT-SIZE: xx-small; FONT-WEIGHT: bold; COLOR: #000080">Date Format:&nbsp; mm/dd/yy</font>
<p><input name="btnAdd" class="Button" onclick="javascript:doAdd()" value="Add" type="button">&nbsp;&nbsp;&nbsp;&nbsp;
<input name="btnBack" class="Button" onclick="javascript:doBack()" value="Back" type="button"></p>



<script language="JavaScript">
     document.forms[0].Name.focus();
</script></body></html>