package openones.oopms.projecteye.model;
// Generated 12:18:17 01-03-2012 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.Date;

/**
 * RiskMitigation generated by hbm2java
 */
public class RiskMitigation  implements java.io.Serializable {


     private BigDecimal riskMitigationId;
     private Risk risk;
     private String mitigation;
     private BigDecimal mitigationCost;
     private String mitigationBenefit;
     private String developerAcc;
     private Date planEndDate;
     private Date actualEndDate;
     private BigDecimal actionStatus;

    public RiskMitigation() {
    }

	
    public RiskMitigation(BigDecimal riskMitigationId) {
        this.riskMitigationId = riskMitigationId;
    }
    public RiskMitigation(BigDecimal riskMitigationId, Risk risk, String mitigation, BigDecimal mitigationCost, String mitigationBenefit, String developerAcc, Date planEndDate, Date actualEndDate, BigDecimal actionStatus) {
       this.riskMitigationId = riskMitigationId;
       this.risk = risk;
       this.mitigation = mitigation;
       this.mitigationCost = mitigationCost;
       this.mitigationBenefit = mitigationBenefit;
       this.developerAcc = developerAcc;
       this.planEndDate = planEndDate;
       this.actualEndDate = actualEndDate;
       this.actionStatus = actionStatus;
    }
   
    public BigDecimal getRiskMitigationId() {
        return this.riskMitigationId;
    }
    
    public void setRiskMitigationId(BigDecimal riskMitigationId) {
        this.riskMitigationId = riskMitigationId;
    }
    public Risk getRisk() {
        return this.risk;
    }
    
    public void setRisk(Risk risk) {
        this.risk = risk;
    }
    public String getMitigation() {
        return this.mitigation;
    }
    
    public void setMitigation(String mitigation) {
        this.mitigation = mitigation;
    }
    public BigDecimal getMitigationCost() {
        return this.mitigationCost;
    }
    
    public void setMitigationCost(BigDecimal mitigationCost) {
        this.mitigationCost = mitigationCost;
    }
    public String getMitigationBenefit() {
        return this.mitigationBenefit;
    }
    
    public void setMitigationBenefit(String mitigationBenefit) {
        this.mitigationBenefit = mitigationBenefit;
    }
    public String getDeveloperAcc() {
        return this.developerAcc;
    }
    
    public void setDeveloperAcc(String developerAcc) {
        this.developerAcc = developerAcc;
    }
    public Date getPlanEndDate() {
        return this.planEndDate;
    }
    
    public void setPlanEndDate(Date planEndDate) {
        this.planEndDate = planEndDate;
    }
    public Date getActualEndDate() {
        return this.actualEndDate;
    }
    
    public void setActualEndDate(Date actualEndDate) {
        this.actualEndDate = actualEndDate;
    }
    public BigDecimal getActionStatus() {
        return this.actionStatus;
    }
    
    public void setActionStatus(BigDecimal actionStatus) {
        this.actionStatus = actionStatus;
    }




}


