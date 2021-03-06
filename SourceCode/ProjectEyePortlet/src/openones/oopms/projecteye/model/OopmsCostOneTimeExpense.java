package openones.oopms.projecteye.model;
// Generated Aug 9, 2012 10:39:08 PM by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.Date;

/**
 * OopmsCostOneTimeExpense generated by hbm2java
 */
public class OopmsCostOneTimeExpense  implements java.io.Serializable {


     private BigDecimal oopmsCostOneTimeExpenseId;
     private BigDecimal projectId;
     private String name;
     private BigDecimal cost;
     private Date occurDate;
     private String description;
     private String isPay;

    public OopmsCostOneTimeExpense() {
    }

	
    public OopmsCostOneTimeExpense(BigDecimal oopmsCostOneTimeExpenseId, BigDecimal projectId) {
        this.oopmsCostOneTimeExpenseId = oopmsCostOneTimeExpenseId;
        this.projectId = projectId;
    }
    public OopmsCostOneTimeExpense(BigDecimal oopmsCostOneTimeExpenseId, BigDecimal projectId, String name, BigDecimal cost, Date occurDate, String description, String isPay) {
       this.oopmsCostOneTimeExpenseId = oopmsCostOneTimeExpenseId;
       this.projectId = projectId;
       this.name = name;
       this.cost = cost;
       this.occurDate = occurDate;
       this.description = description;
       this.isPay = isPay;
    }
   
    public BigDecimal getOopmsCostOneTimeExpenseId() {
        return this.oopmsCostOneTimeExpenseId;
    }
    
    public void setOopmsCostOneTimeExpenseId(BigDecimal oopmsCostOneTimeExpenseId) {
        this.oopmsCostOneTimeExpenseId = oopmsCostOneTimeExpenseId;
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
    public BigDecimal getCost() {
        return this.cost;
    }
    
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
    public Date getOccurDate() {
        return this.occurDate;
    }
    
    public void setOccurDate(Date occurDate) {
        this.occurDate = occurDate;
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


