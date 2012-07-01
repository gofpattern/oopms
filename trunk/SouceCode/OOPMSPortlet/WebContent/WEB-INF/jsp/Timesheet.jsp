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
				
			
		<style>
	#feedback { font-size: 1.4em; }
	#selectable .ui-selecting { background: #FECA40; }
	#selectable .ui-selected { background: #F39814; color: white; }
	#selectable { list-style-type: none; margin: 0; padding: 0; width: 60%; }
	#selectable li { margin: 3px; padding: 0.4em; font-size: 1.4em; height: 18px; }
	</style>

	    <script type="text/javascript">
    $(document).ready(function() {
	  $('#mainTable2 tr').filter(':has(:checkbox:checked)').addClass('selected').end().click(function(event) {
	    $(this).toggleClass('selected');
	    if (event.target.type !== 'checkbox') {
	      $(':checkbox', this).attr('checked', function() {
	        return !this.checked;
	      });
	    }
	  });
	  $( "#datepicker1" ).datepicker({
	            showOn: "button",
	            buttonImage: "images/calendar.gif",
	            buttonImageOnly: true
	        });
	        $( "#datepicker2" ).datepicker({
	            showOn: "button",
	            buttonImage: "images/calendar.gif",
	            buttonImageOnly: true
	        });
	        $( "#selectable" ).selectable();
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
																					<li class="direct"><a href="<portlet:renderURL><portlet:param name="jspPage" value="/login.jsp" /></portlet:renderURL>" />Timesheet</a></li>
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


<table  border="0" >
    <tbody>
	<tr><td><strong>User</strong></td><td width="20%">Mạnh Hoàng Trương</td>
	<td ><strong>Project</strong></td>
        <td width="26%"><select class="styled" name="SearchProjectID" class="SmallCombo" value="0">
<option selected="" value="0">All</option><option value="130674">BSYS</option><option value="10047">CMM-4</option><option value="100517">CMM5</option><option value="103058">CMMI-5</option><option value="125773">FMIS.BU2-JCT</option><option value="265">FMS-1</option><option value="100461">FMS-M-2003</option><option value="101720">FMS-M-2004</option><option value="102378">FMS-TD</option><option value="125608">HIVE-EH</option><option value="128650">JCIS</option><option value="10036">MISC</option><option value="129909">Mobile Renewal</option><option value="103799">RET</option><option value="111122">Sample</option><option value="125908">SNS</option><option value="126347">Tensuite</option><option value="131848">TOKUTEN</option>
      </select></td> 
	   <td ><strong>From Date</td>
        <td  class="vAlignMid">
         <input type="text" id="datepicker1">
           
      </td>
		
	  
	  </tr>
	<tr><td><strong>Role</strong></td><td>Developer</td>
	 <td width="7%" align="left"><strong>Status</strong></td>
        <td width="26%"><select class="styled" size="1" name="SearchStatus" class="SmallCombo" value="0">
            <option value="0" selected="">All</option>
            <option value="1">Unapproved</option>
            <option value="2">Approved</option>
            <option value="3">Rejected</option>
      </select></td>
	   <td width="17%" align="left"><strong>To Date</strong></td>
        <td colspan="2" class="vAlignMid">
         <input type="text" id="datepicker2">
           
        </td><td></td></tr>
</tbody></table>
		<p><input type="button" class="button blue small" name="Search" onClick="location.href='timesheetList.html'" value="Search" class="Button" >		  
		
		  <input type="button" class="button blue small" name="Addnew" onclick="location.href='addTimeSheet.html'" value="Addnew" class="Button" />
		 </p>


<table id="mainTable2" border="0" cellpadding="3" cellspacing="0" width="100%" >

   <tbody><tr >
        <th width="5%"><input type="checkbox" name="allbox" value="Check All"onClick="JavaScript:checkAll();"></th>
        <th width="10%">Date</th>    
        <th width="10%">Project</th>    
        <th width="5%" >Work</th>
        <th width="5%">Process</th>      
        <th width="5%">Time</th>
        <th width="50%">Description</th>
        <th width="5%">Status</th>
    </tr>    
   
    <tr>
       <td class="cb"><input type="checkbox" value="yes"></td>
        <td ><font color="">05/11/12</font></td>
        <td ><font color="">TOKUTEN</font></td>      
        <td><font color="">Create</font></td>
        <td><font color="">Coding</font></td>        
        <td align="center"><font color="">7.0</font></td>
        <td>[TOKUTEN] Coding AWRF_W_WD-002 <font color="">
 </font></td>
        <td><font color="">unapproved</font></td>
    </tr>

</tbody></table>
<p> <input type="button" class="button blue small" name="Update" onclick="javascript:doUpdate()" value="Update" class="Button" />        
		  <input type="button" class="button blue small" name="Delete" onClick="javascript:doDelete()" value="Delete" class="Button"></p>
	
	
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