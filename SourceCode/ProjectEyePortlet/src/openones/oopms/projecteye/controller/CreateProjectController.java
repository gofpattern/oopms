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
import java.util.List;

import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;

import openones.oopms.projecteye.dao.DeveloperDao;
import openones.oopms.projecteye.dao.ProjectDao;
import openones.oopms.projecteye.form.CreateProjectForm;
import openones.oopms.projecteye.model.Assignment;
import openones.oopms.projecteye.model.Developer;
import openones.oopms.projecteye.model.Project;
import openones.oopms.projecteye.utils.Constant;
import openones.oopms.projecteye.validator.CreateProjectValidator;

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
public class CreateProjectController {
    
    Developer user = new Developer();
    /** Logger for logging. */
    private static Logger log = Logger.getLogger(CreateProjectController.class);


    /**
     * Process submitted form by clicking "Login" button.
     * @param formBean bean captures input data
     * @param result result of binding data
     * @param status status of session
     * @param response response of action
     */
    @ActionMapping(params = "action=CreateProject")
    public void processCreateProject(CreateProjectForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("process CreateProject.START");
        CreateProjectValidator validator = new CreateProjectValidator();
        validator.validate(formBean, result);
        if(!result.hasErrors()) {
        DeveloperDao dDao = new DeveloperDao();
		Developer dev = dDao.getDeveloper(ProjectEyeHomeController.username);
        
        ProjectDao pDao = new ProjectDao();
        Project project = new Project();
        
        //set value for project
        project.setCode(formBean.getProjectCode());
        project.setName(formBean.getProjectName());
    	project.setCustomer(formBean.getCustomer());
    	project.setCustomer2nd(formBean.getEndCustomer());
    	project.setPlanStartDate(formBean.getPlanStartDate());
    	project.setPlanFinishDate(formBean.getPlanEndDate());
    	project.setDescription(formBean.getScopeObjective());
    	project.setProjectCategoryCode(formBean.getProjectCategory_SelectedValue());
    	project.setType(formBean.getBusinessDomain_SelectedValue());
        project.setProjectStatusCode(formBean.getProjectStatus_SelectedValue());
    	
        Assignment assignment = new Assignment();
        //set value for assgment
        assignment.setDeveloperId(dev.getDeveloperId());
        assignment.setType(new Byte(Constant.ProjectOwnerAndProjectManagerType)); // type 1 is project Manager
        assignment.setBeginDate(formBean.getPlanStartDate());
    	//Call dao to insert project to database
        if(pDao.insertProject(project,assignment)) {
    		response.setRenderParameter("action", "CreateProject");
    		log.error("Insert success");
    	} else {
    		log.error("Cannot Insert");
    	}
        } else {
        	response.setRenderParameter("action", "GoCreateProject");
        	log.error("Error in binding result:" + result.getErrorCount());
        }
        
    }

    /**
     * Process after the action "login" (method "processLogin") is executed.
     * @return view "ViewDefectList" which next page "ViewDefectList.jsp" will displayed
     */
    @RenderMapping(params = "action=CreateProject")
    public ModelAndView postCreateProject(CreateProjectForm formBean, RenderRequest request) {
        log.debug("post CreateProject.START");
        ModelAndView mav = new ModelAndView("ProjectEyeHome");
        ProjectDao pDao = new ProjectDao();
        DeveloperDao dDao = new DeveloperDao();
		Developer dev = dDao.getDeveloper(ProjectEyeHomeController.username);
		List<Project> projectList = pDao.getProjectList(dev.getDeveloperId());
		mav.addObject("projectList", projectList);
        return mav;
    }
}
