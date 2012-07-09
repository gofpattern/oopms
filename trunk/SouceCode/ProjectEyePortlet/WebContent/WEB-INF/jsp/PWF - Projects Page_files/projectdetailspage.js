 /*
THiS IS THE UNCOMPRESSED VERSION OF THE projectdetailspage.js

FUNCTION NAMES
--------------------
preloadImages
EditinPlaceHover
EditinPlaceOut
removeEditorForElement
JobAlertsConfig
CloseEditInLine
EditinPlaceDropDown
*/

var projectdetailsjs_webroot = "";
var projectdetailsjs_shareimages= "";
var projectdetailsjs_jobid = "";
var projectdetailsjs_sharetemplates = "";
var userid = "";
var fuseaction = "";
var fusesubaction = "";		
var editinlineid = "";
var jobdescription = "";
var includetinymce = 0;
var mouseoverid = 0;
var messageicon = 0;

jQuery.preloadImages = function(){
	  for(var i = 0; i<arguments.length; i++){
	   jQuery("<img>").attr("src", arguments[i]);
	  }
}

function EditinPlaceHover(id){
	 $("#"+id).css({'backgroundColor':''});
	 $("#"+id).addClass("editinlinehover");
	 if(id == 'decriptioncontainer' || id == 'privatenotescontainer' ){
		$("#"+id).css({'border':'1px solid #d3d3d3'});
	 }
}

function EditinPlaceOut(id, bgcolor,type){
	 $("#"+id).removeClass("editinlinehover");
	 $("#"+id).css({'backgroundColor':bgcolor}); 

} 

function removeEditorForElement(id){
  if (tinyMCE.getInstanceById(id) != null)
  {
  	tinyMCE.get(id).remove();
  }
}   
	

function JobAlertsConfig(action,jobID){
	 $.post(projectdetailsjs_webroot+"/act_form_action.cfm", {actionpage:'jobs/jquery/jquery_startalerts.cfm',jobID:jobID, action: action}, function(responsedata){
	  if(responsedata != 0){
	   $('#alertsconfig').empty();
	   $('#alertsconfig').append(responsedata);
	   $("#alertsconfig").css({backgroundColor: '#A1DB00'});
	   $("#alertsconfig").animate({backgroundColor: '#eaeaea'}, 1000 );
	   if(action == '1'){
	    $('#alertsconfigimage').attr("src",projectdetailsjs_shareimages+"/icon_alertsStarted.gif");
	   }
	   else if (action == '0'){
	    $('#alertsconfigimage').attr("src",projectdetailsjs_shareimages+"/icon_alertsStopped.gif");
	   }
	  }     
	 });  
}


function CloseEditInLine(id){
	 $('#editwindow').slideUp('fast', function(){
	  $('#editwindow').remove();
	  $('#'+id).show();
	 });
	if(includetinymce){
	 if(id == 'description'){
	  removeEditorForElement('JobDescription_'+projectdetailsjs_jobid);
	 }
	 if(id == 'notes'){
	  removeEditorForElement('JobNotes_'+projectdetailsjs_jobid);
	 }
	}
}

