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

import java.util.Date;
import java.util.Map;

/**
 * @author HaiTCT
 */
public class CreateDeliverableForm {
	private Map<String, String> deliverable;
	private Date plannedCommittedDate;
	private Date rePlannedCommittedDate;
	private Date actualCommittedDate;
	private Map<String, String> status;
	private String note;
	private String deliverable_SelectedValue;
	private String status_SelectedValue;
	private String projectId;
	/**
	 * @return the deliverable
	 */
	public Map<String, String> getDeliverable() {
		return deliverable;
	}
	/**
	 * @param deliverable the deliverable to set
	 */
	public void setDeliverable(Map<String, String> deliverable) {
		this.deliverable = deliverable;
	}
	/**
	 * @return the plannedCommittedDate
	 */
	public Date getPlannedCommittedDate() {
		return plannedCommittedDate;
	}
	/**
	 * @param plannedCommittedDate the plannedCommittedDate to set
	 */
	public void setPlannedCommittedDate(Date plannedCommittedDate) {
		this.plannedCommittedDate = plannedCommittedDate;
	}
	/**
	 * @return the rePlannedCommittedDate
	 */
	public Date getRePlannedCommittedDate() {
		return rePlannedCommittedDate;
	}
	/**
	 * @param rePlannedCommittedDate the rePlannedCommittedDate to set
	 */
	public void setRePlannedCommittedDate(Date rePlannedCommittedDate) {
		this.rePlannedCommittedDate = rePlannedCommittedDate;
	}
	/**
	 * @return the actualCommittedDate
	 */
	public Date getActualCommittedDate() {
		return actualCommittedDate;
	}
	/**
	 * @param actualCommittedDate the actualCommittedDate to set
	 */
	public void setActualCommittedDate(Date actualCommittedDate) {
		this.actualCommittedDate = actualCommittedDate;
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
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @return the deliverable_SelectedValue
	 */
	public String getDeliverable_SelectedValue() {
		return deliverable_SelectedValue;
	}
	/**
	 * @param deliverable_SelectedValue the deliverable_SelectedValue to set
	 */
	public void setDeliverable_SelectedValue(String deliverable_SelectedValue) {
		this.deliverable_SelectedValue = deliverable_SelectedValue;
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
