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

	<link rel="icon" href="https://c15027075.ssl.cf2.rackcdn.com/favicon.ico" type="image/x-icon">
	<link type="text/css" href="../OOPMSPortlet/resource_files/css/screen.css" rel="Stylesheet" />
	<link type="text/css" href="../OOPMSPortlet/resource_files/css/ui-lightness/jquery-ui-1.8.21.custom.css" rel="Stylesheet" />	
<link type="text/css" href="../OOPMSPortlet/resource_files/css/common.css" rel="Stylesheet" />	
<link type="text/css" href="../OOPMSPortlet/resource_files/css/uportal.css" rel="Stylesheet" />	
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.21.custom.min.js"></script>
<script type="text/javascript" src="js/form-elements.js"></script>
	<meta name="robots" content="noindex, nofollow">
			
						<link rel="stylesheet" type="text/css" href="../OOPMSPortlet/resource_files/css/manage.css" media="all">
				
	<link rel="stylesheet" type="text/css" href="../OOPMSPortlet/resource_files/css/datepicker.css" media="all">
	<link rel="fluid-icon" href="https://c15027075.ssl.cf2.rackcdn.com/images/apple-touch-icon-114x114.png">
	
	<script type="text/javascript" async="" src="../OOPMSPortlet/resource_files/css/ga.js"></script><script language="javascript" type="text/javascript" src="../OOPMSPortlet/resource_files/css/jquery.js"></script>
	<script language="javascript" type="text/javascript" src="../OOPMSPortlet/resource_files/css/jquery.cookie.js"></script>
	<script language="javascript" type="text/javascript" src="../OOPMSPortlet/resource_files/css/default.js"></script>
	<script language="javascript" type="text/javascript" src="../OOPMSPortlet/resource_files/css/manage.js"></script>
	
		
			<link rel="stylesheet" type="text/css" href="../OOPMSPortlet/resource_files/css/print.css" media="print">	
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
<script type="text/javascript">
var todoInputID;
var page = 10;
$(function(){
	$('#domain').fieldreplace();
	$('a.setstatus').live('click', function(){setStatus(this); return false; });
	$('a.setlights').live('click', function(){ setLights(this); return false; });
	$('a.setimportant').live('click', function(){ setImportant(this); return false; });
	$('a.showtodos').live('click', function(){showTodos(this); return false; });
	$('a.addtodos').live('click', function(){addTodos(this); return false; });
	$('a.edittodo').live('click', function(){editTodo(this); return false; });	
	$('a.deletetodo').live('click', function(){deleteTodo(this); return false; });
	$('input.addtodo').live('click', function(){addTodo(this); return false; });
	$('input.todosfield').live('focus', function(){ $(this).addClass('highlight'); });
	$('input.todosfield').live('blur', function(){ $(this).removeClass('highlight'); });	
	$('input.todosfield').live('focus', function(){ todoInputID = $(this).next('input'); });
	$('select#clientID').live('change', function(){ addClient(this); return false; });	
	$('#addCollaborator').live('click', function(){ addCollaborator(); return false; });
	$('.deletecollab').live('click', function(){ deleteCollaborator(this); return false; });
	$('.selectowner').live('click', function(){ selectOwner(this); return false; });		
	$('a.countersub, a.counteradd').live('click', function(){updateCounter(this); return false; });
	$('.timer').live('click', function(){ toggleTimer(this); return false; });
	$('textarea.tidy').live('focus', function(){ tidyTextarea(this); });
	$('textarea.tidy').live('blur', function(){ tidyTextarea(this); });	
	$('.file').live('mousedown', function(){ file = true; });
	$('.showreplies').live('click', function(){ showReplies(this); return false; });
	$('.showreplyfield').live('click', function(){ showReplyField(this); return false; });
	$('.addreply').live('click', function(){ addReply(this); return false; });
	$('.showfile').live('click', function(){ showFile(this); return false; });
	$('.deletecomment').live('click', function(){ deleteComment(this); return false; });
	$('.addcomment').live('click', function(){ return addComment(this); });
	$('.removepop').live('click', function(){ $(this).removeClass('populated'); });
	$('.showhidden').live('click', function(){ $(this).hide().closest('.parent').prev('table.default').find('.newitem').show(); return false; });
	$('a.showform').live('click', function(){showForm(this); return false; });
	$('#cancel').live('click', function(){hideForm(this); return false; });	
	$('.hidedeal').live('click', function(){ $(this).closest('div').fadeOut(); $.cookie('helper_deal', '1', { expires: 999, path: '/manage' }); return false; });
	$('.showcalendar').live('click', function(){ return false; });
	$('.showtip[title]').live('mouseover', function(){
		var target = $(this);
		if (target.data('qtip')) { return false; }
		target.qtip({ show: { ready: true, delay: 800, effect: false }, hide: { event: 'mouseleave click', effect: false }, style: { name: 'cream', tip: true, width: { max: 350 }}});
		target.trigger('mouseover');
	});
	$('a.boldbutton').live('click', function () {
		formatting(this, 'bold');
		return false
	});
	$('a.italicbutton').live('click', function () {
		formatting(this, 'italic');
		return false
	});
	$('a.bulletbutton').live('click', function () {
		formatting(this, 'bullet');
		return false
	});
	$('a.h1button').live('click', function () {
		formatting(this, 'h1');
		return false
	});
	$('a.h2button').live('click', function () {
		formatting(this, 'h2');
		return false
	});
	$('a.h3button').live('click', function () {
		formatting(this, 'h3');
		return false
	});
	$('a.urlbutton').live('click', function () {
		formatting(this, 'url');
		return false
	});
	$('div.error').delay('2000').fadeOut('slow');
	if (!$.cookie('newsmessage')) $('.newsmessage').show(); else $('.newsmessage').hide();
	$('.closemessage').live('click', function(){ $('.newsmessage').fadeOut(); $.cookie('newsmessage', '1', { expires: 999, path: '/manage' }); return false; });
	$(window).keypress(function(e) {
		if (e.keyCode == 13 && $('.todosfield').hasClass('highlight')) {
			addTodo(todoInputID);
		}
	});
});
</script>

	<script type="text/javascript">
		$(function(){
			$('.loadactivity').live('click', function(){
				$('#keepalive').smartupdaterStop();
				$('.loadactivityparent').html('<img src="/static/images/loader.gif" alt="" />');
				$.get('/manage/activity_ajax/'+page, function(data){
					if (!data.match('noactivity')){
						$('#activity').append(data);
						$('tr.new').show();
						page += 10;	
					}
					$('.loadactivityparent').html('<a href="/manage/activity/'+page+'" class="button small blue loadactivity">More</a>');
				});
				return false;
			});
			$('#keepalive').smartupdater({ url: '/manage/activity_ajax/0', minTimeout: 10000 }, function(data){
				$('#activity').html(data);
			});
		});
	</script>

