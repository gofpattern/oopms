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

import java.util.List;


/**
 * @author HaiTCT
 */
public class TeamManagementForm {
	private String projectId;
	private String developerId;
	private String searchString;
	private String searchType;
	private List<TeamManagement> projectTeamList;
	/**
	 * @return the searchType
	 */
	public String getSearchType() {
		return searchType;
	}
	/**
	 * @param searchType the searchType to set
	 */
	public void setSearchType(String searchType) {
		this.searchType = searchType;
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
	/**
	 * @return the developerId
	 */
	public String getDeveloperId() {
		return developerId;
	}
	/**
	 * @param developerId the developerId to set
	 */
	public void setDeveloperId(String developerId) {
		this.developerId = developerId;
	}
	/**
	 * @return the searchString
	 */
	public String getSearchString() {
		return searchString;
	}
	/**
	 * @param searchString the searchString to set
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	/**
	 * @return the projectTeamList
	 */
	public List<TeamManagement> getProjectTeamList() {
		return projectTeamList;
	}
	/**
	 * @param projectTeamList the projectTeamList to set
	 */
	public void setProjectTeamList(List<TeamManagement> projectTeamList) {
		this.projectTeamList = projectTeamList;
	}

	
	
	
}
