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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import openones.oopms.projecteye.dao.CostDao;
import openones.oopms.projecteye.form.CostManagementForm;
import openones.oopms.projecteye.form.CreateBudgetRecordForm;
import openones.oopms.projecteye.form.CreateCostTypeForm;
import openones.oopms.projecteye.form.CreateDailyExpenseForm;
import openones.oopms.projecteye.form.CreateExceptionalDeductForm;
import openones.oopms.projecteye.form.CreateExceptionalExpenseForm;
import openones.oopms.projecteye.form.CreateOneTimeExpenseForm;
import openones.oopms.projecteye.form.DailyExpense;
import openones.oopms.projecteye.form.DeleteCostForm;
import openones.oopms.projecteye.form.InvoiceDailyExpense;
import openones.oopms.projecteye.form.PayCostForm;
import openones.oopms.projecteye.form.UpdateBudgetRecordForm;
import openones.oopms.projecteye.form.UpdateCostTypeForm;
import openones.oopms.projecteye.form.UpdateDailyExpenseForm;
import openones.oopms.projecteye.form.UpdateExceptionalDeductForm;
import openones.oopms.projecteye.form.UpdateExceptionalExpenseForm;
import openones.oopms.projecteye.form.UpdateOneTimeExpenseForm;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.OopmsBudget;
import openones.oopms.projecteye.model.OopmsCostDailyExpense;
import openones.oopms.projecteye.model.OopmsCostOneTimeExpense;
import openones.oopms.projecteye.model.OopmsCostType;
import openones.oopms.projecteye.model.OopmsExceptionalCost;
import openones.oopms.projecteye.model.OopmsProjectCost;
import openones.oopms.projecteye.utils.AppUtil;
import openones.oopms.projecteye.utils.Constant;
import openones.oopms.projecteye.utils.CostUtil;
import openones.oopms.projecteye.utils.HTMLTag;

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
public class CostManagementController {

	Developer user = new Developer();
	/** Logger for logging. */
	private static Logger log = Logger
			.getLogger(CostManagementController.class);

	@RenderMapping(params = "action=GoCreateOneTimeExpense")
	public ModelAndView postGoCreateOneTimeExpense(RenderRequest request) {
		log.debug("post GoCreateOneTimeExpense.START");
		ModelAndView mav = new ModelAndView("CreateOneTimeExpense",
				"CreateOneTimeExpenseForm", new CreateOneTimeExpenseForm());
		String projectId = request.getParameter("projectId");
		log.debug("project ID : " + projectId);
		mav.addObject("projectId", projectId);
		return mav;
	}

	@RenderMapping(params = "action=GoCreateDailyExpense")
	public ModelAndView postGoCreateDailyExpense(RenderRequest request) {
		log.debug("post GoCreateDailyExpense.START");
		String projectId = request.getParameter("projectId");
		ModelAndView mav = new ModelAndView("CreateDailyExpense",
				"CreateDailyExpenseForm", new CreateDailyExpenseForm());
		CostDao cDao = new CostDao();
		List<OopmsCostType> costTypeList = cDao.getCostTypeList(projectId);
		Map<String, String> costTypeMap = new LinkedHashMap<String, String>();
		costTypeMap.put(null, " ");
		if (costTypeList != null) {
			for (int i = 0; i < costTypeList.size(); i++) {
				costTypeMap.put(costTypeList.get(i).getOopmsCostTypeId()
						.toString(), costTypeList.get(i).getName());
			}
		}
		log.debug("project ID : " + projectId);
		mav.addObject("projectId", projectId);
		mav.addObject("costType", costTypeMap);
		return mav;
	}

