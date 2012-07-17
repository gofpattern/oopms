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

/**
 * @author Thach.Le
 *
 */
public class DefectForm extends BaseDefectListForm {

    private String title;
    private String projectOrigin;
    private String description;
    private String ref;
    private String qcActivity;
    /**
     * Get value of title.
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    /**
     * Set the value for title.
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * Get value of projectOrigin.
     * @return the projectOrigin
     */
    public String getProjectOrigin() {
        return projectOrigin;
    }
    /**
     * Set the value for projectOrigin.
     * @param projectOrigin the projectOrigin to set
     */
    public void setProjectOrigin(String projectOrigin) {
        this.projectOrigin = projectOrigin;
    }
    /**
     * Get value of description.
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    /**
     * Set the value for description.
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Get value of ref.
     * @return the ref
     */
    public String getRef() {
        return ref;
    }
    /**
     * Set the value for ref.
     * @param ref the ref to set
     */
    public void setRef(String ref) {
        this.ref = ref;
    }
    /**
     * Get value of qcActivity.
     * @return the qcActivity
     */
    public String getQcActivity() {
        return qcActivity;
    }
    /**
     * Set the value for qcActivity.
     * @param qcActivity the qcActivity to set
     */
    public void setQcActivity(String qcActivity) {
        this.qcActivity = qcActivity;
    }
    
}
