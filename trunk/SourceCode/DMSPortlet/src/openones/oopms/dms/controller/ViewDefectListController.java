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
package openones.oopms.dms.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;

import openones.oopms.daocommon.DefectListDao;
import openones.oopms.daocommon.DefectPriorityDao;
import openones.oopms.daocommon.DefectSeverityDao;
import openones.oopms.daocommon.DefectStatusDao;
import openones.oopms.daocommon.DefectTypeDao;
import openones.oopms.daocommon.DeveloperDao;
import openones.oopms.daocommon.ProcessDao;
import openones.oopms.daocommon.WorkProductDao;
import openones.oopms.dms.biz.DMSWorkspace;
import openones.oopms.dms.dao.BaseDao;
import openones.oopms.dms.dao.DefectDao;
import openones.oopms.dms.dao.UserDao;
import openones.oopms.dms.form.DefectForm;
import openones.oopms.dms.form.DefectUpdateForm;
import openones.oopms.dms.form.LoginForm;
import openones.oopms.dms.form.ViewDefectListForm;
import openones.oopms.dms.validator.AddDefectValidator;
import openones.oopms.entity.Defect;
import openones.oopms.entity.DefectPriority;
import openones.oopms.entity.DefectSeverity;
import openones.oopms.entity.DefectStatus;
import openones.oopms.entity.DefectType;
import openones.oopms.entity.Developer;
import openones.oopms.entity.Project;
import openones.oopms.entity.Timesheet;
import openones.oopms.entity.Workproduct;
import openones.oopms.form.UserInfo;
import openones.oopms.portlet.controller.BaseController;
import openones.oopms.util.BaseUtil;
import openones.portlet.PortletSupport;

import org.hibernate.dialect.FirebirdDialect;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import rocky.common.PropertiesManager;

/**
 * @author Thach.Le
 */
@Controller
@RequestMapping(value = "VIEW")
public class ViewDefectListController extends BaseController {
    
