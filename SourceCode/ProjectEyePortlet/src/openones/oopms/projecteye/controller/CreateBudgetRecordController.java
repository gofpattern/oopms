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

import openones.oopms.projecteye.dao.CostDao;
import openones.oopms.projecteye.form.CreateBudgetRecordForm;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.OopmsBudget;
import openones.oopms.projecteye.model.OopmsProjectCost;
import openones.oopms.projecteye.utils.Constant;
import openones.oopms.projecteye.utils.CostUtil;

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
public class CreateBudgetRecordController {

	Developer user = new Developer();
	/** Logger for logging. */
	private static Logger log = Logger
			.getLogger(CreateBudgetRecordController.class);
	String projectId;

	@ActionMapping(params = "action=CreateBudgetRecord")
	public void processCreateBudgetRecord(CreateBudgetRecordForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("process CreateBudgetRecord.START");
		String projectId = formBean.getProjectId();
		CostDao cDao = new CostDao();
		OopmsBudget budgetRecord = new OopmsBudget();
		budgetRecord.setProjectId(new BigDecimal(projectId));
		budgetRecord.setValue(new BigDecimal(formBean.getValue()));
		budgetRecord.setType(formBean.getBudgetType());
		budgetRecord.setDescription(formBean.getDescription());
		// Call dao to insert project to database
		if (cDao.insertBudgetRecord(budgetRecord)) {
			OopmsProjectCost projectCost = cDao.getProjectCost(new BigDecimal(
					projectId));
			if (projectCost != null) {
				if (projectCost.getCurrentBudget() != null) {
					if (formBean.getBudgetType().equals(
							Constant.BudgetIncreaseType)) {
						projectCost
								.setCurrentBudget(new BigDecimal(formBean
										.getValue()).add(projectCost
										.getCurrentBudget()));
					} else {
						projectCost.setCurrentBudget(projectCost
								.getCurrentBudget().subtract(
										new BigDecimal(formBean.getValue())));
					}
				} else {
					if (formBean.getBudgetType().equals(
							Constant.BudgetIncreaseType)) {
						projectCost.setCurrentBudget(new BigDecimal(formBean
								.getValue()));
					} else {
						projectCost.setCurrentBudget(new BigDecimal("-"
								+ formBean.getValue()));
					}
				}
				projectCost.setCostStatus(CostUtil.getProjectCostStatus(
						projectId, projectCost.getCurrentBudget()));
				cDao.updateProjectCost(projectCost);
				response.setRenderParameter("costStatus", projectCost.getCostStatus());
			} else {
				OopmsProjectCost projectCost2 = new OopmsProjectCost();
				projectCost2.setProjectId(new BigDecimal(projectId));
				projectCost2.setCostStatus("1");
				if (formBean.getBudgetType()
						.equals(Constant.BudgetIncreaseType)) {
					projectCost2.setCurrentBudget(new BigDecimal(formBean
							.getValue()));
				} else {
					projectCost2.setCurrentBudget(new BigDecimal("-"
							+ formBean.getValue()));
				}
				projectCost2.setCurrentExpense(new BigDecimal("0"));
				projectCost2.setCostStatus(CostUtil.getProjectCostStatus(
						projectId, projectCost2.getCurrentBudget()));
				cDao.insertProjectCost(projectCost2);
				response.setRenderParameter("costStatus", projectCost2.getCostStatus());
			}
			response.setRenderParameter("action", "GoCostManagement");
			response.setRenderParameter("projectId", projectId);
			log.error("Insert success");
		} else {
			log.error("Cannot Insert");
		}

	}

}