	@RenderMapping(params = "action=GoCreateExceptionalExpense")
	public ModelAndView postGoCreateExceptionalExpense(RenderRequest request) {
		log.debug("post GoCreateExceptionalExpense.START");
		CreateExceptionalExpenseForm form = new CreateExceptionalExpenseForm();
		form.setAffectTo(Constant.ExceptinalCostEffectToType);
		form.setAdditionEffect(Constant.ExceptinalRationCostEffectType);
		ModelAndView mav = new ModelAndView("CreateExceptionalExpense",
				"CreateExceptionalExpenseForm", form);
		String projectId = request.getParameter("projectId");
		CostDao cDao = new CostDao();
		List<OopmsCostType> costTypeList = cDao.getCostTypeList(projectId);
		List<OopmsCostDailyExpense> dailyExpenseList = cDao
				.getDailyExpensePlanList(projectId);
		List<DailyExpense> dailyExpenseListView = CostUtil
				.getDailyExpenseListView(dailyExpenseList);
		mav.addObject("CostTypeList", costTypeList);
		mav.addObject("DailyExpenseList", dailyExpenseListView);
		log.debug("project ID : " + projectId);
		mav.addObject("projectId", projectId);
		return mav;
	}

	@RenderMapping(params = "action=GoCreateExceptionalDeduct")
	public ModelAndView postGoCreateExceptionalDeduct(RenderRequest request) {
		log.debug("post GoCreateExceptionalDeduct.START");
		CreateExceptionalDeductForm form = new CreateExceptionalDeductForm();
		form.setAffectTo(Constant.ExceptinalCostEffectToType);
		form.setAdditionEffect(Constant.ExceptinalRationCostEffectType);
		ModelAndView mav = new ModelAndView("CreateExceptionalDeduct",
				"CreateExceptionalDeductForm", form);
		String projectId = request.getParameter("projectId");
		CostDao cDao = new CostDao();
		List<OopmsCostType> costTypeList = cDao.getCostTypeList(projectId);
		List<OopmsCostDailyExpense> dailyExpenseList = cDao
				.getDailyExpensePlanList(projectId);
		List<DailyExpense> dailyExpenseListView = CostUtil
				.getDailyExpenseListView(dailyExpenseList);
		mav.addObject("CostTypeList", costTypeList);
		mav.addObject("DailyExpenseList", dailyExpenseListView);
		log.debug("project ID : " + projectId);
		mav.addObject("projectId", projectId);
		return mav;
	}

	@RenderMapping(params = "action=GoCreateCostType")
	public ModelAndView postGoCreateCostType(RenderRequest request) {
		log.debug("post GoCreateOneTimeExpense.START");
		ModelAndView mav = new ModelAndView("CreateCostType",
				"CreateCostTypeForm", new CreateCostTypeForm());
		String projectId = request.getParameter("projectId");
		log.debug("project ID : " + projectId);
		mav.addObject("projectId", projectId);
		return mav;
	}

	@ActionMapping(params = "action=ViewExpense")
	public void processViewExpense(CostManagementForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("post ViewExpense.START");
		response.setRenderParameter("action", "GoCostManagement");
		response.setRenderParameter("projectId", formBean.getProjectId());
		response.setRenderParameter("viewDate", formBean.getViewDate());
	}

	@ActionMapping(params = "action=RemoveOneTimeExpense")
	public void processRemoveOneTimeExpense(DeleteCostForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("post RemoveOneTimeExpense.START");
		CostDao cDao = new CostDao();
		cDao.deleteOneTimeExpense(formBean.getOopmsCostOneTimeExpenseId());
		OopmsProjectCost projectCost = cDao.getProjectCost(new BigDecimal(
				formBean.getProjectId()));
		projectCost.setCostStatus(CostUtil.getProjectCostStatus(
				formBean.getProjectId(), projectCost.getCurrentBudget()));
		cDao.updateProjectCost(projectCost);
		response.setRenderParameter("action", "GoCostManagement");
		response.setRenderParameter("projectId", formBean.getProjectId());
	}

