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

import org.apache.log4j.Logger;

import openones.oopms.dms.form.UserInfo;

/**
 * @author Thach.Le
 */
public class BaseController {
    private static final String SK_USER_INFO = "UserInfo";

    /** Logger for logging. */
    final Logger log = Logger.getLogger(this.getClass());
    
    // Get information of authenticated user: position
    // UserInfo userInfo = new UserInfo();
    
    public UserInfo getUserInfo(PortletSession session) { 
        return (UserInfo) session.getAttribute(SK_USER_INFO);
    }
    
    public void updateUserInfo(PortletSession session, UserInfo userInfo) {
        session.setAttribute(SK_USER_INFO, userInfo);
    }
}
