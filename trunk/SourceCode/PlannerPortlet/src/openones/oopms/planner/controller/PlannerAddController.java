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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import openones.oopms.planner.dao.ModuleDAO;
import openones.oopms.planner.dao.TaskDAO;
import openones.oopms.planner.form.PlannerAddForm;
import openones.oopms.planner.form.PlannerForm;
import openones.oopms.planner.model.Developer;
import openones.oopms.planner.model.GeneralReference;
import openones.oopms.planner.model.Language;
import openones.oopms.planner.model.Module;
import openones.oopms.planner.model.Process;
import openones.oopms.planner.model.Stage;
import openones.oopms.planner.model.Tasks;
import openones.oopms.planner.model.Workproduct;

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
public class PlannerAddController {
    /** Logger for logging. */
    private static Logger log = Logger.getLogger(PlannerAddController.class);
    private List<Stage> stageList;
    private List<Process> processList;
    private List<GeneralReference> statusList;
    private List<Developer> developerList;
//    private List<Workproduct> productList;
    private List<Module> moduleList;
    private List<Language> sizeUnitList;
    TaskDAO taskDAO = new TaskDAO();
    ModuleDAO moduleDAO = new ModuleDAO();

    @ActionMapping(params = "action=plannerAdd")
    public void processPlannerAdd(PlannerForm formBean, PlannerAddForm formBeanAdd, BindingResult result,
            SessionStatus status, ActionResponse response) {
        log.debug("processPlannerAdd.START");

        // get project default to initial developer list
        formBeanAdd.setProjectId(PlannerController.projectDefault);

        statusList = taskDAO.getProjectStatusEn();
        stageList = taskDAO.getAllStage();
//        productList = moduleDAO.getWorkproductByProject(new BigDecimal(PlannerController.projectDefault));
        processList = taskDAO.getAllProcess();
        developerList = taskDAO.getDeveloper(formBeanAdd.getProjectId());
        moduleList = moduleDAO.getModuleByProject(new BigDecimal(PlannerController.projectDefault));
        sizeUnitList = taskDAO.getSizeUnit();

        // set value for statusMap
        for (int i = 0; i < statusList.size(); i++) {
            formBeanAdd.getStatusMap().put(statusList.get(i).getGeneralRefId().toString(),
                    statusList.get(i).getDescription());
        }

        // Set value for stageMap
        for (int i = 0; i < stageList.size(); i++) {
            formBeanAdd.getStageMap().put(stageList.get(i).getStageId().toString(), stageList.get(i).getName());
        }

        // Set value for developerMap
        for (int i = 0; i < developerList.size(); i++) {
            formBeanAdd.getDeveloperMap().put(developerList.get(i).getDeveloperId().toString(),
                    developerList.get(i).getName());
        }

//        // Set value for productMap
//        for (int i = 0; i < productList.size(); i++) {
//            formBeanAdd.getProductMap().put(productList.get(i).getWpId().toString(), productList.get(i).getName());
//        }

        // Set value for processMap
        for (int i = 0; i < processList.size(); i++) {
            formBeanAdd.getProcessMap().put(processList.get(i).getProcessId().toString(), processList.get(i).getName());
        }

        // Set value for moduleMap
        for (int i = 0; i < moduleList.size(); i++) {
            formBeanAdd.getModuleMap().put(moduleList.get(i).getModuleId().toString(), moduleList.get(i).getName());
        }

        // Set value for sizeUnitMap
        for (int i = 0; i < sizeUnitList.size(); i++) {
            formBeanAdd.getSizeUnitMap().put(sizeUnitList.get(i).getLanguageId().toString(),
                    sizeUnitList.get(i).getName().concat(" " + sizeUnitList.get(i).getSizeUnit()));
        }

        // Action for PlannerAddForm
        formBeanAdd.setAction_str("addTask");
        // to show hidden-add-form
        formBean.setFlag(1);
        formBean.setInit(false);
        response.setRenderParameter("action", "taskmanager");
    }

    @RenderMapping(params = "action=plannerAdd")
    public ModelAndView postPlannerAdd(PlannerForm formBean, PlannerAddForm formBeanAdd, RenderRequest request) {
        log.debug("postPlannerAdd.START");
        ModelAndView mav = new ModelAndView("TaskManager");
        return mav;
    }

    @ActionMapping(params = "action=addTask")
    public void processAddTask(PlannerForm formBean, PlannerAddForm formBeanAdd, BindingResult result,
            SessionStatus status, ActionResponse response) {
        log.debug("processAddTask.START");
        Tasks task = new Tasks();

        try {
            task = formBeanAdd.getTask();
            task.setProjectid(new BigDecimal(PlannerController.projectDefault));
            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
            task.setStartdate(dateFormat.parse(formBeanAdd.getStartDate()));
            task.setPlanDate(dateFormat.parse(formBeanAdd.getActualDate()));

            moduleDAO.updateModuleByTask(task);
            taskDAO.addTask(task);
        } catch (ParseException ex) {
            log.error("error when add new task", ex);
        }

        formBean.setInit(true);
        response.setRenderParameter("action", "taskmanager");
    }

