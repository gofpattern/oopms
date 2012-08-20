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

import openones.oopms.projecteye.dao.ProductDao;
import openones.oopms.projecteye.form.UpdateProductForm;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.Module;
import openones.oopms.projecteye.model.Workproduct;

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
public class UpdateProductController {

	Developer user = new Developer();
	/** Logger for logging. */
	private static Logger log = Logger.getLogger(UpdateProductController.class);
	String projectId;

	@ActionMapping(params = "action=UpdateProduct")
	public void processUpdateProduct(UpdateProductForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("process UpdateProduct.START");
		projectId = formBean.getProjectId();
		ProductDao pDao = new ProductDao();
		Module product = pDao
				.getProduct(new BigDecimal(formBean.getProductId()));
		Workproduct workProduct = new Workproduct();
		workProduct.setCode(formBean.getWorkProduct_SelectedValue());
		// set value for Product
		product.setName(formBean.getName());
		product.setWorkproduct(workProduct);
		product.setNote(formBean.getDescription());
		// Call dao to insert project to database
		if (pDao.updateProduct(product)) {
			log.error("projectId : " + projectId);
			response.setRenderParameter("action", "GoProduct");
			response.setRenderParameter("projectId", projectId);
			log.error("Update success");
		} else {
			log.error("Cannot Update");
		}

	}
}
