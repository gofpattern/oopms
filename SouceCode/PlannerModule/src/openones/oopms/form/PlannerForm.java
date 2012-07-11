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

import openones.oopms.model.OopmsTask;
import openones.oopms.model.Tasks;


/**
 * @author PNTG
 *
 */
public class PlannerForm {
    private String title;
    public OopmsTask[] taskList;
    
    
    public PlannerForm() {
        // TODO Auto-generated constructor stub
//        OopmsTask task1 = new OopmsTask(4);
//        task1.setTaskname("task number 01");
//        
//        OopmsTask task2 = new OopmsTask(3);
//        task2.setTaskname("task number 02");
//        
//        OopmsTask task3 = new OopmsTask(6);
//        task3.setTaskname("task number 03");     
        

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
     * Get value of taskList.
     * @return the taskList
     */
    public OopmsTask[] getTaskList() {
        return taskList;
    }

    /**
     * Set the value for taskList.
     * @param taskList the taskList to set
     */
    public void setTaskList(OopmsTask[] taskList) {
        this.taskList = taskList;
    }

 
   
}