	@ActionMapping(params = "action=RemoveDailyExpense")
	public void processRemoveDailyExpense(DeleteCostForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("post RemoveDailyExpense.START");
		CostDao cDao = new CostDao();
		String dailyExpenseUsed = cDao.checkDailyExpenseUsed(formBean
				.getOopmsCostDailyExpenseId());
		if (Constant.DailyExpenseNotUsed.equals(dailyExpenseUsed)) {
			cDao.deleteDailyExpense(formBean.getOopmsCostDailyExpenseId());
			OopmsProjectCost projectCost = cDao.getProjectCost(new BigDecimal(
					formBean.getProjectId()));
			projectCost.setCostStatus(CostUtil.getProjectCostStatus(
					formBean.getProjectId(), projectCost.getCurrentBudget()));
			cDao.updateProjectCost(projectCost);
		} else {
			response.setRenderParameter("deleteDailyFlag", dailyExpenseUsed);
			response.setRenderParameter("deletingOopmsCostDailyExpenseId",
					formBean.getOopmsCostDailyExpenseId());
		}

		response.setRenderParameter("action", "GoCostManagement");
		response.setRenderParameter("projectId", formBean.getProjectId());
	}

	@ActionMapping(params = "action=ForcedRemoveDailyExpense")
	public void processForcedRemoveDailyExpense(DeleteCostForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("post ForcedRemoveDailyExpense.START");
		CostDao cDao = new CostDao();

		cDao.forcedDeleteDailyExpense(formBean.getOopmsCostDailyExpenseId());
		OopmsProjectCost projectCost = cDao.getProjectCost(new BigDecimal(
				formBean.getProjectId()));
		projectCost.setCostStatus(CostUtil.getProjectCostStatus(
				formBean.getProjectId(), projectCost.getCurrentBudget()));
		cDao.updateProjectCost(projectCost);
		response.setRenderParameter("action", "GoCostManagement");
		response.setRenderParameter("projectId", formBean.getProjectId());
	}

	@ActionMapping(params = "action=RemoveExceptionalCost")
	public void processRemoveExceptionalCost(DeleteCostForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("post RemoveExceptionalCost.START");
		CostDao cDao = new CostDao();
		cDao.deleteExceptionalCost(formBean.getOopmsExceptionalCostId());
		OopmsProjectCost projectCost = cDao.getProjectCost(new BigDecimal(
				formBean.getProjectId()));
		projectCost.setCostStatus(CostUtil.getProjectCostStatus(
				formBean.getProjectId(), projectCost.getCurrentBudget()));
		cDao.updateProjectCost(projectCost);
		response.setRenderParameter("action", "GoCostManagement");
		response.setRenderParameter("projectId", formBean.getProjectId());
	}

	@ActionMapping(params = "action=RemoveCostType")
	public void processRemoveCostType(DeleteCostForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("post RemoveCostType.START");
		CostDao cDao = new CostDao();
		String costTypeUsed = cDao.checkCostTypeUsed(formBean
				.getOopmsCostTypeId());
		log.debug("costTypeUsed : " + costTypeUsed);
		if (Constant.CostTypeNotUsed.equals(costTypeUsed)) {
			cDao.deleteCostType(formBean.getOopmsCostTypeId());
		} else {
			response.setRenderParameter("deleteCostTypeFlag", costTypeUsed);
			response.setRenderParameter("deletingOopmsCostTypeId",
					formBean.getOopmsCostTypeId());
		}
		response.setRenderParameter("action", "GoCostManagement");
		response.setRenderParameter("projectId", formBean.getProjectId());
	}

	@ActionMapping(params = "action=ForcedRemoveCostType")
	public void processForcedRemoveCostType(DeleteCostForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("post ForcedRemoveCostType.START");
		CostDao cDao = new CostDao();
		cDao.forcedDeleteCostType(formBean.getOopmsCostTypeId());
		OopmsProjectCost projectCost = cDao.getProjectCost(new BigDecimal(
				formBean.getProjectId()));
		projectCost.setCostStatus(CostUtil.getProjectCostStatus(
				formBean.getProjectId(), projectCost.getCurrentBudget()));
		cDao.updateProjectCost(projectCost);
		response.setRenderParameter("deleteCostTypeFlag",
				Constant.CostTypeNotUsed);
		response.setRenderParameter("action", "GoCostManagement");
		response.setRenderParameter("projectId", formBean.getProjectId());
	}

