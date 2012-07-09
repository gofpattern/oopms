package openones.oopms.controller;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import openones.oopms.form.HelloForm;
import openones.oopms.form.PlannerForm;
import openones.oopms.model.OopmsTask;

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
public class PlannerController {
    /** Logger for logging. */  
    private static Logger log = Logger.getLogger(PlannerController.class);  
    
    /**
     * Create bean for form.
     * @return Form bean for UI.
     */
    @ModelAttribute("PlannerForm")
    public PlannerForm getCommandObject() {
        log.debug("getCommandObject.START");
        
        /*OopmsTask task1 = new OopmsTask(1);
        task1.setTaskname("task number 01");
        
        OopmsTask task2 = new OopmsTask(2);
        task1.setTaskname("task number 02");
        
        ArrayList<OopmsTask> taskList = new ArrayList<OopmsTask>();
        
        taskList.add(task1);
        taskList.add(task2);*/
        
        PlannerForm formBean = new PlannerForm();
        formBean.setTitle("sds");
//        formBean.setTaskList(taskList);
        return formBean;
    }
    
    
    /** 
     * Default screen. 
     * @return name of view which is the name of the JSP page. 
     */  
/*    @RequestMapping  
    public String initScreen() {  
        log.debug("initScreen.START");
        // Display TaskManager.jsp  
        return "TaskManager";  
    }*/ 
    @ActionMapping(params = "action=taskmanager")
    public void processPlanner(PlannerForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("processPlanner.START");
        
    }
    @RenderMapping(params = "action=taskmanager")
    public ModelAndView postPlanner(PlannerForm formBean, RenderRequest request) {
        log.debug("postPlanner.START");
        // request.setAttribute("user2", formBean);
        //set portlet session                    
        
        ModelAndView mav = new ModelAndView("TaskManager");
        
        mav.addObject("taskList", formBean.taskList);
        
        return mav;
    }
    @ActionMapping(params = "action=addTask")
    public void processAddTask(PlannerForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("processAddTask.START");
        
       log.debug("title=" + formBean.getTitle());
  
    }

}
