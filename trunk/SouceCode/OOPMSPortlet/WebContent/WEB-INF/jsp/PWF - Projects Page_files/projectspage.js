 /*
THiS IS THE UNCOMPRESSED VERSION OF THE projectspage.js

FUNCTION NAMES
--------------------
toggleJobTables
closeCats
openCats
EditinPlaceDropDownJob
DeleteOption
HoverOnRow
RemoveToolRow
HoverOutRow
ChangeProjectCategory
AfterCategoryMove
ActivateTools
CompleteProject
DeleteProject
UpdateEditinLineDetails
LoadClientContacts
ChangeProjectsView
ChangeProjectSortHeaders
*/
var webroot = "";
var projectjs_webroot = "";
var	userid = "";
var	fuseaction = "";
var	fusesubaction = "";	
var projectterminology = "";
var currentrowid = "";
var contacttypeID = "";
var tableids = "";
var account_plan = "";


$(document).ready(function(){
	$('.admintitles').click(function (e){
		e.stopPropagation();
	});
});

function toggleJobTables(which) {
	if(document.getElementById("jobcat_"+which).style.display != "none") {
	document.getElementById("jobcat_"+which).style.display="none";
	$('#pagging_'+which).hide();
	$('#record_'+which).show();
	} 
	else {
	document.getElementById("jobcat_"+which).style.display="block";
	$('#record_'+which).hide();
	$('#pagging_'+which).show();
	}
}

function closeCats() {
	$('.jobcatdisplay').each(function(){
		id = $(this).attr('id');
		id =id.substring(id.indexOf("_")+1); 
		$('#pagging_'+id).hide();
		$('#record_'+id).show();
		$('.jobcatdisplay').hide();
	});
	document.getElementById("jobcatslinkopen").style.display="inline";
	document.getElementById("jobcatslinkclose").style.display="none";
}

function openCats() {
	$('.jobcatdisplay').each(function(){
		id = $(this).attr('id');
		id =id.substring(id.indexOf("_")+1); 
		$('#pagging_'+id).show();
		$('#record_'+id).hide();
		$('.jobcatdisplay').show();
	});
	document.getElementById("jobcatslinkopen").style.display="none";
	document.getElementById("jobcatslinkclose").style.display="inline";
}


function EditinPlaceDropDownJob(jobid,id, type, childid, taskid){
 var height=0;
 $.ajax({
    type: "POST",
    url: projectjs_webroot+"/act_form_action.cfm",
    data: "actionpage=jobs/jquery/jquery_editinplace.cfm&Fuseaction="+fuseaction+"&Fusesubaction="+fusesubaction+"&JobID="+jobid+"&TaskID="+taskid+"&Type="+type+"&Height="+height,
    success: function(responsedata){
    if(responsedata != 0){  
     $("#editwindow").remove();
	 $('#'+id).append(responsedata);	 
     $("#editwindow").slideDown('fast', function(){
     });
    $("#editwindow").css("zIndex","1");
	} 
   }    
  });  
} 

function DeleteOption(id, option){
 if(option == 'no')$("#job_delete_"+id).slideUp('slow');
 else if (option == 'yes'){
  $("#job_delete_"+id).slideDown('slow');
  $('#job_complete_'+id).remove();
  $('#job_move_'+id).remove();		
 }
}

function HoverOnRow(id,bgcolor){
	$('#prj_row_'+id+' > div').css({'backgroundColor':bgcolor});
	if(currentrowid != id && currentrowid != ""){
	$('.deletedrpdwn').hide();
	}
	currentrowid = id
}

function RemoveToolRow(id){
	if(!navigator.userAgent.toLowerCase().indexOf('chrome') > -1){
		$('.completeprojectsdrpdwn').remove();
	    $('.moveprojectsdrpdwn').remove();	
		$('.deletedrpdwn').hide();
	}	
	else{
		if(currentrowid != id && currentrowid != ""){
			$('.completeprojectsdrpdwn').remove();
		    $('.moveprojectsdrpdwn').remove();	
			$('.deletedrpdwn').hide();
		}
	}
}

