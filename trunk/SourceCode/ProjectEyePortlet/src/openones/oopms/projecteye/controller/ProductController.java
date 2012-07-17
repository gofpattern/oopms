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

import openones.oopms.projecteye.dao.ProductDao;
import openones.oopms.projecteye.form.CreateProductForm;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.Language;
import openones.oopms.projecteye.model.Workproduct;

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
public class ProductController {

	Developer user = new Developer();
	/** Logger for logging. */
	private static Logger log = Logger.getLogger(ProductController.class);

	@ActionMapping(params = "action=GoCreateProduct")
	public void processGoCreateProduct(BindingResult result,
			SessionStatus status, ActionResponse response) {
		log.debug("process GoCreateProduct.START");
		response.setRenderParameter("action", "GoCreateProduct");
	}

	@RenderMapping(params = "action=GoCreateProduct")
	public ModelAndView postGoCreateProduct(RenderRequest request) {
		log.debug("post GoCreateProduct.START");
		ProductDao pDao = new ProductDao();
		// get work Product List
		List<Workproduct> workProductList = pDao.getWorkProductList();
		Map<String, String> workProductMap = new LinkedHashMap<String, String>();
		for (int i = 0; i < workProductList.size(); i++) {
			workProductMap.put(workProductList.get(i).getWpId().toString(),
					workProductList.get(i).getName());
		}

		// get size Unit List
		List<Language> productSizeUnitList = pDao.getProductSizeUnitList();
		Map<String, String> productSizeUnitMap = new LinkedHashMap<String, String>();
		for (int i = 0; i < productSizeUnitList.size(); i++) {
			productSizeUnitMap.put(productSizeUnitList.get(i).getLanguageId()
					.toString(), productSizeUnitList.get(i).getName() + " "
					+ productSizeUnitList.get(i).getSizeUnit());
		}
		CreateProductForm projectFormBean = new CreateProductForm();
		request.setAttribute("CreateProductForm", projectFormBean);
		ModelAndView mav = new ModelAndView("CreateProduct");
		mav.addObject("workProduct", workProductMap);
		mav.addObject("plannedSizeUnit", productSizeUnitMap);
		mav.addObject("actualSizeUnit", productSizeUnitMap);
		String projectId = request.getParameter("projectId");
		log.debug("project ID la " + projectId);
		mav.addObject("projectId", projectId);
		return mav;
	}

}
