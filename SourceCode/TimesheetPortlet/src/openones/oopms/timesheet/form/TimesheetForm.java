package openones.oopms.timesheet.form;

import java.util.Map;

public class TimesheetForm {
    Map<String,String> projectMap;
    private String status;
    private String projectDefault;
    private String buttonFlag;
    private String fromDate;
    private String toDate;
    
    
    
    
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
