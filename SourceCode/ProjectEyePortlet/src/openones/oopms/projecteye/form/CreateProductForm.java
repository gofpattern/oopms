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
public class CreateProductForm {

	private Map<String, String> workProduct;
	private String name;
	private Map<String, String> plannedSizeUnit;
	private String plannedSize;
	private String rePlannedSize;
	private Map<String, String> actualSizeUnit;
	private String actualSize;
	private String description;
	private String workProduct_SelectedValue;
	private String plannedSizeUnit_SelectedValue;
	private String actualSizeUnit_SelectedValue;
	private String projectId;

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
	 * @return the workProduct
	 */
	public Map<String, String> getWorkProduct() {
		return workProduct;
	}

	/**
	 * @param workProduct
	 *            the workProduct to set
	 */
	public void setWorkProduct(Map<String, String> workProduct) {
		this.workProduct = workProduct;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the plannedSizeUnit
	 */
	public Map<String, String> getPlannedSizeUnit() {
		return plannedSizeUnit;
	}

	/**
	 * @param plannedSizeUnit
	 *            the plannedSizeUnit to set
	 */
	public void setPlannedSizeUnit(Map<String, String> plannedSizeUnit) {
		this.plannedSizeUnit = plannedSizeUnit;
	}

	/**
	 * @return the plannedSize
	 */
	public String getPlannedSize() {
		return plannedSize;
	}

	/**
	 * @param plannedSize
	 *            the plannedSize to set
	 */
	public void setPlannedSize(String plannedSize) {
		this.plannedSize = plannedSize;
	}

	/**
	 * @return the rePlannedSize
	 */
	public String getRePlannedSize() {
		return rePlannedSize;
	}

	/**
	 * @param rePlannedSize
	 *            the rePlannedSize to set
	 */
	public void setRePlannedSize(String rePlannedSize) {
		this.rePlannedSize = rePlannedSize;
	}

	/**
	 * @return the actualSizeUnit
	 */
	public Map<String, String> getActualSizeUnit() {
		return actualSizeUnit;
	}

	/**
	 * @param actualSizeUnit
	 *            the actualSizeUnit to set
	 */
	public void setActualSizeUnit(Map<String, String> actualSizeUnit) {
		this.actualSizeUnit = actualSizeUnit;
	}

	/**
	 * @return the actualSize
	 */
	public String getActualSize() {
		return actualSize;
	}

	/**
	 * @param actualSize
	 *            the actualSize to set
	 */
	public void setActualSize(String actualSize) {
		this.actualSize = actualSize;
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
	 * @return the workProduct_SelectedValue
	 */
	public String getWorkProduct_SelectedValue() {
		return workProduct_SelectedValue;
	}

	/**
	 * @param workProduct_SelectedValue
	 *            the workProduct_SelectedValue to set
	 */
	public void setWorkProduct_SelectedValue(String workProduct_SelectedValue) {
		this.workProduct_SelectedValue = workProduct_SelectedValue;
	}

	/**
	 * @return the plannedSizeUnit_SelectedValue
	 */
	public String getPlannedSizeUnit_SelectedValue() {
		return plannedSizeUnit_SelectedValue;
	}

	/**
	 * @param plannedSizeUnit_SelectedValue
	 *            the plannedSizeUnit_SelectedValue to set
	 */
	public void setPlannedSizeUnit_SelectedValue(
			String plannedSizeUnit_SelectedValue) {
		this.plannedSizeUnit_SelectedValue = plannedSizeUnit_SelectedValue;
	}

	/**
	 * @return the actualSizeUnit_SelectedValue
	 */
	public String getActualSizeUnit_SelectedValue() {
		return actualSizeUnit_SelectedValue;
	}

	/**
	 * @param actualSizeUnit_SelectedValue
	 *            the actualSizeUnit_SelectedValue to set
	 */
	public void setActualSizeUnit_SelectedValue(
			String actualSizeUnit_SelectedValue) {
		this.actualSizeUnit_SelectedValue = actualSizeUnit_SelectedValue;
	}

}