function EditinPlaceDropDown(id, type, childid, taskid){
	 var height=0;
	 $('#editwindow_message').remove();
	 if(type=='order' || type =='priority' || type =='startdate' || type == 'duedate'){
	  height = $('#otherdetails_'+taskid).height()
	  $("#"+id).removeClass("editinlinehover");
	 } 
	 else if(type == 'allocatedtime'){ 
	  height = $('#assingeddetails_'+taskid).height()
	 }
	 if(editinlineid == id){  
	  if(type == 'taskname'){
	   if(includetinymce){removeEditorForElement('TaskDescription_'+taskid);}
	  }
	  if(type == 'jobdescription'){
	   if(includetinymce){removeEditorForElement('JobDescription_'+taskid);}
	  }
	  if(type == 'jobnotes'){
	   if(includetinymce){removeEditorForElement('JobNotes_'+taskid);}
	  }     
	  if (!(type =='order' || type == 'priority' || type == 'startdate' || type == 'duedate' || type == 'allocatedtime')){
	   $("#editwindow").remove();
	   $('#description').show();
	   $('#notes').show();    
	  }
	  editinlineid = ""; 
	 }
	 else{
	  $('#description').show();
	  $('#notes').show();    
	  $.ajax({
	    type: "POST",
	    url: projectdetailsjs_webroot+"/act_form_action.cfm",
	    data: "actionpage=jobs/jquery/jquery_editinplace.cfm&JobID="+projectdetailsjs_jobid+"&TaskID="+taskid+"&Type="+type+"&Height="+height,
	    success: function(responsedata){
	    if(responsedata != 0){  
	     $("#editwindow").remove();
	     if(childid == 'description' || childid == 'notes'){
	      $('#'+childid).hide();
	     }
	     if(type== 'jobcontact'){
	      $('#'+id).append(responsedata);
	     }
		 else if(type == 'description' || type == 'jobnotes'){
		 	if(type == 'description'){
				$('#decriptioncontainer').hide();
				$('#descriptionslidedown').append(responsedata);
			}
			if(type == 'jobnotes' ){	
				$('#privatenotescontainer').hide();
				$('#notesslidedown').append(responsedata);
			}
		 }
	     else{
	      $('#'+id).after(responsedata);
	     }
	     if(includetinymce){
	      if(type == 'taskname' || type == 'description' || type =='jobnotes'){
	       tinyMCE.init({
	        mode : "textareas",
	        theme : "advanced",
			editor_deselector : "mceNoEditor",		
	        plugins : "advlink,contextmenu,paste,safari",
	        theme_advanced_buttons1 :
	        "bold,italic,underline,separator,strikethrough,justifyleft,justifycenter,justifyright,justifyfull,separator,bullist,numlist,link,unlink",
	        theme_advanced_buttons2 : "cut,copy,pastetext,pasteword,separator,help,code,forecolor,separator,charmap,fontsizeselect",
	       theme_advanced_buttons3 : "",
	        paste_create_paragraphs : false,
	        paste_create_linebreaks : false,
	        paste_use_dialog : true,
	        paste_auto_cleanup_on_paste : true,
	        paste_convert_middot_lists : false,
	        paste_unindented_list_class : "unindentedList",
	        paste_convert_headers_to_strong : true,
	        paste_insert_word_content_callback : "convertWord",
	        theme_advanced_toolbar_location : "top",
	        theme_advanced_toolbar_align : "left",
	        theme_advanced_path_location : "bottom",
	        theme_advanced_resizing : true,
	        theme_advanced_resizing_use_cookie : false,
	        theme_advanced_resize_horizontal : false,
	        extended_valid_elements : "a[name|href|target|title|onclick],img[class|src|border=0|alt|title|hspace|vspace|width|height|align|onmouseover|onmouseout|name],hr[class|width|size|noshade],font[face|size|color|style],span[class|align|style]"
	       });
	      
	       function convertWord(type, content) {
	       switch (type) {
	       // Gets executed before the built in logic performes it's cleanups
	       case "before":
	       //content = content.toLowerCase(); // Some dummy logic
	       break;
	       // Gets executed after the built in logic performes it's cleanups
	       case "after":
	       //content = content.toLowerCase(); // Some dummy logic
	       break;
	       }
	       return content;
	       }
	      } 
		}
	    $("#editwindow").slideDown('fast', function(){
	      $("#editwindow").show();
		  document.getElementById("editwindow").style.zIndex = "1";
		  $("#"+id).removeClass("editinlinehover");
	     });
	     editinlineid = id 
	    }       
	    }
	  });  
	 }  
}




