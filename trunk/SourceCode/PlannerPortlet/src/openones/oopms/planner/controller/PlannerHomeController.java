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
package openones.oopms.planner.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;

import openones.oopms.form.UserInfo;
import openones.oopms.planner.dao.AssignmentDAO;
import openones.oopms.planner.dao.DeveloperDAO;
import openones.oopms.planner.model.Developer;
import openones.oopms.planner.model.Project;
import openones.oopms.portlet.controller.BaseController;
import openones.portlet.PortletSupport;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

/**
 * @author PNTG
 */
@Controller
@RequestMapping("VIEW")
public class PlannerHomeController extends BaseController {
    private static Logger log = Logger.getLogger(PlannerHomeController.class);
    private Developer developer;

    /**
     * Default screen.
     * @return name of view which is the name of the JSP page.
     */
    @RequestMapping
    public ModelAndView initScreen(RenderRequest request, PortletSession session) {
        log.debug("initScreen.START");
        ModelAndView mav = new ModelAndView("PlannerHome");
        super.initScreen(request, session);
        // Get log on user
        PortletSupport portletSupport = new PortletSupport(request);
        String logonUser = portletSupport.getLogonUser();
        UserInfo userInfo = new UserInfo(logonUser);
        prepareCommonInfo(userInfo, mav, session);
        // Display PlannerHome.jsp
        return mav;
    }
    void prepareCommonInfo(UserInfo userInfo, ModelAndView mav, PortletSession session) {
        AssignmentDAO assignmentDao = new AssignmentDAO();
        DeveloperDAO DeveloperDAO = new DeveloperDAO();
        // Get user info
        Developer developer = DeveloperDAO.getDeveloperByAccount(userInfo.getUsername());
        // Get projects belong to user
        List<Project> projectList = assignmentDao.getProject(developer.getDeveloperId());
        
        updateUserInfo(session, userInfo);
        session.setAttribute("UserId", developer.getDeveloperId().toString(), PortletSession.APPLICATION_SCOPE);
        mav.addObject("projectList", projectList);
    }

}
