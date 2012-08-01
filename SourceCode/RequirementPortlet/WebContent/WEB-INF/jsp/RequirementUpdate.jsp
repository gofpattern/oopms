<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.21.custom.min.js"></script>
<script type="text/javascript" src="js/form-elements.js"></script>

<title>Add Requirement</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >

<link type="text/css" href="css/ui-lightness/jquery-ui-1.8.21.custom.css" rel="Stylesheet">
<link type="text/css" href="css/common.css" rel="Stylesheet"> 



<style type="text/css"> 
#tabs28 {   float:left;   width:100%;   background:#444;   font-size:93%;   line-height:normal; border-bottom:1px solid #DD740B;   }
#tabs28 ul { margin:0; padding:0px 10px 0 20px; list-style:none;   } 
#tabs28 li {   display:inline;   margin:0;   padding:0;   } 
#tabs28 a {   float:left;   background:url(slide-left.gif) no-repeat left top;   margin:0;   padding:0 0 0 5px;   text-decoration:none;   } 
#tabs28 a span {   float:left;   display:block;   background:url(side-right.gif) no-repeat right top;   padding:5px 15px 4px 6px;   color:#FFF;   } 
#tabs28 a span {float:none;}  #tabsI a:hover span {   color:#FFF;   } #tabs28 a:hover {   background-position:0% -42px;   } 
#tabs28 a:hover span {   background-position:100% -42px;   }
textarea{
padding: .2em .4em;
line-height: 1.4;
background: 
white;
box-shadow: 0 1px 4px 
rgba(0, 0, 0, 0.2) inset;
-moz-border-radius: .6em;
-webkit-border-radius: .6em;
border-radius: .6em;
}
</style>

<script language="javascript">
    
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
    
    $( "#datepicker1" ).datepicker({
        showOn: "button",
        buttonImage: "../RequirementPortlet/resource_files/images/calendar.gif",
        buttonImageOnly: true
    });
    $( "#datepicker2" ).datepicker({
        showOn: "button",
        buttonImage: "../RequirementPortlet/resource_files/images/calendar.gif",
        buttonImageOnly: true
    });
    
</script>
<script type="text/javascript" src='/RequirementPortlet/scripts/common.js'></script>

</head>

<body>

<div class="container">
  
  <!-- Header -->
  <div class="header">
   <table width="960">
    <tr>
        <td width="697" height="92">&nbsp;</td>
        <td width="251" valign="top">
            <p>Hello Duy , <a href="HomePage.html">Log Out</a>
        </p>
        <p><a href="UserDetail.html">Change your Information</a></p></td>
    </tr>
  </table>
  </div>
  
  
  <div class="content">


<div id="tabs28">      
    <ul>
    <h5>
        <li><a href="ManagerHomePage.html"><span>Dash Board</span></a></li>
    </h5>
    </ul>
</div>

 <div class="demo" style="font-size:20px" align="right">
 <a href="Create Project-Admin ver.html">Create Project</a> 
 </div>
 
 <%--Display errors --%>
<font color="red"><form:errors path="*"></form:errors></font>
        
    <%--Submit action --%>
    <portlet:actionURL var="formAction">
    <portlet:param name="action" value="Save"/>
    </portlet:actionURL>
    <%-- Back to Requirement List --%>
    <portlet:renderURL var="requirementmanager"><portlet:param name="action" value="requirementmanager"/></portlet:renderURL>

