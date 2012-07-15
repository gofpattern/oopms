/**
 * 
 */
package openones.oopms.timesheet.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;

import openones.oopms.timesheet.dao.TimesheetDao;
import openones.oopms.timesheet.dao.UserDao;
import openones.oopms.timesheet.form.LoginForm;
import openones.oopms.timesheet.form.TimesheetForm;
import openones.oopms.timesheet.model.Developer;
import openones.oopms.timesheet.model.Project;
import openones.oopms.timesheet.model.Timesheet;
import openones.oopms.timesheet.model.Typeofwork;
import openones.oopms.timesheet.model.Workproduct;

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
 * Controller for handling all action related to Timesheet System
 * 
 * @author Truong
 * 
 */
@Controller
@RequestMapping("VIEW")
public class TimesheetController {
    // Declare static variables
    // List process of timesheet
    private static List<openones.oopms.timesheet.model.Process> processList = new ArrayList<openones.oopms.timesheet.model.Process>();
    // List work types of timesheet
    private static List<Typeofwork> towList = new ArrayList<Typeofwork>();
    // List work product of timesheet
    private static List<Workproduct> workProductList = new ArrayList<Workproduct>();
    // Developer object
    private Developer user = new Developer();

    /** Logger for logging. */
    private static Logger log = Logger.getLogger(TimesheetController.class);

    /**
     * Default screen. If user is "guest" (or null), display Login form. Otherwise (authenticated), display the
     * 
     * @return name of view which is the name of the JSP page.
     */
    @RequestMapping
    public String initScreen(RenderRequest request) {
        log.debug("initScreen.START");
        // Get logon user

        return "login";

    }

    /**
     * Create bean for login form.
     * 
     * @return Form bean for UI.
     */
    @ModelAttribute("loginForm")
    public LoginForm getCommandObject() {
        log.debug("getCommandObject.START");
        LoginForm formBean = new LoginForm();
        formBean.setUsername("TRUONGMH");
        return formBean;
    }

    /**
     * Process submitted form by clicking "Login" button.
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
    @ActionMapping(params = "action=init")
    public void processLogin(LoginForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("processLogin.START");
        log.debug("username=" + formBean.getUsername());
        // session.setAttribute("user", formBean);

        UserDao userDao = new UserDao();

        user = userDao.authenticate(formBean.getUsername(), formBean.getPassword());

        if (user != null) {
            // Prepare parameter to render phase
            response.setRenderParameter("action", "init");
        } else {
            result.rejectValue("username", "error");
            log.error("Error in binding result:" + result.getErrorCount());
        }
    }

    /**
     * Process after the action "login" (method "processLogin") is executed.
     * 
     * @return view "ViewDefectList" which next page "ViewDefectList.jsp" will displayed
     */
    @RenderMapping(params = "action=init")
    public ModelAndView postLogin(TimesheetForm formBean, RenderRequest request) {
        log.debug("postLogin.START");
        // Declare view for this render       
        ModelAndView mav = new ModelAndView("Timesheet"); // display Timesheet.jsp
        log.debug("Timesheet Form value :" + formBean.getProjectDefault() + " status:" + formBean.getStatus());
        TimesheetDao timesheetDao = new TimesheetDao();
        Map<String, String> projectMap = new LinkedHashMap<String, String>();
        
        // Get project List from database
        List<Project> projectList = timesheetDao.getProjectList(String.valueOf(user.getDeveloperId()));
        projectMap.put("All", "All");
        for (int i = 0; i < projectList.size(); i++) {
            projectMap.put(projectList.get(i).getProjectId().toString(), projectList.get(i).getName());
        }        
        // Set project list to form
        formBean.setProjectMap(projectMap);
        
        // Set information of user to session
        PortletSession session = request.getPortletSession();
        session.setAttribute("USERID", user.getDeveloperId(), PortletSession.APPLICATION_SCOPE);
        session.setAttribute("USER", user.getAccount(), PortletSession.APPLICATION_SCOPE);

        // Set default value for timesheet.jsp
        mav.addObject("projectMap", formBean.getProjectMap());
        mav.addObject("projectDefault", formBean.getProjectDefault());
        
        // Search all timesheet record from database
        TimesheetDao timeDao = new TimesheetDao();
        List<Timesheet> timesheetList = timeDao.getTimesheetList(String.valueOf(user.getDeveloperId()), "All", "", "","All");
        
        // Get dropdown list from database
        processList = timeDao.getProcessList();
        towList = timeDao.getTOWList();
        workProductList = timeDao.getWorkProductList();
        
        // Set value of dropdownlist to table timesheet
        int code = 0;
        for (int i = 0; i < timesheetList.size(); i++) {
            code = Integer.parseInt(timesheetList.get(i).getProcessId().toString());
            timesheetList.get(i).setProcessName(processList.get(code - 1).getName());
            timesheetList.get(i).setTowName(towList.get(code - 1).getName());
            timesheetList.get(i).setWorkProductName(workProductList.get(code - 1).getName());
        }
        // Add object timesheetList to request
        mav.addObject("timesheetList", timesheetList);
        // Return to jsp
        return mav;
    }

