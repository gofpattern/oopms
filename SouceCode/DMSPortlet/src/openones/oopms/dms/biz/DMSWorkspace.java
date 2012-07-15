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
package openones.oopms.dms.biz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import openones.oopms.daocommon.QcActivityDao;
import openones.oopms.entity.QcActivity;

/**
 * @author Thach.Le
 */
public class DMSWorkspace {
    /** Authenticated username. */
    private String username;

    private static DMSWorkspace defaultWsp = new DMSWorkspace();

    /** Table of (username, DMSWorkspace). */
    private static Map<String, DMSWorkspace> dmsWspMap = new HashMap<String, DMSWorkspace>();

    /**
     * @param username
     */
    public DMSWorkspace() {
    }
    
    /**
     * @param username
     */
    public DMSWorkspace(String username) {
        super();
        this.username = username;
    }
    
    public static DMSWorkspace getDefaultWorkspace() {
        return defaultWsp;
    }

    public static DMSWorkspace getDefaultWorkspace(String username) {
        DMSWorkspace wsp;
        if (!dmsWspMap.containsKey(username)) {
            dmsWspMap.put(username, new DMSWorkspace(username));
        }

        return dmsWspMap.get(username);
    }

    /**
     * Get roles of given user.
     * @return
     */
    public Collection<String> getRoles() {
        String[] roles = new String[]{"Developer", "Team Leader"};

        return Arrays.asList(roles);
    }

    /**
     * Get projects of user.
     * @return
     */
    public Collection<String> getProjects() {
        String[] roles = new String[]{"OOPMS", "InterWeb"};

        return Arrays.asList(roles);
    }

    public Map<String, String> getActivityMap() {
//        QcActivityDao dao = new QcActivityDao();
//        ArrayList<QcActivity> qcActivityList = dao.getQcActivity();
//        Map<String, String> actMap = new HashMap<String, String>();
//        
//        for (QcActivity qcAct : qcActivityList) {
//            actMap.put(qcAct.getCode(), qcAct.getName());
//        }
        Map<String, String> actMap = new HashMap<String, String>();
        actMap.put("11", "11-Integration test");
        return actMap;
    }
}
