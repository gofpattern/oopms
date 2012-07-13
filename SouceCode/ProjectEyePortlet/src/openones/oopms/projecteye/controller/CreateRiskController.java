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
import java.util.LinkedHashMap;
import java.util.Map;

import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import openones.oopms.projecteye.dao.RiskDao;
import openones.oopms.projecteye.form.CreateRiskForm;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.Project;
import openones.oopms.projecteye.model.Risk;
import openones.oopms.projecteye.model.RiskSource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 * @author Thach.Le
 */
@Controller
@RequestMapping("VIEW")
public class CreateRiskController {

	Developer user = new Developer();
	/** Logger for logging. */
	private static Logger log = Logger.getLogger(CreateRiskController.class);

	/**
	 * Create bean for form.
	 * 
	 * @return Form bean for UI.
	 */
	@ModelAttribute("CreateRiskForm")
	public CreateRiskForm getCommandObject() {
		log.debug("CreateBean CreateRiskForm cc");
		CreateRiskForm formBean = new CreateRiskForm();
		RiskDao rDao = new RiskDao();
		ArrayList<RiskSource> riskSource = rDao.getRiskSourceList();
		Map<String, String> riskSourcetMap = new LinkedHashMap<String, String>();
		riskSourcetMap.put(" ", " ");
		for (int i = 0; i < riskSource.size(); i++) {
			riskSourcetMap.put(riskSource.get(i).getSourceId().toString(),
					riskSource.get(i).getSourceName());
		}
		formBean.setRiskSource(riskSourcetMap);
		formBean.setRiskSource_SelectedValue(" ");
		return formBean;
	}

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
	@ActionMapping(params = "action=createRisk")
	public void processCreateRisk(CreateRiskForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("processCreateRisk.START");
		if (formBean.getButtonAction().equals("Submid")) {
			RiskDao rDao = new RiskDao();
			Risk risk = new Risk();

			// set value for risk
			Project project = new Project();
			project.setProjectId(new BigDecimal("1"));
			risk.setProject(project);
			
			risk.setSourceId(new BigDecimal(formBean.getRiskSource_SelectedValue()));
			risk.setCondition(formBean.getDescription());
			risk.setProb(formBean.getProbability());
			risk.setImpactTo(new BigDecimal("1"));
			risk.setUnit(new BigDecimal("1"));
			risk.setEstimatedImpact(formBean.getEstimatedImpact());
			risk.setImpact(formBean.getTotalImpact());
			risk.setRiskPriority(formBean.getRiskPriority());
			risk.setTriggerName(formBean.getTrigger());
			// Call dao to insert risk to database
			if (rDao.insertRisk(risk)) {
				response.setRenderParameter("action", "createRisk");
				log.error("Insert success");
			} else {
				log.error("Cannot Insert");
			}
		} else if (formBean.getButtonAction().equals("Cancel")) {
			response.setRenderParameter("action", "cancelCreateRisk");
		}

	}

	/**
	 * Process after the action "login" (method "processLogin") is executed.
	 * 
	 * @return view "ViewDefectList" which next page "ViewDefectList.jsp" will
	 *         displayed
	 */
	@RenderMapping(params = "action=createRisk")
	public ModelAndView postCreateRisk(CreateRiskForm formBean,
			RenderRequest request) {
		log.debug("postCreateRisk.START");
		// request.setAttribute("user2", formBean);
		ModelAndView mav = new ModelAndView("ProjectEyeHome");
		return mav;
	}

	/**
	 * Process after the action "login" (method "processLogin") is executed.
	 * 
	 * @return view "ViewDefectList" which next page "ViewDefectList.jsp" will
	 *         displayed
	 */
	@RenderMapping(params = "action=cancelCreateRisk")
	public ModelAndView postCancelCreateRisk(CreateRiskForm formBean,
			RenderRequest request) {
		log.debug("postCancelCreateRisk.START");
		// request.setAttribute("user2", formBean);
		ModelAndView mav = new ModelAndView("ProjectEyeHome");
		return mav;
	}
}
