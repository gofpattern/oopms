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

import openones.oopms.entity.Developer;

import org.junit.Test;

/**
 * @author Open-Ones team
 */
public class DeveloperDaoTest {
    DeveloperDao devDao = new DeveloperDao();
    
    
    @Test
    public void testInsertDeveloper01() {
        Developer dev = new Developer();
        BigDecimal maxId = devDao.getMaxId();
        
        assertNotNull(maxId);
        dev.setDeveloperId(maxId.add(BigDecimal.valueOf(1)));
        dev.setName("User UT 02");
        dev.setGroupName("Open-Ones");
        dev.setAccount("UserUT02");
        dev.setRole("1000000000");
        
        
        boolean ok = devDao.insertDeveloper(dev);
        
        assertEquals(true, ok);
    }
    
    @Test
    public void testInsertDeveloper02() {
        Developer dev = new Developer();

        dev.setName("User UT 05");
        dev.setGroupName("Open-Ones");
        dev.setAccount("UserUT05");
        dev.setRole("1000000000");
        dev.setStatus(BigDecimal.ONE);
        
        
        boolean ok = devDao.insertDeveloper(dev);
        
        assertEquals(true, ok);
    }
    
    /**
     * Test method for {@link openones.oopms.daocommon.DeveloperDao#getDefectSeverity()}.
     */
    @Test
    public void testGetDevelopers() {
        DeveloperDao devDao = new DeveloperDao();

        List<Developer> devList = devDao.getDevelopers();
        assertEquals(11, devList.size());
        Developer dev1 = devList.get(0);
        assertEquals("SYSADMIN", dev1.getAccount());
    }

    /**
     * Test method for {@link openones.oopms.daocommon.DeveloperDao#getDeveloperById(long)}.
     */
    @Test
    public void testGetDeveloper() {
        DeveloperDao devDao = new DeveloperDao();
        Developer dev1 = devDao.getDeveloperById(new BigDecimal(1));

        assertEquals("SYSADMIN", dev1.getAccount());
    }

    public void testGetDeveloperByAccount() {
        DeveloperDao devDao = new DeveloperDao();
        Developer dev1 = devDao.getDeveloperByAccount("TO.CONG.THANH.HAI");

        assertEquals("TO.CONG.THANH.HAI", dev1.getAccount());
        assertEquals("tocongthanhhai@gmail.com", dev1.getEmail());
    }
}
