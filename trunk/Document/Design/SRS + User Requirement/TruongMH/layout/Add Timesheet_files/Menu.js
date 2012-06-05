//--------Truongnn1 start-----------------------------------------------
var DDSPEED = 10;
var DDTIMER = 15;

// main function to handle the mouse events //
function ddMenu(id,d){
  var h = document.getElementById(id + '-ddheader');
  var c = document.getElementById(id + '-ddcontent');
  clearInterval(c.timer);
  if(d == 1){
    clearTimeout(h.timer);
    if(c.maxh && c.maxh <= c.offsetHeight){return}
    else if(!c.maxh){
      c.style.display = 'block';
      c.style.height = 'auto';
      c.maxh = c.offsetHeight;
      c.style.height = '0px';
    }
    c.timer = setInterval(function(){ddSlide(c,1)},DDTIMER);
  }else{
    h.timer = setTimeout(function(){ddCollapse(c)},50);
  }
}

// collapse the menu //
function ddCollapse(c){
  c.timer = setInterval(function(){ddSlide(c,-1)},DDTIMER);
}

// cancel the collapse if a user rolls over the dropdown //
function cancelHide(id){
  var h = document.getElementById(id + '-ddheader');
  var c = document.getElementById(id + '-ddcontent');
  clearTimeout(h.timer);
  clearInterval(c.timer);
  if(c.offsetHeight < c.maxh){
    c.timer = setInterval(function(){ddSlide(c,1)},DDTIMER);
  }
}

// incrementally expand/contract the dropdown and change the opacity //
function ddSlide(c,d){
  var currh = c.offsetHeight;
  var dist;
  if(d == 1){
    dist = (Math.round((c.maxh - currh) / DDSPEED));
  }else{
    dist = (Math.round(currh / DDSPEED));
  }
  if(dist <= 1 && d == 1){
    dist = 1;
  }
  c.style.height = currh + (dist * d) + 'px';
  c.style.opacity = currh / c.maxh;
  c.style.filter = 'alpha(opacity=' + (currh * 100 / c.maxh) + ')';
  if((currh < 2 && d != 1) || (currh > (c.maxh - 2) && d == 1)){
    clearInterval(c.timer);
  }
}
//--------Truongnn1 end-----------------------------------------------

//Submit function
function MenuOnSubmit(goto) {
    var strRole = window.document.forms[0].Role.value;
    var form = window.document.forms[0];

    clearInvalidControls(form);

    // Logout
    if (goto == 'Logout') {
        LogoutClientHandler('TimesheetHomepage');
    }
    // ChangePassword
    else if (goto == 'ChangePassword') {
        GotoChangePasswordClientHandler(goto);
    }
    //Addnew
    else if (goto == 'AddTimesheet') {
	    if ( strRole.substring(0, 1) == '1' || strRole.substring(6, 7) == '1'
	    	 || strRole.substring(7, 8) == '1' || strRole.substring(8, 9) == '1') {
            GotoAddnewClientHandler(goto);
        }
        else {
            alert('Sorry, unauthorized access. You are not a Developer');
        }
    }
    else if (goto == 'ListTimesheet') {
   	    if ( strRole.substring(0, 1) == '1' || strRole.substring(6, 7) == '1'
   	    	 || strRole.substring(7, 8) == '1' || strRole.substring(8, 9) == '1') {
            GotoListingClientHandler(goto);
        }
        else {
            alert('Sorry, unauthorized access. You are not a Developer');
        }
    }
    //Import
    else if (goto == 'ImportTimesheet') {
        //if (strRole.substring(4, 5) == '1') {
            GotoImportClientHandler(goto);
        //}
        //else {
        //    alert('Sorry, unauthorized access. You are not a QA');
        //}
    }
    else if (goto == 'Summary') {
    	if ( strRole.substring(6, 7) == '1' || strRole.substring(7, 8) == '1' ) {
    		alert('Sorry, unauthorized access. You are not a Developer');
    	}
    	else {
   	        GotoSummaryClientHandler("SummaryReport");
    	}
    }
    else if (goto == 'Inquiry') {
    	if ( strRole.substring(6, 7) == '1' || strRole.substring(7, 8) == '1' ) {
    		alert('Sorry, unauthorized access. You are not a Developer');
    	}
    	else {
   	        GotoInquiryClientHandler("InquiryReport");
    	}
    }
    //HanhTN added -- 08/08/2006
    else if (goto == 'Tracking') {
    	if ( strRole.substring(6, 7) == '1' || strRole.substring(7, 8) == '1' ) {
    		alert('Sorry, unauthorized access. You are not a Developer');
    	}
    	else {
   	        GotoTrackingClientHandler("LackTSGroup");
    	}
    }
    else if (goto == 'HelpActivityCode') {
        HelpActivityCodeClientHandler('GotoHelpActivityCode');
    }

    //LD Approve
    else if (goto == 'ListPL') {
        if (strRole.substring(1, 2) == '1' || strRole.substring(2, 3) == '1') {
            GotoLDApproveClientHandler(goto);
        }
        else {
            alert('Sorry, unauthorized access. You are not a Leader');
        }
    }
    //Other Approve
    else if (goto == 'ListGL') {
        if (strRole.substring(3, 4) == '1' || strRole.substring(2, 3) == '1') {
            GotoOtherApproveClientHandler(goto);
        }
        else if (strRole.substring(1, 2) == '1' || strRole.substring(2, 3) == '1') {
            GotoOtherApproveClientHandler(goto);
        }
        else {
            alert('Sorry, unauthorized access. You are not a Leader');
        }
    }
    //QA Approve
    else if (goto == 'ListQA') {
        if (strRole.substring(4, 5) == '1') {
            GotoQAApproveClientHandler(goto);
        }
        else {
            alert('Sorry, unauthorized access. You are not a QA');
        }
    }
}

