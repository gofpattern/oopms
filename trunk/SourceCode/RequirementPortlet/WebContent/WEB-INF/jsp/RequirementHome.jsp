<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- Uncomment below lines to add portlet taglibs to jsp
<%@ page import="javax.portlet.*"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<portlet:defineObjects />
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<portlet:defineObjects />
<c:set var="portletNamespace" scope="request"><portlet:namespace/></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="StyleSheet" href="resource_files/DMSStyleSheet.css" type="text/css">
<link rel="StyleSheet" href="resource_files/pcal.css" type="text/css">

<title>Requirement Home</title>

<script language="javascript">
	function doRefresh() {
		var form = document.frmAllDefectList;
		if (!isValidForm()) {
			return;
		}
		form.hidAction.value = "DM";
		form.hidActionDetail.value = "SearchDefect";
		form.action = "DMSServlet";
		form.submit();
	}

	function doExport() {
		var form = document.frmAllDefectList;
		if (!isValidForm()) {
			return;
		}
		form.onsubmit = window.open('about:blank', 'popup',
				'width=780,height=550,top=0,left=0,menubar=yes');
		form.target = "popup";
		form.hidExportAll.value = "true";
		form.hidAction.value = "DM";
		form.hidActionDetail.value = "ExportDefect";
		form.action = "DMSServlet";
		form.submit();
		//Reset to default settings
		form.onsubmit = "";
		form.target = "";
	}

	function doMoveDefect() {
		var form = document.frmAllDefectList;
		//    if (!isValidForm()) {
		//        return;
		//    }
		if (checkValid()) {
			form.onsubmit = "";
			form.target = "";
			form.hidAction.value = "DM";
			form.hidActionDetail.value = "MoveDefect";
			form.action = "DMSServlet";
			form.submit();
		}
		return;
	}
</script>

<style type="text/css">
#tabs28 {
  float: left;
  width: 100%;
  background: #EFF4FA;
  font-size: 93%;
  line-height: normal;
  border-bottom: 1px solid #DD740B;
}

#tabs28 ul {
  margin: 0;
  padding: 10px 10px 0 50px;
  list-style: none;
}

#tabs28 li {
  display: inline;
  margin: 0;
  padding: 0;
}

#tabs28 a {
  float: left;
  background: url(slide-left.gif) no-repeat left top;
  margin: 0;
  padding: 0 0 0 5px;
  text-decoration: none;
}

#tabs28 a span {
  float: left;
  display: block;
  background: url(side-right.gif) no-repeat right top;
  padding: 5px 15px 4px 6px;
  color: #FFF;
}

#tabs28 a span {
  float: none;
}

#tabsI a:hover span {
  color: #FFF;
}

#tabs28 a:hover {
  background-position: 0% -42px;
}

#tabs28 a:hover span {
  background-position: 100% -42px;
}
</style>

</head>
<body>

  <script type="text/javascript" src='/RequirementModule/scripts/common.js'></script>

  <div class="container">

    <div class="header">
      <table width="960">
        <tr>
          <td width="697" height="92">&nbsp;</td>
          <td width="251" valign="top">
            <p>
              Hello Duy , <a href="HomePage.html">Log Out</a>
            </p>
            <p>
              <a href="UserDetail.html">Change your Information</a></br> <a href="Create Project.html">Create new Project</a>
            </p>
          </td>
        </tr>
      </table>
    </div>

    <portlet:actionURL var="formAction">
      <portlet:param name="action" value="search" />
    </portlet:actionURL>
    
    <portlet:renderURL var="goAddNewRequirement">
      <portlet:param name="action" value="goAddNewRequirement" />
    </portlet:renderURL>
    
    <portlet:actionURL var="goAddNewRequirementAction">
      <portlet:param name="action" value="goAddNewRequirementAction" />
    </portlet:actionURL>
    
    
    <portlet:renderURL var="sortRequirement">
      <portlet:param name="action" value="sort" />
    </portlet:renderURL>
    <portlet:actionURL var="sortRequirementAction">
      <portlet:param name="action" value="sortAction" />
    </portlet:actionURL>


    <form:form name="${portletNamespace}RequirementHome" commandName="RequirementForm" method="post">
      <div class="content">

