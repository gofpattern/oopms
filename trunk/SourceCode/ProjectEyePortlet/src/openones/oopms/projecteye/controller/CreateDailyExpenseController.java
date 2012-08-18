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
import openones.oopms.projecteye.form.CreateDailyExpenseForm;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.OopmsCostDailyExpense;
import openones.oopms.projecteye.model.OopmsCostType;
import openones.oopms.projecteye.model.OopmsProjectCost;
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
public class CreateDailyExpenseController {

	Developer user = new Developer();
	/** Logger for logging. */
	private static Logger log = Logger
			.getLogger(CreateDailyExpenseController.class);
	String projectId;

	@ActionMapping(params = "action=CreateDailyExpense")
	public void processCreateDailyExpense(CreateDailyExpenseForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("process CreateDailyExpense.START");
		String projectId = formBean.getProjectId();
		CostDao cDao = new CostDao();
		OopmsCostDailyExpense dailyExpense = new OopmsCostDailyExpense();
		dailyExpense.setName(formBean.getName());
		dailyExpense.setProjectId(new BigDecimal(projectId));
		dailyExpense.setStartDate(formBean.getStartDate());
		dailyExpense.setEndDate(formBean.getEndDate());
		dailyExpense.setCost(new BigDecimal(formBean.getCost()));
		dailyExpense.setDateUsed(CostUtil.getDaysUsed(formBean.getDays()));
		dailyExpense.setDescription(formBean.getDescription());
		if (!formBean.getCostType_SelectedValue().equals("")) {
			dailyExpense.setOopmsCostTypeId(new BigDecimal(formBean
					.getCostType_SelectedValue()));
		}
		// Call dao to insert project to database
		if (cDao.insertDailyExpense(dailyExpense)) {
			OopmsProjectCost projectCost = cDao.getProjectCost(new BigDecimal(
					projectId));
			projectCost.setCostStatus(CostUtil.getProjectCostStatus(projectId,
					projectCost.getCurrentBudget()));
			cDao.updateProjectCost(projectCost);
			response.setRenderParameter("action", "GoCostManagement");
			response.setRenderParameter("projectId", projectId);
			log.error("Insert success");
		} else {
			log.error("Cannot Insert");
		}

	}

}