	@RenderMapping(params = "action=GoUpdateOneTimeExpense")
	public ModelAndView postGoUpdateOneTimeExpense(RenderRequest request) {
		log.debug("post GoUpdateOneTimeExpense.START");
		String oopmsCostOneTimeExpenseId = request
				.getParameter("oopmsCostOneTimeExpenseId");
		CostDao cDao = new CostDao();
		OopmsCostOneTimeExpense oneTimeExpense = cDao
				.getOneTimeExpense(oopmsCostOneTimeExpenseId);
		UpdateOneTimeExpenseForm form = new UpdateOneTimeExpenseForm();
		form.setCost(String.valueOf(oneTimeExpense.getCost()));
		form.setName(oneTimeExpense.getName());
		form.setDescription(HTMLTag.replaceHTMLTag(oneTimeExpense
				.getDescription()));
		form.setDate(AppUtil.getDateAsFormat(oneTimeExpense.getOccurDate(),
				Constant.DateFormat));
		ModelAndView mav = new ModelAndView("UpdateOneTimeExpense",
				"UpdateOneTimeExpenseForm", form);
		String projectId = request.getParameter("projectId");
		log.debug("project ID : " + projectId);
		mav.addObject("projectId", projectId);
		mav.addObject("oopmsCostOneTimeExpenseId", oopmsCostOneTimeExpenseId);
		return mav;
	}

	@RenderMapping(params = "action=GoUpdateDailyExpense")
	public ModelAndView postGoUpdateDailyExpense(RenderRequest request) {
		log.debug("post GoUpdateDailyExpense.START");
		String oopmsCostDailyExpenseId = request
				.getParameter("oopmsCostDailyExpenseId");
		CostDao cDao = new CostDao();
		OopmsCostDailyExpense dailyExpense = cDao
				.getDailyExpense(new BigDecimal(oopmsCostDailyExpenseId));
		UpdateDailyExpenseForm form = new UpdateDailyExpenseForm();
		form.setCost(String.valueOf(dailyExpense.getCost()));
		form.setName(dailyExpense.getName());
		form.setDescription(HTMLTag.replaceHTMLTag(dailyExpense
				.getDescription()));
		form.setStartDate(AppUtil.getDateAsFormat(dailyExpense.getStartDate(),
				Constant.DateFormat));
		form.setEndDate(AppUtil.getDateAsFormat(dailyExpense.getEndDate(),
				Constant.DateFormat));
		if (dailyExpense.getOopmsCostTypeId() != null) {
			form.setCostType_SelectedValue(String.valueOf(dailyExpense
					.getOopmsCostTypeId()));
		}
		form.setDays(CostUtil.getDaysUsed(dailyExpense.getDateUsed()));
		ModelAndView mav = new ModelAndView("UpdateDailyExpense",
				"UpdateDailyExpenseForm", form);
		String projectId = request.getParameter("projectId");
		List<OopmsCostType> costTypeList = cDao.getCostTypeList(projectId);
		Map<String, String> costTypeMap = new LinkedHashMap<String, String>();
		costTypeMap.put(null, " ");
		if (costTypeList != null) {
			for (int i = 0; i < costTypeList.size(); i++) {
				costTypeMap.put(costTypeList.get(i).getOopmsCostTypeId()
						.toString(), costTypeList.get(i).getName());
			}
		}
		log.debug("project ID : " + projectId);
		mav.addObject("projectId", projectId);
		mav.addObject("oopmsCostDailyExpenseId", oopmsCostDailyExpenseId);
		mav.addObject("costType", costTypeMap);
		return mav;
	}

	@RenderMapping(params = "action=GoUpdateCostType")
	public ModelAndView postGoUpdateCostType(RenderRequest request) {
		log.debug("post GoUpdateCostType.START");
		String oopmsCostTypeId = request.getParameter("oopmsCostTypeId");
		UpdateCostTypeForm form = new UpdateCostTypeForm();
		CostDao cDao = new CostDao();
		OopmsCostType costType = cDao.getCostType(new BigDecimal(
				oopmsCostTypeId));
		form.setName(costType.getName());
		form.setDescription(HTMLTag.replaceHTMLTag(costType.getDescription()));
		ModelAndView mav = new ModelAndView("UpdateCostType",
				"UpdateCostTypeForm", form);
		String projectId = request.getParameter("projectId");
		log.debug("project ID : " + projectId);
		mav.addObject("oopmsCostTypeId", oopmsCostTypeId);
		mav.addObject("projectId", projectId);
		return mav;
	}

