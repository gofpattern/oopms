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

import java.math.BigDecimal;

import openones.oopms.entity.Project;

/**
 * @author Open-Ones team
 *
 */
public class ProjectEx extends Project {
    private BigDecimal workunitid;
    private BigDecimal parentworkunitid;
    /**
     * Get value of workunitid.
     * @return the workunitid
     */
    public BigDecimal getWorkunitid() {
        return workunitid;
    }
    /**
     * Set the value for workunitid.
     * @param workunitid the workunitid to set
     */
    public void setWorkunitid(BigDecimal workunitid) {
        this.workunitid = workunitid;
    }
    /**
     * Get value of parentworkunitid.
     * @return the parentworkunitid
     */
    public BigDecimal getParentworkunitid() {
        return parentworkunitid;
    }
    /**
     * Set the value for parentworkunitid.
     * @param parentworkunitid the parentworkunitid to set
     */
    public void setParentworkunitid(BigDecimal parentworkunitid) {
        this.parentworkunitid = parentworkunitid;
    }
    
}
