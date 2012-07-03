package openones.oopms.entity;
// Generated 12:18:17 01-03-2012 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.sql.Clob;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Pcb generated by hbm2java
 */
public class Pcb  implements java.io.Serializable {


     private BigDecimal pcbId;
     private Developer developer;
     private Workunit workunit;
     private Date fromdate;
     private Date todate;
     private Date reportDate;
     private String reportname;
     private Boolean reporttype;
     private Clob methodology;
     private Clob generalcomm;
     private Clob lastproblemreview;
     private Clob lastsuggreview;
     private Clob problems;
     private Clob suggestions;
     private String period;
     private BigDecimal year;
     private Set projects = new HashSet(0);
     private Set metricgroups = new HashSet(0);

    public Pcb() {
    }

	
    public Pcb(BigDecimal pcbId, Developer developer, Workunit workunit, Date fromdate, Date todate, Date reportDate, String period, BigDecimal year) {
        this.pcbId = pcbId;
        this.developer = developer;
        this.workunit = workunit;
        this.fromdate = fromdate;
        this.todate = todate;
        this.reportDate = reportDate;
        this.period = period;
        this.year = year;
    }
    public Pcb(BigDecimal pcbId, Developer developer, Workunit workunit, Date fromdate, Date todate, Date reportDate, String reportname, Boolean reporttype, Clob methodology, Clob generalcomm, Clob lastproblemreview, Clob lastsuggreview, Clob problems, Clob suggestions, String period, BigDecimal year, Set projects, Set metricgroups) {
       this.pcbId = pcbId;
       this.developer = developer;
       this.workunit = workunit;
       this.fromdate = fromdate;
       this.todate = todate;
       this.reportDate = reportDate;
       this.reportname = reportname;
       this.reporttype = reporttype;
       this.methodology = methodology;
       this.generalcomm = generalcomm;
       this.lastproblemreview = lastproblemreview;
       this.lastsuggreview = lastsuggreview;
       this.problems = problems;
       this.suggestions = suggestions;
       this.period = period;
       this.year = year;
       this.projects = projects;
       this.metricgroups = metricgroups;
    }
   
    public BigDecimal getPcbId() {
        return this.pcbId;
    }
    
    public void setPcbId(BigDecimal pcbId) {
        this.pcbId = pcbId;
    }
    public Developer getDeveloper() {
        return this.developer;
    }
    
    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }
    public Workunit getWorkunit() {
        return this.workunit;
    }
    
    public void setWorkunit(Workunit workunit) {
        this.workunit = workunit;
    }
    public Date getFromdate() {
        return this.fromdate;
    }
    
    public void setFromdate(Date fromdate) {
        this.fromdate = fromdate;
    }
    public Date getTodate() {
        return this.todate;
    }
    
    public void setTodate(Date todate) {
        this.todate = todate;
    }
    public Date getReportDate() {
        return this.reportDate;
    }
    
    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }
    public String getReportname() {
        return this.reportname;
    }
    
    public void setReportname(String reportname) {
        this.reportname = reportname;
    }
    public Boolean getReporttype() {
        return this.reporttype;
    }
    
    public void setReporttype(Boolean reporttype) {
        this.reporttype = reporttype;
    }
    public Clob getMethodology() {
        return this.methodology;
    }
    
    public void setMethodology(Clob methodology) {
        this.methodology = methodology;
    }
    public Clob getGeneralcomm() {
        return this.generalcomm;
    }
    
    public void setGeneralcomm(Clob generalcomm) {
        this.generalcomm = generalcomm;
    }
    public Clob getLastproblemreview() {
        return this.lastproblemreview;
    }
    
    public void setLastproblemreview(Clob lastproblemreview) {
        this.lastproblemreview = lastproblemreview;
    }
    public Clob getLastsuggreview() {
        return this.lastsuggreview;
    }
    
    public void setLastsuggreview(Clob lastsuggreview) {
        this.lastsuggreview = lastsuggreview;
    }
    public Clob getProblems() {
        return this.problems;
    }
    
    public void setProblems(Clob problems) {
        this.problems = problems;
    }
    public Clob getSuggestions() {
        return this.suggestions;
    }
    
    public void setSuggestions(Clob suggestions) {
        this.suggestions = suggestions;
    }
    public String getPeriod() {
        return this.period;
    }
    
    public void setPeriod(String period) {
        this.period = period;
    }
    public BigDecimal getYear() {
        return this.year;
    }
    
    public void setYear(BigDecimal year) {
        this.year = year;
    }
    public Set getProjects() {
        return this.projects;
    }
    
    public void setProjects(Set projects) {
        this.projects = projects;
    }
    public Set getMetricgroups() {
        return this.metricgroups;
    }
    
    public void setMetricgroups(Set metricgroups) {
        this.metricgroups = metricgroups;
    }




}


