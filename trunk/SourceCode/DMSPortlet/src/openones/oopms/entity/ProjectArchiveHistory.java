package openones.oopms.entity;
// Generated 12:18:17 01-03-2012 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.Date;

/**
 * ProjectArchiveHistory generated by hbm2java
 */
public class ProjectArchiveHistory  implements java.io.Serializable {


     private BigDecimal projectArchiveHistoryId;
     private BigDecimal projectId;
     private String projectArchiveStatus;
     private BigDecimal developerId;
     private Date effectDate;
     private String description;

    public ProjectArchiveHistory() {
    }

	
    public ProjectArchiveHistory(BigDecimal projectArchiveHistoryId, BigDecimal projectId, BigDecimal developerId, Date effectDate) {
        this.projectArchiveHistoryId = projectArchiveHistoryId;
        this.projectId = projectId;
        this.developerId = developerId;
        this.effectDate = effectDate;
    }
    public ProjectArchiveHistory(BigDecimal projectArchiveHistoryId, BigDecimal projectId, String projectArchiveStatus, BigDecimal developerId, Date effectDate, String description) {
       this.projectArchiveHistoryId = projectArchiveHistoryId;
       this.projectId = projectId;
       this.projectArchiveStatus = projectArchiveStatus;
       this.developerId = developerId;
       this.effectDate = effectDate;
       this.description = description;
    }
   
    public BigDecimal getProjectArchiveHistoryId() {
        return this.projectArchiveHistoryId;
    }
    
    public void setProjectArchiveHistoryId(BigDecimal projectArchiveHistoryId) {
        this.projectArchiveHistoryId = projectArchiveHistoryId;
    }
    public BigDecimal getProjectId() {
        return this.projectId;
    }
    
    public void setProjectId(BigDecimal projectId) {
        this.projectId = projectId;
    }
    public String getProjectArchiveStatus() {
        return this.projectArchiveStatus;
    }
    
    public void setProjectArchiveStatus(String projectArchiveStatus) {
        this.projectArchiveStatus = projectArchiveStatus;
    }
    public BigDecimal getDeveloperId() {
        return this.developerId;
    }
    
    public void setDeveloperId(BigDecimal developerId) {
        this.developerId = developerId;
    }
    public Date getEffectDate() {
        return this.effectDate;
    }
    
    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }




}


