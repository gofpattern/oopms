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
package openones.oopms.dms.form;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Thach.Le
 */
public class CommonInfo implements Serializable {
    /** List of project. */
    private Map<Integer, String> projectMap;

    /**
     * Get value of projectMap.
     * @return the projectMap
     */
    public Map<Integer, String> getProjectMap() {
        return projectMap;
    }

    /**
     * Set the value for projectMap.
     * @param projectMap the projectMap to set
     */
    public void setProjectMap(Map<Integer, String> projectMap) {
        this.projectMap = projectMap;
    }
}
