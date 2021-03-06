package openones.oopms.entity;
// Generated 12:18:17 01-03-2012 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;

/**
 * TailoringDeviation generated by hbm2java
 */
public class TailoringDeviation  implements java.io.Serializable {


     private BigDecimal tailoringId;
     private BigDecimal projectId;
     private String modification;
     private BigDecimal action;
     private String reason;
     private BigDecimal type;
     private String category;
     private String note;

    public TailoringDeviation() {
    }

	
    public TailoringDeviation(BigDecimal tailoringId) {
        this.tailoringId = tailoringId;
    }
    public TailoringDeviation(BigDecimal tailoringId, BigDecimal projectId, String modification, BigDecimal action, String reason, BigDecimal type, String category, String note) {
       this.tailoringId = tailoringId;
       this.projectId = projectId;
       this.modification = modification;
       this.action = action;
       this.reason = reason;
       this.type = type;
       this.category = category;
       this.note = note;
    }
   
    public BigDecimal getTailoringId() {
        return this.tailoringId;
    }
    
    public void setTailoringId(BigDecimal tailoringId) {
        this.tailoringId = tailoringId;
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
    public BigDecimal getType() {
        return this.type;
    }
    
    public void setType(BigDecimal type) {
        this.type = type;
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


