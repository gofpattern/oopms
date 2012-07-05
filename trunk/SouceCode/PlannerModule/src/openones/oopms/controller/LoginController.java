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
package openones.oopms.controller;

import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;

import openones.oopms.dao.UserDao;
import openones.oopms.form.LoginForm;
import openones.oopms.model.Developer;
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
import org.springframework.web.portlet.mvc.SimpleFormController;

/**
 * @author Thach.Le
 */
@Controller
@RequestMapping("VIEW")
public class LoginController {
    
    Developer user = new Developer();
    /** Logger for logging. */
    private static Logger log = Logger.getLogger(LoginController.class);

    /**
     * Default screen. If user is "guest" (or null), display Login form. Otherwise (authenticated), display the
     * DefectViewList screen.
     * @return name of view which is the name of the JSP page.
     */
    @RequestMapping
    public String initScreen(RenderRequest request) {
        log.debug("initScreen.START");
        // Get logon user
        PortletSupport portletSupport = new PortletSupport(request);       
            
            return "login";
       
    }
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
    public void processLogin(LoginForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("processLogin.START");
        log.debug("username=" + formBean.getUsername());
        // session.setAttribute("user", formBean);
        
        
        UserDao userDao = new UserDao();
        
        user = userDao.authenticate(formBean.getUsername(), formBean.getPassword());
        
        if (user!=null) {
            // Prepare parameter to render phase
           response.setRenderParameter("action", "login");           
        } else {
            result.rejectValue("username", "error");
            log.error("Error in binding result:" + result.getErrorCount());
        }
    }

    /**
     * Process after the action "login" (method "processLogin") is executed.
     * @return view "ViewDefectList" which next page "ViewDefectList.jsp" will displayed
     */
    @RenderMapping(params = "action=login")
    public ModelAndView postLogin(LoginForm formBean, RenderRequest request) {
        log.debug("postLogin.START");
        // request.setAttribute("user2", formBean);
        ModelAndView mav = new ModelAndView("HomePage"); // display ViewDefectList.jsp
        // mav.addObject("helloForm", new HelloForm());
        PortletSession session = request.getPortletSession();
           
            session.setAttribute("USER", user.getAccount(),PortletSession.APPLICATION_SCOPE);
              
      
        return mav;
    }
}
