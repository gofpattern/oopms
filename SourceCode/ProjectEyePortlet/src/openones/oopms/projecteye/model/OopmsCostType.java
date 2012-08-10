package openones.oopms.projecteye.model;
// Generated Aug 9, 2012 10:39:08 PM by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;

/**
 * OopmsCostType generated by hbm2java
 */
public class OopmsCostType  implements java.io.Serializable {


     private BigDecimal oopmsCostTypeId;
     private BigDecimal projectId;
     private String name;
     private String description;

    public OopmsCostType() {
    }

	
    public OopmsCostType(BigDecimal oopmsCostTypeId, BigDecimal projectId) {
        this.oopmsCostTypeId = oopmsCostTypeId;
        this.projectId = projectId;
    }
    public OopmsCostType(BigDecimal oopmsCostTypeId, BigDecimal projectId, String name, String description) {
       this.oopmsCostTypeId = oopmsCostTypeId;
       this.projectId = projectId;
       this.name = name;
       this.description = description;
    }
   
    public BigDecimal getOopmsCostTypeId() {
        return this.oopmsCostTypeId;
    }
    
    public void setOopmsCostTypeId(BigDecimal oopmsCostTypeId) {
        this.oopmsCostTypeId = oopmsCostTypeId;
    }
    public BigDecimal getProjectId() {
        return this.projectId;
    }
    
    public void setProjectId(BigDecimal projectId) {
        this.projectId = projectId;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }




}


