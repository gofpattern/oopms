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

import openones.oopms.projecteye.dao.ProductDao;
import openones.oopms.projecteye.form.CreateProductForm;
import openones.oopms.projecteye.form.ProductForm;
import openones.oopms.projecteye.form.UpdateProductForm;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.Language;
import openones.oopms.projecteye.model.Module;
import openones.oopms.projecteye.model.Project;
import openones.oopms.projecteye.model.Workproduct;
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
public class ProductController {

	Developer user = new Developer();
	/** Logger for logging. */
	private static Logger log = Logger.getLogger(ProductController.class);
	String projectId;
	List<ProductForm> projectProductList = new ArrayList<ProductForm>();
	String searchWorkProduct;
	Map<String, String> workProductMap = new LinkedHashMap<String, String>();
	
	@RenderMapping(params = "action=GoCreateProduct")
	public ModelAndView postGoCreateProduct(RenderRequest request) {
		log.debug("post GoCreateProduct.START");
		ProductDao pDao = new ProductDao();
		// get work Product List
		List<Workproduct> workProductList = pDao.getWorkProductList();
		Map<String, String> workProductMap = new LinkedHashMap<String, String>();
		for (int i = 0; i < workProductList.size(); i++) {
			workProductMap.put(workProductList.get(i).getCode(),
					workProductList.get(i).getName());
		}
		CreateProductForm projectFormBean = new CreateProductForm();
		request.setAttribute("CreateProductForm", projectFormBean);
		ModelAndView mav = new ModelAndView("CreateProduct");
		mav.addObject("workProduct", workProductMap);
		String projectId = request.getParameter("projectId");
		log.debug("project ID la " + projectId);
		mav.addObject("projectId", projectId);
		return mav;
	}
	
	@ActionMapping(params = "action=SearchProduct")
	public void processSearchProduct(ProductForm formBean, BindingResult result,
			SessionStatus status, ActionResponse response) {
		log.debug("process GoCreateProduct.START");
		projectId = formBean.getProjectId();
		ProductDao pDao = new ProductDao();
		// get work Product List
		List<Workproduct> workProductList = pDao.getWorkProductList();
		workProductMap = new LinkedHashMap<String, String>();
		workProductMap.put("All", "All");
		for (int i = 0; i < workProductList.size(); i++) {
			workProductMap.put(workProductList.get(i).getCode(),
					workProductList.get(i).getName());
		}
		Project project = new Project();
		project.setProjectId(new BigDecimal(projectId));
		searchWorkProduct = formBean.getWorkProduct_SelectedValue();
		List<Module> productList = pDao.getProjectProductList(project, searchWorkProduct);
		projectProductList = new ArrayList<ProductForm>();
		if(productList.size()>0) {			
			for(int i=0; i<productList.size();i++) {
				ProductForm temp = new ProductForm();
				temp.setName(productList.get(i).getName());
//				Workproduct temp2 = pDao.getWorkProduct(productList.get(i).getWorkproduct().getCode());
				Language unitSize = pDao.getProductSizeUnit(productList.get(i)
						.getPlannedSizeUnitId());
				temp.setWorkProduct(productList.get(i).getWorkproduct()
						.getName());
				if (productList.get(i).getPlannedSizeUnitId() != null) {					
					temp.setPlannedSize(productList.get(i).getPlannedSize()
							.toString()
							+ " "
							+ unitSize.getName()
							+ " "
							+ unitSize.getSizeUnit());
				}
				if ((productList.get(i).getActualSize() != null)
						&& (productList.get(i).getActualSizeUnitId() != null)) {
					temp.setActualSize(productList.get(i).getActualSize()
							.toString()
							+ " "
							+ unitSize.getName()
							+ " "
							+ unitSize.getSizeUnit());
				}
//				temp.setCreatedSize(createdSize)
				temp.setDescription(productList.get(i).getNote());
				projectProductList.add(temp);
			}
		}
		response.setRenderParameter("action", "SearchProduct");
	}

	@RenderMapping(params = "action=SearchProduct")
	public ModelAndView postSearchProduct(RenderRequest request) {
		ProductForm formBean = new ProductForm();
		formBean.setWorkProduct_SelectedValue(searchWorkProduct);
		ModelAndView mav = new ModelAndView("Product", "ProductForm", formBean);
		mav.addObject("workProduct", workProductMap);
		mav.addObject("projectProductList", projectProductList);
		log.debug("project ID la " + projectId);
		mav.addObject("projectId", projectId);
		return mav;
	}
	
