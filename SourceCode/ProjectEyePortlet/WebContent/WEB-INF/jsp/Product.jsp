<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<portlet:defineObjects />
<link rel="icon" href="https://c15027075.ssl.cf2.rackcdn.com/favicon.ico" type="image/x-icon"/>
<link type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/screen.css" rel="Stylesheet" />
<link type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/ui-lightness/jquery-ui-1.8.21.custom.css" rel="Stylesheet" />	
<link type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/common.css" rel="Stylesheet" />	
<link type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/uportal.css" rel="Stylesheet" />

<link rel="stylesheet" type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/print.css" media="print"/>
<link rel="stylesheet" type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/manage.css" media="all"/>				
<link rel="stylesheet" type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/datepicker.css" media="all"/>
<link rel="fluid-icon" href="https://c15027075.ssl.cf2.rackcdn.com/images/apple-touch-icon-114x114.png"/>
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/jquery-ui-1.8.21.custom.min.js"></script>
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/form-elements.js"></script>
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/css/ga.js"></script>

<script language="javascript" type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/css/jquery.js"></script>
<script language="javascript" type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/css/jquery.cookie.js"></script>
<script language="javascript" type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/css/default.js"></script>
<script language="javascript" type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/css/manage.js"></script>
<script language="javascript" type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/common.js"></script>
<meta name="robots" content="noindex, nofollow"/>
<script type="text/javascript" src="/<spring:message code="app.context"/>/resource_files/js/datatable.js"></script>
<link type="text/css" href="/<spring:message code="app.context"/>/resource_files/css/datatable.css" rel="Stylesheet" /> 
<script type="text/javascript">





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
        	

                     
                fnFeaturesInit();
                $('#mainTable1').dataTable( {
                    "bFilter": true,
                    "bSort": true,
                    "bJQueryUI": true,
                    "sPaginationType": "full_numbers"
                } );
			if(document.getElementById("deleteFlag").value==1) {
				document.getElementById("deleteFlag").value = 0;
				alert('This product has tasks assigned for it. Please remove task before remove this product');
			}
            } );
        </script>

<title>Product</title>	
</head>
<body id="portal" class="up fl-theme-mist">
<div  class="container"><%@ include file="/WEB-INF/jsp/Menu.jsp" %>
  <div class="content">

	<!-- begin .navigator -->
	
	<!-- end .navigator -->
	
	   <div class="fl-widget-titlebar titlebar portlet-titlebar" role="sectionhead">
    	<br><p class="title" id="headerDuyND">Product</p>
    </div>
    
    
    <portlet:actionURL var="formAction">
  		<portlet:param name="action" value="SearchProduct" />
  		<portlet:param name="projectId" value="${projectId}" />
	</portlet:actionURL>
<form:form name="${portletNamespace}SearchProduct" commandName="ProductForm" method="post" action="${formAction}">
    <br> <p id="header2DuyND"> Product Type <form:select  class="styled" path="workProduct_SelectedValue" items="${workProduct}"/> 
	<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}SearchProduct", "${formAction}")'>View</button></p><br>
	</form:form>
	<table id="mainTable1" class="display dataTable" cellpadding="0" cellspacing="0" border="0">
	<c:if test="${not empty projectProductList}">
   <thead><tr>
        <th width="6%" scope="row">No</th>    
        <th width="10%" scope="row">Name</th>
        <th width="10%" scope="row">Work product</th>
        <th width="6%" scope="row">Planned size</th>
        <th width="6%" scope="row">Actual size</th>
        <th width="33%" scope="row">Description</th>
        <c:if test="${(UserRole == 1) || (UserRole==0)}">
        <th width="17%" scope="row">Action</th>
        </c:if>    
    </tr> 
    </thead>
    <tbody> 
        <c:forEach var="product" items="${projectProductList}" varStatus="count">
            <tr>
            <portlet:renderURL var="renderAction">
            	<portlet:param name="action" value="GoUpdateProduct" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="productId" value="${product.productId}" />
            </portlet:renderURL>
            <portlet:renderURL var="renderAction2">
            	<portlet:param name="action" value="RemoveProduct" />
            	<portlet:param name="projectId" value="${projectId}" />
            	<portlet:param name="productId" value="${product.productId}" />
            </portlet:renderURL>
               <td scope="row">${count.count}</td>
               <td scope="row">
               <c:if test="${(UserRole == 1) || (UserRole==0)}"> 
               	<a href="${renderAction}">              
               		${product.name}               	
               	</a>
               	</c:if>
               	<c:if test="${(UserRole != 1) && (UserRole!=0)}">${product.name}</c:if> 
               </td>               
               <td scope="row">${product.workProduct}</td>
               <td scope="row">${product.plannedSize}</td>
               <td scope="row">${product.actualSize}</td>
               <td scope="row">${product.description}</td>
               <c:if test="${(UserRole == 1) || (UserRole==0)}">
               <td scope="row">
                 
					<button type="button" class="button blue small" onclick='return submitAction3("${portletNamespace}SearchProduct", "${renderAction2}", "Do you sure you want to delete this product?");'>Remove This Product</button>
				
			   </td>
			   </c:if>                                 
            </tr>
        </c:forEach>
   </tbody>
	</c:if>
	<c:if test="${empty projectProductList}">
		There is no product!
	</c:if>
    </table><br>
		<portlet:renderURL var="renderAction">
    		<portlet:param name="action" value="GoCreateProduct" />
        	<portlet:param name="projectId" value="${projectId}" />
    	</portlet:renderURL>
    	<c:if test="${(UserRole == 1) || (UserRole==0)}"> 
    	<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}SearchProduct", "${renderAction}")'>Add New Product</button> 
		</c:if>
	<input name="deleteFlag" type="hidden" value="${deleteFlag}" id="deleteFlag"/> 
  <!-- end .content --></div>
  <!-- end .container --></div>
</body>
</html>