	@RenderMapping(params = "action=GoCreateBudgetRecords")
	public ModelAndView postGoCreateBudgetRecords(RenderRequest request) {
		log.debug("post GoCreateBudgetRecords.START");
		CreateBudgetRecordForm form = new CreateBudgetRecordForm();
		form.setBudgetType(Constant.BudgetIncreaseType);
		ModelAndView mav = new ModelAndView("CreateBudgetRecord",
				"CreateBudgetRecordForm", form);
		String projectId = request.getParameter("projectId");
		log.debug("project ID : " + projectId);
		mav.addObject("projectId", projectId);
		return mav;
	}

	@RenderMapping(params = "action=GoUpdateBudgetRecords")
	public ModelAndView postGoUpdateBudgetRecords(RenderRequest request) {
		log.debug("post GoUpdateBudgetRecords.START");
		CostDao cDao = new CostDao();
		OopmsBudget budget = cDao.getBudgetRecord(request
				.getParameter("oopmsBudgetId"));
		UpdateBudgetRecordForm form = new UpdateBudgetRecordForm();
		form.setBudgetType(budget.getType());
		form.setDescription(HTMLTag.replaceHTMLTag(budget.getDescription()));
		form.setValue(String.valueOf(budget.getValue()));
		ModelAndView mav = new ModelAndView("UpdateBudgetRecord",
				"UpdateBudgetRecordForm", form);
		String projectId = request.getParameter("projectId");
		log.debug("project ID : " + projectId);
		mav.addObject("projectId", projectId);
		mav.addObject("oopmsBudgetId", request.getParameter("oopmsBudgetId"));
		return mav;
	}

	@ActionMapping(params = "action=RemoveBudgetRecord")
	public void processRemoveBudgetRecord(DeleteCostForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("post RemoveBudgetRecord.START");
		String projectId = formBean.getProjectId();
		CostDao cDao = new CostDao();
		cDao.deleteBudgetRecord(formBean.getOopmsBudgetId());
		// set value for projectCost
		BigDecimal oldValue = new BigDecimal(formBean.getBudgetValue());
		String oldType = formBean.getBudgetType();
		OopmsProjectCost projectCost = cDao.getProjectCost(new BigDecimal(
				projectId));
		if (oldType.equals(Constant.BudgetIncreaseType)) {
			projectCost.setCurrentBudget(projectCost.getCurrentBudget()
					.subtract(oldValue));
		} else {
			projectCost.setCurrentBudget(oldValue.add(projectCost
					.getCurrentBudget()));
		}
		projectCost.setCostStatus(CostUtil.getProjectCostStatus(projectId,
				projectCost.getCurrentBudget()));
		cDao.updateProjectCost(projectCost);

		response.setRenderParameter("action", "GoCostManagement");
		response.setRenderParameter("ViewBudgetRecord", "ViewBudgetRecord");
		response.setRenderParameter("projectId", projectId);
	}

