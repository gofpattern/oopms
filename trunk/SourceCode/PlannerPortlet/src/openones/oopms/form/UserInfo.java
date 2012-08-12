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
package openones.oopms.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Thach.Le
 */
public class UserInfo implements Serializable {
    /** User identifier. */
    private String username;

    private String group;

    /** If roles has data. The position is the first role. */
    private String position;

    private String loginDate;

    /** Roles of user. */
    private Collection<String> roles;

    /**
     * Create instance of UserInfo with authenticated user.
     * @param logonUser
     */
    public UserInfo(String logonUser) {
        this.username = logonUser;
    }
    /**
     * Get value of username.
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    /**
     * Set the value for username.
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Get value of roles.
     * @return the roles
     */
    public Collection<String> getRoles() {
        return roles;
    }
    /**
     * Set the value for roles.
     * @param roles the roles to set
     */
    public void setRoles(Collection<String> roles) {
        this.roles = roles;
    }

    /**
     * Add more role for user.
     * @param role
     */
    public void addRole(String role) {
        if (roles == null) {
            roles = new ArrayList<String>();
        }

        roles.add(role);
    }
    /**
     * Get value of group.
     * @return the group
     */
    public String getGroup() {
        return group;
    }
    /**
     * Set the value for group.
     * @param group the group to set
     */
    public void setGroup(String group) {
        this.group = group;
    }
    /**
     * Get value of position.
     * @return the position
     */
    public String getPosition() {
        if ((roles == null) || (roles.isEmpty())) {
            return position;
        } else {
            return roles.iterator().next();
        }
    }
    /**
     * Set the value for position.
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }
    /**
     * Get value of loginDate.
     * @return the loginDate
     */
    public String getLoginDate() {
        return loginDate;
    }
    /**
     * Set the value for loginDate.
     * @param loginDate the loginDate to set
     */
    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }
}
