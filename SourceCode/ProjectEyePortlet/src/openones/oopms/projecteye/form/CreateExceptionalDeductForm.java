package openones.oopms.projecteye.form;

import java.util.Date;

public class CreateExceptionalDeductForm {
	private String name;
	private String affectTo;
	private String[] costTypes;
	private String[] dailyExpenses;
	private String additionEffect;
	private String additionEffectInput;
	private Date occurDate;
	private String description;
	private String projectId;

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
	 * @return the affectTo
	 */
	public String getAffectTo() {
		return affectTo;
	}

	/**
	 * @param affectTo
	 *            the affectTo to set
	 */
	public void setAffectTo(String affectTo) {
		this.affectTo = affectTo;
	}

	/**
	 * @return the costTypes
	 */
	public String[] getCostTypes() {
		return costTypes;
	}

	/**
	 * @param costTypes
	 *            the costTypes to set
	 */
	public void setCostTypes(String[] costTypes) {
		this.costTypes = costTypes;
	}

	/**
	 * @return the dailyExpenses
	 */
	public String[] getDailyExpenses() {
		return dailyExpenses;
	}

	/**
	 * @param dailyExpenses
	 *            the dailyExpenses to set
	 */
	public void setDailyExpenses(String[] dailyExpenses) {
		this.dailyExpenses = dailyExpenses;
	}

	/**
	 * @return the additionEffect
	 */
	public String getAdditionEffect() {
		return additionEffect;
	}

	/**
	 * @param additionEffect
	 *            the additionEffect to set
	 */
	public void setAdditionEffect(String additionEffect) {
		this.additionEffect = additionEffect;
	}



	/**
	 * @return the additionEffectInput
	 */
	public String getAdditionEffectInput() {
		return additionEffectInput;
	}

	/**
	 * @param additionEffectInput the additionEffectInput to set
	 */
	public void setAdditionEffectInput(String additionEffectInput) {
		this.additionEffectInput = additionEffectInput;
	}

	/**
	 * @return the occurDate
	 */
	public Date getOccurDate() {
		return occurDate;
	}

	/**
	 * @param occurDate
	 *            the occurDate to set
	 */
	public void setOccurDate(Date occurDate) {
		this.occurDate = occurDate;
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
