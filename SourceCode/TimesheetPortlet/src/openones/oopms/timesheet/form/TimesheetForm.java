package openones.oopms.timesheet.form;

import java.util.List;
import java.util.Map;
import openones.oopms.entity.Timesheet;

public class TimesheetForm {
    Map<String,String> projectMap;
    private String status;
    private String projectDefault;
    private String buttonFlag;
    private String fromDate;
    private String toDate;
    private List<Timesheet> timesheetList;
    
    
    
    
    public List<Timesheet> getTimesheetList() {
        return timesheetList;
    }

    public void setTimesheetList(List<Timesheet> timesheetList) {
        this.timesheetList = timesheetList;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getButtonFlag() {
        return buttonFlag;
    }

    public void setButtonFlag(String buttonFlag) {
        this.buttonFlag = buttonFlag;
    }

    public Map getProjectMap() {
        return projectMap;
    }
   
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getProjectDefault() {
        return projectDefault;
    }
    public void setProjectDefault(String projectDefault) {
        this.projectDefault = projectDefault;
    }
    public void setProjectMap(Map<String, String> projectMap) {
        this.projectMap = projectMap;
    }
    
}