    // User Dao
    UserDao userDao;
    // Map project
    Map<Integer, String> qcActivityMap;
    Map<String, String> projectMap;
    String projectDis="";
    // Map member
    Map<String, String> memberMap;
    String memberDisCreated="";
    String memberDisAssigned="";
    // Map Process
    Map<String, String> statusMap;
    String statusDis="";
    // Map Work Product
    Map<String, String> severityMap;
    String serverityDis="";
    // Map Work Product
    Map<String, String> priorityMap;
    String priorityDis="";
    // Map Work Product
    Map<String, String> typeMap;
    String typeDis="";
    // Map Work Product
    Map<String, String> originMap;
    String originDis="";
    String defectId="";
    // Map Work Product
    Map<String, String> workProductMap;
    String workProductDis="";
    // Create Date
    String createDate="";
    String dueDate="";
    Defect updateDefect;
    // List work product of defect
    private static List<Project> projectList = new ArrayList<Project>();
    // List Developer
    private static List<Developer> developerList = new ArrayList<Developer>();
    // List defstatusList
    private static List<DefectStatus> defStatusList = new ArrayList<DefectStatus>();
    // List defSeverityList
    private static List<DefectSeverity> defSeverityList = new ArrayList<DefectSeverity>();
    // List defSeverityList
    private static List<DefectPriority> defPriorityList = new ArrayList<DefectPriority>();
    // List defSeverityList
    private static List<DefectType> defTypeList = new ArrayList<DefectType>();
    // List defSeverityList
    private static ArrayList<openones.oopms.entity.Process> defOriginList = new ArrayList<openones.oopms.entity.Process>();
 // List defSeverityList
    private static List<Workproduct> defWorkProductList = new ArrayList<Workproduct>();
    // Developer object
    private Developer user = new Developer();
    // Role
    String role;
    private ArrayList<Defect> errorDefectList;
    private ArrayList<Timesheet> updateDefectList;
    private Properties props;
    private String createDateString;
    private String duaDateString;
    
    
    @RequestMapping
    public ModelAndView initScreen(RenderRequest request, PortletSession session) {
        log.debug("initScreen.START");
        ModelAndView mav;
        PortletSupport portletSupport = new PortletSupport(request);
        String logonUser = portletSupport.getLogonUser();

        log.debug("logonUser=" + logonUser);

        mav = new ModelAndView("Forward");
        log.debug("logonUser=" + logonUser);

        if (logonUser != null) {
            UserInfo userInfo = null;
            DeveloperDao devDao = new DeveloperDao();
            Developer dev = devDao.getDeveloperByAccount(logonUser);
         
            if (dev != null) {
               
            
                // Set roles for user
                userInfo = new UserInfo(logonUser);
                userInfo.addRole(dev.getRole());
                userInfo.setGroup(dev.getGroupName());
                userInfo.setLoginDate(BaseUtil.getCurrentDate());
                user= dev;   
                String projectId = request.getParameter("projectId");
                
                System.out.println("postLogin.START");
                // Declare view for this render
                 mav = new ModelAndView("ViewDefectList"); // display Defect.jsp
             
               DefectListDao defectDao = new DefectListDao();
                projectMap = new LinkedHashMap<String, String>();
                memberMap = new LinkedHashMap<String, String>();
                severityMap = new LinkedHashMap<String, String>();
                statusMap = new LinkedHashMap<String, String>();
                workProductMap = new LinkedHashMap<String, String>();
                priorityMap = new LinkedHashMap<String, String>();
                originMap = new LinkedHashMap<String, String>();
                typeMap = new LinkedHashMap<String, String>();
                // Get project List from database
                BaseDao baseDao = new BaseDao();
                DefectStatusDao defStatusDao = new DefectStatusDao();
                DefectTypeDao defTypeDao = new DefectTypeDao();
                DefectSeverityDao defSeverityDao = new DefectSeverityDao();
                DefectPriorityDao defPriorDao = new DefectPriorityDao();
                ProcessDao processDao = new ProcessDao();
                WorkProductDao wpDao = new WorkProductDao();
                DefectDao defDao = new DefectDao();
                System.out.println("User :" + user.getDeveloperId());
                projectList = baseDao.getProjectList(String.valueOf(user.getDeveloperId())); 
                if(projectList == null || projectList.size() ==0) {
                    mav = new ModelAndView("ViewDefectList");
                    return mav;
                }
                for (int i = 0; i < projectList.size(); i++) {
                    projectMap.put(projectList.get(i).getProjectId().toString(), projectList.get(i).getName());
                }
                defOriginList = processDao.getProcess();
                originMap.put("", "All");
                for (int i = 0; i < defOriginList.size(); i++) {                  
                    originMap.put(String.valueOf(defOriginList.get(i).getProcessId()), defOriginList.get(i).getName());
                }
                defStatusList = defStatusDao.getDefectStatus();
                statusMap.put("", "All");
                for (int i = 0; i < defStatusList.size(); i++) {
                  
                    statusMap.put(defStatusList.get(i).getDsId().toString(), defStatusList.get(i).getName());
                }
                defPriorityList = defPriorDao.getDefectPriority();
                priorityMap.put("", "All");            
                for (int i = 0; i < defPriorityList.size(); i++) {
                   
                    priorityMap.put(String.valueOf(defPriorityList.get(i).getDpId()), defPriorityList.get(i).getName());
                }
                defSeverityList = defSeverityDao.getDefectSeverity();
                severityMap.put("", "All");
                for (int i = 0; i < defSeverityList.size(); i++) {
                  
                    severityMap.put(String.valueOf(defSeverityList.get(i).getDefsId()), defSeverityList.get(i).getName());
                }
               
                defTypeList =  defTypeDao.getDefectType();
                typeMap.put("", "All");
                for (int i = 0; i < defTypeList.size(); i++) {
                   
                    typeMap.put(String.valueOf(defTypeList.get(i).getDtId()), defTypeList.get(i).getName());
                }
                defWorkProductList = wpDao.getWorkproduct();
                workProductMap.put("", "All");
                for (int i = 0; i < defWorkProductList.size(); i++) {
                    
                    workProductMap.put(String.valueOf(defWorkProductList.get(i).getWpId()), defWorkProductList.get(i).getName());
                }
                developerList = defDao.getMemberList(projectList.get(0).getProjectId().toString());
                memberMap.put("", "All");
                for (int i = 0; i < developerList.size(); i++) {
                  
                    memberMap.put(String.valueOf(developerList.get(i).getDeveloperId()), developerList.get(i).getAccount());
                }
                role = baseDao.getRole(user.getDeveloperId().toString(), projectList.get(0).getProjectId().toString());
                System.out.println("ROLE : " + role);
              
               
                // Set project list to form
                mav.addObject("projectMap", projectMap);
                mav.addObject("statusMap", statusMap);
                mav.addObject("priorityMap", priorityMap);
                mav.addObject("originMap", originMap);
                mav.addObject("typeMap", typeMap);
                mav.addObject("workProductMap", workProductMap);
                mav.addObject("memberMap", memberMap);
                mav.addObject("severityMap", severityMap);
                

                // Set information of user to session
                 session = request.getPortletSession();
                session.setAttribute("USERID", user.getDeveloperId(), PortletSession.APPLICATION_SCOPE);
                session.setAttribute("USER", user.getAccount(), PortletSession.APPLICATION_SCOPE);
                session.setAttribute("ROLE",role, PortletSession.APPLICATION_SCOPE);       
                mav.addObject("ROLE",role);
                // Search all defect record from database
              
                List<Defect> defectList = new ArrayList<Defect>();
                defectList = defDao.getDefectList("","",projectList.get(0).getProjectId().toString(),"","","","","","","","");

                // Get dropdown list from database
                
               defectList = prepareDefectList(defectList);

               
                // Set default value for defect.jsp
//               
//                              
//                mav.addObject("projectStatus", projectStatus);
//                mav.addObject("fromDate", fromDate);
//                mav.addObject("toDate", toDate);
//                // Add object defectList to request
                mav.addObject("projectDis", projectDis);
                mav.addObject("memberDisAssigned", memberDisAssigned);
                mav.addObject("memberDisCreated", memberDisCreated);
                mav.addObject("serverityDis", serverityDis);
                mav.addObject("workProductDis", workProductDis);
                mav.addObject("typeDis", typeDis);
                mav.addObject("statusDis", statusDis);
                mav.addObject("originDis", originDis);
                mav.addObject("createDate", createDate);
                mav.addObject("createDate", dueDate);
                mav.addObject("defectList", defectList);
                
            }
        }            

        return mav;
    }
    