<!-- Module link -->
<!--   
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
-->

        <h1>Requirement Management</h1>


        <!-- Search drop down list -->
        <table>
          <tr>
            <td><b>&nbsp;&nbsp;Type&nbsp;&nbsp;</b></td>
            <td><b>&nbsp;&nbsp;Project Name &nbsp;&nbsp;</b></td>
            <td><b>&nbsp;&nbsp;Size&nbsp;</b></td>
            <td><b>&nbsp;&nbsp;Sort&nbsp;by&nbsp;&nbsp;</b></td>
          </tr>
          <tr>
            <td><select class="styled" class="SmallCombo">
                <option selected="selected" value="-1">All Types</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select></td>
            <td width="26%"><form:select cssClass="styled" path="projectDefault" multiple="single">
                <c:set var="projectMap" value="${projectMap}" />
                <form:options items="${projectMap}" />
              </form:select></td>
            <td><select class="styled" class="SmallCombo">
                <option selected="selected" value="-1">All Size</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select></td>
            <td width="10%"><select class="styled" class="SmallCombo">
                <option selected="selected" value="0">Created Date</option>
                <option value="1">Response Date</option>
                <option value="3">Coded Date</option>
                <option value="4">Tested Date</option>
                <option value="5">Deployed Date</option>
            </select></td>
            <td><input name="RequirementAdd" class="Button"
              onclick='submitAction("${portletNamespace}RequirementHome", "${sortRequirement}")' value="Sort"
              type="button"></td>
              <td><input name="RequirementAdd" class="Button"
              onclick='submitAction("${portletNamespace}RequirementHome", "${sortRequirementAction}")' value="Sort Action"
              type="button"></td>              
            <td><input name="RequirementAdd" class="Button"
              onclick='submitAction("${portletNamespace}RequirementHome", "${goAddNewRequirementAction}")' value="Add New Action"
              type="button"></td>
              <td><input name="RequirementAdd" class="Button"
              onclick='submitAction("${portletNamespace}RequirementHome", "${goAddNewRequirement}")' value="Add New"
              type="button"></td>
          </tr>
        </table>

        <c:set var="list" value="${requirementList}" />
        <c:if test="${not empty list}">
          <table width="800" border="1">

            <!-- TABLE HEADER -->
            <tr>
              <th scope="col">No</th>
              <th scope="col">Requirement Name</th>
              <th scope="col">Project Name</th>
              <th scope="col">Type</th>
              <th scope="col">Size</th>
              <th scope="col"><table cellspacing="0" cellpadding="0">
                  <tr>
                    <td></td>
                    <td>Effort</td>
                  </tr>
                </table></th>
              <th scope="col">SRS</th>
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
              <td>3</td>
              <td>2</td>
              <td>5</td>
              <td>trunk\document\design\SRS...</td>
              <td>30/5/2012</td>
              <td>Tested</td>
              <td><input type="button" value="Remove" name="OK" /></td>
            </tr>

            <tr>
              <td>0</td>
              <td><a href="RequirementUpdate.html">Requirement B</a></td>
              <td>Test_Demo</td>
              <td>4</td>
              <td>4</td>
              <td>4</td>
              <td>trunk\document\design\SRS...</td>
              <td>30/5/2012</td>
              <td>Designed</td>
              <td><input type="button" value="Remove" name="OK" /></td>
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
                  <td><input type="button" value="Remove" name="OK" /></td>
                </form>
              </tr>
            </c:forEach>

          </table>
        </c:if>


        <!-- end .content -->
      </div>

      <br>




      <table border="0" cellpadding="0" cellspacing="1" width="100%">
        <tbody>
          <tr>
            <td align="left" width="50%">
              <input name="RequirementAdd" class="Button"
              onclick='submitAction("${portletNamespace}RequirementHome", "${goAddNewRequirementAction}")' value="Add New"
              type="button"> 
              <input name="Refresh" class="Button" onclick="javascript:doRefresh()"
              value="Refresh" type="button"> 
              <input name="ExportDefect" class="Button"
              onclick="javascript:doExport()" value="Export" type="button">
              
              <portlet:actionURL var="goAddNewRequirementAction2">
                <portlet:param name="action" value="goAddNewRequirementAction2Value" />
              </portlet:actionURL> 


              <form:form name="requirementFormName" method="post" commandName="RequirementForm"
                action="${goAddNewRequirementAction2}">
                <p><input type="submit" id="Search" class="button green small"
                name="Add" value="Add New Requirement" /></p>
              </form:form> 
                            
            </td>
          </tr>
          <tr>
            <td></td>
          </tr>
          <tr>
            <td align="left" width="50%">Move Requirement(s) to project&nbsp; <form:select cssClass="styled"
                path="projectDefault" multiple="single">
                <c:set var="projectMap" value="${projectMap}" />
                <form:options items="${projectMap}" />
              </form:select>&nbsp;<input name="MoveDefect" class="Button" onclick="javascript:doMoveDefect()" value="Move Requirement"
              type="button"></td>
          </tr>

        </tbody>
      </table>

    </form:form>

    <div class="footer">
      <p>DMS Group</p>
      <!-- end .footer -->
    </div>
    <!-- end .container -->
  </div>

</body>
</html>