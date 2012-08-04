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

import javax.portlet.RenderRequest;

import openones.oopms.projecteye.dao.ChangeRequestDao;
import openones.oopms.projecteye.dao.ProductDao;
import openones.oopms.projecteye.dao.WorkOrderDao;
import openones.oopms.projecteye.form.ProductForm;
import openones.oopms.projecteye.model.ChangesOfProjectPlan;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.Language;
import openones.oopms.projecteye.model.Milestone;
import openones.oopms.projecteye.model.Module;
import openones.oopms.projecteye.model.Ncconstant;
import openones.oopms.projecteye.model.Project;
import openones.oopms.projecteye.model.Workproduct;
import openones.oopms.projecteye.dao.MilestoneDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 * @author HaiTCT
 */
@Controller
@RequestMapping("VIEW")
public class NavigatorController {

	Developer user = new Developer();
	/** Logger for logging. */
	private static Logger log = Logger.getLogger(NavigatorController.class);

	
	@RenderMapping(params = "action=GoRiskIssue")
	public ModelAndView postGoRiskIssue(RenderRequest request) {
		log.debug("post GoRiskIssue.START");
		ModelAndView mav = new ModelAndView("RiskIssue");
		String projectId = request.getParameter("projectId");
		log.debug("project ID la " + projectId);
		mav.addObject("projectId", projectId);
		return mav;
	}

	@RenderMapping(params = "action=GoChangeRequest")
	public ModelAndView postGoChangeRequest(RenderRequest request) {
		log.debug("post GoChangeRequest.START");
		ModelAndView mav = new ModelAndView("ChangeRequest");
		String projectId = request.getParameter("projectId");
		Project project = new Project();
		project.setProjectId(new BigDecimal(projectId));
		ChangeRequestDao crDao = new ChangeRequestDao();
		List<ChangesOfProjectPlan> projectChangeRequestList = crDao
				.getProjectChangeRequestList(project);
		if(projectChangeRequestList.size()>0) {
			for(int i = 0; i<projectChangeRequestList.size();i++) {
				Ncconstant status = crDao.getChangeRequestStatus(projectChangeRequestList.get(i).getVersion());
				projectChangeRequestList.get(i).setVersion(status.getDescription());
			}
		}
		mav.addObject("projectChangeRequestList", projectChangeRequestList);
		
		log.debug("project ID la " + projectId);
		mav.addObject("projectId", projectId);
		return mav;
	}

	@RenderMapping(params = "action=GoProduct")
	public ModelAndView postGoProduct(RenderRequest request) {
		log.debug("post GoProduct.START");
		
		String projectId = request.getParameter("projectId");
		ProductDao pDao = new ProductDao();
		// get work Product List
		List<Workproduct> workProductList = pDao.getWorkProductList();
		Map<String, String> workProductMap = new LinkedHashMap<String, String>();
		workProductMap.put("All", "All");
		for (int i = 0; i < workProductList.size(); i++) {
			workProductMap.put(workProductList.get(i).getCode(),
					workProductList.get(i).getName());
		}
		ModelAndView mav = new ModelAndView("Product", "ProductForm", new ProductForm());
		
		Project project = new Project();
		project.setProjectId(new BigDecimal(projectId));
		List<Module> productList = pDao.getProjectProductList(project, "All");
		List<ProductForm> projectProductList = new ArrayList<ProductForm>();
		if(productList.size()>0) {			
			for(int i=0; i<productList.size();i++) {
				ProductForm temp = new ProductForm();
				temp.setName(productList.get(i).getName());
				//Workproduct temp2 = pDao.getWorkProduct(productList.get(i).getWorkproduct().getCode());
				temp.setWorkProduct(productList.get(i).getWorkproduct().getName());
				Language unitSize = pDao.getProductSizeUnit(productList.get(i).getPlannedSizeUnitId());
				temp.setPlannedSize(productList.get(i).getPlannedSize().toString() + " " +unitSize.getName()+" "+unitSize.getSizeUnit());
				if(productList.get(i).getReplannedSize()!=null) {
					temp.setRePlannedSize(productList.get(i).getReplannedSize().toString() + " " +unitSize.getName()+" "+unitSize.getSizeUnit());
				}
				if((productList.get(i).getActualSize()!=null) && (productList.get(i).getActualSizeUnitId()!=null)) {
					unitSize = pDao.getProductSizeUnit(productList.get(i).getActualSizeUnitId());
					temp.setRePlannedSize(productList.get(i).getActualSize().toString() + " " +unitSize.getName()+" "+unitSize.getSizeUnit());
				}
				//temp.setCreatedSize(createdSize)
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

	@RenderMapping(params = "action=GoWorkOrder")
	public ModelAndView postGoWorkOrder(RenderRequest request) {
		log.debug("post GoWorkOrder.START");
		ModelAndView mav = new ModelAndView("WorkOrder");
		//set value for Stage
		String projectId = request.getParameter("projectId");
		MilestoneDao mDao = new MilestoneDao();
		Project project = new Project();
		project.setProjectId(new BigDecimal(projectId));
		List<Milestone> projectStages = mDao.getProjectStage(project);
		log.debug("project ID la " + projectId);
		log.debug("List Stage Size : " + projectStages.size());
		mav.addObject("stageList", projectStages);
		//set value for deliverable
		WorkOrderDao woDao = new WorkOrderDao();
		List<Module> productList = woDao
				.getSetDeliverableProductList(project);

		mav.addObject("projectId", projectId);
		return mav;
	}

	@RenderMapping(params = "action=GoCostManagement")
	public ModelAndView postGoCostManagement(RenderRequest request) {
		log.debug("post GoCostManagement.START");
		ModelAndView mav = new ModelAndView("CostManagement");
		String projectId = request.getParameter("projectId");
		log.debug("project ID la " + projectId);
		mav.addObject("projectId", projectId);
		return mav;
	}
}
