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
package openones.oopms.requirement.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import openones.oopms.requirement.model.Requirements;

import java.util.List;


/**
 * @author Kenda
 */
public class RequirementAddForm {
    Map<String,String> projectMap;
    private String projectDefault;
    private String title;        
    private List<Requirements> requirementList;
    
    private String requirementName;
    private BigDecimal reqSize;
    private BigDecimal reqType;
    private String srs;
    private String releaseNote;
    private String document;
    private BigDecimal effort;
    private String createdDate;
    private String designedDate;
    private String codedDate;
    private String testedDate;
    private String deployedDate;
    private String acceptedDate;
    private String cancelledDate;

    
    
    public RequirementAddForm() {
        // TODO Auto-generated constructor stub         
        
    }
    
    
    
    
    /**
     * Get value of requirementList.
     * @return the requirementList
     */
    public List<Requirements> getRequirementList() {
        return requirementList;
    }




    /**
     * Set the value for requirementList.
     * @param requirementList the requirementList to set
     */
    public void setRequirementList(List<Requirements> requirementList) {
        this.requirementList = requirementList;
    }




    /**
     * Get value of srs.
     * @return the srs
     */
    public String getSrs() {
        return srs;
    }




    /**
     * Set the value for srs.
     * @param srs the srs to set
     */
    public void setSrs(String srs) {
        this.srs = srs;
    }




    /**
     * Get value of reqSize.
     * @return the reqSize
     */
    public BigDecimal getReqSize() {
        return reqSize;
    }



    /**
     * Set the value for reqSize.
     * @param reqSize the reqSize to set
     */
    public void setReqSize(BigDecimal reqSize) {
        this.reqSize = reqSize;
    }



    /**
     * Get value of reqType.
     * @return the reqType
     */
    public BigDecimal getReqType() {
        return reqType;
    }



    /**
     * Set the value for reqType.
     * @param reqType the reqType to set
     */
    public void setReqType(BigDecimal reqType) {
        this.reqType = reqType;
    }



    /**
     * Get value of releaseNote.
     * @return the releaseNote
     */
    public String getReleaseNote() {
        return releaseNote;
    }



    /**
     * Set the value for releaseNote.
     * @param releaseNote the releaseNote to set
     */
    public void setReleaseNote(String releaseNote) {
        this.releaseNote = releaseNote;
    }



    /**
     * Get value of document.
     * @return the document
     */
    public String getDocument() {
        return document;
    }



    /**
     * Set the value for document.
     * @param document the document to set
     */
    public void setDocument(String document) {
        this.document = document;
    }



    /**
     * Get value of effort.
     * @return the effort
     */
    public BigDecimal getEffort() {
        return effort;
    }



    /**
     * Set the value for effort.
     * @param effort the effort to set
     */
    public void setEffort(BigDecimal effort) {
        this.effort = effort;
    }



    /**
     * Get value of createdDate.
     * @return the createdDate
     */
    public String getCreatedDate() {
        return createdDate;
    }



    /**
     * Set the value for createdDate.
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }



    /**
     * Get value of designedDate.
     * @return the designedDate
     */
    public String getDesignedDate() {
        return designedDate;
    }



    /**
     * Set the value for designedDate.
     * @param designedDate the designedDate to set
     */
    public void setDesignedDate(String designedDate) {
        this.designedDate = designedDate;
    }



    /**
     * Get value of codedDate.
     * @return the codedDate
     */
    public String getCodedDate() {
        return codedDate;
    }



    /**
     * Set the value for codedDate.
     * @param codedDate the codedDate to set
     */
    public void setCodedDate(String codedDate) {
        this.codedDate = codedDate;
    }



    /**
     * Get value of testedDate.
     * @return the testedDate
     */
    public String getTestedDate() {
        return testedDate;
    }



    /**
     * Set the value for testedDate.
     * @param testedDate the testedDate to set
     */
    public void setTestedDate(String testedDate) {
        this.testedDate = testedDate;
    }



    /**
     * Get value of deployedDate.
     * @return the deployedDate
     */
    public String getDeployedDate() {
        return deployedDate;
    }



    /**
     * Set the value for deployedDate.
     * @param deployedDate the deployedDate to set
     */
    public void setDeployedDate(String deployedDate) {
        this.deployedDate = deployedDate;
    }



    /**
     * Get value of acceptedDate.
     * @return the acceptedDate
     */
    public String getAcceptedDate() {
        return acceptedDate;
    }



    /**
     * Set the value for acceptedDate.
     * @param acceptedDate the acceptedDate to set
     */
    public void setAcceptedDate(String acceptedDate) {
        this.acceptedDate = acceptedDate;
    }



    /**
     * Get value of cancelledDate.
     * @return the cancelledDate
     */
    public String getCancelledDate() {
        return cancelledDate;
    }



    /**
     * Set the value for cancelledDate.
     * @param cancelledDate the cancelledDate to set
     */
    public void setCancelledDate(String cancelledDate) {
        this.cancelledDate = cancelledDate;
    }



    /**
     * Get value of requirementName.
     * @return the requirementName
     */
    public String getRequirementName() {
        return requirementName;
    }
    /**
     * Set the value for requirementName.
     * @param requirementName the requirementName to set
     */
    public void setRequirementName(String requirementName) {
        this.requirementName = requirementName;
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
   

    public Map getProjectMap() {
        return projectMap;
    }
      
    public String getProjectDefault() {
        return projectDefault;
    }
    public void setProjectDefault(String projectDefault) {
        this.projectDefault = projectDefault;
    }
    public void setProjectMap(Map<String, String> projectMap) {
        this.projectMap = projectMap;
    }

   

 
   
}
