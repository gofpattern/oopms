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

import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import openones.oopms.projecteye.model.Developer;

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
public class NavigatorController {

	Developer user = new Developer();
	/** Logger for logging. */
	private static Logger log = Logger.getLogger(NavigatorController.class);

	@ActionMapping(params = "action=GoRiskIssue")
    public void processGoRiskIssue(BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("process GoRiskIssue.START");
        response.setRenderParameter("action", "GoRiskIssue");    
    }
    
    @RenderMapping(params = "action=GoRiskIssue")
    public ModelAndView postGoRiskIssue(RenderRequest request) {
        log.debug("post GoRiskIssue.START");
        ModelAndView mav = new ModelAndView("RiskIssue");
        String projectId = request.getParameter("projectId");
        log.debug("project ID la "+ projectId);
        mav.addObject("projectId", projectId);
        return mav;
    }
    
    
    @ActionMapping(params = "action=GoChangeRequest")
    public void processGoChangeRequest(BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("process GoChangeRequest.START");
        response.setRenderParameter("action", "GoChangeRequest");    
    }
    
    @RenderMapping(params = "action=GoChangeRequest")
    public ModelAndView postGoChangeRequest(RenderRequest request) {
        log.debug("post GoChangeRequest.START");
        ModelAndView mav = new ModelAndView("ChangeRequest");
        String projectId = request.getParameter("projectId");
        log.debug("project ID la "+ projectId);
        mav.addObject("projectId", projectId);
        return mav;
    }
    
    
    @ActionMapping(params = "action=GoProduct")
    public void processGoProduct(BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("process GoProduct.START");
        response.setRenderParameter("action", "GoProduct");    
    }
    
    @RenderMapping(params = "action=GoProduct")
    public ModelAndView postGoProduct(RenderRequest request) {
        log.debug("post GoProduct.START");
        ModelAndView mav = new ModelAndView("Product");
        String projectId = request.getParameter("projectId");
        log.debug("project ID la "+ projectId);
        mav.addObject("projectId", projectId);
        return mav;
    }
    
    
    @ActionMapping(params = "action=GoWorkOrder")
    public void processGoWorkOrder(BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("process GoWorkOrder.START");
        response.setRenderParameter("action", "GoWorkOrder");    
    }
    
    @RenderMapping(params = "action=GoWorkOrder")
    public ModelAndView postGoWorkOrder(RenderRequest request) {
        log.debug("post GoWorkOrder.START");
        ModelAndView mav = new ModelAndView("WorkOrder");
        String projectId = request.getParameter("projectId");
        log.debug("project ID la "+ projectId);
        mav.addObject("projectId", projectId);
        return mav;
    }
    
    
    @ActionMapping(params = "action=GoCostManagement")
    public void processGoCostManagement(BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("process GoCostManagement.START");
        response.setRenderParameter("action", "GoCostManagement");    
    }
    
    @RenderMapping(params = "action=GoCostManagement")
    public ModelAndView postGoCostManagement(RenderRequest request) {
        log.debug("post GoCostManagement.START");
        ModelAndView mav = new ModelAndView("CostManagement");
        String projectId = request.getParameter("projectId");
        log.debug("project ID la "+ projectId);
        mav.addObject("projectId", projectId);
        return mav;
    }
}