<script type="text/javascript">
Highcharts.setOptions({
	colors: ['#89A54E', '#4572A7', '#DB843D', '#92A8CD', '#80699B',	'#3D96AE', '#B5CA92', '#AA4643', '#A47D7C'],
    chart: {
        style: {
            fontFamily: '"Helvetica Neue", Helvetica, Arial, sans-serif'
        }
    },
    credits: false
});
</script>

	
		
	
<div id="keepalive"></div>

<div class="col1">

	
	<div class="hidden clearfix"></div>
		
			<div class="helper start clearfix">
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
						
				
		
			<div class="calendar">
			<table class="calendar">

<tbody><tr>
<th class="first"><a href="https://truong.projectbubble.com/manage/dashboard/2012/05">&lt;&lt;</a></th>
<th class="middle" colspan="5"><a href="https://truong.projectbubble.com/manage/calendar">June&nbsp;2012</a></th>
<th class="last"><a href="https://truong.projectbubble.com/manage/dashboard/2012/07">&gt;&gt;</a></th>
</tr>

<tr class="weekdays">
<td>Su</td><td>Mo</td><td>Tu</td><td>We</td><td>Th</td><td>Fr</td><td>Sa</td>
</tr>

<tr>
<td><div class="day blank">&nbsp;</div></td><td><div class="day blank">&nbsp;</div></td><td><div class="day blank">&nbsp;</div></td><td><div class="day blank">&nbsp;</div></td><td><div class="day blank">&nbsp;</div></td><td><div class="day">1</div></td><td><div class="day">2</div></td>
</tr>

<tr>
<td><div class="day">3</div></td><td><div class="day">4</div></td><td><div class="day">5</div></td><td><div class="day">6</div></td><td><div class="day">7</div></td><td><div class="day">8</div></td><td><div class="day">9</div></td>
</tr>

<tr>
<td><div class="day">10</div></td><td><div class="day">11</div></td><td><div class="day">12</div></td><td><div class="day">13</div></td><td><div class="day">14</div></td><td><div class="day">15</div></td><td><div class="day">16</div></td>
</tr>

<tr>
<td><div class="day">17</div></td><td><div class="day">18</div></td><td><div class="day">19</div></td><td><div class="day today">20</div></td><td><div class="day">21</div></td><td><div class="day">22</div></td><td><div class="day">23</div></td>
</tr>

<tr>
<td><div class="day">24</div></td><td><div class="day">25</div></td><td><div class="day">26</div></td><td><div class="day">27</div></td><td><div class="day">28</div></td><td><div class="day">29</div></td><td><div class="day">30</div></td>
</tr>

</tbody></table>		</div>
				
	<div class="smallmodules clearfix">
	
				
				
	</div>
	
			
		
				
		
	<div class="module">
	
		<h4>Storage</h4>
		
		<div class="quota">
			<div class="used" style="width: 0%">0%</div>
		</div>
		
		<p><small>Using 0MB out of your 5GB quota.</small></p>
		
	</div>

	<br class="clear">	
	
</div>
	
</div>		</div>

		
		</div>

	</div>

	<div id="footer" class="content clearfix">

		<div class="container">
		
						
				<p class="links">
					<a href="https://truong.projectbubble.com/privacy" target="_blank">Privacy Policy</a> |
					<a href="https://truong.projectbubble.com/terms-of-service" target="_blank">Terms of Service</a> |
											<a href="https://truong.projectbubble.com/manage/referrals" target="_blank">Refer</a> |
										<a href="https://truong.projectbubble.com/help" target="_blank">Help</a>
				</p>
				
				<p class="copyright">
					<a href="http://projectbubble.com/" target="_blank"><img src="../OOPMSPortlet/resource_files/css/marque.png" class="marque" alt="Project Bubble"></a>
					Managed with <a href="http://projectbubble.com/" target="_blank">Project Bubble</a>
				</p>
			
						
		</div>
		
	</div>


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