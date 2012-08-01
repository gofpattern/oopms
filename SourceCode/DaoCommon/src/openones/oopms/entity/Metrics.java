package openones.oopms.entity;
// Generated 12:18:17 01-03-2012 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.Date;

/**
 * Metrics generated by hbm2java
 */
public class Metrics  implements java.io.Serializable {


     private long code;
     private String projectCode;
     private BigDecimal plannedValue;
     private BigDecimal actualValue;
     private BigDecimal metricIndex;
     private Date reportDate;
     private String note;
     private String causal;
     private BigDecimal usl;
     private BigDecimal lsl;

    public Metrics() {
    }

	
    public Metrics(long code, BigDecimal metricIndex) {
        this.code = code;
        this.metricIndex = metricIndex;
    }
    public Metrics(long code, String projectCode, BigDecimal plannedValue, BigDecimal actualValue, BigDecimal metricIndex, Date reportDate, String note, String causal, BigDecimal usl, BigDecimal lsl) {
       this.code = code;
       this.projectCode = projectCode;
       this.plannedValue = plannedValue;
       this.actualValue = actualValue;
       this.metricIndex = metricIndex;
       this.reportDate = reportDate;
       this.note = note;
       this.causal = causal;
       this.usl = usl;
       this.lsl = lsl;
    }
   
    public long getCode() {
        return this.code;
    }
    
    public void setCode(long code) {
        this.code = code;
    }
    public String getProjectCode() {
        return this.projectCode;
    }
    
    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
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
    public BigDecimal getMetricIndex() {
        return this.metricIndex;
    }
    
    public void setMetricIndex(BigDecimal metricIndex) {
        this.metricIndex = metricIndex;
    }
    public Date getReportDate() {
        return this.reportDate;
    }
    
    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }
    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
    public String getCausal() {
        return this.causal;
    }
    
    public void setCausal(String causal) {
        this.causal = causal;
    }
    public BigDecimal getUsl() {
        return this.usl;
    }
    
    public void setUsl(BigDecimal usl) {
        this.usl = usl;
    }
    public BigDecimal getLsl() {
        return this.lsl;
    }
    
    public void setLsl(BigDecimal lsl) {
        this.lsl = lsl;
    }




}

