<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html><head>
<title>View Defect Listing</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="StyleSheet" href="/<spring:message code="app.context"/>/resource_files/DMSStyleSheet.css" type="text/css">
<script src="/<spring:message code="app.context"/>/resource_files/CommonScript.js"></script>
<script src="/<spring:message code="app.context"/>/resource_files/utils.js"></script>
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

function doDeleteQuery(formName, actionUrl) {
    var form = document.forms[formName];

    if (checkValid1() || checkValid2()) {
        if (confirm("Do you want to delete selected records, continue?")) {
            form.hidAction.value = "DM";
            form.hidActionDetail.value = "DeleteQuery";
            form.action = actionUrl;
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

<%--
<%@ include file="/WEB-INF/jsp/headerDefect.jsp" %>
 --%>
<%@ include file="/WEB-INF/jsp/menu.jsp" %>
<div>
<p><img src="/<spring:message code="app.context"/>/resource_files/ViewDefectListing.gif" height="28" border="0" width="411"></p>
</div>
<portlet:actionURL var="formAction">
  <portlet:param name="action" value="search" />
</portlet:actionURL>

<portlet:renderURL var="goAddQuery">
  <portlet:param name="action" value="goAddQuery" />
</portlet:renderURL>

<portlet:actionURL var="doAddQuery">
  <portlet:param name="action" value="deleteQuery" />
</portlet:actionURL>

<form:form name="ViewMode" commandName="viewDefectMode" method="post" action="${formAction}">

<input name="hidActionDetail" value="" type="hidden">
<input name="hidAction" value="" type="hidden">
<input name="CheckRole" value="1110000000" type="hidden">
<input name="hidTypeOfView" value="" type="hidden">
<input name="Role" value="1000100000" type="hidden">
<p></p>
<%@ include file="/WEB-INF/jsp/header2Defect.jsp" %>
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
<p><input name="btnAddQuery" class="Button" onclick='submitAction("${portletNamespace}ViewMode", "${goAddQuery}")' value="Add Query" type="button"> &nbsp;
<input name="btnDeleteQuery" class="Button" onclick='doDeleteQuery"${portletNamespace}ViewMode", "${doDeleteQuery}")' value="Delete Query" type="button"> &nbsp;

</p></form:form>

</body></html>