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

/**
 * @author HaiTCT
 */
public class UpdateDeliverableForm {
	private String plannedCommittedDate;
	private String rePlannedCommittedDate;
	private String actualCommittedDate;
	private String note;
	private String deliverable_SelectedValue;
	private String projectId;
	private String plannedEndDateOfProject;
	private String delivarableId;
	private String delivarableName;

	/**
	 * @return the delivarableId
	 */
	public String getDelivarableId() {
		return delivarableId;
	}

	/**
	 * @param delivarableId
	 *            the delivarableId to set
	 */
	public void setDelivarableId(String delivarableId) {
		this.delivarableId = delivarableId;
	}

	/**
	 * @return the delivarableName
	 */
	public String getDelivarableName() {
		return delivarableName;
	}

	/**
	 * @param delivarableName
	 *            the delivarableName to set
	 */
	public void setDelivarableName(String delivarableName) {
		this.delivarableName = delivarableName;
	}

	/**
	 * @return the plannedCommittedDate
	 */
	public String getPlannedCommittedDate() {
		return plannedCommittedDate;
	}

	/**
	 * @param plannedCommittedDate
	 *            the plannedCommittedDate to set
	 */
	public void setPlannedCommittedDate(String plannedCommittedDate) {
		this.plannedCommittedDate = plannedCommittedDate;
	}

	/**
	 * @return the rePlannedCommittedDate
	 */
	public String getRePlannedCommittedDate() {
		return rePlannedCommittedDate;
	}

	/**
	 * @param rePlannedCommittedDate
	 *            the rePlannedCommittedDate to set
	 */
	public void setRePlannedCommittedDate(String rePlannedCommittedDate) {
		this.rePlannedCommittedDate = rePlannedCommittedDate;
	}

	/**
	 * @return the actualCommittedDate
	 */
	public String getActualCommittedDate() {
		return actualCommittedDate;
	}

	/**
	 * @param actualCommittedDate
	 *            the actualCommittedDate to set
	 */
	public void setActualCommittedDate(String actualCommittedDate) {
		this.actualCommittedDate = actualCommittedDate;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note
	 *            the note to set
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
	 * @param deliverable_SelectedValue
	 *            the deliverable_SelectedValue to set
	 */
	public void setDeliverable_SelectedValue(String deliverable_SelectedValue) {
		this.deliverable_SelectedValue = deliverable_SelectedValue;
	}

	/**
	 * @return the projectId
	 */
	public String getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId
	 *            the projectId to set
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the plannedEndDateOfProject
	 */
	public String getPlannedEndDateOfProject() {
		return plannedEndDateOfProject;
	}

	/**
	 * @param plannedEndDateOfProject
	 *            the plannedEndDateOfProject to set
	 */
	public void setPlannedEndDateOfProject(String plannedEndDateOfProject) {
		this.plannedEndDateOfProject = plannedEndDateOfProject;
	}

}
