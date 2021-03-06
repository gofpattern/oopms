package openones.oopms.entity;
// Generated Aug 15, 2012 9:48:26 PM by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;

/**
 * OopmsProjectCost generated by hbm2java
 */
public class OopmsProjectCost  implements java.io.Serializable {


     private BigDecimal oopmsProjectCostId;
     private BigDecimal projectId;
     private BigDecimal currentBudget;
     private BigDecimal currentExpense;
     private String costStatus;

    public OopmsProjectCost() {
    }

	
    public OopmsProjectCost(BigDecimal oopmsProjectCostId, BigDecimal projectId) {
        this.oopmsProjectCostId = oopmsProjectCostId;
        this.projectId = projectId;
    }
    public OopmsProjectCost(BigDecimal oopmsProjectCostId, BigDecimal projectId, BigDecimal currentBudget, BigDecimal currentExpense, String costStatus) {
       this.oopmsProjectCostId = oopmsProjectCostId;
       this.projectId = projectId;
       this.currentBudget = currentBudget;
       this.currentExpense = currentExpense;
       this.costStatus = costStatus;
    }
   
    public BigDecimal getOopmsProjectCostId() {
        return this.oopmsProjectCostId;
    }
    
    public void setOopmsProjectCostId(BigDecimal oopmsProjectCostId) {
        this.oopmsProjectCostId = oopmsProjectCostId;
    }
    public BigDecimal getProjectId() {
        return this.projectId;
    }
    
    public void setProjectId(BigDecimal projectId) {
        this.projectId = projectId;
    }
    public BigDecimal getCurrentBudget() {
        return this.currentBudget;
    }
    
    public void setCurrentBudget(BigDecimal currentBudget) {
        this.currentBudget = currentBudget;
    }
    public BigDecimal getCurrentExpense() {
        return this.currentExpense;
    }
    
    public void setCurrentExpense(BigDecimal currentExpense) {
        this.currentExpense = currentExpense;
    }
    public String getCostStatus() {
        return this.costStatus;
    }
    
    public void setCostStatus(String costStatus) {
        this.costStatus = costStatus;
    }




}


