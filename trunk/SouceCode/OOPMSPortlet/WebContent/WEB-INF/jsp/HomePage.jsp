<!DOCTYPE html>
<!-- saved from url=(0049)https://truong.projectbubble.com/manage/dashboard -->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ page import="javax.portlet.PortletSession"%>
 <portlet:defineObjects /> 
 
	<title>Dashboard: Project Bubble</title>

	<jsp:include page="header.jsp"/>
		<style type="text/css">
					</style>
		
			<script type="text/javascript">
			$.ajaxSetup({
			    cache: false
			});
		</script>
	
</head>
<body class="">

<div class="bg">

	<div id="header" class="loggedin">

		<div class="container ">

			<div id="logo" class="loggedin">
			
							
					<a href="../OOPMSPortlet/resource_files/css/Dashboard  Project Bubble.htm" class="direct">
																			<span>Project Management System</span>
											</a>
				
								
			</div>
			
			<div id="toolbar" class="loggedin">
			
					
										
						<ul>
							<li class="last showdropdown">
								<a href="https://truong.projectbubble.com/manage/account"><span class="icon account"></span> Account</a>
								<ul class="dropdown" style="display: none; ">
									<li><a href="https://truong.projectbubble.com/manage/account">My Account</a></li>
									<li><a href="https://truong.projectbubble.com/manage/company">My Company</a></li>
																			<li><a href="https://truong.projectbubble.com/pricing">Change Plan</a></li>
										<li><a href="https://truong.projectbubble.com/manage/addons">Add-ons</a>
																											</li><li class="last"><a href="<portlet:renderURL><portlet:param name="jspPage" value="/login.jsp" /></portlet:renderURL>" />Logout</a></li>
								</ul>
							</li>
						</ul>
						
						<div class="user">
							<img src="../OOPMSPortlet/resource_files/css/noavatar.gif" alt="n/a" width="18" class="avatar">					
							<%=portletSession.getAttribute("USER",  PortletSession.APPLICATION_SCOPE)%>						</div>
						
																				
					
			</div>				
			
			
				<div id="topsearch">
					<form action="https://truong.projectbubble.com/manage/search" method="post" class="ajaxsearch">
						<input type="text" class="searchbox" id="searchquery" autocomplete="off">
						<label for="searchquery" class="searchbutton"></label>
					</form>
					<div class="ajaxresults clearfix"><img src="../OOPMSPortlet/resource_files/css/loader.gif" alt="" class="loader"></div>
				</div>
				
				<div id="navigation">
					<ul>
												<li class="narrow active">
							<a href="../OOPMSPortlet/resource_files/css/Dashboard  Project Bubble.htm" class="direct">
																	Dashboard															</a>
						</li>
																					<li class="direct">  <a href='<portlet:renderURL><portlet:param name="action" value="timesheet"/>
            <portlet:param name="userId"><jsp:attribute name="value"><%=portletSession.getAttribute("USER",  PortletSession.APPLICATION_SCOPE)%></jsp:attribute></portlet:param></portlet:renderURL>'>Timesheet</a></li>
																						<li class=""><a href="https://truong.projectbubble.com/manage/tasks" class="direct">DMS</a></li>
																						<li class=""><a href="https://truong.projectbubble.com/manage/calendar" class="direct">Tasks</a></li>
																						<li class=""><a href="https://truong.projectbubble.com/manage/calendar" class="direct">Risk&Issue</a></li>
																						<li class=""><a href="https://truong.projectbubble.com/manage/calendar" class="direct">Report</a></li>
																						<li class=""><a href="https://truong.projectbubble.com/manage/timesheets">Change Request</a></li>
																						<li class=""><a href="https://truong.projectbubble.com/manage/timesheets">Cost</a></li>	
																						<li class=""><a href="https://truong.projectbubble.com/manage/timesheets">Requirement</a></li>		
																		</ul>
				</div>
			
		</div>

	</div>

	<div id="content" class="content loggedin">

		<div class="container ">

			<div id="tpl-manage" class="clearfix">
<div id="tpl-dashboard">
<script type="text/javascript" src="../OOPMSPortlet/resource_files/css/highcharts.js"></script>
<script type="text/javascript" src="../OOPMSPortlet/resource_files/css/custom.js"></script>
	
		
	
<div id="keepalive"></div>

<div class="col1">

	
	<div class="hidden clearfix"></div>
		
			<div class="helper start clearfix">
      <a href='<portlet:renderURL>
            <portlet:param name="action" value="timesheet"/>
            <portlet:param name="userId">
                <jsp:attribute name="value">
                  <%=portletSession.getAttribute("USER",  PortletSession.APPLICATION_SCOPE)%>
                </jsp:attribute>
            </portlet:param>    
        </portlet:renderURL>
        '>Test</a>
				<h2><strong>Welcome to your dashboard, <%=portletSession.getAttribute("USER",  PortletSession.APPLICATION_SCOPE)%> </strong></h2>
				<h4><strong>Step 1: Let's create a new project</strong></h4>
				<p>Click on the button below to create your first project.</p>
				<br><br><br>
				<p><a href="https://truong.projectbubble.com/manage/setup" class="button big showform">Create New Project</a></p>
				<br class="clear"><br>
			</div>
				
				
		
		
		
		
	
	<div class="module">
	
		<h4>Recent Activity</h4>

		<table class="default activity">
			<thead>
				<tr>
					<th colspan="2" class="first last">Activity Feed</th>
				</tr>
			</thead>
			<tbody id="activity">	<!--noactivity-->
	<tr>
		<td colspan="2">
			There has not been any recent activity yet.
		</td>
	</tr>
</tbody>
		</table>	
		
					<div class="clearfix">
				<p class="loadactivityparent"><a href="https://truong.projectbubble.com/manage/activity" class="button blue small loadactivity">More</a></p>
			</div>
				
	</div>
		
	<p style="text-align: right;"><a href="https://truong.projectbubble.com/manage/dashboard#" class="button grey" id="totop">Back to top</a></p>	
	
</div>
<div class="col2">
	
				
				
											<div class="helper upgrade clearfix">			
					<h4><strong>Setup Progress</strong></h4>
					
					<div class="progress"><div class="progressbar" style="width:0%"></div></div>
													
					<ol>
						<li class="">Create nre project</li>
						<li class="">Invite users</li>
						<li class="">Add products</li>
						<li class="">Add tasks</li>
					</ol>
					
									
				</div>
						
				
		
<jsp:include page="calendar.jsp"/>
	
</div>
	
</div>		</div>

		
		</div>

	</div>

	<jsp:include page="footer.jsp"/>


</div>


<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-6656902-4']);
  _gaq.push(['_setDomainName', 'projectbubble.com']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>


</body></html>