package openones.oopms.entity;
// Generated 12:18:17 01-03-2012 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;

/**
 * ProjectDeviation generated by hbm2java
 */
public class ProjectDeviation  implements java.io.Serializable {


     private BigDecimal deviationId;
     private BigDecimal projectId;
     private String modification;
     private BigDecimal action;
     private String reason;
     private String category;
     private String note;

    public ProjectDeviation() {
    }

	
    public ProjectDeviation(BigDecimal deviationId, BigDecimal projectId) {
        this.deviationId = deviationId;
        this.projectId = projectId;
    }
    public ProjectDeviation(BigDecimal deviationId, BigDecimal projectId, String modification, BigDecimal action, String reason, String category, String note) {
       this.deviationId = deviationId;
       this.projectId = projectId;
       this.modification = modification;
       this.action = action;
       this.reason = reason;
       this.category = category;
       this.note = note;
    }
   
    public BigDecimal getDeviationId() {
        return this.deviationId;
    }
    
    public void setDeviationId(BigDecimal deviationId) {
        this.deviationId = deviationId;
    }
    public BigDecimal getProjectId() {
        return this.projectId;
    }
    
    public void setProjectId(BigDecimal projectId) {
        this.projectId = projectId;
    }
    public String getModification() {
        return this.modification;
    }
    
    public void setModification(String modification) {
        this.modification = modification;
    }
    public BigDecimal getAction() {
        return this.action;
    }
    
    public void setAction(BigDecimal action) {
        this.action = action;
    }
    public String getReason() {
        return this.reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getCategory() {
        return this.category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }




}