    /**
     * Create bean for timesheet form.
     * 
     * @return Form bean for UI.
     */
    
    @ModelAttribute("timesheetForm")
    public TimesheetForm getCommandObject2() {
        log.debug("getCommandObject.START");
        // Decalre Bean and Dao
        TimesheetForm formBean = new TimesheetForm();
       
        return formBean;
    }

    @ActionMapping(params = "action=searchTimesheet")
    public void processSearchTimesheet(TimesheetForm formBean, BindingResult result, SessionStatus status,
            ActionResponse response) {
        log.debug("processSearchTimesheet.START");
        if (result.hasErrors()) {
            log.debug("search timesheet error");
        }
        else {
            log.debug("Timesheet Form value :" + formBean.getProjectDefault() + " status:" + formBean.getStatus());
            response.setRenderParameter("action", "searchTimesheet");
        }
      
    }

    /**
     * Process after the action "searchTimesheet" (method "processsearchTimesheet") is executed.
     * 
     * @return view "Timesheet.jsp" which next page "Timesheet.jsp" will displayed
     */
    @RenderMapping(params = "action=searchTimesheet")
    public ModelAndView postTimesheet(TimesheetForm formBean, RenderRequest request) {
        ModelAndView mav = new ModelAndView("Timesheet"); // display Timesheet.jsp
        TimesheetDao timesheetDao = new TimesheetDao();
        Map<String, String> projectMap = new LinkedHashMap<String, String>();
        
        // Get project List from database
        List<Project> projectList = timesheetDao.getProjectList(String.valueOf(user.getDeveloperId()));
        projectMap.put("All", "All");
        for (int i = 0; i < projectList.size(); i++) {
            projectMap.put(projectList.get(i).getProjectId().toString(), projectList.get(i).getName());
        }        
        // Set project list to form
        formBean.setProjectMap(projectMap);
        
       System.out.println("Timesheet Form value :" + formBean.getProjectDefault() + " status:" + formBean.getStatus());
        
        // Set select value to jsp
        mav.addObject("projectMap", formBean.getProjectMap());
        mav.addObject("projectDefault", formBean.getProjectDefault());
        TimesheetDao timeDao = new TimesheetDao();
        // Get timesheet List from database with value from form
        List<Timesheet> timesheetList = timeDao.getTimesheetList(String.valueOf(user.getDeveloperId()),
                formBean.getProjectDefault(),  formBean.getFromDate(), formBean.getToDate(), formBean.getStatus());
        
        // Set value of dropdownlist to table timesheet
        int code = 0;
        if(timesheetList != null) {
            int size = timesheetList.size();
            if (size > 0) {

                for (int i = 0; i < size; i++) {
                    code = Integer.parseInt(timesheetList.get(i).getProcessId().toString());
                    timesheetList.get(i).setProcessName(processList.get(code - 1).getName());
                    timesheetList.get(i).setTowName(towList.get(code - 1).getName());
                    timesheetList.get(i).setWorkProductName(workProductList.get(code - 1).getName());
                }
            }
        }
      
        mav.addObject("timesheetList", timesheetList);

        return mav;
    }

}
