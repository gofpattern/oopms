<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet"%>
<%@ taglib prefix="portlet2" uri="http://java.sun.com/portlet_2_0"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="javax.portlet.PortletSession"%>

<c:set var="portletNamespace" scope="request">
  <portlet:namespace />
</c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Requirement Home</title>
<jsp:include page="header.jsp" />
<portlet2:defineObjects />
<portlet:defineObjects />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

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

  <div class="container">

    <!-- User infor -->
    <table border="0">
      <tr>
        <th><strong>User: </strong></th>
        <td><strong><font color="#1490E3"><%=portletSession.getAttribute("USER", PortletSession.APPLICATION_SCOPE)%></font></strong></td>
      </tr>
      <tr>
        <th><strong>Joined Projects: </strong></th>
        <td><strong><font color="#1490E3">${projectList.size()}</font></strong></td>
      </tr>
      <tr>
        <th><strong>Requirements: </strong></th>
        <td><strong><font color="#1490E3"><a
              href='<portlet:renderURL><portlet:param name="action" value="requirementmanager"/></portlet:renderURL>'>Lists</a></font></strong></td>
      </tr>
      <tr>
        <th><strong>Projects: </strong></th>
        <td><strong><font color="#1490E3"><a
              href='<portlet:renderURL><portlet:param name="action" value="requirementwelcome"/></portlet:renderURL>'>Lists</a></font></strong></td>
      </tr>
    </table>

    <portlet:actionURL var="formAction">
      <portlet:param name="action" value="search" />
    </portlet:actionURL>

    <portlet:renderURL var="goAddNewRequirementAction">
      <portlet:param name="action" value="goAddNewRequirement" />
    </portlet:renderURL>

    <portlet:renderURL var="goUpdateRequirementAction">
      <portlet:param name="action" value="goUpdateRequirement" />
    </portlet:renderURL>

    <portlet:renderURL var="back">
      <portlet:param name="action" value="requirementwelcome" />
    </portlet:renderURL>

    <portlet:renderURL var="deleteRequirementAction">
      <portlet:param name="action" value="deleteRequirement" />
    </portlet:renderURL>

    <form:form name="${portletNamespace}RequirementHome" commandName="RequirementForm" method="post">
      <table width="70%" cellspacing="1">
        <!-- content -->

        <h1>Requirement Management</h1>
        
        <c:if test="${empty requirementList}">
          <h3>There is not requirement added for this project yet.</h3>
        </c:if>
        <c:if test="${not empty requirementList}">
          <table id="mainTable2" class="display dataTable" cellpadding="0" cellspacing="0" border="0">

            <!-- TABLE HEADER -->
            <thead>
              <tr>
                <th width="5%"><input id="select-all" type="checkbox" name="allbox" value="checkAll"></th>
                <th scope="col">No</th>
                <th scope="col">Requirement Name</th>
                <th scope="col">Project Name</th>
                <th scope="col">Type</th>
                <th scope="col">Size</th>
                <th scope="col">Effort</th>
                <th scope="col">SRS</th>
                <th scope="col">Created Date</th>
                <th scope="col">Designed Date</th>
              </tr>
            </thead>

            <tbody>
              <c:set var="count" value="0" />
              <c:forEach items="${requirementList}" var="requirement" varStatus="status">
                <tr>
                  <c:set var="count" value="${count + 1}" />
                  <td class="cb"><input id="checkbox" type="checkbox"
                    name="requirementList[${status.index}].requirementID" value="${requirement.requirementID}"></td>
                  <td>${count}</td>
                  <td>${requirement.requirement}</td>
                  <td>${requirement.projectName}</td>
                  <td>${requirement.type}</td>
                  <td>${requirement.reqSize}</td>
                  <td>${requirement.effort}</td>
                  <td>${requirement.srs}</td>
                  <td>${requirement.createDate}</td>
                  <td>${requirement.designedDate}</td>
                </tr>
              </c:forEach>
            </tbody>

          </table>
        </c:if>

      </table>
      <!-- end .content -->

      <br>

      <!-- Button -->
      <table border="0" cellpadding="0" cellspacing="1" width="100%">
        <tbody>
          <tr>
            <td align="left" width="50%"><c:if test="${ROLE=='Project Manager' }">
                <input name="RequirementAdd" class="Button"
                  onclick='submitAction("${portletNamespace}RequirementHome", "${goAddNewRequirementAction}")'
                  value="Add New" type="button">
                <input name="RequirementAdd" class="Button"
                  onclick='submitAction("${portletNamespace}RequirementHome", "${goUpdateRequirementAction}")'
                  value="Update" type="button">
                <input name="RequirementAdd" class="Button"
                  onclick='submitAction("${portletNamespace}RequirementHome", "${deleteRequirementAction}")'
                  value="Remove" type="button">
              </c:if> <input name="RequirementAdd" class="Button"
              onclick='submitAction("${portletNamespace}RequirementHome", "${back}")' value="Back" type="button">
              <input name="ExportDefect" class="Button" onclick="javascript:doExport()" value="Export" type="button">
            </td>
          </tr>
        </tbody>
      </table>
      <!-- Button -->

    </form:form>

    <br>
    <div class="footer">
      <p>DMS Group</p>
      <!-- end .footer -->
    </div>
    <!-- end .container -->
  </div>

</body>
</html>