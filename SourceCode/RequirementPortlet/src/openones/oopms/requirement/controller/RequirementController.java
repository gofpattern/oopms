package openones.oopms.requirement.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import openones.oopms.requirement.dao.RequirementDAO;
import openones.oopms.requirement.form.RequirementForm;
import openones.oopms.requirement.model.Project;
import openones.oopms.requirement.model.Requirements;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;


@Controller
@RequestMapping("VIEW")
public class RequirementController {
    /** Logger for logging. */  
    private static Logger log = Logger.getLogger(RequirementController.class);  
    //requirementList to add into render object 
    private List<Requirements> requirementList;
    //deleteRequirementList to delete more than one requirement once
    private List<Requirements> deleteRequirementList;
    //projectList to load into combobox
    private List<Project> projectList;   
    // RequirementController ErrorString requirementError
    String requirementError="";
    
    /**
     * Create bean for form.
     * @return Form bean for UI.
     */
    @ModelAttribute("RequirementForm")
    public RequirementForm getCommandObject(RenderRequest request) {
        log.debug("getCommandObject.START");                                      
        RequirementForm formBean = new RequirementForm();
        formBean.setTitle("Requirement Form");                                          
        return formBean;
    }
    
    //not in use
    @ActionMapping(params = "action=requirementmanager")
    public void processRequirement(RequirementForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("processRequirement.START");        
    }
    
    @RenderMapping(params = "action=requirementmanager")
    public ModelAndView postRequirement(RequirementForm formBean, RenderRequest request) {
        log.debug("postRequirementSTART");                           
        
        ModelAndView mav = new ModelAndView("RequirementHome");
        RequirementDAO requirementDAO = new RequirementDAO();        
        requirementList = requirementDAO.getAllRequirement();
        projectList = requirementDAO.getAllProject();            
        
        //get projectName
        log.debug("projectNameSTART");
        try {
            for (int i = 0; i < requirementList.size(); i++) {
                for (int j = 0; j < projectList.size(); j++) {
                    if (requirementList.get(i).getProjectID().equals(projectList.get(j).getProjectId())) {
                        requirementList.get(i).setProjectName(projectList.get(j).getName());
                    }
                }
            }
        } catch (Exception ex) {
            // TODO: handle exception
            log.debug(requirementList.get(0).getProjectID());
            log.debug(projectList.get(0).getProjectId());
            log.debug(projectList.get(0).getName());
            log.debug(requirementList.get(0).getProjectName());
            log.error("Convert ProcessID to string", ex);
        }
        
        // thieu buo'c set req id vao req list de hien thi len man hinh 
        
        formBean.setRequirementList(requirementList);                
        mav.addObject("requirementList", formBean.getRequirementList());
        
      //set projectMap        
        Map<String,String> projectMap = new LinkedHashMap<String,String>();
        log.debug("projectmap before:" +projectMap.size());
        //projectMap.put("All", "All");
        for (int i=0; i<projectList.size();i++) {
            projectMap.put(projectList.get(i).getProjectId().toString(), projectList.get(i).getName());
        }
        log.debug("projectmap after:" +projectMap.size());
        
        formBean.setProjectMap(projectMap);
        formBean.setProjectDefault(""); 
        mav.addObject("projectDefault", formBean.getProjectDefault());
        mav.addObject("projectMap", formBean.getProjectMap());
        
        return mav;
    }
              
    @RenderMapping(params = "action=deleteRequirement")   
    public ModelAndView postDeleteRequirement(RequirementForm formBean, RenderRequest request) {
        log.debug("deleteRequirementHere");
        ModelAndView mav = new ModelAndView("hello");
                  
        List<Requirements> tempList;
        tempList = formBean.getRequirementList();
        if(tempList == null) {
            log.debug("ListdeleteRequirementHereNULL");
        }
        else {
            log.debug("ListdeleteRequirementHere: "+tempList.size());
//            for (int i = 0; i < tempList.size(); i++) {
//                    log.debug("ListdeleteRequirementHereContentgetRequirementName" +tempList.get(i).getRequirement());
//                    log.debug("ListdeleteRequirementHereContentgetRequirementID" +tempList.get(i).getRequirementID());                    
//                }
            deleteRequirementList = new ArrayList<Requirements>(tempList.size());             
            requirementError = "";
            
        for (int i = 0; i < tempList.size(); i++) {
            if (tempList.get(i).getRequirementID() != null) {
                deleteRequirementList.add(tempList.get(i));
            }
        }        
        log.debug("tobedeletedList.deleteRequirementList: "+deleteRequirementList.size());
        RequirementDAO reqDao = new RequirementDAO();
        try {
            reqDao.deleteReq(deleteRequirementList);
        } catch (ParseException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }        
        }
        
        return mav;

    }
      

}