//Clear invalid date values if this form contains them
function clearInvalidControls(form) {
    var control;
    for (var count = 0x00; count < form.length; count++) {
        control = form.elements[count];
        if (control.name.indexOf("FromDate", 0) >= 0 ||
                control.name.indexOf("ToDate", 0) >= 0) {
            
            if (control.value.length > 0) {
                if (!isDate(control.value)) {
                    form.item(count).value = "";
                }
            }
        }
    }
}

//*****************************************************************************
// Menu Client Handlers....

function LogoutClientHandler(eventSourceName) {
    document.forms[0].hidAction.value = eventSourceName;    //approval action
    fireMenuServerEvent(eventSourceName);
}

function GotoChangePasswordClientHandler(eventSourceName) {
    document.forms[0].hidAction.value = "AA";
    fireMenuServerEvent(eventSourceName);
}

//
function GotoListingClientHandler(eventSourceName) {
    document.forms[0].hidAction.value = "AA";
    fireMenuServerEvent(eventSourceName);
}

function GotoAddnewClientHandler(eventSourceName) {
    document.forms[0].hidAction.value = "AA";
    fireMenuServerEvent(eventSourceName);
}

function GotoImportClientHandler(eventSourceName) {
    document.forms[0].hidAction.value = "AA";
    fireMenuServerEvent(eventSourceName);
}

//
function GotoInquiryClientHandler(eventSourceName) {
    document.forms[0].hidAction.value = "RA";       //report action
    fireMenuServerEvent(eventSourceName);
}

function GotoSummaryClientHandler(eventSourceName) {
    document.forms[0].hidAction.value = "RA";
    fireMenuServerEvent(eventSourceName);
}

function GotoWeeklyClientHandler(eventSourceName) {
    document.forms[0].hidAction.value = "RA";
    fireMenuServerEvent(eventSourceName);
}

//HanhTN added -- 08/08/2006
function GotoTrackingClientHandler(eventSourceName) {
//	alert("Hello -- GotoTrackingClientHandler");
    document.forms[0].hidAction.value = "RA";
    fireMenuServerEvent(eventSourceName);
}

function HelpActivityCodeClientHandler(eventSourceName) {
    document.forms[0].hidAction.value = "AA";
    fireMenuServerEvent(eventSourceName);
}

//////////////////////////////////
function GotoLDApproveClientHandler(eventSourceName) {
    document.forms[0].hidAction.value = "AA";
    fireMenuServerEvent(eventSourceName);
}

function GotoQAApproveClientHandler(eventSourceName) {
    document.forms[0].hidAction.value = "AA";
    fireMenuServerEvent(eventSourceName);
}

function GotoOtherApproveClientHandler(eventSourceName) {
    document.forms[0].hidAction.value = "AA";
    fireMenuServerEvent(eventSourceName);
}

//////////////////////////////

function fireMenuServerEvent(eventSourceName) {
    document.forms[0].hidActionDetail.value = eventSourceName;
    document.forms[0].action = "TimesheetServlet";
    document.forms[0].submit();
}