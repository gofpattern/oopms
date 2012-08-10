package openones.oopms.projecteye.form;

import java.math.BigDecimal;
import java.util.Date;

public class DailyExpense {
	private BigDecimal oopmsCostDailyExpenseId;
    private BigDecimal projectId;
    private String name;
    private BigDecimal cost;
    private Date startDate;
    private Date endDate;
    private String dateUsed;
    private String type;
    private String description;
	/**
	 * @return the oopmsCostDailyExpenseId
	 */
	public BigDecimal getOopmsCostDailyExpenseId() {
		return oopmsCostDailyExpenseId;
	}
	/**
	 * @param oopmsCostDailyExpenseId the oopmsCostDailyExpenseId to set
	 */
	public void setOopmsCostDailyExpenseId(BigDecimal oopmsCostDailyExpenseId) {
		this.oopmsCostDailyExpenseId = oopmsCostDailyExpenseId;
	}
	/**
	 * @return the projectId
	 */
	public BigDecimal getProjectId() {
		return projectId;
	}
	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(BigDecimal projectId) {
		this.projectId = projectId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the cost
	 */
	public BigDecimal getCost() {
		return cost;
	}
	/**
	 * @param cost the cost to set
	 */
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the dateUsed
	 */
	public String getDateUsed() {
		return dateUsed;
	}
	/**
	 * @param dateUsed the dateUsed to set
	 */
	public void setDateUsed(String dateUsed) {
		this.dateUsed = dateUsed;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
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
    
    
}
