package openones.oopms.planner.model;
// Generated Jul 12, 2012 3:49:54 PM by Hibernate Tools 3.2.1.GA

import java.math.BigDecimal;

/**
 * ProjectStatus generated by hbm2java
 */
public class ProjectStatus implements java.io.Serializable {

    private BigDecimal projectStatusId;
    private String projectStatusName;

    public ProjectStatus() {
    }

    public ProjectStatus(BigDecimal projectStatusId, String projectStatusName) {
        this.projectStatusId = projectStatusId;
        this.projectStatusName = projectStatusName;
    }

    public BigDecimal getProjectStatusId() {
        return this.projectStatusId;
    }

    public void setProjectStatusId(BigDecimal projectStatusId) {
        this.projectStatusId = projectStatusId;
    }
    public String getProjectStatusName() {
        return this.projectStatusName;
    }

    public void setProjectStatusName(String projectStatusName) {
        this.projectStatusName = projectStatusName;
    }

}
