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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.RenderRequest;

import openones.oopms.projecteye.dao.ChangeRequestDao;
import openones.oopms.projecteye.dao.DeveloperDao;
import openones.oopms.projecteye.dao.ProjectDao;
import openones.oopms.projecteye.dao.RiskDao;
import openones.oopms.projecteye.form.CreateProjectForm;
import openones.oopms.projecteye.form.ProjectEyeHomeForm;
import openones.oopms.projecteye.model.BusinessDomain;
import openones.oopms.projecteye.model.ChangesOfProjectPlan;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.GeneralReference;
import openones.oopms.projecteye.model.Project;
import openones.oopms.projecteye.model.Risk;
import openones.portlet.PortletSupport;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 * @author HaiTCT
 */
@Controller
@RequestMapping("VIEW")
public class ProjectEyeHomeController {

	/** Logger for logging. */
	private static Logger log = Logger
			.getLogger(ProjectEyeHomeController.class);
	public static String username;

	/**
	 * Default screen.
	 * 
	 * @return name of view which is the name of the JSP page.
	 */
	@RequestMapping
	public String initScreen(RenderRequest request) {
		log.debug("initScreen.START conme");
		PortletSupport portletSupport = new PortletSupport(request);
		username = portletSupport.getLogonUser();
		ProjectDao pDao = new ProjectDao();
		DeveloperDao dDao = new DeveloperDao();
		Developer dev = dDao.getDeveloper(username);
		List<Project> projectList = pDao.getProjectList(dev.getDeveloperId());
		request.setAttribute("projectList", projectList);
		return "ProjectEyeHome";

	}

	/**
	 * Create bean for form.
	 * 
	 * @return Form bean for UI.
	 */
	@ModelAttribute("ProjectEyeHomeForm")
	public ProjectEyeHomeForm getCommandObject() {
		log.debug("CreateBean for Home JSP");

		ProjectEyeHomeForm formBean = new ProjectEyeHomeForm();
		return formBean;
	}

	/**
	 * Process after the action "Create New Project"
	 * 
	 * @return view "CreateProject" which next page "CreateProject.jsp" will
	 *         displayed
	 */
	@RenderMapping(params = "action=GoCreateProject")
	public ModelAndView postCreateProject(CreateProjectForm formBean,
			RenderRequest request) {
		log.debug("post GoCreateProject.START");
		ProjectDao pDao = new ProjectDao();
		// get category List
		List<GeneralReference> projectCategoryList = pDao
				.getProjectCategoryList();
		Map<String, String> projectCategoryMap = new LinkedHashMap<String, String>();
		for (int i = 0; i < projectCategoryList.size(); i++) {
			projectCategoryMap.put(projectCategoryList.get(i).getGeneralRefId()
					.toString(), projectCategoryList.get(i).getDescription());
		}
		// get Status list
		List<GeneralReference> projectStatusList = pDao.getProjectStatusList();
		Map<String, String> projectStatusMap = new LinkedHashMap<String, String>();
		for (int i = 0; i < projectStatusList.size(); i++) {
			projectStatusMap.put(projectStatusList.get(i).getGeneralRefId()
					.toString(), projectStatusList.get(i).getDescription());
		}

		// get Bussiness domain list
		List<BusinessDomain> projectBussinessDomainList = pDao
				.getProjectBussinessDomainList();
		Map<String, String> projectBussinessDomainMap = new LinkedHashMap<String, String>();
		for (int i = 0; i < projectBussinessDomainList.size(); i++) {
			projectBussinessDomainMap.put(projectBussinessDomainList.get(i)
					.getDomainId().toString(), projectBussinessDomainList
					.get(i).getDomainName());
		}
		request.setAttribute("CreateProjectForm", formBean);
		ModelAndView mav = new ModelAndView("CreateProject");
		mav.addObject("projectStatus", projectStatusMap);
		mav.addObject("projectCategory", projectCategoryMap);
		mav.addObject("businessDomain", projectBussinessDomainMap);
		mav.addObject("username", username);
		return mav;
	}

	@RenderMapping(params = "action=GoProjectDetail")
	public ModelAndView postProjectDetail(RenderRequest request) {
		log.debug("postProjectDetail.START");
		String projectId = request.getParameter("projectId");
		ProjectDao pDao = new ProjectDao();
		Project project = pDao.getProject(projectId);
		// Get project Manager Name
		DeveloperDao dDao = new DeveloperDao();
		Developer developer = dDao.getProjectManager(project);
		List<Developer> projectTeam = dDao.getDeveloperTeamOfProject(project);
		RiskDao rDao = new RiskDao();
		List<Risk> projectRiskList = rDao.getProjectRiskList(project);
		ChangeRequestDao crDao = new ChangeRequestDao();
		List<ChangesOfProjectPlan> projectChangeRequestList = crDao
				.getProjectChangeRequestList(project);
		ModelAndView mav = new ModelAndView("ProjectDetail");
		mav.addObject("projectManager", developer.getName());
		if (project.getProjectStatusCode() != null) {
			mav.addObject("projectStatus",
					pDao.getProjectStatus(project.getProjectStatusCode())
							.getDescription());
		}
		mav.addObject("projectCode", project.getCode());
		mav.addObject("projectName", project.getName());
		if (project.getProjectCategoryCode() != null) {
			mav.addObject("projectCategory",
					pDao.getProjectCategory(project.getProjectCategoryCode())
							.getDescription());
		}
		mav.addObject("directCustomer", project.getCustomer());
		mav.addObject("endCustomer", project.getCustomer2nd());
		if (project.getType() != null) {
			mav.addObject("businessDomain",
					pDao.getProjectBussinessDomain(project.getType())
							.getDomainName());
		}
		mav.addObject("plannedStartDate", project.getPlanStartDate());
		mav.addObject("plannedEndDate", project.getPlanFinishDate());
		mav.addObject("scopeAndObjective", project.getDescription());
		mav.addObject("projectSize", projectTeam.size());
		mav.addObject("riskIssue", projectRiskList.size());
		mav.addObject("changeRequest", projectChangeRequestList.size());
		// mav.addObject("planEffort", project);
		// mav.addObject("actualEffort", project);
		// mav.addObject("progress", project);
		// mav.addObject("cost", project);
		// mav.addObject("projectHealth", project);
		// mav.addObject("projectEvaluation", project);

		log.debug("project ID la " + projectId);
		mav.addObject("projectId", projectId);
		return mav;
	}
}
