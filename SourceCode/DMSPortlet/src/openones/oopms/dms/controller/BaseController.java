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

import java.util.List;
import java.util.Map;

import javax.portlet.PortletSession;

import org.apache.log4j.Logger;

import openones.oopms.dms.form.CommonInfo;
import openones.oopms.dms.form.SubMenu;
import openones.oopms.dms.form.UserInfo;

/**
 * @author Thach.Le
 */
public class BaseController {
    private static final String SK_USER_INFO = "UserInfo";
    private static final String SK_COMMON_INFO = "CommonInfo";

    /** Logger for logging. */
    final Logger log = Logger.getLogger(this.getClass());

    // Get information of authenticated user: position
    // UserInfo userInfo = new UserInfo();

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
}
