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
package openones.oopms.dashboard.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;

import openones.oopms.daocommon.AssignmentDao;
import openones.oopms.daocommon.DeveloperDao;
import openones.oopms.daocommon.GeneralReferenceDao;
import openones.oopms.dashboard.form.DashboardForm;
import openones.oopms.dashboard.model.Dashboard;
import openones.oopms.entity.Developer;
import openones.oopms.entity.GeneralReference;
import openones.oopms.entity.Project;
import openones.oopms.form.UserInfo;
import openones.oopms.portlet.controller.BaseController;
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

/**
 * @author PNTG
 */
@Controller
@RequestMapping("VIEW")
public class DashboardController extends BaseController {
    private static Logger log = Logger.getLogger(DashboardController.class);
    private List<Project> projectList;
    private List<Dashboard> dashboardList = new ArrayList<Dashboard>();
    private List<GeneralReference> statusList;
    private List<GeneralReference> typeList;
    private List<GeneralReference> categoryList;
    private Map<String, String> typeMap;
    private Map<String, String> statusMap;
    private Map<String, String> categoryMap;
    private Map<String, String> projectHealthMap;
    private AssignmentDao assignmentDao;
    private DeveloperDao developerDao;
    private GeneralReferenceDao generalReferenceDao;
    private Dashboard dashboard;

    @ModelAttribute("DashboardForm")
    public DashboardForm getCommandObject() {
        log.debug("getCommandObject.START");
        DashboardForm formBean = new DashboardForm();
        return formBean;
    }
    /**
     * Default screen.
     * @return name of view which is the name of the JSP page.
     */
    @RequestMapping
    public ModelAndView initScreen(RenderRequest request, PortletSession session, DashboardForm formBean) {
        log.debug("initScreen.START");
        ModelAndView mav = new ModelAndView("Dashboard"); // Display ViewDefectMode.jsp
        PortletSupport portletSupport = new PortletSupport(request);
        String logonUser = portletSupport.getLogonUser();

        // Update User Information
        // call super initScreen to get information of user, create user in OOPMS if it has not existed.
        super.initScreen(request, session);
        UserInfo userInfo = new UserInfo(logonUser);
        prepareCommonInfo(userInfo, mav, session, formBean);

        return mav;

    }
    
    @ActionMapping(params = "action=dashboard")
    public void processDashboard ( DashboardForm formBean,ActionResponse response, ActionRequest request, PortletSession session){
        log.debug("processDashboard.START");
        response.setRenderParameter("action", "taskmanager");
    }
    
    @RenderMapping(params = "action=taskmanager")
    public ModelAndView postPlanner( DashboardForm formBean,RenderRequest request, PortletSession session) {
        ModelAndView mav = new ModelAndView("Dashboard");
        PortletSupport portletSupport = new PortletSupport(request);
        String logonUser = portletSupport.getLogonUser();
        UserInfo userInfo = new UserInfo(logonUser);
        prepareCommonInfo(userInfo, mav, session, formBean);
        return mav;
    }
    
    @ActionMapping(params = "action=search")
    public void processSearchByStatus(DashboardForm formBean, BindingResult result, SessionStatus status,
            ActionResponse response) {
        log.debug("processSearchByStatus.START");
        log.debug(dashboardList.get(1).getProject().getStatus());
        for (int i = 0; i < dashboardList.size(); i++) {
            dashboardList.get(i).setVisible(true);
            if (!formBean.getProjectType().equals(DashboardForm.ALL_VALUE))
                if (!dashboardList.get(i).getProject().getType().equals(formBean.getProjectType())) {
                    dashboardList.get(i).setVisible(false);
                }
            if (!formBean.getProjectCategory().equals(DashboardForm.ALL_VALUE))
                if (!dashboardList.get(i).getProject().getCategory().equals(formBean.getProjectCategory())) {
                    dashboardList.get(i).setVisible(false);
                }
            if (!formBean.getProjectStatus().equals(DashboardForm.ALL_VALUE))
                if (!dashboardList.get(i).getProject().getStatus().equals(formBean.getProjectStatus())) {
                    dashboardList.get(i).setVisible(false);
                }
            if (!formBean.getProjectHealth().equals(DashboardForm.ALL_VALUE))
                if (!dashboardList.get(i).getProjectHealthStatus().equals(formBean.getProjectHealth())) {
                    dashboardList.get(i).setVisible(false);
                }

        }
        formBean.setInit(false);
        response.setRenderParameter("action", "taskmanager");
    }

