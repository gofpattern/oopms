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

/**
 * @author HaiTCT
 */
public class UpdateStageForm {
	private Date plannedStartDate;
	private Date rePlannedStartDate;
	private Date actualStartDate;
	private Date plannedEndDate;
	private String plannedEndDateOfNextStage;
	private Date rePlannedEndDate;
	private Date actualEndDate;
	private String description;
	private String milestone;
	private String projectId;
	private String stageId;
	private String stage;

	/**
	 * @return the plannedEndDateOfNextStage
	 */
	public String getPlannedEndDateOfNextStage() {
		return plannedEndDateOfNextStage;
	}

	/**
	 * @param plannedEndDateOfNextStage
	 *            the plannedEndDateOfNextStage to set
	 */
	public void setPlannedEndDateOfNextStage(String plannedEndDateOfNextStage) {
		this.plannedEndDateOfNextStage = plannedEndDateOfNextStage;
	}

	public String getStageId() {
		return stageId;
	}

	public void setStageId(String stageId) {
		this.stageId = stageId;
	}

	/**
	 * @return the stage
	 */
	public String getStage() {
		return stage;
	}

	/**
	 * @param stage
	 *            the stage to set
	 */
	public void setStage(String stage) {
		this.stage = stage;
	}

	/**
	 * @return the plannedEndDate
	 */
	public Date getPlannedEndDate() {
		return plannedEndDate;
	}

	/**
	 * @param plannedEndDate
	 *            the plannedEndDate to set
	 */
	public void setPlannedEndDate(Date plannedEndDate) {
		this.plannedEndDate = plannedEndDate;
	}

	/**
	 * @return the rePlannedEndDate
	 */
	public Date getRePlannedEndDate() {
		return rePlannedEndDate;
	}

	/**
	 * @param rePlannedEndDate
	 *            the rePlannedEndDate to set
	 */
	public void setRePlannedEndDate(Date rePlannedEndDate) {
		this.rePlannedEndDate = rePlannedEndDate;
	}

	/**
	 * @return the actualEndDate
	 */
	public Date getActualEndDate() {
		return actualEndDate;
	}

	/**
	 * @param actualEndDate
	 *            the actualEndDate to set
	 */
	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the milestone
	 */
	public String getMilestone() {
		return milestone;
	}

	/**
	 * @param milestone
	 *            the milestone to set
	 */
	public void setMilestone(String milestone) {
		this.milestone = milestone;
	}

	/**
	 * @return the plannedStartDate
	 */
	public Date getPlannedStartDate() {
		return plannedStartDate;
	}

	/**
	 * @param plannedStartDate
	 *            the plannedStartDate to set
	 */
	public void setPlannedStartDate(Date plannedStartDate) {
		this.plannedStartDate = plannedStartDate;
	}

	/**
	 * @return the rePlannedStartDate
	 */
	public Date getRePlannedStartDate() {
		return rePlannedStartDate;
	}

	/**
	 * @param rePlannedStartDate
	 *            the rePlannedStartDate to set
	 */
	public void setRePlannedStartDate(Date rePlannedStartDate) {
		this.rePlannedStartDate = rePlannedStartDate;
	}

	/**
	 * @return the actualStartDate
	 */
	public Date getActualStartDate() {
		return actualStartDate;
	}

	/**
	 * @param actualStartDate
	 *            the actualStartDate to set
	 */
	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
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

}
