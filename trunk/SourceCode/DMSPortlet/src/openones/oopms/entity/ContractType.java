package openones.oopms.entity;
// Generated 12:18:17 01-03-2012 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;

/**
 * ContractType generated by hbm2java
 */
public class ContractType  implements java.io.Serializable {


     private BigDecimal contractTypeId;
     private String contractTypeName;
     private String contractTypeDescription;
     private Boolean contractTypeStatus;

    public ContractType() {
    }

	
    public ContractType(BigDecimal contractTypeId, String contractTypeName) {
        this.contractTypeId = contractTypeId;
        this.contractTypeName = contractTypeName;
    }
    public ContractType(BigDecimal contractTypeId, String contractTypeName, String contractTypeDescription, Boolean contractTypeStatus) {
       this.contractTypeId = contractTypeId;
       this.contractTypeName = contractTypeName;
       this.contractTypeDescription = contractTypeDescription;
       this.contractTypeStatus = contractTypeStatus;
    }
   
    public BigDecimal getContractTypeId() {
        return this.contractTypeId;
    }
    
    public void setContractTypeId(BigDecimal contractTypeId) {
        this.contractTypeId = contractTypeId;
    }
    public String getContractTypeName() {
        return this.contractTypeName;
    }
    
    public void setContractTypeName(String contractTypeName) {
        this.contractTypeName = contractTypeName;
    }
    public String getContractTypeDescription() {
        return this.contractTypeDescription;
    }
    
    public void setContractTypeDescription(String contractTypeDescription) {
        this.contractTypeDescription = contractTypeDescription;
    }
    public Boolean getContractTypeStatus() {
        return this.contractTypeStatus;
    }
    
    public void setContractTypeStatus(Boolean contractTypeStatus) {
        this.contractTypeStatus = contractTypeStatus;
    }




}

