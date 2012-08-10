package openones.oopms.projecteye.form;

import java.math.BigDecimal;
import java.util.Date;

public class ExceptionalCost {
	private BigDecimal oopmsExceptionalCostId;
	private BigDecimal projectId;
	private String name;
	private String affectTo;
	private String additionEffect;
	private Date occurDate;
	private String description;

	/**
	 * @return the oopmsExceptionalCostId
	 */
	public BigDecimal getOopmsExceptionalCostId() {
		return oopmsExceptionalCostId;
	}

	/**
	 * @param oopmsExceptionalCostId
	 *            the oopmsExceptionalCostId to set
	 */
	public void setOopmsExceptionalCostId(BigDecimal oopmsExceptionalCostId) {
		this.oopmsExceptionalCostId = oopmsExceptionalCostId;
	}

	/**
	 * @return the projectId
	 */
	public BigDecimal getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId
	 *            the projectId to set
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

}
