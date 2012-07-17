<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<portlet:defineObjects />
<html><head>
<title>View Defect Listing</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="StyleSheet" href="resource_files/DMSStyleSheet.css" type="text/css">
<script src="resource_files/CommonScript.js"></script>
<script src="resource_files/utils.js"></script>
<script language="JavaScript">
function doAllDefects() {
    var form = document.frmViewDefectListing;
    form.hidAction.value = "DM";
    form.hidActionDetail.value = "SearchDefect";
    form.hidTypeOfView.value = "ViewAllDefects";
    form.action = "DMSServlet";
    form.submit();
}

function doAllOpenDefects() {
    var form = document.frmViewDefectListing;
    form.hidAction.value = "DM";
    form.hidActionDetail.value = "SearchDefect";
    form.hidTypeOfView.value = "ViewAllOpenDefects";
    form.action = "DMSServlet";
    form.submit();
}
function doAllLeakageDefects() {
    var form = document.frmViewDefectListing;
    form.hidAction.value = "DM";
    form.hidActionDetail.value = "SearchDefect";
    form.hidTypeOfView.value = "ViewAllLeakageDefects";
    form.action = "DMSServlet";
    form.submit();
}

function doAddQuery() {
    var form = document.frmViewDefectListing;
    form.hidAction.value = "DM";
    form.hidActionDetail.value = "AddQuery";
    form.action = "DMSServlet";
    form.submit();
}

function doDeleteQuery() {
    var form = document.frmViewDefectListing;
    if (checkValid1() || checkValid2()) {
        if (confirm("Do you want to delete selected records, continue?")) {
            form.hidAction.value = "DM";
            form.hidActionDetail.value = "DeleteQuery";
            form.action = "DMSServlet";
            form.submit();
        }
    }
    else {
        alert("Please select queries to do delete!");
    }
}

function doQueryDefectList(id) {
    var form = document.frmViewDefectListing;
    form.hidTypeOfView.value = id;
    form.hidAction.value = "DM";
    form.hidActionDetail.value = "UserQueryListing";
    form.action = "DMSServlet";
    form.submit();
}

function checkValid1() {
    var flag = false;
    for (var i = 0; i < document.forms[0].elements.length; i++) {
        var e = document.forms[0].elements[i];
        if (e.name == "selectIndex1" && e.type == "checkbox") {
            if (e.checked == 1) {
                flag = true;
            }
        }
    }
    return flag;
}

function checkValid2() {
    var flag = false;
    for (var i = 0; i < document.forms[0].elements.length; i++) {
        var e = document.forms[0].elements[i];
        if (e.name == "selectIndex2" && e.type == "checkbox") {
            if (e.checked == 1) {
                flag = true;
            }
        }
    }
    return flag;
}

function CheckAll1(form) {
    for (var i = 0; i < form.elements.length; i++) {
        var e = form.elements[i];
        if (e.name == "selectIndex1") {
            e.checked = form.allbox1.checked;
        }
    }
}

function CheckAll2(form) {
    for (var i = 0; i < form.elements.length; i++) {
        var e = form.elements[i];
        if (e.name == "selectIndex2" && e.type == "checkbox") {
            e.checked = form.allbox2.checked;
        }
    }
}

</script>
</head>
<body topmargin="0" leftmargin="0" bgcolor="#FFFFFF">
<script type="text/javascript" src='/<spring:message code="app.context"/>/scripts/common.js'></script>

<%@ include file="/WEB-INF/jsp/headerDefect.jsp" %>

<div>
<p><img src="resource_files/ViewDefectListing.gif" height="28" border="0" width="411"></p>
</div>
<portlet:actionURL var="formAction">
  <portlet:param name="action" value="search" />
</portlet:actionURL>
<form:form commandName="viewDefectList" method="post" action="${formAction}">

