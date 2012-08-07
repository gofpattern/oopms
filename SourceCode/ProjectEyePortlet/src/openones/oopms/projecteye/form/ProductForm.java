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
public class ProductForm {
	private String name;
	private String workProduct;
	private String plannedSize;
	private String rePlannedSize;
	private String actualSize;
	private String createdSize;
	private String description;
	private String projectId;
	private String productId;
	private String workProduct_SelectedValue;

	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
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
	 * @return the workProduct
	 */
	public String getWorkProduct() {
		return workProduct;
	}

	/**
	 * @param workProduct
	 *            the workProduct to set
	 */
	public void setWorkProduct(String workProduct) {
		this.workProduct = workProduct;
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
	 * @return the createdSize
	 */
	public String getCreatedSize() {
		return createdSize;
	}

	/**
	 * @param createdSize
	 *            the createdSize to set
	 */
	public void setCreatedSize(String createdSize) {
		this.createdSize = createdSize;
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

}
