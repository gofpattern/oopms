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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import openones.oopms.planner.model.Tasks;
/**
 * @author PNTG
 */
public class PlannerForm {
    private int flag;
    private Boolean init;
    private String taskId;
    private String currentTaskIndex;
    private String projectId;

    private String statusDefault;
    private String stageDefault;
    private String developerDefault;
    private String projectDefault;

    private List<Tasks> taskList;
    private List<Tasks> searchResult;

    Map<String, String> statusMap;
    Map<String, String> stageMap;
    Map<String, String> developerMap;
    Map<String, String> processMap;
    Map<String, String> projectMap;

    public PlannerForm() {
        statusMap = new LinkedHashMap<String, String>();
        stageMap = new LinkedHashMap<String, String>();
        developerMap = new LinkedHashMap<String, String>();
        projectMap = new LinkedHashMap<String, String>();

        statusMap.put(" ", " ");
        stageMap.put(" ", " ");
        developerMap.put(" ", " ");
        projectMap.put(" ", " ");

        flag = 0;
        init = true;
    }

    /**
     * Get value of currentTaskIndex.
     * @return the currentTaskIndex
     */
    public String getCurrentTaskIndex() {
        return currentTaskIndex;
    }

    /**
     * Set the value for currentTaskIndex.
     * @param currentTaskIndex the currentTaskIndex to set
     */
    public void setCurrentTaskIndex(String currentTaskIndex) {
        this.currentTaskIndex = currentTaskIndex;
    }

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
     * Get value of flag.
     * @return the flag
     */
    public int getFlag() {
        return flag;
    }

    /**
     * Set the value for flag.
     * @param flag the flag to set
     */
    public void setFlag(int flag) {
        this.flag = flag;
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

    /**
     * Get value of searchResult.
     * @return the searchResult
     */
    public List<Tasks> getSearchResult() {
        return searchResult;
    }

    /**
     * Set the value for searchResult.
     * @param searchResult the searchResult to set
     */
    public void setSearchResult(List<Tasks> searchResult) {
        this.searchResult = searchResult;
    }
    
    

}
