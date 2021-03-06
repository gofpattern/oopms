package openones.oopms.entity;
// Generated 12:18:17 01-03-2012 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Risk generated by hbm2java
 */
public class Risk  implements java.io.Serializable {


     private BigDecimal riskId;
     private Developer developer;
     private Project project;
     private String condition;
     private String consequence;
     private BigDecimal prob;
     private BigDecimal impactTo;
     private BigDecimal unit;
     private BigDecimal estimatedImpact;
     private String mitigation;
     private String contigencyPlan;
     private String triggerName;
     private Date assessmentDate;
     private BigDecimal status;
     private String actualRiskScenarior;
     private String actualAction;
     private String actualImpact;
     private String plannedImpact;
     private BigDecimal unplanned;
     private BigDecimal exposure;
     private String developerAcc;
     private Short processId;
     private Boolean baselined;
     private BigDecimal sourceId;
     private BigDecimal type;
     private String threshold;
     private String mitigationBenefit;
     private String actualMitigation;
     private BigDecimal exposureNew;
     private Boolean priority;
     private Date lastUpdatedDate;
     private Byte riskPriority;
     private Byte impact;
     private Set riskMitigations = new HashSet(0);
     private Set riskContigencies = new HashSet(0);

    public Risk() {
    }

	
    public Risk(BigDecimal riskId) {
        this.riskId = riskId;
    }
    public Risk(BigDecimal riskId, Developer developer, Project project, String condition, String consequence, BigDecimal prob, BigDecimal impactTo, BigDecimal unit, BigDecimal estimatedImpact, String mitigation, String contigencyPlan, String triggerName, Date assessmentDate, BigDecimal status, String actualRiskScenarior, String actualAction, String actualImpact, String plannedImpact, BigDecimal unplanned, BigDecimal exposure, String developerAcc, Short processId, Boolean baselined, BigDecimal sourceId, BigDecimal type, String threshold, String mitigationBenefit, String actualMitigation, BigDecimal exposureNew, Boolean priority, Date lastUpdatedDate, Byte riskPriority, Byte impact, Set riskMitigations, Set riskContigencies) {
       this.riskId = riskId;
       this.developer = developer;
       this.project = project;
       this.condition = condition;
       this.consequence = consequence;
       this.prob = prob;
       this.impactTo = impactTo;
       this.unit = unit;
       this.estimatedImpact = estimatedImpact;
       this.mitigation = mitigation;
       this.contigencyPlan = contigencyPlan;
       this.triggerName = triggerName;
       this.assessmentDate = assessmentDate;
       this.status = status;
       this.actualRiskScenarior = actualRiskScenarior;
       this.actualAction = actualAction;
       this.actualImpact = actualImpact;
       this.plannedImpact = plannedImpact;
       this.unplanned = unplanned;
       this.exposure = exposure;
       this.developerAcc = developerAcc;
       this.processId = processId;
       this.baselined = baselined;
       this.sourceId = sourceId;
       this.type = type;
       this.threshold = threshold;
       this.mitigationBenefit = mitigationBenefit;
       this.actualMitigation = actualMitigation;
       this.exposureNew = exposureNew;
       this.priority = priority;
       this.lastUpdatedDate = lastUpdatedDate;
       this.riskPriority = riskPriority;
       this.impact = impact;
       this.riskMitigations = riskMitigations;
       this.riskContigencies = riskContigencies;
    }
   
    public BigDecimal getRiskId() {
        return this.riskId;
    }
    
