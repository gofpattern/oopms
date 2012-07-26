package openones.oopms.entity;
// Generated 12:18:17 01-03-2012 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;

/**
 * CusPoint generated by hbm2java
 */
public class CusPoint  implements java.io.Serializable {


     private BigDecimal cusPointId;
     private Milestone milestone;
     private CusMetrics cusMetrics;
     private BigDecimal point;
     private BigDecimal actualValue;

    public CusPoint() {
    }

	
    public CusPoint(BigDecimal cusPointId, Milestone milestone, CusMetrics cusMetrics) {
        this.cusPointId = cusPointId;
        this.milestone = milestone;
        this.cusMetrics = cusMetrics;
    }
    public CusPoint(BigDecimal cusPointId, Milestone milestone, CusMetrics cusMetrics, BigDecimal point, BigDecimal actualValue) {
       this.cusPointId = cusPointId;
       this.milestone = milestone;
       this.cusMetrics = cusMetrics;
       this.point = point;
       this.actualValue = actualValue;
    }
   
    public BigDecimal getCusPointId() {
        return this.cusPointId;
    }
    
    public void setCusPointId(BigDecimal cusPointId) {
        this.cusPointId = cusPointId;
    }
    public Milestone getMilestone() {
        return this.milestone;
    }
    
    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }
    public CusMetrics getCusMetrics() {
        return this.cusMetrics;
    }
    
    public void setCusMetrics(CusMetrics cusMetrics) {
        this.cusMetrics = cusMetrics;
    }
    public BigDecimal getPoint() {
        return this.point;
    }
    
    public void setPoint(BigDecimal point) {
        this.point = point;
    }
    public BigDecimal getActualValue() {
        return this.actualValue;
    }
    
    public void setActualValue(BigDecimal actualValue) {
        this.actualValue = actualValue;
    }




}

