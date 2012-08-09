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
package openones.oopms.dms.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;

import openones.oopms.dms.dao.BaseDao;
import openones.oopms.dms.dao.DefectDao;
import openones.oopms.daocommon.DefectListDao;
import openones.oopms.daocommon.DefectPriorityDao;
import openones.oopms.daocommon.DefectSeverityDao;
import openones.oopms.daocommon.DefectStatusDao;
import openones.oopms.daocommon.DefectTypeDao;
import openones.oopms.daocommon.ProcessDao;
import openones.oopms.daocommon.WorkProductDao;
import openones.oopms.dms.form.ViewDefectListForm;
import openones.oopms.entity.Defect;
import openones.oopms.entity.DefectPriority;
import openones.oopms.entity.DefectSeverity;
import openones.oopms.entity.DefectStatus;
import openones.oopms.entity.DefectType;
import openones.oopms.entity.Project;
import openones.oopms.entity.Workproduct;
import openones.oopms.form.UserInfo;
import openones.oopms.portlet.controller.BaseController;
import openones.oopms.entity.Developer;
import openones.oopms.dms.dao.UserDao;
import openones.oopms.dms.form.LoginForm;


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
@RequestMapping(value = "VIEW")
public class ViewDefectListController extends BaseController {
    
    // User Dao
    UserDao userDao;
    // Map project
    Map<String, String> projectMap;
    // Map member
    Map<String, String> memberMap;
    // Map Process
    Map<String, String> statusMap;
    // Map Work Product
    Map<String, String> severityMap;
    // Map Work Product
    Map<String, String> priorityMap;
    // Map Work Product
    Map<String, String> typeMap;
    // Map Work Product
    Map<String, String> originMap;
    // Map Work Product
    Map<String, String> workProductMap;
    // List work product of timesheet
    private static List<Project> projectList = new ArrayList<Project>();
    // List Developer
    private static List<Developer> developerList = new ArrayList<Developer>();
    // List defstatusList
    private static List<DefectStatus> defStatusList = new ArrayList<DefectStatus>();
    // List defSeverityList
    private static List<DefectSeverity> defSeverityList = new ArrayList<DefectSeverity>();
    // List defSeverityList
    private static List<DefectPriority> defPriorityList = new ArrayList<DefectPriority>();
    // List defSeverityList
    private static List<DefectType> defTypeList = new ArrayList<DefectType>();
    // List defSeverityList
    private static ArrayList<openones.oopms.entity.Process> defOriginList = new ArrayList<openones.oopms.entity.Process>();
 // List defSeverityList
    private static List<Workproduct> defWorkProductList = new ArrayList<Workproduct>();
    // Developer object
    private Developer user = new Developer();
    // Role
    String role;
    /**
     * Create bean for form.
     * @return Form bean for UI.
     */
    @ModelAttribute("viewDefectList")
    public ViewDefectListForm getCommandObject() {
        log.debug("getCommandObject.START");
        ViewDefectListForm formBean = new ViewDefectListForm();
        return formBean;
    }
    
    @RenderMapping(params = "action=goViewDefectMode")
    public ModelAndView goViewDefectMode(RenderRequest request, PortletSession session) {
        log.debug("goViewDefectMode.START");

        ModelAndView mav = new ModelAndView("ViewDefectMode"); // display ViewDefectMode.jsp
        
        return mav;
    }
    
    /**
     * Process render event "ViewDefectList".
     * Param kindOfDefect: All, Open, Leakage
     * @return view "ViewDefectList" which next page "ViewDefectList.jsp" will displayed
     */
    @RenderMapping(params = "action=goViewDefectList2")
    public ModelAndView processViewDefectList(RenderRequest request, PortletSession session) {
        log.debug("processViewDefectList.START");

        ModelAndView mav = new ModelAndView("ViewDefectList2"); // display ViewDefectList2.jsp
        String kindOfDefect = request.getParameter("kindOfDefect");
        log.info("kindOfDefect=" + kindOfDefect);
        ViewDefectListForm viewDefectList = new ViewDefectListForm();
        // Sample data
        
        // Set projects that logon user is joining
        UserInfo userInfo = getUserInfo(session);
//        DMSWorkspace dmsWsp = DMSWorkspace.getDefaultWorkspace(userInfo.getUsername());
//        
//        viewDefectList.addProject(dmsWsp.getProjects());
        

        mav.addObject("viewDefectList", viewDefectList);
        return mav;
    }
    
/*    @ActionMapping(params = "action=init")
    public void processLogin(LoginForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
        System.out.println("processLogin.START");
        System.out.println("username=" + formBean.getUsername());
        // session.setAttribute("user", formBean);
        
        userDao = new UserDao();
        UserInfo userinfo = getUserInfo(session);
        user = userDao.authenticate(formBean.getUsername(), formBean.getPassword());

        if (user != null) {
            // Prepare parameter to render phase
            response.setRenderParameter("action", "init");
        } else {
            result.rejectValue("username", "error");
            log.error("Error in binding result:" + result.getErrorCount());
        }
    }
*/
    
