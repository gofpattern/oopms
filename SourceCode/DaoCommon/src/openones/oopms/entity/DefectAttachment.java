package openones.oopms.entity;
// Generated 12:18:17 01-03-2012 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.sql.Blob;

/**
 * DefectAttachment generated by hbm2java
 */
public class DefectAttachment  implements java.io.Serializable {


     private BigDecimal attachmentId;
     private Defect defect;
     private Developer developer;
     private String name;
     private Blob content;
     private Integer contentSize;
     private Boolean converted;

    public DefectAttachment() {
    }

	
    public DefectAttachment(BigDecimal attachmentId, String name) {
        this.attachmentId = attachmentId;
        this.name = name;
    }
    public DefectAttachment(BigDecimal attachmentId, Defect defect, Developer developer, String name, Blob content, Integer contentSize, Boolean converted) {
       this.attachmentId = attachmentId;
       this.defect = defect;
       this.developer = developer;
       this.name = name;
       this.content = content;
       this.contentSize = contentSize;
       this.converted = converted;
    }
   
    public BigDecimal getAttachmentId() {
        return this.attachmentId;
    }
    
    public void setAttachmentId(BigDecimal attachmentId) {
        this.attachmentId = attachmentId;
    }
    public Defect getDefect() {
        return this.defect;
    }
    
    public void setDefect(Defect defect) {
        this.defect = defect;
    }
    public Developer getDeveloper() {
        return this.developer;
    }
    
    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Blob getContent() {
        return this.content;
    }
    
    public void setContent(Blob content) {
        this.content = content;
    }
    public Integer getContentSize() {
        return this.contentSize;
    }
    
    public void setContentSize(Integer contentSize) {
        this.contentSize = contentSize;
    }
    public Boolean getConverted() {
        return this.converted;
    }
    
    public void setConverted(Boolean converted) {
        this.converted = converted;
    }




}