    @RenderMapping(params = "action=search")
    public void postSearchByStatus(DashboardForm formBean, BindingResult result, SessionStatus status,
            ActionResponse response) {
        log.debug("postSearchByStatus.START");
    }
    
    /**
     * Prepare data to initialize the screen ViewDefectMode. Update information of user: roles, group, loginDate
     * @param userInfo is updated roles by username
     * @param mav contains data ------------------------------------------- |key |value
     *            ------------------------------------------- | |
     */
    void prepareCommonInfo(UserInfo userInfo, ModelAndView mav, PortletSession session, DashboardForm formBean) {
        log.debug("prepareCommonInfo.START");
        assignmentDao = new AssignmentDao();
        developerDao = new DeveloperDao();
        generalReferenceDao = new GeneralReferenceDao();
        
        
        if (formBean.getInit()){
            // Get user info
            Developer developer = developerDao.getDeveloperByAccount(userInfo.getUsername());
            // Get project user belong to
            projectList = assignmentDao.getProjectByDeveloperId(developer.getDeveloperId());
            statusList = generalReferenceDao.getProjectStatusEn();
            typeList = generalReferenceDao.getProjectTypeEn();
            categoryList = generalReferenceDao.getProjectCategoryEn();
        }
        

        // set value for statusMap
        statusMap = new HashMap<String, String>();
        statusMap.put(formBean.getProjectStatus(), DashboardForm.ALL_VALUE);
        if (!formBean.getInit())
            statusMap.put(DashboardForm.ALL_VALUE, DashboardForm.ALL_VALUE);
        for (int i = 0; i < statusList.size(); i++) {
            statusMap.put(statusList.get(i).getGeneralRefId().toString(), statusList.get(i).getDescription());
        }
        // set value for typeMap
        typeMap = new HashMap<String, String>();
        typeMap.put(formBean.getProjectType(), DashboardForm.ALL_VALUE);
        if (!formBean.getInit())
            typeMap.put(DashboardForm.ALL_VALUE, DashboardForm.ALL_VALUE);
        for (int i = 0; i < typeList.size(); i++) {
            typeMap.put(typeList.get(i).getGeneralRefId().toString(), typeList.get(i).getDescription());
        }
        // set value for categoryMap
        categoryMap = new HashMap<String, String>();
        categoryMap.put(formBean.getProjectCategory(), DashboardForm.ALL_VALUE);
        if (!formBean.getInit())
            categoryMap.put(DashboardForm.ALL_VALUE, DashboardForm.ALL_VALUE);
        for (int i = 0; i < categoryList.size(); i++) {
            categoryMap.put(categoryList.get(i).getGeneralRefId().toString(), categoryList.get(i).getDescription());
        }
        // set value for projectHealthMap
        projectHealthMap = new HashMap<String, String>();
        projectHealthMap.put(formBean.getProjectHealth(), DashboardForm.ALL_VALUE);
        if (!formBean.getInit())
            projectHealthMap.put(DashboardForm.ALL_VALUE, DashboardForm.ALL_VALUE);
        projectHealthMap.put(Dashboard.GOOD_STATUS, Dashboard.GOOD_STATUS);
        projectHealthMap.put(Dashboard.NORMAL_STATUS, Dashboard.NORMAL_STATUS);
        projectHealthMap.put(Dashboard.BAD_STATUS, Dashboard.BAD_STATUS);
        
        
        // Set value for dashboard
        for (int i = 0; i < projectList.size(); i++) {
            dashboard = new Dashboard();
            dashboard.setProject(projectList.get(i));
            dashboard.setProjectHealthStatus(Dashboard.GOOD_STATUS);
            dashboard.setPercentTime(50);
            dashboard.setPercentProgress(50);
            dashboard.setEfficiencyStatus(Dashboard.NORMAL_STATUS);
            dashboard.setCostStatus(Dashboard.BAD_STATUS);
            dashboardList.add(dashboard);
        }

        mav.addObject("dashboardList", dashboardList);
        mav.addObject("statusMap", statusMap);
        mav.addObject("typeMap", typeMap);
        mav.addObject("categoryMap", categoryMap);
        mav.addObject("projectHealthMap", projectHealthMap);

        updateUserInfo(session, userInfo);

    }

    void calculatePercentProgress() {
        // TODO:asas
    }
    void calculatePercentTime() {
        // TODO:asas
    }
    void calculateProjectHealth() {
        // TODO:asas
    }
    void calculateEfficiencyStatus() {
        // TODO:asas
    }
    void calculateCostStatus() {
        // TODO:asas
    }
}
