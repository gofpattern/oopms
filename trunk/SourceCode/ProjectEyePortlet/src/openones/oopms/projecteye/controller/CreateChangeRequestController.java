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

import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import openones.oopms.projecteye.dao.ChangeRequestDao;
import openones.oopms.projecteye.form.CreateChangeRequestForm;
import openones.oopms.projecteye.model.ChangesOfProjectPlan;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.Project;

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
public class CreateChangeRequestController {

	Developer user = new Developer();
	/** Logger for logging. */
	private static Logger log = Logger
			.getLogger(CreateChangeRequestController.class);
	String projectId;

	@ActionMapping(params = "action=CreateChangeRequest")
	public void processCreateProject(CreateChangeRequestForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("process CreateProject.START");
		ChangeRequestDao crDao = new ChangeRequestDao();
		Project project = new Project();
		projectId = formBean.getProjectId();
		project.setProjectId(new BigDecimal(projectId));
		ChangesOfProjectPlan changeRequest = new ChangesOfProjectPlan();

		// set value for changeRequest
		changeRequest.setProject(project);
		changeRequest.setItem(formBean.getName());
		changeRequest.setChanges(formBean.getDescription());
		changeRequest.setVersion(formBean.getStatus_SelectedValue());
		// Call dao to insert project to database
		if (crDao.insertChangeRequest(changeRequest)) {
			response.setRenderParameter("action", "CreateChangeRequest");
			log.error("Insert success");
		} else {
			log.error("Cannot Insert");
		}

	}

	@RenderMapping(params = "action=CreateChangeRequest")
	public ModelAndView postCreateProject(RenderRequest request) {
		log.debug("post CreateChangeRequest.START");
		ModelAndView mav = new ModelAndView("ChangeRequest");
		log.debug("project ID la " + projectId);
		mav.addObject("projectId", projectId);
		return mav;
	}
}
