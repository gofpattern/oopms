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
import java.util.Date;
import java.util.List;

import javax.portlet.RenderRequest;

import openones.oopms.projecteye.dao.AssignmentDao;
import openones.oopms.projecteye.dao.DeveloperDao;
import openones.oopms.projecteye.form.AssignProjectManagerForm;
import openones.oopms.projecteye.model.Assignment;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.Project;
import openones.oopms.projecteye.utils.Constant;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 * @author HaiTCT
 */
@Controller
@RequestMapping("VIEW")
public class AssignProjectManagerController {

	Developer user = new Developer();
	/** Logger for logging. */
	private static Logger log = Logger
			.getLogger(AssignProjectManagerController.class);

	@RenderMapping(params = "action=ChangeProjectManager")
	public ModelAndView postChangeProjectManager(RenderRequest request) {
		log.debug("post ChangeProjectManager.START");
		String projectId = request.getParameter("projectId");
		String userId = request.getParameter("userId");
		Assignment assignment = new Assignment();
		AssignmentDao aDao = new AssignmentDao();

		// set value for assgment
		Project project = new Project();
		project.setProjectId(new BigDecimal(projectId));
		Assignment currentRole = aDao.getUserRole(project, new BigDecimal(
				userId));
		aDao.removeTeamMember(project, new BigDecimal(userId));
		aDao.removeProjectManager(project);
		assignment.setProject(project);
		assignment.setDeveloperId(new BigDecimal(userId));
		if (Constant.ProjectOwnerType.equals(String.valueOf(currentRole
				.getType()))) {
			assignment.setType(new Byte(
					Constant.ProjectOwnerAndProjectManagerType));
		} else {
			assignment.setType(new Byte(Constant.ProjectManagerType));
		}
		assignment.setBeginDate(new Date());

		aDao.insertAssigment(assignment);
		DeveloperDao dDao = new DeveloperDao();
		List<Developer> teamOfProject = dDao.getDeveloperTeamOfProject(project);
		List<AssignProjectManagerForm> projectTeamList = new ArrayList<AssignProjectManagerForm>();
		if (teamOfProject.size() > 0) {
			for (int i = 0; i < teamOfProject.size(); i++) {
				AssignProjectManagerForm temp = new AssignProjectManagerForm();
				temp.setUserName(teamOfProject.get(i).getName());
				temp.setUserAccount(teamOfProject.get(i).getAccount());
				temp.setUserId(teamOfProject.get(i).getDeveloperId().toString());
				Assignment role = aDao.getUserRole(project, teamOfProject
						.get(i).getDeveloperId());
				temp.setUserRole(String.valueOf(role.getType()));
				if (Constant.ProjectOwnerAndProjectManagerType.equals(String
						.valueOf(role.getType()))) {
					temp.setUserRoleString("Project Owner and Project Manager");
				} else if (Constant.ProjectManagerType.equals(String
						.valueOf(role.getType()))) {
					temp.setUserRoleString("Project Manager");
				} else if (Constant.DeveloperType.equals(String.valueOf(role
						.getType()))) {
					temp.setUserRoleString("Developer");
				} else if (Constant.TesterType.equals(String.valueOf(role
						.getType()))) {
					temp.setUserRoleString("Tester");
				} else if (Constant.QAType
						.equals(String.valueOf(role.getType()))) {
					temp.setUserRoleString("QA");
				} else if (Constant.CustomerType.equals(String.valueOf(role
						.getType()))) {
					temp.setUserRoleString("Customer");
				} else if (Constant.ProjectOwnerType.equals(String.valueOf(role
						.getType()))) {
					temp.setUserRoleString("Project Owner");
				}
				projectTeamList.add(temp);
			}
		}
		AssignProjectManagerForm form = new AssignProjectManagerForm();
		ModelAndView mav = new ModelAndView("AssignProjectManager",
				"AssignProjectManagerForm", form);
		mav.addObject("projectTeamList", projectTeamList);
		log.debug("project ID la " + projectId);
		mav.addObject("projectId", projectId);
		return mav;
	}
}
