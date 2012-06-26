<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>	
	<title>Project Management System123</title>

	<link rel="icon" href="https://c15027075.ssl.cf2.rackcdn.com/favicon.ico" type="image/x-icon">
	<link rel="stylesheet" type="text/css" href="../OOPMSPortlet/resource_files/css/screen.css" media="all">
    <link rel="stylesheet" type="text/css" href="./OOPMSPortlet/resource_files/css/screen.css" media="all">
	<meta name="robots" content="noindex, nofollow">
	
		
		
	<link rel="stylesheet" type="text/css" href="./css/datepicker.css" media="all">
	<link rel="fluid-icon" href="https://c15027075.ssl.cf2.rackcdn.com/images/apple-touch-icon-114x114.png">
	
	<script type="text/javascript" async="" src="./css/ga.js"></script><script language="javascript" type="text/javascript" src="./css/jquery.js"></script>
	<script language="javascript" type="text/javascript" src="./css/jquery.cookie.js"></script>
	<script language="javascript" type="text/javascript" src="./css/default.js"></script>
	<script language="javascript" type="text/javascript" src="./css/manage.js"></script>
	
		
		
			<script type="text/javascript">
			$(function(){
				$('.showlogin').click(function(){
					$('.callus').hide();
					$('#login').fadeIn();
					return false;
				});
			});
		</script>
	
</head>
<body class="">

<div class="bg">

	<div id="header" class="">

		<div class="container narrow">

			<div id="logo" class="">
			
							
					<a href="https://secure.projectbubble.com/"><img src="./css/logo.png" alt="Project Bubble" id="fDI2MDc" class="pic"></a>
					
								
			</div>
			<%-- Define actionURL --%>
<portlet:actionURL var="formAction">
  <portlet:param name="action" value="login" />
</portlet:actionURL>
      
			<div id="toolbar" class="">
			
									
					<div id="login">
		
						<form:form method="post" commandName="loginForm" action="${formAction}">
		
							<input type="text" name="username" title="your@email.com" class="longer formelement replacer">
							<input type="password" name="password" title="password" class="formelement replacer">
							
		                      <form:button id="Login"  class="button blue small" name="Login" value="Login"/>
							<br class="clear">
		
							<div class="extras">
								<label for="remember">Remember me</label>
								<input type="checkbox" name="remember" id="remember" value="1" class="nolabel" checked="checked">
							</div>
						</form:form>
		
					</div>
	
					<div class="callus">
						<span>Call us: <strong>484.319.4055</strong></span>
						<span><a href="https://secure.projectbubble.com/help">Help</a></span>
						<span><img src="./css/padlock.png" alt="Login"> <a href="./css/Project Bubble.htm">Login</a></span>
					</div>
					
					<ul class="bigger">
						<li class=""><a href="https://secure.projectbubble.com/pricing">Pricing &amp; Signup</a></li>
						<li class=""><a href="http://projectbubble.com/tour">Tour</a></li>
						<li class=""><a href="http://projectbubble.com/">Home</a></li>
					</ul>
				
					
			</div>				
			
			
		</div>

	</div>

	<div id="content" class="content ">

		<div class="container narrow">

			<div id="tpl-manage" class="clearfix">

	<div id="tpl-signup">
		
		<div class="tpl-2col">
		
			<div class="col1 bigmargin">
			
								
				<div class="box">
				
					<h1>Login</h1>
					<form:form method="post" commandName="loginForm" action="${formAction}" class="default biglabel">
					
									
						<label for="email">Email:</label>
						<input type="text" id="email" name="username" class="formelement" placeholder="me@myemail.com">
						<br class="clear">
				
						<label for="password">Password:</label>
						<input type="password" id="password" name="password" class="formelement" placeholder="Password">
						<br class="clear">
			
						
						<label for="remember2">Remember me?</label>
						<input type="checkbox" name="remember" id="remember2" value="1" class="" checked="checked">
						<br class="clear"><br>					
						<input type="submit" id="login" name="login" value="Login" class="button big">
						<br class="clear"><br>
			
						
					</form:form>
			
				</div>
				
				<div id="holdon">
				
										
				</div>
				
			</div>
			
		</div>
		
	</div>

		</div>

		
		</div>

	</div>

	<div id="footer" class="content clearfix">

		<div class="container">
		
						
				<p class="links">
					<a href="https://secure.projectbubble.com/privacy">Privacy Policy</a> |
					<a href="https://secure.projectbubble.com/terms-of-service">Terms of Service</a> |
					<a href="https://secure.projectbubble.com/help">Help</a>
				</p>
				
				<p class="copyright">
					<a href="http://projectbubble.com/"><img src="./css/marque.png" class="marque" alt="Project Bubble"></a>
					Â© 2012 Project Bubble LLC
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