    public void setRiskId(BigDecimal riskId) {
        this.riskId = riskId;
    }
    public Developer getDeveloper() {
        return this.developer;
    }
    
    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }
    public Project getProject() {
        return this.project;
    }
    
    public void setProject(Project project) {
        this.project = project;
    }
    public String getCondition() {
        return this.condition;
    }
    
    public void setCondition(String condition) {
        this.condition = condition;
    }
    public String getConsequence() {
        return this.consequence;
    }
    
    public void setConsequence(String consequence) {
        this.consequence = consequence;
    }
    public BigDecimal getProb() {
        return this.prob;
    }
    
    public void setProb(BigDecimal prob) {
        this.prob = prob;
    }
    public BigDecimal getImpactTo() {
        return this.impactTo;
    }
    
    public void setImpactTo(BigDecimal impactTo) {
        this.impactTo = impactTo;
    }
    public BigDecimal getUnit() {
        return this.unit;
    }
    
    public void setUnit(BigDecimal unit) {
        this.unit = unit;
    }
    public BigDecimal getEstimatedImpact() {
        return this.estimatedImpact;
    }
    
    public void setEstimatedImpact(BigDecimal estimatedImpact) {
        this.estimatedImpact = estimatedImpact;
    }
    public String getMitigation() {
        return this.mitigation;
    }
    
    public void setMitigation(String mitigation) {
        this.mitigation = mitigation;
    }
    public String getContigencyPlan() {
        return this.contigencyPlan;
    }
    
    public void setContigencyPlan(String contigencyPlan) {
        this.contigencyPlan = contigencyPlan;
    }
    public String getTriggerName() {
        return this.triggerName;
    }
    
    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }
    public Date getAssessmentDate() {
        return this.assessmentDate;
    }
    
    public void setAssessmentDate(Date assessmentDate) {
        this.assessmentDate = assessmentDate;
    }
    public BigDecimal getStatus() {
        return this.status;
    }
    
    public void setStatus(BigDecimal status) {
        this.status = status;
    }
    public String getActualRiskScenarior() {
        return this.actualRiskScenarior;
    }
    
    public void setActualRiskScenarior(String actualRiskScenarior) {
        this.actualRiskScenarior = actualRiskScenarior;
    }
    public String getActualAction() {
        return this.actualAction;
    }
    
    public void setActualAction(String actualAction) {
        this.actualAction = actualAction;
    }
    public String getActualImpact() {
        return this.actualImpact;
    }
    
    public void setActualImpact(String actualImpact) {
        this.actualImpact = actualImpact;
    }
    public String getPlannedImpact() {
        return this.plannedImpact;
    }
    
    public void setPlannedImpact(String plannedImpact) {
        this.plannedImpact = plannedImpact;
    }
    public BigDecimal getUnplanned() {
        return this.unplanned;
    }
    
    public void setUnplanned(BigDecimal unplanned) {
        this.unplanned = unplanned;
    }
    public BigDecimal getExposure() {
        return this.exposure;
    }
    
    public void setExposure(BigDecimal exposure) {
        this.exposure = exposure;
    }
    public String getDeveloperAcc() {
        return this.developerAcc;
    }
    
    public void setDeveloperAcc(String developerAcc) {
        this.developerAcc = developerAcc;
    }
    public Short getProcessId() {
        return this.processId;
    }
    
    public void setProcessId(Short processId) {
        this.processId = processId;
    }
    public Boolean getBaselined() {
        return this.baselined;
    }
    
    public void setBaselined(Boolean baselined) {
        this.baselined = baselined;
    }
    public BigDecimal getSourceId() {
        return this.sourceId;
    }
    
    public void setSourceId(BigDecimal sourceId) {
        this.sourceId = sourceId;
    }
    public BigDecimal getType() {
        return this.type;
    }
    
    public void setType(BigDecimal type) {
        this.type = type;
    }
    public String getThreshold() {
        return this.threshold;
    }
    
    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }
    public String getMitigationBenefit() {
        return this.mitigationBenefit;
    }
    
    public void setMitigationBenefit(String mitigationBenefit) {
        this.mitigationBenefit = mitigationBenefit;
    }
    public String getActualMitigation() {
        return this.actualMitigation;
    }
    
    public void setActualMitigation(String actualMitigation) {
        this.actualMitigation = actualMitigation;
    }
    public BigDecimal getExposureNew() {
        return this.exposureNew;
    }
    
    public void setExposureNew(BigDecimal exposureNew) {
        this.exposureNew = exposureNew;
    }
    public Boolean getPriority() {
        return this.priority;
    }
    
    public void setPriority(Boolean priority) {
        this.priority = priority;
    }
    public Date getLastUpdatedDate() {
        return this.lastUpdatedDate;
    }
    
    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }
    public Byte getRiskPriority() {
        return this.riskPriority;
    }
    
    public void setRiskPriority(Byte riskPriority) {
        this.riskPriority = riskPriority;
    }
    public Byte getImpact() {
        return this.impact;
    }
    
    public void setImpact(Byte impact) {
        this.impact = impact;
    }
    public Set getRiskMitigations() {
        return this.riskMitigations;
    }
    
    public void setRiskMitigations(Set riskMitigations) {
        this.riskMitigations = riskMitigations;
    }
    public Set getRiskContigencies() {
        return this.riskContigencies;
    }
    
    public void setRiskContigencies(Set riskContigencies) {
        this.riskContigencies = riskContigencies;
    }




}


