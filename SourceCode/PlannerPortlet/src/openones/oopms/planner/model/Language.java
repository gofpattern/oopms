package openones.oopms.planner.model;
// Generated Aug 5, 2012 2:58:18 AM by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.Date;

/**
 * Language generated by hbm2java
 */
public class Language  implements java.io.Serializable {


     private BigDecimal languageId;
     private String name;
     private String note;
     private Date convLastUpdate;
     private String sizeUnit;
     private BigDecimal isrelevant;

    public Language() {
    }

	
    public Language(BigDecimal languageId, String sizeUnit) {
        this.languageId = languageId;
        this.sizeUnit = sizeUnit;
    }
    public Language(BigDecimal languageId, String name, String note, Date convLastUpdate, String sizeUnit, BigDecimal isrelevant) {
       this.languageId = languageId;
       this.name = name;
       this.note = note;
       this.convLastUpdate = convLastUpdate;
       this.sizeUnit = sizeUnit;
       this.isrelevant = isrelevant;
    }
   
    public BigDecimal getLanguageId() {
        return this.languageId;
    }
    
    public void setLanguageId(BigDecimal languageId) {
        this.languageId = languageId;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
    public Date getConvLastUpdate() {
        return this.convLastUpdate;
    }
    
    public void setConvLastUpdate(Date convLastUpdate) {
        this.convLastUpdate = convLastUpdate;
    }
    public String getSizeUnit() {
        return this.sizeUnit;
    }
    
    public void setSizeUnit(String sizeUnit) {
        this.sizeUnit = sizeUnit;
    }
    public BigDecimal getIsrelevant() {
        return this.isrelevant;
    }
    
    public void setIsrelevant(BigDecimal isrelevant) {
        this.isrelevant = isrelevant;
    }




}


