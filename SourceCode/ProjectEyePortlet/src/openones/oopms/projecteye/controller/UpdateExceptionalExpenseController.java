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
import openones.oopms.projecteye.form.UpdateExceptionalExpenseForm;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.OopmsExceptionalCost;
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
public class UpdateExceptionalExpenseController {

	Developer user = new Developer();
	/** Logger for logging. */
	private static Logger log = Logger
			.getLogger(UpdateExceptionalExpenseController.class);
	String projectId;

	@ActionMapping(params = "action=UpdateExceptionalExpense")
	public void processUpdateExceptionalExpense(
			UpdateExceptionalExpenseForm formBean, BindingResult result,
			SessionStatus status, ActionResponse response) {
		log.debug("process CreateExceptionalExpense.START");
		String projectId = formBean.getProjectId();
		String oopmsExceptionalCostId = formBean.getOopmsExceptionalCostId();
		CostDao cDao = new CostDao();
		if (formBean.getAffectTo().equals(Constant.ExceptinalCostEffectToType)) {
			if (formBean.getCostTypes() != null) {
				for (int i = 0; i < formBean.getCostTypes().length; i++) {
					OopmsExceptionalCost exceptionalExpense = new OopmsExceptionalCost();
					exceptionalExpense.setName(formBean.getName());
					exceptionalExpense.setProjectId(new BigDecimal(projectId));
					exceptionalExpense.setType(new BigDecimal(
							Constant.ExceptinalExpenseType));
					exceptionalExpense.setOccurDate(AppUtil
							.getDateFromFormattedDate(formBean.getOccurDate(),
									Constant.DateFormat));
					exceptionalExpense
							.setDescription(formBean.getDescription());
					exceptionalExpense.setEffectType(new BigDecimal(formBean
							.getAdditionEffect()));
					exceptionalExpense.setEffect(new BigDecimal(formBean
							.getAdditionEffectInput()));
					exceptionalExpense.setOopmsCostTypeId(new BigDecimal(
							formBean.getCostTypes()[i]));
					cDao.insertExceptionalCost(exceptionalExpense);
				}
			}
		} else {
			for (int i = 0; i < formBean.getDailyExpenses().length; i++) {
				OopmsExceptionalCost exceptionalExpense = new OopmsExceptionalCost();
				exceptionalExpense.setName(formBean.getName());
				exceptionalExpense.setProjectId(new BigDecimal(projectId));
				exceptionalExpense.setType(new BigDecimal(
						Constant.ExceptinalExpenseType));
				exceptionalExpense.setOccurDate(AppUtil
						.getDateFromFormattedDate(formBean.getOccurDate(),
								Constant.DateFormat));
				exceptionalExpense.setDescription(formBean.getDescription());
				exceptionalExpense.setEffectType(new BigDecimal(formBean
						.getAdditionEffect()));
				exceptionalExpense.setEffect(new BigDecimal(formBean
						.getAdditionEffectInput()));
				exceptionalExpense.setOopmsCostDailyExpenseId(new BigDecimal(
						formBean.getDailyExpenses()[i]));
				cDao.insertExceptionalCost(exceptionalExpense);
			}
		}
		// /Delete old expense
		cDao.deleteExceptionalCost(oopmsExceptionalCostId);
		OopmsProjectCost projectCost = cDao.getProjectCost(new BigDecimal(
				projectId));
		projectCost.setCostStatus(CostUtil.getProjectCostStatus(projectId,
				projectCost.getCurrentBudget()));
		cDao.updateProjectCost(projectCost);
		response.setRenderParameter("action", "GoCostManagement");
		response.setRenderParameter("projectId", projectId);
	}

}
