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

import openones.oopms.planner.dao.AssignmentDAO;
import openones.oopms.planner.dao.ModuleDAO;
import openones.oopms.planner.dao.TaskDAO;
import openones.oopms.planner.form.PlannerAddForm;
import openones.oopms.planner.form.PlannerForm;
import openones.oopms.planner.model.Developer;
import openones.oopms.planner.model.GeneralReference;
import openones.oopms.planner.model.Module;
import openones.oopms.planner.model.Process;
import openones.oopms.planner.model.Project;
import openones.oopms.planner.model.Stage;
import openones.oopms.planner.model.Tasks;
import openones.oopms.planner.utils.Constant;

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
    private List<Developer> developerList;
    private List<Project> projectList;
    private List<GeneralReference> statusList;
    // Current projectId of planner
    static String projectDefault;
    static String developerId;
    // role of user, depend on project
    private String role;

    /**
     * Create bean for form.
     * @return Form bean for UI.
     */
    @ModelAttribute("PlannerForm")
    public PlannerForm getCommandObject(PortletSession session) {
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
            SessionStatus status, ActionResponse response, ActionRequest request, PortletSession session) {
        log.debug("processPlanner.START");
        ModuleDAO moduleDAO = new ModuleDAO();
        List<Module> moduleList = moduleDAO.getModuleByProject(new BigDecimal(request.getParameter("projectId")));
        if (moduleList.size() != 0) {
            // Prepare parameter to render phase
            projectDefault = request.getParameter("projectId");
            developerId = (String) request.getParameter("developerId");
            response.setRenderParameter("action", "taskmanager");
        } else {
            log.debug("processPlanner.START + setError");
            TaskDAO dao = new TaskDAO();
            session.setAttribute("ERROR", true);
            session.setAttribute("PROJECT_NAME", dao.getProjectById(new BigDecimal(request.getParameter("projectId")))
                    .getName());
        }

    }
    @RenderMapping(params = "action=taskmanager")
    public ModelAndView postPlanner(PlannerForm formBean, PlannerAddForm formBeanAdd, RenderRequest request,
            PortletSession session) {
        log.debug("postPlanner.START");
        log.debug("postPlanner.START" + request.getParameter("projectId"));
        log.debug("postPlanner.START" + request.getParameter("developerId"));

        TaskDAO taskDAO = new TaskDAO();
        AssignmentDAO assignmentDAO = new AssignmentDAO();
        ModelAndView mav = new ModelAndView("TaskManager");

        Map<String, String> statusMap = new LinkedHashMap<String, String>();
        Map<String, String> stageMap = new LinkedHashMap<String, String>();
        Map<String, String> developerMap = new LinkedHashMap<String, String>();
        Map<String, String> projectMap = new LinkedHashMap<String, String>();

        if (formBean.getInit()) {
            formBean.setStatusDefault(Constant.ALL_VALUE);
            formBean.setStageDefault(Constant.ALL_VALUE);
            formBean.setDeveloperDefault(Constant.ALL_VALUE);
            role = assignmentDAO.getRole(developerId, projectDefault);
            statusList = taskDAO.getProjectStatusEn();

            if (role.equals(Constant.PROJECT_MANAGER))
                taskList = taskDAO.getTasksByProjectId(projectDefault);
            else
                taskList = taskDAO.getTasksByDeveloperOfProject(projectDefault, developerId);
            stageList = taskDAO.getAllStage();
            projectList = assignmentDAO.getProject(new BigDecimal(developerId));
            processList = taskDAO.getAllProcess();
            developerList = taskDAO.getDeveloper(projectDefault);
        }

        // set value for statusMap
        statusMap.put(formBean.getStatusDefault(), Constant.ALL_VALUE);
        if (!formBean.getInit())
            statusMap.put(Constant.ALL_VALUE, Constant.ALL_VALUE);
        for (int i = 0; i < statusList.size(); i++) {
            statusMap.put(statusList.get(i).getGeneralRefId().toString(), statusList.get(i).getDescription());
        }
        // Set value for stageMap
        stageMap.put(formBean.getStageDefault(), Constant.ALL_VALUE);
        if (!formBean.getInit())
            stageMap.put(Constant.ALL_VALUE, Constant.ALL_VALUE);
        for (int i = 0; i < stageList.size(); i++) {
            stageMap.put(stageList.get(i).getStageId().toString(), stageList.get(i).getName());
        }

        // Set value for developerMap
        developerMap.put(formBean.getDeveloperDefault(), Constant.ALL_VALUE);
        if (!formBean.getInit())
            developerMap.put(Constant.ALL_VALUE, Constant.ALL_VALUE);
        for (int i = 0; i < developerList.size(); i++) {
            developerMap.put(developerList.get(i).getDeveloperId().toString(), developerList.get(i).getName());
        }

        // Set value for projectMap
        projectMap.put(projectDefault, Constant.BLANK_VALUE);
        for (int i = 0; i < projectList.size(); i++) {
            projectMap.put(projectList.get(i).getProjectId().toString(), projectList.get(i).getName());
        }

        // Convert
        try {
            if (!taskList.isEmpty())
                for (int i = 0; i < taskList.size(); i++) {
                    // Convert stageId to name
                    for (int j = 0; j < stageList.size(); j++) {
                        if (taskList.get(i).getStageid().equals(stageList.get(j).getStageId())) {
                            taskList.get(i).setStage_str(stageList.get(j).getName());
                        }
                    }
                    // Convert processId to name
                    for (int j = 0; j < processList.size(); j++) {
                        if (taskList.get(i).getProcess().equals(processList.get(j).getProcessId())) {
                            taskList.get(i).setProcess_str(processList.get(j).getName());
                        }
                    }
                    // Convert developerId to name
                    for (int j = 0; j < developerList.size(); j++) {
                        if (taskList.get(i).getAssignedto().equals(developerList.get(j).getDeveloperId())) {
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
                    DateFormat dateFormat = new SimpleDateFormat(Constant.DATEFORMAT);
                    taskList.get(i).setStartdate_str(dateFormat.format(taskList.get(i).getStartdate()));
                    taskList.get(i).setPlanDate_str(dateFormat.format(taskList.get(i).getPlanDate()));
                    if (taskList.get(i).getStatusid().equals(new BigDecimal(Constant.CLOSED_STATUS)))
                        taskList.get(i).setActualDate_str(dateFormat.format(taskList.get(i).getActualDate()));
                }

        } catch (Exception ex) {

            log.error("Convert id to name", ex);
        }

        removeHtmlTag(taskList);

        // Value for PlannerForm
        formBean.setProjectId(projectDefault);
        formBean.setTaskList(taskList);
        formBean.setStatusMap(statusMap);
        formBean.setStageMap(stageMap);
        formBean.setDeveloperMap(developerMap);
        formBean.setProjectMap(projectMap);

        // Object form PlannerForm
        mav.addObject("taskList", formBean.getTaskList());
        mav.addObject("statusMap", formBean.getStatusMap());
        mav.addObject("stageMap", formBean.getStageMap());
        mav.addObject("developerMap", formBean.getDeveloperMap());
        mav.addObject("projectMap", formBean.getProjectMap());
        mav.addObject("taskStatus", formBean.getStatusDefault());
        session.setAttribute("role", role, PortletSession.APPLICATION_SCOPE);
        // mav.addObject("role", role);

        // Object form PlannerAddForm
        mav.addObject("edTask", formBeanAdd.getEditTask());
        mav.addObject("statusMapAdd", formBeanAdd.getStatusMap());
        mav.addObject("stageMapAdd", formBeanAdd.getStageMap());
        mav.addObject("developerMapAdd", formBeanAdd.getDeveloperMap());
        mav.addObject("processMapAdd", formBeanAdd.getProcessMap());
        mav.addObject("moduleMapAdd", formBeanAdd.getModuleMap());
        mav.addObject("sizeUnitMapAdd", formBeanAdd.getSizeUnitMap());
        mav.addObject("plAddAction", formBeanAdd.getAction_str());

        // flag to hide and show Add-Edit window
        mav.addObject("flag", formBean.getFlag());

        return mav;
    }

    @ActionMapping(params = "action=deleteTask")
    public void processDeleteTask(PlannerForm formBean, PlannerAddForm formBeanAdd, BindingResult result,
            SessionStatus status, ActionResponse response) {
        log.debug("processDeleteTask.ACTION.START");
        TaskDAO taskDAO = new TaskDAO();
        taskDAO.deleteTask(new BigDecimal(formBean.getTaskId()));

        response.setRenderParameter("action", "taskmanager");
    }

    @RenderMapping(params = "action=deleteTask")
    public void postDeleteTask(PlannerForm formBean, PlannerAddForm formBeanAdd, BindingResult result,
            SessionStatus status, ActionResponse response) {
        log.debug("postDeleteTask.START");
    }

    /**
     * filter task
     * @param formBean
     * @param result
     * @param status
     * @param response
     * @param session
     */
    @ActionMapping(params = "action=search")
    public void processSearchByStatus(PlannerForm formBean, BindingResult result, SessionStatus status,
            ActionResponse response, PortletSession session) {
        log.debug("processSearchByStatus.START");
        for (int i = 0; i < taskList.size(); i++) {
            taskList.get(i).setVisible(true);
            if (!formBean.getStageDefault().equals(Constant.ALL_VALUE))
                if (!taskList.get(i).getStageid().equals(new BigDecimal(formBean.getStageDefault()))) {
                    taskList.get(i).setVisible(false);
                }
            if (!formBean.getDeveloperDefault().equals(Constant.ALL_VALUE))
                if (!taskList.get(i).getAssignedto().equals(new BigDecimal(formBean.getDeveloperDefault()))) {
                    taskList.get(i).setVisible(false);
                }
            if (!formBean.getStatusDefault().equals(Constant.ALL_VALUE))
                if (!taskList.get(i).getStatusid().equals(new BigDecimal(formBean.getStatusDefault()))) {
                    taskList.get(i).setVisible(false);
                }

        }
        formBean.setInit(false);
        response.setRenderParameter("action", "taskmanager");
    }

    @RenderMapping(params = "action=search")
    public void postSearchByStatus(PlannerForm formBean, BindingResult result, SessionStatus status,
            ActionResponse response) {
        log.debug("postSearchByStatus.START");
    }

    @ActionMapping(params = "action=changeProject")
    public void processChangeProject(PlannerForm formBean, BindingResult result, SessionStatus status,
            ActionResponse response, PortletSession session) {
        log.debug("processChangeProject.START");

        ModuleDAO moduleDAO = new ModuleDAO();
        List<Module> moduleList = moduleDAO.getModuleByProject(new BigDecimal(formBean.getProjectId()));

        if (moduleList.size() != 0) {
            projectDefault = formBean.getProjectId();
            formBean.setInit(true);
            response.setRenderParameter("action", "taskmanager");
        } else {
            log.debug("CHANGEPROJECT_ERROR");
            TaskDAO dao = new TaskDAO();
            session.setAttribute("CHANGEPROJECT_ERROR", true);
            session.setAttribute("PROJECT_NAME", dao.getProjectById(new BigDecimal(formBean.getProjectId())).getName());
            response.setRenderParameter("action", "taskmanager");
        }
    }

    @RenderMapping(params = "action=changeProject")
    public void postChangeProject(PlannerForm formBean, BindingResult result, SessionStatus status,
            ActionResponse response) {
        log.debug("postChangeProject.START");
    }

    /**
     * remove Html Tag of task name and task description
     * @param taskList
     */
    public static void removeHtmlTag(List<Tasks> taskList) {
        if (!taskList.isEmpty())
            for (int i = 0; i < taskList.size(); i++) {
                taskList.get(i).setTaskname(taskList.get(i).getTaskname().replaceAll(">", "&gt;"));
                taskList.get(i).setTaskname(taskList.get(i).getTaskname().replaceAll("<", "&lt;"));
                taskList.get(i).setDescription(taskList.get(i).getDescription().replaceAll(">", "&gt;"));
                taskList.get(i).setDescription(taskList.get(i).getDescription().replaceAll("<", "&lt;"));
            }
    }

}
