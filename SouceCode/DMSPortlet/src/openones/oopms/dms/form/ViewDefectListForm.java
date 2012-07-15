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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Thach.Le
 */
public class ViewDefectListForm {
    /** Selected project. */
    private String selProject;

    /** List of the project are displayed in combo box Project. */
    private Map<String, String> projectMap;

    /**
     * Get value of projectMap.
     * @return the projectMap
     */
    public Map<String, String> getProjectMap() {
        return projectMap;
    }

    /**
     * Set the value for projectMap.
     * @param projectMap the projectMap to set
     */
    public void setProjectMap(Map<String, String> projectMap) {
        this.projectMap = projectMap;
    }

    /**
     * Add more project which user joins.
     * @param key use for value attribute of HTML tag option of tag select
     * @param value use for label of HTML tag option of tag select
     */
    public void addProject(String key, String value) {
        if (projectMap == null) {
            projectMap = new HashMap<String, String>();
        }

        projectMap.put(key, value);
    }

    /**
     * [Give the description for method].
     * @param projectColl
     */
    public void addProject(Collection<String> projectColl) {
        for (String project : projectColl) {
            addProject(project, project);
        }
    }

    /**
     * Get value of selProject.
     * @return the selProject
     */
    public String getSelProject() {
        return selProject;
    }

    /**
     * Set the value for selProject.
     * @param selProject the selProject to set
     */
    public void setSelProject(String selProject) {
        this.selProject = selProject;
    }
}
