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
public class CreateChangeRequestForm {
	private String name;
	private String description;
	private Map<String,String> status;
	private String status_SelectedValue;
	private String projectId;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the status
	 */
	public Map<String, String> getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Map<String, String> status) {
		this.status = status;
	}
	/**
	 * @return the status_SelectedValue
	 */
	public String getStatus_SelectedValue() {
		return status_SelectedValue;
	}
	/**
	 * @param status_SelectedValue the status_SelectedValue to set
	 */
	public void setStatus_SelectedValue(String status_SelectedValue) {
		this.status_SelectedValue = status_SelectedValue;
	}
	/**
	 * @return the projectId
	 */
	public String getProjectId() {
		return projectId;
	}
	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	

}
