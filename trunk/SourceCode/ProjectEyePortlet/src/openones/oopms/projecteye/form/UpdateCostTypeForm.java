package openones.oopms.projecteye.form;

public class UpdateCostTypeForm {
	private String name;
	private String description;
	private String projectId;
	private String oopmsCostTypeId;

	/**
	 * @return the oopmsCostTypeId
	 */
	public String getOopmsCostTypeId() {
		return oopmsCostTypeId;
	}

	/**
	 * @param oopmsCostTypeId the oopmsCostTypeId to set
	 */
	public void setOopmsCostTypeId(String oopmsCostTypeId) {
		this.oopmsCostTypeId = oopmsCostTypeId;
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
