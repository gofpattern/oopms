package openones.oopms.controller;

import java.util.List;

import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import openones.oopms.dao.TaskDAO;
import openones.oopms.form.PlannerForm;
import openones.oopms.model.Process;
import openones.oopms.model.Tasks;
import openones.oopms.model.Stage;

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
    /**
     * Create bean for form.
     * @return Form bean for UI.
     */
    @ModelAttribute("PlannerForm")
    public PlannerForm getCommandObject() {
        log.debug("getCommandObject.START");

        PlannerForm formBean = new PlannerForm();
        formBean.setTitle("sds");
        // formBean.setTaskList(taskList);
        return formBean;
    }

    @ActionMapping(params = "action=taskmanager")
    public void processPlanner(PlannerForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("processPlanner.START");

    }
    @RenderMapping(params = "action=taskmanager")
    public ModelAndView postPlanner(PlannerForm formBean, RenderRequest request) {
        log.debug("postPlanner.START");
        // request.setAttribute("user2", formBean);
        // set portlet session

        ModelAndView mav = new ModelAndView("TaskManager");
        TaskDAO taskDAO = new TaskDAO();

        taskList = taskDAO.getAllTask();
        stageList = taskDAO.getAllStage();
        processList = taskDAO.getAllProcess();

        // Convert StageID to string
        for (int i = 0; i < taskList.size(); i++) {
            for (int j = 0; j < stageList.size(); j++) {
                if (taskList.get(i).getStageid().equals(stageList.get(j).getStageId())) {
                    taskList.get(i).setStage_str(stageList.get(j).getName());
                }
            }
        }
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
            // TODO: handle exception
            log.debug(processList.get(0).getName());
            log.debug(taskList.get(0).getProcessId());
            log.debug(processList.get(0).getProcessId());
            log.error("Convert ProcessID to string", ex);
        }
        formBean.setTaskList(taskList);

        mav.addObject("taskList", formBean.getTaskList());

        return mav;
    }
    @ActionMapping(params = "action=addTask")
    public void processAddTask(PlannerForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("processAddTask.START");

        log.debug("title=" + formBean.getTitle());

    }

}
