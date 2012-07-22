<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">

<script type="text/javascript" src="/<spring:message code="app.context"/>/vertical-menu/jquery-1.6.1.min.js"></script>

<script type="text/javascript" src="/<spring:message code="app.context"/>/vertical-menu/ddaccordion.js">

/***********************************************
* Accordion Content script- (c) Dynamic Drive DHTML code library (www.dynamicdrive.com)
* Visit http://www.dynamicDrive.com for hundreds of DHTML scripts
* This notice must stay intact for legal use
***********************************************/

</script>

<script type="text/javascript">

//Initialize Arrow Side Menu:
ddaccordion.init({
	headerclass: "menuheaders", //Shared CSS class name of headers group
	contentclass: "menucontents", //Shared CSS class name of contents group
	revealtype: "clickgo", //Reveal content when user clicks or onmouseover the header? Valid value: "click", or "mouseover"
	mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
	collapseprev: true, //Collapse previous content (so only one open at any time)? true/false 
	defaultexpanded: [0], //index of content(s) open by default [index1, index2, etc]. [] denotes no content.
	onemustopen: false, //Specify whether at least one header should be open always (so never all headers closed)
	animatedefault: false, //Should contents open by default be animated into view?
	persiststate: true, //persist state of opened contents within browser session?
	toggleclass: ["unselected", "selected"], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
	togglehtml: ["none", "", ""], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
	animatespeed: 500, //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
	oninit:function(expandedindices){ //custom code to run when headers have initalized
		//do nothing
	},
	onopenclose:function(header, index, state, isuseractivated){ //custom code to run whenever a header is opened or closed
		//do nothing
	}
})

</script>

<style type="text/css">

.arrowsidemenu{
	width: 180px; /*width of menu*/
	border-style: solid solid none solid;
	border-color: #94AA74;
	border-size: 1px;
	border-width: 1px;
}
	
.arrowsidemenu div a{ /*header bar links*/
	font: bold 12px Verdana, Arial, Helvetica, sans-serif;
	display: block;
	background: transparent url(arrowgreen.gif) 100% 0;
  height: 24px; /*Set to height of bg image-padding within link (ie: 32px - 4px - 4px)*/
	padding: 4px 0 4px 10px;
	line-height: 24px; /*Set line-height of bg image-padding within link (ie: 32px - 4px - 4px)*/
	text-decoration: none;
}
	
.arrowsidemenu div a:link, .arrowsidemenu div a:visited{
	color: #26370A;
}

.arrowsidemenu div a:hover{
	background-position: 100% -32px;
}

.arrowsidemenu div.unselected a{ /*header that's currently not selected*/
	color: #6F3700;
}

	
.arrowsidemenu div.selected a{ /*header that's currently selected*/
	color: blue;
	background-position: 100% -64px !important;
}

.arrowsidemenu ul{
	list-style-type: none;
	margin: 0;
	padding: 0;
}

.arrowsidemenu ul li{
	border-bottom: 1px solid #a1c67b;
}


.arrowsidemenu ul li a{ /*sub menu links*/
	display: block;
	font: normal 12px Verdana, Arial, Helvetica, sans-serif;
	text-decoration: none;
	color: black;
	padding: 5px 0;
	padding-left: 10px;
	border-left: 10px double #a1c67b;
}

.arrowsidemenu ul li a:hover{
	background: #d5e5c1;
}

</style>

<body>

<div class="arrowsidemenu">

<div class="menuheaders"><a href="#" title="CSS">Menu</a></div>
	<ul class="menucontents">
    <c:forEach var="masterData" items="${envList}">
    <li><a href="#">${masterData}</a></li>
    </c:forEach>
	</ul>
</div>


</body>

</html> 
