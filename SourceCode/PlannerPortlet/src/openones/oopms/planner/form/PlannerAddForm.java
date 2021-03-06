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
import java.util.Map;

import openones.oopms.planner.model.Tasks;
import openones.oopms.planner.utils.Constant;

/**
 * @author PNTG
 */
public class PlannerAddForm {
    private Tasks editTask;
    private Tasks task;
    private String startDate;
    private String actualDate;
    private String projectId;

    private String action_str;

    Map<String, String> statusMap;
    Map<String, String> stageMap;
    Map<String, String> developerMap;
    Map<String, String> processMap;
    Map<String, String> productMap;
    Map<String, String> moduleMap;
    Map<String, String> sizeUnitMap;

    public PlannerAddForm() {

        editTask = new Tasks();
        task = new Tasks();

        statusMap = new LinkedHashMap<String, String>();
        stageMap = new LinkedHashMap<String, String>();
        developerMap = new LinkedHashMap<String, String>();
        processMap = new LinkedHashMap<String, String>();
        productMap = new LinkedHashMap<String, String>();
        moduleMap = new LinkedHashMap<String, String>();
        sizeUnitMap = new LinkedHashMap<String, String>();

        statusMap.put(Constant.NULL_VALUE, Constant.BLANK_VALUE);
        stageMap.put(Constant.NULL_VALUE, Constant.BLANK_VALUE);
        stageMap.put(Constant.NULL_VALUE, Constant.BLANK_VALUE);
        developerMap.put(Constant.NULL_VALUE, Constant.BLANK_VALUE);
        processMap.put(Constant.NULL_VALUE, Constant.BLANK_VALUE);
        productMap.put(Constant.NULL_VALUE, Constant.BLANK_VALUE);
        moduleMap.put(Constant.NULL_VALUE, Constant.BLANK_VALUE);
        sizeUnitMap.put(Constant.NULL_VALUE, Constant.BLANK_VALUE);
        projectId = Constant.NULL_VALUE;

        action_str = Constant.NONE_ACTION;
    }

    /**
     * Get value of editTask.
     * @return the editTask
     */
    public Tasks getEditTask() {
        return editTask;
    }

    /**
     * Set the value for editTask.
     * @param editTask the editTask to set
     */
    public void setEditTask(Tasks editTask) {
        this.editTask = editTask;
    }

    /**
     * Get value of action_str.
     * @return the action_str
     */
    public String getAction_str() {
        return action_str;
    }

    /**
     * Set the value for action_str.
     * @param action_str the action_str to set
     */
    public void setAction_str(String action_str) {
        this.action_str = action_str;
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
     * Get value of actualDate.
     * @return the actualDate
     */
    public String getActualDate() {
        return actualDate;
    }

    /**
     * Set the value for actualDate.
     * @param actualDate the actualDate to set
     */
    public void setActualDate(String actualDate) {
        this.actualDate = actualDate;
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

    /**
     * Get value of task.
     * @return the task
     */
    public Tasks getTask() {
        return task;
    }

    /**
     * Set the value for task.
     * @param task the task to set
     */
    public void setTask(Tasks task) {
        this.task = task;
    }

    /**
     * Get value of moduleMap.
     * @return the moduleMap
     */
    public Map<String, String> getModuleMap() {
        return moduleMap;
    }

    /**
     * Set the value for moduleMap.
     * @param moduleMap the moduleMap to set
     */
    public void setModuleMap(Map<String, String> moduleMap) {
        this.moduleMap = moduleMap;
    }

    /**
     * Get value of sizeUnitMap.
     * @return the sizeUnitMap
     */
    public Map<String, String> getSizeUnitMap() {
        return sizeUnitMap;
    }

    /**
     * Set the value for sizeUnitMap.
     * @param sizeUnitMap the sizeUnitMap to set
     */
    public void setSizeUnitMap(Map<String, String> sizeUnitMap) {
        this.sizeUnitMap = sizeUnitMap;
    }

}
