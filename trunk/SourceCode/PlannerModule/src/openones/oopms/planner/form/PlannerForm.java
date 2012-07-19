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
package openones.oopms.planner.form;

import java.util.List;
import java.util.Map;

import openones.oopms.planner.model.Process;
import openones.oopms.planner.model.Stage;
import openones.oopms.planner.model.Tasks;
/**
 * @author PNTG
 */
public class PlannerForm {
    private String taskId;
    private String title;
    private String startDate;
    private String endDate;
    private String plannedEffort;
    private String actualEffort;
    private String stageId;
    private String processId;
    private String productId;
    private String developerId;
    private String statusId;
    private String description;
    private String projectId;

    private String statusDefault;
    private String stageDefault;
    private String developerDefault;
    private String projectDefault;

    private List<Tasks> taskList;
    private List<Stage> stageList;
    private List<Process> processList;
    private List<Process> projectList;
    private List<Process> productList;

    Map<String, String> statusMap;
    Map<String, String> stageMap;
    Map<String, String> developerMap;
    Map<String, String> processMap;
    Map<String, String> projectMap;
    Map<String, String> productMap;

    /**
     * Get value of taskId.
     * @return the taskId
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * Set the value for taskId.
     * @param taskId the taskId to set
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * Get value of startDate.
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Set the value for startDate.
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Get value of endDate.
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Set the value for endDate.
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Get value of plannedEffort.
     * @return the plannedEffort
     */
    public String getPlannedEffort() {
        return plannedEffort;
    }

    /**
     * Set the value for plannedEffort.
     * @param plannedEffort the plannedEffort to set
     */
    public void setPlannedEffort(String plannedEffort) {
        this.plannedEffort = plannedEffort;
    }

    /**
     * Get value of actualEffort.
     * @return the actualEffort
     */
    public String getActualEffort() {
        return actualEffort;
    }

    /**
     * Set the value for actualEffort.
     * @param actualEffort the actualEffort to set
     */
    public void setActualEffort(String actualEffort) {
        this.actualEffort = actualEffort;
    }

    /**
     * Get value of stageId.
     * @return the stageId
     */
    public String getStageId() {
        return stageId;
    }

    /**
     * Set the value for stageId.
     * @param stageId the stageId to set
     */
    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    /**
     * Get value of processId.
     * @return the processId
     */
    public String getProcessId() {
        return processId;
    }

    /**
     * Set the value for processId.
     * @param processId the processId to set
     */
    public void setProcessId(String processId) {
        this.processId = processId;
    }

    /**
     * Get value of productId.
     * @return the productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     * Set the value for productId.
     * @param productId the productId to set
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * Get value of developerId.
     * @return the developerId
     */
    public String getDeveloperId() {
        return developerId;
    }

    /**
     * Set the value for developerId.
     * @param developerId the developerId to set
     */
    public void setDeveloperId(String developerId) {
        this.developerId = developerId;
    }

    /**
     * Get value of statusId.
     * @return the statusId
     */
    public String getStatusId() {
        return statusId;
    }

    /**
     * Set the value for statusId.
     * @param statusId the statusId to set
     */
    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    /**
     * Get value of description.
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the value for description.
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get value of processMap.
     * @return the processMap
     */
    public Map<String, String> getProcessMap() {
        return processMap;
    }

    /**
     * Set the value for processMap.
     * @param processMap the processMap to set
     */
    public void setProcessMap(Map<String, String> processMap) {
        this.processMap = processMap;
    }

    /**
     * Get value of developerDefault.
     * @return the developerDefault
     */
    public String getDeveloperDefault() {
        return developerDefault;
    }

    /**
     * Set the value for developerDefault.
     * @param developerDefault the developerDefault to set
     */
    public void setDeveloperDefault(String developerDefault) {
        this.developerDefault = developerDefault;
    }

    /**
     * Get value of developerMap.
     * @return the developerMap
     */
    public Map<String, String> getDeveloperMap() {
        return developerMap;
    }

    /**
     * Set the value for developerMap.
     * @param developerMap the developerMap to set
     */
    public void setDeveloperMap(Map<String, String> developerMap) {
        this.developerMap = developerMap;
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
     * Get value of stageDefault.
     * @return the stageDefault
     */
    public String getStageDefault() {
        return stageDefault;
    }

    /**
     * Set the value for stageDefault.
     * @param stageDefault the stageDefault to set
     */
    public void setStageDefault(String stageDefault) {
        this.stageDefault = stageDefault;
    }

    /**
     * Get value of stageMap.
     * @return the stageMap
     */
    public Map<String, String> getStageMap() {
        return stageMap;
    }

    /**
     * Set the value for stageMap.
     * @param stageMap the stageMap to set
     */
    public void setStageMap(Map<String, String> stageMap) {
        this.stageMap = stageMap;
    }

    public PlannerForm() {

    }

    /**
     * Get value of title.
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the value for title.
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get value of statusDefault.
     * @return the statusDefault
     */
    public String getStatusDefault() {
        return statusDefault;
    }

    /**
     * Set the value for statusDefault.
     * @param statusDefault the statusDefault to set
     */
    public void setStatusDefault(String statusDefault) {
        this.statusDefault = statusDefault;
    }

    /**
     * Get value of taskList.
     * @return the taskList
     */
    public List<Tasks> getTaskList() {
        return taskList;
    }

    /**
     * Set the value for taskList.
     * @param taskList the taskList to set
     */
    public void setTaskList(List<Tasks> taskList) {
        this.taskList = taskList;
    }

    /**
     * Get value of stageList.
     * @return the stageList
     */
    public List<Stage> getStageList() {
        return stageList;
    }

    /**
     * Set the value for stageList.
     * @param stageList the stageList to set
     */
    public void setStageList(List<Stage> stageList) {
        this.stageList = stageList;
    }

    /**
     * Get value of processList.
     * @return the processList
     */
    public List<Process> getProcessList() {
        return processList;
    }

    /**
     * Set the value for processList.
     * @param processList the processList to set
     */
    public void setProcessList(List<Process> processList) {
        this.processList = processList;
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

    /**
     * Get value of projectDefault.
     * @return the projectDefault
     */
    public String getProjectDefault() {
        return projectDefault;
    }

    /**
     * Set the value for projectDefault.
     * @param projectDefault the projectDefault to set
     */
    public void setProjectDefault(String projectDefault) {
        this.projectDefault = projectDefault;
    }

    /**
     * Get value of projectList.
     * @return the projectList
     */
    public List<Process> getProjectList() {
        return projectList;
    }

    /**
     * Set the value for projectList.
     * @param projectList the projectList to set
     */
    public void setProjectList(List<Process> projectList) {
        this.projectList = projectList;
    }

    /**
     * Get value of projectMap.
     * @return the projectMap
     */
    public Map<String, String> getProjectMap() {
        return projectMap;
    }

    /**
     * Set the value for projectMap.
     * @param projectMap the projectMap to set
     */
    public void setProjectMap(Map<String, String> projectMap) {
        this.projectMap = projectMap;
    }

    /**
     * Get value of productList.
     * @return the productList
     */
    public List<Process> getProductList() {
        return productList;
    }

    /**
     * Set the value for productList.
     * @param productList the productList to set
     */
    public void setProductList(List<Process> productList) {
        this.productList = productList;
    }

    /**
     * Get value of productMap.
     * @return the productMap
     */
    public Map<String, String> getProductMap() {
        return productMap;
    }

    /**
     * Set the value for productMap.
     * @param productMap the productMap to set
     */
    public void setProductMap(Map<String, String> productMap) {
        this.productMap = productMap;
    }

}