function HoverOutRow(id,bgcolor){
	$('#prj_row_'+id+' > div').css({'backgroundColor':bgcolor});
}

function ChangeProjectCategory(id){
	var catid = document.getElementById('categorymove_'+id).value;
	$.post(projectjs_webroot+"/act_form_action.cfm", {actionpage:'jobs/jquery/jquery_changeprojcat.cfm',JobStatus:catid,JobID:id}, function(responsedata){
		   $('#categorymove_'+id).hide();	
		   $('#categoryinfo_'+id).append(projectterminology+' category changed.');	
		   $("#job_move_"+id).css({backgroundColor: '#A1DB00'});
		   timeoutvariable = setTimeout("AfterCategoryMove("+id+","+catid+")",1000);		   
	});
}

function AfterCategoryMove(id, catid){
   var jobdetails = $('#prj_row_'+id).html();
   $("#prj_row_"+id + "> div").animate({backgroundColor: '#F5F5F5'}, 1000);
	$("#prj_row_"+id).slideToggle('slow', function(data){		
		$("#prj_row_"+id).remove();
		$("#noprojectmessage_"+catid).remove();
		if(document.getElementById('jobnewcat_'+catid)){
		  $('#jobnewcat_'+catid).show(); 
		}		
		if(document.getElementById(catid)){
		    $('#job_complete_'+id).remove();
		    $('#job_move_'+id).remove();			
			data = '<div id="prj_row_'+id+'" style="clear:both;background-color:#ffffff;padding-top:1px;'
			if(!$.browser.msie){data = data +'padding-bottom:3px;'}
			data = data +'"';
			if(contacttypeID == 3){data = data +'class="groupItem"'}
			data = data +' onmouseover="$(this).css({';
			data = data + "'backgroundColor':'#eaeaea'});$('#tools_"+id+"').show();";
			data = data +'"onmouseout="$(this).css({';
			data = data +"'backgroundColor':'#ffffff'});$('#tools_"+id+"').hide();"
			data = data + '">' + jobdetails + '</div>'
			$('#'+catid).append(data);	
			$('#prj_row_'+id).hide();
			$('#prj_row_'+id).slideDown();	 	
		    $('#prj_row_'+id+' > div').css({backgroundColor: '#A1DB00'});
		    $('#prj_row_'+id+' > div').animate({ backgroundColor: "#FFFFFF" }, 1000);
		    $('#job_complete_'+id).remove();
		    $('#job_move_'+id).remove();			
		}
		else{location.reload(true);} 		
	});
}

