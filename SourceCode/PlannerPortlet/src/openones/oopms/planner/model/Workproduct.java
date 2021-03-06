package openones.oopms.planner.model;
// Generated Jul 19, 2012 2:23:38 PM by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Workproduct generated by hbm2java
 */
public class Workproduct  implements java.io.Serializable {


     private String code;
     private String name;
     private Byte hasLoc;
     private BigDecimal wpId;
     private BigDecimal process;
     private Byte disabled;
     private String appName;
     private Set modules = new HashSet(0);

    public Workproduct() {
    }

	
    public Workproduct(String code, String name) {
        this.code = code;
        this.name = name;
    }
    public Workproduct(String code, String name, Byte hasLoc, BigDecimal wpId, BigDecimal process, Byte disabled, String appName, Set modules) {
        this.code = code;
        this.name = name;
        this.hasLoc = hasLoc;
        this.wpId = wpId;
        this.process = process;
        this.disabled = disabled;
        this.appName = appName;
        this.modules = modules;
     }
   
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Byte getHasLoc() {
        return this.hasLoc;
    }
    
    public void setHasLoc(Byte hasLoc) {
        this.hasLoc = hasLoc;
    }
    public BigDecimal getWpId() {
        return this.wpId;
    }
    
    public void setWpId(BigDecimal wpId) {
        this.wpId = wpId;
    }
    public BigDecimal getProcess() {
        return this.process;
    }
    
    public void setProcess(BigDecimal process) {
        this.process = process;
    }
    public Byte getDisabled() {
        return this.disabled;
    }
    
    public void setDisabled(Byte disabled) {
        this.disabled = disabled;
    }
    public String getAppName() {
        return this.appName;
    }
    
    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Set getModules() {
        return this.modules;
    }
    
    public void setModules(Set modules) {
        this.modules = modules;
    }


}