$(document).ready(function() {
	$('.textlinks').click(function(e){
	  e.stopPropagation();
	});		

	$('#AddTaskNumber').click(function(e){
	  e.stopPropagation();
	});		
   
   	$('#invoicetype').click(function(e){
	  e.stopPropagation();
	});		
   		 

	
	$('.containerheader').click(function (e){
		 e.stopPropagation();
		id= $(this).next().attr("id");
		if($('#'+id).css("display") == 'none'){
			if(id == 'decriptioncontainer'){
				$('#showdescriptionlink').hide();
				$('#hidedescriptionlink').show();
				$('#decriptioncontainer').slideDown(function(){$('#description').show();});
			}
			else if(id == 'privatenotescontainer'){
				$('#showprivatenoteslink').hide();
				$('#hideprivatenoteslink').show();
				$('#privatenotescontainer').slideDown(function(){$('#notes').show();});
			}
			else if(id == 'customformcontainer'){
				$('#showcustomformlink').hide();
				$('#hidecustomformlink').show();
				$('#customformcontainer').slideDown(function(){$('#customform').show();});
			}			
			else if(id == 'jobtaskscontainer'){
				ShowTasks();
			}
			else if(id == 'recurringtaskscontainer'){
				ShowRecurringTasks();
			}
			else if(id == 'timelinecontainer'){
				ShowTimeline();
			}
			else if(id == 'timesummarycontainer'){
				ShowTimeSummary();
			}
			else if(id == 'invoicescontainer'){
				ShowInvoices();
			}
			else if(id == 'messagescontainer'){
				ShowMessages();
			}
			else if(id == 'filescontainer'){
				ShowFiles('filelist');
			}
			else if(id == 'expensescontainer'){
				$('#expensescontainer').slideDown();
			}	
			else if(id == 'sharednotescontainer'){
				$('#sharednotescontainer').slideDown();
			}		
			else if(id == 'bookmarkscontainer'){
				$('#bookmarkscontainer').slideDown();
			}							
			else if(id == 'jobquotescontainer'){
				$('#jobquotescontainer').slideDown();
			}				
			else if(id == 'contactscontainer'){
				$('#contactscontainer').slideDown();
			}	
            else if(id == 'customfieldscontainer'){
				$('#customfieldscontainer').slideDown();
			}					
		}
		else{
			if(id == 'decriptioncontainer'){
				$('#hidedescriptionlink').hide();
				$('#showdescriptionlink').show();
				$('#decriptioncontainer').slideUp();
			}
			else if(id == 'privatenotescontainer'){
				$('#hideprivatenoteslink').hide();
				$('#showprivatenoteslink').show();
				$('#privatenotescontainer').slideUp();
			}
			else if(id == 'customformcontainer'){
				$('#hidecustomformlink').hide();
				$('#showcustomformlink').show();
				$('#customformcontainer').slideUp();
			}				
			else if(id == 'jobtaskscontainer'){
				$('#hidejobtaskslink').hide();
				$('#showjobtaskslink').show();
				$('#jobtaskscontainer').slideUp();
			}
			else if(id == 'recurringtaskscontainer'){
				$('#hiderecurringtaskslink').hide();
				$('#showrecurringtaskslink').show();
				$('#recurringtaskscontainer').slideUp();
			}
			else if(id == 'timelinecontainer'){
				$('#hidetimelineink').hide();
				$('#showtimelinelink').show();
				$('#timelinecontainer').slideUp();
			}
			else if(id == 'timesummarycontainer'){
				$('#hidetimesummarylink').hide();
				$('#showtimesummarylink').show();
				$('#timesummarycontainer').slideUp();
			}
			else if(id == 'invoicescontainer'){
				$('#hideinvoiceistlink').hide();
				$('#showinvoicelistlink').show();
				$('#invoicescontainer').slideUp();
			}
			else if(id == 'messagescontainer'){
				$('#hidemessageslink').hide();
				$('#showmessageslink').show();
				$('#messagescontainer').slideUp(function(){$('#view_messages').empty();});
			}
			else if(id == 'filescontainer'){
				$('#hidefilelistlink').hide();
				$('#showfilelistlink').show();
				$('#filescontainer').slideUp();
			}	
			else if(id == 'expensescontainer'){
				$('#expensescontainer').slideUp();
			}	
			else if(id == 'sharednotescontainer'){
				$('#sharednotescontainer').slideUp();
			}		
			else if(id == 'bookmarkscontainer'){
				$('#bookmarkscontainer').slideUp();
			}							
			else if(id == 'jobquotescontainer'){
				$('#jobquotescontainer').slideUp();
			}				
			else if(id == 'contactscontainer'){
				$('#contactscontainer').slideUp();
			}			
			else if(id == 'customfieldscontainer'){
				$('#customfieldscontainer').slideUp();
			}						
		}																									
	});
});

function iCalSubscribe(jobID){
    $.post(projectdetailsjs_webroot+"/act_form_action.cfm", {actionpage:'jobs/act_icalendar_job.cfm',jobID:jobID}, function(responsedata){
        if(responsedata != 0){
            document.getElementById('iCalLinkText').innerHTML='<a href="##" onclick="javascript:iCalBoxDisplay();" class="textlinks"><b>Subscribed</b></a>';
            document.getElementById('iCalLinkBox').style.display='block';
        }
    });  
}
function iCalUnsubscribe(jobID){
    $.post(projectdetailsjs_webroot+"/act_form_action.cfm", {actionpage:'jobs/act_iCal_unsubscribe.cfm',jobID:jobID}, function(responsedata){
        if(responsedata !=0 ){
            document.getElementById('iCalLinkText').innerHTML='<a href="##" onclick="javascript:iCalSubscribe(' + responsedata + ');" class="textlinks"><b>Subscribe</b></a>';
            document.getElementById('iCalLinkBox').style.display='none';
        }
    });  
} 

function iCalBoxDisplay(){
    document.getElementById('iCalLinkBox').style.display='block';
}