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
package openones.oopms.daocommon;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import openones.oopms.entity.Tasks;

import org.junit.Test;

/**
 * @author PNTG
 *
 */
public class TaskDaoTest {

    /**
     * Test method for {@link openones.oopms.daocommon.TaskDao#getTasksByProjectId(java.lang.String)}.
     */
    @Test
    public void testGetTasksByProjectId() {
        TaskDao dao = new TaskDao();
        List<Tasks> list = dao.getTasksByProjectId(new BigDecimal(118386));
        assertEquals(3, list.size());
    }

}