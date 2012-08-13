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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;

import openones.oopms.projecteye.dao.AssignmentDao;
import openones.oopms.projecteye.dao.DeveloperDao;
import openones.oopms.projecteye.dao.ProjectDao;
import openones.oopms.projecteye.dao.WorkUnitDao;
import openones.oopms.projecteye.form.CreateProjectForm;
import openones.oopms.projecteye.form.ProjectEyeHomeForm;
import openones.oopms.projecteye.model.Assignment;
import openones.oopms.projecteye.model.BusinessDomain;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.GeneralReference;
import openones.oopms.projecteye.model.Project;
import openones.oopms.projecteye.model.Workunit;
import openones.oopms.projecteye.utils.Constant;
import openones.oopms.projecteye.validator.CreateProjectValidator;

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
public class CreateProjectController {

	Developer user = new Developer();
	/** Logger for logging. */
	private static Logger log = Logger.getLogger(CreateProjectController.class);
	String error;
	CreateProjectForm bean;

	/**
	 * Process submitted form by clicking "Login" button.
	 * 
	 * @param formBean
	 *            bean captures input data
	 * @param result
	 *            result of binding data
	 * @param status
	 *            status of session
	 * @param response
	 *            response of action
	 */
	@ActionMapping(params = "action=CreateProject")
	public void processCreateProject(CreateProjectForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("process CreateProject.START");
		try {
			CreateProjectValidator validator = new CreateProjectValidator();
			error = "";
			error = validator.validate(formBean);
			bean = formBean;
//			bean.setScopeObjective(HTMLTag.replaceHTMLTag(formBean.getScopeObjective()));
//			bean.setProjectCode(HTMLTag.replaceHTMLTag(formBean.getProjectCode()));
//			bean.setCustomer(HTMLTag.replaceHTMLTag(formBean.getCustomer()));
//			bean.setEndCustomer(HTMLTag.replaceHTMLTag(formBean.getEndCustomer()));
//			bean.setProjectName(HTMLTag.replaceHTMLTag(formBean.getProjectName()));		
			if (error.equals("")) {
				DeveloperDao dDao = new DeveloperDao();
				Developer dev = dDao
						.getDeveloper(ProjectEyeHomeController.username);

				ProjectDao pDao = new ProjectDao();
				Project project = new Project();

				// set value for project
				project.setType(Constant.WorkUnitProjectType);
				project.setCode(formBean.getProjectCode());
				project.setName(formBean.getProjectName());
				project.setCustomer(formBean.getCustomer());
				project.setCustomer2nd(formBean.getEndCustomer());
				DateFormat formatter;
				formatter = new SimpleDateFormat("MM/dd/yyyy");

				project.setPlanStartDate((Date) formatter.parse(formBean
						.getPlanStartDate()));

				project.setPlanFinishDate((Date) formatter.parse(formBean
						.getPlanEndDate()));
				project.setDescription(formBean.getScopeObjective());
				project.setProjectCategoryCode(formBean
						.getProjectCategory_SelectedValue());
				project.setProjectTypeCode(formBean.getBusinessDomain_SelectedValue());
				project.setProjectStatusCode(formBean
						.getProjectStatus_SelectedValue());

				Assignment assignment = new Assignment();
				// set value for assgment
				assignment.setDeveloperId(dev.getDeveloperId());
				assignment.setType(new Byte(
						Constant.ProjectOwnerAndProjectManagerType));
				assignment.setBeginDate((Date) formatter.parse(formBean
						.getPlanStartDate()));
				// Call dao to insert project to database
				if (pDao.insertProject(project, assignment)) {
					//insert work unit of project
					Workunit workunit = new Workunit();
					workunit.setType(Integer
							.parseInt(Constant.WorkUnitProjectType));
					workunit.setWorkunitname(project.getCode());
					WorkUnitDao wuDao = new WorkUnitDao();					
					if (wuDao.insertWorkUnit(workunit)) {
						response.setRenderParameter("action", "CreateProject");
						log.error("Insert success");
					} else {
						log.error("Cannot Insert WorkUnit");
					}

				} else {
					log.error("Cannot Insert");
				}
			} else {
				log.error("Errors " + error);
				response.setRenderParameter("action", "GoCreateProject2");

			}
		} catch (ParseException e) {
			log.error(e.getMessage());
		}

	}

	/**
	 * Process after the action "login" (method "processLogin") is executed.
	 * 
	 * @return view "ViewDefectList" which next page "ViewDefectList.jsp" will
	 *         displayed
	 */
	@RenderMapping(params = "action=CreateProject")
	public ModelAndView postCreateProject(CreateProjectForm formBean,
			RenderRequest request) {
		log.debug("post CreateProject.START");
		ModelAndView mav = new ModelAndView("ProjectEyeHome");
		ProjectDao pDao = new ProjectDao();
		DeveloperDao dDao = new DeveloperDao();
		Developer dev = dDao.getDeveloper(ProjectEyeHomeController.username);
		AssignmentDao aDao = new AssignmentDao();
		List<Project> projectList = pDao.getProjectList(dev.getDeveloperId());
		List<ProjectEyeHomeForm> projectRoleList = new ArrayList<ProjectEyeHomeForm>();
		for(int i=0;i<projectList.size();i++) {
			ProjectEyeHomeForm temp = new ProjectEyeHomeForm();
			temp.setProjectId(projectList.get(i).getProjectId().toString());
			temp.setCode(projectList.get(i).getCode());
			temp.setName(projectList.get(i).getName());
			Assignment role = aDao.getUserRole(projectList.get(i), dev.getDeveloperId());
			temp.setRole(String.valueOf(role.getType()));
			if(Constant.CustomerType.equals(String.valueOf(role.getType()))) {
				temp.setRoleString("Customer");
			} else if(Constant.DeveloperType.equals(String.valueOf(role.getType()))) {
				temp.setRoleString("Developer");
			} else if(Constant.ProjectManagerType.equals(String.valueOf(role.getType()))) {
				temp.setRoleString("Project Manager");
			}else if(Constant.ProjectOwnerAndProjectManagerType.equals(String.valueOf(role.getType()))) {
				temp.setRoleString("Project Owner and Project Manager");
			}else if(Constant.ProjectOwnerType.equals(String.valueOf(role.getType()))) {
				temp.setRoleString("Project Owner");
			}else if(Constant.QAType.equals(String.valueOf(role.getType()))) {
				temp.setRoleString("QA");
			} else if(Constant.TesterType.equals(String.valueOf(role.getType()))) {
				temp.setRoleString("Tester");
			}
			projectRoleList.add(temp);
		}
		request.setAttribute("projectList", projectRoleList);
		mav.addObject("projectList", projectList);
		return mav;
	}

	/**
	 * Process after the action "Create New Project"
	 * 
	 * @return view "CreateProject" which next page "CreateProject.jsp" will
	 *         displayed
	 */
	@RenderMapping(params = "action=GoCreateProject2")
	public ModelAndView postGoCreateProject(RenderRequest request,
			PortletSession session) {
		log.debug("post GoCreateProject.START");
		ProjectDao pDao = new ProjectDao();
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
		ModelAndView mav = new ModelAndView("CreateProject",
				"CreateProjectForm", bean);
		mav.addObject("projectStatus", projectStatusMap);
		mav.addObject("projectCategory", projectCategoryMap);
		mav.addObject("businessDomain", projectBussinessDomainMap);
		mav.addObject("errorList", error);
		mav.addObject("username", ProjectEyeHomeController.username);
		return mav;
	}
}
