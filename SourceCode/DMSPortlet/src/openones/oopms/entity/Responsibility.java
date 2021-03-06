package openones.oopms.entity;
// Generated 12:18:17 01-03-2012 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Responsibility generated by hbm2java
 */
public class Responsibility  implements java.io.Serializable {


     private String projectPositionCode;
     private String name;
     private Byte orderNumber;
     private String rightgroup;
     private Byte priority;
     private BigDecimal responsibilityId;
     private Byte disabled;
     private BigDecimal isdrop;
     private Set assignments = new HashSet(0);

    public Responsibility() {
    }

	
    public Responsibility(String projectPositionCode, String name) {
        this.projectPositionCode = projectPositionCode;
        this.name = name;
    }
    public Responsibility(String projectPositionCode, String name, Byte orderNumber, String rightgroup, Byte priority, BigDecimal responsibilityId, Byte disabled, BigDecimal isdrop, Set assignments) {
       this.projectPositionCode = projectPositionCode;
       this.name = name;
       this.orderNumber = orderNumber;
       this.rightgroup = rightgroup;
       this.priority = priority;
       this.responsibilityId = responsibilityId;
       this.disabled = disabled;
       this.isdrop = isdrop;
       this.assignments = assignments;
    }
   
    public String getProjectPositionCode() {
        return this.projectPositionCode;
    }
    
    public void setProjectPositionCode(String projectPositionCode) {
        this.projectPositionCode = projectPositionCode;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Byte getOrderNumber() {
        return this.orderNumber;
    }
    
    public void setOrderNumber(Byte orderNumber) {
        this.orderNumber = orderNumber;
    }
    public String getRightgroup() {
        return this.rightgroup;
    }
    
    public void setRightgroup(String rightgroup) {
        this.rightgroup = rightgroup;
    }
    public Byte getPriority() {
        return this.priority;
    }
    
    public void setPriority(Byte priority) {
        this.priority = priority;
    }
    public BigDecimal getResponsibilityId() {
        return this.responsibilityId;
    }
    
    public void setResponsibilityId(BigDecimal responsibilityId) {
        this.responsibilityId = responsibilityId;
    }
    public Byte getDisabled() {
        return this.disabled;
    }
    
    public void setDisabled(Byte disabled) {
        this.disabled = disabled;
    }
    public BigDecimal getIsdrop() {
        return this.isdrop;
    }
    
    public void setIsdrop(BigDecimal isdrop) {
        this.isdrop = isdrop;
    }
    public Set getAssignments() {
        return this.assignments;
    }
    
    public void setAssignments(Set assignments) {
        this.assignments = assignments;
    }




}


