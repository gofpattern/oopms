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
package openones.oopms.form;

import java.util.List;

import openones.oopms.model.Process;
import openones.oopms.model.Stage;
import openones.oopms.model.Tasks;
/**
 * @author PNTG
 */
public class PlannerForm {
    private String title;
    private  List<Tasks> taskList;
    private List<Stage> stageList;
    private List<Process> processList;

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
     * Get value of taskList.
     * @return the taskList
     */
    public  List<Tasks> getTaskList() {
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
    
    
}
