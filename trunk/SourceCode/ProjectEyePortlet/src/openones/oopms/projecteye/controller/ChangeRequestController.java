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

import openones.oopms.projecteye.dao.ChangeRequestDao;
import openones.oopms.projecteye.form.CreateChangeRequestForm;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.Ncconstant;

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
public class ChangeRequestController {

	Developer user = new Developer();
	/** Logger for logging. */
	private static Logger log = Logger.getLogger(ChangeRequestController.class);

	@ActionMapping(params = "action=GoCreateChangeRequest")
	public void processGoCreateChangeRequest(BindingResult result,
			SessionStatus status, ActionResponse response) {
		log.debug("process GoCreateChangeRequest.START");
		response.setRenderParameter("action", "GoCreateChangeRequest");
	}

	@RenderMapping(params = "action=GoCreateChangeRequest")
	public ModelAndView postGoCreateChangeRequest(RenderRequest request) {
		log.debug("post GoCreateChangeRequest.START");
		ChangeRequestDao crDao = new ChangeRequestDao();
		// get status List
		List<Ncconstant> statusList = crDao.getStatusList();
		Map<String, String> statusMap = new LinkedHashMap<String, String>();
		for (int i = 0; i < statusList.size(); i++) {
			statusMap.put(statusList.get(i).getConstantid().toString(),
					statusList.get(i).getDescription());
		}
		CreateChangeRequestForm projectFormBean = new CreateChangeRequestForm();
		request.setAttribute("CreateChangeRequestForm", projectFormBean);
		ModelAndView mav = new ModelAndView("CreateChangeRequest");
		mav.addObject("status", statusMap);
		String projectId = request.getParameter("projectId");
		log.debug("project ID la " + projectId);
		mav.addObject("projectId", projectId);
		return mav;
	}

}
