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

import openones.oopms.requirement.dao.DeveloperDao;
import openones.oopms.requirement.dao.RequirementDao;
import openones.oopms.requirement.model.Developer;
import openones.oopms.requirement.model.Project;
import openones.portlet.PortletSupport;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author PNTG
 */
@Controller
@RequestMapping("VIEW")
public class HelloController {    
    private String username;
    Developer developer = new Developer();
    private static Logger log = Logger.getLogger(RequirementController.class);
    /**
     * Default screen.
     * @return name of view which is the name of the JSP page.
     */
    
    @RequestMapping
    public String initScreen(RenderRequest request, PortletSession session) {
        log.debug("initScreen.START");
        // Get log on user
        PortletSupport portletSupport = new PortletSupport(request);
        username = portletSupport.getLogonUser();
        
        // Get developer and related projects from account log on        
        DeveloperDao developerDAO = new DeveloperDao();
        RequirementDao requirementDao = new RequirementDao();
        developer = developerDAO.getDeveloperByAccount(username);
        List<Project> projectList = requirementDao.getAllProject();
        
        
        // Set information of user to session           
        // session.setAttribute("USERID", developer.getDeveloperId(), PortletSession.APPLICATION_SCOPE);
        session.setAttribute("USER", developer.getAccount(), PortletSession.APPLICATION_SCOPE);
        
        // sent projectList to jsp
        request.setAttribute("projectList", projectList);

        // Display PlannerHome.jsp
        return "RequirementWelcome";
    }

}
