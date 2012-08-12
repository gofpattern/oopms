/**
 * Licensed to Open-Ones Group under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Open-Ones Group licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package openones.oopms.entity.ex;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * This is non-managed entitiy (of Hibernate) to contains data from native SQL.
 * The properties are set to support Oracle.
 * @see openones.oopms.daocommon.BaseDao.getRoles
 * @author Open-Ones team
 */
public class Right implements Serializable {
    private String RIGHTGROUPID;
    private BigDecimal WORKUNITID;
    private BigDecimal TYPE;
    private String WORKUNITNAME;
    private BigDecimal TABLEID;
    /**
     * Get value of rIGHTGROUPID.
     * @return the rIGHTGROUPID
     */
    public String getRIGHTGROUPID() {
        return RIGHTGROUPID;
    }
    /**
     * Set the value for rIGHTGROUPID.
     * @param rIGHTGROUPID the rIGHTGROUPID to set
     */
    public void setRIGHTGROUPID(String rIGHTGROUPID) {
        RIGHTGROUPID = rIGHTGROUPID;
    }
    /**
     * Get value of wORKUNITID.
     * @return the wORKUNITID
     */
    public BigDecimal getWORKUNITID() {
        return WORKUNITID;
    }
    /**
     * Set the value for wORKUNITID.
     * @param wORKUNITID the wORKUNITID to set
     */
    public void setWORKUNITID(BigDecimal wORKUNITID) {
        WORKUNITID = wORKUNITID;
    }
    /**
     * Get value of tYPE.
     * @return the tYPE
     */
    public BigDecimal getTYPE() {
        return TYPE;
    }
    /**
     * Set the value for tYPE.
     * @param tYPE the tYPE to set
     */
    public void setTYPE(BigDecimal tYPE) {
        TYPE = tYPE;
    }
    /**
     * Get value of wORKUNITNAME.
     * @return the wORKUNITNAME
     */
    public String getWORKUNITNAME() {
        return WORKUNITNAME;
    }
    /**
     * Set the value for wORKUNITNAME.
     * @param wORKUNITNAME the wORKUNITNAME to set
     */
    public void setWORKUNITNAME(String wORKUNITNAME) {
        WORKUNITNAME = wORKUNITNAME;
    }
    /**
     * Get value of tABLEID.
     * @return the tABLEID
     */
    public BigDecimal getTABLEID() {
        return TABLEID;
    }
    /**
     * Set the value for tABLEID.
     * @param tABLEID the tABLEID to set
     */
    public void setTABLEID(BigDecimal tABLEID) {
        TABLEID = tABLEID;
    }
    
}