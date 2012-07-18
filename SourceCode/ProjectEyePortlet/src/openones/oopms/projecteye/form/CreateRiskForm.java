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

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author HaiTCT
 */
public class CreateRiskForm {
	private Map<String, String> riskSource;
	private String description;
	private BigDecimal probability;
	private Map<String, String> estimatedImpactTo;
	private Map<String, String> estimatedImpactUnit;
	private BigDecimal estimatedImpact;
	private Byte totalImpact;
	private Byte riskPriority;
	private String trigger;
	private String buttonAction;
	private String riskSource_SelectedValue;
	private String estimatedImpactTo_SelectedValue;
	private String estimatedImpactUnit_SelectedValue;

	/**
	 * @return the riskSource
	 */
	public Map<String, String> getRiskSource() {
		return riskSource;
	}

	/**
	 * @param riskSource
	 *            the riskSource to set
	 */
	public void setRiskSource(Map<String, String> riskSource) {
		this.riskSource = riskSource;
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
	 * @return the probability
	 */
	public BigDecimal getProbability() {
		return probability;
	}

	/**
	 * @param probability
	 *            the probability to set
	 */
	public void setProbability(BigDecimal probability) {
		this.probability = probability;
	}

	/**
	 * @return the estimatedImpactTo
	 */
	public Map<String, String> getEstimatedImpactTo() {
		return estimatedImpactTo;
	}

	/**
	 * @param estimatedImpactTo
	 *            the estimatedImpactTo to set
	 */
	public void setEstimatedImpactTo(Map<String, String> estimatedImpactTo) {
		this.estimatedImpactTo = estimatedImpactTo;
	}

	/**
	 * @return the estimatedImpactUnit
	 */
	public Map<String, String> getEstimatedImpactUnit() {
		return estimatedImpactUnit;
	}

	/**
	 * @param estimatedImpactUnit
	 *            the estimatedImpactUnit to set
	 */
	public void setEstimatedImpactUnit(Map<String, String> estimatedImpactUnit) {
		this.estimatedImpactUnit = estimatedImpactUnit;
	}

	/**
	 * @return the estimatedImpact
	 */
	public BigDecimal getEstimatedImpact() {
		return estimatedImpact;
	}

	/**
	 * @param estimatedImpact
	 *            the estimatedImpact to set
	 */
	public void setEstimatedImpact(BigDecimal estimatedImpact) {
		this.estimatedImpact = estimatedImpact;
	}

	/**
	 * @return the totalImpact
	 */
	public Byte getTotalImpact() {
		return totalImpact;
	}

	/**
	 * @param totalImpact
	 *            the totalImpact to set
	 */
	public void setTotalImpact(Byte totalImpact) {
		this.totalImpact = totalImpact;
	}

	/**
	 * @return the riskPriority
	 */
	public Byte getRiskPriority() {
		return riskPriority;
	}

	/**
	 * @param riskPriority
	 *            the riskPriority to set
	 */
	public void setRiskPriority(Byte riskPriority) {
		this.riskPriority = riskPriority;
	}

	/**
	 * @return the trigger
	 */
	public String getTrigger() {
		return trigger;
	}

	/**
	 * @param trigger
	 *            the trigger to set
	 */
	public void setTrigger(String trigger) {
		this.trigger = trigger;
	}

	/**
	 * @return the buttonAction
	 */
	public String getButtonAction() {
		return buttonAction;
	}

	/**
	 * @param buttonAction
	 *            the buttonAction to set
	 */
	public void setButtonAction(String buttonAction) {
		this.buttonAction = buttonAction;
	}

	/**
	 * @return the riskSource_SelectedValue
	 */
	public String getRiskSource_SelectedValue() {
		return riskSource_SelectedValue;
	}

	/**
	 * @param riskSource_SelectedValue
	 *            the riskSource_SelectedValue to set
	 */
	public void setRiskSource_SelectedValue(String riskSource_SelectedValue) {
		this.riskSource_SelectedValue = riskSource_SelectedValue;
	}

	/**
	 * @return the estimatedImpactTo_SelectedValue
	 */
	public String getEstimatedImpactTo_SelectedValue() {
		return estimatedImpactTo_SelectedValue;
	}

	/**
	 * @param estimatedImpactTo_SelectedValue
	 *            the estimatedImpactTo_SelectedValue to set
	 */
	public void setEstimatedImpactTo_SelectedValue(
			String estimatedImpactTo_SelectedValue) {
		this.estimatedImpactTo_SelectedValue = estimatedImpactTo_SelectedValue;
	}

	/**
	 * @return the estimatedImpactUnit_SelectedValue
	 */
	public String getEstimatedImpactUnit_SelectedValue() {
		return estimatedImpactUnit_SelectedValue;
	}

	/**
	 * @param estimatedImpactUnit_SelectedValue
	 *            the estimatedImpactUnit_SelectedValue to set
	 */
	public void setEstimatedImpactUnit_SelectedValue(
			String estimatedImpactUnit_SelectedValue) {
		this.estimatedImpactUnit_SelectedValue = estimatedImpactUnit_SelectedValue;
	}

}
