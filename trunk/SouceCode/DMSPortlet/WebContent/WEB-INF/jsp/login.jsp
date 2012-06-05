<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html><head>
<script language="javascript" src="resource_files/fnc_CookieFunctions.js"></script>
<script language="javascript" src="resource_files/CommonScript.js"></script>
<script language="javascript" src="resource_files/BigInt.js"></script>
<script language="javascript" src="resource_files/rsa.html"></script>
<script language="javascript" src="resource_files/sha1.js"></script>
<title>DMS Login Home Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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

<form name="frmLoginPost">
<table border="0" width="411">
    <tbody><tr>
        <td align="right" width="70%"><b>User name</b></td>
        <td width="5%"></td>
        <td align="right" width="25%"><input name="txtAccount" class="SmallBox" type="text"></td>
    </tr>
    <tr>
        <td align="right" width="70%"><b>Password</b></td>
        <td width="5%"></td>
        <td align="right" width="25%"><input name="txtPassword" class="SmallBox" type="password"></td>
    </tr>
    <tr>
    </tr>
    <tr>
        <td align="right" width="70%"></td>
        <td width="5%"></td>
        <td width="25%">
        <p><input name="Login" class="Button" onclick="DoLogin()" value="Login" type="button"></p>
        </td>
    </tr>
</tbody></table>
</form>
<form method="POST" action="DMSServlet" name="frmLogin">
<input name="hidActionDetail" value="" type="hidden">
<input name="hidAction" value="" type="hidden">
<input name="txtAccount" type="hidden">
<input name="txtPassword" type="hidden">
</form>
<div>
<p><br><br><br><br><br><br><br><br></p>
<hr class="Line1">
<table>
    <tbody><tr>
        <td width="10%"></td>
        <td width="90%"><font class="label1">
        Copyright © 2002-2007 FPT-Software. Fsoft Management Suite 2006, DMS Version 3.8.2<br>
        </font>
        </td>
    </tr>
</tbody></table>
</div>
<script language="Javascript">
    var key;
    //LogForm(frmLogin, "load");
    document.frmLoginPost.txtAccount.focus();
function DoLogin() {
    //Save Cookie:
    //LogForm(document.frmLogin, "save");
    var form = document.frmLogin;
    document.frmLogin.txtAccount.value = document.frmLoginPost.txtAccount.value;
    document.frmLogin.txtPassword.value = hex_sha1(document.frmLoginPost.txtPassword.value);
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