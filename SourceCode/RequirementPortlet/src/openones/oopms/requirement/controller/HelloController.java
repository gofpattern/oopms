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
package openones.oopms.requirement.controller;


import java.util.List;

import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;

import openones.oopms.form.UserInfo;
import openones.oopms.portlet.controller.BaseController;
import openones.oopms.requirement.dao.AssignmentDao;
import openones.oopms.requirement.dao.DeveloperDao;
import openones.oopms.requirement.form.RequirementForm;
import openones.oopms.requirement.model.Developer;
import openones.oopms.requirement.model.Project;
import openones.oopms.util.BaseUtil;
import openones.portlet.PortletSupport;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 * @author Kenda
 */
@Controller
@RequestMapping("VIEW")
public class HelloController extends BaseController {    
    private String username;
    Developer developer = new Developer();
    private static Logger log = Logger.getLogger(RequirementController.class);
    /**
     * Default screen.
     * @return name of view which is the name of the JSP page.
     */
    
    @RequestMapping
    public ModelAndView initScreen(RenderRequest request, PortletSession session) {
        log.debug("initScreen.START");
        ModelAndView mav = new ModelAndView("RequirementWelcome");
        
        PortletSupport portletSupport = new PortletSupport(request);
        username = portletSupport.getLogonUser(); //PHAM.NGUYEN.TRUONG.GIANG

        // Update User Information
        // call super initScreen to get information of user, create user in OOPMS if it has not existed.
        super.initScreen(request, session);
        UserInfo userInfo = new UserInfo(username);
        mav = new ModelAndView("Dashboard"); // Display ViewDefectMode.jsp
        prepareCommonInfo(userInfo, mav, session);
        
        // Get log on user                
        log.debug("initScreenUser.START: "+username);
        // Get developer and related projects from account log on        
        DeveloperDao developerDAO = new DeveloperDao();
        AssignmentDao assignmentDAO = new AssignmentDao();
        //RequirementDao requirementDao = new RequirementDao();
        developer = developerDAO.getDeveloperByAccount(username);
        log.debug("initScreenUserID.START: "+developer.getDeveloperId());
        //List<Project> projectList = requirementDao.getAllProject();
        List<Project> projectList = assignmentDAO.getProject(developer.getDeveloperId());
        
        
        // Set information of user to session           
        // session.setAttribute("USERID", developer.getDeveloperId(), PortletSession.APPLICATION_SCOPE);
        session.setAttribute("USER", developer.getName(), PortletSession.APPLICATION_SCOPE);
        
        // sent projectList to jsp
        request.setAttribute("projectList", projectList);

        // Display PlannerHome.jsp
        return mav;
    }
    
    void prepareCommonInfo(UserInfo userInfo, ModelAndView mav, PortletSession session) {
        // Sample data
        // Set roles for user
        userInfo.addRole("Developer");
        userInfo.setGroup("Development");
        userInfo.setLoginDate(BaseUtil.getCurrentDate());
        // Update userInfo into the session
        updateUserInfo(session, userInfo);

    }
    
    @RenderMapping(params = "action=requirementwelcome")
    public ModelAndView postRequirement(RequirementForm formBean, RenderRequest request, PortletSession session) {
        log.debug("postRequirementSTART");                           
        
        ModelAndView mav = new ModelAndView("RequirementWelcome");
        
     // Get log on user
        PortletSupport portletSupport = new PortletSupport(request);
        username = portletSupport.getLogonUser();
        
        // Get developer and related projects from account log on        
        DeveloperDao developerDAO = new DeveloperDao();
        //RequirementDao requirementDao = new RequirementDao();
        AssignmentDao assignmentDAO = new AssignmentDao();
        developer = developerDAO.getDeveloperByAccount(username);
        //List<Project> projectList = requirementDao.getAllProject();
        List<Project> projectList = assignmentDAO.getProject(developer.getDeveloperId());
        
        
        // Set information of user to session           
        // session.setAttribute("USERID", developer.getDeveloperId(), PortletSession.APPLICATION_SCOPE);
        session.setAttribute("USER", developer.getAccount(), PortletSession.APPLICATION_SCOPE);
        
        // sent projectList to jsp
        request.setAttribute("projectList", projectList);
        
        return mav;
    }

}
