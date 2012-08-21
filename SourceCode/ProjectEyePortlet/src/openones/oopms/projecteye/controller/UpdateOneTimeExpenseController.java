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
import openones.oopms.projecteye.form.UpdateOneTimeExpenseForm;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.OopmsCostOneTimeExpense;
import openones.oopms.projecteye.model.OopmsProjectCost;
import openones.oopms.projecteye.utils.AppUtil;
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
public class UpdateOneTimeExpenseController {

	Developer user = new Developer();
	/** Logger for logging. */
	private static Logger log = Logger
			.getLogger(UpdateOneTimeExpenseController.class);
	String projectId;

	@ActionMapping(params = "action=UpdateOneTimeExpense")
	public void processUpdateOneTimeExpense(UpdateOneTimeExpenseForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("process UpdateOneTimeExpense.START");
		String projectId = formBean.getProjectId();
		CostDao cDao = new CostDao();
		OopmsCostOneTimeExpense oneTimeExpense = cDao
				.getOneTimeExpense(formBean.getOopmsCostOneTimeExpenseId());
		oneTimeExpense.setName(formBean.getName());
		oneTimeExpense.setCost(new BigDecimal(formBean.getCost()));
		oneTimeExpense.setOccurDate(AppUtil.getDateFromFormattedDate(
				formBean.getDate(), Constant.DateFormat));
		oneTimeExpense.setDescription(formBean.getDescription());
		// Call dao to insert project to database
		if (cDao.updateOneTimeExpense(oneTimeExpense)) {
			OopmsProjectCost projectCost = cDao.getProjectCost(new BigDecimal(
					projectId));
			projectCost.setCostStatus(CostUtil.getProjectCostStatus(projectId,
					projectCost.getCurrentBudget()));
			cDao.updateProjectCost(projectCost);
			response.setRenderParameter("action", "GoCostManagement");
			response.setRenderParameter("projectId", projectId);
			response.setRenderParameter("costStatus", projectCost.getCostStatus());
			log.error("Update success");
		} else {
			log.error("Cannot Update");
		}

	}
}
