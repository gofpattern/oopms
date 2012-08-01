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
<jsp:include page="header.jsp" />

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
	
	function fnFeaturesInit ()
    {
        /* Not particularly modular this - but does nicely :-) */
        $('ul.limit_length>li').each( function(i) {
            if ( i > 10 ) {
                this.style.display = 'none';
            }
        } );
        
        $('ul.limit_length').append( '<li class="css_link">Show more<\/li>' );
        $('ul.limit_length li.css_link').click( function () {
            $('ul.limit_length li').each( function(i) {
                if ( i > 5 ) {
                    this.style.display = 'list-item';
                }
            } );
            $('ul.limit_length li.css_link').css( 'display', 'none' );
        } );
    }
	
	$(document).ready( function() {
        // Listen for click on toggle checkbox
        $('#select-all').click(function(event) {   
            if(this.checked) {
                // Iterate each checkbox
                $(':checkbox').each(function() {
                    this.checked = true;                        
                });
            } else { 
             $(":checkbox").each(function() { this.checked = false; }); 
             }
        });             
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
                            buttonImage: "/<spring:message code="app.context"/>/resource_files/images/calendar.gif",
                            buttonImageOnly: true
                        });
                        $( "#datepicker2" ).datepicker({
                            showOn: "button",
                            buttonImage: "/<spring:message code="app.context"/>/resource_files/images/calendar.gif",
                            buttonImageOnly: true
                        });
                        $( "#selectable" ).selectable();
            fnFeaturesInit();
            $('#mainTable2').dataTable( {
                "bFilter": true,
                "bSort": true,
                "bJQueryUI": true,
                "sPaginationType": "full_numbers"
            } );
            
            SyntaxHighlighter.config.clipboardSwf = 'media/javascript/syntax/clipboard.swf';
            SyntaxHighlighter.all();
            
        } );
	
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

  <script type="text/javascript" src='/RequirementPortlet/scripts/common.js'></script>

  <div class="container">

    <div class="header">
      <table width="960">
        <tr>
          <td width="697" height="92">&nbsp;</td>
          <td width="251" valign="top">
            <p>
              Hello, <a href="HomePage.html">Log Out</a>
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
    
    <portlet:renderURL var="goAddNewRequirementAction">
      <portlet:param name="action" value="goAddNewRequirement" />
    </portlet:renderURL>
    
       

    <form:form name="${portletNamespace}RequirementHome" commandName="RequirementForm" method="post">
      <table >

        <h1>Requirement Management</h1>


        <!-- Search drop down list -->
        <table>
          <tr>
            <td><b>&nbsp;Type&nbsp;</b></td>
            <td><b>&nbsp;Project Name &nbsp;</b></td>
            <td><b>&nbsp;Size&nbsp;</b></td>
            <td><b>&nbsp;Sort&nbsp;by&nbsp;</b></td>
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
            
          </tr>
        </table>

        <c:set var="list" value="${requirementList}" />
        <c:if test="${not empty list}">
      <table id="mainTable2" class="display dataTable" cellpadding="0"
    cellspacing="0" border="0">
            <!-- TABLE HEADER -->
            <thead>
            <tr>
              <th scope="col">No</th>
              <th scope="col">Requirement Name</th>
              <th scope="col">Project Name</th>
              <th scope="col">Type</th>
              <th scope="col">Size</th>
              <th scope="col">Effort</th>
              <th scope="col">SRS</th>
              <th scope="col">Created Date</th>
              <th scope="col">Response Date</th>
              <th scope="col">Action</th>
             </tr>
             </thead>
             <tbody>
            <tr>
              <td>-1</td>
              <td>Requirement A</td>
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
              <td>Requirement B</td>
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
            </tbody>
          </table>
        </c:if>


        <!-- end .content -->
      </table>

      <br>




      <table border="0" cellpadding="0" cellspacing="1" width="100%">
        <tbody>
          <tr>
            <td align="left" width="50%"><input name="RequirementAdd" class="Button"
              onclick='submitAction("${portletNamespace}RequirementHome", "${goAddNewRequirementAction}")' value="Add New"
              type="button"> <input name="Refresh" class="Button" onclick="javascript:doRefresh()"
              value="Refresh" type="button"> <input name="ExportDefect" class="Button"
              onclick="javascript:doExport()" value="Export" type="button"></td>
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