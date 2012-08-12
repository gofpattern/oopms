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
package openones.oopms.dms.form;

import java.util.Map;

/**
 * @author Thach.Le
 *
 */
public class DefectForm extends BaseDefectListForm {

    private String title;
    private String projectOrigin;
    private String description;
    private String ref;
    private String qcActivityDis;
    private String causeAnalysis;
    private String correctiveAction;
    private String workProductDis="";
    // Map Work Product
    private Map<String, String> originMap;
    private String originDis;
    private Map<String, String> projectMap;
    private String projectDis="";
    // Map member
    private Map<String, String> memberMap;
    private  String memberDis="";
    // Map Process
    private Map<String, String> statusMap;
    private String statusDis="";
    // Map Work Product
    private Map<String, String> severityMap;
    private String severityDis="";
    // Map Work Product
    private Map<String, String> priorityMap;
    private String priorityDis="";
    // Map Work Product
    private Map<String, String> typeMap;   
    private String typeDis="";
    private String testCaseId;
    private String createDate;
    private String dueDate;
    
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
     * Get value of projectOrigin.
     * @return the projectOrigin
     */
    public String getProjectOrigin() {
        return projectOrigin;
    }
    /**
     * Set the value for projectOrigin.
     * @param projectOrigin the projectOrigin to set
     */
    public void setProjectOrigin(String projectOrigin) {
        this.projectOrigin = projectOrigin;
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
     * Get value of ref.
     * @return the ref
     */
    public String getRef() {
        return ref;
    }
    /**
     * Set the value for ref.
     * @param ref the ref to set
     */
    public void setRef(String ref) {
        this.ref = ref;
    }
    /**
     * Get value of qcActivity.
     * @return the qcActivity
     */
    public String getQcActivity() {
        return qcActivityDis;
    }
    /**
     * Set the value for qcActivity.
     * @param qcActivity the qcActivity to set
     */
    public void setQcActivity(String qcActivity) {
        this.qcActivityDis = qcActivity;
    }
    /**
     * @return the causeAnalysis
     */
    public String getCauseAnalysis() {
        return causeAnalysis;
    }
    /**
     * @param causeAnalysis the causeAnalysis to set
     */
    public void setCauseAnalysis(String causeAnalysis) {
        this.causeAnalysis = causeAnalysis;
    }
    /**
     * @return the correctiveAction
     */
    public String getCorrectiveAction() {
        return correctiveAction;
    }
    /**
     * @param correctiveAction the correctiveAction to set
     */
    public void setCorrectiveAction(String correctiveAction) {
        this.correctiveAction = correctiveAction;
    }
    /**
     * @return the qcActivityDis
     */
    public String getQcActivityDis() {
        return qcActivityDis;
    }
    /**
     * @param qcActivityDis the qcActivityDis to set
     */
    public void setQcActivityDis(String qcActivityDis) {
        this.qcActivityDis = qcActivityDis;
    }
    /**
     * @return the projectMap
     */
    public Map<String, String> getProjectMap() {
        return projectMap;
    }
    /**
     * @param projectMap the projectMap to set
     */
    public void setProjectMap(Map<String, String> projectMap) {
        this.projectMap = projectMap;
    }
    /**
     * @return the projectDis
     */
    public String getProjectDis() {
        return projectDis;
    }
    /**
     * @param projectDis the projectDis to set
     */
    public void setProjectDis(String projectDis) {
        this.projectDis = projectDis;
    }
    /**
     * @return the memberMap
     */
    public Map<String, String> getMemberMap() {
        return memberMap;
    }
    /**
     * @param memberMap the memberMap to set
     */
    public void setMemberMap(Map<String, String> memberMap) {
        this.memberMap = memberMap;
    }
    /**
     * @return the memberDis
     */
    public String getMemberDis() {
        return memberDis;
    }
    /**
     * @param memberDis the memberDis to set
     */
    public void setMemberDis(String memberDis) {
        this.memberDis = memberDis;
    }
    /**
     * @return the statusMap
     */
    public Map<String, String> getStatusMap() {
        return statusMap;
    }
    /**
     * @param statusMap the statusMap to set
     */
    public void setStatusMap(Map<String, String> statusMap) {
        this.statusMap = statusMap;
    }
    /**
     * @return the statusDis
     */
    public String getStatusDis() {
        return statusDis;
    }
    /**
     * @param statusDis the statusDis to set
     */
    public void setStatusDis(String statusDis) {
        this.statusDis = statusDis;
    }
    /**
     * @return the severityMap
     */
    public Map<String, String> getSeverityMap() {
        return severityMap;
    }
    /**
     * @param severityMap the severityMap to set
     */
    public void setSeverityMap(Map<String, String> severityMap) {
        this.severityMap = severityMap;
    }
    /**
     * @return the severityDis
     */
    public String getSeverityDis() {
        return severityDis;
    }
    /**
     * @param severityDis the severityDis to set
     */
    public void setSeverityDis(String severityDis) {
        this.severityDis = severityDis;
    }
    /**
     * @return the priorityMap
     */
    public Map<String, String> getPriorityMap() {
        return priorityMap;
    }
    /**
     * @param priorityMap the priorityMap to set
     */
    public void setPriorityMap(Map<String, String> priorityMap) {
        this.priorityMap = priorityMap;
    }
    /**
     * @return the priorityDis
     */
    public String getPriorityDis() {
        return priorityDis;
    }
    /**
     * @param priorityDis the priorityDis to set
     */
    public void setPriorityDis(String priorityDis) {
        this.priorityDis = priorityDis;
    }
    /**
     * @return the typeMap
     */
    public Map<String, String> getTypeMap() {
        return typeMap;
    }
    /**
     * @param typeMap the typeMap to set
     */
    public void setTypeMap(Map<String, String> typeMap) {
        this.typeMap = typeMap;
    }
    /**
     * @return the testCaseId
     */
    public String getTestCaseId() {
        return testCaseId;
    }
    /**
     * @param testCaseId the testCaseId to set
     */
    public void setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
    }
    /**
     * @return the createDate
     */
    public String getCreateDate() {
        return createDate;
    }
    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    /**
     * @return the dueDate
     */
    public String getDueDate() {
        return dueDate;
    }
    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    /**
     * @return the originMap
     */
    public Map<String, String> getOriginMap() {
        return originMap;
    }
    /**
     * @param originMap the originMap to set
     */
    public void setOriginMap(Map<String, String> originMap) {
        this.originMap = originMap;
    }
    /**
     * @return the originDis
     */
    public String getOriginDis() {
        return originDis;
    }
    /**
     * @param originDis the originDis to set
     */
    public void setOriginDis(String originDis) {
        this.originDis = originDis;
    }
    /**
     * @return the typeDis
     */
    public String getTypeDis() {
        return typeDis;
    }
    /**
     * @param typeDis the typeDis to set
     */
    public void setTypeDis(String typeDis) {
        this.typeDis = typeDis;
    }
    /**
     * @return the workProductDis
     */
    public String getWorkProductDis() {
        return workProductDis;
    }
    /**
     * @param workProductDis the workProductDis to set
     */
    public void setWorkProductDis(String workProductDis) {
        this.workProductDis = workProductDis;
    }
    
    
    
}