	@RenderMapping(params = "action=GoUpdateExceptinalExpense")
	public ModelAndView postGoUpdateExceptinalExpense(RenderRequest request) {
		log.debug("post GoUpdateExceptinalExpense.START");
		UpdateExceptionalExpenseForm form = new UpdateExceptionalExpenseForm();
		String oopmsExceptionalCostId = request
				.getParameter("oopmsExceptionalCostId");
		CostDao cDao = new CostDao();
		OopmsExceptionalCost exceptionalCost = cDao
				.getExceptionalCost(oopmsExceptionalCostId);
		// set value for form
		form.setName(exceptionalCost.getName());
		form.setOccurDate(AppUtil.getDateAsFormat(
				exceptionalCost.getOccurDate(), Constant.DateFormat));
		form.setDescription(HTMLTag.replaceHTMLTag(exceptionalCost
				.getDescription()));
		form.setAdditionEffect(String.valueOf(exceptionalCost.getEffectType()));
		form.setAdditionEffectInput(String.valueOf(exceptionalCost.getEffect()));
		if (exceptionalCost.getOopmsCostTypeId() != null) {
			String[] costTypes = new String[1];
			costTypes[0] = String.valueOf(exceptionalCost.getOopmsCostTypeId());
			form.setCostTypes(costTypes);
			form.setAffectTo(Constant.ExceptinalCostEffectToType);
		} else {
			String[] daily = new String[1];
			daily[0] = String.valueOf(exceptionalCost
					.getOopmsCostDailyExpenseId());
			form.setDailyExpenses(daily);
			form.setAffectTo(Constant.ExceptinalCostEffectToDaily);
		}
		// //////////
		ModelAndView mav = new ModelAndView("UpdateExceptionalExpense",
				"UpdateExceptionalExpenseForm", form);
		String projectId = request.getParameter("projectId");
		List<OopmsCostType> costTypeList = cDao.getCostTypeList(projectId);
		List<OopmsCostDailyExpense> dailyExpenseList = cDao
				.getDailyExpensePlanList(projectId);
		List<DailyExpense> dailyExpenseListView = CostUtil
				.getDailyExpenseListView(dailyExpenseList);
		mav.addObject("CostTypeList", costTypeList);
		mav.addObject("DailyExpenseList", dailyExpenseListView);
		log.debug("project ID : " + projectId);
		mav.addObject("oopmsExceptionalCostId", oopmsExceptionalCostId);
		mav.addObject("projectId", projectId);
		return mav;
	}

	@RenderMapping(params = "action=GoUpdateExceptinalDeduct")
	public ModelAndView postGoUpdateExceptinalDeduct(RenderRequest request) {
		log.debug("post GoUpdateExceptinalDeduct.START");
		UpdateExceptionalDeductForm form = new UpdateExceptionalDeductForm();
		String oopmsExceptionalCostId = request
				.getParameter("oopmsExceptionalCostId");
		CostDao cDao = new CostDao();
		OopmsExceptionalCost exceptionalCost = cDao
				.getExceptionalCost(oopmsExceptionalCostId);
		// set value for form
		form.setName(exceptionalCost.getName());
		form.setOccurDate(AppUtil.getDateAsFormat(
				exceptionalCost.getOccurDate(), Constant.DateFormat));
		form.setDescription(HTMLTag.replaceHTMLTag(exceptionalCost
				.getDescription()));
		form.setAdditionEffect(String.valueOf(exceptionalCost.getEffectType()));
		form.setAdditionEffectInput(String.valueOf(exceptionalCost.getEffect()));
		if (exceptionalCost.getOopmsCostTypeId() != null) {
			String[] costTypes = new String[1];
			costTypes[0] = String.valueOf(exceptionalCost.getOopmsCostTypeId());
			form.setCostTypes(costTypes);
			form.setAffectTo(Constant.ExceptinalCostEffectToType);
		} else {
			String[] daily = new String[1];
			daily[0] = String.valueOf(exceptionalCost
					.getOopmsCostDailyExpenseId());
			form.setDailyExpenses(daily);
			form.setAffectTo(Constant.ExceptinalCostEffectToDaily);
		}
		// //////////
		ModelAndView mav = new ModelAndView("UpdateExceptionalDeduct",
				"UpdateExceptionalDeductForm", form);
		String projectId = request.getParameter("projectId");
		List<OopmsCostType> costTypeList = cDao.getCostTypeList(projectId);
		List<OopmsCostDailyExpense> dailyExpenseList = cDao
				.getDailyExpensePlanList(projectId);
		List<DailyExpense> dailyExpenseListView = CostUtil
				.getDailyExpenseListView(dailyExpenseList);
		mav.addObject("CostTypeList", costTypeList);
		mav.addObject("DailyExpenseList", dailyExpenseListView);
		log.debug("project ID : " + projectId);
		mav.addObject("oopmsExceptionalCostId", oopmsExceptionalCostId);
		mav.addObject("projectId", projectId);
		return mav;
	}

