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
package openones.oopms.portlet.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;

import openones.oopms.daocommon.DeveloperDao;
import openones.oopms.entity.Developer;
import openones.oopms.form.CommonInfo;
import openones.oopms.form.SubMenu;
import openones.oopms.form.UserInfo;
import openones.oopms.util.BaseUtil;
import openones.portlet.PortletSupport;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.portlet.ModelAndView;

/**
 * @author Open-Ones team
 */
public class BaseController {
    private static final String SK_USER_INFO = "UserInfo";
    private static final String SK_COMMON_INFO = "CommonInfo";

    /** Logger for logging. */
    public final Logger log = Logger.getLogger(this.getClass());

    // Get information of authenticated user: position
    // UserInfo userInfo = new UserInfo();

    /**
     * Initial render.
     * Get logon user from the portal or from the configuration if the portlet runs on the GlassFish + OpenPortlet Container.
     * If the portlet runs within the portal (ex: uPortal) and the authenticated user is not exist in the database of OOPMS,
     * then user will be created.
     * @param request
     * @param session
     * @return
     */
    public ModelAndView initScreen(RenderRequest request, PortletSession session) {
        log.debug("initScreen.START");
        PortletSupport portletSupport = new PortletSupport(request);
        String logonUser = portletSupport.getLogonUser();

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
            } else {
                dev = new Developer();
                dev.setName(logonUser);
                dev.setAccount(logonUser);
                dev.setStatus(BigDecimal.ONE);
                dev.setRole(BaseUtil.getProperies().getProperty("DefRole"));
                dev.setGroupName(BaseUtil.getProperies().getProperty("DefGroup"));
                if (devDao.insertDeveloper(dev)) {
                    log.info("Created user: " + dev.getAccount());
                } else {
                    log.warn("Could not create user: " + dev.getAccount());
                }
            }
            // Update userInfo into the session
            updateUserInfo(session, userInfo);
        }

        return null;
    }
    /**
     * [Give the description for method].
     * @param session
     * @return
     */
    public UserInfo getUserInfo(PortletSession session) {
        return (UserInfo) session.getAttribute(SK_USER_INFO);
    }

    /**
     * [Give the description for method].
     * @param session
     * @param userInfo
     */
    public void updateUserInfo(PortletSession session, UserInfo userInfo) {
        session.setAttribute(SK_USER_INFO, userInfo);
    }

    /**
     * [Give the description for method].
     * @param session
     * @param commonInfo
     */
    public void updateCommonInfo(PortletSession session, CommonInfo commonInfo) {
        session.setAttribute(SK_COMMON_INFO, commonInfo);
    }

    /**
     * [Give the description for method].
     * @param session
     * @return
     */
    public CommonInfo getCommonInfo(PortletSession session) {
        return (CommonInfo) session.getAttribute(SK_COMMON_INFO);
    }

    /**
     * [Give the description for method].
     * @param session
     * @param projectMap
     */
    public void updateProjectMap(PortletSession session, Map<Integer, String> projectMap) {
        CommonInfo commonInfo = getCommonInfo(session);

        if (commonInfo == null) {
            commonInfo = new CommonInfo();
        }
        commonInfo.setProjectMap(projectMap);
        updateCommonInfo(session, commonInfo);
    }

    public void updateMenuBar(PortletSession session, List<SubMenu> subMenuList) {
        session.setAttribute("MenuBar", subMenuList);
    }
    
    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, RenderRequest request) {
        log.debug("Handling exception", ex);
        return "GeneralError";
    }

}
