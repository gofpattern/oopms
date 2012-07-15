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
    private String title;
    private String projectId;
    private String statusDefault;
    private String stageDefault;
    private String developerDefault;

    private List<Tasks> taskList;
    private List<Stage> stageList;
    private List<Process> processList;


    Map<String, String> statusMap;
    Map<String, String> stageMap;
    Map<String, String> developerMap;
    Map<String, String> processMap;

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
     * Set the value for title.
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
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

}
