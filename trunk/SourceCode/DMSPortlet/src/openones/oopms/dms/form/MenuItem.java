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

/**
 * @author Thach.Le
 */
public class MenuItem implements Serializable {
    private String id;
    private String name;
    private String actionId;
    private String iconPath;

    public MenuItem() {
    }

    public MenuItem(String id, String name, String iconPath, String actionId) {
        this.id = id;
        this.name = name;
        this.iconPath = iconPath;
        this.actionId = actionId;
    }

    /**
     * Get value of id.
     * @return the id
     */
    public String getId() {
        return id;
    }
    /**
     * Set the value for id.
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Get value of name.
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * Set the value for name.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get value of iconPath.
     * @return the iconPath
     */
    public String getIconPath() {
        return iconPath;
    }
    /**
     * Set the value for iconPath.
     * @param iconPath the iconPath to set
     */
    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    /**
     * Get value of actionId.
     * @return the actionId
     */
    public String getActionId() {
        return actionId;
    }
    /**
     * Set the value for actionId.
     * @param actionId the actionId to set
     */
    public void setActionId(String actionId) {
        this.actionId = actionId;
    }
}
