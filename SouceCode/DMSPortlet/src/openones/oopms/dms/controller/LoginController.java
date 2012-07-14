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

import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;

import openones.oopms.dms.form.LoginForm;
import openones.oopms.dms.form.UserInfo;
import openones.oopms.dms.form.ViewDefectListForm;
import openones.oopms.dms.util.AppUtil;
import openones.portlet.PortletSupport;

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
 * @author Thach.Le
 */
@Controller
@RequestMapping("VIEW")
public class LoginController {
    /** Logger for logging. */
    private final static Logger LOG = Logger.getLogger(LoginController.class);
    
    // Get information of authenticated user: position
    UserInfo userInfo = new UserInfo();

    /**
     * Default screen. If user is "guest" (or null), display Login form. Otherwise (authenticated), display the
     * DefectViewList screen.
     * @return name of view which is the name of the JSP page.
     */
    @RequestMapping
    public ModelAndView initScreen(RenderRequest request, PortletSession session) {
        LOG.debug("initScreen.START");
        ModelAndView mav;
        PortletSupport portletSupport = new PortletSupport(request);
        String logonUser = portletSupport.getLogonUser();

        LOG.debug("logonUser=" + logonUser);

        if ((logonUser == null) || ("guest".equals(logonUser))) {
            mav = new ModelAndView("login"); // Display login.jsp
        } else {
            userInfo.setUsername(logonUser);

            mav = new ModelAndView("ViewDefectList"); // Display ViewDefectList.jsp
            prepareDataForViewDefectList(userInfo, mav);
            
            // Update user roles
            session.setAttribute("UserInfo", userInfo);
            
        }

        return mav;
    }
    /**
     * Create bean for form.
     * @return Form bean for UI.
     */
    @ModelAttribute("loginForm")
    public LoginForm getCommandObject() {
        LOG.debug("getCommandObject.START");
        LoginForm formBean = new LoginForm();
        return formBean;
    }

    /**
     * Process submitted form by clicking "Login" button.
     * @param formBean bean captures input data
     * @param result result of binding data
     * @param status status of session
     * @param response response of action
     */
    @ActionMapping(params = "action=login")
    public void processLogin(LoginForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
        LOG.debug("processLogin.START");
        LOG.debug("username=" + formBean.getUsername());
        // session.setAttribute("user", formBean);
        if (!result.hasErrors()) {
            // Prepare parameter to render phase
            response.setRenderParameter("action", "login");
        } else {
            LOG.error("Error in binding result:" + result.getErrorCount());
        }
        
        // Logon success
        response.setRenderParameter("action", "login");
    }

    /**
     * Process after the action "login" (method "processLogin") is executed.
     * @return view "ViewDefectList" which next page "ViewDefectList.jsp" will displayed
     */
    @RenderMapping(params = "action=login")
    public ModelAndView postLogin(LoginForm formBean, RenderRequest request, PortletSession session) {
        LOG.debug("postLogin.START");
        // request.setAttribute("user2", formBean);

        ModelAndView mav = new ModelAndView("ViewDefectList"); // display ViewDefectList.jsp
        prepareDataForViewDefectList(userInfo, mav);

        // Update user roles
        session.setAttribute("UserInfo", userInfo);
        
        return mav;
    }
    
    /**
     * Prepare data to initialize the screen ViewDefectList.
     * Update information of user: roles, group, loginDate
     * @param userInfo is updated roles by username
     * @param mav contains data
     *   -------------------------------------------
     *   |key             |value
     *   -------------------------------------------
     *   |viewDefectList  |ViewDefectListForm
     */
    void prepareDataForViewDefectList(UserInfo userInfo, ModelAndView mav) {
        ViewDefectListForm viewDefectList = new ViewDefectListForm();
        // Sample data
        // Set roles for user
        userInfo.addRole("Developer");
        userInfo.setGroup("Development");
        userInfo.setLoginDate(AppUtil.getCurrentDate());
        
        // Set projects that logon user is joining
        viewDefectList.addProject("val1", "Project1");
        viewDefectList.addProject("val2", "Project2");

        mav.addObject("viewDefectList", viewDefectList);
    }
}
