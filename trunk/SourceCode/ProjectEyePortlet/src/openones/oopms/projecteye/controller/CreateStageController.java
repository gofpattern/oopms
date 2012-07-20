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

import openones.oopms.projecteye.dao.WorkOrderDao;
import openones.oopms.projecteye.form.CreateStageForm;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.Milestone;
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
public class CreateStageController {

	Developer user = new Developer();
	/** Logger for logging. */
	private static Logger log = Logger.getLogger(CreateStageController.class);
	String projectId;

	@ActionMapping(params = "action=CreateStage")
	public void processCreateStage(CreateStageForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("process CreateStage.START");
		WorkOrderDao woDao = new WorkOrderDao();
		Project project = new Project();
		projectId = formBean.getProjectId();
		project.setProjectId(new BigDecimal(projectId));
		Milestone stage = new Milestone();

		// set value for Product
		stage.setProject(project);
		stage.setComplete(new BigDecimal("0"));
		stage.setName(formBean.getStage());
		stage.setStandardstage(new BigDecimal(formBean
				.getStandarStage_SelectedValue()));
		stage.setPlanStartDate(formBean.getPlannedStartDate());
		stage.setBaseStartDate(formBean.getRePlannedStartDate());
		stage.setActualStartDate(formBean.getActualStartDate());
		stage.setPlanFinishDate(formBean.getPlannedEndDate());
		stage.setBaseFinishDate(formBean.getRePlannedEndDate());
		stage.setActualFinishDate(formBean.getActualEndDate());
		stage.setDescription(formBean.getDescription());
		stage.setMilestone(formBean.getMilestone());
		// Call dao to insert project to database
		if (woDao.insertStage(stage)) {
			response.setRenderParameter("action", "CreateStage");
			log.error("Insert success");
		} else {
			log.error("Cannot Insert");
		}

	}

	@RenderMapping(params = "action=CreateStage")
	public ModelAndView postCreateStage(RenderRequest request) {
		log.debug("post CreateStage.START");
		ModelAndView mav = new ModelAndView("WorkOrder");
		log.debug("project ID la " + projectId);
		mav.addObject("projectId", projectId);
		return mav;
	}
}
