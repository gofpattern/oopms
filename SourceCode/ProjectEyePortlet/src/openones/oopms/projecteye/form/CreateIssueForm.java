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
public class CreateIssueForm {
	private String description;
	private Map<String,String> priority;
	private Map<String,String> status;
	private Map<String,String> type;
	private Map<String,String> processRelated;
	private Date dueDate;
	private Date closedDate;
	private String commentSolution;
	private String reference;
	private String priority_SelectedValue;
	private String status_SelectedValue;
	private String type_SelectedValue;
	private String processRelated_SelectedValue;
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
	 * @return the priority
	 */
	public Map<String, String> getPriority() {
		return priority;
	}
	/**
	 * @param priority the priority to set
	 */
	public void setPriority(Map<String, String> priority) {
		this.priority = priority;
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
	 * @return the type
	 */
	public Map<String, String> getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(Map<String, String> type) {
		this.type = type;
	}
	/**
	 * @return the processRelated
	 */
	public Map<String, String> getProcessRelated() {
		return processRelated;
	}
	/**
	 * @param processRelated the processRelated to set
	 */
	public void setProcessRelated(Map<String, String> processRelated) {
		this.processRelated = processRelated;
	}
	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}
	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	/**
	 * @return the closedDate
	 */
	public Date getClosedDate() {
		return closedDate;
	}
	/**
	 * @param closedDate the closedDate to set
	 */
	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}
	/**
	 * @return the commentSolution
	 */
	public String getCommentSolution() {
		return commentSolution;
	}
	/**
	 * @param commentSolution the commentSolution to set
	 */
	public void setCommentSolution(String commentSolution) {
		this.commentSolution = commentSolution;
	}
	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}
	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}
	/**
	 * @return the priority_SelectedValue
	 */
	public String getPriority_SelectedValue() {
		return priority_SelectedValue;
	}
	/**
	 * @param priority_SelectedValue the priority_SelectedValue to set
	 */
	public void setPriority_SelectedValue(String priority_SelectedValue) {
		this.priority_SelectedValue = priority_SelectedValue;
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
	 * @return the type_SelectedValue
	 */
	public String getType_SelectedValue() {
		return type_SelectedValue;
	}
	/**
	 * @param type_SelectedValue the type_SelectedValue to set
	 */
	public void setType_SelectedValue(String type_SelectedValue) {
		this.type_SelectedValue = type_SelectedValue;
	}
	/**
	 * @return the processRelated_SelectedValue
	 */
	public String getProcessRelated_SelectedValue() {
		return processRelated_SelectedValue;
	}
	/**
	 * @param processRelated_SelectedValue the processRelated_SelectedValue to set
	 */
	public void setProcessRelated_SelectedValue(String processRelated_SelectedValue) {
		this.processRelated_SelectedValue = processRelated_SelectedValue;
	}
	
	
}
