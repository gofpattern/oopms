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

import openones.oopms.projecteye.form.CreateIssueForm;

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
public class CreateIssueController {

	/** Logger for logging. */
	private static Logger log = Logger.getLogger(CreateIssueController.class);
	String projectId;

	/**
	 * Process submitted form by clicking "Add" button.
	 * 
	 * @param formBean
	 *            bean captures input data
	 * @param result
	 *            result of binding data
	 * @param status
	 *            status of session
	 * @param response
	 *            response of action
	 */
	@ActionMapping(params = "action=CreateIssue")
	public void processCreateIssue(CreateIssueForm formBean,
			BindingResult result, SessionStatus status, ActionResponse response) {
		// log.debug("process CreateIssue.START");
		// IssueDao iDao = new IssueDao();
		// Issue issue = new Issue();
		// projectId = formBean.getProjectId();
		// // set value for risk
		// Project project = new Project();
		// project.setProjectId(new BigDecimal(projectId));
		// issue.setProject(project);
		// issue.setOwner(ProjectEyeHomeController.username);
		// issue.setDescription(formBean.getDescription());
		// issue.setPriorityid(Integer.parseInt(formBean
		// .getPriority_SelectedValue()));
		// issue.setStatusid(Integer.parseInt(formBean.getStatus_SelectedValue()));
		// issue.setTypeid(Integer.parseInt(formBean.getType_SelectedValue()));
		// issue.setProcessId(Short.parseShort(formBean
		// .getProcessRelated_SelectedValue()));
		// issue.setStartdate(new Date());
		// issue.setDuedate(formBean.getDueDate());
		// issue.setCloseddate(formBean.getClosedDate());
		// issue.setComments(formBean.getCommentSolution());
		// issue.setReference(formBean.getReference());
		// // Call dao to insert risk to database
		// if (iDao.insertIssue(issue)) {
		// response.setRenderParameter("action", "CreateIssue");
		// log.error("Insert success");
		// } else {
		// log.error("Cannot Insert");
		// }
		response.setRenderParameter("action", "CreateIssue");
	}

	/**
	 * Process after the action "login" (method "processLogin") is executed.
	 * 
	 * @return view "ViewDefectList" which next page "ViewDefectList.jsp" will
	 *         displayed
	 */
	@RenderMapping(params = "action=CreateIssue")
	public ModelAndView postCreateStage(RenderRequest request) {
		log.debug("post CreateStage.START");
		ModelAndView mav = new ModelAndView("RiskIssue");
		log.debug("project ID la " + projectId);
		mav.addObject("projectId", projectId);
		return mav;
	}

}
