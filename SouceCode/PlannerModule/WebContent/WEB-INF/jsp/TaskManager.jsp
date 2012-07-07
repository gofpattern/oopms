<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html><head>
<link href="../PlannerModule/Resource_files/StyleSheet.css" rel="stylesheet" type="text/css">
<link type="text/css" href="css/ui-lightness/jquery-ui-1.8.21.custom.css" rel="Stylesheet" />	
<link type="text/css" href="css/common.css" rel="Stylesheet" />	
<link type="text/css" href="css/uportal.css" rel="Stylesheet" />	
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.21.custom.min.js"></script>
<script type="text/javascript" src="js/form-elements.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
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
.hidden-add-form 
{
	display:block;
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
	height: 300px;
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
</style>
</head>
<body id="portal" class="up fl-theme-mist">
<div class="container">
  
  <div class="content">
  <div>
<p align="left"><input id="add-button" type="button" name="ok" value=" Add "></p>
</div>
<div class="hidden-add-form" >
  <p id="add-form">
    <input id="cancel-button" type="button" name="cancel-button" value=" Close ">
  <table class="Table" cellspacing="1" width="560">
  <caption class="TableCaption">&nbsp;
  </caption>
  <caption class="TableCaption">
  <h1>Add new Task</h1>
  </caption>
  <tbody>
    <tr>
      <td width="139" class="ColumnLabel">Title*</td>
      <td width="412" class="CellBGR3"><input name="code" size="9" type="text" ></td>
    </tr>
    <tr>
      <td class="ColumnLabel"> Start Date* </td>
      <td class="CellBGR3"><input name="planDate" size="9" maxlength="9">
        (DD-MMM-YY) </td>
    </tr>
    <tr>
      <td class="ColumnLabel"> Finish Date </td>
      <td class="CellBGR3"><input name="rePlanDate" size="9" maxlength="9">
        (DD-MMM-YY) </td>
    </tr>
    <tr>
      <td class="ColumnLabel"> Planned Effort </td>
      <td class="CellBGR3"><input name="rePlanDate" size="9" maxlength="9">
        (Hours) </td>
    </tr>
    <tr>
      <td class="ColumnLabel"> Actual Effort </td>
      <td class="CellBGR3"><input name="rePlanDate" size="9" maxlength="9">
        (Hours) </td>
    </tr>
    <tr>
      <td class="ColumnLabel"> Stage* </td>
      <td class="CellBGR3"><select name="cboStatus2" size="1" style="width: 90">
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
      <td class="CellBGR3"><select name="process">
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
      <td class="CellBGR3"><select name="cboStatus2" size="1" style="width: 90">
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
      <td class="CellBGR3"><select name="assignedTo">
        <option value="118124">PNTG</option>
        <option value="1">SYSADMIN</option>
      </select></td>
    </tr>
    <tr>
      <td class="ColumnLabel"> Status* </td>
      <td class="CellBGR3"><select name="assignedTo">
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
  <caption class="TableCaption">&nbsp;
  </caption>
</table>
</p>
<p>
<input type="button" onClick="location.href='TaskManager.html'" name="ok" value=" OK " class="BUTTON">
</p>
</div>
<div id="content_planner">
  <table width="1020" border="0" cellspacing="1">
    <tbody><tr>
        <td colspan="12" align="left"><!-- GROUP COMBO -->
        <table border="0" cellspacing="0" width="100%">
            <tbody><tr>
                   <td class="required_left_cell_bkgrnd" align="left" valign="middle" width="9%">
                   <b><font color="#000080">&nbsp;&nbsp;Status&nbsp;&nbsp;</font></b>
                	<select name="cboStatus" size="1" style="width: 90">                    
                    <option selected="selected" value="-1">All status</option>                    
                    <option value="1">Closed</option>                    
                    <option value="2">Cancelled</option>                    
                    <option value="3">Tentative</option>                    
                    <option value="0">On-going</option>                    
               	 	</select></td>
                     <td class="required_left_cell_bkgrnd" align="left" valign="middle" width="9%">
                   <b><font color="#000080">&nbsp;&nbsp;Stage&nbsp;&nbsp;</font></b>
                	<select name="cboStatus" size="1" style="width: 90">                    
                    <option selected="selected" value="-1">All stages</option>                    
                    <option value="1">Initiation</option>                    
                    <option value="2">Definition</option>                    
                    <option value="3">Solution</option>                    
                    <option value="4">Construction</option>   
                    <option value="0">Transition &amp; Termination </option>                
               	 	</select></td>
                <td class="required_left_cell_bkgrnd" align="left" valign="middle" width="10%">
                <b><font color="#000080">&nbsp;&nbsp;Assigned To&nbsp;</font></b>     
     			<select name="cboCategory" size="1" style="width: 100">
           	 	<option selected="selected" value="-1">All categories</option>
            	<option value="0">Development</option>
            	<option value="1">Maintenance</option>
            	<option value="2">Others</option>
        		</select></td>
                <td width="10%">
                <b><font color="#000080">&nbsp;&nbsp;Sort&nbsp;by&nbsp;&nbsp;</font></b> 
                	<select style="width: 100" name="cboOrder">
                    <option selected="selected" value="0">Status</option>
                    <option value="1">Task Name</option>
                    <option value="2">Remaining Time</option>
                    <option value="2">Stage</option>
                    <option value="2">Product</option>
                </select></td>
                <td width="56%"><input type="button" name="" value="Search"/></td>
            </tr>
        </tbody></table>
        </td>
    </tr>
    <!-- PROJECT LIST -->
    <tr class="table_header">
        <!-- TABLE HEADER -->
        <td width="51"><b><font size="1" face="Verdana"><span >ID</span></font></b></td>
        <td width="200"><b><font face="Verdana" size="1"><span >Task Name</span></font></b></td>
        <td width="102"><b><font size="1" face="Verdana"><span>Stage</span></font></b></td>        
        <td width="82"><b><font size="1" face="Verdana"><span>Product</span></font></b></td>
        <td width="48"><b><font face="Verdana" size="1"><span>Assigned To</span></font></b></td>
        <td width="55"><b><font size="1" face="Verdana"><span>Remaining Time</span></font></b></td>
        <td width="72"><b><font size="1" face="Verdana"><span>Completeness Rate</span></font></b></td>
        <td width="94"><b><font face="Verdana" size="1"><span >Start Date</span></font></b></td>
        <td width="101"><b><font face="Verdana" size="1"><span>Finish Date</span></font></b></td>
        <td width="43"><b><font face="Verdana" size="1"><span >Planned Effort</span></font></b></td>
        <td width="46"><b><font face="Verdana" size="1"><span >Actual Effort</span></font></b></td>
        <td width="43"><b><font face="Verdana" size="1"><span >Update</span></font></b></td>
        <td width="43"><b><font face="Verdana" size="1"><span >Delete</span></font></b></td>
    </tr>
    <tr>        
    	<td width="51">PJA01</td>
        <td width="200">Task No.1 of Project A</td>
        <td width="102">Initation</td>
        <td width="82">Document</td>
        <td width="48">PNTG</td>
        <td width="55">0</td>
        <td width="72">100%</td>
        <td width="94">03/28/2012</td>
        <td width="101">03/30/2012</td>
        <td width="43">10</td>
        <td width="46">12</td>
        <td><img src="Resource_files/icons/Actions-document-edit-icon.png" width="24" height="24"></td>
        <td><img src="Resource_files/icons/Actions-delete-icon.png" width="24" height="24"/></td>
    </tr>
    <tr>        
    	<td width="51">PJA02</td>
        <td width="200">Task No.2 of Project A</td>
        <td width="102">Definition</td>
        <td width="82">Document</td>
        <td width="48">PNTG</td>
        <td width="55">0</td>
        <td width="72">100%</td>
        <td width="94">03/28/2012</td>
        <td width="101">03/30/2012</td>
        <td width="43">10</td>
        <td width="46">12</td>
        <td><img src="Resource_files/icons/Actions-document-edit-icon.png" width="24" height="24"></td>
        <td><img src="Resource_files/icons/Actions-delete-icon.png" width="24" height="24"/></td>
    </tr>
    <tr>        
    	<td width="51">PJA03</td>
        <td width="200">Task No.3 of Project A</td>
        <td width="102">Constuction</td>
        <td width="82">LOC</td>
        <td width="48">PNTG</td>
        <td width="55">0</td>
        <td width="72">100%</td>
        <td width="94">03/28/2012</td>
        <td width="101">03/30/2012</td>
        <td width="43">10</td>
        <td width="46">12</td>        
        <td><img src="Resource_files/icons/Actions-document-edit-icon.png" width="24" height="24"></td>
        <td><img src="Resource_files/icons/Actions-delete-icon.png" width="24" height="24"/></td>
    </tr>
        <tr>        
    	<td width="51">PJA09</td>
        <td width="200">Task No.9 of Project A</td>
        <td width="102">Constuction</td>
        <td width="82">LOC</td>
        <td width="48">PNTG</td>
        <td width="55">0</td>
        <td width="72">100%</td>
        <td width="94">03/28/2012</td>
        <td width="101">03/30/2012</td>
        <td width="43">10</td>
        <td width="46">12</td>
        <td><img src="Resource_files/icons/Actions-document-edit-icon.png" width="24" height="24"></td>
        <td><img src="Resource_files/icons/Actions-delete-icon.png" width="24" height="24"/></td>
    </tr>
        <tr>        
    	<td width="51">PJA06</td>
        <td width="200">Task No.6 of Project A</td>
        <td width="102">Constuction</td>
        <td width="82">LOC</td>
        <td width="48">PNTG</td>
        <td width="55">0</td>
        <td width="72">100%</td>
        <td width="94">03/28/2012</td>
        <td width="101">03/30/2012</td>
        <td width="43">10</td>
        <td width="46">12</td>
        <td><img src="Resource_files/icons/Actions-document-edit-icon.png" width="24" height="24"></td>
        <td><img src="Resource_files/icons/Actions-delete-icon.png" width="24" height="24"/></td>
    </tr>
    
</tbody></table>
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