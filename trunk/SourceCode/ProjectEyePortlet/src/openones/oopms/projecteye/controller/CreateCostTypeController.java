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
import java.util.List;
import java.util.Map;

import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import openones.oopms.projecteye.dao.CostDao;
import openones.oopms.projecteye.dao.ProductDao;
import openones.oopms.projecteye.form.CreateCostTypeForm;
import openones.oopms.projecteye.form.CreateOneTimeExpenseForm;
import openones.oopms.projecteye.form.CreateProductForm;
import openones.oopms.projecteye.form.ProductForm;
import openones.oopms.projecteye.model.Assignment;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.Language;
import openones.oopms.projecteye.model.Module;
import openones.oopms.projecteye.model.OopmsCostOneTimeExpense;
import openones.oopms.projecteye.model.OopmsCostType;
import openones.oopms.projecteye.model.Project;
import openones.oopms.projecteye.model.Workproduct;
import openones.oopms.projecteye.utils.Constant;

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
public class CreateCostTypeController {

	Developer user = new Developer();
	/** Logger for logging. */
	private static Logger log = Logger
			.getLogger(CreateCostTypeController.class);
	String projectId;

	@ActionMapping(params = "action=CreateCostType")
	public void processCreateCostType(CreateCostTypeForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("process CreateCostType.START");
		String projectId = formBean.getProjectId();
		CostDao cDao = new CostDao();
		OopmsCostType costType = new OopmsCostType();
		costType.setName(formBean.getName());
		costType.setProjectId(new BigDecimal(projectId));
		costType.setDescription(formBean.getDescription());
		// Call dao to insert project to database
		if (cDao.insertCostType(costType)) {
			response.setRenderParameter("action", "GoCostManagement");
			response.setRenderParameter("projectId", projectId);
			log.error("Insert success");
		} else {
			log.error("Cannot Insert");
		}

	}
}
