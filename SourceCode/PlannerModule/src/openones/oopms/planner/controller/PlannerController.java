package openones.oopms.planner.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;

import openones.oopms.planner.dao.TaskDAO;
import openones.oopms.planner.form.PlannerAddForm;
import openones.oopms.planner.form.PlannerForm;
import openones.oopms.planner.model.Developer;
import openones.oopms.planner.model.Process;
import openones.oopms.planner.model.Project;
import openones.oopms.planner.model.ProjectStatus;
import openones.oopms.planner.model.Stage;
import openones.oopms.planner.model.Tasks;

import org.apache.log4j.Logger;
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
    static String projectDefault;

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

    @ModelAttribute("PlannerAddForm")
    public PlannerAddForm getCommandObjectSubForm() {
        log.debug("getCommandObjectSubForm.START");
        PlannerAddForm formBean = new PlannerAddForm();
        return formBean;
    }

    @ActionMapping(params = "action=taskmanager")
    public void processPlanner(PlannerForm formBean, PlannerAddForm formBeanAdd, BindingResult result,
            SessionStatus status, ActionResponse response) {
        log.debug("processPlanner.START");

    }
    @RenderMapping(params = "action=taskmanager")
    public ModelAndView postPlanner(PlannerForm formBean, PlannerAddForm formBeanAdd, RenderRequest request) {
        log.debug("postPlanner.START");
        TaskDAO taskDAO = new TaskDAO();
        ModelAndView mav = new ModelAndView("TaskManager");

        // formBean.setProjectId("118385");

        formBean.setStatusDefault("All");
        formBean.setStageDefault("All");
        formBean.setDeveloperDefault("All");
        
        Map<String, String> statusMap = new LinkedHashMap<String, String>();
        Map<String, String> stageMap = new LinkedHashMap<String, String>();
        Map<String, String> developerMap = new LinkedHashMap<String, String>();
        Map<String, String> projectMap = new LinkedHashMap<String, String>();

        statusList = taskDAO.getAllStatus();
        taskList = taskDAO.getAllTask();
        stageList = taskDAO.getAllStage();
        projectList = taskDAO.getAllProject();
        processList = taskDAO.getAllProcess();
        // get developer form first project
        projectDefault = projectList.get(0).getProjectId().toString();
        developerList = taskDAO.getDeveloper(projectDefault);

        // set value for statusMap
        statusMap.put("All", "All");
        for (int i = 0; i < statusList.size(); i++) {
            statusMap.put(statusList.get(i).getProjectStatusId().toString(), statusList.get(i).getProjectStatusName());
        }

        // Set value for stageMap
        stageMap.put("All", "All");
        for (int i = 0; i < stageList.size(); i++) {
            stageMap.put(stageList.get(i).getStageId().toString(), stageList.get(i).getName());
        }

        // Set value for developerMap
        developerMap.put("All", "All");
        for (int i = 0; i < developerList.size(); i++) {
            developerMap.put(developerList.get(i).getDeveloperId().toString(), developerList.get(i).getName());
        }

        // Set value for projectMap
        for (int i = 0; i < projectList.size(); i++) {
            projectMap.put(projectList.get(i).getProjectId().toString(), projectList.get(i).getName());
        }

        // Convert
        try {
            for (int i = 0; i < taskList.size(); i++) {
                // Convert stageId to name
                for (int j = 0; j < stageList.size(); j++) {
                    if (taskList.get(i).getStageid().equals(stageList.get(j).getStageId())) {
                        taskList.get(i).setStage_str(stageList.get(j).getName());
                    }
                }
                // Convert processId to name
                for (int j = 0; j < processList.size(); j++) {
                    if (taskList.get(i).getProcessId().equals(processList.get(j).getProcessId())) {
                        taskList.get(i).setProcess_str(processList.get(j).getName());
                    }
                }
                // Convert developerId to name
                for (int j = 0; j < developerList.size(); j++) {
                    if (taskList.get(i).getDeveloperid().equals(developerList.get(j).getDeveloperId())) {
                        taskList.get(i).setDeveloper_str(developerList.get(j).getName());
                    }
                }
                // Convert projectId to code
                for (int j = 0; j < projectList.size(); j++) {
                    if (taskList.get(i).getProjectid().equals(projectList.get(j).getProjectId())) {
                        taskList.get(i).setProject_str(projectList.get(j).getCode());
                        log.debug("projectcode" + taskList.get(i).getProject_str());
                    }
                }
                // Convert date
                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                taskList.get(i).setStartdate_str(dateFormat.format(taskList.get(i).getStartdate()));
                taskList.get(i).setPlannedenddate_str(dateFormat.format(taskList.get(i).getPlannedenddate()));
            }

        } catch (Exception ex) {

            log.error("Convert id to name", ex);
        }
        
        
        //
        PortletSession portletSession = request.getPortletSession();
        portletSession.setAttribute("PROJECTID", projectDefault,PortletSession.APPLICATION_SCOPE);
                
        // Value for PlannerForm
        formBean.setProjectId(projectDefault);
        formBean.setTaskList(taskList);
        formBean.setStatusMap(statusMap);
        formBean.setStageMap(stageMap);
        formBean.setDeveloperMap(developerMap);
        formBean.setProjectMap(projectMap);

        // Object form PlannerForm
        mav.addObject("taskList", formBean.getTaskList());
        mav.addObject("statusDefault", formBean.getStatusDefault());
        mav.addObject("statusMap", formBean.getStatusMap());
        mav.addObject("stageMap", formBean.getStageMap());
        mav.addObject("developerMap", formBean.getDeveloperMap());
        mav.addObject("projectMap", formBean.getProjectMap());

        // Object form PlannerAddForm
        mav.addObject("edTask", formBeanAdd.getEditTask());

        mav.addObject("statusMapAdd", formBeanAdd.getStatusMap());
        mav.addObject("stageMapAdd", formBeanAdd.getStageMap());
        mav.addObject("developerMapAdd", formBeanAdd.getDeveloperMap());
        mav.addObject("processMapAdd", formBeanAdd.getProcessMap());
        mav.addObject("productMapAdd", formBeanAdd.getProductMap());
        mav.addObject("projectMapAdd", formBeanAdd.getProjectMap());
        mav.addObject("plAddAction", formBeanAdd.getAction_str());

        // flag to hide and show Add-Edit window
        mav.addObject("flag", formBean.getFlag());

        return mav;
    }

    @ActionMapping(params = "action=deleteTask")
    public void processDeleteTask(PlannerForm formBean, PlannerAddForm formBeanAdd, BindingResult result,
            SessionStatus status, ActionResponse response) {
        // log.debug("processDeleteTask.ACTION.START");
        TaskDAO taskDAO = new TaskDAO();
        taskDAO.deleteTask(new BigDecimal(formBean.getTaskId()));

        System.out.println("processDeleteTask.ACTION.START");

        response.setRenderParameter("action", "taskmanager");
    }

    @RenderMapping(params = "action=deleteTask")
    public void processDeleteTaskRender(PlannerForm formBean, PlannerAddForm formBeanAdd, BindingResult result,
            SessionStatus status, ActionResponse response) {
        log.debug("processDeleteTask.RENDER.START");
    }

}
