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


import java.math.BigDecimal;
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
public class EntryController extends BaseController {    
    private String logonUser;
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
        
        UserInfo userInfo;
        PortletSupport portletSupport = new PortletSupport(request);
        logonUser = portletSupport.getLogonUser(); //PHAM.NGUYEN.TRUONG.GIANG

        // Update User Information
        // call super initScreen to get information of user, create user in OOPMS if it has not existed.               

        log.debug("logonUser=" + logonUser);

        if (logonUser != null) {
            userInfo = null;
            DeveloperDao devDao = new DeveloperDao();
            Developer dev = devDao.getDeveloperByAccount(logonUser);
            if(logonUser.equals("sysadmin") || logonUser.equals("SYSADMIN"))
            {
                userInfo = new UserInfo(logonUser);                
                prepareCommonInfo(userInfo, mav, session);
                return mav;
            }else if (dev != null) {
                // Set roles for user
                userInfo = new UserInfo(logonUser);
                userInfo.addRole(dev.getRole());
                userInfo.setGroup(dev.getGroupName());
                userInfo.setLoginDate(BaseUtil.getCurrentDate());
            } else {
                dev = new Developer();
                dev.setName(logonUser);
                dev.setAccount(logonUser);
                dev.setStatus(BigDecimal.ONE);
                dev.setRole(BaseUtil.getProperies().getProperty("DefRole"));
                dev.setGroupName(BaseUtil.getProperies().getProperty("DefGroup"));
                if (devDao.insertDeveloper(dev)) {
                    log.info("Created user: " + dev.getAccount());
                } else {
                    log.warn("Could not create user: " + dev.getAccount());
                }
            }
            // Update userInfo into the session
            updateUserInfo(session, userInfo);
        }
        
        
        userInfo = new UserInfo(logonUser);        
        prepareCommonInfo(userInfo, mav, session);
        
        // Get log on user                
        log.debug("initScreenUser.START: "+logonUser);
        // Get developer and related projects from account log on        
        DeveloperDao developerDAO = new DeveloperDao();
        AssignmentDao assignmentDAO = new AssignmentDao();
        //RequirementDao requirementDao = new RequirementDao();
        developer = developerDAO.getDeveloperByAccount(logonUser);
        log.debug("initScreenUserID.START: "+developer.getDeveloperId());
        //List<Project> projectList = requirementDao.getAllProject();
        List<Project> projectList = assignmentDAO.getProject(developer.getDeveloperId());
        
        
        // Set information of user to session           
        // session.setAttribute("USERID", developer.getDeveloperId(), PortletSession.APPLICATION_SCOPE);
        session.setAttribute("USER", developer.getName(), PortletSession.APPLICATION_SCOPE);
        
        // sent projectList to jsp
        //request.setAttribute("projectList", projectList);
        mav.addObject("projectList", projectList);

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
    
    public void updateUserInfo(PortletSession session, UserInfo userInfo) {
        session.setAttribute("UserInfo", userInfo);
    }
    
    @RenderMapping(params = "action=requirementwelcome")
    public ModelAndView postRequirement(RequirementForm formBean, RenderRequest request, PortletSession session) {
        log.debug("postRequirementSTART");                           
        
        ModelAndView mav = new ModelAndView("RequirementWelcome");
        
     // Get log on user
        PortletSupport portletSupport = new PortletSupport(request);
        logonUser = portletSupport.getLogonUser();
        
        // Get developer and related projects from account log on        
        DeveloperDao developerDAO = new DeveloperDao();
        //RequirementDao requirementDao = new RequirementDao();
        AssignmentDao assignmentDAO = new AssignmentDao();
        developer = developerDAO.getDeveloperByAccount(logonUser);
        //List<Project> projectList = requirementDao.getAllProject();
        List<Project> projectList = assignmentDAO.getProject(developer.getDeveloperId());
        
        
        // Set information of user to session           
        // session.setAttribute("USERID", developer.getDeveloperId(), PortletSession.APPLICATION_SCOPE);
        session.setAttribute("USER", developer.getAccount(), PortletSession.APPLICATION_SCOPE);
        
        // sent projectList to jsp
        //request.setAttribute("projectList", projectList);
        mav.addObject("projectList", projectList);
        
        return mav;
    }

}
