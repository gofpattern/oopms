/**
 * Licensed to Open-Ones Group under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Open-Ones Group licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package openones.oopms.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;

import openones.oopms.dao.TimesheetDao;
import openones.oopms.dao.UserDao;
import openones.oopms.form.LoginForm;
import openones.oopms.form.TimesheetForm;
import openones.oopms.model.Developer;
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
import org.springframework.web.portlet.mvc.SimpleFormController;

/**
 * @author Thach.Le
 */
@Controller
@RequestMapping("VIEW")
public class TimesheetController {
    
    Developer user = new Developer();
    /** Logger for logging. */
    private static Logger log = Logger.getLogger(LoginController.class);

 
    /**
     * Create bean for form.
     * @return Form bean for UI.
     */
    @ModelAttribute("timesheetForm")
    public TimesheetForm getCommandObject() {
        log.debug("getCommandObject.START");
        TimesheetForm formBean = new TimesheetForm();
       TimesheetDao timesheetDao = new TimesheetDao();
        Map<String,String> projectMap = new LinkedHashMap<String,String>();
        List <Project> projectList = timesheetDao.getProjectList("118124");
        projectMap.put("All", "All");
        for (int i=0; i<projectList.size();i++) {
            projectMap.put(projectList.get(i).getProjectId().toString(), projectList.get(i).getName());
        }
        formBean.setProjectMap(projectMap);
        return formBean;
    }
 
    /**
     * Process submitted form by clicking "Login" button.
     * @param formBean bean captures input data
     * @param result result of binding data
     * @param status status of session
     * @param response response of action
     */
    @ActionMapping(params = "action=timesheet")
    public void processTimesheet(TimesheetForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
      
        response.setRenderParameter("action", "timesheet");    
      
    }
    
    @ActionMapping(params = "action=searchTimesheet")
    public void processSearchTimesheet(TimesheetForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
        //TimesheetDao timesheetDao = new TimesheetDao();
        
        response.setRenderParameter("action", "timesheet");    
      
    }

    /**
     * Process after the action "login" (method "processLogin") is executed.
     * @return view "ViewDefectList" which next page "ViewDefectList.jsp" will displayed
     */
    @RenderMapping(params = "action=timesheet")
    public ModelAndView postTimesheet(TimesheetForm formBean, RenderRequest request) {
        log.debug("postLogin.START");
        // request.setAttribute("user2", formBean);
        ModelAndView mav = new ModelAndView("Timesheet"); // display ViewDefectList.jsp
                  mav.addObject("projectMap", formBean.getProjectMap());
                  mav.addObject("projectDefault", formBean.getProjectDefault());   
                          
        return mav;
    }
}
