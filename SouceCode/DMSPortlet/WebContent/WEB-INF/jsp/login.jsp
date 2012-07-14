<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html><head>
<script language="javascript" src="resource_files/fnc_CookieFunctions.js"></script>
<script language="javascript" src="resource_files/CommonScript.js"></script>
<script language="javascript" src="resource_files/BigInt.js"></script>
<script language="javascript" src="resource_files/rsa.html"></script>
<script language="javascript" src="resource_files/sha1.js"></script>
<title>DMS Login Home Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="StyleSheet" href="resource_files/DMSStyleSheet.css" type="text/css">
</head>
<body topmargin="0" leftmargin="0" onkeypress="javascript:setKeypress(event.which)" bgcolor="#FFFFFF">
<table height="51" border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
        <tr>
            <td height="51" background="resource_files/bgr_header.gif" bgcolor="#310c52" width="212"> <img src="resource_files/defect_logop1.gif" border="0"><br>
            </td>
            <td align="left" height="51" background="resource_files/bgr_header.gif" bgcolor="#310C52" valign="top" width="50%"><img src="resource_files/defect_logop2.gif" border="0"></td>
            <td align="right" height="51" background="resource_files/bgr_header.gif" bgcolor="#310C52" valign="top" width="50%"><img src="resource_files/header.gif" border="0"></td>
        </tr>
        <tr>
            <td align="left" bgcolor="#000084" width="111"><img src="resource_files/logo2.gif" border="0"></td>
            <td colspan="2" align="left" bgcolor="#310C52" valign="middle"></td>
        </tr>
    </tbody>
</table>
<div>
<p><br>
<img src="resource_files/Login.gif" height="28" border="0" width="411"></p>
</div>
<hr class="Line1">
<br>

<%-- Define actionURL --%>
<portlet:actionURL var="formAction">
  <portlet:param name="action" value="login" />
</portlet:actionURL>

<form:form method="post" commandName="loginForm" action="${formAction}">
<table border="0" width="411">
    <tbody><tr>
        <td align="right" width="70%"><b>User name</b></td>
        <td width="5%"></td>
        <td align="right" width="25%"><input name="username" class="SmallBox" type="text"></td>
    </tr>
    <tr>
        <td align="right" width="70%"><b>Password</b></td>
        <td width="5%"></td>
        <td align="right" width="25%"><input name="password" class="SmallBox" type="password"></td>
    </tr>
    <tr>
    </tr>
    <tr>
        <td align="right" width="70%"></td>
        <td width="5%"></td>
        <td width="25%">
        <!-- 
        <p><input name="Login" class="Button" onclick="DoLogin()" value="Login" type="button"></p>
        -->
        <p><form:button id="Login" name="Login" value="Login">Login</form:button></p>
         
        </td>
    </tr>
</tbody></table>
</form:form>
<form method="POST" action="DMSServlet" name="frmLogin">
<input name="hidActionDetail" value="" type="hidden">
<input name="hidAction" value="" type="hidden">
<input name="username" type="hidden">
<input name="password" type="hidden">
</form>
<div>
<p><br><br><br><br><br><br><br><br></p>
<hr class="Line1">
<table>
    <tbody><tr>
        <td width="10%"></td>
        <td width="90%"><font class="label1">
        Copyright © 2012-2017 Open-Ones. Online Open Management Suite, DMS Version 0.1.0<br>
        </font>
        </td>
    </tr>
</tbody></table>
</div>
<script language="Javascript">
    var key;
    //LogForm(frmLogin, "load");
    document.frmLoginPost.username.focus();
function DoLogin() {
    //Save Cookie:
    //LogForm(document.frmLogin, "save");
    var form = document.frmLogin;
    document.frmLogin.username.value = document.frmLoginPost.username.value;
    document.frmLogin.password.value = hex_sha1(document.frmLoginPost.password.value);
    document.frmLogin.hidAction.value = "ViewDefectListing";
    document.frmLogin.action = "DMSServlet";
    document.frmLogin.submit();
}
function setKeypress(key) {
    if (navigator.appName != "Netscape") {
        key = event.keyCode;
    }
    if (key==13) {
        DoLogin();
    }
}
</script>

</body></html>