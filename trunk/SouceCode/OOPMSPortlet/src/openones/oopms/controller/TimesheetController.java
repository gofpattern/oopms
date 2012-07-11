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

import java.util.ArrayList;
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
import openones.oopms.model.Process;
import openones.oopms.model.Project;
import openones.oopms.model.Timesheet;
import openones.oopms.model.Typeofwork;
import openones.oopms.model.Workproduct;
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
    
   
   
    /** Logger for logging. */
    private static Logger log = Logger.getLogger(TimesheetController.class);

    private static List<Process> processList = new ArrayList<Process>();
    private static List<Typeofwork> towList = new ArrayList<Typeofwork>();
    private static List<Workproduct> workProductList = new ArrayList<Workproduct>();
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
        if (result.hasErrors()) {
            log.debug("search timesheet error");
        }
        //TimesheetDao timesheetDao = new TimesheetDao();
        log.debug("Timesheet Form value :" + formBean.getProjectDefault()+" status:" + formBean.getStatus());
        response.setRenderParameter("action", "searchTimesheet");    
      
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
                  formBean.setProjectDefault("All");
                  mav.addObject("projectDefault", formBean.getProjectDefault());   
                  TimesheetDao timeDao = new TimesheetDao();
                  List<Timesheet> timesheetList = timeDao.getTimesheetList("118124", "All", "", "", "");
                   processList = timeDao.getProcessList();
                   towList = timeDao.getTOWList();
                   workProductList = timeDao.getWorkProductList();
                  int code = 0;
                   for(int i=0;i<timesheetList.size();i++) {
                       code = Integer.parseInt(timesheetList.get(i).getProcessId().toString());
                       timesheetList.get(i).setProcessName(processList.get(code-1).getName());
                       timesheetList.get(i).setTowName(towList.get(code-1).getName());
                       timesheetList.get(i).setWorkProductName(workProductList.get(code-1).getName());
                   }
                  mav.addObject("timesheetList", timesheetList);                          
        return mav;
    }
    @RenderMapping(params = "action=searchTimesheet")
    public ModelAndView postSearchTimesheet(TimesheetForm formBean, RenderRequest request) {
        log.debug("postSearchTimesheet.START");
        // request.setAttribute("user2", formBean);
        ModelAndView mav = new ModelAndView("login"); // display ViewDefectList.jsp
        mav.addObject("projectMap", formBean.getProjectMap());
        mav.addObject("projectDefault", formBean.getProjectDefault());   
        TimesheetDao timeDao = new TimesheetDao();
        List<Timesheet> timesheetList = timeDao.getTimesheetList("118124", "All", "", "", "");
         processList = timeDao.getProcessList();
         towList = timeDao.getTOWList();
         workProductList = timeDao.getWorkProductList();
        int code = 0;
         for(int i=0;i<timesheetList.size();i++) {
             code = Integer.parseInt(timesheetList.get(i).getProcessId().toString());
             timesheetList.get(i).setProcessName(processList.get(code-1).getName());
             timesheetList.get(i).setTowName(towList.get(code-1).getName());
             timesheetList.get(i).setWorkProductName(workProductList.get(code-1).getName());
         }
        mav.addObject("timesheetList", timesheetList);                          
                          
        return mav;
    }
}
