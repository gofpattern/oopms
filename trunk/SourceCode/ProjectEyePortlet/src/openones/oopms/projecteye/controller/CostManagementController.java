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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import openones.oopms.projecteye.dao.CostDao;
import openones.oopms.projecteye.form.CostManagementForm;
import openones.oopms.projecteye.form.CreateCostTypeForm;
import openones.oopms.projecteye.form.CreateDailyExpenseForm;
import openones.oopms.projecteye.form.CreateExceptionalDeductForm;
import openones.oopms.projecteye.form.CreateExceptionalExpenseForm;
import openones.oopms.projecteye.form.CreateOneTimeExpenseForm;
import openones.oopms.projecteye.form.CreateProjectForm;
import openones.oopms.projecteye.form.DailyExpense;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.OopmsCostDailyExpense;
import openones.oopms.projecteye.model.OopmsCostType;
import openones.oopms.projecteye.utils.Constant;
import openones.oopms.projecteye.utils.CostUtil;

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
				.getDailyExpenseList(projectId);
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
				.getDailyExpenseList(projectId);
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
}
