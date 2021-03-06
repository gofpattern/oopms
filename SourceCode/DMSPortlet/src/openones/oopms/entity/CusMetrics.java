package openones.oopms.entity;
// Generated 12:18:17 01-03-2012 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * CusMetrics generated by hbm2java
 */
public class CusMetrics  implements java.io.Serializable {


     private BigDecimal code;
     private String name;
     private String unit;
     private BigDecimal plannedValue;
     private BigDecimal actualValue;
     private String note;
     private Long projectId;
     private BigDecimal lcl;
     private BigDecimal ucl;
     private String causal;
     private Set cusPoints = new HashSet(0);

    public CusMetrics() {
    }

	
    public CusMetrics(BigDecimal code) {
        this.code = code;
    }
    public CusMetrics(BigDecimal code, String name, String unit, BigDecimal plannedValue, BigDecimal actualValue, String note, Long projectId, BigDecimal lcl, BigDecimal ucl, String causal, Set cusPoints) {
       this.code = code;
       this.name = name;
       this.unit = unit;
       this.plannedValue = plannedValue;
       this.actualValue = actualValue;
       this.note = note;
       this.projectId = projectId;
       this.lcl = lcl;
       this.ucl = ucl;
       this.causal = causal;
       this.cusPoints = cusPoints;
    }
   
    public BigDecimal getCode() {
        return this.code;
    }
    
    public void setCode(BigDecimal code) {
        this.code = code;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getUnit() {
        return this.unit;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public BigDecimal getPlannedValue() {
        return this.plannedValue;
    }
    
    public void setPlannedValue(BigDecimal plannedValue) {
        this.plannedValue = plannedValue;
    }
    public BigDecimal getActualValue() {
        return this.actualValue;
    }
    
    public void setActualValue(BigDecimal actualValue) {
        this.actualValue = actualValue;
    }
    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
    public Long getProjectId() {
        return this.projectId;
    }
    
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
    public BigDecimal getLcl() {
        return this.lcl;
    }
    
    public void setLcl(BigDecimal lcl) {
        this.lcl = lcl;
    }
    public BigDecimal getUcl() {
        return this.ucl;
    }
    
    public void setUcl(BigDecimal ucl) {
        this.ucl = ucl;
    }
    public String getCausal() {
        return this.causal;
    }
    
    public void setCausal(String causal) {
        this.causal = causal;
    }
    public Set getCusPoints() {
        return this.cusPoints;
    }
    
    public void setCusPoints(Set cusPoints) {
        this.cusPoints = cusPoints;
    }




}