    /**
     * Process after the action "login" (method "processLogin") is executed.
     * 
     * @return view "ViewDefectList" which next page "ViewDefectList.jsp" will displayed
     */
    @RenderMapping(params = "action=init")
    public ModelAndView postLogin(ViewDefectListForm formBean, RenderRequest request) {
        String projectId = request.getParameter("projectId");
        UserInfo userinfo = getUserInfo(request.getPortletSession());
        user = userDao.authenticate(userinfo.getUsername(), "");
        System.out.println("postLogin.START");
        // Declare view for this render
        ModelAndView mav = new ModelAndView("ViewDefectList"); // display Timesheet.jsp
     
       DefectListDao defectDao = new DefectListDao();
        projectMap = new LinkedHashMap<String, String>();
        memberMap = new LinkedHashMap<String, String>();
        severityMap = new LinkedHashMap<String, String>();
        statusMap = new LinkedHashMap<String, String>();
        workProductMap = new LinkedHashMap<String, String>();
        priorityMap = new LinkedHashMap<String, String>();
        originMap = new LinkedHashMap<String, String>();
        typeMap = new LinkedHashMap<String, String>();
        // Get project List from database
        BaseDao baseDao = new BaseDao();
        DefectStatusDao defStatusDao = new DefectStatusDao();
        DefectTypeDao defTypeDao = new DefectTypeDao();
        DefectSeverityDao defSeverityDao = new DefectSeverityDao();
        DefectPriorityDao defPriorDao = new DefectPriorityDao();
        ProcessDao processDao = new ProcessDao();
        WorkProductDao wpDao = new WorkProductDao();
        DefectDao defDao = new DefectDao();
        projectList = baseDao.getProjectList(String.valueOf(user.getDeveloperId()));   
        for (int i = 0; i < projectList.size(); i++) {
            projectMap.put(projectList.get(i).getProjectId().toString(), projectList.get(i).getName());
        }
        defStatusList = defStatusDao.getDefectStatus();
        for (int i = 0; i < defStatusList.size(); i++) {
            statusMap.put(defStatusList.get(i).getDsId().toString(), defStatusList.get(i).getName());
        }
        defPriorityList = defPriorDao.getDefectPriority();
        for (int i = 0; i < defPriorityList.size(); i++) {
            priorityMap.put(String.valueOf(defPriorityList.get(i).getDpId()), defPriorityList.get(i).getName());
        }
        defOriginList = processDao.getProcess();
        for (int i = 0; i < defOriginList.size(); i++) {
            originMap.put(String.valueOf(defOriginList.get(i).getProcessId()), defOriginList.get(i).getName());
        }
        defTypeList =  defTypeDao.getDefectType();
        for (int i = 0; i < defTypeList.size(); i++) {
            typeMap.put(String.valueOf(defTypeList.get(i).getDtId()), defTypeList.get(i).getName());
        }
        defWorkProductList = wpDao.getWorkproduct();
        for (int i = 0; i < defWorkProductList.size(); i++) {
            workProductMap.put(String.valueOf(defWorkProductList.get(i).getWpId()), defWorkProductList.get(i).getName());
        }
        developerList = defDao.getMemberList(projectId);
        for (int i = 0; i < developerList.size(); i++) {
            memberMap.put(String.valueOf(developerList.get(i).getDeveloperId()), developerList.get(i).getName());
        }
        role = baseDao.getRole(user.getDeveloperId().toString(), projectList.get(0).getProjectId().toString());
        System.out.println("ROLE : " + role);
      
       
        // Set project list to form
        mav.addObject("projectMap", projectMap);
        mav.addObject("statusMap", statusMap);
        mav.addObject("priorityMap", priorityMap);
        mav.addObject("originMap", originMap);
        mav.addObject("typeMap", typeMap);
        mav.addObject("workProductMap", workProductMap);
        mav.addObject("memberMap", memberMap);
        

        // Set information of user to session
        PortletSession session = request.getPortletSession();
        session.setAttribute("USERID", user.getDeveloperId(), PortletSession.APPLICATION_SCOPE);
        session.setAttribute("USER", user.getAccount(), PortletSession.APPLICATION_SCOPE);
        session.setAttribute("ROLE",role, PortletSession.APPLICATION_SCOPE);       
        mav.addObject("ROLE",role);
        // Search all timesheet record from database
      
        List<Defect> defectList = new ArrayList<Defect>();
        defectList = defDao.getDefectList("","","118385","","","","","","","","","");

        // Get dropdown list from database
        
      //  defectList = prepareTimesheetList(defectList);

       
        // Set default value for timesheet.jsp
//       
//                      
//        mav.addObject("projectStatus", projectStatus);
//        mav.addObject("fromDate", fromDate);
//        mav.addObject("toDate", toDate);
//        // Add object timesheetList to request
        mav.addObject("defectList", defectList);
        // Return to jsp
        return mav;
    }


}
