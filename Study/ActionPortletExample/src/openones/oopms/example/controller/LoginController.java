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
package openones.oopms.example.controller;

import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;


import openones.oopms.example.Form.LoginForm;

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
    
    
    /** Logger for logging. */
    private static Logger log = Logger.getLogger(LoginController.class);

   
    @RequestMapping
    public String initScreen(RenderRequest request) {
        log.debug("initScreen.START");
        // Get logon user
      
            
            return "login";
       
    }
   
    @ModelAttribute("loginForm")
    public LoginForm getCommandObject() {
        log.debug("getCommandObject.START");
        LoginForm formBean = new LoginForm();
        formBean.setUsername("TRUONGMH");
        return formBean;
    }

  
    @ActionMapping(params = "action=forward")
    public void processForward(LoginForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("processForward");
        log.debug("username=" + formBean.getUsername());
       
       
           response.setRenderParameter("action", "forward");          
       
    }

   
    @RenderMapping(params = "action=forward")
    public ModelAndView postForward(LoginForm formBean, RenderRequest request) {
        log.debug("postForward.START");
       
        ModelAndView mav = new ModelAndView("ActionPortlet_view");
      
        return mav;
    }
    
   
    @ActionMapping(params = "action=forward2")
    public void processForward2(LoginForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("processForward2.START");
        log.debug("username=" + formBean.getUsername());
       
           response.setRenderParameter("action", "forward");          
       
    }

   
    @RenderMapping(params = "action=forward2")
    public ModelAndView postForward2(LoginForm formBean, RenderRequest request) {
        log.debug("postForward2.START");
      
        ModelAndView mav = new ModelAndView("ActionPortlet_view_Portlet_2_0"); // display ViewDefectList.jsp
      
        return mav;
    }
    
    @ActionMapping(params = "action=returnHome")
    public void processReturnHome(LoginForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("processReturnHome.START");
        log.debug("username=" + formBean.getUsername());
        // session.setAttribute("user", formBean);
        
       
           response.setRenderParameter("action", "returnHome");          
       
    }

   
    @RenderMapping(params = "action=returnHome")
    public ModelAndView postReturnHome(LoginForm formBean, RenderRequest request) {
        log.debug("postReturnHome.START");
       
        ModelAndView mav = new ModelAndView("ActionPortlet_edit");
     
        return mav;
    }
    
    @ActionMapping(params = "action=return")
    public void processReturn(LoginForm formBean, BindingResult result, SessionStatus status, ActionResponse response) {
        log.debug("processReturn.START");
        log.debug("username=" + formBean.getUsername());
      
        
       
           response.setRenderParameter("action", "return");          
       
    }

   
    @RenderMapping(params = "action=return")
    public ModelAndView postReturn(LoginForm formBean, RenderRequest request) {
        log.debug("postReturn.START");
      
        ModelAndView mav = new ModelAndView("ActionPortlet_view");
      
        return mav;
    }
    
}
