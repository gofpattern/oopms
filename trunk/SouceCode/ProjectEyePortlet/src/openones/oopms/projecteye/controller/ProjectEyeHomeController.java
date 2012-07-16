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
import java.util.List;
import java.util.Map;

import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import openones.oopms.projecteye.dao.ProjectDao;
import openones.oopms.projecteye.dao.RiskDao;
import openones.oopms.projecteye.form.CreateRiskForm;
import openones.oopms.projecteye.form.ProjectEyeHomeForm;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.Project;
import openones.oopms.projecteye.model.RiskSource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class ProjectEyeHomeController {
    
    Developer user = new Developer();
    /** Logger for logging. */
    private static Logger log = Logger.getLogger(ProjectEyeHomeController.class);

    /**
     * Default screen. 
     * @return name of view which is the name of the JSP page.
     */
    @RequestMapping
    public String initScreen(RenderRequest request) {
        log.debug("initScreen.START conme");
        ProjectDao pDao = new ProjectDao();
        List<Project> projectList = pDao.getProjectList("1");
        request.setAttribute("projectList", projectList);
            return "ProjectEyeHome";
       
    }
    /**
     * Create bean for form.
     * @return Form bean for UI.
     */
    @ModelAttribute("ProjectEyeHomeForm")
    public ProjectEyeHomeForm getCommandObject() {
        log.debug("CreateBean for Home JSP");
        ProjectEyeHomeForm formBean = new ProjectEyeHomeForm();
        return formBean;
    }

    /**
     * Process submitted form by clicking "Create New Project" button.
     * @param formBean bean captures input data
     * @param result result of binding data
     * @param status status of session
     * @param response response of action
     */
    @ActionMapping(params = "action=homeCreateProject")
    public void processCreateProject(ProjectEyeHomeForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("processHomeCreateProject.START");
        response.setRenderParameter("action", "homeCreateProject");    
    }

    /**
     * Process after the action "Create New Project"
     * @return view "CreateProject" which next page "CreateProject.jsp" will displayed
     */
    @RenderMapping(params = "action=homeCreateProject")
    public ModelAndView postCreateProject(ProjectEyeHomeForm formBean, RenderRequest request) {
        log.debug("postCreateProject.START");
        ModelAndView mav = new ModelAndView("CreateProject");
        return mav;
    }
    
    /**
     * Process submitted form by clicking "Create New Product" button.
     * @param formBean bean captures input data
     * @param result result of binding data
     * @param status status of session
     * @param response response of action
     */
    @ActionMapping(params = "action=homeCreateProduct")
    public void processCreateProduct(ProjectEyeHomeForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("processHomeCreateProduct.START");
        response.setRenderParameter("action", "homeCreateProduct");    
    }

    /**
     * Process after the action "Create New Product"
     * @return view "CreateProduct" which next page "CreateProduct.jsp" will displayed
     */
    @RenderMapping(params = "action=homeCreateProduct")
    public ModelAndView postCreateProduct(ProjectEyeHomeForm formBean, RenderRequest request) {
        log.debug("postCreateProduct.START");
        ModelAndView mav = new ModelAndView("CreateProduct");
        return mav;
    }
    
    /**
     * Process submitted form by clicking "Create New Product" button.
     * @param formBean bean captures input data
     * @param result result of binding data
     * @param status status of session
     * @param response response of action
     */
    @ActionMapping(params = "action=homeCreateRisk")
    public void processCreateRisk(ProjectEyeHomeForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("processHomeCreateProduct.START");
        response.setRenderParameter("action", "homeCreateRisk");    
    }

    /**
     * Process after the action "Create New Product"
     * @return view "CreateProduct" which next page "CreateProduct.jsp" will displayed
     */
    @RenderMapping(params = "action=homeCreateRisk")
    public ModelAndView postCreateRisk(ProjectEyeHomeForm formBean, RenderRequest request) {
        log.debug("postCreateRisk.START cc");
        CreateRiskForm riskFormBean = new CreateRiskForm();
		RiskDao rDao = new RiskDao();
		ArrayList<RiskSource> riskSource = rDao.getRiskSourceList();
		Map<String, String> riskSourcetMap = new LinkedHashMap<String, String>();
		riskSourcetMap.put(" ", " ");
		for (int i = 0; i < riskSource.size(); i++) {
			riskSourcetMap.put(riskSource.get(i).getSourceId().toString(),
					riskSource.get(i).getSourceName());
		}
		riskFormBean.setRiskSource(riskSourcetMap);
		riskFormBean.setRiskSource_SelectedValue(" ");
        ModelAndView mav = new ModelAndView("CreateRisk");
        request.setAttribute("CreateRiskForm", riskFormBean);
     // Set default value for risk source
        mav.addObject("riskSource", riskFormBean.getRiskSource());
        mav.addObject("riskSource_SelectedValue", riskFormBean.getRiskSource_SelectedValue());
        return mav;
    }
    
    @ActionMapping(params = "action=homeProjectDetail")
    public void processProjectDetail(BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("processHomeProjectDetail.START");
        response.setRenderParameter("action", "homeProjectDetail");    
    }
    
    @RenderMapping(params = "action=homeProjectDetail")
    public ModelAndView postProjectDetail(RenderRequest request) {
        log.debug("postProjectDetail.START");
        ModelAndView mav = new ModelAndView("ProjectDetail");
        String projectId = request.getParameter("projectId");
        log.debug("project ID la "+ projectId);
        mav.addObject("projectId", projectId);
        return mav;
    }
}