    @ActionMapping(params = "action=plannerEdit")
    public void processPlannerEdit(PlannerForm formBean, PlannerAddForm formBeanAdd, BindingResult result,
            SessionStatus status, ActionResponse response) {
        log.debug("processEditTask.ACTION.START");

        Tasks task = new Tasks();
        task = taskDAO.getTaskById(new BigDecimal(formBean.getTaskId()));

        formBean.setProjectId(PlannerController.projectDefault);

        statusList = taskDAO.getProjectStatusEn();
        stageList = taskDAO.getAllStage();
//        productList = taskDAO.getAllProduct();
        processList = taskDAO.getAllProcess();
        developerList = taskDAO.getDeveloper(formBean.getProjectId());
        moduleList = moduleDAO.getModuleByProject(new BigDecimal(PlannerController.projectDefault));
        sizeUnitList = taskDAO.getSizeUnit();

        // set value for statusMap
        formBeanAdd.getStatusMap().clear();
        formBeanAdd.getStatusMap().put(task.getStatusid().toString(), "");
        for (int i = 0; i < statusList.size(); i++) {
            formBeanAdd.getStatusMap().put(statusList.get(i).getGeneralRefId().toString(),
                    statusList.get(i).getDescription());
        }

        // Set value for stageMap
        formBeanAdd.getStageMap().clear();
        formBeanAdd.getStageMap().put(task.getStageid().toString(), "");
        for (int i = 0; i < stageList.size(); i++) {
            formBeanAdd.getStageMap().put(stageList.get(i).getStageId().toString(), stageList.get(i).getName());
        }

        // Set value for developerMap
        formBeanAdd.getDeveloperMap().clear();
        formBeanAdd.getDeveloperMap().put(task.getAssignedto().toString(), "");
        for (int i = 0; i < developerList.size(); i++) {
            formBeanAdd.getDeveloperMap().put(developerList.get(i).getDeveloperId().toString(),
                    developerList.get(i).getName());
        }

//        // Set value for productMap
//        formBeanAdd.getProductMap().clear();
//        formBeanAdd.getProductMap().put(task.getProduct().toString(), "");
//        for (int i = 0; i < productList.size(); i++) {
//            formBeanAdd.getProductMap().put(productList.get(i).getWpId().toString(), productList.get(i).getName());
//        }

        // Set value for processMap
        formBeanAdd.getProcessMap().clear();
        formBeanAdd.getProcessMap().put(task.getProcess().toString(), "");
        for (int i = 0; i < processList.size(); i++) {
            formBeanAdd.getProcessMap().put(processList.get(i).getProcessId().toString(), processList.get(i).getName());
        }

        // Set value for moduleMap
        formBeanAdd.getModuleMap().clear();
        if (!task.getModule().equals(null))
            formBeanAdd.getModuleMap().put(task.getModule().getModuleId().toString(), "");
        for (int i = 0; i < moduleList.size(); i++) {
            formBeanAdd.getModuleMap().put(moduleList.get(i).getModuleId().toString(), moduleList.get(i).getName());
        }

        // Set value for sizeUnitMap
        formBeanAdd.getSizeUnitMap().clear();
        formBeanAdd.getSizeUnitMap().put(task.getSizeunit().toString(), "");
        for (int i = 0; i < sizeUnitList.size(); i++) {
            formBeanAdd.getSizeUnitMap().put(sizeUnitList.get(i).getLanguageId().toString(),
                    sizeUnitList.get(i).getName().concat(" " + sizeUnitList.get(i).getSizeUnit()));
        }

        // Convert date to string
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        task.setStartdate_str(dateFormat.format(task.getStartdate()));
        task.setPlanDate_str(dateFormat.format(task.getPlanDate()));

        // Value for PlannerAddForm
        formBeanAdd.setEditTask(task);

        // Set Action for PlannerAddForm
        formBeanAdd.setAction_str("editTask");

        // To show hidden-add-form
        formBean.setFlag(1);
        // Reload taskList
        formBean.setInit(false);
        response.setRenderParameter("action", "taskmanager");
    }

    @RenderMapping(params = "action=plannerEdit")
    public void postPlannerEdit(PlannerForm formBean, PlannerAddForm formBeanAdd, BindingResult result,
            SessionStatus status, ActionResponse response) {
        log.debug("postEditTask.RENDER.START");
    }

    @ActionMapping(params = "action=editTask")
    public void processEditTask(PlannerForm formBean, PlannerAddForm formBeanAdd, BindingResult result,
            SessionStatus status, ActionResponse response) {
        log.debug("processEditTask.START");
        Tasks editedTask = new Tasks();
        Tasks task = taskDAO.getTaskById(formBeanAdd.getTask().getTaskid());
        try {
            editedTask = formBeanAdd.getTask();
            editedTask.setProjectid(new BigDecimal(PlannerController.projectDefault));// get id from plannerController
            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
            editedTask.setStartdate(dateFormat.parse(formBeanAdd.getStartDate()));
            editedTask.setPlanDate(dateFormat.parse(formBeanAdd.getActualDate()));

            moduleDAO.updateModuleByEditedTask(task, editedTask);
            taskDAO.updateTask(editedTask);            

        } catch (Exception ex) {
            log.error("error when update task", ex);
        }
        // formBean.setFlag(true);
        formBean.setInit(true);
        response.setRenderParameter("action", "taskmanager");
    }
}
