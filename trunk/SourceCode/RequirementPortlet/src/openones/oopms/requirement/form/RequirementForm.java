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
package openones.oopms.requirement.form;

import java.util.ArrayList;
import java.util.Map;

import openones.oopms.requirement.model.Requirements;
import java.util.List;


/**
 * @author KenDa
 *
 */
public class RequirementForm {
    Map<String,String> projectMap;    
    private String projectDefault;
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

    public Map getProjectMap() {
        return projectMap;
    }
      
    public String getProjectDefault() {
        return projectDefault;
    }
    public void setProjectDefault(String projectDefault) {
        this.projectDefault = projectDefault;
    }
    public void setProjectMap(Map<String, String> projectMap) {
        this.projectMap = projectMap;
    }

 
   
}
