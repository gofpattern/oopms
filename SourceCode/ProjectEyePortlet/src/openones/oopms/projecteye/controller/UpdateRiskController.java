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

import openones.oopms.projecteye.dao.DeveloperDao;
import openones.oopms.projecteye.dao.RiskDao;
import openones.oopms.projecteye.form.UpdateRiskForm;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.Project;
import openones.oopms.projecteye.model.Risk;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

/**
 * @author HaiTCT
 */
@Controller
@RequestMapping("VIEW")
public class UpdateRiskController {

	Developer user = new Developer();
	/** Logger for logging. */
	private static Logger log = Logger.getLogger(UpdateRiskController.class);
	String projectId;

	/**
	 * Process submitted form by clicking "Add" button.
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
	@ActionMapping(params = "action=UpdateRisk")
	public void processUpdateRisk(UpdateRiskForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("processCreateRisk.START");
		String riskId = formBean.getRiskId();
		RiskDao rDao = new RiskDao();
		Risk risk = rDao.getProjectRisk(riskId);
		projectId = formBean.getProjectId();
		// set value for risk

		risk.setSourceId(new BigDecimal(formBean.getRiskSource_SelectedValue()));
		risk.setCondition(formBean.getDescription());
		risk.setProb(formBean.getProbability());
		risk.setImpactTo(new BigDecimal(formBean
				.getEstimatedImpactTo_SelectedValue()));
		risk.setUnit(new BigDecimal(formBean
				.getEstimatedImpactUnit_SelectedValue()));
		risk.setEstimatedImpact(formBean.getEstimatedImpact());
		risk.setImpact(formBean.getTotalImpact());
		risk.setRiskPriority(formBean.getRiskPriority());
		risk.setTriggerName(formBean.getTrigger());
		// Call dao to insert risk to database
		if (rDao.updateRisk(risk)) {
			response.setRenderParameter("action", "GoRiskIssue");
			response.setRenderParameter("projectId", projectId);
			log.error("Update success");
		} else {
			log.error("Cannot Update");
		}

	}

}
