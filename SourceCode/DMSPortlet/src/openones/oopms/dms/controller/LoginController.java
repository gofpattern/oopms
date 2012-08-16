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

import java.util.Map;

import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;

import openones.oopms.daocommon.DeveloperDao;
import openones.oopms.dms.biz.DMSWorkspace;
import openones.oopms.dms.form.LoginForm;
import openones.oopms.dms.form.ViewDefectModeForm;
import openones.oopms.dms.util.AppUtil;
import openones.oopms.entity.Developer;
import openones.oopms.form.UserInfo;
import openones.oopms.portlet.controller.BaseController;
import openones.oopms.util.BaseUtil;
import openones.portlet.PortletSupport;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

/**
 * @author Thach.Le
 */
@Controller
@RequestMapping("VIEW")
public class LoginController extends BaseController {

    /**
     * Default screen. If user is "guest" (or null), display Login form. Otherwise (authenticated), display the
     * DefectViewList screen.
     * @return name of view which is the name of the JSP page.
     */
  

    /**
     * Create bean for form.
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
     * @param formBean bean captures input data
     * @param result result of binding data
     * @param status status of session
     * @param response response of action
     */
    @ActionMapping(params = "action=login")
    public void processLogin(LoginForm formBean, BindingResult result, SessionStatus status, ActionResponse response, PortletSession session) {
        log.debug("processLogin.START");
        log.debug("username=" + formBean.getUsername());

        // session.setAttribute("user", formBean);
        if (!result.hasErrors()) {
            UserInfo userInfo = getUserInfo(session);
            if (userInfo == null) {
                userInfo = new UserInfo(formBean.getUsername());
            }
            userInfo.setUsername(formBean.getUsername());
            updateUserInfo(session, userInfo);
            // Prepare parameter to render phase
            response.setRenderParameter("action", "init");
        } else {
            log.error("Error in binding result:" + result.getErrorCount());
        }
        
        // Logon success
        response.setRenderParameter("action", "login");
    }

    /**
     * Prepare data to initialize the screen ViewDefectMode.
     * Update information of user: roles, group, loginDate
     * @param userInfo is updated roles by username
     * @param mav contains data
     *   -------------------------------------------
     *   |key             |value
     *   -------------------------------------------
     *   |                |
     */
    void prepareCommonInfo(UserInfo userInfo, ModelAndView mav, PortletSession session) {
        // Sample data
        // Set roles for user
        userInfo.addRole("Developer");
        userInfo.setGroup("Development");
        userInfo.setLoginDate(BaseUtil.getCurrentDate());
        // Update userInfo into the session
        updateUserInfo(session, userInfo);
        
        DMSWorkspace dmsWsp = DMSWorkspace.getDefaultWorkspace(userInfo.getUsername());
        Map<Integer, String> projectMap = dmsWsp.getProjectMap();
        
        // Update list of project
        updateProjectMap(session, projectMap);

        // Save menu
        updateMenuBar(session, dmsWsp.getMenuBar());
        
        mav.addObject("viewDefectMode", new ViewDefectModeForm());
    }
    
}
