package openones.oopms.projecteye.model;
// Generated Aug 9, 2012 10:39:08 PM by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.Date;

/**
 * OopmsExceptionalCost generated by hbm2java
 */
public class OopmsExceptionalCost  implements java.io.Serializable {


     private BigDecimal oopmsExceptionalCostId;
     private BigDecimal projectId;
     private String name;
     private BigDecimal type;
     private BigDecimal oopmsCostTypeId;
     private BigDecimal oopmsCostDailyExpenseId;
     private Date occurDate;
     private BigDecimal effectType;
     private BigDecimal effect;
     private String description;
     private String isPay;

    public OopmsExceptionalCost() {
    }

	
    public OopmsExceptionalCost(BigDecimal oopmsExceptionalCostId, BigDecimal projectId) {
        this.oopmsExceptionalCostId = oopmsExceptionalCostId;
        this.projectId = projectId;
    }
    public OopmsExceptionalCost(BigDecimal oopmsExceptionalCostId, BigDecimal projectId, String name, BigDecimal type, BigDecimal oopmsCostTypeId, BigDecimal oopmsCostDailyExpenseId, Date occurDate, BigDecimal effectType, BigDecimal effect, String description, String isPay) {
       this.oopmsExceptionalCostId = oopmsExceptionalCostId;
       this.projectId = projectId;
       this.name = name;
       this.type = type;
       this.oopmsCostTypeId = oopmsCostTypeId;
       this.oopmsCostDailyExpenseId = oopmsCostDailyExpenseId;
       this.occurDate = occurDate;
       this.effectType = effectType;
       this.effect = effect;
       this.description = description;
       this.isPay = isPay;
    }
   
    public BigDecimal getOopmsExceptionalCostId() {
        return this.oopmsExceptionalCostId;
    }
    
    public void setOopmsExceptionalCostId(BigDecimal oopmsExceptionalCostId) {
        this.oopmsExceptionalCostId = oopmsExceptionalCostId;
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
    public BigDecimal getType() {
        return this.type;
    }
    
    public void setType(BigDecimal type) {
        this.type = type;
    }
    public BigDecimal getOopmsCostTypeId() {
        return this.oopmsCostTypeId;
    }
    
    public void setOopmsCostTypeId(BigDecimal oopmsCostTypeId) {
        this.oopmsCostTypeId = oopmsCostTypeId;
    }
    public BigDecimal getOopmsCostDailyExpenseId() {
        return this.oopmsCostDailyExpenseId;
    }
    
    public void setOopmsCostDailyExpenseId(BigDecimal oopmsCostDailyExpenseId) {
        this.oopmsCostDailyExpenseId = oopmsCostDailyExpenseId;
    }
    public Date getOccurDate() {
        return this.occurDate;
    }
    
    public void setOccurDate(Date occurDate) {
        this.occurDate = occurDate;
    }
    public BigDecimal getEffectType() {
        return this.effectType;
    }
    
    public void setEffectType(BigDecimal effectType) {
        this.effectType = effectType;
    }
    public BigDecimal getEffect() {
        return this.effect;
    }
    
    public void setEffect(BigDecimal effect) {
        this.effect = effect;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }


	public String getIsPay() {
		return isPay;
	}


	public void setIsPay(String isPay) {
		this.isPay = isPay;
	}

    


}