function ActivateTools(id){
	var selectvalue = document.getElementById('tools_'+id).value;
	RemoveToolRow(id);	
	if(selectvalue != 0){
		if(selectvalue == 'ChangeCategory'){
		$('#job_complete_'+id).remove();
		$("#job_delete_"+id).hide();
		 $.post(projectjs_webroot+"/act_form_action.cfm", {actionpage:'jobs/jquery/jquery_projectpageeditor.cfm',JobId:id,type:selectvalue, Fusesubaction:fusesubaction,Fuseaction:fuseaction}, function(responsedata){
  			if(responsedata !=""){
				$('#prj_row_'+id).append(responsedata);	 
				$('#job_move_'+id).slideDown();
			}
		 });			
		}
		else if(selectvalue == 'Complete'){
		$('#job_move_'+id).remove();
		$("#job_delete_"+id).hide();
		 $.post(projectjs_webroot+"/act_form_action.cfm", {actionpage:'jobs/jquery/jquery_projectpageeditor.cfm',JobId:id,type:selectvalue, Fusesubaction:fusesubaction,Fuseaction:fuseaction}, function(responsedata){
  			if(responsedata !=""){
				$('#prj_row_'+id).append(responsedata);	 
				$('#job_complete_'+id).slideDown();
			}
		 });	
		}
		else if(selectvalue == 'basic'){
fdbackwindow=window.open(webroot+'/act_url_action_normal.cfm?actionpage=reports/dsp_report_popup.cfm&reportID=1&jobID='+id+'','report_popup','width=850,height=600,top=10,left=10,resizable=1,menubar=1,status=0,scrollbars=1,panel=0,addressbar=0');		
		}
		else if(selectvalue == 'detailed'){
fdbackwindow=window.open(webroot+'/act_url_action_normal.cfm?actionpage=reports/dsp_report_popup.cfm&reportID=2&jobID='+id+'','report_popup','width=850,height=600,top=10,left=10,resizable=1,menubar=1,status=0,scrollbars=1,panel=0,addressbar=0');			
		}
		else if(selectvalue == 'tasks'){
		 $.post(projectjs_webroot+"/act_form_action.cfm", {actionpage:'jobs/jquery/jquery_projectpageeditor.cfm',JobId:id,type:selectvalue, Fusesubaction:fusesubaction,Fuseaction:fuseaction}, function(responsedata){
  			if(responsedata !=""){
fdbackwindow=window.open(webroot+'/act_url_action_normal.cfm?actionpage=jobs/dsp_jobtasks_printview.cfm&tasks='+responsedata+'&jobID='+id+'','report_popup','width=850,height=600,top=10,left=10,resizable=1,menubar=1,status=0,scrollbars=1,panel=0,addressbar=0');	
			}
			else{
				alert('There are no Tasks for this '+projectterminology);
			}
		 });			
		}
		document.getElementById('tools_'+id).selectedIndex = 0; 						
	}
}

function CompleteProject(id, method){
	$('#job_complete_'+id).slideUp(function(){
		$('#job_complete_'+id).remove();
	});
	if(method == 'yes'){
	 $.post(projectjs_webroot+"/act_form_action.cfm", {actionpage:'jobs/jquery/jquery_updatededitinplace_nrml.cfm',JobComplete:'1',type:'rightjobstatus',JobID:id, Fusesubaction:fusesubaction,Fuseaction:fuseaction}, function(responsedata){
	  	if(responsedata != ""){
		   $("#prj_row_"+id + "> div").css({backgroundColor: '#A1DB00'});
		   $("#prj_row_"+id + "> div > div").css({backgroundColor: '#A1DB00'});
		   $("#job_delete_"+id+ "> div").css({backgroundColor: '#A1DB00'});
		   $("#prj_row_"+id + "> div").animate({backgroundColor: '##F5F5F5'}, 1000,function(data){$("#prj_row_"+id).slideToggle('slow', function(data){$("#prj_row_"+id).remove();});});
		   $("#prj_row_"+id + "> div > div").animate({backgroundColor: '#F5F5F5'}, 1000,function(){
			   $('#prj_row_'+id).remove();
		   });			
		}
	 }); 	
	}	
}


function DeleteProject(id){
 $.post(projectjs_webroot+"/act_form_action.cfm", {actionpage:'jobs/jquery/jquery_job_delete.cfm',JobID:id, Fusesubaction:fusesubaction,Fuseaction:fuseaction}, function(responsedata){
  if(responsedata == 1){
   $("#job_delete_"+id).remove();    
   $("#prj_row_"+id + "> div").css({backgroundColor: '#A1DB00'});
   $("#prj_row_"+id + "> div > div").css({backgroundColor: '#A1DB00'});
   $("#job_delete_"+id+ "> div").css({backgroundColor: '#A1DB00'});
   $("#prj_row_"+id + "> div").animate({backgroundColor: '#F5F5F5'}, 1000,function(data){$("#prj_row_"+id).slideToggle('slow', function(data){$("#prj_row_"+id).remove();});});
   $("#prj_row_"+id + "> div > div").animate({backgroundColor: '#F5F5F5'}, 1000);
  } 
 });
}

