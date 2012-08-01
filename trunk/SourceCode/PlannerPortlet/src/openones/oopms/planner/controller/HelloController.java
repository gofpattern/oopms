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

import javax.portlet.RenderRequest;

import openones.oopms.planner.dao.AssignmentDAO;
import openones.oopms.planner.dao.DeveloperDAO;
import openones.oopms.planner.model.Project;
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
    private static Logger log = Logger.getLogger(PlannerController.class);
    public static String username;

    /**
     * Default screen.
     * @return name of view which is the name of the JSP page.
     */
    @RequestMapping
    public String initScreen(RenderRequest request) {
        log.debug("initScreen.START");
        PortletSupport portletSupport = new PortletSupport(request);
        username = portletSupport.getLogonUser();
        AssignmentDAO assignmentDAO = new AssignmentDAO();
        DeveloperDAO developerDAO = new DeveloperDAO();
        BigDecimal developerId = developerDAO.getDeveloperId(username);
        List<Project> projectList = assignmentDAO.getProject(developerId);
        request.setAttribute("projectList", projectList);

        // Display PlannerHome.jsp
        return "PlannerHome";
    }

}
