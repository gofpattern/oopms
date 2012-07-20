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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import openones.oopms.planner.dao.TaskDAO;
import openones.oopms.planner.form.PlannerAddForm;
import openones.oopms.planner.form.PlannerForm;
import openones.oopms.planner.model.Developer;
import openones.oopms.planner.model.Process;
import openones.oopms.planner.model.ProjectStatus;
import openones.oopms.planner.model.Stage;
import openones.oopms.planner.model.Tasks;
import openones.oopms.planner.model.Workproduct;

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
 * @author PNTG
 *
 */
@Controller
@RequestMapping("VIEW")
public class PlannerAddController {
    /** Logger for logging. */
    private static Logger log = Logger.getLogger(PlannerAddController.class);
    private List<Stage> stageList;
    private List<Process> processList;
    private List<ProjectStatus> statusList;
    private List<Developer> developerList;
    private List<Workproduct> productList;    

//    @ModelAttribute("PlannerAddForm")
//    public PlannerAddForm getCommandObjectSubForm() {
//        log.debug("getCommandObjectSubForm.START");
//        PlannerAddForm formBeanAdd = new PlannerAddForm();
//        //PlannerForm formBeanAddPlannerForm = new PlannerForm();
//        return formBeanAdd;
//    }
    
//    @ModelAttribute("PlannerForm")
//    public PlannerForm getCommandObject() {
//        log.debug("getCommandObject.START");
//        PlannerForm formBean = new PlannerForm();
//        return formBean;
//    }
    
    @ActionMapping(params = "action=plannerAdd")
    public void processPlannerAdd(PlannerForm formBean, PlannerAddForm formBeanAdd, BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("processPlannerAdd.START");
        TaskDAO taskDAO = new TaskDAO();
//        ModelAndView mav = new ModelAndView("TaskManager");
//        
        formBean.setProjectId("118385");
//
        formBeanAdd.setActualEffort("10");
        formBeanAdd.setPlannedEffort("10");
        
        Map<String, String> statusMap = new LinkedHashMap<String, String>();
        Map<String, String> stageMap = new LinkedHashMap<String, String>();
        Map<String, String> developerMap = new LinkedHashMap<String, String>();
        Map<String, String> processMap = new LinkedHashMap<String, String>();
        Map<String, String> productMap = new LinkedHashMap<String, String>();

        statusList = taskDAO.getAllStatus();
        stageList = taskDAO.getAllStage();
        productList = taskDAO.getAllProduct();
        processList = taskDAO.getAllProcess();
        developerList = taskDAO.getDeveloper(formBean.getProjectId());

        // set value for statusMap
        for (int i = 0; i < statusList.size(); i++) {
            statusMap.put(statusList.get(i).getProjectStatusId().toString(), statusList.get(i).getProjectStatusName());
        }

        // Set value for stageMap
        for (int i = 0; i < stageList.size(); i++) {
            stageMap.put(stageList.get(i).getStageId().toString(), stageList.get(i).getName());
        }

        // Set value for developerMap
        for (int i = 0; i < developerList.size(); i++) {
            developerMap.put(developerList.get(i).getDeveloperId().toString(), developerList.get(i).getName());
        }
        
     // Set value for productMap
        for (int i = 0; i < productList.size(); i++) {
            productMap.put(productList.get(i).getWpId().toString(), productList.get(i).getName());
        }
        
        // Set value for processMap
        for (int i = 0; i < processList.size(); i++) {
            processMap.put(processList.get(i).getProcessId().toString(), processList.get(i).getName());
        }
        
        formBeanAdd.setStatusMap(statusMap);
        formBeanAdd.setProcessMap(processMap);
        formBeanAdd.setStageMap(stageMap);
        formBeanAdd.setDeveloperMap(developerMap);
        formBeanAdd.setProductMap(productMap);
        
        
        response.setRenderParameter("action", "taskmanager");
    }
    
    @RenderMapping(params = "action=plannerAdd")
    public ModelAndView postPlannerAdd(PlannerForm formBean, PlannerAddForm formBeanAdd, RenderRequest request) {
        log.debug("postPlannerAdd.START");
        ModelAndView mav = new ModelAndView("TaskManager");
        return mav;
    }
    
    @ActionMapping(params = "action=addTask")
    public void processAddTask(PlannerForm formBean, PlannerAddForm formBeanAdd, BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("processAddTask.START");
        
        TaskDAO taskDAO = new TaskDAO();
        Tasks task = new Tasks();
        
        try {
            task.setTaskname(formBeanAdd.getTitle());
            task.setTaskcode("NEWTASK");
            task.setStageid(new BigDecimal(formBeanAdd.getStageId()));
            task.setProcessId(new BigDecimal(formBeanAdd.getProcessId()));
            task.setDeveloperid(new BigDecimal(formBeanAdd.getDeveloperId()));
            task.setPlannedeffort(new BigDecimal(formBeanAdd.getPlannedEffort()));
            task.setActualeffort(new BigDecimal(formBeanAdd.getActualEffort()));
            
            taskDAO.addTask(task);
        } catch (Exception ex) {
            log.error("error when add new task", ex);
        }
        response.setRenderParameter("action", "taskmanager");   
    }
}