function DoContactCheck(jobid, checktype){
	var checkvalue = 1 ,staff = "",clients = "", inlineditor="", check_method = "", dothecheck = 0, changecontact="";
	
	if(checktype=="staff"){
 	  if(account_plan > 2){
 	  	var id = document.getElementById('JobCreatedBy_'+jobid).options[document.getElementById('JobCreatedBy_'+jobid).selectedIndex].value;
	  	id  = id.substr(id.lastIndexOf("|")+1,id.length);
	  }
	  else{
	 	var id = document.getElementById('JobCreatedBy_'+jobid).options[document.getElementById('JobCreatedBy_'+jobid).selectedIndex].value;
	  }	
	  staff = id;
	  inlineditor = "jobmanager";
	  check_method = "staff";
	  dothecheck = 1;
	}
	else if(checktype=="extclient"){
	  if(document.getElementById('ChangeProjectContactsRemove').checked){
	   changecontact = 'remove';
	  }
	  else if(document.getElementById('ChangeProjectContactsNew').checked){
       for (var iCount=0;document.getElementById('ContactID_'+jobid).options[iCount]; iCount++){
          if (document.getElementById('ContactID_'+jobid).options[iCount].selected == true){
         	if(clients == "")clients =document.getElementById('ContactID_'+jobid).options[iCount].value;
      		else clients = clients + ','+ document.getElementById('ContactID_'+jobid).options[iCount].value;
          }
       }  	  
	   changecontact = 'new';
	  }
	  else if(document.getElementById('ChangeProjectContactsKeep').checked){
	   changecontact = 'keep';
	  }	
	  if(changecontact != "keep"){dothecheck = 1;}	  
	  inlineditor = "jobclientname";	 
	  check_method = "client";
	}
	else if(checktype=="intclient"){
	  inlineditor = "jobclientname";	 
	  check_method = "client";
	  dothecheck = 1;
      for (var iCount=0;document.getElementById('Int_ClientContactsAssigned').options[iCount]; iCount++){
        if (document.getElementById('Int_ClientContactsAssigned').options[iCount].selected == true){
       		if(clients == "")clients =document.getElementById('Int_ClientContactsAssigned').options[iCount].value;
    		else clients = clients + ','+ document.getElementById('Int_ClientContactsAssigned').options[iCount].value;
        }
      }		
	  clientid = document.getElementById('JR_Group').options[document.getElementById('JR_Group').selectedIndex].value  
	  clientid  = clientid.substr(clientid.lastIndexOf("|")+1,clientid.length); 
	  if(clients == "")clients =clientid;
	  else clients = clients + ','+ clientid;				  
	}
	
	if(dothecheck){
		$.post(projectjs_webroot+"/act_form_action.cfm", {actionpage:'jobs/jquery/jquery_editprojectcontacts.cfm',type:'inlineediting',checktype:check_method,jobid:jobid,JobAssignedTo:staff,ClientsAssigned:clients,changecontact:changecontact}, function(responsedata){
			if(responsedata != 0){
				$('#jobcontactserrormessage').show();
				 $('#inlinecancelbutton').show();
				 $('#inlineupdatelbutton').show();
				 $('#inlineloader').hide();			
			    return;		
			}
			else{
				if(checktype=="intclient"){
				UpdateInternalClients(inlineditor, jobid);
				}
				else{
				UpdateEditinLineDetails(inlineditor, jobid);
				}
			}
	 	});			
	}
	else{
		if(checktype=="intclient"){
		UpdateInternalClients(inlineditor, jobid);
		}
		else{
		UpdateEditinLineDetails(inlineditor, jobid);
		}
	}	

}

