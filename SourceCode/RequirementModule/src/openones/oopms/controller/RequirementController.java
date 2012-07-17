package openones.oopms.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;

import openones.oopms.dao.RequirementDAO;
import openones.oopms.form.RequirementForm;
import openones.oopms.model.Requirements;
import openones.oopms.model.Project;
import openones.portlet.PortletSupport;

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
    private List<Requirements> requirementList;
    private List<Project> projectList;
    /** 
     * Default screen. 
     * @return name of view which is the name of the JSP page. 
     */  
    @RequestMapping 
    public String initScreen(RenderRequest request) {  
        log.debug("initScreen.START"); 
        // Display RequirementHome.jsp  
        PortletSupport portletSupport = new PortletSupport(request);
        return "RequirementHome";  
    } 
    
    
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
    
    
    @ActionMapping(params = "action=requirementmanager")
    public void processRequirement(RequirementForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("processRequirement.START");
        
    }
    @RenderMapping(params = "action=requirementmanager")
    public ModelAndView postRequirement(RequirementForm formBean, RenderRequest request) {
        log.debug("postRequirementSTART");
        // request.setAttribute("user2", formBean);
        //set portlet session                    
        
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
        
        formBean.setRequirementList(requirementList);                
        mav.addObject("requirementList", formBean.getRequirementList());
        return mav;
    }
         

}
