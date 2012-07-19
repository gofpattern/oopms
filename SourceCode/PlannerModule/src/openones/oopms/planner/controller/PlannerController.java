package openones.oopms.planner.controller;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import openones.oopms.planner.dao.TaskDAO;
import openones.oopms.planner.form.PlannerForm;
import openones.oopms.planner.model.Developer;
import openones.oopms.planner.model.Process;
import openones.oopms.planner.model.Project;
import openones.oopms.planner.model.ProjectStatus;
import openones.oopms.planner.model.Stage;
import openones.oopms.planner.model.Tasks;
import openones.oopms.planner.model.Workproduct;

import org.apache.log4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

@Controller
@RequestMapping("VIEW")
public class PlannerController {
    /** Logger for logging. */
    private static Logger log = Logger.getLogger(PlannerController.class);
    private List<Tasks> taskList;
    private List<Stage> stageList;
    private List<Process> processList;
    private List<ProjectStatus> statusList;
    private List<Developer> developerList;
    private List<Project> projectList;
    private List<Workproduct> productList;

    /**
     * Create bean for form.
     * @return Form bean for UI.
     */
    @ModelAttribute("PlannerForm")
    public PlannerForm getCommandObject() {
        log.debug("getCommandObject.START");

        PlannerForm formBean = new PlannerForm();
        return formBean;
    }

