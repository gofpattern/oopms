package openones.oopms.entity;
// Generated 12:18:17 01-03-2012 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.Date;

/**
 * Nc generated by hbm2java
 */
public class Nc  implements java.io.Serializable {


     private BigDecimal ncid;
     private BigDecimal nclevel;
     private String projectid;
     private BigDecimal nctype;
     private BigDecimal detectedby;
     private String code;
     private String description;
     private String creator;
     private Date creationdate;
     private BigDecimal status;
     private BigDecimal typeofcause;
     private String cause;
     private BigDecimal process;
     private String impact;
     private BigDecimal typeofaction;
     private String cpaction;
     private String assignee;
     private Date deadline;
     private BigDecimal repeat;
     private String note;
     private Date closuredate;
     private String reviewer;
     private BigDecimal kpa;
     private BigDecimal isoclause;
     private String groupname;
     private Date reviewdate;
     private String updatedBy;
     private String effectofchange;

    public Nc() {
    }

	
    public Nc(BigDecimal ncid, BigDecimal nclevel, String projectid, BigDecimal nctype, String creator, Date creationdate, String groupname) {
        this.ncid = ncid;
        this.nclevel = nclevel;
        this.projectid = projectid;
        this.nctype = nctype;
        this.creator = creator;
        this.creationdate = creationdate;
        this.groupname = groupname;
    }
    public Nc(BigDecimal ncid, BigDecimal nclevel, String projectid, BigDecimal nctype, BigDecimal detectedby, String code, String description, String creator, Date creationdate, BigDecimal status, BigDecimal typeofcause, String cause, BigDecimal process, String impact, BigDecimal typeofaction, String cpaction, String assignee, Date deadline, BigDecimal repeat, String note, Date closuredate, String reviewer, BigDecimal kpa, BigDecimal isoclause, String groupname, Date reviewdate, String updatedBy, String effectofchange) {
       this.ncid = ncid;
       this.nclevel = nclevel;
       this.projectid = projectid;
       this.nctype = nctype;
       this.detectedby = detectedby;
       this.code = code;
       this.description = description;
       this.creator = creator;
       this.creationdate = creationdate;
       this.status = status;
       this.typeofcause = typeofcause;
       this.cause = cause;
       this.process = process;
       this.impact = impact;
       this.typeofaction = typeofaction;
       this.cpaction = cpaction;
       this.assignee = assignee;
       this.deadline = deadline;
       this.repeat = repeat;
       this.note = note;
       this.closuredate = closuredate;
       this.reviewer = reviewer;
       this.kpa = kpa;
       this.isoclause = isoclause;
       this.groupname = groupname;
       this.reviewdate = reviewdate;
       this.updatedBy = updatedBy;
       this.effectofchange = effectofchange;
    }
   
    public BigDecimal getNcid() {
        return this.ncid;
    }
    
    public void setNcid(BigDecimal ncid) {
        this.ncid = ncid;
    }
    public BigDecimal getNclevel() {
        return this.nclevel;
    }
    
    public void setNclevel(BigDecimal nclevel) {
        this.nclevel = nclevel;
    }
    public String getProjectid() {
        return this.projectid;
    }
    
    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }
    public BigDecimal getNctype() {
        return this.nctype;
    }
    
    public void setNctype(BigDecimal nctype) {
        this.nctype = nctype;
    }
    public BigDecimal getDetectedby() {
        return this.detectedby;
    }
    
    public void setDetectedby(BigDecimal detectedby) {
        this.detectedby = detectedby;
    }
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCreator() {
        return this.creator;
    }
    
    public void setCreator(String creator) {
        this.creator = creator;
    }
    public Date getCreationdate() {
        return this.creationdate;
    }
    
    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }
    public BigDecimal getStatus() {
        return this.status;
    }
    
    public void setStatus(BigDecimal status) {
        this.status = status;
    }
    public BigDecimal getTypeofcause() {
        return this.typeofcause;
    }
    
    public void setTypeofcause(BigDecimal typeofcause) {
        this.typeofcause = typeofcause;
    }
    public String getCause() {
        return this.cause;
    }
    
    public void setCause(String cause) {
        this.cause = cause;
    }
    public BigDecimal getProcess() {
        return this.process;
    }
    
    public void setProcess(BigDecimal process) {
        this.process = process;
    }
    public String getImpact() {
        return this.impact;
    }
    
    public void setImpact(String impact) {
        this.impact = impact;
    }
    public BigDecimal getTypeofaction() {
        return this.typeofaction;
    }
    
    public void setTypeofaction(BigDecimal typeofaction) {
        this.typeofaction = typeofaction;
    }
    public String getCpaction() {
        return this.cpaction;
    }
    
    public void setCpaction(String cpaction) {
        this.cpaction = cpaction;
    }
    public String getAssignee() {
        return this.assignee;
    }
    
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
    public Date getDeadline() {
        return this.deadline;
    }
    
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
    public BigDecimal getRepeat() {
        return this.repeat;
    }
    
    public void setRepeat(BigDecimal repeat) {
        this.repeat = repeat;
    }
    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
    public Date getClosuredate() {
        return this.closuredate;
    }
    
    public void setClosuredate(Date closuredate) {
        this.closuredate = closuredate;
    }
    public String getReviewer() {
        return this.reviewer;
    }
    
    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }
    public BigDecimal getKpa() {
        return this.kpa;
    }
    
    public void setKpa(BigDecimal kpa) {
        this.kpa = kpa;
    }
    public BigDecimal getIsoclause() {
        return this.isoclause;
    }
    
    public void setIsoclause(BigDecimal isoclause) {
        this.isoclause = isoclause;
    }
    public String getGroupname() {
        return this.groupname;
    }
    
    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }
    public Date getReviewdate() {
        return this.reviewdate;
    }
    
    public void setReviewdate(Date reviewdate) {
        this.reviewdate = reviewdate;
    }
    public String getUpdatedBy() {
        return this.updatedBy;
    }
    
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
    public String getEffectofchange() {
        return this.effectofchange;
    }
    
    public void setEffectofchange(String effectofchange) {
        this.effectofchange = effectofchange;
    }




}


