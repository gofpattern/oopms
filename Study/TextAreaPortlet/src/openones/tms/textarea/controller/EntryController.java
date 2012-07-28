package openones.tms.textarea.controller;

import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import openones.tms.textarea.form.EditForm;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 * @author Thach.Le
 */
@Controller
@RequestMapping("VIEW")
public class EntryController {
    /** Logger for logging. */
    private static Logger log = Logger.getLogger(EntryController.class);

    /**
     * Default screen.
     * @return name of view which is the name of the JSP page.
     */
    @RequestMapping
    public String initScreen() {
        log.debug("initScreen.START");
        return "Edit";
    }

    @ModelAttribute("EditForm")
    public EditForm getCommandObject() {
        log.debug("getCommandObject.START");

        EditForm formBean = new EditForm();
        // init value
        // formBean.setName("init Name");
        // formBean.setDescription("init Decsription");
        // log.debug("getNameFormClient" + formBean.getName());
        // log.debug("getDescriptionFormClient" + formBean.getDescription());
        return formBean;
    }

    @ActionMapping(params = "action=edit")
    public void processPlanner(EditForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("processPlanner.START");
        log.debug("getNameFormClient" + formBean.getName());
        log.debug("getDescriptionFormClient" + formBean.getDescription());
        response.setRenderParameter("action", "edit");
    }

    @RenderMapping(params = "action=edit")
    public ModelAndView postPlannerAdd(EditForm formBean, RenderRequest request) {
        log.debug("postPlannerAdd.START");
        ModelAndView mav = new ModelAndView("Edit");
        
        // set hard value 
        // formBean.setName("default Name");
        // formBean.setDescription("default Description");
        
        // get value form formBean
        mav.addObject("name", formBean.getName());
        mav.addObject("description", formBean.getDescription());

        log.debug("postPlannerAdd.END");
        return mav;
    }

}