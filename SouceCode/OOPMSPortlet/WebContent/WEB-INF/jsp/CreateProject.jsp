<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Create Project</title>
<link type="text/css" href="../css/ui-lightness/jquery-ui-1.8.21.custom.css" rel="Stylesheet" />	
<link type="text/css" href="../css/common.css" rel="Stylesheet" />	
<link type="text/css" href="../css/uportal.css" rel="Stylesheet" />	
<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui-1.8.21.custom.min.js"></script>
<script type="text/javascript" src="../js/form-elements.js"></script>

<style type="text/css"> 
#tabs28 {   float:left;   width:100%;   background:#444;   font-size:93%;   line-height:normal; border-bottom:1px solid #DD740B;   }
#tabs28 ul { margin:0; padding:0px 10px 0 20px; list-style:none;   } 
#tabs28 li {   display:inline;   margin:0;   padding:0;   } 
#tabs28 a {   float:left;   background:url(slide-left.gif) no-repeat left top;   margin:0;   padding:0 0 0 5px;   text-decoration:none;   } 
#tabs28 a span {   float:left;   display:block;   background:url(side-right.gif) no-repeat right top;   padding:5px 15px 4px 6px;   color:#FFF;   } 
#tabs28 a span {float:none;}  #tabsI a:hover span {   color:#FFF;   } #tabs28 a:hover {   background-position:0% -42px;   } 
#tabs28 a:hover span {   background-position:100% -42px;   }</style>

<script>
	$(function() {
		$( "#radio" ).buttonset();
		$( "#format" ).buttonset();
			$( "input:submit, a, button", ".demo" ).button();
		$( "a", ".demo" ).click(function() { return false; });
		
		// run the currently selected effect
		function runEffect() {
			// get effect type from 
			var selectedEffect = $( "#effectTypes" ).val();

			// most effect types need no options passed by default
			var options = {};
			// some effects have required parameters
			if ( selectedEffect === "scale" ) {
				options = { percent: 100 };
			} else if ( selectedEffect === "size" ) {
				options = { to: { width: 280, height: 185 } };
			}

			// run the effect
			$( "#effect" ).show( selectedEffect, options, 500, callback );
		};

		//callback function to bring a hidden box back
		function callback() {
			setTimeout(function() {
				$( "#effect:visible" ).removeAttr( "style" ).fadeOut();
			}, 1000 );
		};

		// set effect from select menu value
		$( "#button" ).click(function() {
			runEffect();
			return false;
		});

		$( "#effect" ).hide();
	});
</script>
<script language="javascript">
	
	$(function() {
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
	$(document).ready(function() {
$('#mainTable tr')
    .filter(':has(:checkbox:checked)')
    .addClass('selected')
    .end()
  .click(function(event) {
    $(this).toggleClass('selected');
    if (event.target.type !== 'checkbox') {
      $(':checkbox', this).attr('checked', function() {
        return !this.checked;
      });
    }
  });
});
</script>

</head>

<body id="portal" class="up fl-theme-mist">

<div id="portalPageBodyInner" class="container">


  <div class="header">
  <table width="960">
  	<tr>
    	<td width="697" height="92">&nbsp;</td>
        <td width="251" valign="top">
        	<p>Hello Manager , <a href="HomePage.html">Log Out</a>
        </p>
       	<p><a href="UserDetail.html">Change your Information</a>
        </p></td>
    </tr>
  </table>
  </div>
  
  <div class="content">    
<div class="demo" style="font-size:20px" align="right"> 
		<a href="Create Project-Admin ver.html">Create Project</a>	
</div>
   <div class="fl-widget-titlebar titlebar portlet-titlebar" role="sectionhead">
    	<h2 class="title" >Create Project</h2>
    </div>
<portlet:actionURL var="formAction">
  <portlet:param name="action" value="login" />
</portlet:actionURL>
<form:form method="post" commandName="loginForm" action="${formAction}">                       
    <table class="portlet-table">
  <tr>
    <th width="186" scope="row">Project Manager</th>
    <td width="433">Manager</td>
  </tr>
  <tr>
    <th scope="row">Project Code* </th>
    <td><input name="txtProjectCode" value="" maxlength="50" size="50" type="text" /></td>
  </tr>
  <tr>
    <th scope="row">Project Name* </th>
    <td><input name="txtProjectName" value="" maxlength="50" size="50" type="text" /></td>
  </tr>
  <tr>
    <th scope="row">Project Status</th>
    <td><select class="styled" class="SmallCombo">
      <option selected="selected">Tentative</option>
      <option>Ongoing</option>
      <option>Closed</option>
      <option>Cancelled</option>
    </select></td>
  </tr>
  <tr>
    <th scope="row">Project Type</th>
    <td><select class="styled" class="SmallCombo">
      <option selected="selected">Product Maintainance</option>
      <option>Project Development</option>
    </select></td>
  </tr>
  <tr>
   <th scope="row">Direct Customer</th>
        <td><input name="txtCustomer" value="" maxlength="50" size="50" type="text" /><input type="button" value="Search Icon"></input></td>
      </tr>
      <tr>
        <th scope="row">End Customer</th>
        <td><input name="txt2NDCustomer" value="" maxlength="50" size="50" type="text" /></td>
      </tr>
      <tr>
   <th scope="row">Business Domain</th>
    <td><select name="select" class="styled" class="SmallCombo">
      <option selected="selected">Healthcare</option>
      <option>Universities</option>
      <option>Bank & Finance</option>
      <option>Technology</option>
      <option>Real Estate</option>
      <option>Construction</option>
      <option>Manufacturing</option>
    </select></td>
  </tr>
      <tr>
        <th scope="row">Planned Start Date* </th>
        <td><input maxlength="9" name="txtPlanStartDate" size="9" value="" type="text" id="datepicker1"/>
          (mm-dd-yyyy)</td>
      </tr>
      <tr>
        <th scope="row">Planned End Date* </th>
        <td><input maxlength="9" name="txtPlanEndDate" size="9" value="" type="text" id="datepicker2"/>
          (mm-dd-yyyy)</td>
      </tr>
      <tr>
        <th scope="row">Scope and Objective</th>
        <td><textarea rows="10" cols="70" name="txtScopeObjective"></textarea></td>
      </tr>
</table>
	<form:button id="Submit"  class="button blue small" name="Submit" value="Submit"/>
	<form:button id="Cancel"  class="button blue small" name="Cancel" value="Cancel"/>
</form:form>

  <!-- end .content --></div>
  <!-- end .container --></div>
</body>
</html>
