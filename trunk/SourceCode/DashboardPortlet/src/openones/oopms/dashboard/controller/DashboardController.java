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
package openones.oopms.dashboard.controller;

import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;

import openones.oopms.form.UserInfo;
import openones.oopms.portlet.controller.BaseController;
import openones.oopms.util.BaseUtil;
import openones.portlet.PortletSupport;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

/**
 * @author PNTG
 */
@Controller
@RequestMapping("VIEW")
public class DashboardController extends BaseController {
    private static Logger log = Logger.getLogger(DashboardController.class);

    /**
     * Default screen.
     * @return name of view which is the name of the JSP page.
     */
    @RequestMapping
    public ModelAndView initScreen(RenderRequest request, PortletSession session) {
        log.debug("initScreen.START");
        ModelAndView mav;
        PortletSupport portletSupport = new PortletSupport(request);
        String logonUser = portletSupport.getLogonUser();

        // Update User Information
        // call super initScreen to get information of user, create user in OOPMS if it has not existed.
        super.initScreen(request, session);
        UserInfo userInfo = new UserInfo(logonUser);
        mav = new ModelAndView("Dashboard"); // Display ViewDefectMode.jsp
        prepareCommonInfo(userInfo, mav, session);

        return mav;

    }
    /**
     * Prepare data to initialize the screen ViewDefectMode. Update information of user: roles, group, loginDate
     * @param userInfo is updated roles by username
     * @param mav contains data ------------------------------------------- |key |value
     *            ------------------------------------------- | |
     */
    void prepareCommonInfo(UserInfo userInfo, ModelAndView mav, PortletSession session) {
        // Sample data
        // Set roles for user
        userInfo.addRole("Developer");
        userInfo.setGroup("Development");
        userInfo.setLoginDate(BaseUtil.getCurrentDate());
        // Update userInfo into the session
        updateUserInfo(session, userInfo);

    }
}