<form:form name="${portletNamespace}RequirementAdd" commandName="RequirementAddForm" method="post" action="${formAction}">
    
    
    <table width="95%" cellspacing="1">    
        
    <div>
    <tbody>      
      <tr>
        <td>Project Name*</td>                
        <td width="26%"> <form:select  cssClass="styled" path="projectDefault" multiple="single">
        <c:set var="projectMap" value="${projectMap}" />
        <form:options  items="${projectMap}" /></form:select> </td>
      </tr> 
           
      <tr>               
        <!-- Requirement Name -->
        <td align="left" valign="middle"><b><font color="black">Requirement Name* </font></b><font color="red">&nbsp;*</font></td>
        <td align="left" valign="middle">        
        <form:input path="requirementName" maxlength="150"/>
        </td>        
      </tr>                                        
             
      <tr>
        <td>Requirement Size* </td>
        <td>
        
        <form:select path="reqSize">
        <option value="1" selected="selected">1</option>
        <option value="2" >2</option>
        <option value="3" >3</option>
        <option value="4" >4</option>
        <option value="5" >5</option>
        </form:select>
        
        </td>
      </tr>
      
      <tr>
        <td>Requirement Type* </td>
        <td>
        <form:select path="reqType">
        <option value="1" selected="selected">1</option>
        <option value="2" >2</option>
        <option value="3" >3</option>
        <option value="4" >4</option>
        <option value="5" >5</option>
        </form:select>
        </td>
      </tr>
      
      <tr>               
        <!-- srs -->
        <td align="left" valign="middle"><b><font color="black">SRS </font></b><font color="red">&nbsp;*</font></td>
        <td align="left" valign="middle">        
        <form:input path="srs" maxlength="150"/>
        </td>        
      </tr>
      
      <tr>               
        <!-- releaseNote -->
        <td align="left" valign="middle"><b><font color="black">Release Note </font></b><font color="red">&nbsp;*</font></td>
        <td align="left" valign="middle">        
        <form:input path="releaseNote" maxlength="150"/>
        </td>        
      </tr>
      
      <tr>               
        <!-- document -->
        <td align="left" valign="middle"><b><font color="black">Document </font></b><font color="red">&nbsp;*</font></td>
        <td align="left" valign="middle">        
        <form:input path="document" maxlength="150"/>
        </td>        
      </tr>
      
      <tr>               
        <!-- effort -->
        <td align="left" valign="middle"><b><font color="black">Effort </font></b><font color="red">&nbsp;*</font></td>
        <td align="left" valign="middle">        
        <form:input path="effort" maxlength="150"/>
        </td>        
      </tr>                 
         
      <tr>
        <td>Created Date</td>
        <td><input maxlength="9" name="createdDate" size="9" value="" type="text" id="datepicker1"/>
          (mm-dd-yyyy)</td>
      </tr>      
      <tr>
        <td>Designed Date</td>
        <td><input maxlength="9" name="designedDate" size="9" value="" type="text" id="datepicker1"/>
          (mm-dd-yyyy)</td>
      </tr>      
      <tr>
        <td>Coded Date</td>
        <td><input maxlength="9" name="codedDate" size="9" value="" type="text" id="datepicker1"/>
          (mm-dd-yyyy)</td>
      </tr>      
      <tr>
        <td>Tested Date</td>
        <td><input maxlength="9" name="testedDate" size="9" value="" type="text" id="datepicker1"/>
          (mm-dd-yyyy)</td>
      </tr>      
      <tr>
        <td>Deployed Date</td>
        <td><input maxlength="9" name="deployedDate" size="9" value="" type="text" id="datepicker1"/>
          (mm-dd-yyyy)</td>
      </tr>      
      <tr>
        <td>Accepted Date</td>
        <td><input maxlength="9" name="acceptedDate" size="9" value="" type="text" id="datepicker1"/>
          (mm-dd-yyyy)</td>
      </tr>      
      <tr>
        <td>Cancelled Date</td>
        <td><input maxlength="9" name="cancelledDate" size="9" value="" type="text" id="datepicker1"/>
          (mm-dd-yyyy)</td>
      </tr>   
            
         
    </tbody>
    </div>
  
    
  </table>  
  
  <input name="RequirementAdd" class="Button" value="Save" type="button" onclick='submitAction("${portletNamespace}RequirementAdd", "${formAction}")'>&nbsp;&nbsp;&nbsp;&nbsp;
  <input name="Back" class="Button" onclick='submitAction("${portletNamespace}RequirementAdd", "${requirementmanager}")' value="Requirement List" type="button">&nbsp;&nbsp;&nbsp;&nbsp;  
          
  </form:form>
       
  <div class="footer">
    <p>DMS Group</p>    
  </div> <!-- end .footer -->
  
  </div> <!-- end .content -->
  
  </div> <!-- end .container -->
</body>
</html>
