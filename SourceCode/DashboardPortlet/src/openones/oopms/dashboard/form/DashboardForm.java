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

import openones.oopms.dashboard.model.Dashboard;

/**
 * @author PNTG
 */
public class DashboardForm {
    public static final String ALL_VALUE = "All";
    private List<Dashboard> dashboard;
    private String projectId;
    private String projectDomain;
    private String projectStatus;
    private String projectCategory;
    private String projectHealth;
    private Boolean init;

    public DashboardForm() {
        init = true;
    }

    /**
     * Get value of projectDomain.
     * @return the projectDomain
     */
    public String getProjectDomain() {
        return projectDomain;
    }

    /**
     * Set the value for projectDomain.
     * @param projectDomain the projectDomain to set
     */
    public void setProjectDomain(String projectDomain) {
        this.projectDomain = projectDomain;
    }

    /**
     * Get value of projectStatus.
     * @return the projectStatus
     */
    public String getProjectStatus() {
        return projectStatus;
    }

    /**
     * Set the value for projectStatus.
     * @param projectStatus the projectStatus to set
     */
    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    /**
     * Get value of projectCategory.
     * @return the projectCategory
     */
    public String getProjectCategory() {
        return projectCategory;
    }

    /**
     * Set the value for projectCategory.
     * @param projectCategory the projectCategory to set
     */
    public void setProjectCategory(String projectCategory) {
        this.projectCategory = projectCategory;
    }

    /**
     * Get value of projectHealth.
     * @return the projectHealth
     */
    public String getProjectHealth() {
        return projectHealth;
    }

    /**
     * Set the value for projectHealth.
     * @param projectHealth the projectHealth to set
     */
    public void setProjectHealth(String projectHealth) {
        this.projectHealth = projectHealth;
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
     * Get value of projectId.
     * @return the projectId
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * Set the value for projectId.
     * @param projectId the projectId to set
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    /**
     * Get value of init.
     * @return the init
     */
    public Boolean getInit() {
        return init;
    }

    /**
     * Set the value for init.
     * @param init the init to set
     */
    public void setInit(Boolean init) {
        this.init = init;
    }

}