	@ActionMapping(params = "action=PayOneTimeExpense")
	public void processPayOneTimeExpense(PayCostForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("post PayOneTimeExpense.START");
		String projectId = formBean.getProjectId();
		CostDao cDao = new CostDao();
		cDao.payOneTimeExpense(formBean.getOopmsCostOneTimeExpenseId());
		// Update invoice
		OopmsProjectCost projectCost = cDao.getProjectCost(new BigDecimal(
				projectId));
		OopmsCostOneTimeExpense oneTimeExpense = cDao
				.getOneTimeExpense(formBean.getOopmsCostOneTimeExpenseId());
		projectCost.setCurrentExpense(projectCost.getCurrentExpense().add(
				oneTimeExpense.getCost()));
		cDao.updateProjectCost(projectCost);

		response.setRenderParameter("action", "GoCostManagement");
		response.setRenderParameter("ViewInvoiceRecords", "ViewInvoiceRecords");
		response.setRenderParameter("projectId", projectId);
	}

	@ActionMapping(params = "action=PayDailyExpense")
	public void processPayDailyExpense(PayCostForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("post PayDailyExpense.START");
		String projectId = formBean.getProjectId();
		CostDao cDao = new CostDao();
		cDao.payDailyExpense(formBean.getOopmsCostDailyExpenseId(), (AppUtil
				.getDateFromFormattedDate(formBean.getPayDate(),
						Constant.DateFormat)));
		// Update invoice
		OopmsProjectCost projectCost = cDao.getProjectCost(new BigDecimal(
				projectId));
		OopmsCostDailyExpense dailyExpense = cDao
				.getDailyExpense(new BigDecimal(formBean
						.getOopmsCostDailyExpenseId()));
		InvoiceDailyExpense invoice = CostUtil
				.getInvoiceDailyExpense(dailyExpense);
		projectCost.setCurrentExpense(projectCost.getCurrentExpense().add(
				new BigDecimal(invoice.getTotal())));
		cDao.updateProjectCost(projectCost);
		response.setRenderParameter("payDate", formBean.getPayDate());
		response.setRenderParameter("action", "GoCostManagement");
		response.setRenderParameter("ViewInvoiceRecords", "ViewInvoiceRecords");
		response.setRenderParameter("projectId", projectId);
	}

	@ActionMapping(params = "action=PayExceptionalCost")
	public void processPayExceptionalCost(PayCostForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("post PayExceptionalCost.START");
		String projectId = formBean.getProjectId();
		CostDao cDao = new CostDao();
		String payExceptionalCostFlag = cDao.checkExceptionalCostUsed(
				formBean.getOopmsExceptionalCostId(), projectId);
		if ("1".equals(payExceptionalCostFlag)) {
			response.setRenderParameter("payExceptionalCostFlag",
					payExceptionalCostFlag);
		} else {
			cDao.payExceptionalCost(formBean.getOopmsExceptionalCostId());
			// Update invoice
			OopmsProjectCost projectCost = cDao.getProjectCost(new BigDecimal(
					projectId));
			OopmsExceptionalCost exceptionalCost = cDao
					.getExceptionalCost(formBean.getOopmsExceptionalCostId());
			BigDecimal invoiceValue = CostUtil
					.getTotalValueOfExceptionalCost(exceptionalCost);
			if (String.valueOf(exceptionalCost.getType()).equals(
					Constant.ExceptinalExpenseType)) {
				projectCost.setCurrentExpense(projectCost.getCurrentExpense()
						.add(invoiceValue));
			} else {
				projectCost.setCurrentExpense(projectCost.getCurrentExpense()
						.subtract(invoiceValue));
			}

			cDao.updateProjectCost(projectCost);
			response.setRenderParameter("ViewInvoiceRecords",
					"ViewInvoiceRecords");
		}
		response.setRenderParameter("action", "GoCostManagement");
		response.setRenderParameter("projectId", projectId);
	}
}
	
