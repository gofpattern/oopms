/* truongnn1 */
function getWorkProductList(nRowNum, productCategoryID, projectID, nCurrentProduct){
	var postString = 'hidAction=AA&hidActionAjax=ajaxCall';
	postString = postString + '&productCategoryID=' + productCategoryID;
	postString = postString + '&projectID=' + projectID;
	postString = postString + '&ajaxAction=workProduct';
	
  	var xmlHttpReq = getAjaxObj();
    
    if (typeof xmlHttpReq != "undefined") {
	    xmlHttpReq.open('POST', 'TimesheetServlet', true);
	    xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    xmlHttpReq.onreadystatechange = function() {
	        if (xmlHttpReq.readyState == 4 && xmlHttpReq.status == 200) {
	            updatePageProduct(xmlHttpReq.responseXML, nRowNum, nCurrentProduct);
	        }
	    }
	    xmlHttpReq.send(postString);
    }
}
function updatePageProduct(xmlDoc, nRowNum, nCurrentProduct){
	var form = document.forms[0];
	form.Product[nRowNum].options.length = 0; //Remove all options from drop-down select list
	if(xmlDoc){
		var optionList = xmlDoc.documentElement.childNodes;
		for (i=0;i<optionList.length;i++)
		{
			if(optionList[i].nodeType == 1 && optionList[i].tagName=='option'){
				var valueList = optionList[i].childNodes;
				var v = '';
				var t = '';
				for (j=0;j<valueList.length;j++){
					if(valueList[j].nodeType == 1){					
						if(valueList[j].tagName=='value'){v = valueList[j].firstChild.nodeValue;}
						if(valueList[j].tagName=='text'){t = valueList[j].firstChild.nodeValue;}
					}
				}
				if(v!=''){
					if (nCurrentProduct == v) {
						appendOption(form.Product[nRowNum], v, t, true);
					}
		 			else {
 						appendOption(form.Product[nRowNum], v, t, false);
 					}
	  			}
	  		}
		}
	}
	if (form.Product[nRowNum].options.length <= 0) {
		appendOption(form.Product[nRowNum], 0, "", false);
	}
}
function getWorkProductCategoryList(nRowNum, currentProcessID, nCurrentProduct){
	var postString = 'hidAction=AA&hidActionAjax=ajaxCall';
	postString = postString + '&ProcessID=' + currentProcessID;

  	var xmlHttpReq = getAjaxObj();
    
    if (typeof xmlHttpReq != "undefined") {
	    xmlHttpReq.open('POST', 'TimesheetServlet', true);
	    xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    xmlHttpReq.onreadystatechange = function() {
	        if (xmlHttpReq.readyState == 4 && xmlHttpReq.status == 200) {
	            updatePageProductCategory(xmlHttpReq.responseXML, nRowNum, nCurrentProduct);
	        }
	    }
	    xmlHttpReq.send(postString);
    }
}

function updatePageProductCategory(xmlDoc, nRowNum, nCurrentProduct){
	var form = document.forms[0];
	form.ProductCategory[nRowNum].options.length = 0; //Remove all options from drop-down select list
	appendOption(form.ProductCategory[nRowNum], 0, "", false);
	if(xmlDoc){
		var optionList = xmlDoc.documentElement.childNodes;
		for (i=0;i<optionList.length;i++)
		{
			if(optionList[i].nodeType == 1 && optionList[i].tagName=='option'){
				var valueList = optionList[i].childNodes;
				var v = '';
				var t = '';
				for (j=0;j<valueList.length;j++){
					if(valueList[j].nodeType == 1){					
						if(valueList[j].tagName=='value'){v = valueList[j].firstChild.nodeValue;}
						if(valueList[j].tagName=='text'){t = valueList[j].firstChild.nodeValue;}
					}
				}
				if(v!=''){
					if (nCurrentProduct == v) {
						appendOption(form.ProductCategory[nRowNum], v, t, true);
					}
		 			else {
 						appendOption(form.ProductCategory[nRowNum], v, t, false);
 					}
	  			}
	  		}
		}
	}
}
function getAjaxObj(){
    var xmlHttpReq;
    
    if (window.XMLHttpRequest) {
        xmlHttpReq = new XMLHttpRequest();// code for IE7+, Firefox, Chrome, Opera, Safari
    }
    else if (window.ActiveXObject) {
        xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");// code for IE6, IE5
    }
    return xmlHttpReq;
}
/* truongnn1 */

function appendOption(ctrlSelect, strValue, strText, bSelected) {
    var optNew = document.createElement("option");
    var txtNode = document.createTextNode(strText);
    optNew.setAttribute("value",strValue);
    optNew.appendChild(txtNode);
    ctrlSelect.appendChild(optNew);
    if (bSelected == true) {
        optNew.selected = true;
    }
}

// Select Work Product lists by Processes
function setProductCategoryList() {
	var objProcess = document.forms[0].Process;
	var objProductCategory = document.forms[0].ProductCategory;
	for (var i = 0; i < objProcess.length - 1; i++) {
		getWorkProductCategoryList(i, objProcess[i].value, objProductCategory[i].value);
	}
}
function setProductList() {
	var objProject = document.forms[0].Project;
	var objProductCategory = document.forms[0].ProductCategory;
	var objProduct = document.forms[0].Product;
	for (var i = 0; i < objProductCategory.length - 1; i++) {
		getWorkProductList(i, objProductCategory[i].value, objProject[i].value, objProduct[i].value);
	}
}
function resetAddNew() {
	var form = document.forms[0];
	for (var nRowNum = 0; nRowNum < form.Product.length - 1; nRowNum++) {
		while (form.Product[nRowNum].options.length > 0) {
			form.Product[nRowNum].options[0] = null;
		}
		appendOption(form.Product[nRowNum], 0, "", false);
	}
}

function resetUpdate() {
	var objProcess = document.forms[0].oldProcess;
	var objProduct = document.forms[0].oldProduct;
	for (var i = 0; i < objProcess.length - 1; i++) {
		selectProcess(i, objProcess[i].value, objProduct[i].value);
	}
}
