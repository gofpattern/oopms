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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import openones.oopms.projecteye.dao.RiskDao;
import openones.oopms.projecteye.form.CreateRiskForm;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.RiskSource;

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

	@ActionMapping(params = "action=GoCreateStage")
    public void processGoCreateStage(BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("process GoCreateStage.START");
        response.setRenderParameter("action", "GoCreateStage");    
    }
    
	@RenderMapping(params = "action=GoCreateStage")
    public ModelAndView postGoCreateStage(RenderRequest request) {
        log.debug("post GoCreateIssue.START");
        ModelAndView mav = new ModelAndView("CreateStage");
        String projectId = request.getParameter("projectId");
        log.debug("project ID la "+ projectId);
        mav.addObject("projectId", projectId);
        return mav;
    }
    
    
    @ActionMapping(params = "action=GoCreateDeliverable")
    public void processGoCreateDeliverable(BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("process GoCreateDeliverable.START");
        response.setRenderParameter("action", "GoCreateDeliverable");    
    }
    
    @RenderMapping(params = "action=GoCreateDeliverable")
    public ModelAndView postGoCreateDeliverable(RenderRequest request) {
        log.debug("post GoCreateDeliverable.START");
        ModelAndView mav = new ModelAndView("CreateDeliverable");
        String projectId = request.getParameter("projectId");
        log.debug("project ID la "+ projectId);
        mav.addObject("projectId", projectId);
        return mav;
    }
    
}