    /**
     * Create bean for form.
     * @return Form bean for UI.
     */
    @ModelAttribute("viewDefectList")
    public ViewDefectListForm getCommandObject() {
        log.debug("getCommandObject.START");
        ViewDefectListForm formBean = new ViewDefectListForm();
       
        return formBean;
    }
    
    @RenderMapping(params = "action=goViewDefectMode")
    public ModelAndView goViewDefectMode(RenderRequest request, PortletSession session) {
        log.debug("goViewDefectMode.START");

        ModelAndView mav = new ModelAndView("ViewDefectMode"); // display ViewDefectMode.jsp
        
        return mav;
    }
    
      
    @ActionMapping(params = "action=init")
    public void processLogin(LoginForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
        System.out.println("processLogin.START");
        System.out.println("username=" + formBean.getUsername());
        // session.setAttribute("user", formBean);
        DeveloperDao devDao = new DeveloperDao();
        
        user = devDao.getDeveloperByAccount(formBean.getUsername());

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
    public ModelAndView postLogin(ViewDefectListForm formBean, RenderRequest request) {
        String projectId = request.getParameter("projectId");
       
        System.out.println("postLogin.START");
        // Declare view for this render
        ModelAndView mav = new ModelAndView("ViewDefectList"); // display Defect.jsp
     
       DefectListDao defectDao = new DefectListDao();
        projectMap = new LinkedHashMap<String, String>();
        memberMap = new LinkedHashMap<String, String>();
        severityMap = new LinkedHashMap<String, String>();
        statusMap = new LinkedHashMap<String, String>();
        workProductMap = new LinkedHashMap<String, String>();
        priorityMap = new LinkedHashMap<String, String>();
        originMap = new LinkedHashMap<String, String>();
        typeMap = new LinkedHashMap<String, String>();
        // Get project List from database
        BaseDao baseDao = new BaseDao();
        DefectStatusDao defStatusDao = new DefectStatusDao();
        DefectTypeDao defTypeDao = new DefectTypeDao();
        DefectSeverityDao defSeverityDao = new DefectSeverityDao();
        DefectPriorityDao defPriorDao = new DefectPriorityDao();
        ProcessDao processDao = new ProcessDao();
        WorkProductDao wpDao = new WorkProductDao();
        DefectDao defDao = new DefectDao();
        System.out.println("User :" + user.getDeveloperId());
        projectList = baseDao.getProjectList(String.valueOf(user.getDeveloperId()));   
        for (int i = 0; i < projectList.size(); i++) {
            projectMap.put(projectList.get(i).getProjectId().toString(), projectList.get(i).getName());
        }
        defOriginList = processDao.getProcess();
        for (int i = 0; i < defOriginList.size(); i++) {
            originMap.put("", "All");
            originMap.put(String.valueOf(defOriginList.get(i).getProcessId()), defOriginList.get(i).getName());
        }
        defStatusList = defStatusDao.getDefectStatus();
        for (int i = 0; i < defStatusList.size(); i++) {
            statusMap.put("", "All");
            statusMap.put(defStatusList.get(i).getDsId().toString(), defStatusList.get(i).getName());
        }
        defPriorityList = defPriorDao.getDefectPriority();
        for (int i = 0; i < defPriorityList.size(); i++) {
            priorityMap.put("", "All");            
            priorityMap.put(String.valueOf(defPriorityList.get(i).getDpId()), defPriorityList.get(i).getName());
        }
        defSeverityList = defSeverityDao.getDefectSeverity();
        for (int i = 0; i < defSeverityList.size(); i++) {
            severityMap.put("", "All");
            severityMap.put(String.valueOf(defSeverityList.get(i).getDefsId()), defSeverityList.get(i).getName());
        }
       
        defTypeList =  defTypeDao.getDefectType();
        for (int i = 0; i < defTypeList.size(); i++) {
            typeMap.put("", "All");
            typeMap.put(String.valueOf(defTypeList.get(i).getDtId()), defTypeList.get(i).getName());
        }
        defWorkProductList = wpDao.getWorkproduct();
        for (int i = 0; i < defWorkProductList.size(); i++) {
            workProductMap.put("", "All");
            workProductMap.put(String.valueOf(defWorkProductList.get(i).getWpId()), defWorkProductList.get(i).getName());
        }
        developerList = defDao.getMemberList(projectList.get(0).getProjectId().toString());
        for (int i = 0; i < developerList.size(); i++) {
            memberMap.put("", "All");
            memberMap.put(String.valueOf(developerList.get(i).getDeveloperId()), developerList.get(i).getAccount());
        }
        role = baseDao.getRole(user.getDeveloperId().toString(), projectList.get(0).getProjectId().toString());
        System.out.println("ROLE : " + role);
      
       
        // Set project list to form
        mav.addObject("projectMap", projectMap);
        mav.addObject("statusMap", statusMap);
        mav.addObject("priorityMap", priorityMap);
        mav.addObject("originMap", originMap);
        mav.addObject("typeMap", typeMap);
        mav.addObject("workProductMap", workProductMap);
        mav.addObject("memberMap", memberMap);
        mav.addObject("severityMap", severityMap);
        

        // Set information of user to session
        PortletSession session = request.getPortletSession();
        session.setAttribute("USERID", user.getDeveloperId(), PortletSession.APPLICATION_SCOPE);
        session.setAttribute("USER", user.getAccount(), PortletSession.APPLICATION_SCOPE);
        session.setAttribute("ROLE",role, PortletSession.APPLICATION_SCOPE);       
        mav.addObject("ROLE",role);
        // Search all defect record from database
      
        List<Defect> defectList2 = new ArrayList<Defect>();
        defectList2 = defDao.getDefectList("","",projectList.get(0).getProjectId().toString(),"","","","","","","","");

        // Get dropdown list from database
        
       defectList2 = prepareDefectList(defectList2);

       
        // Set default value for defect.jsp
//       
//                      
//        mav.addObject("projectStatus", projectStatus);
//        mav.addObject("fromDate", fromDate);
//        mav.addObject("toDate", toDate);
//        // Add object defectList to request
        mav.addObject("projectDis", projectDis);
        mav.addObject("memberDisAssigned", memberDisAssigned);
        mav.addObject("memberDisCreated", memberDisCreated);
        mav.addObject("serverityDis", serverityDis);
        mav.addObject("workProductDis", workProductDis);
        mav.addObject("typeDis", typeDis);
        mav.addObject("statusDis", statusDis);
        mav.addObject("originDis", originDis);
        mav.addObject("createDate", createDate);
        mav.addObject("createDate", dueDate);
        mav.addObject("defectList", defectList2);
        // Return to jsp
        return mav;
    }

    private List<Defect> prepareDefectList(List<Defect> defList) {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        int statusCode = 0;
        int severityCode = 0;
        int priorityCode = 0;
        int assignCode = 0;
        int createCode = 0;

        for (int i = 0; i < defList.size(); i++) {
            statusCode = Integer.parseInt(defList.get(i).getDsId().toString());
            severityCode = Integer.parseInt(defList.get(i).getDefsId().toString());
            priorityCode = Integer.parseInt(defList.get(i).getDpId().toString());
           assignCode = Integer.parseInt(defList.get(i).getAssignedTo());
           createCode = Integer.parseInt(defList.get(i).getCreatedBy());
            if(defList.get(i).getFixedDate()!=null) {
                defList.get(i).setFixedDateString(df.format(defList.get(i).getFixedDate()));
            }
            for(int j=0; j<developerList.size();j++) {
                if(assignCode == developerList.get(j).getDeveloperId().intValue()) {
                    defList.get(i).setAssignedToString(developerList.get(j).getAccount());  
                }
                if(createCode == developerList.get(j).getDeveloperId().intValue()) {
                    defList.get(i).setCreateByString(developerList.get(j).getAccount());  
                }
            }           
        
            defList.get(i).setDueDateString(df.format(defList.get(i).getDueDate()));  
            defList.get(i).setStatus(defStatusList.get(statusCode-1).getName());
            defList.get(i).setSeverity(defSeverityList.get(severityCode-1).getName());
            defList.get(i).setPriority(defPriorityList.get(priorityCode-1).getName());
            
        }
        return defList;
    }
    
    @ModelAttribute("defectForm")
    public DefectForm getCommandObject2() {
        log.debug("getCommandObject.START");
        DefectForm formBean = new DefectForm();
       
        return formBean;
    }
    
    /**
     * Create bean for form.
     * @return Form bean for UI.
     */
    @ModelAttribute("updateDefectForm")
    public DefectUpdateForm getCommandObject3() {
        log.debug("getCommandObject.START");
        DefectUpdateForm formBean = new DefectUpdateForm();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                   
             
        return formBean;
    }
    
//    @RequestMapping
//    public String initScreen(RenderRequest request) {
//        log.debug("initScreen.START");
//        return "AddDefect";
//    }
    
    @RenderMapping(params = "action=goAddNewDefect")
    public ModelAndView processAddNew(RenderRequest request, PortletSession session) {
        log.debug("processAddNew.START");

        ModelAndView mav = new ModelAndView("AddDefect"); // display AddDefect.jsp
         qcActivityMap = DMSWorkspace.getDefaultWorkspace().getActivityMap();
         mav.addObject("qcActivityMap", qcActivityMap);
        System.out.println("oringin map size : " + originMap.size());
        mav.addObject("defect", new DefectForm());
        projectMap.remove("");
        System.out.println("contains : "+projectMap.containsKey(""));
        mav.addObject("projectMap", projectMap);
        statusMap.remove("");
        mav.addObject("statusMap", statusMap);
        priorityMap.remove("");
        mav.addObject("priorityMap", priorityMap);
        originMap.remove("");
        mav.addObject("originMap", originMap);
        typeMap.remove("");
        mav.addObject("typeMap", typeMap);
        workProductMap.remove("");
        mav.addObject("workProductMap", workProductMap);
        memberMap.remove("");
        mav.addObject("memberMap", memberMap);
        severityMap.remove("");
        mav.addObject("severityMap", severityMap);
      
        return mav;
    }
    
    /**
     * Process submitted form by clicking "Add" button.
     * @param formBean bean captures input data
     * @param result result of binding data
     * @param status status of session
     * @param response response of action
     */
    @ActionMapping(params = "action=save")
    public void processSave(DefectForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("processSave.START");
        
        log.debug("formBean:title=" + formBean.getTitle());

        Validator addDefectValidator = new AddDefectValidator();
        addDefectValidator.validate(formBean, result);
        
        if (!result.hasErrors()) {
            // Prepare parameter to render phase
            response.setRenderParameter("action", "goViewDefectList");
        } else {
            log.error("Error in binding result:" + result.getErrorCount());
            
            // Re-display the Add Defect screen with errors
            response.setRenderParameter("action", "goAddNewDefect");
        }
    }

    /**
     * Action mapping for search action
     * @param formBean
     * @param result
     * @param status
     * @param response
     */
    ViewDefectListForm tempForm = null;
    @ActionMapping(params = "action=searchDefect")
    public void processSearchDefect(ViewDefectListForm formBean, BindingResult result, SessionStatus status,
            ActionResponse response) {
        System.out.println("processSearchDefect.START");
        if (result.hasErrors()) {
            System.out.println("search defect error");
        } else {
          
           projectDis = formBean.getProjectDis();
           statusDis = formBean.getStatusDis();
           originDis = formBean.getOriginDis();
           serverityDis = formBean.getSeverityDis();
           priorityDis = formBean.getPriorityDis();
           memberDisAssigned = formBean.getMemberDisAssigned();
           memberDisCreated = formBean.getMemberDisCreated();
           typeDis = formBean.getTypeDis();
           workProductDis = formBean.getWorkProductDis();
           createDate = formBean.getCreateDate();
           dueDate = formBean.getDueDate();
          tempForm = formBean;
            response.setRenderParameter("action", "searchDefect");
        }

    }

    /**
     * Process after the action "searchDefect" (method "processsearchDefect") is executed.
     * 
     * @return view "Defect.jsp" which next page "Defect.jsp" will displayed
     */
    @RenderMapping(params = "action=searchDefect")
    public ModelAndView postDefect(DefectForm formBean, RenderRequest request) {
        ModelAndView mav = new ModelAndView("ViewDefectList"); // display Defect.jsp
        
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
       
        mav.addObject("viewDefectList", tempForm);
       
        mav.addObject("defect", new DefectForm());
        qcActivityMap = DMSWorkspace.getDefaultWorkspace().getActivityMap();
        mav.addObject("qcActivityMap", qcActivityMap);
        mav.addObject("projectMap", projectMap);
        mav.addObject("statusMap", statusMap);
        mav.addObject("priorityMap", priorityMap);
        mav.addObject("originMap", originMap);
        mav.addObject("typeMap", typeMap);
        mav.addObject("workProductMap", workProductMap);
        mav.addObject("memberMap", memberMap);
        mav.addObject("severityMap", severityMap);
        
        DefectDao defDao = new DefectDao();
        // Get defect List from database with value from form
        List<Defect> defectList = defDao.getDefectList(memberDisCreated, memberDisAssigned, projectDis,
                createDate, dueDate, serverityDis, workProductDis, priorityDis, originDis, typeDis, statusDis
                );

        // Set value of dropdownlist to table defect

        if (defectList != null) {
            int size = defectList.size();
            if (size > 0) {

                defectList = prepareDefectList(defectList);
            }
        }
       
        mav.addObject("defectList", defectList);       
        return mav;
    }
    
    /**
     * Action mapping for forward to Add time sheet page.
     * @param formBean
     * @param result
     * @param status
     * @param response
     */
    
    @ActionMapping(params = "action=addDefect")
    public void processAddDefectToDB(DefectForm formBean, BindingResult result, SessionStatus status,
            ActionResponse response) throws IOException, ParseException {
      
       boolean isError = false;
       errorDefectList = new ArrayList<Defect>();
      Defect defect = new Defect();
      DefectDao defDao = new DefectDao();
      BigDecimal defectId = defDao.getNextDefect();
      defect = convertDefectFormToDTO(formBean,defectId);
       
            
      
            DefectDao defectDao = new DefectDao();
            defectDao.insertDefect(defect);

            response.setRenderParameter("action", "init");
        
       
    }
    
    /**
     * Action mapping for forward to Add time sheet page.
     * @param formBean
     * @param result
     * @param status
     * @param response
     */
    
    @ActionMapping(params = "action=updateDefect")
    public void processUpdateDefectToDB(DefectUpdateForm formBean, BindingResult result, SessionStatus status,
            ActionResponse response) throws IOException, ParseException {
        Defect timesheet;
       boolean isError = false;
       errorDefectList = new ArrayList<Defect>();
      Defect defect = new Defect();
      DefectDao defDao = new DefectDao();    
      defect = convertDefectUpdateFormToDTO(formBean);
      defDao.updateDefect(defect);                 
       
            response.setRenderParameter("action", "init");
        
       
    }

    private Defect convertDefectFormToDTO(DefectForm formBean, BigDecimal defId) throws ParseException {
       Defect def = new Defect();
       SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        def.setAssignedTo(formBean.getMemberDisAssigned());
        def.setAtId(new BigDecimal(1));
        def.setQaId(new BigDecimal(formBean.getQcActivityDis()));
        def.setCauseAnalysis(formBean.getCauseAnalysis());
        def.setCreateDate(df.parse(formBean.getCreateDateString()));
        def.setCreatedBy(formBean.getMemberDisCreated());
        def.setDefectOwner(formBean.getMemberDisOwner());
        def.setDefsId(new BigDecimal(formBean.getSeverityDis()));
        def.setDescription(formBean.getDescription());
        def.setDpId(new BigDecimal(formBean.getPriorityDis()));
        def.setDsId(new BigDecimal(1));
        def.setDtId(new BigDecimal(formBean.getTypeDis()));
        def.setDueDate(df.parse(formBean.getDueDateString()));
        def.setModuleId(new BigDecimal(1));
        def.setProcessId(new BigDecimal(formBean.getOriginDis()));
        def.setProjectId(new BigDecimal(formBean.getProjectDis()));
        def.setSolution(formBean.getCorrectiveAction());
        def.setTestCase(formBean.getTestCaseId());
        def.setTitle(formBean.getTitle());
        def.setWpId(new BigDecimal(formBean.getWorkProductDis()));        
        def.setDefectId(defId);
        return def;
    }
    private Defect convertDefectUpdateFormToDTO(DefectUpdateForm formBean) throws ParseException {
        Defect def = new Defect();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
         def.setAssignedTo(formBean.getMemberDisAssigned());
         def.setAtId(new BigDecimal(1));
         def.setCauseAnalysis(formBean.getCauseAnalysis());
         def.setCreateDate(df.parse(formBean.getCreateDateString()));
         def.setCreatedBy(formBean.getMemberDisCreated());
         def.setDefectOwner(formBean.getMemberDisOwner());
         if(formBean.getStatusDis().equals("3")){
             def.setFixedDate(new Date());
         }
         def.setDefsId(new BigDecimal(formBean.getSeverityDis()));
         def.setDescription(formBean.getDescription());
         def.setQaId(new BigDecimal(formBean.getQcActivityDis()));
         def.setDpId(new BigDecimal(formBean.getPriorityDis()));
         def.setDsId(new BigDecimal(1));
         def.setDtId(new BigDecimal(formBean.getTypeDis()));
         def.setDueDate(df.parse(formBean.getDueDateString()));
         def.setModuleId(new BigDecimal(1));
         def.setProcessId(new BigDecimal(formBean.getOriginDis()));
         def.setProjectId(new BigDecimal(formBean.getProjectDis()));
         def.setSolution(formBean.getCorrectiveAction());
         def.setTestCase(formBean.getTestCaseId());
         def.setTitle(formBean.getTitle());
         def.setWpId(new BigDecimal(formBean.getWorkProductDis()));        
         def.setDefectId(new BigDecimal(formBean.getDefectId()));
         return def;
     }
    @ActionMapping(params = "action=goUpdateDefect")
    public void processUpdate(ViewDefectListForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
        updateDefectList = new ArrayList<Timesheet>();
      
   
      System.out.println("defectId param :" + defectId);
      
          if(defectId == null) {
           
           // props = PropertiesManager.newInstanceFromProps("/messages.properties");
            //String defectError = props.getProperty("timesheet.error.noselect");
           
            response.setRenderParameter("action", "init");
        }
        else {
            
           // timesheetError = "";
            
            response.setRenderParameter("action", "goUpdateDefect"); 
        }
       
    }

    @RenderMapping(params = "action=goUpdateDefect")
    public ModelAndView processAUpdateR(DefectUpdateForm formBean, RenderRequest request, PortletSession session) {
        defectId = request.getParameter("defectId");
        System.out.println("defectId param :" + defectId);
        log.debug("processAddNew.START");
        ModelAndView mav = new ModelAndView("UpdateDefect"); // display AddDefect.jsp
       
        DefectDao defDao = new DefectDao();
        updateDefect = new Defect();
        updateDefect = defDao.getDefectById(new BigDecimal(defectId));
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
         
        formBean.setDefectId(updateDefect.getDefectId().toString());
        formBean.setProjectDis(updateDefect.getProjectId().toString());
        formBean.setMemberDisCreated(updateDefect.getCreatedBy());
        formBean.setTitle(updateDefect.getTitle());
        formBean.setDescription(updateDefect.getDescription());
        formBean.setQcActivityDis(updateDefect.getQaId().toString());
        formBean.setOriginDis(updateDefect.getProcessId().toString());
        formBean.setTypeDis(updateDefect.getDtId().toString());
        formBean.setPriorityDis(updateDefect.getDpId().toString());
        formBean.setWorkProductDis(updateDefect.getWpId().toString());
        formBean.setSeverityDis(updateDefect.getDefsId().toString());
        formBean.setTestCaseId(updateDefect.getTestCase());
        formBean.setMemberDisOwner(updateDefect.getDefectOwner());
        formBean.setMemberDisAssigned(updateDefect.getAssignedTo());
        formBean.setCreateDateString(df.format(updateDefect.getCreateDate()));
        formBean.setDueDateString(df.format(updateDefect.getDueDate()));
        formBean.setCauseAnalysis(updateDefect.getCauseAnalysis());
        formBean.setCorrectiveAction(updateDefect.getSolution());
        mav.addObject("updateDefectForm", formBean);
         qcActivityMap = DMSWorkspace.getDefaultWorkspace().getActivityMap();
         mav.addObject("qcActivityMap", qcActivityMap);
        System.out.println("oringin map size : " + originMap.size());
        mav.addObject("defect", new DefectForm());
        projectMap.remove("");
        System.out.println("contains : "+projectMap.containsKey(""));
        mav.addObject("projectMap", projectMap);
        statusMap.remove("");
        mav.addObject("statusMap", statusMap);
        priorityMap.remove("");
        mav.addObject("priorityMap", priorityMap);
        originMap.remove("");
        mav.addObject("originMap", originMap);
        typeMap.remove("");
        mav.addObject("typeMap", typeMap);
        workProductMap.remove("");
        mav.addObject("workProductMap", workProductMap);
        memberMap.remove("");
        mav.addObject("memberMap", memberMap);
        severityMap.remove("");
        mav.addObject("severityMap", severityMap);
      
        return mav;
    }
    
    
    
}
