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
import javax.portlet.RenderRequest;

import openones.oopms.projecteye.dao.ProductDao;
import openones.oopms.projecteye.form.CreateProductForm;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.Module;
import openones.oopms.projecteye.model.Project;
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
public class CreateProductController {

	Developer user = new Developer();
	/** Logger for logging. */
	private static Logger log = Logger
			.getLogger(CreateProductController.class);
	String projectId;

	@ActionMapping(params = "action=CreateProduct")
	public void processCreateProject(CreateProductForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("process CreateProduct.START");
		ProductDao pDao = new ProductDao();
		Project project = new Project();
		projectId = formBean.getProjectId();
		project.setProjectId(new BigDecimal(projectId));
		Workproduct workProduct = new Workproduct();
		workProduct.setCode(formBean.getWorkProduct_SelectedValue());
		Module product = new Module();

		// set value for Product
		product.setProject(project);
		product.setName(formBean.getName());
		product.setWorkproduct(workProduct);
		product.setPlannedSizeUnitId(new BigDecimal(formBean.getPlannedSizeUnit_SelectedValue()));
		if(formBean.getPlannedSize().equals("") || formBean.getPlannedSize()==null) {
			product.setPlannedSize(null);
		} else {
			product.setPlannedSize(new BigDecimal(formBean.getPlannedSize()));
		}
		
		if(formBean.getRePlannedSize().equals("") || formBean.getRePlannedSize()==null) {
			product.setReplannedSize(null);
		} else {
			product.setReplannedSize(new BigDecimal(formBean.getRePlannedSize()));
		}
		
		if(formBean.getActualSize().equals("") || formBean.getActualSize()==null) {
			product.setActualSize(null);
		} else {
			product.setActualSize(new BigDecimal(formBean.getActualSize()));
		}
		
		product.setActualSizeUnitId(new BigDecimal(formBean.getActualSizeUnit_SelectedValue()));
		
		product.setNote(formBean.getDescription());
		// Call dao to insert project to database
		if (pDao.insertProduct(product)) {
			response.setRenderParameter("action", "CreateProduct");
			log.error("Insert success");
		} else {
			log.error("Cannot Insert");
		}

	}

	@RenderMapping(params = "action=CreateProduct")
	public ModelAndView postCreateProject(RenderRequest request) {
		log.debug("post CreateProduct.START");
		ModelAndView mav = new ModelAndView("Product");
		log.debug("project ID la " + projectId);
		mav.addObject("projectId", projectId);
		return mav;
	}
}
