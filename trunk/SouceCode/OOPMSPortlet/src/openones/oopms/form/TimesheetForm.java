package openones.oopms.form;

import java.util.Map;

public class TimesheetForm {
    Map<String,String> projectMap;
    private String status;
    private String projectDefault = "All";
    
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
