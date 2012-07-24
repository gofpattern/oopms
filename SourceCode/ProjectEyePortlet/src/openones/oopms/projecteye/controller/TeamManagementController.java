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
import java.util.Date;
import java.util.List;

import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import openones.oopms.projecteye.dao.AssignmentDao;
import openones.oopms.projecteye.dao.DeveloperDao;
import openones.oopms.projecteye.dao.ProductDao;
import openones.oopms.projecteye.form.CreateProductForm;
import openones.oopms.projecteye.form.TeamManagementForm;
import openones.oopms.projecteye.model.Assignment;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.Module;
import openones.oopms.projecteye.model.Project;
import openones.oopms.projecteye.model.Workproduct;

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
public class TeamManagementController {

	Developer user = new Developer();
	/** Logger for logging. */
	private static Logger log = Logger
			.getLogger(TeamManagementController.class);
	String projectId;
	List<Developer> userList;
	String searchType;
	
	@ActionMapping(params = "action=SearchUser")
	public void processSearchUser(TeamManagementForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("process SearchUser.START");
		projectId = formBean.getProjectId();
		searchType= formBean.getSearchType();
		Project project = new Project();
		project.setProjectId(new BigDecimal(projectId));
		DeveloperDao dDao = new DeveloperDao();
		userList = dDao.getDeveloperListForAddToProject(project, formBean.getSearchString(), searchType);
		response.setRenderParameter("action", "SearchUser");
	}

	@RenderMapping(params = "action=SearchUser")
	public ModelAndView postSearchUser(RenderRequest request) {
		log.debug("post SearchUser.START");
		TeamManagementForm form = new TeamManagementForm();
        form.setSearchType(searchType);
        ModelAndView mav = new ModelAndView("TeamManagement", "TeamManagementForm", form);
		log.debug("project ID la " + projectId);
		mav.addObject("userList", userList);
		mav.addObject("projectId", projectId);
		return mav;
	}
	
	@RenderMapping(params = "action=AddUserToTeam")
	public ModelAndView postAddUserToTeam(RenderRequest request) {
		log.debug("post AddUserToTeam.START");
		projectId = request.getParameter("projectId");
		String developerId = request.getParameter("developerId");
		Assignment assignment = new Assignment();
        //set value for assgment
		Project project = new Project();
		project.setProjectId(new BigDecimal(projectId));
		assignment.setProject(project);
        assignment.setDeveloperId(new BigDecimal(developerId));
        assignment.setType(new Byte("2"));
        assignment.setBeginDate(new Date());
        AssignmentDao aDao = new AssignmentDao();
        aDao.insertAssigment(assignment);
        TeamManagementForm form = new TeamManagementForm();
        form.setSearchType("name");
        ModelAndView mav = new ModelAndView("TeamManagement", "TeamManagementForm", form);;
		log.debug("project ID la " + projectId);
		mav.addObject("projectId", projectId);
		return mav;
	}
}
