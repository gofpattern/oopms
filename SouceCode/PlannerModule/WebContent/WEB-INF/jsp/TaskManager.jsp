<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" href="../PlannerModule/Resource_files/css/ui-lightness/jquery-ui-1.8.21.custom.css" rel="Stylesheet" />	
<link type="text/css" href="../PlannerModule/Resource_files/css/common.css" rel="Stylesheet" />	
<link type="text/css" href="../PlannerModule/Resource_files/css/uportal.css" rel="Stylesheet" />	
<script type="text/javascript" src="../PlannerModule/Resource_files/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../PlannerModule/Resource_files/js/jquery-ui-1.8.21.custom.min.js"></script>
<script type="text/javascript" src="../PlannerModule/Resource_files/js/form-elements.js"></script>
<script type="text/javascript">
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
<script type="text/javascript">
$(document).ready(function(){

  $("#add-button").click(function(){
    $(".hidden-add-form").show("slow");
  });
    $("#cancel-button").click(function(){
    $(".hidden-add-form").hide("slow");
  });
});
</script>
<style type="text/css">
<!--
#portal #portalPageBodyInner .content #content_planner .portlet-table {
	font-size: 12px;
}
#portal #portalPageBodyInner .content #content_planner table {
	font-size: 12px;
}
.hidden-add-form 
{
	display:none;
	webkit-box-shadow: rgb(170, 170, 170) 0px 0px 5px 0px;
	background-attachment:scroll;
	background-color:#EFEFEF;
	background-origin: padding-box;
	border-bottom-left-radius: 5px;
	border-bottom-right-radius: 5px;
	border-top-left-radius: 5px;
	border-top-right-radius: 5px;
	box-shadow: rgb(170, 170, 170) 0px 0px 5px 0px;
	color: #505050;
	font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
	font-size: 12px;
	height: 500px;
	margin-bottom: 20px;
	margin-left: 50px;
	margin-right: 0px;
	margin-top: 0px;
	padding-bottom: 25px;
	padding-left: 25px;
	padding-right: 25px;
	padding-top: 25px;
	width: 900px;
}
-->
</style>
</head>
<body id="portal" class="up fl-theme-mist">
<div class="container" id="portalPageBodyInner">
<div class="content">

<input id="add-button" type="button" name="ok" value=" Add "/>

<portlet:actionURL var="formAction">
  <portlet:param name="action" value="addTask" />
</portlet:actionURL>


<div class="hidden-add-form" >
<form:form commandName="PlannerForm" method="post" action="${formAction}">

  <p id="add-form">
    <input id="cancel-button" type="button" name="cancel-button" value=" Close "/>
  <table class="Table" cellspacing="1" width="560">
  <caption class="TableCaption">&nbsp;
  </caption>
  <caption class="TableCaption">
  <h1>Add new Task</h1>
  </caption>
  <tbody>
    <tr>
      <td width="139" class="ColumnLabel">Title*</td>
      <td width="412" class="CellBGR3"><form:input path="title"></form:input></td>
    </tr>
    <tr>
      <td class="ColumnLabel"> Start Date* </td>
      <td class="CellBGR3"><input type="text" id="datepicker1"/>
        (DD-MMM-YY) </td>
    </tr>
    <tr>
      <td class="ColumnLabel"> Finish Date </td>
      <td class="CellBGR3"><input type="text" id="datepicker2"/>
        (DD-MMM-YY) </td>
    </tr>
    <tr>
      <td class="ColumnLabel"> Planned Effort </td>
      <td class="CellBGR3"><input type="text"/>
        (Hours) </td>
    </tr>
    <tr>
      <td class="ColumnLabel"> Actual Effort </td>
      <td class="CellBGR3"><input type="text"/>
        (Hours) </td>
    </tr>
    <tr>
      <td class="ColumnLabel"> Stage* </td>
      <td class="CellBGR3"><select class="styled" class="SmallCombo">
        <option selected="selected" value="-1">All stages</option>
        <option value="1">Initiation</option>
        <option value="2">Definition</option>
        <option value="3">Solution</option>
        <option value="4">Construction</option>
        <option value="0">Transition & Termination </option>
      </select></td>
    </tr>
    <tr>
      <td class="ColumnLabel"> Process </td>
      <td class="CellBGR3"><select name="process" class="styled" class="SmallCombo">
        <option value="23">Administration</option>
        <option value="4">Coding</option>
        <option value="21">Collaborator Management</option>
        <option value="8">Configuration Management</option>
        <option value="1">Contract management</option>
        <option value="14">Correction</option>
        <option value="6">Customer Support</option>
        <option value="5">Deployment</option>
        <option value="3">Design</option>
        <option value="18">Document Control</option>
        <option value="24">Facility Management</option>
        <option value="19">IS Management</option>
        <option value="13">Internal Audit</option>
        <option value="15">Management Review</option>
        <option value="26">Other</option>
        <option value="28">Prevention</option>
        <option value="29">Process Improvement</option>
        <option value="9">Project Management</option>
        <option value="12">Quality Control</option>
        <option value="10">Quality Planning</option>
        <option value="16">Recruitment</option>
        <option value="2">Requirement</option>
        <option value="25">Retirement</option>
        <option value="20">Staff Management</option>
        <option value="22">Student Management</option>
        <option value="11">Subcontract Management</option>
        <option value="27">Technology management</option>
        <option value="7">Test</option>
        <option value="17">Training</option>
      </select></td>
    </tr>
    <tr>
      <td class="ColumnLabel"> Product* </td>
      <td class="CellBGR3"><select name="cboStatus2" class="styled" class="SmallCombo">
        <option selected="selected" value="-1">All Product</option>
        <option value="1">LOC</option>
        <option value="2">Report</option>
        <option value="3">Document</option>
        <option value="4">Test Case</option>
        <option value="0">Others</option>
      </select></td>
    </tr>
    <tr>
      <td class="ColumnLabel"> Assigned To* </td>
      <td class="CellBGR3"><select name="assignedTo" class="styled" class="SmallCombo">
        <option value="118124">PNTG</option>
        <option value="1">SYSADMIN</option>
      </select></td>
    </tr>
    <tr>
      <td class="ColumnLabel"> Status* </td>
      <td class="CellBGR3"><select class="styled" class="SmallCombo">
        <option value="1">Closed</option>
        <option value="2">Cancelled</option>
        <option value="3">Tentative</option>
        <option selected="selected" value="0">On-going</option>
      </select></td>
    </tr>
    <tr>
      <td class="ColumnLabel">Description</td>
      <td class="CellBGR3"><textarea rows="4" cols="50" name="note"></textarea></td>
    </tr>
  </tbody>
