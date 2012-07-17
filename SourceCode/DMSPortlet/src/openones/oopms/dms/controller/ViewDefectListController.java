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

import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;

import openones.oopms.dms.form.UserInfo;
import openones.oopms.dms.form.ViewDefectListForm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 * @author Thach.Le
 */
@Controller
@RequestMapping(value = "VIEW")
public class ViewDefectListController extends BaseController {

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
    
    @RequestMapping
    public String initScreen(RenderRequest request) {
        log.debug("initScreen.START");
        return "ViewDefectList";
    }

    @RenderMapping(params = "action=goViewDefectMode")
    public ModelAndView goViewDefectMode(RenderRequest request, PortletSession session) {
        log.debug("goViewDefectMode.START");

        ModelAndView mav = new ModelAndView("ViewDefectMode"); // display ViewDefectMode.jsp
        
        return mav;
    }
    
    /**
     * Process render event "ViewDefectList".
     * Param kindOfDefect: All, Open, Leakage
     * @return view "ViewDefectList" which next page "ViewDefectList.jsp" will displayed
     */
    @RenderMapping(params = "action=goViewDefectList2")
    public ModelAndView processViewDefectList(RenderRequest request, PortletSession session) {
        log.debug("processViewDefectList.START");

        ModelAndView mav = new ModelAndView("ViewDefectList2"); // display ViewDefectList2.jsp
        String kindOfDefect = request.getParameter("kindOfDefect");
        log.info("kindOfDefect=" + kindOfDefect);
        ViewDefectListForm viewDefectList = new ViewDefectListForm();
        // Sample data
        
        // Set projects that logon user is joining
        UserInfo userInfo = getUserInfo(session);
//        DMSWorkspace dmsWsp = DMSWorkspace.getDefaultWorkspace(userInfo.getUsername());
//        
//        viewDefectList.addProject(dmsWsp.getProjects());

        mav.addObject("viewDefectList", viewDefectList);
        return mav;
    }
    


}