function UpdateEditinLineDetails(type, taskid){
 $('#inlinecancelbutton').hide();
 $('#inlineupdatelbutton').hide();
 $('#inlineloader').show();
 if(type == 'jobnumber'){
  $.post(projectjs_webroot+"/act_form_action.cfm", {actionpage:'jobs/jquery/jquery_updatededitinplace_nrml.cfm',JobID:taskid,Type:type,JobNumber:$("#JobNumber_"+taskid).val(),Fusesubaction:fusesubaction,Fuseaction:fuseaction,approved:'no'}, function(responsedata){
   responsedata = $.trim(responsedata.replace('<b>', ''));
   responsedata = $.trim(responsedata.replace('</b>', ''));
   if(responsedata != 0){
    $('#editwindow').slideUp('fast', function(){
     $('#editwindow').remove();
     $('#jobnumber_'+taskid).empty();
     $('#jobnumber_'+taskid).append(responsedata);
     $('#jobnumber_'+taskid).show();
    });
   }
   else{
    var answer = confirm ("This is a duplicate project number, would you like to proceed")
    if (answer){
     $.post(projectjs_webroot+"/act_form_action.cfm", {actionpage:'jobs/jquery/jquery_updatededitinplace_nrml.cfm',JobID:taskid,Type:type,JobNumber:$("#JobNumber_"+taskid).val(),Fusesubaction:fusesubaction,Fuseaction:fuseaction, approved:'yes'}, function(responsedata){
	  responsedata = $.trim(responsedata.replace('<b>', ''));
	  responsedata = $.trim(responsedata.replace('</b>', ''));
      if(responsedata != 0){
       $('#editwindow').slideUp('fast', function(){
        $('#editwindow').remove();
        $('#jobnumber_'+taskid).empty();
        $('#jobnumber_'+taskid).append(responsedata);
        $('#jobnumber_'+taskid).show();
       });
      }
     }); 
    }
    else{
     $('#editwindow').slideUp('fast')
    } 
   } 
  })
 }
 else if (type == "jobtitle"){
  $.post(projectjs_webroot+"/act_form_action.cfm", {actionpage:'jobs/jquery/jquery_updatededitinplace_nrml.cfm',JobID:taskid,Type:type,JobTitle:$("#JobTitle_"+taskid).val(),Fusesubaction:fusesubaction,Fuseaction:fuseaction}, function(data){
   data = '<b><a href="'+webroot+'/index.cfm?Fuseaction=jobs&Fusesubaction=jobdetails&Jobs_currentJobID='+taskid+'" class="textlinks">'+data+'</a></b>';	
   $('#editwindow').slideUp('fast', function(){
    $('#editwindow').remove();
    $('#jobtitle_'+taskid).empty();
    $('#jobtitle_'+taskid).append(data);
    $('#jobtitle_'+taskid).show();
   });
  });  
 }
 else if(type == "jobpriority"){
  $.post(projectjs_webroot+"/act_form_action.cfm", {actionpage:'jobs/jquery/jquery_updatededitinplace_nrml.cfm',JobID:taskid,Type:type,JobPriority:$("#JobPriority_"+taskid+" option:selected").val(),Fusesubaction:fusesubaction,Fuseaction:fuseaction}, function(responsedata){
   if(responsedata != 0){
    $('#editwindow').slideUp('fast', function(){
     $('#editwindow').remove();
     $('#jobpriority_'+taskid).empty();
     $('#jobpriority_'+taskid).append(responsedata);
     $('#jobpriority_'+taskid).show();
    });
   } 
  })  
 } 
 else if(type == "jobstartdate"){
  $.post(projectjs_webroot+"/act_form_action.cfm", {actionpage:'jobs/jquery/jquery_updatededitinplace_nrml.cfm',JobID:taskid,Type:type,StartDate:document.getElementById('StartDate_'+taskid).value,Fusesubaction:fusesubaction,Fuseaction:fuseaction}, function(responsedata){
   if(responsedata != 0){
    $('#editwindow').slideUp('fast', function(){
     $('#editwindow').remove();
     $('#jobstartdate_'+taskid).empty();
     $('#jobstartdate_'+taskid).append(responsedata);
     $('#jobstartdate_'+taskid).show();
    });
   } 
  })  
 }
 else if(type == "jobstatus"){
  $.post(projectjs_webroot+"/act_form_action.cfm", {actionpage:'jobs/jquery/jquery_updatededitinplace_nrml.cfm',JobID:taskid,Type:type,InternalStatusID:document.getElementById('InternalStatusID_'+taskid).options[document.getElementById('InternalStatusID_'+taskid).selectedIndex].value, Fusesubaction:fusesubaction,Fuseaction:fuseaction}, function(responsedata){
   if(responsedata != 0){
    $('#editwindow').slideUp('fast', function(){
     $('#editwindow').remove();
     $('#jobstatus_'+taskid).empty();
     $('#jobstatus_'+taskid).append(responsedata);
     $('#jobstatus_'+taskid).show();
    });
   } 
  })  
 }    
 else if(type == "jobcatname"){
  $.post(projectjs_webroot+"/act_form_action.cfm", {actionpage:'jobs/jquery/jquery_updatededitinplace_nrml.cfm',JobID:taskid,Type:type,CatName:document.getElementById('categorynamefield_'+taskid).value, Fusesubaction:fusesubaction,Fuseaction:fuseaction}, function(responsedata){
   if(responsedata != 0){
    $('#editwindow').slideUp('fast', function(){
     $('#editwindow').remove();
     $('#jobcatname_'+taskid).empty();
     $('#jobcatname_'+taskid).append(responsedata);
     $('#jobcatname_'+taskid).show();
    });
   } 
  })  
 } 
 else if(type == "jobduedate"){
  $.post(projectjs_webroot+"/act_form_action.cfm", {actionpage:'jobs/jquery/jquery_updatededitinplace_nrml.cfm',JobID:taskid,Type:type,DueDate:document.getElementById('DueDate_'+taskid).value, Fusesubaction:fusesubaction,Fuseaction:fuseaction}, function(responsedata){
   if(responsedata != 0){
    $('#editwindow').slideUp('fast', function(){
     $('#editwindow').remove();
     $('#jobduedate_'+taskid).empty();
     $('#jobduedate_'+taskid).append(responsedata);
     $('#jobduedate_'+taskid).show();
    });
   } 
  })  
 }
else if(type == "jobmanager"){
  $.post(projectjs_webroot+"/act_form_action.cfm", {actionpage:'jobs/jquery/jquery_updatededitinplace_nrml.cfm',JobID:taskid,Type:type,JobCreatedBy:document.getElementById('JobCreatedBy_'+taskid).options[document.getElementById('JobCreatedBy_'+taskid).selectedIndex].value, Fusesubaction:fusesubaction,Fuseaction:fuseaction}, function(responsedata){
   if(responsedata != 0){
    $('#editwindow').slideUp('fast', function(){
     $('#editwindow').remove();
     $('#jobprojectmanager_'+taskid).empty();
     $('#jobprojectmanager_'+taskid).append(responsedata);
     $('#jobprojectmanager_'+taskid).show();
    });
   } 
  })  
 }   
 else if(type == "customfields"){
  $.post(projectjs_webroot+"/act_form_action.cfm", {actionpage:'jobs/jquery/jquery_updatededitinplace_nrml.cfm',JobID:taskid,Type:type,JobCreatedBy:document.getElementById('JobCreatedBy_'+taskid).options[document.getElementById('JobCreatedBy_'+taskid).selectedIndex].value, Fusesubaction:fusesubaction,Fuseaction:fuseaction}, function(responsedata){
   if(responsedata != 0){
    $('#editwindow').slideUp('fast', function(){
     $('#editwindow').remove();
     $('#jobprojectmanager_'+taskid).empty();
     $('#jobprojectmanager_'+taskid).append(responsedata);
     $('#jobprojectmanager_'+taskid).show();
    });
   } 
  })  
 }   
 else if(type == "jobclientname"){
	  var changecontact = ""; 
	  if(document.getElementById('ChangeProjectContactsRemove').checked){
	   changecontact = document.getElementById('ChangeProjectContactsRemove').checked
	  }
	  else if(document.getElementById('ChangeProjectContactsNew').checked){
	   changecontact = document.getElementById('ChangeProjectContactsNew').checked;
	  }
	 $.post(projectjs_webroot+"/act_form_action.cfm", {actionpage:'jobs/jquery/jquery_updatededitinplace_nrml.cfm',JobID:taskid,Type:type,JobClientID:document.getElementById('JobClientID_'+taskid).options[document.getElementById('JobClientID_'+taskid).selectedIndex].value,Method:changecontact,Fusesubaction:fusesubaction,Fuseaction:fuseaction}, function(responsedata){
	   if(responsedata != 0){
	    $('#editwindow').slideUp('fast', function(){
	     $('#editwindow').remove();
	     $('#jobclientname_'+taskid).empty();
	     $('#jobclientname_'+taskid).append(responsedata);
	     $('#jobclientname_'+taskid).show();
	    });
	    if(changecontact=='remove'){
	    $.post(projectjs_webroot+"/act_form_action.cfm", {actionpage:'jobs/jquery/jquery_updatededitinplace_nrml.cfm',JobID:taskid,Type:'jobcontact',ContactID:'', Fusesubaction:fusesubaction,Fuseaction:fuseaction}, function(responsedata){
	      if(responsedata != 0){
	       $('#projectcontacts').hide();
	       $('#projectcontacts').empty();
	       $('#projectcontacts').append(responsedata);
	       $('#projectcontacts').show();
	      } 
	     })
	    }
	    else if(changecontact =='new'){
	     var selectedClientNames = "";
	        for (var iCount=0;document.getElementById('ContactID_'+taskid).options[iCount]; iCount++){
	           if (document.getElementById('ContactID_'+taskid).options[iCount].selected == true){
	          	if(selectedClientNames == "")selectedClientNames =document.getElementById('ContactID_'+taskid).options[iCount].value;
	       		else selectedClientNames = selectedClientNames + ','+ document.getElementById('ContactID_'+taskid).options[iCount].value;
	           }
	        }  
	     $.post(projectjs_webroot+"/act_form_action.cfm", {actionpage:'jobs/jquery/jquery_updatededitinplace_nrml.cfm',JobID:taskid,Type:'jobcontact',ContactID:selectedClientNames, Fusesubaction:fusesubaction,Fuseaction:fuseaction}, function(responsedata){
	      if(responsedata != 0){
	        $('#projectcontacts').hide();
	        $('#projectcontacts').empty();
	        $('#projectcontacts').append(responsedata);
	        $('#projectcontacts').show();
	      } 
	     })     
	    }       
	   } 
	  });  
 }
}  


