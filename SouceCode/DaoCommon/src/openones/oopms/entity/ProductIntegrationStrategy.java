package openones.oopms.entity;
// Generated 12:18:17 01-03-2012 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;

/**
 * ProductIntegrationStrategy generated by hbm2java
 */
public class ProductIntegrationStrategy  implements java.io.Serializable {


     private BigDecimal integrId;
     private BigDecimal integrPrjId;
     private String integrCompId;
     private String integrDescription;
     private String integrWithComponents;
     private String integrOrder;
     private String integrReadyNeed;

    public ProductIntegrationStrategy() {
    }

	
    public ProductIntegrationStrategy(BigDecimal integrId, BigDecimal integrPrjId, String integrCompId) {
        this.integrId = integrId;
        this.integrPrjId = integrPrjId;
        this.integrCompId = integrCompId;
    }
    public ProductIntegrationStrategy(BigDecimal integrId, BigDecimal integrPrjId, String integrCompId, String integrDescription, String integrWithComponents, String integrOrder, String integrReadyNeed) {
       this.integrId = integrId;
       this.integrPrjId = integrPrjId;
       this.integrCompId = integrCompId;
       this.integrDescription = integrDescription;
       this.integrWithComponents = integrWithComponents;
       this.integrOrder = integrOrder;
       this.integrReadyNeed = integrReadyNeed;
    }
   
    public BigDecimal getIntegrId() {
        return this.integrId;
    }
    
    public void setIntegrId(BigDecimal integrId) {
        this.integrId = integrId;
    }
    public BigDecimal getIntegrPrjId() {
        return this.integrPrjId;
    }
    
    public void setIntegrPrjId(BigDecimal integrPrjId) {
        this.integrPrjId = integrPrjId;
    }
    public String getIntegrCompId() {
        return this.integrCompId;
    }
    
    public void setIntegrCompId(String integrCompId) {
        this.integrCompId = integrCompId;
    }
    public String getIntegrDescription() {
        return this.integrDescription;
    }
    
    public void setIntegrDescription(String integrDescription) {
        this.integrDescription = integrDescription;
    }
    public String getIntegrWithComponents() {
        return this.integrWithComponents;
    }
    
    public void setIntegrWithComponents(String integrWithComponents) {
        this.integrWithComponents = integrWithComponents;
    }
    public String getIntegrOrder() {
        return this.integrOrder;
    }
    
    public void setIntegrOrder(String integrOrder) {
        this.integrOrder = integrOrder;
    }
    public String getIntegrReadyNeed() {
        return this.integrReadyNeed;
    }
    
    public void setIntegrReadyNeed(String integrReadyNeed) {
        this.integrReadyNeed = integrReadyNeed;
    }




}