</table>
</p>
<p>
<input type="submit" name="ok" value=" OK " class="BUTTON">
</p>
</form:form>
</div>
<div id="content_planner">
<table>
	<tr>
    	<td><b>&nbsp;&nbsp;Status&nbsp;&nbsp;</b></td>
        <td><b>&nbsp;&nbsp;Stage&nbsp;&nbsp;</b></td>
        <td><b>&nbsp;&nbsp;Assigned To&nbsp;</b></td>
        <td><b>&nbsp;&nbsp;Sort&nbsp;by&nbsp;&nbsp;</b></td>
    </tr>
	<tr>
                <td>
                  <select class="styled" class="SmallCombo">
                    <option selected="selected" value="-1">All status</option>
                    <option value="1">Closed</option>
                    <option value="2">Cancelled</option>
                    <option value="3">Tentative</option>
                    <option value="0">On-going</option>
                  </select></td>
                <td >
                  <select class="styled" class="SmallCombo">
                    <option selected="selected" value="-1">All stages</option>
                    <option value="1">Initiation</option>
                    <option value="2">Definition</option>
                    <option value="3">Solution</option>
                    <option value="4">Construction</option>
                    <option value="0">Transition &amp; Termination </option>
                  </select></td>
                <td >
                  <select class="styled" class="SmallCombo">
                    <option selected="selected" value="-1">All categories</option>
                    <option value="0">Development</option>
                    <option value="1">Maintenance</option>
                    <option value="2">Others</option>
                  </select></td>
                <td width="10%">
                  <select class="styled" class="SmallCombo">
                    <option selected="selected" value="0">Status</option>
                    <option value="1">Task Name</option>
                    <option value="2">Remaining Time</option>
                    <option value="2">Stage</option>
                    <option value="2">Product</option>
                  </select></td>
                <td width="56%"><input type="button" name="input2" value="Search"/></td>
      </tr>
</table>

