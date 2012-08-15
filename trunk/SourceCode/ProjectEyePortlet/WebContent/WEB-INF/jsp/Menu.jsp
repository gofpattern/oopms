<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>
<link rel="stylesheet" type="text/css" href="/<spring:message code="app.context"/>/resource-menu/jquerycssmenu.css" />
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource-menu/jquerycssmenu.js"></script>

<%-- Create Menu Bar --%>
<script type="text/javascript">
var arrowimages={down:['downarrowclass', 'arrow-down.gif', 25], right:['rightarrowclass', 'arrow-right.gif']}

jquerycssmenu.buildmenu("myjquerymenu", arrowimages, "/ResourceServingWebapp/resource-menu//");

</script>

<!--[if lte IE 7]>
<style type="text/css">
html .jquerycssmenu{height: 1%;} /*Holly Hack for IE7 and below*/
</style>
<![endif]-->



<body>

		  <div id="nav">
		<ul>
			<li>
	<portlet:renderURL var="renderAction">
    		<portlet:param name="jspPage" value="/ProjectEyeHome.jsp" />
    	</portlet:renderURL>
		<a href="${renderAction}">ProjectEye Home</a>
	</li>
<c:forEach var="subMenu" items="${MenuBar}">
  <li><a href='<portlet:renderURL>
  	<portlet:param name="action" value="${subMenu.actionId}"/>
  	<portlet:param name="projectId" value="${projectId}" />
  		</portlet:renderURL>'>${subMenu.name}</a>
   <c:if test="${not empty subMenu.menuItemList}">
    <ul>
      <c:forEach var="menuItem" items="${subMenu.menuItemList}">
        <li>
        	<a href='<portlet:renderURL>
        	<portlet:param name="action" value="${menuItem.actionId}"/>
        	<portlet:param name="projectId" value="${projectId}" />
        	</portlet:renderURL>'>${menuItem.name}</a>
        </li>
      </c:forEach>
    </ul>  
   </c:if>
  </li>
</c:forEach>
		</ul>
	</div>
<!--
<div id="myjquerymenu" class="jquerycssmenu">
<ul>
	<li>
	<portlet:renderURL var="renderAction">
    		<portlet:param name="jspPage" value="/ProjectEyeHome.jsp" />
    	</portlet:renderURL>
		<a href="${renderAction}">ProjectEye Home</a>
	</li>
<c:forEach var="subMenu" items="${MenuBar}">
  <li><a href='<portlet:renderURL>
  	<portlet:param name="action" value="${subMenu.actionId}"/>
  	<portlet:param name="projectId" value="${projectId}" />
  		</portlet:renderURL>'>${subMenu.name}</a>
   <c:if test="${not empty subMenu.menuItemList}">
    <ul>
      <c:forEach var="menuItem" items="${subMenu.menuItemList}">
        <li>
        	<a href='<portlet:renderURL>
        	<portlet:param name="action" value="${menuItem.actionId}"/>
        	<portlet:param name="projectId" value="${projectId}" />
        	</portlet:renderURL>'>${menuItem.name}</a>
        </li>
      </c:forEach>
    </ul>  
   </c:if>
  </li>
</c:forEach>
</ul>

<br style="clear: left" />
</div>
       -->  
		  


	  
	  
</body>