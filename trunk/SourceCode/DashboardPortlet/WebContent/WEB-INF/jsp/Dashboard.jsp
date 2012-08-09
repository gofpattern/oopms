<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet"%>
<%@ taglib prefix="portlet2" uri="http://java.sun.com/portlet_2_0"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="javax.portlet.PortletSession"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="header.jsp" />
<portlet2:defineObjects />
<portlet:defineObjects />
<script language="javascript">    
    $(function() {
        $( "#datepicker1" ).datepicker({
        	showOn : "button",
            buttonImage : "/<spring:message code="app.context"/>/resource_files/images/calendar.gif",
            buttonImageOnly : true
        });
        $( "#datepicker2" ).datepicker({
        	showOn : "button",
            buttonImage : "/<spring:message code="app.context"/>/resource_files/images/calendar.gif",
            buttonImageOnly : true
        });
        $( "#selectable" ).selectable();
    });
/*     $(document).ready(function() {
  $('#mainTable tr').filter(':has(:checkbox:checked)').addClass('selected').end().click(function(event) {
    $(this).toggleClass('selected');
    if (event.target.type !== 'checkbox') {
      $(':checkbox', this).attr('checked', function() {
        return !this.checked;
      });
    }
  });
}); */
</script>
</head>
<body id="portal" class="up fl-theme-mist">
<div id="portalPageBodyInner" class="container">     
  <div class="content">
         <table>
        <tbody>
            <tr>
              <td ><b>Status</b></td>
              <td><b>Category</b></td>
                <td><b>Time</b></td>
                <td> <b>Progress</b></td>
                 <td><b>Efficiency</font></b></td>
                 <td >            <b>Cost</b></td>
                  <td><b>Start Date</b>   </td>
                  <td><b>Finish Date</b></td>
                            </tr>
            <tr>
                
               <td >
                <select   class="styled"  name="cboStatus" size="1" >                    
                    <option value="-1" selected>All status</option>                    
                    <option value="1" >Closed</option>                    
                    <option value="2">Cancelled</option>                    
                    <option value="3">Tentative</option>                    
                    <option value="0">On-going</option>                    
                </select></td>
                
                <td>
                 <select class="styled" name="cboCategory" size="1" >
                <option selected="selected" value="-1">All categories</option>
                <option value="0">Development</option>
                <option value="1">Maintenance</option>
                <option value="2">Others</option>
            </select></td>
            
            <td>           
            <select class="styled" name="" >
                <option selected="selected" value="-1">All</option>
                <option value="0">Good</option>
                <option value="1">Normal</option>
                <option value="2">Bad</option>
            </select>
            </td>    
                    
            <td>
            
            <select class="styled" name="" >
                <option selected="selected" value="-1">All</option>
                <option value="0">Good</option>
                <option value="1">Normal</option>
                <option value="2">Bad</option>
            </select>
            </td>
            <td align="left" valign="middle" width="8%">
           
            <select class="styled" name="" >
                <option selected="selected" value="-1">All</option>
                <option value="0">Good</option>
                <option value="1">Normal</option>
                <option value="2">Bad</option>
            </select></td>
            
            <td align="left" >          
            <select class="styled" name="" >
                <option selected="selected" value="-1">All</option>
                <option value="0">Good</option>
                <option value="1">Normal</option>
                <option value="2">Bad</option>
            </select></td>
            
            <td style="width:150px;"  class="vAlignMid">
         <input style="width:100px;" type="text" id="datepicker1">           
      </td>
            <td >           
             <input style="width:100px;" type="text" id="datepicker2"> 
               </td>
               
                <td><input name="DoSearchProject" onClick="" value="Search" type="button"></td>
            </tr>
        </tbody></table>
      </td>
    </tr>
    </tbody>
    <!-- PROJECT LIST -->
    </table>
    
    <table class="portlet-table">
    <tr>
        <!-- TABLE HEADER -->
        <th scope="col">Project Code</th>
        <th scope="col">Project Name</th>
        <th scope="col">Project Manager</th>      
        <th scope="col">Project Health</th>
        <th scope="col">Time</th>
        <th scope="col">Progress</th>
        <th scope="col">Efficiency</th>
        <th scope="col">Cost</th>
        <th scope="col">Start Date</th>
        <th scope="col">Finish Date</th>
        <th scope="col">Planned Effort</th>
        <th scope="col">Actual Effort</th>
    </tr>
    
    <tr>
        <td>APJ</td>
        <td><a href="#">Demo A Project</a></td>
        <td>Manager A</td>
        <td><img src="icons/circle_red.png" width="24" height="24"></td>
        <td><img src="icons/progress_bar.png" width="150" height="24" ></td>
        <td><img src="icons/progress_bar_red_50.png" width="150" height="24" ></td>
      <td><img src="icons/circle_red.png" width="24" height="24"></td>
      <td><img src="icons/circle_red.png" width="24" height="24"></td>
        <td>2/28/12</td>
      <td>6/28/12</td>
      <td>100</td>
        <td>0</td>
   </tr>
        <tr>
        <td>BPJ</td>
        <td><a href="#">Demo B Project</a></td>
        <td>Manager B</td>        
        <td><img src="icons/circle_yellow.png" width="24" height="24"></td>
        <td><img src="icons/progress_bar.png" width="150" height="24" ></td>
        <td><img src="icons/progress_bar.png" width="150" height="24" ></td>
      <td><img src="icons/circle_yellow.png" width="24" height="24"></td>
      <td><img src="icons/circle_yellow.png" width="24" height="24"></td>
        <td>2/28/12</td>
      <td>6/28/12</td>
      <td>100</td>
        <td>0</td>
   </tr>    
   <tr>
        <td>CPJ</td>
       <td><a href="#">Demo C Project</a></td>
        <td>Manager C</td>
        <td><img src="icons/circle_yellow.png" width="24" height="24"></td>
        <td><img src="icons/progress_bar_100.png" width="150" height="24" ></td>
        <td><img src="icons/progress_bar_100.png" width="150" height="24" ></td>
        <td><img src="icons/circle_yellow.png" width="24" height="24"></td>
        <td><img src="icons/circle_yellow.png" width="24" height="24"></td>
        <td>2/28/12</td>
        <td>4/28/12</td>
        <td>100</td>
        <td>120</td>
   </tr>    
   <tr>
        <td>DPJ</td>
        <td><a href="#">Demo D Project</a></td>
        <td>Manager D</td>
        <td><img src="icons/circle_green.png" width="24" height="24"></td>
        <td><img src="icons/progress_bar_100.png" width="150" height="24" ></td>
        <td><img src="icons/progress_bar_100.png" width="150" height="24" ></td>
        <td><img src="icons/circle_green.png" width="24" height="24"></td>
        <td><img src="icons/circle_green.png" width="24" height="24"></td>
        <td>2/28/12</td>
        <td>4/28/12</td>
        <td>100</td>
        <td width="36">120</td>
   </tr></table>


  <div class="footer">
    <p>OOPMS Group</p>
    <!-- end .footer --></div>

<!-- end .content--></div>


<!-- end .container --></div>
</body>
</html>