function UpdateInternalClients(type,jobid){
 var JR_Division = document.getElementById('JR_Division').options[document.getElementById('JR_Division').selectedIndex].value;
 var JR_Team = document.getElementById('JR_Team').options[document.getElementById('JR_Team').selectedIndex].value;
 var JR_Group = document.getElementById('JR_Group').options[document.getElementById('JR_Group').selectedIndex].value;
 var Int_ClientContactsAssigned = "";
   for (var iCount=0;document.getElementById('Int_ClientContactsAssigned').options[iCount]; iCount++){
      if (document.getElementById('Int_ClientContactsAssigned').options[iCount].selected == true){
     	if(Int_ClientContactsAssigned == "")Int_ClientContactsAssigned =document.getElementById('Int_ClientContactsAssigned').options[iCount].value;
  		else Int_ClientContactsAssigned = Int_ClientContactsAssigned + ','+ document.getElementById('Int_ClientContactsAssigned').options[iCount].value;
      }
   }  
  
  if(JR_Group != "0"){
	 $('#inlinecancelbutton').hide();
	 $('#inlineupdatelbutton').hide();
	 $('#inlineloader').show();  
	  $.post(projectjs_webroot+"/act_form_action.cfm", {actionpage:'jobs/jquery/jquery_updatededitinplace_nrml.cfm',JobClientID:'0',JR_Division:JR_Division,JR_Team:JR_Team,JR_Group:JR_Group,Int_ClientContactsAssigned:Int_ClientContactsAssigned,JobID:jobid,Fusesubaction:fusesubaction,Fuseaction:fuseaction,Type:type},  function(responsedata){
	   if(responsedata != 0){
	    $('#editwindow').slideUp('fast', function(){
	     $('#editwindow').remove();
	     $('#jobclientname_'+jobid).empty();
	     $('#jobclientname_'+jobid).append(responsedata);
	     $('#jobclientname_'+jobid).show();
	    });
	   }
	 });  
  }
  else{
   $('#nointernalperson').show();
  }  	
}


