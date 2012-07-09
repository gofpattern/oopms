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
package openones.oopms.projecteye.form;

import java.util.Map;

/**
 * @author HaiTCT
 */
public class CreateProjectForm {
	private String projectCode;
	private String projectName;
	private String customer;
	private String endCustomer;
	private String planStartDate;
	private String planEndDate;
	private String scopeObjective;
	Map<String,String> projectStatus;
	Map<String,String> projectType;
	Map<String,String> businessDomain;
	/**
	 * @return the projectStatus
	 */
	public Map<String, String> getProjectStatus() {
		return projectStatus;
	}
	/**
	 * @param projectStatus the projectStatus to set
	 */
	public void setProjectStatus(Map<String, String> projectStatus) {
		this.projectStatus = projectStatus;
	}
	/**
	 * @return the projectType
	 */
	public Map<String, String> getProjectType() {
		return projectType;
	}
	/**
	 * @param projectType the projectType to set
	 */
	public void setProjectType(Map<String, String> projectType) {
		this.projectType = projectType;
	}
	/**
	 * @return the businessDomain
	 */
	public Map<String, String> getBusinessDomain() {
		return businessDomain;
	}
	/**
	 * @param businessDomain the businessDomain to set
	 */
	public void setBusinessDomain(Map<String, String> businessDomain) {
		this.businessDomain = businessDomain;
	}

	/**
	 * @return the projectCode
	 */
	public String getProjectCode() {
		return projectCode;
	}
	/**
	 * @param projectCode the projectCode to set
	 */
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}
	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	/**
	 * @return the customer
	 */
	public String getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	/**
	 * @return the endCustomer
	 */
	public String getEndCustomer() {
		return endCustomer;
	}
	/**
	 * @param endCustomer the endCustomer to set
	 */
	public void setEndCustomer(String endCustomer) {
		this.endCustomer = endCustomer;
	}
	/**
	 * @return the planStartDate
	 */
	public String getPlanStartDate() {
		return planStartDate;
	}
	/**
	 * @param planStartDate the planStartDate to set
	 */
	public void setPlanStartDate(String planStartDate) {
		this.planStartDate = planStartDate;
	}
	/**
	 * @return the planEndDate
	 */
	public String getPlanEndDate() {
		return planEndDate;
	}
	/**
	 * @param planEndDate the planEndDate to set
	 */
	public void setPlanEndDate(String planEndDate) {
		this.planEndDate = planEndDate;
	}
	/**
	 * @return the scopeObjective
	 */
	public String getScopeObjective() {
		return scopeObjective;
	}
	/**
	 * @param scopeObjective the scopeObjective to set
	 */
	public void setScopeObjective(String scopeObjective) {
		this.scopeObjective = scopeObjective;
	}
	
	
}
