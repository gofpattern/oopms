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
package openones.oopms.projecteye.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import openones.oopms.projecteye.dao.AssignmentDao;
import openones.oopms.projecteye.dao.DeveloperDao;
import openones.oopms.projecteye.form.ProductForm;
import openones.oopms.projecteye.form.TeamManagementForm;
import openones.oopms.projecteye.model.Assignment;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.Project;
import openones.oopms.projecteye.utils.Constant;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 * @author HaiTCT
 */
@Controller
@RequestMapping("VIEW")
public class ProjectDetailController {

	Developer user = new Developer();
	/** Logger for logging. */
	private static Logger log = Logger.getLogger(ProjectDetailController.class);

	@ActionMapping(params = "action=goUpdateProject")
    public void processGoUpdateProject(BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("process goUpdateProject.START");
        response.setRenderParameter("action", "goUpdateProject");    
    }
    
    @RenderMapping(params = "action=goUpdateProject")
    public ModelAndView postGoUpdateProject(RenderRequest request) {
        log.debug("post goUpdateProject.START");
        ModelAndView mav = new ModelAndView("UpdateProject");
        String projectId = request.getParameter("projectId");
        log.debug("project ID la "+ projectId);
        mav.addObject("projectId", projectId);
        return mav;
    }
    
    
    @ActionMapping(params = "action=goTeamManagement")
    public void processGoTeamManagement(BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("process GoTeamManagement.START");
        response.setRenderParameter("action", "goTeamManagement");    
    }
    
    @RenderMapping(params = "action=goTeamManagement")
    public ModelAndView postGoTeamManagement(RenderRequest request) {
        log.debug("post GoTeamManagement.START");
        String projectId = request.getParameter("projectId");
        Project project = new Project();
        project.setProjectId(new BigDecimal(projectId));
        DeveloperDao dDao = new DeveloperDao();
        AssignmentDao aDao = new AssignmentDao();
        List<Developer> teamOfProject = dDao.getDeveloperTeamOfProject(project);
        List<TeamManagementForm> projectTeamList = new ArrayList<TeamManagementForm>();
        if(teamOfProject.size()>0) {
        	for(int i=0; i<teamOfProject.size();i++) {
        		TeamManagementForm temp = new TeamManagementForm();
        		temp.setUserName(teamOfProject.get(i).getName());
        		temp.setUserAccount(teamOfProject.get(i).getAccount());
        		Assignment role = aDao.getUserRole(project, teamOfProject.get(i).getDeveloperId());
        		if(Constant.ProjectOwnerAndProjectManagerType.equals(String.valueOf(role.getType()))) {
        			temp.setUserRole("Project Owner and Project Manager");
        		} else if (Constant.ProjectManagerType.equals(String.valueOf(role.getType()))) {
        			temp.setUserRole("Project Manager");
        		} else if (Constant.DeveloperType.equals(String.valueOf(role.getType()))) {
        			temp.setUserRole("Developer");
        		} else if (Constant.TesterType.equals(String.valueOf(role.getType()))){
        			temp.setUserRole("Tester");
        		} else if (Constant.QAType.equals(String.valueOf(role.getType()))) {
        			temp.setUserRole("QA");
        		} else if (Constant.CustomerType.equals(String.valueOf(role.getType()))) {
        			temp.setUserRole("Customer");
        		} else if (Constant.ProjectOwnerType.equals(String.valueOf(role.getType()))) {
        			temp.setUserRole("Project Owner");
        		}
        		projectTeamList.add(temp);
        	}
        }
        TeamManagementForm form = new TeamManagementForm();
        form.setSearchType("name");
        ModelAndView mav = new ModelAndView("TeamManagement", "TeamManagementForm", form);
        mav.addObject("projectTeamList",projectTeamList);
        log.debug("project ID la "+ projectId);
        mav.addObject("projectId", projectId);
        return mav;
    }
}
