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
package openones.oopms.planner.model;

/**
 * @author PNTG
 */
public class ReportInfo {

    private String developerName;
    private long totalOngoingTasks;
    private long totalTentativeTasks;
    private long totalClosedTasks;
    private long totalCancelTasks;
    private long totalLoc;
    private long totalTestCase;
    private long totalPage;
    private long totalSheet;
    
    public ReportInfo (){
        totalLoc = 0;
        totalTestCase = 0;
        totalPage = 0;
        totalSheet = 0;
    }
    /**
     * Get value of developerName.
     * @return the developerName
     */
    public String getDeveloperName() {
        return developerName;
    }
    /**
     * Set the value for developerName.
     * @param developerName the developerName to set
     */
    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }
    /**
     * Get value of totalOngoingTasks.
     * @return the totalOngoingTasks
     */
    public long getTotalOngoingTasks() {
        return totalOngoingTasks;
    }
    /**
     * Set the value for totalOngoingTasks.
     * @param totalOngoingTasks the totalOngoingTasks to set
     */
    public void setTotalOngoingTasks(long totalOngoingTasks) {
        this.totalOngoingTasks = totalOngoingTasks;
    }
    /**
     * Get value of totalTentativeTasks.
     * @return the totalTentativeTasks
     */
    public long getTotalTentativeTasks() {
        return totalTentativeTasks;
    }
    /**
     * Set the value for totalTentativeTasks.
     * @param totalTentativeTasks the totalTentativeTasks to set
     */
    public void setTotalTentativeTasks(long totalTentativeTasks) {
        this.totalTentativeTasks = totalTentativeTasks;
    }
    /**
     * Get value of totalClosedTasks.
     * @return the totalClosedTasks
     */
    public long getTotalClosedTasks() {
        return totalClosedTasks;
    }
    /**
     * Set the value for totalClosedTasks.
     * @param totalClosedTasks the totalClosedTasks to set
     */
    public void setTotalClosedTasks(long totalClosedTasks) {
        this.totalClosedTasks = totalClosedTasks;
    }
    /**
     * Get value of totalCancelTasks.
     * @return the totalCancelTasks
     */
    public long getTotalCancelTasks() {
        return totalCancelTasks;
    }
    /**
     * Set the value for totalCancelTasks.
     * @param totalCancelTasks the totalCancelTasks to set
     */
    public void setTotalCancelTasks(long totalCancelTasks) {
        this.totalCancelTasks = totalCancelTasks;
    }
    /**
     * Get value of totalLoc.
     * @return the totalLoc
     */
    public long getTotalLoc() {
        return totalLoc;
    }
    /**
     * Set the value for totalLoc.
     * @param totalLoc the totalLoc to set
     */
    public void setTotalLoc(long totalLoc) {
        this.totalLoc = totalLoc;
    }
    /**
     * Get value of totalTestCase.
     * @return the totalTestCase
     */
    public long getTotalTestCase() {
        return totalTestCase;
    }
    /**
     * Set the value for totalTestCase.
     * @param totalTestCase the totalTestCase to set
     */
    public void setTotalTestCase(long totalTestCase) {
        this.totalTestCase = totalTestCase;
    }
    /**
     * Get value of totalPage.
     * @return the totalPage
     */
    public long getTotalPage() {
        return totalPage;
    }
    /**
     * Set the value for totalPage.
     * @param totalPage the totalPage to set
     */
    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }
    /**
     * Get value of totalSheet.
     * @return the totalSheet
     */
    public long getTotalSheet() {
        return totalSheet;
    }
    /**
     * Set the value for totalSheet.
     * @param totalSheet the totalSheet to set
     */
    public void setTotalSheet(long totalSheet) {
        this.totalSheet = totalSheet;
    }

}
