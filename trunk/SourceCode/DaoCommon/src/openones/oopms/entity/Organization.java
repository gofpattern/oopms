package openones.oopms.entity;
// Generated 12:18:17 01-03-2012 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;

/**
 * Organization generated by hbm2java
 */
public class Organization  implements java.io.Serializable {


     private BigDecimal orgId;
     private String orgname;

    public Organization() {
    }

    public Organization(BigDecimal orgId, String orgname) {
       this.orgId = orgId;
       this.orgname = orgname;
    }
   
    public BigDecimal getOrgId() {
        return this.orgId;
    }
    
    public void setOrgId(BigDecimal orgId) {
        this.orgId = orgId;
    }
    public String getOrgname() {
        return this.orgname;
    }
    
    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }




}

