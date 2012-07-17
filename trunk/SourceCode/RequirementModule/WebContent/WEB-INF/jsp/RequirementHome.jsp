<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- Uncomment below lines to add portlet taglibs to jsp
<%@ page import="javax.portlet.*"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<portlet:defineObjects />
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Requirement Home</title>

<style type="text/css"> 
#tabs28 {   float:left;   width:100%;   background:#EFF4FA;   font-size:93%;   line-height:normal; border-bottom:1px solid #DD740B;   }
#tabs28 ul { margin:0; padding:10px 10px 0 50px; list-style:none;   } 
#tabs28 li {   display:inline;   margin:0;   padding:0;   } 
#tabs28 a {   float:left;   background:url(slide-left.gif) no-repeat left top;   margin:0;   padding:0 0 0 5px;   text-decoration:none;   } 
#tabs28 a span {   float:left;   display:block;   background:url(side-right.gif) no-repeat right top;   padding:5px 15px 4px 6px;   color:#FFF;   } 
#tabs28 a span {float:none;}  #tabsI a:hover span {   color:#FFF;   } #tabs28 a:hover {   background-position:0% -42px;   } 
#tabs28 a:hover span {   background-position:100% -42px;   }
</style>

</head>
<body>

<div class="container">

  <div class="header">
  <table width="960">
    <tr>
        <td width="697" height="92">&nbsp;</td>
        <td width="251" valign="top">
            <p>Hello Duy , <a href="HomePage.html">Log Out</a>
        </p>
        <p><a href="UserDetail.html">Change your Information</a></br>
        <a href="Create Project.html">Create new Project</a>
        </p></td>
    </tr>
  </table>
  </div>
  
  
  <div class="content">
    
<!-- Module link -->    
<div id="tabs28">      
<ul>
  <h5>
<li><a href="ManagerHomePage.html"><span>Dash Board</span></a></li>
<li><a><span>Time Sheet</span></a></li>
<li><a><span>Project Planner</span></a></li>
<li><a><span>DMS</span></a></li>
<li><a><span>Report</span></a></li>
<li><a href="RiskIssue.html"><span>Risk, Issue</span></a></li>
<li><a href="ChangeRequest.html"><span>Change Request</span></a></li>
<li><a href="Cost.html"><span>Cost</span></a></li>
<li><a><span>Requirement</span></a></li>
<li><a href="Product.html"><span>Product</span></a></li>
<li><a href="WorkOrder.html"><span>Work Order</span></a></li>
  </h5>
</ul>
</div>


    <p>    
    <h1>Requirement</h1>


<!-- Search drop down list -->
<table>
          <tr>
            <td><b>&nbsp;&nbsp;Status&nbsp;&nbsp;</b></td>
            <td><b>&nbsp;&nbsp;Project Name &nbsp;&nbsp;</b></td>
            <td><b>&nbsp;&nbsp;Type&nbsp;</b></td>
            <td><b>&nbsp;&nbsp;Sort&nbsp;by&nbsp;&nbsp;</b></td>
          </tr>
          <tr>
            <td><select class="styled" class="SmallCombo">
                <option selected="selected" value="-1">All status</option>
                <option value="1">Closed</option>
                <option value="2">Cancelled</option>
                <option value="3">Tentative</option>
                <option value="0">On-going</option>
            </select></td>
            <td><select class="styled" class="SmallCombo">
                <option selected="selected" value="-1">All stages</option>
                <option value="1">Initiation</option>
                <option value="2">Definition</option>
                <option value="3">Solution</option>
                <option value="4">Construction</option>
                <option value="0">Transition &amp; Termination</option>
            </select></td>
            <td><select class="styled" class="SmallCombo">
                <option selected="selected" value="-1">All categories</option>
                <option value="0">Development</option>
                <option value="1">Maintenance</option>
                <option value="2">Others</option>
            </select></td>
            <td width="10%"><select class="styled" class="SmallCombo">
                <option selected="selected" value="0">Status</option>
                <option value="1">Task Name</option>
                <option value="2">Remaining Time</option>
                <option value="2">Stage</option>
                <option value="2">Product</option>
            </select></td>
            <td width="56%"><input type="button" name="input2" value="Search" /></td>
          </tr>
</table>    
 
<p>test here outside </p>   
<c:set var="list" value="${requirementList}" />
<c:if test="${not empty list}">
<table width="800" border="1">
<p>test here inside </p>
<!-- TABLE HEADER -->
  <tr>  
    <th scope="col">No</th>
    <th scope="col">Requirement Name</th>
    <th scope="col">Project Name</th>
    <th scope="col">Type</th>
    <th scope="col">Size</th>       
    <th scope="col">SRS</th>
    <th scope="col"><table cellspacing="0" cellpadding="0">
      <tr>
        <td></td>
        <td>Effort</td>
      </tr>
    </table></th>    
    <th scope="col"><table cellspacing="0" cellpadding="0">
      <tr>
        <td></td>
        <td nowrap="nowrap">Created Date</td>
      </tr>
    </table></th>
    <th scope="col">Response Date</th>
    <th scope="col">Action</th>
  </tr>
  
  <tr>
    <td>0</td>
    <td><a href="RequirementUpdate.html">Requirement A</a></td>
    <td>Test</td>
    <td>New</td>
    <td>2</td>
    <td>5</td>
    <td>trunk\document\design\SRS...</td>
    <td>30/5/2012</td>
    <td>Tested</td>
    <td><input type="button" value="Remove" name="OK"/></td>
  </tr>
  
  <tr>
    <td>0</td>
    <td><a href="RequirementUpdate.html">Requirement B</a></td>
    <td>Test_Demo</td>
    <td>Change Request</td>
    <td>4</td>
    <td>4</td>
    <td>trunk\document\design\SRS...</td>
    <td>30/5/2012</td>
    <td>Designed</td>
    <td><input type="button" value="Remove" name="OK"/></td>
  </tr>
  
  <c:set var="count" value="0" />
              <c:forEach items="${list}" var="requirement">
                <tr>
                  <form action="Controller">
                    <c:set var="count" value="${count + 1}" />
                    <td>${count}</td>
                    <td>${requirement.requirement}</td>
                    <td>${requirement.projectName}</td>
                    <td>${requirement.type}</td>
                    <td>${requirement.reqSize}</td>
                    <td>${requirement.effort}</td>
                    <td>${requirement.srs}</td>
                    <td>${requirement.createDate}</td>
                    <td>${requirement.responseDate}</td>                                                       
                    <td><input type="button" value="Remove" name="OK"/></td>
                  </form>
                </tr>
              </c:forEach>
  
</table>
</c:if>    


<!-- end .content --></div>
  
 <br>
      <div id="button">        
        <form:form method="post" commandName="CreateProjectForm" action="${formAction}">
            <button type="submit" name="Submit" value="Submit">Create New Product</button>
        </form:form> 
        <input type="button" name="import" value="Import" /> 
        <input type="button" name="report" value="Report" />
      </div>
 
  <div class="footer">
    <p>DMS Group</p>
    <!-- end .footer --></div>
  <!-- end .container --></div>

</body>
</html>