package openones.oopms.projecteye.form;

import java.util.Date;

public class UpdateOneTimeExpenseForm {
	private String name;
	private String cost;
	private String date;
	private String description;
	private String projectId;
	private String oopmsCostOneTimeExpenseId;

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
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
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

	/**
	 * @return the oopmsCostOneTimeExpenseId
	 */
	public String getOopmsCostOneTimeExpenseId() {
		return oopmsCostOneTimeExpenseId;
	}

	/**
	 * @param oopmsCostOneTimeExpenseId
	 *            the oopmsCostOneTimeExpenseId to set
	 */
	public void setOopmsCostOneTimeExpenseId(String oopmsCostOneTimeExpenseId) {
		this.oopmsCostOneTimeExpenseId = oopmsCostOneTimeExpenseId;
	}

}
