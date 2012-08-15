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
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;

import openones.oopms.daocommon.AssignmentDao;
import openones.oopms.daocommon.BusinessDomainDao;
import openones.oopms.daocommon.DeveloperDao;
import openones.oopms.daocommon.GeneralReferenceDao;
import openones.oopms.dashboard.form.DashboardForm;
import openones.oopms.dashboard.model.Dashboard;
import openones.oopms.entity.BusinessDomain;
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
    private List<Dashboard> dashboardList;
    private List<Project> projectList;
    private List<GeneralReference> statusList;
    private List<BusinessDomain> businessDomainList;
    private List<GeneralReference> categoryList;
    private Map<String, String> businessDomainMap;
    private Map<String, String> statusMap;
    private Map<String, String> categoryMap;
    private Map<String, String> projectHealthMap;
    private AssignmentDao assignmentDao;
    private DeveloperDao developerDao;
    private GeneralReferenceDao generalReferenceDao;
    private BusinessDomainDao businessDomainDao;
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
    public void processDashboard(DashboardForm formBean, ActionResponse response, ActionRequest request,
            PortletSession session) {
        log.debug("processDashboard.START");
        response.setRenderParameter("action", "dashboard");

    }

    @RenderMapping(params = "action=dashboard")
    public ModelAndView postDashboard(DashboardForm formBean, RenderRequest request, PortletSession session) {
        log.debug("postDashboard.START");
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
        for (int i = 0; i < dashboardList.size(); i++) {
            dashboardList.get(i).setVisible(true);
            if (!formBean.getProjectCategory().equals(DashboardForm.ALL_VALUE))
                if (!dashboardList.get(i).getProject().getProjectCategoryCode().equals(formBean.getProjectCategory())) {
                    dashboardList.get(i).setVisible(false);
                }
            if (!formBean.getProjectDomain().equals(DashboardForm.ALL_VALUE))
                if (!dashboardList.get(i).getProject().getProjectTypeCode().equals(formBean.getProjectDomain())) {
                    dashboardList.get(i).setVisible(false);
                }
            if (!formBean.getProjectStatus().equals(DashboardForm.ALL_VALUE))
                if (!dashboardList.get(i).getProject().getProjectStatusCode().equals(formBean.getProjectStatus())) {
                    dashboardList.get(i).setVisible(false);
                }
            if (!formBean.getProjectHealth().equals(DashboardForm.ALL_VALUE))
                if (!dashboardList.get(i).getProjectHealthStatus().equals(formBean.getProjectHealth())) {
                    dashboardList.get(i).setVisible(false);
                }

        }
        formBean.setInit(false);
        response.setRenderParameter("action", "dashboard");
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
        businessDomainDao = new BusinessDomainDao();

        if (formBean.getInit()) {
            // Get user info
            Developer developer = developerDao.getDeveloperByAccount(userInfo.getUsername());
            dashboardList = new ArrayList<Dashboard>();
            // Get project user belong to
            projectList = assignmentDao.getProjectByDeveloperId(developer.getDeveloperId());
            statusList = generalReferenceDao.getProjectStatusEn();
            businessDomainList = businessDomainDao.getBusinessDomain();
            log.debug("BizDomainSize " + businessDomainList.size());
            categoryList = generalReferenceDao.getProjectCategoryEn();

            formBean.setProjectCategory(DashboardForm.ALL_VALUE);
            formBean.setProjectDomain(DashboardForm.ALL_VALUE);
            formBean.setProjectStatus(DashboardForm.ALL_VALUE);
            formBean.setProjectHealth(DashboardForm.ALL_VALUE);

            // Set value for dashboard
            for (int i = 0; i < projectList.size(); i++) {
                dashboard = new Dashboard();
                dashboard.setProject(projectList.get(i));
                dashboard.setProjectHealthStatus(Dashboard.GOOD_STATUS);
                dashboard.setPercentTime((int) calculatePercentTime(projectList.get(i).getStartDate(),
                        projectList.get(i).getPlanFinishDate()));
                dashboard.setPercentProgress(50);
                dashboard.setEfficiencyStatus(Dashboard.NORMAL_STATUS);
                dashboard.setCostStatus(Dashboard.BAD_STATUS);
                dashboardList.add(dashboard);
                log.debug("dashboard.getPercentProgress() " + dashboard.getPercentTime());
            }
        }

        statusMap = new LinkedHashMap<String, String>();
        projectHealthMap = new LinkedHashMap<String, String>();
        categoryMap = new LinkedHashMap<String, String>();
        businessDomainMap = new LinkedHashMap<String, String>();

        // set value for statusMap
        statusMap.put(formBean.getProjectStatus(), DashboardForm.ALL_VALUE);
        if (formBean.getInit() == false)
            statusMap.put(DashboardForm.ALL_VALUE, DashboardForm.ALL_VALUE);
        for (int i = 0; i < statusList.size(); i++) {
            statusMap.put(statusList.get(i).getGeneralRefId().toString(), statusList.get(i).getDescription());
        }
        // set value for businessDomainMap
        businessDomainMap.put(formBean.getProjectDomain(), DashboardForm.ALL_VALUE);
        if (formBean.getInit() == false)
            businessDomainMap.put(DashboardForm.ALL_VALUE, DashboardForm.ALL_VALUE);
        for (int i = 0; i < businessDomainList.size(); i++) {
            businessDomainMap.put(businessDomainList.get(i).getDomainId().toString(), businessDomainList.get(i)
                    .getDomainName());
        }
        // set value for categoryMap
        categoryMap.put(formBean.getProjectCategory(), DashboardForm.ALL_VALUE);
        if (formBean.getInit() == false)
            categoryMap.put(DashboardForm.ALL_VALUE, DashboardForm.ALL_VALUE);
        for (int i = 0; i < categoryList.size(); i++) {
            categoryMap.put(categoryList.get(i).getGeneralRefId().toString(), categoryList.get(i).getDescription());
        }
        // set value for projectHealthMap
        projectHealthMap.put(formBean.getProjectHealth(), DashboardForm.ALL_VALUE);
        if (formBean.getInit() == false)
            projectHealthMap.put(DashboardForm.ALL_VALUE, DashboardForm.ALL_VALUE);
        projectHealthMap.put(Dashboard.GOOD_STATUS, Dashboard.GOOD_STATUS);
        projectHealthMap.put(Dashboard.NORMAL_STATUS, Dashboard.NORMAL_STATUS);
        projectHealthMap.put(Dashboard.BAD_STATUS, Dashboard.BAD_STATUS);

        mav.addObject("dashboardList", dashboardList);
        mav.addObject("statusMap", statusMap);
        mav.addObject("businessDomainMap", businessDomainMap);
        mav.addObject("categoryMap", categoryMap);
        mav.addObject("projectHealthMap", projectHealthMap);

        updateUserInfo(session, userInfo);

        log.debug("prepareCommonInfo.END");

    }

    private void calculatePercentProgress() {
        // TODO:asas
    }
    private double calculatePercentTime(Date startDate, Date endDate) {
        Date currentDate = new Date();
        Double percentTime = ((double) currentDate.getTime() - (double) startDate.getTime())
                / ((double) endDate.getTime() - (double) startDate.getTime()) * 100;        
        return percentTime;
    }
    private void calculateProjectHealth() {
        // TODO:asas
    }
    private void calculateEfficiencyStatus() {
        // TODO:asas
    }
    private void calculateCostStatus() {
        // TODO:asas
    }
}
