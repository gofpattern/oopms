package openones.oopms.projecteye.form;

import java.util.Date;

public class UpdateDailyExpenseForm {
	private String name;
	private String cost;
	private String startDate;
	private String endDate;
	private String[] days;
	private String costType_SelectedValue;
	private String description;
	private String projectId;
	private String oopmsCostDailyExpenseId;

	/**
	 * @return the oopmsCostDailyExpenseId
	 */
	public String getOopmsCostDailyExpenseId() {
		return oopmsCostDailyExpenseId;
	}

	/**
	 * @param oopmsCostDailyExpenseId
	 *            the oopmsCostDailyExpenseId to set
	 */
	public void setOopmsCostDailyExpenseId(String oopmsCostDailyExpenseId) {
		this.oopmsCostDailyExpenseId = oopmsCostDailyExpenseId;
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
	 * @return the cost
	 */
	public String getCost() {
		return cost;
	}

	/**
	 * @param cost
	 *            the cost to set
	 */
	public void setCost(String cost) {
		this.cost = cost;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the days
	 */
	public String[] getDays() {
		return days;
	}

	/**
	 * @param days
	 *            the days to set
	 */
	public void setDays(String[] days) {
		this.days = days;
	}

	/**
	 * @return the costType_SelectedValue
	 */
	public String getCostType_SelectedValue() {
		return costType_SelectedValue;
	}

	/**
	 * @param costType_SelectedValue
	 *            the costType_SelectedValue to set
	 */
	public void setCostType_SelectedValue(String costType_SelectedValue) {
		this.costType_SelectedValue = costType_SelectedValue;
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
