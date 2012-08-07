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
import openones.oopms.projecteye.dao.WorkOrderDao;
import openones.oopms.projecteye.form.CreateDeliverableForm;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.Module;
import openones.oopms.projecteye.utils.Constant;

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
public class CreateDeliverableController {

	Developer user = new Developer();
	/** Logger for logging. */
	private static Logger log = Logger
			.getLogger(CreateDeliverableController.class);
	String projectId;

	@ActionMapping(params = "action=CreateDeliverable")
	public void processCreateDeliverable(CreateDeliverableForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		log.debug("process CreateDeliverable.START");
		projectId = formBean.getProjectId();
		WorkOrderDao woDao = new WorkOrderDao();
		ProductDao pDao = new ProductDao();

		Module deliverable = woDao.getProduct(formBean
				.getDeliverable_SelectedValue());

		// set value for Deliverable
		deliverable.setIsDeliverable(new BigDecimal(Constant.SettedDeliverable));
		deliverable.setPlannedReleaseDate(formBean.getPlannedCommittedDate());
		deliverable.setReplannedReleaseDate(formBean
				.getRePlannedCommittedDate());
		deliverable.setActualReleaseDate(formBean.getActualCommittedDate());
		deliverable.setBaselineNote(formBean.getNote());

		// Call dao to insert Deliverable to database
		if (woDao.insertDeliverable(deliverable)) {
			response.setRenderParameter("action", "GoWorkOrder");
			response.setRenderParameter("projectId", projectId);
			log.error("Insert success");
		} else {
			log.error("Cannot Insert");
		}

	}
}
