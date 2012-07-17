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

import java.util.ArrayList;

import openones.oopms.model.Requirements;
import java.util.List;


/**
 * @author KenDa
 *
 */
public class RequirementForm {
    private String title;    
    private List<Requirements> requirementList;
    
    
    public RequirementForm() {
        // TODO Auto-generated constructor stub         

    }
    
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
     * Get value of requirementList.
     * @return the requirementList
     */
    public List<Requirements> getRequirementList() {
        return requirementList;
    }

    /**
     * Set the value for requirementList.
     * @param requirementList the requirementList to set
     */
    public void setRequirementList(List<Requirements> requirementList) {
        this.requirementList = requirementList;
    }

    

 
   
}
