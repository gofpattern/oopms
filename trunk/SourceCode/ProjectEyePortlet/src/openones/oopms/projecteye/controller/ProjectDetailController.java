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
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import openones.oopms.projecteye.dao.AssignmentDao;
import openones.oopms.projecteye.dao.DeveloperDao;
import openones.oopms.projecteye.dao.ProjectDao;
import openones.oopms.projecteye.form.AssignProjectManagerForm;
import openones.oopms.projecteye.form.TeamManagement;
import openones.oopms.projecteye.form.TeamManagementForm;
import openones.oopms.projecteye.form.UpdateProjectForm;
import openones.oopms.projecteye.model.Assignment;
import openones.oopms.projecteye.model.BusinessDomain;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.GeneralReference;
import openones.oopms.projecteye.model.Project;
import openones.oopms.projecteye.utils.Constant;
import openones.oopms.projecteye.utils.HTMLTag;

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

	@RenderMapping(params = "action=goUpdateProject")
	public ModelAndView postGoUpdateProject(RenderRequest request) {
		log.debug("post GoCreateProject.START");
		String projectId = request.getParameter("projectId");
		ProjectDao pDao = new ProjectDao();
		Project project = pDao.getProject(projectId);
		DeveloperDao dDao = new DeveloperDao();
		Developer developer = dDao.getProjectManager(project);
		// get category List
		List<GeneralReference> projectCategoryList = pDao
				.getProjectCategoryList();
		Map<String, String> projectCategoryMap = new LinkedHashMap<String, String>();
		projectCategoryMap.put(null, " ");
		for (int i = 0; i < projectCategoryList.size(); i++) {
			projectCategoryMap.put(projectCategoryList.get(i).getGeneralRefId()
					.toString(), projectCategoryList.get(i).getDescription());
		}
		// get Status list
		List<GeneralReference> projectStatusList = pDao.getProjectStatusList();
		Map<String, String> projectStatusMap = new LinkedHashMap<String, String>();
		projectStatusMap.put(null, " ");
		for (int i = 0; i < projectStatusList.size(); i++) {
			projectStatusMap.put(projectStatusList.get(i).getGeneralRefId()
					.toString(), projectStatusList.get(i).getDescription());
		}

		// get Bussiness domain list
		List<BusinessDomain> projectBussinessDomainList = pDao
				.getProjectBussinessDomainList();
		Map<String, String> projectBussinessDomainMap = new LinkedHashMap<String, String>();
		projectBussinessDomainMap.put(null, " ");
		for (int i = 0; i < projectBussinessDomainList.size(); i++) {
			projectBussinessDomainMap.put(projectBussinessDomainList.get(i)
					.getDomainId().toString(), projectBussinessDomainList
					.get(i).getDomainName());
		}
		// set value for form
		UpdateProjectForm formBean = new UpdateProjectForm();
		DateFormat df = new java.text.SimpleDateFormat("MM/dd/yyyy");
		String formattedDate = df.format(project.getPlanStartDate());
		formBean.setPlanStartDate(formattedDate);
		formattedDate = df.format(project.getPlanFinishDate());
		formBean.setPlanEndDate(formattedDate);
		formBean.setScopeObjective(HTMLTag.replaceHTMLTag(project
				.getDescription()));
		formBean.setProjectCode(project.getCode());
		formBean.setProjectName(project.getName());
		formBean.setBusinessDomain_SelectedValue(project.getProjectTypeCode());
		formBean.setCustomer(project.getCustomer());
		formBean.setEndCustomer(project.getCustomer2nd());
		formBean.setProjectCategory_SelectedValue(project
				.getProjectCategoryCode());
		formBean.setProjectStatus_SelectedValue(project.getProjectStatusCode());
		formBean.setManager(developer.getName());

		ModelAndView mav = new ModelAndView("UpdateProject",
				"UpdateProjectForm", formBean);
		mav.addObject("projectStatus", projectStatusMap);
		mav.addObject("projectCategory", projectCategoryMap);
		mav.addObject("businessDomain", projectBussinessDomainMap);
		log.debug("project ID la " + projectId);
		mav.addObject("projectId", projectId);
		return mav;
	}

	@ActionMapping(params = "action=goTeamManagement")
	public void processGoTeamManagement(BindingResult result,
			SessionStatus status, ActionResponse response) {
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
		mav.addObject("projectTeamList", projectTeamList);
		mav.addObject("roleList", roleList);
		log.debug("project ID la " + projectId);
		mav.addObject("projectId", projectId);
		return mav;
	}

	@RenderMapping(params = "action=GoAssignProjectManager")
	public ModelAndView postGoAssignProjectManager(RenderRequest request) {
		log.debug("post GoAssignProjectManager.START");
		String projectId = request.getParameter("projectId");
		Project project = new Project();
		project.setProjectId(new BigDecimal(projectId));
		DeveloperDao dDao = new DeveloperDao();
		AssignmentDao aDao = new AssignmentDao();
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
