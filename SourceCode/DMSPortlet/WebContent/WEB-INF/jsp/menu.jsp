<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<portlet:defineObjects />

<html xmlns="http://www.w3.org/1999/xhtml">
<head id="ctl00_Head1">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>
	Demo Menu - version 0.2.x
</title>
<link href="/<spring:message code="app.context"/>/menu_resource/Advanced.css" type="text/css" rel="stylesheet">
<link href="/<spring:message code="app.context"/>/menu_resource/Ajax.css" type="text/css" rel="stylesheet">
<link href="/<spring:message code="app.context"/>/menu_resource/AjaxJQuery.css" type="text/css" rel="stylesheet">
<link href="/<spring:message code="app.context"/>/menu_resource/GridView.css" type="text/css" rel="stylesheet">
<link href="/<spring:message code="app.context"/>/menu_resource/menustyles.css" type="text/css" rel="stylesheet">
<style type="text/css">
	.ctl00_MainMenu_0 { background-color:white;visibility:hidden;display:none;position:absolute;left:0px;top:0px; }
	.ctl00_MainMenu_1 { text-decoration:none; }
	.ctl00_MainMenu_2 {  }
	.ctl00_MainMenu_3 { border-style:none; }
	.ctl00_MainMenu_4 {  }
	.ctl00_MainMenu_5 {  }
	.ctl00_MainMenu_6 { border-style:none; }
	.ctl00_MainMenu_7 { padding:2px 0px 2px 0px; }
	.ctl00_MainMenu_8 {  }
	.ctl00_MainMenu_9 { border-style:none; }
	.ctl00_MainMenu_10 {  }
	.ctl00_MainMenu_11 { border-style:none; }
	.ctl00_MainMenu_12 {  }

</style></head>
<body style="padding-bottom: 29px;">

<script src="/<spring:message code="app.context"/>/menu_resource/WebResource.js" type="text/javascript"></script>


<script src="/<spring:message code="app.context"/>/menu_resource/ScriptResource_004.js" type="text/javascript"></script>
<script src="/<spring:message code="app.context"/>/menu_resource/ScriptResource_002.js" type="text/javascript"></script>


<script src="/<spring:message code="app.context"/>/menu_resource/ScriptResource_003.js" type="text/javascript"></script>

<script src="/<spring:message code="app.context"/>/menu_resource/ScriptResource.js" type="text/javascript"></script>

<script language="javascript" src="/<spring:message code="app.context"/>/menu_resource/dropdown_menu_hack.js" type="text/javascript"></script>

<script type="text/javascript" src='/MenuPortlet/scripts/common.js'></script>
<script type="text/javascript">
//<![CDATA[
var ctl00_MainMenu_Data = new Object();
ctl00_MainMenu_Data.disappearAfter = 100;
ctl00_MainMenu_Data.horizontalOffset = 0;
ctl00_MainMenu_Data.verticalOffset = 0;

//]]>
</script>
<form name="Menu" method="post" action="#">
    <div id="SMASMainPage">
        <div class="GridFull">

            <div class="MenuNav">
                <div class="GridFix">
                    <div class="bgMenu">
                        <table border="0" cellpadding="0" cellspacing="0" width="100%">
                            <tbody><tr>
                                <td align="left" valign="middle">
                                    <div class="navigationArea">
   
   <%-- Menu Level1.START --%>
   <table id="ctl00_MainMenu" class="dmRootmenu ctl00_MainMenu_5 ctl00_MainMenu_2" border="0" cellpadding="0" cellspacing="0">
	<tbody>
   <tr>
     <c:forEach var="subMenu" items="${portletSessionScope.MenuBar}">
		<td onmouseover="Menu_HoverStatic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="${subMenu.id}">
          <table class="dmRootItem ctl00_MainMenu_4" border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
              <tr>
				<td style="white-space: nowrap;">
                 <a class="ctl00_MainMenu_1 dmRootItem ctl00_MainMenu_3" href='<portlet:renderURL><portlet:param name="action" value="${subMenu.actionId}"/></portlet:renderURL>' style="border-style: none; font-size: 1em;"><img src="/<spring:message code="app.context"/>/menu_resource/${subMenu.iconPath}" alt="" style="border-style: none; vertical-align: middle;">&nbsp; ${subMenu.name}</a>
                </td>
			 </tr>
            </tbody>
          </table>
        </td>
        <td style="width: 3px;"></td>
      </c:forEach>
	</tr>
</tbody>
   </table>
   <%-- Menu Level1.END --%>
   
   <%-- Menu Level2.START --%>
  <c:forEach var="subMenu" items="${portletSessionScope.MenuBar}">
    <div id="ctl00_MainMenun0Items" class="ctl00_MainMenu_0 dmSubmenu ctl00_MainMenu_8">
      <table border="0" cellpadding="0" cellspacing="0">
        <tbody>
          <%-- Scan Menu Items --%>
          <c:set var="menuItemList" value="${subMenu.menuItemList}"/>
          <c:forEach var="menuItem" items="${menuItemList}">
            <tr onmouseover="Menu_HoverDynamic(this)" onmouseout="Menu_Unhover(this)" onkeyup="Menu_Key(event)" id="ctl00_MainMenun6">
              <td>
                <table class="dmItem1 ctl00_MainMenu_7" border="0" cellpadding="0" cellspacing="0" width="100%">
                  <tbody>
                    <tr>
                      <td style="white-space: nowrap; width: 100%;">
                        <a class="ctl00_MainMenu_1 dmItem1 ctl00_MainMenu_6" href='<portlet:renderURL><portlet:param name="action" value="${menuItem.actionId}"/></portlet:renderURL>' style="border-style: none; font-size: 1em;"><img src="/<spring:message code="app.context"/>/menu_resource/misamples2.gif" alt="" style="border-style: none; vertical-align: middle;">&nbsp;&nbsp;${menuItem.name}</a>
                        </td>
                    </tr>
                  </tbody>
                </table>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
     </div>
       
  </c:forEach>
                                    </div>
                                </td>
                                <td style="padding-right: 5px;" align="right" valign="middle">
                                    
                                    &nbsp;

                                </td>
                            </tr>
                        </tbody></table>
                    </div>
                </div>
            </div>
        </div>
        <%--Content --%>



    </div>
</form>


</body></html>