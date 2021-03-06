package openones.oopms.planner.model;
// Generated Jul 29, 2012 4:42:23 PM by Hibernate Tools 3.2.1.GA

import java.math.BigDecimal;
import java.util.Date;

/**
 * Tasks generated by hbm2java
 */
public class Tasks implements java.io.Serializable {

    private BigDecimal taskid;
    private Workunit workunit;
    private String description;
    private BigDecimal assignedto;
    private BigDecimal effort;
    private Date planDate;
    private Date actualDate;
    private boolean status;
    private byte type;
    private String note;
    private BigDecimal process;
    private Date replanDate;
    private Boolean feasible;
    private String code;
    private String taskname;
    private BigDecimal stageid;
    private BigDecimal product;
    private BigDecimal productsize;
    private BigDecimal completedsize;
    private Date startdate;
    private BigDecimal plannedeffort;
    private BigDecimal currenteffort;
    private BigDecimal projectid;
    private BigDecimal statusid;
    private BigDecimal sizeunit;
    private Module module;
    private boolean active;

    private String stage_str;
    private String process_str;
    private String developer_str;
    private String project_str;
    private String startdate_str;
    private String planDate_str;
    private String actualDate_str;
    private Boolean visible;// support search function

    public Tasks() {
        visible = true;
        active = true;
        plannedeffort = new BigDecimal(0);
        currenteffort = new BigDecimal(0);
        productsize = new BigDecimal(0);
        completedsize = new BigDecimal(0);
       
    }

    public Tasks(BigDecimal taskid, String description, BigDecimal assignedto, Date planDate, boolean status,
            BigDecimal process, String taskname, BigDecimal stageid, BigDecimal projectid, BigDecimal statusid) {
        this.taskid = taskid;
        this.description = description;
        this.assignedto = assignedto;
        this.planDate = planDate;
        this.status = status;
        this.process = process;
        this.taskname = taskname;
        this.stageid = stageid;
        this.projectid = projectid;
        this.statusid = statusid;
    }
    public Tasks(BigDecimal taskid, Workunit workunit, Module module, String description, BigDecimal assignedto,
            BigDecimal effort, Date planDate, Date actualDate, boolean status, Byte type, String note,
            BigDecimal process, Date replanDate, Boolean feasible, String code, String taskname, BigDecimal stageid,
            BigDecimal product, BigDecimal productsize, BigDecimal completedsize, Date startdate,
            BigDecimal plannedeffort, BigDecimal currenteffort, BigDecimal projectid, BigDecimal statusid,
            Boolean active) {
        this.taskid = taskid;
        this.workunit = workunit;
        this.module = module;
        this.description = description;
        this.assignedto = assignedto;
        this.effort = effort;
        this.planDate = planDate;
        this.actualDate = actualDate;
        this.status = status;
        this.type = type;
        this.note = note;
        this.process = process;
        this.replanDate = replanDate;
        this.feasible = feasible;
        this.code = code;
        this.taskname = taskname;
        this.stageid = stageid;
        this.product = product;
        this.productsize = productsize;
        this.completedsize = completedsize;
        this.startdate = startdate;
        this.plannedeffort = plannedeffort;
        this.currenteffort = currenteffort;
        this.projectid = projectid;
        this.statusid = statusid;
        this.active = active;
    }

    public BigDecimal getTaskid() {
        return this.taskid;
    }

    public void setTaskid(BigDecimal taskid) {
        this.taskid = taskid;
    }
    public Workunit getWorkunit() {
        return this.workunit;
    }

    public void setWorkunit(Workunit workunit) {
        this.workunit = workunit;
    }
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public BigDecimal getAssignedto() {
        return this.assignedto;
    }

    public void setAssignedto(BigDecimal assignedto) {
        this.assignedto = assignedto;
    }
    public BigDecimal getEffort() {
        return this.effort;
    }

    public void setEffort(BigDecimal effort) {
        this.effort = effort;
    }
    public Date getPlanDate() {
        return this.planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }
    public Date getActualDate() {
        return this.actualDate;
    }

    public void setActualDate(Date actualDate) {
        this.actualDate = actualDate;
    }
    public boolean isStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public byte getType() {
        return this.type;
    }

    public void setType(byte type) {
        this.type = type;
    }
    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    public BigDecimal getProcess() {
        return this.process;
    }

    public void setProcess(BigDecimal process) {
        this.process = process;
    }
    public Date getReplanDate() {
        return this.replanDate;
    }

    public void setReplanDate(Date replanDate) {
        this.replanDate = replanDate;
    }
    public Boolean getFeasible() {
        return this.feasible;
    }