function LoadClientContacts(id){ 
 if(document.getElementById('ChangeProjectContactsNew').checked){
 $('#inlinecancelbutton').hide();
 $('#inlineupdatelbutton').hide();
 $('#inlineloader').show(); 
  $.post(projectjs_webroot+"/act_form_action.cfm", {actionpage:'jobs/jquery/jquery_editinplace.cfm',JobID:id,ClientID:document.getElementById('JobClientID_'+id).options[document.getElementById('JobClientID_'+id).selectedIndex].value, Type:'getmoreprojectcontacts'}, function(responsedata){
    $('#newprojectcontacts').empty();
    $('#newprojectcontacts').append(responsedata);
	 $('#inlinecancelbutton').show();
	 $('#inlineupdatelbutton').show();
	 $('#inlineloader').hide();	
  });  
 }
}

function ChangeProjectsView(maxrows,startrow,id,type,view){
 $('#pagging_'+id).hide();
 $('#loader_'+id).show();
  $.post(projectjs_webroot+"/act_form_action.cfm", {actionpage:'jobs/jquery/jquery_changejobpageview.cfm',param3:maxrows,param2:startrow,param4:'1',loopstatus:id,Fusesubaction:fusesubaction,Fuseaction:fuseaction}, function(responsedata){
    if(type == 'topnav'){
		$('#jobcat_'+id).slideUp(function(){
			$('#jobcat_'+id).empty();
			$('#jobcat_'+id).append(responsedata);
			$('#jobcat_'+id).slideDown();						
		});
	}
	else if(type == 'prev'){
		$('#jobcat_'+id).hide();
		$('#jobcat_'+id).empty();
		$('#jobcat_'+id).append(responsedata);				
		$('#jobcat_'+id).show("slide", { direction: "left" });	
	}
	else if(type == 'next'){
		$('#jobcat_'+id).hide();
		$('#jobcat_'+id).empty();
		$('#jobcat_'+id).append(responsedata);				
		$('#jobcat_'+id).show("slide", { direction: "right" });	
	}
	 $('#loader_'+id).hide();
	 $('#pagging_'+id).show();		
  }); 	
}

function ChangeProjectSortHeaders(JobPage_SortBy,JobPage_SortOrder,param3,param2,id){
 $('#pagging_'+id).hide();
 $('#loader_'+id).show();
 $.post(projectjs_webroot+"/act_form_action.cfm", {actionpage:'jobs/jquery/jquery_changejobpagesortheader.cfm',param3:param3,param2:param2,param4:'1',loopstatus:id,Fusesubaction:fusesubaction,Fuseaction:fuseaction,JobPage_SortBy:JobPage_SortBy,JobPage_SortOrder:JobPage_SortOrder}, function(responsedata){
	$('#jobcat_'+id).slideUp(function(){
		$('#jobcat_'+id).empty();
		$('#jobcat_'+id).append(responsedata);
		$('#jobcat_'+id).slideDown();						
	});
	 $('#loader_'+id).hide();
	 $('#pagging_'+id).show();		
  });  
}