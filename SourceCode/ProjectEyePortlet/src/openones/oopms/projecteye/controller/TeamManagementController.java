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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import openones.oopms.projecteye.dao.AssignmentDao;
import openones.oopms.projecteye.dao.DeveloperDao;
import openones.oopms.projecteye.form.TeamManagement;
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
		searchType = formBean.getSearchType();
		Project project = new Project();
		project.setProjectId(new BigDecimal(projectId));
		DeveloperDao dDao = new DeveloperDao();
		userList = dDao.getDeveloperListForAddToProject(project,
				formBean.getSearchString(), searchType);
		response.setRenderParameter("action", "SearchUser");
	}

	@RenderMapping(params = "action=SearchUser")
	public ModelAndView postSearchUser(RenderRequest request) {
		log.debug("post SearchUser.START");
		Project project = new Project();
		project.setProjectId(new BigDecimal(projectId));
		DeveloperDao dDao = new DeveloperDao();
		AssignmentDao aDao = new AssignmentDao();
		List<Developer> teamOfProject = dDao.getDeveloperTeamOfProject(project);
		List<TeamManagement> projectTeamList = new ArrayList<TeamManagement>();
		List<TeamManagement> projectTeamList2 = new ArrayList<TeamManagement>();
		if (teamOfProject.size() > 0) {
			for (int i = 0; i < teamOfProject.size(); i++) {
				TeamManagement temp = new TeamManagement();
				temp.setUserName(teamOfProject.get(i).getName());
				temp.setUserAccount(teamOfProject.get(i).getAccount());
				temp.setDeveloperId(teamOfProject.get(i).getDeveloperId()
						.toString());
				Assignment role = aDao.getUserRole(project, teamOfProject
						.get(i).getDeveloperId());
				temp.setSelectedRole(String.valueOf(role.getType()));
				projectTeamList.add(temp);
				projectTeamList2.add(temp);
			}
		}
		Map<String, String> roleList = new LinkedHashMap<String, String>();
		roleList.put(Constant.DeveloperType, "Developer");
		roleList.put(Constant.TesterType, "Tester");
		roleList.put(Constant.QAType, "QA");
		roleList.put(Constant.CustomerType, "Customer");
		TeamManagementForm form = new TeamManagementForm();
		form.setSearchType(searchType);
		form.setProjectTeamList(projectTeamList2);
		ModelAndView mav = new ModelAndView("TeamManagement",
				"TeamManagementForm", form);
		log.debug("project ID la " + projectId);
		mav.addObject("projectTeamList", projectTeamList);
		mav.addObject("userList", userList);
		mav.addObject("projectId", projectId);
		mav.addObject("roleList", roleList);
		return mav;
	}

	@RenderMapping(params = "action=AddUserToTeam")
	public ModelAndView postAddUserToTeam(RenderRequest request) {
		log.debug("post AddUserToTeam.START");
		projectId = request.getParameter("projectId");
		String developerId = request.getParameter("developerId");
		Assignment assignment = new Assignment();
		// set value for assgment
		Project project = new Project();
		project.setProjectId(new BigDecimal(projectId));
		assignment.setProject(project);
		assignment.setDeveloperId(new BigDecimal(developerId));
		assignment.setType(new Byte(Constant.DeveloperType));
		assignment.setBeginDate(new Date());
		AssignmentDao aDao = new AssignmentDao();
		aDao.insertAssigment(assignment);
		DeveloperDao dDao = new DeveloperDao();
		List<Developer> teamOfProject = dDao.getDeveloperTeamOfProject(project);
		List<TeamManagement> projectTeamList = new ArrayList<TeamManagement>();
		List<TeamManagement> projectTeamList2 = new ArrayList<TeamManagement>();
		if (teamOfProject.size() > 0) {
			for (int i = 0; i < teamOfProject.size(); i++) {
				TeamManagement temp = new TeamManagement();
				temp.setUserName(teamOfProject.get(i).getName());
				temp.setUserAccount(teamOfProject.get(i).getAccount());
				temp.setDeveloperId(teamOfProject.get(i).getDeveloperId()
						.toString());
				Assignment role = aDao.getUserRole(project, teamOfProject
						.get(i).getDeveloperId());
				temp.setSelectedRole(String.valueOf(role.getType()));
				projectTeamList.add(temp);
				projectTeamList2.add(temp);
			}
		}
		Map<String, String> roleList = new LinkedHashMap<String, String>();
		roleList.put(Constant.DeveloperType, "Developer");
		roleList.put(Constant.TesterType, "Tester");
		roleList.put(Constant.QAType, "QA");
		roleList.put(Constant.CustomerType, "Customer");
		TeamManagementForm form = new TeamManagementForm();
		form.setSearchType("name");
		form.setProjectTeamList(projectTeamList2);
		ModelAndView mav = new ModelAndView("TeamManagement",
				"TeamManagementForm", form);
		;
		log.debug("project ID la " + projectId);
		mav.addObject("projectId", projectId);
		mav.addObject("projectTeamList", projectTeamList);
		mav.addObject("roleList", roleList);
		return mav;
	}

	@ActionMapping(params = "action=UpdateRoleAction")
	public void processUpdateRoleAction(TeamManagementForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("process UpdateRoleAction.START");
		projectId = formBean.getProjectId();
		Project project = new Project();
		project.setProjectId(new BigDecimal(projectId));
		AssignmentDao aDao = new AssignmentDao();

		List<TeamManagement> tempList = formBean.getProjectTeamList();
		if (tempList.size() > 0) {
			for (int i = 0; i < tempList.size(); i++) {
				log.error("list role la : " + tempList.get(i).getSelectedRole());
				log.error("list ID la : " + tempList.get(i).getDeveloperId());
			}
			for (int i = 0; i < tempList.size(); i++) {
				if (tempList.get(i).getSelectedRole() != null) {
					Assignment currentRole = aDao.getUserRole(project,
							new BigDecimal(tempList.get(i).getDeveloperId()));
					if (!String.valueOf(currentRole.getType()).equals(
							tempList.get(i).getSelectedRole())) {
						aDao.removeTeamMember(project, new BigDecimal(tempList
								.get(i).getDeveloperId()));
						Assignment assignment = new Assignment();
						assignment.setProject(project);
						assignment.setDeveloperId(new BigDecimal(tempList
								.get(i).getDeveloperId()));
						assignment.setBeginDate(new Date());
						assignment.setType(new Byte(tempList.get(i)
								.getSelectedRole()));
						aDao.insertAssigment(assignment);
					}
				}
			}
		}
		response.setRenderParameter("action", "UpdateRoleAction");
	}
	
	@RenderMapping(params = "action=UpdateRoleAction")
	public ModelAndView postUpdateRoleAction(RenderRequest request) {
		log.debug("post UpdateRoleAction.START");
        Project project = new Project();
        project.setProjectId(new BigDecimal(projectId));
        DeveloperDao dDao = new DeveloperDao();
        AssignmentDao aDao = new AssignmentDao();
        List<Developer> teamOfProject = dDao.getDeveloperTeamOfProject(project);
        List<TeamManagement> projectTeamList = new ArrayList<TeamManagement>();
        List<TeamManagement> projectTeamList2 = new ArrayList<TeamManagement>();
        if(teamOfProject.size()>0) {
        	for(int i=0; i<teamOfProject.size();i++) {
        		TeamManagement temp = new TeamManagement();
        		temp.setUserName(teamOfProject.get(i).getName());
        		temp.setUserAccount(teamOfProject.get(i).getAccount());
        		temp.setDeveloperId(teamOfProject.get(i).getDeveloperId().toString());
        		Assignment role = aDao.getUserRole(project, teamOfProject.get(i).getDeveloperId());
        		temp.setSelectedRole(String.valueOf(role.getType()));
        		projectTeamList.add(temp);
        		projectTeamList2.add(temp);
        	}
        }
        Map<String, String> roleList = new LinkedHashMap<String, String>();
        roleList.put(Constant.DeveloperType, "Developer");
        roleList.put(Constant.TesterType, "Tester");
        roleList.put(Constant.QAType, "QA");
        roleList.put(Constant.CustomerType, "Customer");
        TeamManagementForm form = new TeamManagementForm();
        form.setSearchType("name");
        form.setProjectTeamList(projectTeamList2);
        ModelAndView mav = new ModelAndView("TeamManagement", "TeamManagementForm", form);
        mav.addObject("projectTeamList",projectTeamList);
        mav.addObject("roleList",roleList);
        log.debug("project ID la "+ projectId);
        mav.addObject("projectId", projectId);
        return mav;
	}
}