	@RenderMapping(params = "action=GoUpdateProduct")
	public ModelAndView postGoUpdateProduct(RenderRequest request) {
		log.debug("post GoUpdateProduct.START");
		ProductDao pDao = new ProductDao();
		// get work Product List
		List<Workproduct> workProductList = pDao.getWorkProductList();
		Map<String, String> workProductMap = new LinkedHashMap<String, String>();
		for (int i = 0; i < workProductList.size(); i++) {
			workProductMap.put(workProductList.get(i).getCode(),
					workProductList.get(i).getName());
		}
		String productId = request.getParameter("productId");
		String projectId = request.getParameter("projectId");
		Module product = pDao.getProduct(new BigDecimal(productId));
		UpdateProductForm bean = new UpdateProductForm();
		bean.setDescription(HTMLTag.replaceHTMLTag(product.getNote()));
		bean.setName(HTMLTag.replaceHTMLTag(product.getName()));
		bean.setWorkProduct_SelectedValue(product.getWorkproduct().getCode());
		ModelAndView mav = new ModelAndView("UpdateProduct","UpdateProductForm",bean);
		mav.addObject("workProduct", workProductMap);
		log.debug("project ID la " + projectId);
		mav.addObject("projectId", projectId);
		mav.addObject("productId", productId);
		return mav;
	}
	
	@RenderMapping(params = "action=RemoveProduct")
	public ModelAndView postRemoveProduct(RenderRequest request) {
		log.debug("post RemoveProduct.START");
		String projectId = request.getParameter("projectId");
		String productId = request.getParameter("productId");
		ProductDao productDao = new ProductDao();
		ModelAndView mav = new ModelAndView("Product", "ProductForm",
				new ProductForm());
		if(!productDao.deteleProduct(productId)) {
			mav.addObject("deleteFlag","1");
		}
		ProductDao pDao = new ProductDao();
		// get work Product List
		List<Workproduct> workProductList = pDao.getWorkProductList();
		Map<String, String> workProductMap = new LinkedHashMap<String, String>();
		workProductMap.put("All", "All");
		for (int i = 0; i < workProductList.size(); i++) {
			workProductMap.put(workProductList.get(i).getCode(),
					workProductList.get(i).getName());
		}
		Project project = new Project();
		log.debug("project ID la " + projectId);
		project.setProjectId(new BigDecimal(projectId));
		List<Module> productList = pDao.getProjectProductList(project, "All");
		List<ProductForm> projectProductList = new ArrayList<ProductForm>();
		if (productList.size() > 0) {
			for (int i = 0; i < productList.size(); i++) {
				ProductForm temp = new ProductForm();
				temp.setProductId(String.valueOf(productList.get(i).getModuleId()));
				temp.setName(productList.get(i).getName());
				// Workproduct temp2 =
				// pDao.getWorkProduct(productList.get(i).getWorkproduct().getCode());
				Language unitSize = new Language();
				temp.setWorkProduct(productList.get(i).getWorkproduct()
						.getName());
				if (productList.get(i).getPlannedSizeUnitId() != null) {
					unitSize = pDao.getProductSizeUnit(productList.get(i)
							.getPlannedSizeUnitId());
					temp.setPlannedSize(productList.get(i).getPlannedSize()
							.toString()
							+ " "
							+ unitSize.getName()
							+ " "
							+ unitSize.getSizeUnit());
				}
				if (productList.get(i).getReplannedSize() != null) {
					temp.setRePlannedSize(productList.get(i).getReplannedSize()
							.toString()
							+ " "
							+ unitSize.getName()
							+ " "
							+ unitSize.getSizeUnit());
				}
				if ((productList.get(i).getActualSize() != null)
						&& (productList.get(i).getActualSizeUnitId() != null)) {
					unitSize = pDao.getProductSizeUnit(productList.get(i)
							.getActualSizeUnitId());
					temp.setRePlannedSize(productList.get(i).getActualSize()
							.toString()
							+ " "
							+ unitSize.getName()
							+ " "
							+ unitSize.getSizeUnit());
				}
				// temp.setCreatedSize(createdSize)
				temp.setDescription(productList.get(i).getNote());
				projectProductList.add(temp);
			}
		}
		mav.addObject("workProduct", workProductMap);
		mav.addObject("projectProductList", projectProductList);
		log.debug("project ID la " + projectId);
		mav.addObject("projectId", projectId);
		return mav;
	}
}
