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
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;

import openones.oopms.projecteye.dao.ProjectDao;
import openones.oopms.projecteye.form.UpdateProjectForm;
import openones.oopms.projecteye.model.BusinessDomain;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.GeneralReference;
import openones.oopms.projecteye.model.Project;
import openones.oopms.projecteye.utils.Constant;
import openones.oopms.projecteye.validator.UpdateProjectValidator;

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
public class UpdateProjectController {

	Developer user = new Developer();
	/** Logger for logging. */
	private static Logger log = Logger.getLogger(UpdateProjectController.class);
	String error;
	UpdateProjectForm bean;
	String projectId;
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
	@ActionMapping(params = "action=UpdateProject")
	public void processUpdateProject(UpdateProjectForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("process UpdateProject.START");
		try {
			projectId = formBean.getProjectId();
			UpdateProjectValidator validator = new UpdateProjectValidator();
			error = "";
			error = validator.validate(formBean);
			bean = formBean;
			if (error.equals("")) {

				ProjectDao pDao = new ProjectDao();
				Project project = pDao.getProject(projectId);

				// set value update for project
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
				project.setProjectTypeCode(formBean
						.getBusinessDomain_SelectedValue());
				project.setProjectStatusCode(formBean
						.getProjectStatus_SelectedValue());

				// Call dao to insert project to database
				if (pDao.updateProject(project)) {
					response.setRenderParameter("action", "GoProjectDetail");
					response.setRenderParameter("projectId", projectId);
					log.error("Update success");

				} else {
					log.error("Cannot Update");
				}
			} else {
				log.error("Errors " + error);
				response.setRenderParameter("action", "GoUpdateProject2");

			}
		} catch (ParseException e) {
			log.error(e.getMessage());
		}

	}


	@RenderMapping(params = "action=GoUpdateProject2")
	public ModelAndView postGoUpdateProject2(RenderRequest request,
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
		ModelAndView mav = new ModelAndView("CreateProject","UpdateProjectForm",bean);
		mav.addObject("projectStatus", projectStatusMap);
		mav.addObject("projectCategory", projectCategoryMap);
		mav.addObject("businessDomain", projectBussinessDomainMap);
		mav.addObject("errorList", error);
        log.debug("project ID la "+ projectId);
        mav.addObject("projectId", projectId);
        return mav;
	}
}
