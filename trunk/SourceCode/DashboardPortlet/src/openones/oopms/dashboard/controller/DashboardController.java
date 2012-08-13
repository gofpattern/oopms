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
import java.util.List;

import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;

import openones.oopms.daocommon.AssignmentDao;
import openones.oopms.daocommon.DeveloperDao;
import openones.oopms.dashboard.form.DashboardForm;
import openones.oopms.dashboard.model.Dashboard;
import openones.oopms.entity.Developer;
import openones.oopms.entity.Project;
import openones.oopms.form.UserInfo;
import openones.oopms.portlet.controller.BaseController;
import openones.portlet.PortletSupport;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

/**
 * @author PNTG
 */
@Controller
@RequestMapping("VIEW")
public class DashboardController extends BaseController {
    private static Logger log = Logger.getLogger(DashboardController.class);
    private List<Dashboard> dashboardList;

    /**
     * Default screen.
     * @return name of view which is the name of the JSP page.
     */
    @RequestMapping
    public ModelAndView initScreen(RenderRequest request, PortletSession session) {
        log.debug("initScreen.START");
        ModelAndView mav = new ModelAndView("Dashboard"); // Display ViewDefectMode.jsp
        PortletSupport portletSupport = new PortletSupport(request);
        String logonUser = portletSupport.getLogonUser();

        // Update User Information
        // call super initScreen to get information of user, create user in OOPMS if it has not existed.
        super.initScreen(request, session);
        UserInfo userInfo = new UserInfo(logonUser);
        prepareCommonInfo(userInfo, mav, session);

        return mav;

    }

    @ModelAttribute("DashboardForm")
    public DashboardForm getCommandObject() {
        log.debug("getCommandObject.START");
        DashboardForm formBean = new DashboardForm();
        return formBean;
    }

    /**
     * Prepare data to initialize the screen ViewDefectMode. Update information of user: roles, group, loginDate
     * @param userInfo is updated roles by username
     * @param mav contains data ------------------------------------------- |key |value
     *            ------------------------------------------- | |
     */
    void prepareCommonInfo(UserInfo userInfo, ModelAndView mav, PortletSession session) {
        AssignmentDao assignmentDao = new AssignmentDao();
        DeveloperDao developerDao = new DeveloperDao();
        dashboardList = new ArrayList<Dashboard>();
        Dashboard dashboard;
        // Get user info
        Developer developer = developerDao.getDeveloperByAccount(userInfo.getUsername());
        // Get project user belong to
        List<Project> projectList = assignmentDao.getProjectByDeveloperId(developer.getDeveloperId());

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
