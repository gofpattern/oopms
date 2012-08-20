package openones.oopms.projecteye.form;

public class PayCostForm {
	private String projectId;
	private String oopmsExceptionalCostId;
	private String oopmsCostDailyExpenseId;
	private String oopmsCostOneTimeExpenseId;
	private String payDate;
	private String templatePath;
	private String exportPath;

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
	 * @return the oopmsExceptionalCostId
	 */
	public String getOopmsExceptionalCostId() {
		return oopmsExceptionalCostId;
	}

	/**
	 * @param oopmsExceptionalCostId
	 *            the oopmsExceptionalCostId to set
	 */
	public void setOopmsExceptionalCostId(String oopmsExceptionalCostId) {
		this.oopmsExceptionalCostId = oopmsExceptionalCostId;
	}

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

	/**
	 * @return the payDate
	 */
	public String getPayDate() {
		return payDate;
	}

	/**
	 * @param payDate
	 *            the payDate to set
	 */
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	/**
	 * @return the templatePath
	 */
	public String getTemplatePath() {
		return templatePath;
	}

	/**
	 * @param templatePath
	 *            the templatePath to set
	 */
	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	/**
	 * @return the exportPath
	 */
	public String getExportPath() {
		return exportPath;
	}

	/**
	 * @param exportPath
	 *            the exportPath to set
	 */
	public void setExportPath(String exportPath) {
		this.exportPath = exportPath;
	}

}