<input name="hidActionDetail" value="" type="hidden">
<input name="hidAction" value="" type="hidden">
<input name="CheckRole" value="1110000000" type="hidden">
<input name="hidTypeOfView" value="" type="hidden">
<input name="Role" value="1000100000" type="hidden">
<p></p>
<table class="TblOut2" border="0" width="100%">
    <tbody><tr>
        <td width="8%"><b>User:</b></td>
        <td width="24%">${portletSessionScope.UserInfo.username}</td>
        <td width="12%"><b>Login Date:</b></td>
        <td width="25%">${portletSessionScope.UserInfo.loginDate}</td>
        <td width="9%"><b>Project</b></td>
        <td align="right" width="22%">
        <!-- 
        <select name="cboProjectList" class="SmallCombo" onchange="javascript:doChangeProject('DM','QueryListing','');"><option selected="selected" value="118385">OOPMS</option>
        </select>
         -->
        <form:select id="cboProjectList" path="selProject" multiple="false" size="1" items="${viewDefectList.projectMap}">
         <form:options ></form:options>
         </form:select>
         
        </td>
    </tr>
    <tr>
        <td width="8%"><b>Group:</b></td>
        <td width="24%">${portletSessionScope.UserInfo.group}</td>
        <td width="12%"><b>Position:</b></td>
        <td width="25%">${portletSessionScope.UserInfo.position}</td>
        <td width="9%"><b>Status</b></td>
        <td align="right" width="22%"><select name="cboProjectStatus" class="SmallCombo" onchange="javascript:doChangeProject('DM','QueryListing','');"><option selected="selected" value="0">On-going</option>        </select></td>

    </tr>
</tbody></table>
<p></p>
<table class="TblOut" border="0" cellpadding="1" cellspacing="1" width="100%">
    <tbody><tr>
        <td>
        <table bgcolor="#000000" border="0" cellpadding="0" cellspacing="1" width="100%">
            <tbody><tr class="Row0">
                <td width="3%"></td>
                <td colspan="1" align="center" height="19" width="59%"><b>Fixed Queries</b></td>
            </tr>
            <tr class="Row2">
                <td width="3%"></td>
                <td height="19" width="59%">
                <!-- 
                <a href="javascript:doAllDefects()">All Defects</a>
                 -->
                <a href='<portlet:renderURL>
                            <portlet:param name="action" value="goViewDefectList2"/>
                            <portlet:param name="kindOfDefect" value="All"/>
                </portlet:renderURL>'>All Defects</a>
                </td>
            </tr>
            <tr class="Row1">
                <td width="3%"></td>
                <td height="19" width="59%">
                <%--
                <a href="javascript:doAllOpenDefects()">All Open Defects</a>
                 --%>
                 <a href='<portlet:renderURL>
                            <portlet:param name="action" value="goViewDefectList2"/>
                            <portlet:param name="kindOfDefect" value="Open"/>
                 </portlet:renderURL>'>All Open Defects</a>
                </td>
            </tr>
            <tr class="Row2">
                <td width="3%"></td>
                <td height="19" width="59%">
                <%-- 
                <a href="javascript:doAllLeakageDefects()">All Leakage Defects</a>
                --%>
                <a href='<portlet:renderURL>
                           <portlet:param name="action" value="goViewDefectList2"/>
                           <portlet:param name="kindOfDefect" value="Leakage"/>
                  </portlet:renderURL>'>All Leakage Defects</a>
                </td>
            </tr>
        </tbody></table>
        </td>
    </tr>
</tbody></table>
<table class="TblOut" border="0" cellpadding="1" cellspacing="1" width="100%">
    <tbody><tr>
        <td>
        <table bgcolor="#000000" border="0" cellpadding="1" cellspacing="1" width="100%">
            <tbody><tr class="Row0">
                <td align="center" height="19" width="3%"><input name="allbox1" value="CheckAll" onclick="JavaScript: CheckAll1(document.forms[0])" type="checkbox"></td>
                <td align="center" height="19" width="59%"><b>Public Queries</b></td>
            </tr>
        </tbody></table>
        </td>
    </tr>
</tbody></table>
<table class="TblOut" border="0" cellpadding="1" cellspacing="1" width="100%">
    <tbody><tr>
        <td>
        <table bgcolor="#000000" border="0" cellpadding="1" cellspacing="1" width="100%">
            <tbody><tr class="Row0">
                <td align="center" height="19" width="3%"><input name="allbox2" value="CheckAll" onclick="JavaScript: CheckAll2(document.forms[0])" type="checkbox"></td>
                <td align="center" height="19" width="59%"><b>Private Queries</b></td>
            </tr>
        </tbody></table>
        </td>
    </tr>
</tbody></table>
<p><input name="btnAddQuery" class="Button" onclick="javascript:doAddQuery()" value="Add Query" type="button"> &nbsp;
<input name="btnDeleteQuery" class="Button" onclick="javascript:doDeleteQuery()" value="Delete Query" type="button"> &nbsp;

</p></form:form>

</body></html>