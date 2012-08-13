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
package openones.oopms.dashboard.form;

import java.util.List;
import java.util.Map;

import openones.oopms.dashboard.model.Dashboard;

/**
 * @author PNTG
 *
 */
public class DashboardForm {
    private List<Dashboard> dashboard;
    Map<String, String> statusMap;
    Map<String, String> projectCategoryMap;
    Map<String, String> projectTypeMap;
    Map<String, String> projectHealthMap;
    
    public DashboardForm() {
        
    }
    
    /**
     * Get value of dashboard.
     * @return the dashboard
     */
    public List<Dashboard> getDashboard() {
        return dashboard;
    }
    /**
     * Set the value for dashboard.
     * @param dashboard the dashboard to set
     */
    public void setDashboard(List<Dashboard> dashboard) {
        this.dashboard = dashboard;
    }
    /**
     * Get value of statusMap.
     * @return the statusMap
     */
    public Map<String, String> getStatusMap() {
        return statusMap;
    }
    /**
     * Set the value for statusMap.
     * @param statusMap the statusMap to set
     */
    public void setStatusMap(Map<String, String> statusMap) {
        this.statusMap = statusMap;
    }
    
    
    
}
