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
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import openones.oopms.projecteye.dao.MilestoneDao;
import openones.oopms.projecteye.dao.ProductDao;
import openones.oopms.projecteye.dao.ProjectDao;
import openones.oopms.projecteye.dao.WorkOrderDao;
import openones.oopms.projecteye.form.CreateDeliverableForm;
import openones.oopms.projecteye.form.UpdateDeliverableForm;
import openones.oopms.projecteye.form.UpdateStageForm;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.Milestone;
import openones.oopms.projecteye.model.Module;
import openones.oopms.projecteye.model.Project;
import openones.oopms.projecteye.utils.Constant;
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
public class WorkOrderController {

	Developer user = new Developer();
	/** Logger for logging. */
	private static Logger log = Logger.getLogger(WorkOrderController.class);

	@ActionMapping(params = "action=GoUpdateStage")
	public void processGoUpdateStage(BindingResult result,
			SessionStatus status, ActionResponse response) {
		log.debug("process GoUpdateStage.START");
		response.setRenderParameter("action", "GoUpdateStage");
	}

	@RenderMapping(params = "action=GoUpdateStage")
	public ModelAndView postGoUpdateStage(RenderRequest request) {
		log.debug("post GoCreateIssue.START");
		// get stage list
		MilestoneDao mDao = new MilestoneDao();
		String stageId = request.getParameter("stageId");
		String stageNumber = request.getParameter("stageNumber");
		String projectId = request.getParameter("projectId");
		UpdateStageForm formBean = new UpdateStageForm();
		DateFormat df = new java.text.SimpleDateFormat("MM/dd/yyyy");
		if (!stageNumber.equals("6")) {
			int nextStageId = Integer.parseInt(stageId) + 1;
			Milestone stage = mDao.getStage(String.valueOf(nextStageId));
			if (stage.getBaseFinishDate() != null) {
				formBean.setPlannedEndDateOfNextStage(df.format(stage
						.getBaseFinishDate()));
			} else {
				ProjectDao pDao = new ProjectDao();
				Project project = pDao.getProject(projectId);
				formBean.setPlannedEndDateOfNextStage(df.format(project
						.getPlanFinishDate()));
			}
		} else {
			ProjectDao pDao = new ProjectDao();
			Project project = pDao.getProject(projectId);
			formBean.setPlannedEndDateOfNextStage(df.format(project
					.getPlanFinishDate()));
		}
		Milestone stage = mDao.getStage(stageId);
		formBean.setPlannedEndDate(stage.getBaseFinishDate());
		formBean.setRePlannedEndDate(stage.getPlanFinishDate());
		formBean.setActualEndDate(stage.getActualFinishDate());
		formBean.setDescription(HTMLTag.replaceHTMLTag(stage.getDescription()));
		formBean.setMilestone(HTMLTag.replaceHTMLTag(stage.getMilestone()));
		formBean.setStage(stage.getName());
		ModelAndView mav = new ModelAndView("UpdateStage", "UpdateStageForm",
				formBean);

		log.debug("project ID la " + projectId);
		mav.addObject("projectId", projectId);
		mav.addObject("stageId", stageId);
		return mav;
	}

	@ActionMapping(params = "action=GoCreateDeliverable")
	public void processGoCreateDeliverable(BindingResult result,
			SessionStatus status, ActionResponse response) {
		log.debug("process GoCreateDeliverable.START");
		response.setRenderParameter("action", "GoCreateDeliverable");
	}

	@RenderMapping(params = "action=GoCreateDeliverable")
	public ModelAndView postGoCreateDeliverable(RenderRequest request) {
		log.debug("post GoCreateDeliverable.START");
		String projectId = request.getParameter("projectId");
		// get status list
		WorkOrderDao woDao = new WorkOrderDao();
		ProjectDao pDao = new ProjectDao();
		Project project = pDao.getProject(projectId);
		List<Module> productList = woDao
				.getUnsetDeliverableProductList(project);
		Map<String, String> productMap = new LinkedHashMap<String, String>();
		if (productList.size() != 0) {
			for (int i = 0; i < productList.size(); i++) {
				productMap.put(productList.get(i).getModuleId().toString(),
						productList.get(i).getName());
			}
		}
		CreateDeliverableForm bean = new CreateDeliverableForm();
		DateFormat df = new java.text.SimpleDateFormat("MM/dd/yyyy");
		bean.setPlannedEndDateOfProject(df.format(project.getPlanFinishDate()));
		ModelAndView mav = new ModelAndView("CreateDeliverable",
				"CreateDeliverableForm", bean);
		mav.addObject("deliverable", productMap);
		log.debug("project ID la " + projectId);
		mav.addObject("projectId", projectId);
		return mav;
	}

	@RenderMapping(params = "action=GoUpdateDeliverable")
	public ModelAndView postGoUpdateDeliverable(RenderRequest request) {
		log.debug("post GoCreateDeliverable.START");
		String projectId = request.getParameter("projectId");
		String delivarableId = request.getParameter("delivarableId");
		ProjectDao pDao = new ProjectDao();
		Project project = pDao.getProject(projectId);
		// get deliverable info
		ProductDao productDao = new ProductDao();
		Module deliverable = productDao
				.getProduct(new BigDecimal(delivarableId));
		UpdateDeliverableForm bean = new UpdateDeliverableForm();
		bean.setDelivarableName(deliverable.getName());
		bean.setNote(deliverable.getNote());
		DateFormat df = new java.text.SimpleDateFormat("MM/dd/yyyy");
		bean.setPlannedCommittedDate(df.format(deliverable
				.getPlannedReleaseDate()));
		if (deliverable.getReplannedReleaseDate() != null) {
			bean.setRePlannedCommittedDate(df.format(deliverable
					.getReplannedReleaseDate()));
		}
		if (deliverable.getActualReleaseDate() != null) {
			bean.setActualCommittedDate(df.format(deliverable
					.getActualReleaseDate()));
		}
		bean.setPlannedEndDateOfProject(df.format(project.getPlanFinishDate()));
		ModelAndView mav = new ModelAndView("UpdateDeliverable",
				"UpdateDeliverableForm", bean);
		log.debug("project ID la " + projectId);
		mav.addObject("projectId", projectId);
		mav.addObject("delivarableId", delivarableId);
		return mav;
	}
	
	@RenderMapping(params = "action=RemoveDeliverable")
	public ModelAndView postRemoveDeliverable(RenderRequest request) {
		log.debug("post RemoveDeliverable.START");
		ModelAndView mav = new ModelAndView("WorkOrder");
		//Remove diliverable
		String delivarableId = request.getParameter("delivarableId");
		ProductDao productDao = new ProductDao();
		Module deliverable = productDao.getProduct(new BigDecimal(delivarableId));
		deliverable.setIsDeliverable(new BigDecimal(Constant.UnsettedDeliverable));
		deliverable.setPlannedReleaseDate(null);
		deliverable.setReplannedReleaseDate(null);
		deliverable.setActualReleaseDate(null);
		productDao.updateProduct(deliverable);
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

}
