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

import openones.oopms.dms.biz.DMSWorkspace;
import openones.oopms.dms.form.DefectForm;
import openones.oopms.dms.form.UserInfo;

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
@RequestMapping(value = "VIEW")
public class AddDefectController extends BaseController {

    /**
     * Create bean for form.
     * @return Form bean for UI.
     */
    @ModelAttribute("defect")
    public DefectForm getCommandObject() {
        log.debug("getCommandObject.START");
        DefectForm formBean = new DefectForm();

        return formBean;
    }
    
    @RequestMapping
    public String initScreen(RenderRequest request) {
        log.debug("initScreen.START");
        return "AddDefect";
    }
    
    @RenderMapping(params = "action=goAddNewDefect")
    public ModelAndView processAddNew(RenderRequest request, PortletSession session) {
        log.debug("processAddNew.START");

        ModelAndView mav = new ModelAndView("AddDefect"); // display AddDefect.jsp
        Map<Integer, String> qcActivityMap = DMSWorkspace.getDefaultWorkspace().getActivityMap();
        
        mav.addObject("defect", new DefectForm());
        mav.addObject("qcActivity", qcActivityMap);

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
    public void processSave(DefectForm formBean, BindingResult result, SessionStatus status, ActionResponse response, PortletSession session) {
        log.debug("processLogin.START");

        if (!result.hasErrors()) {
            UserInfo userInfo = getUserInfo(session);

            // Prepare parameter to render phase
            response.setRenderParameter("action", "goViewDefectList2");
        } else {
            log.error("Error in binding result:" + result.getErrorCount());
        }
    }   
}
