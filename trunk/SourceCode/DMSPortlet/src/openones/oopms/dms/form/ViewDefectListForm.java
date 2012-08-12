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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import openones.oopms.entity.DefectPriority;
import openones.oopms.entity.DefectSeverity;
import openones.oopms.entity.DefectStatus;
import openones.oopms.entity.DefectType;
import openones.oopms.entity.Developer;
import openones.oopms.entity.Project;
import openones.oopms.entity.Workproduct;


/**j
 * @author Thach.Le
 */
public class ViewDefectListForm extends BaseDefectListForm {
    // Map project
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
    private String title;
    private String testCaseId;
    String memberDisCreated="";
    String memberDisAssigned="";
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
    private String typeDis="";
    // Map Work Product
    Map<String, String> originMap;
    
    private String createDate;
    
    private String dueDate;
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
     * @return the serverityDis
     */
    public String getSeverityDis() {
        return severityDis;
    }
    /**
     * @param serverityDis the serverityDis to set
     */
    public void setSeverityDis(String serverityDis) {
        this.severityDis = serverityDis;
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
     * @return the workProductMap
     */
    public Map<String, String> getWorkProductMap() {
        return workProductMap;
    }
    /**
     * @param workProductMap the workProductMap to set
     */
    public void setWorkProductMap(Map<String, String> workProductMap) {
        this.workProductMap = workProductMap;
    }
    /**
     * @return the projectList
     */
    public List<Project> getProjectList() {
        return projectList;
    }
    /**
     * @param projectList the projectList to set
     */
    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }
    /**
     * @return the developerList
     */
    public List<Developer> getDeveloperList() {
        return developerList;
    }
    /**
     * @param developerList the developerList to set
     */
    public void setDeveloperList(List<Developer> developerList) {
        this.developerList = developerList;
    }
    /**
     * @return the defStatusList
     */
    public List<DefectStatus> getDefStatusList() {
        return defStatusList;
    }
    /**
     * @param defStatusList the defStatusList to set
     */
    public void setDefStatusList(List<DefectStatus> defStatusList) {
        this.defStatusList = defStatusList;
    }
    /**
     * @return the defSeverityList
     */
    public List<DefectSeverity> getDefSeverityList() {
        return defSeverityList;
    }
    /**
     * @param defSeverityList the defSeverityList to set
     */
    public void setDefSeverityList(List<DefectSeverity> defSeverityList) {
        this.defSeverityList = defSeverityList;
    }
    /**
     * @return the defPriorityList
     */
    public List<DefectPriority> getDefPriorityList() {
        return defPriorityList;
    }
    /**
     * @param defPriorityList the defPriorityList to set
     */
    public void setDefPriorityList(List<DefectPriority> defPriorityList) {
        this.defPriorityList = defPriorityList;
    }
    /**
     * @return the defTypeList
     */
    public List<DefectType> getDefTypeList() {
        return defTypeList;
    }
    /**
     * @param defTypeList the defTypeList to set
     */
    public void setDefTypeList(List<DefectType> defTypeList) {
        this.defTypeList = defTypeList;
    }
    /**
     * @return the defOriginList
     */
    public ArrayList<openones.oopms.entity.Process> getDefOriginList() {
        return defOriginList;
    }
    /**
     * @param defOriginList the defOriginList to set
     */
    public void setDefOriginList(ArrayList<openones.oopms.entity.Process> defOriginList) {
        this.defOriginList = defOriginList;
    }
    /**
     * @return the defWorkProductList
     */
    public List<Workproduct> getDefWorkProductList() {
        return defWorkProductList;
    }
    /**
     * @param defWorkProductList the defWorkProductList to set
     */
    public void setDefWorkProductList(List<Workproduct> defWorkProductList) {
        this.defWorkProductList = defWorkProductList;
    }
    private String originDis="";
    // Map Work Product
    private Map<String, String> workProductMap;
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
    private String workProductDis="";
    private  List<Project> projectList = new ArrayList<Project>();
    // List Developer
    private  List<Developer> developerList = new ArrayList<Developer>();
    // List defstatusList
    private  List<DefectStatus> defStatusList = new ArrayList<DefectStatus>();
    // List defSeverityList
    private  List<DefectSeverity> defSeverityList = new ArrayList<DefectSeverity>();
    // List defSeverityList
    private  List<DefectPriority> defPriorityList = new ArrayList<DefectPriority>();
    // List defSeverityList
    private  List<DefectType> defTypeList = new ArrayList<DefectType>();
    // List defSeverityList
    private  ArrayList<openones.oopms.entity.Process> defOriginList = new ArrayList<openones.oopms.entity.Process>();
    // List defSeverityList
    private  List<Workproduct> defWorkProductList = new ArrayList<Workproduct>();
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
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
     * @return the memberDisCreated
     */
    public String getMemberDisCreated() {
        return memberDisCreated;
    }
    /**
     * @param memberDisCreated the memberDisCreated to set
     */
    public void setMemberDisCreated(String memberDisCreated) {
        this.memberDisCreated = memberDisCreated;
    }
    /**
     * @return the memberDisAssigned
     */
    public String getMemberDisAssigned() {
        return memberDisAssigned;
    }
    /**
     * @param memberDisAssigned the memberDisAssigned to set
     */
    public void setMemberDisAssigned(String memberDisAssigned) {
        this.memberDisAssigned = memberDisAssigned;
    }
    
    
    
    
    
    
}
