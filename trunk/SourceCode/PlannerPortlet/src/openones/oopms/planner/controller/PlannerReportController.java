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
package openones.oopms.planner.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;

import openones.oopms.form.UserInfo;
import openones.oopms.planner.dao.AssignmentDAO;
import openones.oopms.planner.dao.TaskDAO;
import openones.oopms.planner.form.PlannerAddForm;
import openones.oopms.planner.form.PlannerForm;
import openones.oopms.planner.model.Developer;
import openones.oopms.planner.model.Project;
import openones.oopms.planner.model.ReportInfo;
import openones.oopms.planner.model.Tasks;
import openones.portlet.PortletSupport;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 * @author PNTG
 */
@Controller
@RequestMapping("VIEW")
public class PlannerReportController {
    private static Logger log = Logger.getLogger(PlannerReportController.class);

    @ActionMapping(params = "action=plannerReport")
    public void processPlannerReport(PlannerForm formBean, PlannerAddForm formBeanAdd, BindingResult result,
            SessionStatus status, ActionResponse response, ActionRequest request, PortletSession session) {
        log.debug("processPlannerReport.START");
        response.setRenderParameter("action", "plannerReport");
    }

    @RenderMapping(params = "action=plannerReport")
    public ModelAndView postPlannerReport(RenderRequest request, PortletSession session) {
        log.debug("postDashboard.START");
        ModelAndView mav = new ModelAndView("PlannerReport");
        PortletSupport portletSupport = new PortletSupport(request);
        String logonUser = portletSupport.getLogonUser();
        UserInfo userInfo = new UserInfo(logonUser);
        prepareReportInfo(userInfo, mav, PlannerController.developerId, PlannerController.projectDefault);

        return mav;
    }

    /**
     * Prepare information for PlannerReport.jsp.
     * @param userInfo
     * @param mav
     * @param session
     * @param formBean
     */
    void prepareReportInfo(UserInfo userInfo, ModelAndView mav, String developerId, String projectId) {
        log.debug("prepareReportInfo.START");

        TaskDAO taskDAO = new TaskDAO();
        AssignmentDAO assignmentDAO = new AssignmentDAO();
        List<ReportInfo> reportInfoList = new ArrayList<ReportInfo>();
        Project project = taskDAO.getProjectById(new BigDecimal(projectId));
        List<Developer> developers = assignmentDAO.getDeveloperByProject(new BigDecimal(projectId));
        ReportInfo reportInfo;

        for (int i = 0; i < developers.size(); i++) {
            reportInfo = new ReportInfo();
            reportInfo.setDeveloperName(developers.get(i).getName());
            reportInfo.setTotalTentativeTasks(taskDAO.getNumberOfTentativeTask(projectId, developers.get(i).getDeveloperId().toString()));
            reportInfo.setTotalOngoingTasks(taskDAO.getNumberOfOngoingTask(projectId, developers.get(i).getDeveloperId().toString()));
            reportInfo.setTotalClosedTasks(taskDAO.getNumberOfClosedTask(projectId, developers.get(i).getDeveloperId().toString()));
            reportInfo.setTotalCancelTasks(taskDAO.getNumberOfClosedTask(projectId, developers.get(i).getDeveloperId().toString()));
            reportInfoList.add(reportInfo);
        }

        mav.addObject("reportInfoList", reportInfoList);
        mav.addObject("project", project);
    }
}