    public void setFeasible(Boolean feasible) {
        this.feasible = feasible;
    }
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getTaskname() {
        return this.taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }
    public BigDecimal getStageid() {
        return this.stageid;
    }

    public void setStageid(BigDecimal stageid) {
        this.stageid = stageid;
    }
    public BigDecimal getProduct() {
        return this.product;
    }

    public void setProduct(BigDecimal product) {
        this.product = product;
    }
    public BigDecimal getProductsize() {
        return this.productsize;
    }

    public void setProductsize(BigDecimal productsize) {
        this.productsize = productsize;
    }
    public BigDecimal getCompletedsize() {
        return this.completedsize;
    }

    public void setCompletedsize(BigDecimal completedsize) {
        this.completedsize = completedsize;
    }
    public Date getStartdate() {
        return this.startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }
    public BigDecimal getPlannedeffort() {
        return this.plannedeffort;
    }

    public void setPlannedeffort(BigDecimal plannedeffort) {
        this.plannedeffort = plannedeffort;
    }
    public BigDecimal getCurrenteffort() {
        return this.currenteffort;
    }

    public void setCurrenteffort(BigDecimal currenteffort) {
        this.currenteffort = currenteffort;
    }
    public BigDecimal getProjectid() {
        return this.projectid;
    }

    public void setProjectid(BigDecimal projectid) {
        this.projectid = projectid;
    }
    public BigDecimal getStatusid() {
        return this.statusid;
    }

    public void setStatusid(BigDecimal statusid) {
        this.statusid = statusid;
    }

    /**
     * Get value of stage_str.
     * @return the stage_str
     */
    public String getStage_str() {
        return stage_str;
    }

    /**
     * Set the value for stage_str.
     * @param stage_str the stage_str to set
     */
    public void setStage_str(String stage_str) {
        this.stage_str = stage_str;
    }

    /**
     * Get value of process_str.
     * @return the process_str
     */
    public String getProcess_str() {
        return process_str;
    }

    /**
     * Set the value for process_str.
     * @param process_str the process_str to set
     */
    public void setProcess_str(String process_str) {
        this.process_str = process_str;
    }

    /**
     * Get value of developer_str.
     * @return the developer_str
     */
    public String getDeveloper_str() {
        return developer_str;
    }

    /**
     * Set the value for developer_str.
     * @param developer_str the developer_str to set
     */
    public void setDeveloper_str(String developer_str) {
        this.developer_str = developer_str;
    }

    /**
     * Get value of project_str.
     * @return the project_str
     */
    public String getProject_str() {
        return project_str;
    }

    /**
     * Set the value for project_str.
     * @param project_str the project_str to set
     */
    public void setProject_str(String project_str) {
        this.project_str = project_str;
    }

    /**
     * Get value of startdate_str.
     * @return the startdate_str
     */
    public String getStartdate_str() {
        return startdate_str;
    }

    /**
     * Set the value for startdate_str.
     * @param startdate_str the startdate_str to set
     */
    public void setStartdate_str(String startdate_str) {
        this.startdate_str = startdate_str;
    }

    /**
     * Get value of planDate_str.
     * @return the planDate_str
     */
    public String getPlanDate_str() {
        return planDate_str;
    }

    /**
     * Set the value for planDate_str.
     * @param planDate_str the planDate_str to set
     */
    public void setPlanDate_str(String planDate_str) {
        this.planDate_str = planDate_str;
    }

    /**
     * Get value of visible.
     * @return the visible
     */
    public Boolean getVisible() {
        return visible;
    }

    /**
     * Set the value for visible.
     * @param visible the visible to set
     */
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    /**
     * Get value of actualDate_str.
     * @return the actualDate_str
     */
    public String getActualDate_str() {
        return actualDate_str;
    }

    /**
     * Set the value for actualDate_str.
     * @param actualDate_str the actualDate_str to set
     */
    public void setActualDate_str(String actualDate_str) {
        this.actualDate_str = actualDate_str;
    }

    /**
     * Get value of active.
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Set the value for active.
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Get value of module.
     * @return the module
     */
    public Module getModule() {
        return module;
    }

    /**
     * Set the value for module.
     * @param module the module to set
     */
    public void setModule(Module module) {
        this.module = module;
    }

    /**
     * Get value of sizeunit.
     * @return the sizeunit
     */
    public BigDecimal getSizeunit() {
        return sizeunit;
    }

    /**
     * Set the value for sizeunit.
     * @param sizeunit the sizeunit to set
     */
    public void setSizeunit(BigDecimal sizeunit) {
        this.sizeunit = sizeunit;
    }

}
