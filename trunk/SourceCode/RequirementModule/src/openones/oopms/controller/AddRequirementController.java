package openones.oopms.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;

import openones.oopms.dao.RequirementDAO;
import openones.oopms.form.RequirementAddForm;
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
public class AddRequirementController {
    /** Logger for logging. */  
    private static Logger log = Logger.getLogger(AddRequirementController.class);      
    private List<Project> projectList;
                
    /**
     * Create bean for form.
     * @return Form bean for UI.
     */
    @ModelAttribute("RequirementForm")
    public RequirementForm getCommandObject(RenderRequest request) {
        log.debug("RequirementAddForm.START");                       
               
        RequirementForm formBean = new RequirementForm();
        formBean.setTitle("RequirementAddForm");                                          
        return formBean;
    }
    
    @RenderMapping(params = "action=sort")
    public  ModelAndView postRequirementSort(RequirementForm formBean, RenderRequest request) {
        log.debug("postRequirementSortSTART");                                 
        ModelAndView mav = new ModelAndView("RequirementAdd");                
        return mav;
    }
    
    @ActionMapping(params = "action=sortAction")
    public void processSortAction(RequirementAddForm formBean, BindingResult result, SessionStatus status, ActionResponse response, PortletSession session) {
        log.debug("processSortActionSTART");
        log.debug("Sort requirement button ");
    }
    
    @ActionMapping(params = "action=goAddNewRequirementAction")
    public void processgoAddNewRequirementAction(RequirementForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("goAddNewRequirementActionSTART");
        log.debug("goAddNewRequirementAction Test");
        System.out.println("testgoAddNewRequirementAction");
        response.setRenderParameter("action", "goAddNewRequirement");
    }
    
    
    @RenderMapping(params = "action=goAddNewRequirement")
    public ModelAndView postRequirementAdd(RequirementForm formBean, RenderRequest request) {
        log.debug("goAddNewRequirementSTART");                         
        
        ModelAndView mav = new ModelAndView("RequirementAdd");  
        
        //Get project name list
        RequirementDAO requirementDAO = new RequirementDAO();           
        projectList = requirementDAO.getAllProject();  
        log.debug("projectlist after:" +projectList.size());
        
        Map<String,String> projectMap = new LinkedHashMap<String,String>();
        log.debug("projectmap before:" +projectMap.size());
        projectMap.put("All", "All");
        for (int i=0; i<projectList.size();i++) {
            projectMap.put(projectList.get(i).getProjectId().toString(), projectList.get(i).getName());
        }
        log.debug("projectmap after:" +projectMap.size());
        
        formBean.setProjectMap(projectMap);
        formBean.setProjectDefault("All"); 
        mav.addObject("projectDefault", formBean.getProjectDefault());
        mav.addObject("projectMap", formBean.getProjectMap());
        return mav;
    }
    
    
    /**
     * Process submitted form by clicking "Add" button.
     * @param formBean bean captures input data
     * @param result result of binding data
     * @param status status of session
     * @param response response of action
     */
    @ActionMapping(params = "action=save")
    public void processSave(RequirementAddForm formBean, BindingResult result, SessionStatus status, ActionResponse response, PortletSession session) {
        log.debug("processLogin.START");
        log.debug("Save requirement button ");
    }
    

}