    @ActionMapping(params = "action=taskmanager")
    public void processPlanner(PlannerForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("processPlanner.START");

    }
    @RenderMapping(params = "action=taskmanager")
    public ModelAndView postPlanner(PlannerForm formBean, RenderRequest request) {
        log.debug("postPlanner.START");
        TaskDAO taskDAO = new TaskDAO();
        ModelAndView mav = new ModelAndView("TaskManager");
        
        formBean.setProjectId("118385");

        formBean.setActualEffort("10");
        formBean.setPlannedEffort("10");

        formBean.setStatusDefault("All");
        formBean.setStageDefault("All");
        formBean.setDeveloperDefault("All");
        formBean.setProjectDefault("All");
        Map<String, String> statusMap = new LinkedHashMap<String, String>();
        Map<String, String> stageMap = new LinkedHashMap<String, String>();
        Map<String, String> developerMap = new LinkedHashMap<String, String>();
        Map<String, String> processMap = new LinkedHashMap<String, String>();
        Map<String, String> projectMap = new LinkedHashMap<String, String>();
        Map<String, String> productMap = new LinkedHashMap<String, String>();

        statusList = taskDAO.getAllStatus();
        taskList = taskDAO.getAllTask();
        stageList = taskDAO.getAllStage();
        projectList = taskDAO.getAllProject();
        productList = taskDAO.getAllProduct();
        processList = taskDAO.getAllProcess();
        developerList = taskDAO.getDeveloper(formBean.getProjectId());

        // set value for statusMap
        statusMap.put("All", "All");
        for (int i = 0; i < statusList.size(); i++) {
            statusMap.put(statusList.get(i).getProjectStatusId().toString(), statusList.get(i).getProjectStatusName());
        }
        formBean.setStatusMap(statusMap);

        // Set value for stageMap
        stageMap.put("All", "All");
        for (int i = 0; i < stageList.size(); i++) {
            stageMap.put(stageList.get(i).getStageId().toString(), stageList.get(i).getName());
        }
        formBean.setStageMap(stageMap);

        // Set value for developerMap
        developerMap.put("All", "All");
        for (int i = 0; i < developerList.size(); i++) {
            developerMap.put(developerList.get(i).getDeveloperId().toString(), developerList.get(i).getName());
        }
        formBean.setDeveloperMap(developerMap);
        
     // Set value for productMap
        for (int i = 0; i < productList.size(); i++) {
            productMap.put(productList.get(i).getWpId().toString(), productList.get(i).getName());
        }
        formBean.setProductMap(productMap);
        
        //System.out.println("Size: " + productList.size());

        // Set value for processMap
        for (int i = 0; i < processList.size(); i++) {
            processMap.put(processList.get(i).getProcessId().toString(), processList.get(i).getName());
        }
        formBean.setProcessMap(processMap);
        
     // Set value for projectMap
        projectMap.put("All", "All");
        for (int i = 0; i < projectList.size(); i++) {
            projectMap.put(projectList.get(i).getProjectId().toString(), projectList.get(i).getName());
        }
        formBean.setProjectMap(projectMap);
        
        

        // Convert StageID to string
        for (int i = 0; i < taskList.size(); i++) {
            for (int j = 0; j < stageList.size(); j++) {
                if (taskList.get(i).getStageid().equals(stageList.get(j).getStageId())) {
                    taskList.get(i).setStage_str(stageList.get(j).getName());
                }
            }
        }
        
//        // Convert ProjectID to string
//        for (int i = 0; i < taskList.size(); i++) {
//            for (int j = 0; j < projectList.size(); j++) {
//                if (taskList.get(i).getProjectid().equals(projectList.get(j).getProjectId())) {
//                    taskList.get(i).setProject_str(projectList.get(j).getName());
//                }
//            }
//        }
        
        // Convert ProcessID to string
        try {
            for (int i = 0; i < taskList.size(); i++) {
                for (int j = 0; j < processList.size(); j++) {
                    if (taskList.get(i).getProcessId().equals(processList.get(j).getProcessId())) {
                        taskList.get(i).setProcess_str(processList.get(j).getName());
                    }
                }
            }
        } catch (Exception ex) {
         
            log.error("Convert ProcessID to string", ex);
        }

        // Convert DeveloperID to string
        for (int i = 0; i < taskList.size(); i++) {
            for (int j = 0; j < developerList.size(); j++) {
                if (taskList.get(i).getDeveloperid().equals(developerList.get(j).getDeveloperId())) {
                    taskList.get(i).setDeveloper_str(developerList.get(j).getName());
                }
            }
        }

        formBean.setTaskList(taskList);

        mav.addObject("taskList", formBean.getTaskList());
        mav.addObject("statusDefault", formBean.getStatusDefault());
        mav.addObject("statusMap", formBean.getStatusMap());
        mav.addObject("stageMap", formBean.getStageMap());
        mav.addObject("developerMap", formBean.getDeveloperMap());
        mav.addObject("processMap", formBean.getProcessMap());
        mav.addObject("projectMap", formBean.getProjectMap());
        mav.addObject("productMap", formBean.getProductMap());
        mav.addObject("pEff", formBean.getPlannedEffort());
        mav.addObject("aEff", formBean.getActualEffort());
        return mav;
    }
    @ActionMapping(params = "action=addTask")
    public void processAddTask(PlannerForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("processAddTask.START");
        
        TaskDAO taskDAO = new TaskDAO();
        Tasks task = new Tasks();
        
        try {
            task.setTaskname(formBean.getTitle());
            task.setTaskcode("NEWTASK");
            task.setStageid(new BigDecimal(formBean.getStageId()));
            task.setProcessId(new BigDecimal(formBean.getProcessId()));
            task.setDeveloperid(new BigDecimal(formBean.getDeveloperId()));
            task.setPlannedeffort(new BigDecimal(formBean.getPlannedEffort()));
            task.setActualeffort(new BigDecimal(formBean.getActualEffort()));
            
            taskDAO.addTask(task);
        } catch (Exception ex) {
            log.error("error when add new task", ex);
        }
        response.setRenderParameter("action", "taskmanager");   
    }
    
    @ActionMapping(params = "action=deleteTask")
    public void processDeleteTask(PlannerForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("processDeleteTask.ACTION.START"); 
        log.debug(formBean.getTaskId());
        TaskDAO taskDAO = new TaskDAO();
        taskDAO.deleteTask(new BigDecimal(formBean.getTaskId()));
        
        System.out.println("processDeleteTask.ACTION.START");
        System.out.println(formBean.getTaskId());
        
        
        response.setRenderParameter("action", "taskmanager");   
    }
    
    @RenderMapping(params = "action=deleteTask")
    public void processDeleteTaskRender(PlannerForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("processDeleteTask.RENDER.START"); 
    }

}