<c:set var="list" value="${taskList}"/>
<c:if test="${not empty list}">
  <table class="portlet-table">
  <thead>
      <tr>
        <!-- TABLE HEADER -->
        <th ><b><font><span >No.</span></font></b> 
        <th ><b><font><span >Task Code</span></font></b> 
        <th ><b><font><span >Task Name</span></font></b> 
        <th width="10"><b><font><span>Stage</span></font></b> 
        <th ><b><font><span>Product</span></font></b> 
        <th ><b><font><span>Assigned To</span></font></b> 
        <th ><b><font><span>Remaining Time</span></font></b> 
        <th ><b><font><span>Completeness Rate</span></font></b> 
        <th ><b><font><span >Start Date</span></font></b> 
        <th ><b><font><span>Finish Date</span></font></b> 
        <th ><b><font><span >Planned Effort</span></font></b> 
        <th ><b><font><span >Actual Effort</span></font></b> 
        <th ><b><font><span >Update</span></font></b> 
        <th ><b><font><span >Delete</span></font></b>   
      </tr>
  </thead>
    <tbody>
        <c:set var="count" value="0"/>
                    <c:forEach items="${list}" var="task">
                        <tr>
                        <form action="Controller">
                        <c:set var="count" value="${count + 1}"/>
                        <td>${count}</td>
                        <td>${task.taskcode}</td>
                        <td>${task.taskname}</td>
                        <td>${task.stageid}</td>
                        <td>${task.product}</td>
                        <td>${task.assignmentid}</td>
                        <td></td>
                        <td>${task.completenessstatus}</td>
                        <td>${task.startdate}</td>
                        <td>${task.plannedenddate}</td>
                        <td>${task.plannedeffort}</td>
                        <td>${task.actualeffort}</td>
                        <td><input type="image" alt="Submit" 
                        src="../PlannerModule/Resource_files/icons/Actions-document-edit-icon.png" 
                        width="24" height="24" value="edit" name="action"></input></td>
                        <td><input type="image" alt="Submit" 
                        src="../PlannerModule/Resource_files/icons/Actions-delete-icon.png" 
                        width="24" height="24" value="delete" name="action"></input></td>
                        </form>
                        </tr>
                    </c:forEach>
     <!--  <tr>
        <td>PJA01</td>
        <td>Task No.1 of Project A</td>
        <td>Initation</td>
        <td>Document</td>
        <td>PNTG</td>
        <td>0</td>
        <td>100%</td>
        <td>03/28/2012</td>
        <td>03/30/2012</td>
        <td>10</td>
        <td>12</td>
        <td><img src="../PlannerModule/Resource_files/icons/Actions-document-edit-icon.png" width="24" height="24"></td>
        <td><img src="../PlannerModule/Resource_files/icons/Actions-delete-icon.png" width="24" height="24"/></td>
      </tr>
      <tr>
        <td >PJA02</td>
        <td >Task No.2 of Project A</td>
        <td >Definition</td>
        <td >Document</td>
        <td >PNTG</td>
        <td >0</td>
        <td >100%</td>
        <td >03/28/2012</td>
        <td >03/30/2012</td>
        <td >10</td>
        <td >12</td>
        <td><img src="../PlannerModule/Resource_files/icons/Actions-document-edit-icon.png" width="24" height="24"></td>
        <td><img src="../PlannerModule/Resource_files/icons/Actions-delete-icon.png" width="24" height="24"/></td>
      </tr>
      <tr>
        <td >PJA03</td>
        <td >Task No.3 of Project A</td>
        <td >Constuction</td>
        <td >LOC</td>
        <td >PNTG</td>
        <td >0</td>
        <td >100%</td>
        <td >03/28/2012</td>
        <td >03/30/2012</td>
        <td >10</td>
        <td >12</td>
        <td><img src="../PlannerModule/Resource_files/icons/Actions-document-edit-icon.png" width="24" height="24"></td>
        <td><img src="../PlannerModule/Resource_files/icons/Actions-delete-icon.png" width="24" height="24"/></td>
      </tr>
      <tr>
        <td >PJA09</td>
        <td >Task No.9 of Project A</td>
        <td >Constuction</td>
        <td >LOC</td>
        <td >PNTG</td>
        <td >0</td>
        <td >100%</td>
        <td >03/28/2012</td>
        <td >03/30/2012</td>
        <td >10</td>
        <td >12</td>
        <td><img src="../PlannerModule/Resource_files/icons/Actions-document-edit-icon.png" width="24" height="24"></td>
        <td><img src="../PlannerModule/Resource_files/icons/Actions-delete-icon.png" width="24" height="24"/></td>
      </tr>
      <tr>
        <td >PJA06</td>
        <td >Task No.6 of Project A</td>
        <td >Constuction</td>
        <td >LOC</td>
        <td >PNTG</td>
        <td >0</td>
        <td >100%</td>
        <td >03/28/2012</td>
        <td >03/30/2012</td>
        <td >10</td>
        <td >12</td>
        <td><img src="../PlannerModule/Resource_files/icons/Actions-document-edit-icon.png" width="24" height="24"></td>
        <td><img src="../PlannerModule/Resource_files/icons/Actions-delete-icon.png" width="24" height="24"/></td>
      </tr> -->
     
      
    </tbody>
  </table>
  </c:if>        
</div>
<div id="button">
<input type="button" name="" value="Add New Task"/>
<input type="button" name="" value="Import"/>
<input type="button" name="input" value="Report"/>
</div>
  </div>
  <div class="footer">
    <p>OOPMS Group</p>
    <!-- end .footer --></div>
</div>
</body></html>