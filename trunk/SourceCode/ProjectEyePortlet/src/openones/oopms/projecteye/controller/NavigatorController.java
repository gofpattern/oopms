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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import openones.oopms.projecteye.dao.ChangeRequestDao;
import openones.oopms.projecteye.dao.CostDao;
import openones.oopms.projecteye.dao.DeveloperDao;
import openones.oopms.projecteye.dao.MilestoneDao;
import openones.oopms.projecteye.dao.ProductDao;
import openones.oopms.projecteye.dao.ProjectDao;
import openones.oopms.projecteye.dao.RiskDao;
import openones.oopms.projecteye.dao.WorkOrderDao;
import openones.oopms.projecteye.form.CostManagementForm;
import openones.oopms.projecteye.form.DailyExpense;
import openones.oopms.projecteye.form.ExceptionalCost;
import openones.oopms.projecteye.form.InvoiceDailyExpense;
import openones.oopms.projecteye.form.InvoiceExceptionalCost;
import openones.oopms.projecteye.form.ProductForm;
import openones.oopms.projecteye.form.RiskView;
import openones.oopms.projecteye.model.ChangesOfProjectPlan;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.Language;
import openones.oopms.projecteye.model.Milestone;
import openones.oopms.projecteye.model.Module;
import openones.oopms.projecteye.model.Ncconstant;
import openones.oopms.projecteye.model.OopmsBudget;
import openones.oopms.projecteye.model.OopmsCostDailyExpense;
import openones.oopms.projecteye.model.OopmsCostOneTimeExpense;
import openones.oopms.projecteye.model.OopmsCostType;
import openones.oopms.projecteye.model.OopmsExceptionalCost;
import openones.oopms.projecteye.model.OopmsProjectCost;
import openones.oopms.projecteye.model.Project;
import openones.oopms.projecteye.model.Risk;
import openones.oopms.projecteye.model.RiskSource;
import openones.oopms.projecteye.model.Workproduct;
import openones.oopms.projecteye.utils.AppUtil;
import openones.oopms.projecteye.utils.Constant;
import openones.oopms.projecteye.utils.CostUtil;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.util.PortletUtils;

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
		Project project = new Project();
		project.setProjectId(new BigDecimal(projectId));
		RiskDao rDao = new RiskDao();
		List<Risk> riskList = rDao.getProjectRiskList(project);
		List<RiskView> riskListView = new ArrayList<RiskView>();
		if (riskList != null) {
			for (int i = 0; i < riskList.size(); i++) {
				RiskView temp = new RiskView();
				temp.setDescription(riskList.get(i).getCondition());
				temp.setPriority(String.valueOf(riskList.get(i)
						.getRiskPriority()));
				temp.setProbability(String.valueOf(riskList.get(i).getProb()));
				temp.setRiskId(String.valueOf(riskList.get(i).getRiskId()));
				temp.setTrigger(riskList.get(i).getTriggerName());
				RiskSource riskSource = rDao.getRiskSource(riskList.get(i)
						.getSourceId());
				temp.setRiskSource(riskSource.getSourceName());
				String EstimatedImpact = "";
				int impactTo = riskList.get(i).getImpactTo().intValue();
				if (impactTo == 1) {
					EstimatedImpact = EstimatedImpact + "Schedule";
				} else if (impactTo == 2) {
					EstimatedImpact = EstimatedImpact + "Effort";
				} else if (impactTo == 3) {
					EstimatedImpact = EstimatedImpact + "Finance";
				} else if (impactTo == 4) {
					EstimatedImpact = EstimatedImpact + "Team";
				} else if (impactTo == 5) {
					EstimatedImpact = EstimatedImpact + "Timeliness";
				} else if (impactTo == 6) {
					EstimatedImpact = EstimatedImpact + "Requirement";
				} else if (impactTo == 7) {
					EstimatedImpact = EstimatedImpact + "Leakage";
				} else if (impactTo == 8) {
					EstimatedImpact = EstimatedImpact + "Customer";
				} else if (impactTo == 9) {
					EstimatedImpact = EstimatedImpact + "Correction";
				} else if (impactTo == 10) {
					EstimatedImpact = EstimatedImpact + "Other";
				}

				EstimatedImpact = EstimatedImpact + " "
						+ String.valueOf(riskList.get(i).getEstimatedImpact())
						+ " ";

				int impactUnit = riskList.get(i).getUnit().intValue();
				if (impactUnit == 1) {
					EstimatedImpact = EstimatedImpact + "%";
				} else if (impactUnit == 2) {
					EstimatedImpact = EstimatedImpact + "day";
				} else if (impactUnit == 3) {
					EstimatedImpact = EstimatedImpact + "month";
				} else if (impactUnit == 4) {
					EstimatedImpact = EstimatedImpact + "person.day";
				} else if (impactUnit == 5) {
					EstimatedImpact = EstimatedImpact + "person.month";
				} else if (impactUnit == 6) {
					EstimatedImpact = EstimatedImpact + "$";
				} else if (impactUnit == 7) {
					EstimatedImpact = EstimatedImpact + "#";
				}

				temp.setEstimatedImpact(EstimatedImpact);
				riskListView.add(temp);
			}
		}
		log.debug("project ID la " + projectId);
		mav.addObject("projectId", projectId);
		mav.addObject("riskList", riskListView);
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
		if (projectChangeRequestList.size() > 0) {
			for (int i = 0; i < projectChangeRequestList.size(); i++) {
				Ncconstant status = crDao
						.getChangeRequestStatus(projectChangeRequestList.get(i)
								.getVersion());
				projectChangeRequestList.get(i).setVersion(
						status.getDescription());
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
		ModelAndView mav = new ModelAndView("Product", "ProductForm",
				new ProductForm());

		Project project = new Project();
		log.debug("project ID la " + projectId);
		project.setProjectId(new BigDecimal(projectId));
		List<Module> productList = pDao.getProjectProductList(project, "All");
		List<ProductForm> projectProductList = new ArrayList<ProductForm>();
		if (productList.size() > 0) {
			for (int i = 0; i < productList.size(); i++) {
				ProductForm temp = new ProductForm();
				temp.setProductId(String.valueOf(productList.get(i)
						.getModuleId()));
				temp.setName(productList.get(i).getName());
				// Workproduct temp2 =
				// pDao.getWorkProduct(productList.get(i).getWorkproduct().getCode());
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

	@RenderMapping(params = "action=GoWorkOrder")
	public ModelAndView postGoWorkOrder(RenderRequest request) {
		log.debug("post GoWorkOrder.START");
		ModelAndView mav = new ModelAndView("WorkOrder");
		// set value for Stage
		String projectId = request.getParameter("projectId");
		MilestoneDao mDao = new MilestoneDao();
		Project project = new Project();
		project.setProjectId(new BigDecimal(projectId));
		List<Milestone> projectStages = mDao.getProjectStage(project);
		log.debug("project ID la " + projectId);
		log.debug("List Stage Size : " + projectStages.size());
		mav.addObject("stageList", projectStages);
		// set value for deliverable
		WorkOrderDao woDao = new WorkOrderDao();
		List<Module> productList = woDao.getSetDeliverableProductList(project);
		List<Module> productListStage1 = new ArrayList<Module>();
		List<Module> productListStage2 = new ArrayList<Module>();
		List<Module> productListStage3 = new ArrayList<Module>();
		List<Module> productListStage4 = new ArrayList<Module>();
		List<Module> productListStage5 = new ArrayList<Module>();
		List<Module> productListStage6 = new ArrayList<Module>();
		for (int i = 0; i < productList.size(); i++) {
			// priority of actual date > re-planned > planned
			if (productList.get(i).getActualDeliveryDate() != null) {
				if (projectStages.get(0).getActualFinishDate() != null) {
					if (!productList.get(i).getActualDeliveryDate()
							.after(projectStages.get(0).getActualFinishDate())) {
						productListStage1.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(0).getPlanFinishDate() != null) {
					if (!productList.get(i).getActualDeliveryDate()
							.after(projectStages.get(0).getPlanFinishDate())) {
						productListStage1.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(0).getBaseFinishDate() != null) {
					if (!productList.get(i).getActualDeliveryDate()
							.after(projectStages.get(0).getBaseFinishDate())) {
						productListStage1.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(1).getActualFinishDate() != null) {
					if (!productList.get(i).getActualDeliveryDate()
							.after(projectStages.get(1).getActualFinishDate())) {
						productListStage2.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(1).getPlanFinishDate() != null) {
					if (!productList.get(i).getActualDeliveryDate()
							.after(projectStages.get(1).getPlanFinishDate())) {
						productListStage2.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(1).getBaseFinishDate() != null) {
					if (!productList.get(i).getActualDeliveryDate()
							.after(projectStages.get(1).getBaseFinishDate())) {
						productListStage2.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(2).getActualFinishDate() != null) {
					if (!productList.get(i).getActualDeliveryDate()
							.after(projectStages.get(2).getActualFinishDate())) {
						productListStage3.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(2).getPlanFinishDate() != null) {
					if (!productList.get(i).getActualDeliveryDate()
							.after(projectStages.get(2).getPlanFinishDate())) {
						productListStage3.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(2).getBaseFinishDate() != null) {
					if (!productList.get(i).getActualDeliveryDate()
							.after(projectStages.get(2).getBaseFinishDate())) {
						productListStage3.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(3).getActualFinishDate() != null) {
					if (!productList.get(i).getActualDeliveryDate()
							.after(projectStages.get(3).getActualFinishDate())) {
						productListStage4.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(3).getPlanFinishDate() != null) {
					if (!productList.get(i).getActualDeliveryDate()
							.after(projectStages.get(3).getPlanFinishDate())) {
						productListStage4.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(3).getBaseFinishDate() != null) {
					if (!productList.get(i).getActualDeliveryDate()
							.after(projectStages.get(3).getBaseFinishDate())) {
						productListStage4.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(4).getActualFinishDate() != null) {
					if (!productList.get(i).getActualDeliveryDate()
							.after(projectStages.get(4).getActualFinishDate())) {
						productListStage5.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(4).getPlanFinishDate() != null) {
					if (!productList.get(i).getActualDeliveryDate()
							.after(projectStages.get(4).getPlanFinishDate())) {
						productListStage5.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(4).getBaseFinishDate() != null) {
					if (!productList.get(i).getActualDeliveryDate()
							.after(projectStages.get(4).getBaseFinishDate())) {
						productListStage5.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(5).getActualFinishDate() != null) {
					if (!productList.get(i).getActualDeliveryDate()
							.after(projectStages.get(5).getActualFinishDate())) {
						productListStage6.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(5).getPlanFinishDate() != null) {
					if (!productList.get(i).getActualDeliveryDate()
							.after(projectStages.get(5).getPlanFinishDate())) {
						productListStage6.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(5).getBaseFinishDate() != null) {
					if (!productList.get(i).getActualDeliveryDate()
							.after(projectStages.get(5).getBaseFinishDate())) {
						productListStage6.add(productList.get(i));
						continue;
					}
				}
			} else if (productList.get(i).getReplannedReleaseDate() != null) {
				if (projectStages.get(0).getActualFinishDate() != null) {
					if (!productList.get(i).getReplannedReleaseDate()
							.after(projectStages.get(0).getActualFinishDate())) {
						productListStage1.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(0).getPlanFinishDate() != null) {
					if (!productList.get(i).getReplannedReleaseDate()
							.after(projectStages.get(0).getPlanFinishDate())) {
						productListStage1.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(0).getBaseFinishDate() != null) {
					if (!productList.get(i).getReplannedReleaseDate()
							.after(projectStages.get(0).getBaseFinishDate())) {
						productListStage1.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(1).getActualFinishDate() != null) {
					if (!productList.get(i).getReplannedReleaseDate()
							.after(projectStages.get(1).getActualFinishDate())) {
						productListStage2.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(1).getPlanFinishDate() != null) {
					if (!productList.get(i).getReplannedReleaseDate()
							.after(projectStages.get(1).getPlanFinishDate())) {
						productListStage2.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(1).getBaseFinishDate() != null) {
					if (!productList.get(i).getReplannedReleaseDate()
							.after(projectStages.get(1).getBaseFinishDate())) {
						productListStage2.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(2).getActualFinishDate() != null) {
					if (!productList.get(i).getReplannedReleaseDate()
							.after(projectStages.get(2).getActualFinishDate())) {
						productListStage3.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(2).getPlanFinishDate() != null) {
					if (!productList.get(i).getReplannedReleaseDate()
							.after(projectStages.get(2).getPlanFinishDate())) {
						productListStage3.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(2).getBaseFinishDate() != null) {
					if (!productList.get(i).getReplannedReleaseDate()
							.after(projectStages.get(2).getBaseFinishDate())) {
						productListStage3.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(3).getActualFinishDate() != null) {
					if (!productList.get(i).getReplannedReleaseDate()
							.after(projectStages.get(3).getActualFinishDate())) {
						productListStage4.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(3).getPlanFinishDate() != null) {
					if (!productList.get(i).getReplannedReleaseDate()
							.after(projectStages.get(3).getPlanFinishDate())) {
						productListStage4.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(3).getBaseFinishDate() != null) {
					if (!productList.get(i).getReplannedReleaseDate()
							.after(projectStages.get(3).getBaseFinishDate())) {
						productListStage4.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(4).getActualFinishDate() != null) {
					if (!productList.get(i).getReplannedReleaseDate()
							.after(projectStages.get(4).getActualFinishDate())) {
						productListStage5.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(4).getPlanFinishDate() != null) {
					if (!productList.get(i).getReplannedReleaseDate()
							.after(projectStages.get(4).getPlanFinishDate())) {
						productListStage5.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(4).getBaseFinishDate() != null) {
					if (!productList.get(i).getReplannedReleaseDate()
							.after(projectStages.get(4).getBaseFinishDate())) {
						productListStage5.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(5).getActualFinishDate() != null) {
					if (!productList.get(i).getReplannedReleaseDate()
							.after(projectStages.get(5).getActualFinishDate())) {
						productListStage6.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(5).getPlanFinishDate() != null) {
					if (!productList.get(i).getReplannedReleaseDate()
							.after(projectStages.get(5).getPlanFinishDate())) {
						productListStage6.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(5).getBaseFinishDate() != null) {
					if (!productList.get(i).getReplannedReleaseDate()
							.after(projectStages.get(5).getBaseFinishDate())) {
						productListStage6.add(productList.get(i));
						continue;
					}
				}
			} else {
				if (projectStages.get(0).getActualFinishDate() != null) {
					if (!productList.get(i).getPlannedReleaseDate()
							.after(projectStages.get(0).getActualFinishDate())) {
						productListStage1.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(0).getPlanFinishDate() != null) {
					if (!productList.get(i).getPlannedReleaseDate()
							.after(projectStages.get(0).getPlanFinishDate())) {
						productListStage1.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(0).getBaseFinishDate() != null) {
					if (!productList.get(i).getPlannedReleaseDate()
							.after(projectStages.get(0).getBaseFinishDate())) {
						productListStage1.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(1).getActualFinishDate() != null) {
					if (!productList.get(i).getPlannedReleaseDate()
							.after(projectStages.get(1).getActualFinishDate())) {
						productListStage2.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(1).getPlanFinishDate() != null) {
					if (!productList.get(i).getPlannedReleaseDate()
							.after(projectStages.get(1).getPlanFinishDate())) {
						productListStage2.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(1).getBaseFinishDate() != null) {
					if (!productList.get(i).getPlannedReleaseDate()
							.after(projectStages.get(1).getBaseFinishDate())) {
						productListStage2.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(2).getActualFinishDate() != null) {
					if (!productList.get(i).getPlannedReleaseDate()
							.after(projectStages.get(2).getActualFinishDate())) {
						productListStage3.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(2).getPlanFinishDate() != null) {
					if (!productList.get(i).getPlannedReleaseDate()
							.after(projectStages.get(2).getPlanFinishDate())) {
						productListStage3.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(2).getBaseFinishDate() != null) {
					if (!productList.get(i).getPlannedReleaseDate()
							.after(projectStages.get(2).getBaseFinishDate())) {
						productListStage3.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(3).getActualFinishDate() != null) {
					if (!productList.get(i).getPlannedReleaseDate()
							.after(projectStages.get(3).getActualFinishDate())) {
						productListStage4.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(3).getPlanFinishDate() != null) {
					if (!productList.get(i).getPlannedReleaseDate()
							.after(projectStages.get(3).getPlanFinishDate())) {
						productListStage4.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(3).getBaseFinishDate() != null) {
					if (!productList.get(i).getPlannedReleaseDate()
							.after(projectStages.get(3).getBaseFinishDate())) {
						productListStage4.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(4).getActualFinishDate() != null) {
					if (!productList.get(i).getPlannedReleaseDate()
							.after(projectStages.get(4).getActualFinishDate())) {
						productListStage5.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(4).getPlanFinishDate() != null) {
					if (!productList.get(i).getPlannedReleaseDate()
							.after(projectStages.get(4).getPlanFinishDate())) {
						productListStage5.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(4).getBaseFinishDate() != null) {
					if (!productList.get(i).getPlannedReleaseDate()
							.after(projectStages.get(4).getBaseFinishDate())) {
						productListStage5.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(5).getActualFinishDate() != null) {
					if (!productList.get(i).getPlannedReleaseDate()
							.after(projectStages.get(5).getActualFinishDate())) {
						productListStage6.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(5).getPlanFinishDate() != null) {
					if (!productList.get(i).getPlannedReleaseDate()
							.after(projectStages.get(5).getPlanFinishDate())) {
						productListStage6.add(productList.get(i));
						continue;
					}
				}
				if (projectStages.get(5).getBaseFinishDate() != null) {
					if (!productList.get(i).getPlannedReleaseDate()
							.after(projectStages.get(5).getBaseFinishDate())) {
						productListStage6.add(productList.get(i));
						continue;
					}
				}
			}
		}
		mav.addObject("deliverableListStage1", productListStage1);
		mav.addObject("deliverableListStage2", productListStage2);
		mav.addObject("deliverableListStage3", productListStage3);
		mav.addObject("deliverableListStage4", productListStage4);
		mav.addObject("deliverableListStage5", productListStage5);
		mav.addObject("deliverableListStage6", productListStage6);
		mav.addObject("projectId", projectId);
		return mav;
	}

	@RenderMapping(params = "action=GoCostManagement")
	public ModelAndView postGoCostManagement(RenderRequest request,
			RenderResponse response) {
		log.debug("post GoCostManagement.START");
		DateFormat df = new java.text.SimpleDateFormat("MM/dd/yyyy");
		String viewDate = request.getParameter("viewDate");
		String projectId = request.getParameter("projectId");
		String formattedDate = "";
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		BigDecimal expense = new BigDecimal("0");
		String templatePath = "/resource_files/CostDetailTemplate.xls";
		String createPath = "/resource_files/Invoice.xls";
		if (viewDate != null) {
			try {
				expense = CostUtil.getExpense(projectId,
						(Date) formatter.parse(viewDate));
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
			formattedDate = viewDate;
		} else {
			formattedDate = df.format(new Date());
			expense = CostUtil.getExpense(projectId, new Date());
		}

		CostManagementForm form = new CostManagementForm();
		form.setViewDate(formattedDate);
		String payDate = request.getParameter("payDate");
		if (payDate != null) {
			form.setPayDate(payDate);
		} else {
			form.setPayDate(df.format(new Date()));
		}
		ModelAndView mav = new ModelAndView("CostManagement",
				"CostManagementForm", form);

		CostDao cDao = new CostDao();
		List<OopmsCostOneTimeExpense> oneTimeExpenseList = cDao
				.getOneTimeExpensePlanList(projectId);
		List<OopmsCostType> costTypeList = cDao.getCostTypeList(projectId);
		List<OopmsCostDailyExpense> dailyExpenseList = cDao
				.getDailyExpensePlanList(projectId);
		List<DailyExpense> dailyExpenseListView = CostUtil
				.getDailyExpenseListView(dailyExpenseList);
		List<OopmsExceptionalCost> exceptionalExpenseList = cDao
				.getExceptionalExpensePlanList(projectId);
		List<ExceptionalCost> exceptionalExpenseListView = CostUtil
				.getExceptionalListView(exceptionalExpenseList);
		List<OopmsExceptionalCost> exceptionalDeductList = cDao
				.getExceptionalDeductPlanList(projectId);
		List<ExceptionalCost> exceptionalDeductListView = CostUtil
				.getExceptionalListView(exceptionalDeductList);
		OopmsProjectCost projectCost = cDao.getProjectCost(new BigDecimal(
				projectId));
		log.debug("project ID is " + projectId);
		String deleteCostTypeFlag = request.getParameter("deleteCostTypeFlag");
		if (deleteCostTypeFlag != null) {
			mav.addObject("deleteCostTypeFlag", deleteCostTypeFlag);
			String deletingOopmsCostTypeId = request
					.getParameter("deletingOopmsCostTypeId");
			mav.addObject("deletingOopmsCostTypeId", deletingOopmsCostTypeId);
		}

		String deleteDailyFlag = request.getParameter("deleteDailyFlag");
		if (deleteDailyFlag != null) {
			mav.addObject("deleteDailyFlag", deleteDailyFlag);
			String deletingOopmsCostDailyExpenseId = request
					.getParameter("deletingOopmsCostDailyExpenseId");
			mav.addObject("deletingOopmsCostDailyExpenseId",
					deletingOopmsCostDailyExpenseId);
		}

		String payExceptionalCostFlag = request
				.getParameter("payExceptionalCostFlag");
		if (payExceptionalCostFlag != null) {
			mav.addObject("payExceptionalCostFlag", payExceptionalCostFlag);
		}

		if (request.getParameter("ViewBudgetRecord") != null) {
			List<OopmsBudget> budgetList = cDao.getProjectBudgetList(projectId);
			mav.addObject("BudgetRecords", budgetList);
		}
		if (request.getParameter("ViewInvoiceRecords") != null) {
			List<OopmsCostOneTimeExpense> invoiceOneTime = cDao
					.getOneTimeExpenseInvoiceList(projectId);
			List<OopmsCostDailyExpense> invoiceDaily = cDao
					.getDailyExpenseInvoiceList(projectId);
			List<OopmsExceptionalCost> invoiceExceptionalExpense = cDao
					.getExceptionalExpenseInvoiceList(projectId);
			List<OopmsExceptionalCost> invoiceExceptionalDeduct = cDao
					.getExceptionalDeductInvoiceList(projectId);
			List<InvoiceDailyExpense> invoiceDailyView = new ArrayList<InvoiceDailyExpense>();
			List<InvoiceExceptionalCost> invoiceExceptionalExpenseView = new ArrayList<InvoiceExceptionalCost>();
			List<InvoiceExceptionalCost> invoiceExceptionalDeductView = new ArrayList<InvoiceExceptionalCost>();
			if (invoiceDaily != null) {
				invoiceDailyView = CostUtil
						.getInvoiceDailyExpense(invoiceDaily);
			}
			if (invoiceExceptionalExpense != null) {
				invoiceExceptionalExpenseView = CostUtil
						.getInvoiceExceptionalCostList(invoiceExceptionalExpense);
			}
			if (invoiceExceptionalDeduct != null) {
				invoiceExceptionalDeductView = CostUtil
						.getInvoiceExceptionalCostList(invoiceExceptionalDeduct);
			}
			if (invoiceOneTime != null) {
				if (invoiceOneTime.size() > 0) {
					mav.addObject("InvoiceRecords", "InvoiceRecords");
				}
			}
			if (invoiceDailyView.size() > 0
					|| invoiceExceptionalExpenseView.size() > 0
					|| invoiceExceptionalDeductView.size() > 0) {
				mav.addObject("InvoiceRecords", "InvoiceRecords");
			}
			mav.addObject("InvoiceOneTime", invoiceOneTime);
			mav.addObject("InvoiceDaily", invoiceDailyView);
			mav.addObject("InvoiceExceptionalExpense",
					invoiceExceptionalExpenseView);
			mav.addObject("InvoiceExceptionalDeduct",
					invoiceExceptionalDeductView);
			try {
				templatePath = PortletUtils.getRealPath(request
						.getPortletSession().getPortletContext(), templatePath);
				createPath = PortletUtils.getRealPath(request
						.getPortletSession().getPortletContext(), createPath);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			processExportInvoice(invoiceOneTime, invoiceDailyView,
					invoiceExceptionalExpenseView,
					invoiceExceptionalDeductView, projectId, templatePath,
					createPath);

		}
		if (projectCost != null) {
			mav.addObject("currentBudget", projectCost.getCurrentBudget());
			mav.addObject("currentExpense", projectCost.getCurrentExpense());
		}

		mav.addObject("OneTimeExpenseList", oneTimeExpenseList);
		mav.addObject("CostTypeList", costTypeList);
		mav.addObject("DailyExpenseList", dailyExpenseListView);
		mav.addObject("ExceptionalExpenseList", exceptionalExpenseListView);
		mav.addObject("ExceptionalDeductList", exceptionalDeductListView);
		mav.addObject("expense", expense);
		mav.addObject("viewDate", formattedDate);
		mav.addObject("projectId", projectId);
		mav.addObject("exportPath", createPath);
		return mav;
	}

	public void processExportInvoice(
			List<OopmsCostOneTimeExpense> invoiceOneTime,
			List<InvoiceDailyExpense> invoiceDailyView,
			List<InvoiceExceptionalCost> invoiceExceptionalExpenseView,
			List<InvoiceExceptionalCost> invoiceExceptionalDeductView,
			String projectId, String templatePath, String exportPath) {
		log.debug("post ExportInvoice.START");
		// Get information to input to excel
		CostDao cDao = new CostDao();
		// Create excel
		try {
			// initial
			// String templatePath = formBean.getTemplatePath();
			// String exportPath = formBean.getExportPath();
			log.error("Path : " + templatePath);
			Workbook workbook = Workbook.getWorkbook(new File(templatePath));
			WritableWorkbook copy = Workbook.createWorkbook(
					new File(exportPath), workbook);
			WritableSheet sheet = copy.getSheet(0);
			int currentLine = 8;
			BigDecimal totalOneTime = new BigDecimal("0");
			BigDecimal totalDaily = new BigDecimal("0");
			BigDecimal totalExceptionalExpense = new BigDecimal("0");
			BigDecimal totalExceptionalDeduct = new BigDecimal("0");
			// write data//////////////////////
			// Write One Time Expense
			if (invoiceOneTime != null) {
				int firstRecord = currentLine;
				for (int i = 0; i < invoiceOneTime.size(); i++) {
					sheet.insertRow(currentLine);
					// write Name
					Label label = new Label(4, currentLine, invoiceOneTime.get(
							i).getName());
					sheet.addCell(label);
					// write description
					label = new Label(5, currentLine, invoiceOneTime.get(i)
							.getDescription());
					sheet.addCell(label);
					// write Date
					label = new Label(7, currentLine, AppUtil.getDateAsFormat(
							invoiceOneTime.get(i).getOccurDate(),
							Constant.DateFormat));
					sheet.addCell(label);
					// write Total
					label = new Label(8, currentLine,
							String.valueOf(invoiceOneTime.get(i).getCost()));
					sheet.addCell(label);
					totalOneTime = totalOneTime.add(invoiceOneTime.get(i)
							.getCost());
					currentLine++;
				}

				WritableCell cell = sheet.getWritableCell(10, firstRecord - 1);
				Label label = new Label(10, firstRecord - 1,
						String.valueOf(totalOneTime), cell.getCellFormat());
				sheet.addCell(label);
			}

			// Write Daily Expense
			currentLine = currentLine + 3;
			if (invoiceDailyView != null) {
				int firstRecord = currentLine;
				for (int i = 0; i < invoiceDailyView.size(); i++) {
					sheet.insertRow(currentLine);
					// write Name
					Label label = new Label(4, currentLine, invoiceDailyView
							.get(i).getName());
					sheet.addCell(label);
					// write description
					label = new Label(5, currentLine, invoiceDailyView.get(i)
							.getDescription());
					sheet.addCell(label);
					// write cost/day
					label = new Label(6, currentLine, invoiceDailyView.get(i)
							.getCostDay());
					sheet.addCell(label);
					// write Days
					label = new Label(7, currentLine, invoiceDailyView.get(i)
							.getDays());
					sheet.addCell(label);
					// write Total
					label = new Label(8, currentLine, invoiceDailyView.get(i)
							.getTotal());
					sheet.addCell(label);
					totalDaily = totalDaily.add(new BigDecimal(invoiceDailyView
							.get(i).getTotal()));
					currentLine++;
				}
				WritableCell cell = sheet.getWritableCell(10, firstRecord - 1);
				Label label = new Label(10, firstRecord - 1,
						String.valueOf(totalDaily), cell.getCellFormat());
				sheet.addCell(label);
			}

			// Write Exceptional Expense
			currentLine = currentLine + 3;
			if (invoiceExceptionalExpenseView != null) {
				int firstRecord = currentLine;
				for (int i = 0; i < invoiceExceptionalExpenseView.size(); i++) {
					sheet.insertRow(currentLine);
					// write Name
					Label label = new Label(4, currentLine,
							invoiceExceptionalExpenseView.get(i).getName());
					sheet.addCell(label);
					// write description
					label = new Label(5, currentLine,
							invoiceExceptionalExpenseView.get(i)
									.getDescription());
					sheet.addCell(label);
					// write AffectTo
					label = new Label(6, currentLine,
							invoiceExceptionalExpenseView.get(i).getEffectTo());
					sheet.addCell(label);
					// write Effect
					label = new Label(7, currentLine,
							invoiceExceptionalExpenseView.get(i).getEffect());
					sheet.addCell(label);
					// write Total
					label = new Label(8, currentLine,
							invoiceExceptionalExpenseView.get(i).getTotal());
					sheet.addCell(label);
					totalExceptionalExpense = totalExceptionalExpense
							.add(new BigDecimal(invoiceExceptionalExpenseView
									.get(i).getTotal()));
					currentLine++;
				}
				WritableCell cell = sheet.getWritableCell(10, firstRecord - 1);
				Label label = new Label(10, firstRecord - 1,
						String.valueOf(totalExceptionalExpense),
						cell.getCellFormat());
				sheet.addCell(label);
			}

			// Write Exceptional Deduct
			currentLine = currentLine + 3;
			if (invoiceExceptionalDeductView != null) {
				int firstRecord = currentLine;
				for (int i = 0; i < invoiceExceptionalDeductView.size(); i++) {
					sheet.insertRow(currentLine);
					// write Name
					Label label = new Label(4, currentLine,
							invoiceExceptionalDeductView.get(i).getName());
					sheet.addCell(label);
					// write description
					label = new Label(5, currentLine,
							invoiceExceptionalDeductView.get(i)
									.getDescription());
					sheet.addCell(label);
					// write AffectTo
					label = new Label(6, currentLine,
							invoiceExceptionalDeductView.get(i).getEffectTo());
					sheet.addCell(label);
					// write Effect
					label = new Label(7, currentLine,
							invoiceExceptionalDeductView.get(i).getEffect());
					sheet.addCell(label);
					// write Total
					label = new Label(8, currentLine,
							invoiceExceptionalDeductView.get(i).getTotal());
					sheet.addCell(label);
					totalExceptionalDeduct = totalExceptionalDeduct
							.add(new BigDecimal(invoiceExceptionalDeductView
									.get(i).getTotal()));
					currentLine++;
				}
				WritableCell cell = sheet.getWritableCell(10, firstRecord - 1);
				Label label = new Label(10, firstRecord - 1,
						String.valueOf(totalExceptionalDeduct),
						cell.getCellFormat());
				sheet.addCell(label);
			}

			// write Total
			BigDecimal total = new BigDecimal("0");
			total = total.add(totalOneTime);
			total = total.add(totalDaily);
			total = total.add(totalExceptionalExpense);
			total = total.add(totalExceptionalDeduct);
			WritableCell cell = sheet.getWritableCell(10, currentLine + 2);
			Label label = new Label(10, currentLine + 2, String.valueOf(total),
					cell.getCellFormat());
			sheet.addCell(label);

			// Write project name, project Manager, Current Budget and date
			// export
			ProjectDao pDao = new ProjectDao();
			Project project = pDao.getProject(projectId);
			label = new Label(3, 2, project.getName());
			sheet.addCell(label);
			DeveloperDao dDao = new DeveloperDao();
			Developer pm = dDao.getProjectManager(project);
			label = new Label(3, 3, pm.getName());
			sheet.addCell(label);
			OopmsProjectCost projectCost = cDao.getProjectCost(project
					.getProjectId());
			label = new Label(3, 4, String.valueOf(projectCost
					.getCurrentBudget()));
			sheet.addCell(label);
			label = new Label(10, 2, AppUtil.getDateAsFormat(new Date(),
					Constant.DateFormat));
			sheet.addCell(label);
			// final
			copy.write();
			copy.close();
		} catch (BiffException e) {
			log.error("ERRORRO");
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error("ERRORRO");
			log.error(e.getMessage());
		} catch (WriteException e) {
			log.error("ERRORRO");
			log.error(e.getMessage());
		}
	}

	// public void processExportInvoice(RenderRequest request,
	// RenderResponse response) {
	// log.debug("post ExportInvoice.START");
	// String projectId = request.getParameter("projectId");
	// // Get information to input to excel
	// CostDao cDao = new CostDao();
	// List<OopmsCostOneTimeExpense> invoiceOneTime = cDao
	// .getOneTimeExpenseInvoiceList(projectId);
	// List<OopmsCostDailyExpense> invoiceDaily = cDao
	// .getDailyExpenseInvoiceList(projectId);
	// List<OopmsExceptionalCost> invoiceExceptionalExpense = cDao
	// .getExceptionalExpenseInvoiceList(projectId);
	// List<OopmsExceptionalCost> invoiceExceptionalDeduct = cDao
	// .getExceptionalDeductInvoiceList(projectId);
	// List<InvoiceDailyExpense> invoiceDailyView = new
	// ArrayList<InvoiceDailyExpense>();
	// List<InvoiceExceptionalCost> invoiceExceptionalExpenseView = new
	// ArrayList<InvoiceExceptionalCost>();
	// List<InvoiceExceptionalCost> invoiceExceptionalDeductView = new
	// ArrayList<InvoiceExceptionalCost>();
	// if (invoiceDaily != null) {
	// invoiceDailyView = CostUtil.getInvoiceDailyExpense(invoiceDaily);
	// }
	// if (invoiceExceptionalExpense != null) {
	// invoiceExceptionalExpenseView = CostUtil
	// .getInvoiceExceptionalCostList(invoiceExceptionalExpense);
	// }
	// if (invoiceExceptionalDeduct != null) {
	// invoiceExceptionalDeductView = CostUtil
	// .getInvoiceExceptionalCostList(invoiceExceptionalDeduct);
	// }
	// // Create excel
	// try {
	// // initial
	// // String templatePath = formBean.getTemplatePath();
	// // String exportPath = formBean.getExportPath();
	// String templatePath = request.getParameter("templatePath");
	// String exportPath = request.getParameter("exportPath");
	// log.error("Path : " + templatePath);
	// Workbook workbook = Workbook.getWorkbook(new File(templatePath));
	// WritableWorkbook copy = Workbook.createWorkbook(
	// new File(exportPath), workbook);
	// WritableSheet sheet = copy.getSheet(0);
	// int currentLine = 8;
	// BigDecimal totalOneTime = new BigDecimal("0");
	// BigDecimal totalDaily = new BigDecimal("0");
	// BigDecimal totalExceptionalExpense = new BigDecimal("0");
	// BigDecimal totalExceptionalDeduct = new BigDecimal("0");
	// // write data//////////////////////
	// // Write One Time Expense
	// if (invoiceOneTime != null) {
	// int firstRecord = currentLine;
	// for (int i = 0; i < invoiceOneTime.size(); i++) {
	// sheet.insertRow(currentLine);
	// // write Name
	// Label label = new Label(4, currentLine, invoiceOneTime.get(
	// i).getName());
	// sheet.addCell(label);
	// // write description
	// label = new Label(5, currentLine, invoiceOneTime.get(i)
	// .getDescription());
	// sheet.addCell(label);
	// // write Date
	// label = new Label(7, currentLine, AppUtil.getDateAsFormat(
	// invoiceOneTime.get(i).getOccurDate(),
	// Constant.DateFormat));
	// sheet.addCell(label);
	// // write Total
	// label = new Label(8, currentLine,
	// String.valueOf(invoiceOneTime.get(i).getCost()));
	// sheet.addCell(label);
	// totalOneTime = totalOneTime.add(invoiceOneTime.get(i)
	// .getCost());
	// currentLine++;
	// }
	//
	// WritableCell cell = sheet.getWritableCell(10, firstRecord - 1);
	// Label label = new Label(10, firstRecord - 1,
	// String.valueOf(totalOneTime), cell.getCellFormat());
	// sheet.addCell(label);
	// }
	//
	// // Write Daily Expense
	// currentLine = currentLine + 3;
	// if (invoiceDailyView != null) {
	// int firstRecord = currentLine;
	// for (int i = 0; i < invoiceDailyView.size(); i++) {
	// sheet.insertRow(currentLine);
	// // write Name
	// Label label = new Label(4, currentLine, invoiceDailyView
	// .get(i).getName());
	// sheet.addCell(label);
	// // write description
	// label = new Label(5, currentLine, invoiceDailyView.get(i)
	// .getDescription());
	// sheet.addCell(label);
	// // write cost/day
	// label = new Label(6, currentLine, invoiceDailyView.get(i)
	// .getCostDay());
	// sheet.addCell(label);
	// // write Days
	// label = new Label(7, currentLine, invoiceDailyView.get(i)
	// .getDays());
	// sheet.addCell(label);
	// // write Total
	// label = new Label(8, currentLine, invoiceDailyView.get(i)
	// .getTotal());
	// sheet.addCell(label);
	// totalDaily = totalDaily.add(new BigDecimal(invoiceDailyView
	// .get(i).getTotal()));
	// currentLine++;
	// }
	// WritableCell cell = sheet.getWritableCell(10, firstRecord - 1);
	// Label label = new Label(10, firstRecord - 1,
	// String.valueOf(totalDaily), cell.getCellFormat());
	// sheet.addCell(label);
	// }
	//
	// // Write Exceptional Expense
	// currentLine = currentLine + 3;
	// if (invoiceExceptionalExpenseView != null) {
	// int firstRecord = currentLine;
	// for (int i = 0; i < invoiceExceptionalExpenseView.size(); i++) {
	// sheet.insertRow(currentLine);
	// // write Name
	// Label label = new Label(4, currentLine,
	// invoiceExceptionalExpenseView.get(i).getName());
	// sheet.addCell(label);
	// // write description
	// label = new Label(5, currentLine,
	// invoiceExceptionalExpenseView.get(i)
	// .getDescription());
	// sheet.addCell(label);
	// // write AffectTo
	// label = new Label(6, currentLine,
	// invoiceExceptionalExpenseView.get(i).getEffectTo());
	// sheet.addCell(label);
	// // write Effect
	// label = new Label(7, currentLine,
	// invoiceExceptionalExpenseView.get(i).getEffect());
	// sheet.addCell(label);
	// // write Total
	// label = new Label(8, currentLine,
	// invoiceExceptionalExpenseView.get(i).getTotal());
	// sheet.addCell(label);
	// totalExceptionalExpense = totalExceptionalExpense
	// .add(new BigDecimal(invoiceExceptionalExpenseView
	// .get(i).getTotal()));
	// currentLine++;
	// }
	// WritableCell cell = sheet.getWritableCell(10, firstRecord - 1);
	// Label label = new Label(10, firstRecord - 1,
	// String.valueOf(totalExceptionalExpense),
	// cell.getCellFormat());
	// sheet.addCell(label);
	// }
	//
	// // Write Exceptional Deduct
	// currentLine = currentLine + 3;
	// if (invoiceExceptionalDeductView != null) {
	// int firstRecord = currentLine;
	// for (int i = 0; i < invoiceExceptionalDeductView.size(); i++) {
	// sheet.insertRow(currentLine);
	// // write Name
	// Label label = new Label(4, currentLine,
	// invoiceExceptionalDeductView.get(i).getName());
	// sheet.addCell(label);
	// // write description
	// label = new Label(5, currentLine,
	// invoiceExceptionalDeductView.get(i)
	// .getDescription());
	// sheet.addCell(label);
	// // write AffectTo
	// label = new Label(6, currentLine,
	// invoiceExceptionalDeductView.get(i).getEffectTo());
	// sheet.addCell(label);
	// // write Effect
	// label = new Label(7, currentLine,
	// invoiceExceptionalDeductView.get(i).getEffect());
	// sheet.addCell(label);
	// // write Total
	// label = new Label(8, currentLine,
	// invoiceExceptionalDeductView.get(i).getTotal());
	// sheet.addCell(label);
	// totalExceptionalDeduct = totalExceptionalDeduct
	// .add(new BigDecimal(invoiceExceptionalDeductView
	// .get(i).getTotal()));
	// currentLine++;
	// }
	// WritableCell cell = sheet.getWritableCell(10, firstRecord - 1);
	// Label label = new Label(10, firstRecord - 1,
	// String.valueOf(totalExceptionalDeduct),
	// cell.getCellFormat());
	// sheet.addCell(label);
	// }
	//
	// // write Total
	// BigDecimal total = new BigDecimal("0");
	// total = total.add(totalOneTime);
	// total = total.add(totalDaily);
	// total = total.add(totalExceptionalExpense);
	// total = total.add(totalExceptionalDeduct);
	// WritableCell cell = sheet.getWritableCell(10, currentLine + 2);
	// Label label = new Label(10, currentLine + 2, String.valueOf(total),
	// cell.getCellFormat());
	// sheet.addCell(label);
	//
	// // Write project name, project Manager, Current Budget and date
	// // export
	// ProjectDao pDao = new ProjectDao();
	// Project project = pDao.getProject(projectId);
	// label = new Label(3, 2, project.getName());
	// sheet.addCell(label);
	// DeveloperDao dDao = new DeveloperDao();
	// Developer pm = dDao.getProjectManager(project);
	// label = new Label(3, 3, pm.getName());
	// sheet.addCell(label);
	// OopmsProjectCost projectCost = cDao.getProjectCost(project
	// .getProjectId());
	// label = new Label(3, 4, String.valueOf(projectCost
	// .getCurrentBudget()));
	// sheet.addCell(label);
	// label = new Label(10, 2, AppUtil.getDateAsFormat(new Date(),
	// Constant.DateFormat));
	// sheet.addCell(label);
	// // final
	// copy.write();
	// copy.close();
	//
	// File export = new File(exportPath);
	// byte[] a = getBytesFromFile(export);
	// response.reset();
	// OutputStream out = response.getPortletOutputStream();
	// response.setProperty("Content-Type", "application/octet-stream");
	// response.setProperty("Content-Disposition",
	// "attachment;filename=export.xls");
	// out.write(a);
	// out.flush();
	// out.close();
	// log.error("file lenght : " + a.length);
	// } catch (BiffException e) {
	// log.error("ERRORRO");
	// log.error(e.getMessage());
	// } catch (IOException e) {
	// log.error("ERRORRO");
	// log.error(e.getMessage());
	// } catch (WriteException e) {
	// log.error("ERRORRO");
	// log.error(e.getMessage());
	// }
	// }

	// Returns the contents of the file in a byte array.
	public static byte[] getBytesFromFile(File file) throws IOException {
		FileInputStream is = new FileInputStream(file);

		// Get the size of the file
		long length = file.length();

		// You cannot create an array using a long type.
		// It needs to be an int type.
		// Before converting to an int type, check
		// to ensure that file is not larger than Integer.MAX_VALUE.
		if (length > Integer.MAX_VALUE) {
			// File is too large
		}

		// Create the byte array to hold the data
		byte[] bytes = new byte[(int) length];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}

		// Close the input stream and return bytes
		is.close();
		return bytes;
	}

}
